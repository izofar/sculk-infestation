package net.izofar.sculkinfestation.client.renderer.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.izofar.sculkinfestation.client.model.PartedModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.List;

public class SkulkFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final Identifier texture;
    private final AnimationAngleAdjuster animationAngleAdjuster;
    private final ModelPartVisibility modelPartVisibility;

    public SkulkFeatureRenderer(FeatureRendererContext<T, M> context, Identifier texture, AnimationAngleAdjuster animationAngleAdjuster, ModelPartVisibility modelPartVisibility) {
        super(context);
        this.texture = texture;
        this.animationAngleAdjuster = animationAngleAdjuster;
        this.modelPartVisibility = modelPartVisibility;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T wardenEntity, float f, float g, float h, float j, float k, float l) {
        if (wardenEntity.isInvisible()) {
            return;
        }
        this.updateModelPartVisibility();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucentEmissive(this.texture));
        this.getContextModel().render(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlay(wardenEntity, 0.0f), 1.0f, 1.0f, 1.0f, this.animationAngleAdjuster.apply(wardenEntity, h, j));
        this.unhideAllModelParts();
    }

    private void updateModelPartVisibility() {
        List<ModelPart> list = this.modelPartVisibility.getPartsToDraw(this.getContextModel());
        ((PartedModel)this.getContextModel()).getPart().traverse().forEach(part -> part.hidden = true);
        list.forEach(part -> part.hidden = false);
    }

    private void unhideAllModelParts() {
        ((PartedModel)this.getContextModel()).getPart().traverse().forEach(part -> part.hidden = false);
    }

    @Environment(value=EnvType.CLIENT)
    public interface AnimationAngleAdjuster<T extends LivingEntity> {
        float apply(T var1, float var2, float var3);
    }

    @Environment(value=EnvType.CLIENT)
    public interface ModelPartVisibility<T extends LivingEntity, M extends EntityModel<T>> {
        List<ModelPart> getPartsToDraw(M var1);
    }
}
