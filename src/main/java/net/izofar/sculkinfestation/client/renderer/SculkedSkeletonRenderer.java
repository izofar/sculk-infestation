package net.izofar.sculkinfestation.client.renderer;

import net.izofar.sculkinfestation.SculkInfestation;
import net.izofar.sculkinfestation.client.model.SculkedSkeletonModel;
import net.izofar.sculkinfestation.client.renderer.feature.SculkFeatureRenderer;
import net.izofar.sculkinfestation.entity.SculkedSkeleton;
import net.izofar.sculkinfestation.init.ModModelLayers;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SculkedSkeletonRenderer extends BipedEntityRenderer<SculkedSkeleton, SculkedSkeletonModel> {

    private static final Identifier TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_skeleton/sculked_skeleton.png");
    private static final Identifier HEART_LAYER_TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/entity/sculked_skeleton/sculked_skeleton_heart.png");


    public SculkedSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public SculkedSkeletonRenderer(EntityRendererFactory.Context context, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(context, new SculkedSkeletonModel(context.getPart(ModModelLayers.SCULKED_SKELETON)), 0.5f);
        this.addFeature(new ArmorFeatureRenderer(this, new SculkedSkeletonModel(context.getPart(legArmorLayer)), new SculkedSkeletonModel(context.getPart(bodyArmorLayer))));
        this.addFeature(new SculkFeatureRenderer<>(this, HEART_LAYER_TEXTURE, (skulkedSkeleton, tickDelta, animationProgress) -> ((SculkedSkeleton) skulkedSkeleton).getHeartPitch(tickDelta), (SculkFeatureRenderer.ModelPartVisibility<SculkedSkeleton, SculkedSkeletonModel>) SculkedSkeletonModel::getBodyAndHead));
    }

    @Override
    public Identifier getTexture(SculkedSkeleton abstractSkeletonEntity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(SculkedSkeleton abstractSkeletonEntity) { return false; }
}
