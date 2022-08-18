package com.izofar.sculkinfestation.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class SculkedZombie extends Zombie implements ISculkEntity {

    private int heartCounter;
    private int heartBase;

    public SculkedZombie(EntityType<? extends Zombie> entityType, Level level) {
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
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ARMOR, 2.0f)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
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
