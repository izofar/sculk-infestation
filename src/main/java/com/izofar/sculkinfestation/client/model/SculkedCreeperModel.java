package com.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import com.izofar.sculkinfestation.entity.SculkedCreeper;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import java.util.List;

public class SculkedCreeperModel extends CreeperModel<SculkedCreeper> implements IPartedModel {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    protected final ModelPart rightTendril;
    protected final ModelPart leftTendril;
    private final List<ModelPart> justBody;
    private final List<ModelPart> bodyHeadAndLimbs;
    private final List<ModelPart> headAndLimbs;

    public SculkedCreeperModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftHindLeg = root.getChild("right_hind_leg");
        this.rightHindLeg = root.getChild("left_hind_leg");
        this.leftFrontLeg = root.getChild("right_front_leg");
        this.rightFrontLeg = root.getChild("left_front_leg");
        this.rightTendril = this.head.getChild("right_tendril");
        this.leftTendril = this.head.getChild("left_tendril");
        this.justBody = ImmutableList.of(this.body);
        this.bodyHeadAndLimbs = ImmutableList.of(this.head, this.body, this.rightFrontLeg, this.rightHindLeg, this.leftFrontLeg, this.leftHindLeg, this.leftTendril, this.rightTendril);
        this.headAndLimbs = ImmutableList.of(this.head, this.body, this.leftTendril, this.rightTendril);
    }

    public static LayerDefinition createBodyLayer(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new CubeDeformation(0.0F)), PartPose.offset(0.0f, 6.0f, 0.0f));
        head.addOrReplaceChild("right_tendril", CubeListBuilder.create().texOffs(42, 10).addBox(3.0F, -19.0F, 0.0F, 8.0F, 10.0F, 2.0F), PartPose.offset(0.0f, 6.0f, 0.0f));
        head.addOrReplaceChild("left_tendril", CubeListBuilder.create().texOffs(32, 0).addBox(-11.0F, -19.0F, 0.0F, 8.0F, 10.0F, 2.0F), PartPose.offset(0.0f, 6.0f, 0.0f));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, new CubeDeformation(0.0F)), PartPose.offset(0.0f, 6.0f, 0.0f));
        CubeListBuilder modelPartBuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 6.0f, 4.0f, new CubeDeformation(0.0F));
        partdefinition.addOrReplaceChild("right_hind_leg", modelPartBuilder, PartPose.offset(-2.0f, 18.0f, 4.0f));
        partdefinition.addOrReplaceChild("left_hind_leg", modelPartBuilder, PartPose.offset(2.0f, 18.0f, 4.0f));
        partdefinition.addOrReplaceChild("right_front_leg", modelPartBuilder, PartPose.offset(-2.0f, 18.0f, -4.0f));
        partdefinition.addOrReplaceChild("left_front_leg", modelPartBuilder, PartPose.offset(2.0f, 18.0f, -4.0f));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setupAnim(SculkedCreeper sculkedCreeper, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(sculkedCreeper, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float k = ageInTicks - sculkedCreeper.getAgeInTicks();
        this.setTendrilPitches(sculkedCreeper, ageInTicks, k);
    }

    private void setTendrilPitches(SculkedCreeper sculkedCreeper, float animationProgress, float tickDelta) {
        float f;
        this.leftTendril.xRot = f = sculkedCreeper.getTendrilPitch(tickDelta) * (float)(Math.cos((double)animationProgress * 2.25) * Math.PI * (double)0.06f);
        this.rightTendril.xRot = -f;
    }

    public List<ModelPart> getHeadAndLimbs() {
        return this.headAndLimbs;
    }

    public List<ModelPart> getBodyHeadAndLimbs() {
        return this.bodyHeadAndLimbs;
    }

    public List<ModelPart> getBody() {
        return this.justBody;
    }

}
