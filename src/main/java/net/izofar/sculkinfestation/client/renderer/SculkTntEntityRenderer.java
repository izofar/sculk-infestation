package net.izofar.sculkinfestation.client.renderer;

import net.izofar.sculkinfestation.SculkInfestation;
import net.izofar.sculkinfestation.entity.SculkTntEntity;
import net.izofar.sculkinfestation.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class SculkTntEntityRenderer extends EntityRenderer<SculkTntEntity> {

    private static final Identifier TEXTURE = new Identifier(SculkInfestation.MOD_ID, "textures/block/skulk_tnt.png");
    private final BlockRenderManager blockRenderManager;

    public SculkTntEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.blockRenderManager = context.getBlockRenderManager();
    }

    @Override
    public void render(SculkTntEntity tntEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.5D, 0.0D);
        int j = tntEntity.getFuse();
        if ((float)j - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float)j - g + 1.0F) / 10.0F;
            h = MathHelper.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float k = 1.0F + h * 0.3F;
            matrixStack.scale(k, k, k);
        }

        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
        matrixStack.translate(-0.5D, -0.5D, 0.5D);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        renderFlashingBlock(this.blockRenderManager, ModBlocks.SCULK_TNT.getDefaultState(), matrixStack, vertexConsumerProvider, i, j / 5 % 2 == 0);
        matrixStack.pop();
        super.render(tntEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static void renderFlashingBlock(BlockRenderManager blockRenderManager, BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean drawFlash) {
        int i;
        if (drawFlash) {
            i = OverlayTexture.packUv(OverlayTexture.getU(1.0F), 10);
        } else {
            i = OverlayTexture.DEFAULT_UV;
        }

        blockRenderManager.renderBlockAsEntity(state, matrices, vertexConsumers, light, i);
    }

    @Override
    public Identifier getTexture(SculkTntEntity tntEntity) {
        return TEXTURE;
    }

}
