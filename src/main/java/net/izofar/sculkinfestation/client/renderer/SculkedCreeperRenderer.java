package net.izofar.sculkinfestation.client.renderer;

import net.izofar.sculkinfestation.SculkInfestation;
import net.izofar.sculkinfestation.client.renderer.feature.SculkFeatureRenderer;
import net.izofar.sculkinfestation.init.ModModelLayers;
import net.izofar.sculkinfestation.client.model.SculkedCreeperModel;
import net.izofar.sculkinfestation.entity.SculkedCreeper;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SculkedCreeperRenderer extends MobEntityRenderer<SculkedCreeper, SculkedCreeperModel> {

    private static final Identifier TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_creeper/sculked_creeper.png");
    private static final Identifier EXPLODE_LAYER_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_creeper/sculked_creeper_explode.png");
    private static final Identifier HEART_LAYER_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_creeper/sculked_creeper_heart.png");
    private static final Identifier PULSATING_SPOTS_1_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_creeper/sculked_creeper_glow_1.png");
    private static final Identifier PULSATING_SPOTS_2_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_creeper/sculked_creeper_glow_2.png");

    public SculkedCreeperRenderer(EntityRendererFactory.Context context) {
        super(context, new SculkedCreeperModel(context.getPart(ModModelLayers.SCULKED_CREEPER)), 0.5F);
        this.addFeature(new SculkFeatureRenderer<>(this, PULSATING_SPOTS_1_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f) * 0.25f), (SculkFeatureRenderer.ModelPartVisibility<SculkedCreeper, SculkedCreeperModel>) SculkedCreeperModel::getBodyHeadAndLimbs));
        this.addFeature(new SculkFeatureRenderer<>(this, PULSATING_SPOTS_2_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f + (float) Math.PI) * 0.25f), (SculkFeatureRenderer.ModelPartVisibility<SculkedCreeper, SculkedCreeperModel>) SculkedCreeperModel::getBodyHeadAndLimbs));
        this.addFeature(new SculkFeatureRenderer<>(this, EXPLODE_LAYER_TEXTURE, (skulkedCreeper, tickDelta, animationProgress) -> Float.min(2.5F * ((SculkedCreeper) skulkedCreeper).getClientFuseTime(1), 1.0F), (SculkFeatureRenderer.ModelPartVisibility<SculkedCreeper, SculkedCreeperModel>) SculkedCreeperModel::getHeadAndLimbs));
        this.addFeature(new SculkFeatureRenderer<>(this, HEART_LAYER_TEXTURE, (skulkedCreeper, tickDelta, animationProgress) -> ((SculkedCreeper) skulkedCreeper).getHeartPitch(tickDelta), (SculkFeatureRenderer.ModelPartVisibility<SculkedCreeper, SculkedCreeperModel>) SculkedCreeperModel::getBody));
    }

    @Override
    public Identifier getTexture(SculkedCreeper entity) {
        return TEXTURE;
    }
}
