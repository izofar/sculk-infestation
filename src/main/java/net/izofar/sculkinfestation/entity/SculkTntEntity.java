package net.izofar.sculkinfestation.entity;

import net.izofar.sculkinfestation.init.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class SculkTntEntity extends TntEntity {

    private final float power = 8.0F;
    @Nullable
    private LivingEntity causingEntity;

    public SculkTntEntity(EntityType<? extends SculkTntEntity> entityType, World world) { super(entityType, world); }

    private SculkTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntityTypes.SCULK_TNT, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(-Math.sin(d) * 0.02D, 0.20000000298023224D, -Math.cos(d) * 0.02D);
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    public static SculkTntEntity of(World world, double x, double y, double z, @Nullable LivingEntity igniter){
        return new SculkTntEntity(world, x, y, z, igniter);
    }

    @Override
    public void tick(){
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98D));
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.world.isClient) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), this.power, Explosion.DestructionType.BREAK);
    }
}
