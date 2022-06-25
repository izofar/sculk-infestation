package net.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import net.izofar.sculkinfestation.entity.SkulkedCreeper;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class SkulkedCreeperModel extends SinglePartEntityModel<SkulkedCreeper> implements PartedModel{

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

    public SkulkedCreeperModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.body = root.getChild(EntityModelPartNames.BODY);
        this.rightHindLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
        this.leftHindLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
        this.rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
        this.leftFrontLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
        this.rightTendril = root.getChild(EntityModelPartNames.HEAD).getChild(EntityModelPartNames.RIGHT_TENDRIL);
        this.leftTendril = root.getChild(EntityModelPartNames.HEAD).getChild(EntityModelPartNames.LEFT_TENDRIL);
        this.justBody = ImmutableList.of(this.body);
        this.bodyHeadAndLimbs = ImmutableList.of(this.head, this.body, this.rightFrontLeg, this.rightHindLeg, this.leftFrontLeg, this.leftHindLeg, this.leftTendril, this.rightTendril);
        this.headAndLimbs = ImmutableList.of(this.head, this.body, this.leftTendril, this.rightTendril);
    }

    public static TexturedModelData getTexturedModelData(){
        Dilation dilation = Dilation.NONE;
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation), ModelTransform.pivot(0.0f, 6.0f, 0.0f));
        head.addChild(EntityModelPartNames.RIGHT_TENDRIL, ModelPartBuilder.create().uv(42, 10).cuboid(3.0F, -19.0F, 0.0F, 8.0F, 10.0F, 2.0F), ModelTransform.pivot(0.0f, 6.0f, 0.0f));
        head.addChild(EntityModelPartNames.LEFT_TENDRIL, ModelPartBuilder.create().uv(32, 0).cuboid(-11.0F, -19.0F, 0.0F, 8.0F, 10.0F, 2.0F), ModelTransform.pivot(0.0f, 6.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, dilation), ModelTransform.pivot(0.0f, 6.0f, 0.0f));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 16).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 6.0f, 4.0f, dilation);
        modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(-2.0f, 18.0f, 4.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(2.0f, 18.0f, 4.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, modelPartBuilder, ModelTransform.pivot(-2.0f, 18.0f, -4.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, modelPartBuilder, ModelTransform.pivot(2.0f, 18.0f, -4.0f));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(SkulkedCreeper skulkedCreeper, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch){
        this.head.yaw = headYaw * ((float)Math.PI / 180);
        this.head.pitch = headPitch * ((float)Math.PI / 180);
        this.leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662f) * 1.4f * limbDistance;
        this.rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * limbDistance;
        this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * limbDistance;
        this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662f) * 1.4f * limbDistance;
        float k = animationProgress - (float)skulkedCreeper.age;
        this.setTendrilPitches(skulkedCreeper, animationProgress, k);
    }

    private void setTendrilPitches(SkulkedCreeper skulkedCreeper, float animationProgress, float tickDelta) {
        float f;
        this.leftTendril.pitch = f = skulkedCreeper.getTendrilPitch(tickDelta) * (float)(Math.cos((double)animationProgress * 2.25) * Math.PI * (double)0.06f);
        this.rightTendril.pitch = -f;
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
