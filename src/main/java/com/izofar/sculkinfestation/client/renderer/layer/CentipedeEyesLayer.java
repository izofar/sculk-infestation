package com.izofar.sculkinfestation.client.renderer.layer;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.renderer.centipede.CentipedeHeadRenderer;
import com.izofar.sculkinfestation.entity.centipede.CentipedeHead;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Credit to Alex-the-666 for his Centipede Entity from Alex's Mobs!
 * Some changes were done to use custom resources.
 * Source: https://github.com/Alex-the-666/AlexsMobs/blob/1.19/src/main/java/com/github/alexthe666/alexsmobs/client/render/layer/LayerCentipedeHeadEyes.java
 */
@OnlyIn(Dist.CLIENT)
public class CentipedeEyesLayer extends RenderLayer<CentipedeHead, AdvancedEntityModel<CentipedeHead>> {

    public CentipedeEyesLayer(CentipedeHeadRenderer render) {
        super(render);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, CentipedeHead entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.eyes(getEyes(entitylivingbaseIn)));
        this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static ResourceLocation getEyes(CentipedeHead entity){
        return new ResourceLocation(SculkInfestation.MODID, CentipedeHeadRenderer.CENTIPEDE_TEXTURE_MAP.get(entity.getType()) + "_eyes.png");
    }

}
