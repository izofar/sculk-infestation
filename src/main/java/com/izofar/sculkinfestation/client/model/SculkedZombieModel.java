package com.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import com.izofar.sculkinfestation.entity.SculkedZombie;
import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import java.util.List;

public class SculkedZombieModel extends AbstractZombieModel<SculkedZombie> implements IPartedModel {

    private final List<ModelPart> headAndBody;
    private final List<ModelPart> bodyHeadAndLimbs;

    private final ModelPart root;
    public SculkedZombieModel(ModelPart root) {
        super(root);
        this.root = root;
        this.headAndBody = ImmutableList.of(this.head, this.body);
        this.bodyHeadAndLimbs = ImmutableList.of(
                this.body,
                this.head,
                this.rightArm,
                this.leftArm,
                this.rightLeg,
                this.leftLeg
            );
    }

    public static LayerDefinition createBodyLayer(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f), PartPose.offset(0.0f, 0.0f, 0.0f));
        head.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new CubeDeformation(-0.5f)), PartPose.offset(0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new CubeDeformation(0.5f)), PartPose.offset(0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f), PartPose.offset(0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.offset(-5.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.offset(5.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.offset(-1.9f, 12.0f, 0.0f));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.offset(1.9f, 12.0f, 0.0f));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public List<ModelPart> getHeadAndBody(){
        return this.headAndBody;
    }

    public List<ModelPart> getBodyHeadAndLimbs(){
        return this.bodyHeadAndLimbs;
    }

    @Override
    public boolean isAggressive(SculkedZombie zombie) {
        return zombie.isAggressive();
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

}
