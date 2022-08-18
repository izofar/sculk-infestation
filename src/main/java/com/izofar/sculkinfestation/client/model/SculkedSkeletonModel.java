package com.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import com.izofar.sculkinfestation.entity.SculkedSkeleton;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;


import java.util.List;

public class SculkedSkeletonModel extends SkeletonModel<SculkedSkeleton> implements IPartedModel {

    private final ModelPart root;
    private ModelPart rightTendril;
    private ModelPart leftTendril;
    private List<ModelPart> bodyAndHead;

    public SculkedSkeletonModel(ModelPart root) {
        super(root);
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.getChild("head");

        head.addOrReplaceChild("right_tendril", CubeListBuilder.create().texOffs(48, 10).addBox(0.0F, -36.0F, -4.0F, 0.0F, 4.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        head.addOrReplaceChild("left_tendril", CubeListBuilder.create().texOffs(48, 10).addBox(0.0F, -36.0F, -4.0F, 0.0F, 4.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f), PartPose.offset(-5.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f), PartPose.offset(5.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 12.0f, 2.0f), PartPose.offset(-2.0f, 12.0f, 0.0f));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0f, 0.0f, -1.0f, 2.0f, 12.0f, 2.0f), PartPose.offset(2.0f, 12.0f, 0.0f));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public List<ModelPart> getBodyAndHead(){
        if(this.bodyAndHead == null){
            this.rightTendril = this.head.getChild("right_tendril");
            this.leftTendril = this.head.getChild("left_tendril");
            this.bodyAndHead = ImmutableList.of(this.body, this.getHead(), this.leftTendril, this.rightTendril);
        }
        return this.bodyAndHead;
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

}
