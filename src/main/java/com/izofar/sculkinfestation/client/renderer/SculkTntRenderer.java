package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.entity.PrimedSculkTnt;
import com.izofar.sculkinfestation.init.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkTntRenderer extends EntityRenderer<PrimedSculkTnt> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/block/skulk_tnt.png");
    private final BlockRenderDispatcher blockRenderManager;

    public SculkTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderManager = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PrimedSculkTnt tntEntity, float f, float g, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.5D, 0.0D);
        int j = tntEntity.getFuse();
        if ((float)j - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float)j - g + 1.0F) / 10.0F;
            h = Mth.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float k = 1.0F + h * 0.3F;
            matrixStack.scale(k, k, k);
        }

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStack.translate(-0.5D, -0.5D, 0.5D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderManager, ModBlocks.SCULK_TNT.get().defaultBlockState(), matrixStack, buffer, packedLight, j / 5 % 2 == 0);
        matrixStack.popPose();
        super.render(tntEntity, f, g, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PrimedSculkTnt tntEntity) {
        return TEXTURE;
    }

}
