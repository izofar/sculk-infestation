package com.izofar.sculkinfestation.entity.centipede;

import com.izofar.sculkinfestation.entity.IHurtableMultipart;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

/*
 * Credit to Alex-the-666 for his Centipede Entity from Alex's Mobs!
 * Some changes were done to make it more generic.
 * Source: https://github.com/Alex-the-666/AlexsMobs/blob/1.19/src/main/java/com/github/alexthe666/alexsmobs/entity/CentipedeBody.java
 */
public abstract class CentipedeBody extends Mob implements IHurtableMultipart {

    private static final EntityDataAccessor<Integer> BODYINDEX = SynchedEntityData.defineId(CentipedeBody.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> BODY_XROT = SynchedEntityData.defineId(CentipedeBody.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> SCALE = SynchedEntityData.defineId(CentipedeBody.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.defineId(CentipedeBody.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID = SynchedEntityData.defineId(CentipedeBody.class, EntityDataSerializers.OPTIONAL_UUID);
    public EntityDimensions multipartSize;
    protected float radius;
    protected float angleYaw;
    protected float offsetY;
    protected float damageMultiplier = 1;
    private float parentYaw = 0;
    private double prevHeight = 0;

    protected CentipedeBody(EntityType type, Level worldIn) {
        super(type, worldIn);
        multipartSize = type.getDimensions();
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.getParent() != null;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return  source == DamageSource.IN_WALL || source == DamageSource.FALLING_BLOCK || super.isInvulnerableTo(source);
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public boolean isNoGravity() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        isInsidePortal = false;
        this.setDeltaMovement(Vec3.ZERO);
        if (this.tickCount > 1) {
            Entity parent = getParent();
            refreshDimensions();
            if (parent != null && !level.isClientSide) {
                if (parent instanceof LivingEntity livingEntity && (livingEntity.hurtTime > 0 || livingEntity.deathTime > 0)) {
                    sendHurtToServer(this.getId(), parent.getId(), 0);
                    this.hurtTime = livingEntity.hurtTime;
                    this.deathTime = livingEntity.deathTime;
                }
                if (parent.isRemoved()) {
                    this.remove(RemovalReason.DISCARDED);
                }
            } else if (tickCount > 20 && !level.isClientSide) {
                remove(RemovalReason.DISCARDED);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getParentId() != null) {
            compound.putUUID("ParentUUID", this.getParentId());
        }
        if (this.getChildId() != null) {
            compound.putUUID("ChildUUID", this.getChildId());
        }
        compound.putInt("BodyIndex", getBodyIndex());
        compound.putFloat("PartAngle", angleYaw);
        compound.putFloat("PartRadius", radius);
        compound.putFloat("Scale", this.getScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.hasUUID("ParentUUID")) {
            this.setParentId(compound.getUUID("ParentUUID"));
        }
        if (compound.hasUUID("ChildUUID")) {
            this.setChildId(compound.getUUID("ChildUUID"));
        }
        this.setBodyIndex(compound.getInt("BodyIndex"));
        this.angleYaw = compound.getFloat("PartAngle");
        this.radius = compound.getFloat("PartRadius");
        this.setScale(compound.getFloat("Scale"));
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PARENT_UUID, Optional.empty());
        this.entityData.define(CHILD_UUID, Optional.empty());
        this.entityData.define(BODYINDEX, 0);
        this.entityData.define(BODY_XROT, 0F);
        this.entityData.define(SCALE, 1.0F);
    }

    public Entity getParent() {
        UUID id = getParentId();
        if (id != null && !level.isClientSide) {
            return ((ServerLevel) level).getEntity(id);
        }
        return null;
    }

    public void setParent(Entity entity) {
        this.setParentId(entity.getUUID());
    }

    public Entity getChild() {
        UUID id = getChildId();
        if (id != null && !level.isClientSide) {
            return ((ServerLevel) level).getEntity(id);
        }
        return null;
    }

    @Nullable
    public UUID getChildId() {
        return this.entityData.get(CHILD_UUID).orElse(null);
    }

    public void setChildId(@Nullable UUID uniqueId) {
        this.entityData.set(CHILD_UUID, Optional.ofNullable(uniqueId));
    }

    @Override
    public boolean is(net.minecraft.world.entity.Entity entity) {
        return this == entity || this.getParent() == entity;
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        Entity parent = getParent();
        boolean prev = parent != null && parent.hurt(source, damage * this.damageMultiplier);
        if (prev && !level.isClientSide) {
            sendHurtToServer(this.getId(), parent.getId(), damage * this.damageMultiplier);
        }
        return prev;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void pushEntities() {
        List<Entity> entities = this.level.getEntities(this, this.getBoundingBox().expandTowards(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        Entity parent = this.getParent();
        if (parent != null) {
            entities.stream().filter(entity -> entity != parent && !(entity instanceof CentipedeBody) && entity.isPushable()).forEach(entity -> entity.push(parent));

        }
    }

    @Override
    public boolean startRiding(Entity entityIn) {
        if(!(entityIn instanceof AbstractMinecart || entityIn instanceof Boat)){
            return super.startRiding(entityIn);
        }
        return false;
    }

    public int getBodyIndex() {
        return this.entityData.get(BODYINDEX);
    }

    public void setBodyIndex(int index) {
        this.entityData.set(BODYINDEX, index);
    }

    @Nullable
    public UUID getParentId() {
        return this.entityData.get(PARENT_UUID).orElse(null);
    }

    public void setParentId(@Nullable UUID uniqueId) {
        this.entityData.set(PARENT_UUID, Optional.ofNullable(uniqueId));
    }

    public Vec3 tickMultipartPosition(int headId, float parentOffset, Vec3 parentPosition, float parentXRot, float ourYRot, boolean doHeight) {
        float yDif = doHeight ? 1.0F - 0.95F * (float)Math.min(Math.abs(parentPosition.y - this.getY()), 1.0F) : 1F;
        Vec3 parentFront = parentPosition.add(calcOffsetVec(yDif * parentOffset, parentXRot, ourYRot));
        Vec3 parentButt = parentPosition.add(calcOffsetVec(yDif * -parentOffset, parentXRot, ourYRot));
        Vec3 ourButt = parentButt.add(calcOffsetVec((yDif * -getBackOffset() - 0.5F * this.getBbWidth()) * this.getScale(), this.getXRot(), ourYRot));
        Vec3 avg = new Vec3((parentButt.x + ourButt.x) / 2F, (parentButt.y + ourButt.y) / 2F, (parentButt.z + ourButt.z) / 2F);
        double d0 = parentButt.x - ourButt.x;
        double d1 = parentButt.y - ourButt.y;
        double d2 = parentButt.z - ourButt.z;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        double hgt = doHeight ? (getLowPartHeight(parentButt.x, parentButt.y, parentButt.z) + getHighPartHeight(ourButt.x, ourButt.y, ourButt.z)) : 0;
        if(Math.abs(prevHeight - hgt) > 0.2){
            prevHeight = hgt;
        }
        if(!isOpaqueBlockAt(parentFront.x,parentFront.y + 0.4F, parentFront.z) && Math.abs(prevHeight) > 1){
            prevHeight = 0;
        }
        double partYDest = Mth.clamp(prevHeight, -0.4F, 0.4F);
        float f = (float) (Mth.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
        float rawAngle = Mth.wrapDegrees((float) (-(Mth.atan2(partYDest, d3) * (double) (180F / (float) Math.PI))));
        float f2 = this.limitAngle(this.getXRot(), rawAngle, 10);
        this.setXRot(f2);
        this.entityData.set(BODY_XROT, f2);
        this.setYRot(f);
        this.yHeadRot = f;
        this.moveTo(avg.x, avg.y, avg.z, f, f2);
        return avg;
    }

    @Override
    public float getXRot() {
        return this.entityData.get(BODY_XROT);
    }

    public double getLowPartHeight(double x, double yIn, double z) {
        if(isFluidAt(x, yIn, z)){
            return 0.0;
        }
        double checkAt = 0D;
        while (checkAt > -3D && !isOpaqueBlockAt(x,yIn + checkAt, z)) {
            checkAt -= 0.2D;
        }
        return checkAt;
    }

    public double getHighPartHeight(double x, double yIn, double z) {
        if(isFluidAt(x, yIn, z)){
            return 0.0;
        }
        double checkAt = 0D;
        while (checkAt <= 3) {
            if(isOpaqueBlockAt(x, yIn + checkAt, z)) {
                checkAt += 0.2D;
            }else{
                break;
            }
        }
        return checkAt;
    }

    public boolean isFluidAt(double x, double y, double z) {
        if (this.noPhysics) {
            return false;
        } else {
            return !level.getFluidState(new BlockPos(x, y, z)).isEmpty();
        }
    }

    public boolean isOpaqueBlockAt(double x, double y, double z) {
        if (this.noPhysics) {
            return false;
        } else {
            float f = 1F;
            Vec3 vec3 = new Vec3(x, y, z);
            AABB axisalignedbb = AABB.ofSize(vec3, f, 1.0E-6D, f);
            return this.level.getBlockStates(axisalignedbb).filter(Predicate.not(BlockBehaviour.BlockStateBase::isAir)).anyMatch((p_185969_) -> {
                BlockPos blockpos = new BlockPos(vec3);
                return p_185969_.isSuffocating(this.level, blockpos) && Shapes.joinIsNotEmpty(p_185969_.getCollisionShape(this.level, blockpos).move(vec3.x, vec3.y, vec3.z), Shapes.create(axisalignedbb), BooleanOp.AND);
            });
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public float getBackOffset() {
        return 0.5F * this.getScale();
    }

    @Override
    public void onAttackedFromServer(LivingEntity parent, float damage, DamageSource damageSource) {
        if(parent.deathTime > 0){
            this.deathTime = parent.deathTime;
        }
        if(parent.hurtTime > 0){
            this.hurtTime = parent.hurtTime;
        }
    }

    @Override
    public float getScale(){
        return this.entityData.get(SCALE);
    }

    protected void setScale(float scale){
        this.entityData.set(SCALE, scale);
    }

    private static void sendHurtToServer(int part, int parent, float damage){
        // TODO AlexsMobs.sendMSGToAll(new MessageHurtMultipart(part, parent, damage));
    }
}
