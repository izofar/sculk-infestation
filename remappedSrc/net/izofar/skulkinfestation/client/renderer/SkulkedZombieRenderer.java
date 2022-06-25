package net.izofar.sculkinfestation.client.renderer;

import net.izofar.sculkinfestation.SkulkInfestation;
import net.izofar.sculkinfestation.client.model.SkulkedZombieModel;
import net.izofar.sculkinfestation.client.renderer.feature.SkulkFeatureRenderer;
import net.izofar.sculkinfestation.entity.SkulkedZombie;
import net.izofar.sculkinfestation.init.ModModelLayers;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SkulkedZombieRenderer extends BipedEntityRenderer<SkulkedZombie, SkulkedZombieModel> {

    private static final Identifier TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_zombie/skulked_zombie.png");
    private static final Identifier HEART_LAYER_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_zombie/skulked_zombie_heart.png");
    private static final Identifier PULSATING_SPOTS_1_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_zombie/skulked_zombie_glow_1.png");
    private static final Identifier PULSATING_SPOTS_2_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_zombie/skulked_zombie_glow_2.png");
    
    public SkulkedZombieRenderer(EntityRendererFactory.Context context) {
        super(context, new SkulkedZombieModel(context.getPart(ModModelLayers.SKULKED_ZOMBIE)), 0.5f);
        this.addFeature(new SkulkFeatureRenderer<>(this, PULSATING_SPOTS_1_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f) * 0.25f), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedZombie, SkulkedZombieModel>) SkulkedZombieModel::getBodyHeadAndLimbs));
        this.addFeature(new SkulkFeatureRenderer<>(this, PULSATING_SPOTS_2_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f + (float) Math.PI) * 0.25f), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedZombie, SkulkedZombieModel>) SkulkedZombieModel::getBodyHeadAndLimbs));
        this.addFeature(new SkulkFeatureRenderer<>(this, HEART_LAYER_TEXTURE, (skulkedZombie, tickDelta, animationProgress) -> ((SkulkedZombie) skulkedZombie).getHeartPitch(tickDelta), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedZombie, SkulkedZombieModel>) SkulkedZombieModel::getHeadAndBody));
    }

    @Override
    public Identifier getTexture(SkulkedZombie skulkedZombie) {
        return TEXTURE;
    }
}
