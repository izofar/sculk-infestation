package com.izofar.sculkinfestation.client.renderer.centipede;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.izofar.sculkinfestation.client.model.CaveCentipedeModel;
import com.izofar.sculkinfestation.entity.centipede.CentipedeBody;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Credit to Alex-the-666 for his Centipede Entity from Alex's Mobs!
 * Some changes were done to use custom resources.
 * Source: https://github.com/Alex-the-666/AlexsMobs/blob/1.19/src/main/java/com/github/alexthe666/alexsmobs/client/render/RenderCentipedeBody.java
 */
@OnlyIn(Dist.CLIENT)
public class CentipedeBodyRenderer<T extends CentipedeBody> extends MobRenderer<T, AdvancedEntityModel<T>> {

    public CentipedeBodyRenderer(EntityRendererProvider.Context renderManagerIn, int type) {
        super(renderManagerIn, new CaveCentipedeModel<>(type), 0.5F);
    }

    @Override
    protected void scale(T pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float scale = pLivingEntity.getScale();
        pMatrixStack.scale(scale, scale, scale);
    }

    @Override
    protected float getFlipDegrees(T centipede) {
        return 180.0F;
    }

    @Override
    protected void setupRotations(T entity, PoseStack stack, float pitchIn, float yawIn, float partialTickTime) {
        float newYaw = entity.yHeadRot;
        if (this.isShaking(entity)) {
            newYaw += (float) (Math.cos((double) entity.tickCount * 3.25D) * Math.PI * (double) 0.4F);
        }

        Pose pose = entity.getPose();
        if (pose != Pose.SLEEPING) {
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F - newYaw));
            stack.mulPose(Vector3f.XP.rotationDegrees(entity.getXRot()));
        }

        if (entity.deathTime > 0) {
            float f = ((float) entity.deathTime + partialTickTime - 1.0F) / 20.0F * 1.6F;
            f = Mth.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }

            stack.translate(0, f * 1.15F, 0);
            stack.mulPose(Vector3f.ZP.rotationDegrees(f * this.getFlipDegrees(entity)));
        } else if (entity.hasCustomName()) {
            String s = ChatFormatting.stripFormatting(entity.getName().getString());
            if (("Dinnerbone".equals(s) || "Grumm".equals(s))) {
                stack.translate(0.0D, entity.getBbHeight() + 0.1F, 0.0D);
                stack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
            }
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return CentipedeHeadRenderer.getTexture(entity.getType());
    }
}
