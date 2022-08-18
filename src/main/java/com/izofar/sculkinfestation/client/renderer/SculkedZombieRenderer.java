package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.SculkedZombieModel;
import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.SculkedZombie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkedZombieRenderer extends HumanoidMobRenderer<SculkedZombie, SculkedZombieModel> implements ISculkEmissive{

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_zombie/sculked_zombie.png");
    private static final ResourceLocation HEART_LAYER_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_zombie/sculked_zombie_heart.png");
    private static final ResourceLocation PULSATING_SPOTS_1_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_zombie/sculked_zombie_glow_1.png");
    private static final ResourceLocation PULSATING_SPOTS_2_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_zombie/sculked_zombie_glow_2.png");
    
    public SculkedZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new SculkedZombieModel(SculkedZombieModel.createBodyLayer().bakeRoot()), 0.5f);
        this.addLayer(new SculkEmissiveLayer<>(this, PULSATING_SPOTS_1_TEXTURE, this::getFirstPulsatingAlpha, this.getHeadAndLimbsDrawSelector()));
        this.addLayer(new SculkEmissiveLayer<>(this, PULSATING_SPOTS_2_TEXTURE, this::getSecondPulsatingAlpha, this.getHeadAndLimbsDrawSelector()));
        this.addLayer(new SculkEmissiveLayer<>(this, HEART_LAYER_TEXTURE, this::getHeartBeatingAlpha, this.getDrawSelector()));
    }

    @Override
    public ResourceLocation getTextureLocation(SculkedZombie sculkedZombie) {
        return TEXTURE;
    }

    @Override
    public float getFirstPulsatingAlpha(LivingEntity entity, float tickDelta, float animationProgress) {
        return Math.max(0.0f, Mth.cos(animationProgress * 0.045f) * 0.25f);
    }

    @Override
    public float getSecondPulsatingAlpha(LivingEntity entity, float tickDelta, float animationProgress) {
        return Math.max(0.0f, Mth.cos(animationProgress * 0.045f + (float) Math.PI) * 0.25f);
    }

    public SculkEmissiveLayer.DrawSelector getHeadAndLimbsDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkedZombie, SculkedZombieModel>) SculkedZombieModel::getBodyHeadAndLimbs;
    }

    @Override
    public SculkEmissiveLayer.DrawSelector getDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkedZombie, SculkedZombieModel>) SculkedZombieModel::getHeadAndBody;
    }
}
