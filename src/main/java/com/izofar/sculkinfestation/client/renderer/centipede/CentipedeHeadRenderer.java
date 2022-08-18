package com.izofar.sculkinfestation.client.renderer.centipede;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.google.common.collect.ImmutableMap;
import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.CaveCentipedeModel;
import com.izofar.sculkinfestation.client.renderer.layer.CentipedeEyesLayer;
import com.izofar.sculkinfestation.entity.centipede.CentipedeHead;
import com.izofar.sculkinfestation.init.ModEntityTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

/*
 * Credit to Alex-the-666 for his Centipede Entity from Alex's Mobs!
 * Some changes were done to use custom resources.
 * Source: https://github.com/Alex-the-666/AlexsMobs/blob/1.19/src/main/java/com/github/alexthe666/alexsmobs/client/render/RenderCentipedeHead.java
 */
@OnlyIn(Dist.CLIENT)
public class CentipedeHeadRenderer extends MobRenderer<CentipedeHead, AdvancedEntityModel<CentipedeHead>> {
    public static final Map<EntityType<?>, String> CENTIPEDE_TEXTURE_MAP = ImmutableMap.of(
            ModEntityTypes.SCULKIPEDE_HEAD.get(), "textures/entity/sculkipede/sculkipede",
            ModEntityTypes.SCULKIPEDE_BODY.get(), "textures/entity/sculkipede/sculkipede",
            ModEntityTypes.SCULKIPEDE_TAIL.get(), "textures/entity/sculkipede/sculkipede"
        );


    public CentipedeHeadRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CaveCentipedeModel<>(0), 0.5F);
        this.addLayer(new CentipedeEyesLayer(this));
    }

    @Override
    protected void scale(CentipedeHead pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float scale = pLivingEntity.getScale();
        pMatrixStack.scale(scale, scale, scale);
    }

    @Override
    protected void setupRotations(CentipedeHead entity, PoseStack stack, float pitchIn, float yawIn, float partialTickTime) {
        if (this.isShaking(entity)) {
            yawIn += (float) (Math.cos((double) entity.tickCount * 3.25D) * Math.PI * (double) 0.4F);
        }

        Pose pose = entity.getPose();
        if (pose != Pose.SLEEPING) {
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F - yawIn));
        }

        if (entity.deathTime > 0) {
            float f = ((float) entity.deathTime + partialTickTime - 1.0F) / 20.0F * 1.6F;
            f = Mth.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }
            stack.translate(0, f, 0);
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
    protected float getFlipDegrees(CentipedeHead centipede) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(CentipedeHead entity) {
        return CentipedeHeadRenderer.getTexture(entity.getType());
    }

    public static ResourceLocation getTexture(EntityType<?> entityType){
        return new ResourceLocation(SculkInfestation.MODID, CENTIPEDE_TEXTURE_MAP.get(entityType) + ".png");
    }
}
