package net.izofar.skulkinfestation.client.renderer;

import net.izofar.skulkinfestation.SkulkInfestation;
import net.izofar.skulkinfestation.client.renderer.feature.SkulkFeatureRenderer;
import net.izofar.skulkinfestation.init.ModModelLayers;
import net.izofar.skulkinfestation.client.model.SkulkedCreeperModel;
import net.izofar.skulkinfestation.entity.SkulkedCreeper;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SkulkedCreeperRenderer extends MobEntityRenderer<SkulkedCreeper, SkulkedCreeperModel> {

    private static final Identifier TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_creeper/skulked_creeper.png");
    private static final Identifier EXPLODE_LAYER_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_creeper/skulked_creeper_explode.png");
    private static final Identifier HEART_LAYER_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_creeper/skulked_creeper_heart.png");
    private static final Identifier PULSATING_SPOTS_1_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_creeper/skulked_creeper_glow_1.png");
    private static final Identifier PULSATING_SPOTS_2_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_creeper/skulked_creeper_glow_2.png");

    public SkulkedCreeperRenderer(EntityRendererFactory.Context context) {
        super(context, new SkulkedCreeperModel(context.getPart(ModModelLayers.SKULKED_CREEPER)), 0.5F);
        this.addFeature(new SkulkFeatureRenderer<>(this, PULSATING_SPOTS_1_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f) * 0.25f), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedCreeper, SkulkedCreeperModel>) SkulkedCreeperModel::getBodyHeadAndLimbs));
        this.addFeature(new SkulkFeatureRenderer<>(this, PULSATING_SPOTS_2_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f + (float) Math.PI) * 0.25f), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedCreeper, SkulkedCreeperModel>) SkulkedCreeperModel::getBodyHeadAndLimbs));
        this.addFeature(new SkulkFeatureRenderer<>(this, EXPLODE_LAYER_TEXTURE, (skulkedCreeper, tickDelta, animationProgress) -> Float.min(2.5F * ((SkulkedCreeper) skulkedCreeper).getClientFuseTime(1), 1.0F), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedCreeper, SkulkedCreeperModel>) SkulkedCreeperModel::getHeadAndLimbs));
        this.addFeature(new SkulkFeatureRenderer<>(this, HEART_LAYER_TEXTURE, (skulkedCreeper, tickDelta, animationProgress) -> ((SkulkedCreeper) skulkedCreeper).getHeartPitch(tickDelta), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedCreeper, SkulkedCreeperModel>) SkulkedCreeperModel::getBody));
    }

    @Override
    public Identifier getTexture(SkulkedCreeper entity) {
        return TEXTURE;
    }
}
