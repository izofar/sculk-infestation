package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.SculkedCreeperModel;
import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.SculkedCreeper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkedCreeperRenderer extends MobRenderer<SculkedCreeper, SculkedCreeperModel> implements ISculkEmissive{

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_creeper/sculked_creeper.png");
    private static final ResourceLocation EXPLODE_LAYER_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_creeper/sculked_creeper_explode.png");
    private static final ResourceLocation HEART_LAYER_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_creeper/sculked_creeper_heart.png");
    private static final ResourceLocation PULSATING_SPOTS_1_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_creeper/sculked_creeper_glow_1.png");
    private static final ResourceLocation PULSATING_SPOTS_2_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_creeper/sculked_creeper_glow_2.png");

    public SculkedCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new SculkedCreeperModel(SculkedCreeperModel.createBodyLayer().bakeRoot()), 0.5F);
        this.addLayer(new SculkEmissiveLayer<>(this, PULSATING_SPOTS_1_TEXTURE, this::getFirstPulsatingAlpha, this.getHeadBodyAndLimbsDrawSelector()));
        this.addLayer(new SculkEmissiveLayer<>(this, PULSATING_SPOTS_2_TEXTURE, this::getSecondPulsatingAlpha, this.getHeadBodyAndLimbsDrawSelector()));
        this.addLayer(new SculkEmissiveLayer<>(this, EXPLODE_LAYER_TEXTURE, this::getExplosionAlpha, this.getHeadAndLimbsDrawSelector()));
        this.addLayer(new SculkEmissiveLayer<>(this, HEART_LAYER_TEXTURE, this::getHeartBeatingAlpha, this.getDrawSelector()));
    }

    @Override
    public ResourceLocation getTextureLocation(SculkedCreeper entity) {
        return TEXTURE;
    }

    public SculkEmissiveLayer.DrawSelector getHeadBodyAndLimbsDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkedCreeper, SculkedCreeperModel>) SculkedCreeperModel::getBodyHeadAndLimbs;
    }

    public SculkEmissiveLayer.DrawSelector getHeadAndLimbsDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkedCreeper, SculkedCreeperModel>) SculkedCreeperModel::getHeadAndLimbs;
    }

    public float getExplosionAlpha(LivingEntity entity, float tickDelta, float animationProgress) {
        return Float.min(2.5F * ((SculkedCreeper) entity).getSwelling(1), 1.0F);
    }

    @Override
    public SculkEmissiveLayer.DrawSelector getDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkedCreeper, SculkedCreeperModel>) SculkedCreeperModel::getBody;
    }

}
