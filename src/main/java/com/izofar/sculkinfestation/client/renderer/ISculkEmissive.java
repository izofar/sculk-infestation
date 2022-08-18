package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.ISculkEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public interface ISculkEmissive {

    default float getFirstPulsatingAlpha(LivingEntity entity, float tickDelta, float animationProgress) {
        return Math.max(0.0f, Mth.cos(animationProgress * 0.045f) * 0.25f);
    }

    default float getSecondPulsatingAlpha(LivingEntity entity, float tickDelta, float animationProgress) {
        return Math.max(0.0f, Mth.cos(animationProgress * 0.045f + (float) Math.PI) * 0.25f);
    }

    default float getHeartBeatingAlpha(LivingEntity entity, float tickDelta, float animationProgress){
        return ((ISculkEntity) entity).getHeartPitch(tickDelta);
    }
    SculkEmissiveLayer.DrawSelector getDrawSelector();
}
