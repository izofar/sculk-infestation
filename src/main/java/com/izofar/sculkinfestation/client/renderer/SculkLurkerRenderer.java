package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.SculkLurkerModel;
import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.SculkLurker;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkLurkerRenderer extends MobRenderer<SculkLurker, SculkLurkerModel> implements ISculkEmissive {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculk_lurker/sculk_lurker.png");
    private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculk_lurker/sculk_lurker_glow.png");

    public SculkLurkerRenderer(EntityRendererProvider.Context context) {
        super(context, new SculkLurkerModel(SculkLurkerModel.createSpiderBodyLayer().bakeRoot()), 0.8F);
        this.addLayer(new SculkEmissiveLayer<>(this, GLOW_TEXTURE, this::getHeartBeatingAlpha, this.getDrawSelector()));
    }

    @Override
    protected void scale(SculkLurker entity, PoseStack matrixStack, float partialTickTime) {
        matrixStack.scale(0.5F, 0.5F, 0.5F);
    }

    @Override
    public SculkEmissiveLayer.DrawSelector getDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkLurker, SculkLurkerModel>) SculkLurkerModel::getParts;
    }

    @Override
    public ResourceLocation getTextureLocation(SculkLurker entity) {
        return TEXTURE;
    }
}
