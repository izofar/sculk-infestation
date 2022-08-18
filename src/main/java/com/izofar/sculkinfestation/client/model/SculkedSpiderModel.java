package com.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import com.izofar.sculkinfestation.entity.SculkedSpider;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import java.util.List;

public class SculkedSpiderModel extends SpiderModel<SculkedSpider> implements IPartedModel {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightPincer;
    private final ModelPart leftPincer;
    private final ModelPart plant1;
    private final ModelPart plant2;

    public SculkedSpiderModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.rightPincer = head.getChild("right_pincer");
        this.leftPincer = head.getChild("left_pincer");
        this.plant1 = head.getChild("plant1");
        this.plant2 = head.getChild("plant2");
    }

    public static LayerDefinition createBodyLayer(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -3.0F));
        head.addOrReplaceChild("plant2", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -8.0F, -3.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -4.0F, 0.0F, 0.7854F, 0.0F));
        head.addOrReplaceChild("plant1", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -8.0F, -3.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -4.0F, 0.0F, -0.7854F, 0.0F));

        head.addOrReplaceChild("left_pincer", CubeListBuilder.create().texOffs(46, 24).mirror().addBox(0.0F, -2.0F, -6.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(51, 20).mirror().addBox(-1.0F, -1.0F, -11.0F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 4.0F, -4.0F));

        head.addOrReplaceChild("right_pincer", CubeListBuilder.create().texOffs(51, 20).addBox(-2.0F, -1.0F, -11.0F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(46, 24).addBox(-2.0F, -2.0F, -6.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.0F, -4.0F));

        partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 9.0F));

        partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, 4.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 15.0F, 4.0F));
        partdefinition.addOrReplaceChild("right_middle_hind_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, 1.0F));
        partdefinition.addOrReplaceChild("left_middle_hind_leg", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 15.0F, 1.0F));
        partdefinition.addOrReplaceChild("right_middle_front_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, -2.0F));
        partdefinition.addOrReplaceChild("left_middle_front_leg", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 15.0F, -2.0F));
        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, -5.0F));
        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 15.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(SculkedSpider entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float f = (Mth.cos(4F * ageInTicks * (float) Math.PI / 180) - 1.05F) * (float) Math.PI / 20.0F;
        this.rightPincer.yRot = -f;
        this.leftPincer.yRot = f;
    }

    @Override
    public ModelPart getPart() {
        return this.root();
    }

    public List<ModelPart> getParts(){
        return ImmutableList.of(this.body, this.plant1, this.plant2);
    }
}
