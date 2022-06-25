package net.izofar.sculkinfestation.client.renderer;

import net.izofar.sculkinfestation.SculkInfestation;
import net.izofar.sculkinfestation.client.model.SculkedZombieModel;
import net.izofar.sculkinfestation.client.renderer.feature.SculkFeatureRenderer;
import net.izofar.sculkinfestation.entity.SculkedZombie;
import net.izofar.sculkinfestation.init.ModModelLayers;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SculkedZombieRenderer extends BipedEntityRenderer<SculkedZombie, SculkedZombieModel> {

    private static final Identifier TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_zombie/sculked_zombie.png");
    private static final Identifier HEART_LAYER_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_zombie/sculked_zombie_heart.png");
    private static final Identifier PULSATING_SPOTS_1_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_zombie/sculked_zombie_glow_1.png");
    private static final Identifier PULSATING_SPOTS_2_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_zombie/sculked_zombie_glow_2.png");
    
    public SculkedZombieRenderer(EntityRendererFactory.Context context) {
        super(context, new SculkedZombieModel(context.getPart(ModModelLayers.SCULKED_ZOMBIE)), 0.5f);
        this.addFeature(new SculkFeatureRenderer<>(this, PULSATING_SPOTS_1_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f) * 0.25f), (SculkFeatureRenderer.ModelPartVisibility<SculkedZombie, SculkedZombieModel>) SculkedZombieModel::getBodyHeadAndLimbs));
        this.addFeature(new SculkFeatureRenderer<>(this, PULSATING_SPOTS_2_TEXTURE, (warden, tickDelta, animationProgress) -> Math.max(0.0f, MathHelper.cos(animationProgress * 0.045f + (float) Math.PI) * 0.25f), (SculkFeatureRenderer.ModelPartVisibility<SculkedZombie, SculkedZombieModel>) SculkedZombieModel::getBodyHeadAndLimbs));
        this.addFeature(new SculkFeatureRenderer<>(this, HEART_LAYER_TEXTURE, (skulkedZombie, tickDelta, animationProgress) -> ((SculkedZombie) skulkedZombie).getHeartPitch(tickDelta), (SculkFeatureRenderer.ModelPartVisibility<SculkedZombie, SculkedZombieModel>) SculkedZombieModel::getHeadAndBody));
    }

    @Override
    public Identifier getTexture(SculkedZombie sculkedZombie) {
        return TEXTURE;
    }
}
