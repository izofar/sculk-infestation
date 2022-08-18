package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.SculkHuggerModel;
import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.SculkHugger;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkHuggerRenderer extends MobRenderer<SculkHugger, SculkHuggerModel> implements ISculkEmissive {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculk_hugger/sculk_hugger.png");
    private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculk_hugger/sculk_hugger_glow.png");

    private static final float SCALE = 0.7F;

    public SculkHuggerRenderer(EntityRendererProvider.Context context) {
        super(context, new SculkHuggerModel(SculkHuggerModel.createSpiderBodyLayer().bakeRoot()), 0.8F * SCALE);
        this.addLayer(new SculkEmissiveLayer<>(this, GLOW_TEXTURE, this::getHeartBeatingAlpha, this.getDrawSelector()));
    }

    @Override
    protected void scale(SculkHugger pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(SCALE, SCALE, SCALE);
    }

    @Override
    public ResourceLocation getTextureLocation(SculkHugger entity) {
        return TEXTURE;
    }

    @Override
    public SculkEmissiveLayer.DrawSelector getDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkHugger, SculkHuggerModel>) SculkHuggerModel::getParts;
    }
}
