package com.izofar.sculkinfestation.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SculkedCreeper extends Creeper implements ISculkEntity {

    private int twitchCounter;
    private int twitchBase;
    private int heartCounter;
    private int heartBase;

    public SculkedCreeper(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick(){
        super.tick();
        Level level = this.getLevel();
        if(level.isClientSide()){
            if (this.tickCount % this.getHeartRate() == 0) {
                this.heartCounter = 10;
                if (!this.isSilent()) {
                    level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.WARDEN_HEARTBEAT, this.getSoundSource(), 0.1f, this.getVoicePitch(), false);
                }
            }
            this.heartBase = this.heartCounter;
            if (this.heartCounter > 0) {
                --this.heartCounter;
            }
            int currentFuseTime = Math.round(this.getSwelling(1));
            if(currentFuseTime == 1) twitchCounter = 10;
            this.twitchBase = this.twitchCounter;
            if (this.twitchCounter > 0) {
                --this.twitchCounter;
            }
        }
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3f);
    }

    private int getHeartRate() {
        float f = 0.1F;
        return 40 - Mth.floor(Mth.clamp(f, 0.0f, 1.0f) * 30.0f);
    }

    public float getAgeInTicks(){
        return this.tickCount;
    }

    public float getTendrilPitch(float tickDelta) {
        return Mth.lerp(tickDelta, this.twitchBase, this.twitchCounter) / 10.0f;
    }

    public float getHeartPitch(float tickDelta) {
        return Mth.lerp(tickDelta, this.heartBase, this.heartCounter) / 10.0f;
    }

}
