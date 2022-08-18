package com.izofar.sculkinfestation.client.renderer.layer;

import com.izofar.sculkinfestation.client.model.IPartedModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SculkEmissiveLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ResourceLocation texture;
    private final AlphaFunction<T> alphaFunction;
    private final DrawSelector<T, M> drawSelector;

    public SculkEmissiveLayer(RenderLayerParent<T, M> context, ResourceLocation texture, AlphaFunction alphaFunction, DrawSelector drawSelector) {
        super(context);
        this.texture = texture;
        this.alphaFunction = alphaFunction;
        this.drawSelector = drawSelector;
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T sculkEntity, float f, float g, float h, float j, float k, float l) {
        if (sculkEntity.isInvisible()) return;
        this.onlyDrawSelectedParts();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityTranslucentEmissive(this.texture));
        this.getParentModel().renderToBuffer(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlayCoords(sculkEntity, 0.0f), 1.0f, 1.0f, 1.0f, this.alphaFunction.apply(sculkEntity, h, j));
        this.resetDrawForAllParts();
    }

    private void onlyDrawSelectedParts() {
        List<ModelPart> list = this.drawSelector.getPartsToDraw(this.getParentModel());
        ((IPartedModel)this.getParentModel()).getPart().getAllParts().forEach(part -> part.skipDraw = true);
        list.forEach(part -> part.skipDraw = false);
    }

    private void resetDrawForAllParts() {
        ((IPartedModel)this.getParentModel()).getPart().getAllParts().forEach(part -> part.skipDraw = false);
    }


    public interface AlphaFunction<T extends LivingEntity> {
        float apply(T var1, float var2, float var3);
    }

    public interface DrawSelector<T extends LivingEntity, M extends EntityModel<T>> {
        List<ModelPart> getPartsToDraw(M var1);
    }
}
