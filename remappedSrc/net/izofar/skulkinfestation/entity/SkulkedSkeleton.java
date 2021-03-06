package net.izofar.sculkinfestation.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SkulkedSkeleton extends SkeletonEntity implements SkulkEntity{

    private int twitchCounter;
    private int twitchBase;
    private int heartCounter;
    private int heartBase;

    public SkulkedSkeleton(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick(){
        super.tick();
        World world = this.world;
        if(world.isClient()){
            if (this.age % this.getHeartRate() == 0) {
                this.heartCounter = 10;
                if (!this.isSilent()) {
                    this.world.playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, this.getSoundCategory(), 0.1f, this.getSoundPitch(), false);
                }
            }
            this.heartBase = this.heartCounter;
            if (this.heartCounter > 0) {
                --this.heartCounter;
            }
            // Change to other property when tendrils added
            int currentFuseTime = Math.round(0);
            if(currentFuseTime == 1) twitchCounter = 10;
            this.twitchBase = this.twitchCounter;
            if (this.twitchCounter > 0) {
                --this.twitchCounter;
            }
        }
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }

    private int getHeartRate() {
        float f = 0.1F;
        return 40 - MathHelper.floor(MathHelper.clamp(f, 0.0f, 1.0f) * 30.0f);
    }

    public float getHeartPitch(float tickDelta) {
        return MathHelper.lerp(tickDelta, this.heartBase, this.heartCounter) / 10.0f;
    }
}
