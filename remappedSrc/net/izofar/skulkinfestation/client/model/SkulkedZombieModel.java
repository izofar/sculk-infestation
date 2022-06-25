package net.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import net.izofar.sculkinfestation.entity.SkulkedZombie;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AbstractZombieModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

import java.util.List;

public class SkulkedZombieModel extends AbstractZombieModel<SkulkedZombie> implements PartedModel{

    private final List<ModelPart> headAndBody;
    private final List<ModelPart> bodyHeadAndLimbs;

    private final ModelPart root;
    public SkulkedZombieModel(ModelPart root) {
        super(root);
        this.root = root;
        this.headAndBody = ImmutableList.of(root.getChild(EntityModelPartNames.HEAD), root.getChild(EntityModelPartNames.BODY));
        this.bodyHeadAndLimbs = ImmutableList.of(
                root.getChild(EntityModelPartNames.BODY),
                root.getChild(EntityModelPartNames.HEAD),
                root.getChild(EntityModelPartNames.RIGHT_ARM),
                root.getChild(EntityModelPartNames.LEFT_ARM),
                root.getChild(EntityModelPartNames.RIGHT_LEG),
                root.getChild(EntityModelPartNames.LEFT_LEG));

    }

    public static TexturedModelData getTexturedModelData(){
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        head.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation.NONE.add(-0.5f)), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 32).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation.NONE.add(0.5f)), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f), ModelTransform.pivot(-5.0f, 2.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f), ModelTransform.pivot(5.0f, 2.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), ModelTransform.pivot(-1.9f, 12.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), ModelTransform.pivot(1.9f, 12.0f, 0.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public List<ModelPart> getHeadAndBody(){
        return this.headAndBody;
    }

    public List<ModelPart> getBodyHeadAndLimbs(){
        return this.bodyHeadAndLimbs;
    }

    @Override
    public boolean isAttacking(SkulkedZombie zombieEntity) {
        return zombieEntity.isAttacking();
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
