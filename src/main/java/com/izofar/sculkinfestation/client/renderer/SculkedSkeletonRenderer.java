package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.SculkedSkeletonModel;
import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.SculkedSkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkedSkeletonRenderer extends HumanoidMobRenderer<SculkedSkeleton, SculkedSkeletonModel> implements ISculkEmissive{

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_skeleton/sculked_skeleton.png");
    private static final ResourceLocation HEART_LAYER_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_skeleton/sculked_skeleton_heart.png");


    public SculkedSkeletonRenderer(EntityRendererProvider.Context context) {
        this(context, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    public SculkedSkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation legArmorLayer, ModelLayerLocation bodyArmorLayer) {
        super(context, new SculkedSkeletonModel(SculkedSkeletonModel.createBodyLayer().bakeRoot()), 0.5f);
        this.addLayer(new HumanoidArmorLayer(this, new SculkedSkeletonModel(context.bakeLayer(legArmorLayer)), new SculkedSkeletonModel(context.bakeLayer(bodyArmorLayer))));
        this.addLayer(new SculkEmissiveLayer<>(this, HEART_LAYER_TEXTURE, this::getHeartBeatingAlpha, this.getDrawSelector()));
    }

    @Override
    public ResourceLocation getTextureLocation(SculkedSkeleton abstractSkeletonEntity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(SculkedSkeleton abstractSkeletonEntity) { return false; }

    @Override
    public SculkEmissiveLayer.DrawSelector getDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkedSkeleton, SculkedSkeletonModel>) SculkedSkeletonModel::getBodyAndHead;
    }
}
