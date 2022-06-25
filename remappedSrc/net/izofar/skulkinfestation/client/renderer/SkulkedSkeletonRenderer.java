package net.izofar.sculkinfestation.client.renderer;

import net.izofar.sculkinfestation.SkulkInfestation;
import net.izofar.sculkinfestation.client.model.SkulkedSkeletonModel;
import net.izofar.sculkinfestation.client.renderer.feature.SkulkFeatureRenderer;
import net.izofar.sculkinfestation.entity.SkulkedSkeleton;
import net.izofar.sculkinfestation.init.ModModelLayers;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SkulkedSkeletonRenderer extends BipedEntityRenderer<SkulkedSkeleton, SkulkedSkeletonModel> {

    private static final Identifier TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_skeleton/skulked_skeleton.png");
    private static final Identifier HEART_LAYER_TEXTURE = new Identifier(SkulkInfestation.MOD_ID, "textures/entity/skulked_skeleton/skulked_skeleton_heart.png");


    public SkulkedSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public SkulkedSkeletonRenderer(EntityRendererFactory.Context context, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(context, new SkulkedSkeletonModel(context.getPart(ModModelLayers.SKULKED_SKELETON)), 0.5f);
        this.addFeature(new ArmorFeatureRenderer(this, new SkulkedSkeletonModel(context.getPart(legArmorLayer)), new SkulkedSkeletonModel(context.getPart(bodyArmorLayer))));
        this.addFeature(new SkulkFeatureRenderer<>(this, HEART_LAYER_TEXTURE, (skulkedSkeleton, tickDelta, animationProgress) -> ((SkulkedSkeleton) skulkedSkeleton).getHeartPitch(tickDelta), (SkulkFeatureRenderer.ModelPartVisibility<SkulkedSkeleton, SkulkedSkeletonModel>) SkulkedSkeletonModel::getBodyAndHead));
    }

    @Override
    public Identifier getTexture(SkulkedSkeleton abstractSkeletonEntity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(SkulkedSkeleton abstractSkeletonEntity) { return false; }
}
