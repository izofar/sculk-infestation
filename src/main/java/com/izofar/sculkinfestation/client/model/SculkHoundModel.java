package com.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import com.izofar.sculkinfestation.entity.SculkHound;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SculkHoundModel extends EntityModel<SculkHound> implements IPartedModel {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;
    private final ModelPart mane;

    public SculkHoundModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.mane = root.getChild("mane");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 13).addBox(-3.0F, -4.0F, -4.0F, 8.0F, 7.0F, 6.0F), PartPose.offset(-1.0F, 13.5F, -7.0F));
        head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(30, 3).addBox(-1.5F, -1.02F, -10.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -8.0F, -2.0F, 0.0F, 4.0F, 3.0F), PartPose.ZERO);
        head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(5.0F, -8.0F, -2.0F, 0.0F, 4.0F, 3.0F).mirror(false), PartPose.ZERO);
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 19).addBox(-4.0F, 2.0F, -3.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 1.5708F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 10.0F, 9.0F), PartPose.offsetAndRotation(0.0F, 14.0F, -3.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(52, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, 16.0F, 7.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(52, 16).addBox(1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 16.0F, 7.0F));
        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(52, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, 16.0F, -4.0F));
        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(52, 16).addBox(1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 16.0F, -4.0F));
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(21, -7).addBox(1.0F, 0.0F, -1.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 12.0F, 10.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg, this.tail, this.mane);
    }

    public List<ModelPart> getParts(){
        return ImmutableList.of(this.head, this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg, this.tail, this.mane);
    }

    @Override
    public void prepareMobModel(SculkHound entity, float limbSwing, float limbSwingAmount, float partialTick) {
        if (entity.isAngry()) {
            this.tail.yRot = 0.0F;
        } else {
            this.tail.yRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        this.body.setPos(0.0F, 14.0F, 2.0F);
        this.body.xRot = ((float)Math.PI / 2F);
        this.mane.setPos(0.0F, 14.0F, -3.0F);
        this.mane.xRot = this.body.xRot;
        this.tail.setPos(-1.0F, 12.0F, 10.0F);
        this.rightHindLeg.setPos(-2.5F, 16.0F, 7.0F);
        this.leftHindLeg.setPos(0.5F, 16.0F, 7.0F);
        this.rightFrontLeg.setPos(-2.5F, 16.0F, -4.0F);
        this.leftFrontLeg.setPos(0.5F, 16.0F, -4.0F);
        this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.head.zRot = entity.getHeadRollAngle(partialTick) + entity.getBodyRollAngle(partialTick, 0.0F);
        this.mane.zRot = entity.getBodyRollAngle(partialTick, -0.08F);
        this.body.zRot = entity.getBodyRollAngle(partialTick, -0.16F);
        this.tail.zRot = entity.getBodyRollAngle(partialTick, -0.2F);

    }

    @Override
    public void setupAnim(SculkHound pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.headParts().forEach((headPart) -> headPart.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
        this.bodyParts().forEach((bodyPart) -> bodyPart.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
