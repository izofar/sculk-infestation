package com.izofar.sculkinfestation.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;

public class SculkedSpider extends CaveSpider implements ISculkEntity{

    private int twitchCounter;
    private int twitchBase;
    private int heartCounter;
    private int heartBase;

    public SculkedSpider(EntityType<? extends CaveSpider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
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
            // Change to other property when tendrils added
            int currentFuseTime = Math.round(0);
            if(currentFuseTime == 1) twitchCounter = 10;
            this.twitchBase = this.twitchCounter;
            if (this.twitchCounter > 0) {
                --this.twitchCounter;
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Spider.createAttributes();
    }

    private int getHeartRate() {
        float f = 0.1F;
        return 40 - Mth.floor(Mth.clamp(f, 0.0f, 1.0f) * 30.0f);
    }

    @Override
    public float getHeartPitch(float tickDelta) {
        return Mth.lerp(tickDelta, this.heartBase, this.heartCounter) / 10.0f;
    }
}
