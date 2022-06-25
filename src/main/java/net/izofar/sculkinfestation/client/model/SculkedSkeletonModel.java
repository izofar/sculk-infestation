package net.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import net.izofar.sculkinfestation.entity.SculkedSkeleton;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class SculkedSkeletonModel extends BipedEntityModel<SculkedSkeleton> implements PartedModel{

    private final ModelPart root;
    private ModelPart rightTendril;
    private ModelPart leftTendril;
    private List<ModelPart> bodyAndHead = null;

    public SculkedSkeletonModel(ModelPart root) {
        super(root);
        this.root = root;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0f);
        ModelPartData root = modelData.getRoot();
        ModelPartData head = root.getChild(EntityModelPartNames.HEAD);

        head.addChild(EntityModelPartNames.RIGHT_TENDRIL, ModelPartBuilder.create().uv(48, 10).cuboid(0.0F, -36.0F, -4.0F, 0.0F, 4.0F, 8.0F), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        head.addChild(EntityModelPartNames.LEFT_TENDRIL, ModelPartBuilder.create().uv(48, 10).cuboid(0.0F, -36.0F, -4.0F, 0.0F, 4.0F, 8.0F), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        root.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f), ModelTransform.pivot(-5.0f, 2.0f, 0.0f));
        root.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f), ModelTransform.pivot(5.0f, 2.0f, 0.0f));
        root.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-1.0f, 0.0f, -1.0f, 2.0f, 12.0f, 2.0f), ModelTransform.pivot(-2.0f, 12.0f, 0.0f));
        root.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0f, 0.0f, -1.0f, 2.0f, 12.0f, 2.0f), ModelTransform.pivot(2.0f, 12.0f, 0.0f));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void animateModel(SculkedSkeleton mobEntity, float f, float g, float h) {
        this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
        this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
        ItemStack itemStack = mobEntity.getStackInHand(Hand.MAIN_HAND);
        if (itemStack.isOf(Items.BOW) && mobEntity.isAttacking()) {
            if (mobEntity.getMainArm() == Arm.RIGHT) {
                this.rightArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
            }
        }
        super.animateModel(mobEntity, f, g, h);
    }

    @Override
    public void setAngles(SculkedSkeleton mobEntity, float f, float g, float h, float i, float j) {
        super.setAngles(mobEntity, f, g, h, i, j);
        ItemStack itemStack = mobEntity.getMainHandStack();
        if (mobEntity.isAttacking() && (itemStack.isEmpty() || !itemStack.isOf(Items.BOW))) {
            float k = MathHelper.sin(this.handSwingProgress * (float)Math.PI);
            float l = MathHelper.sin((1.0f - (1.0f - this.handSwingProgress) * (1.0f - this.handSwingProgress)) * (float)Math.PI);
            this.rightArm.roll = 0.0f;
            this.leftArm.roll = 0.0f;
            this.rightArm.yaw = -(0.1f - k * 0.6f);
            this.leftArm.yaw = 0.1f - k * 0.6f;
            this.rightArm.pitch = -1.5707964f;
            this.leftArm.pitch = -1.5707964f;
            this.rightArm.pitch -= k * 1.2f - l * 0.4f;
            this.leftArm.pitch -= k * 1.2f - l * 0.4f;
            CrossbowPosing.swingArms(this.rightArm, this.leftArm, h);
        }
    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) {
        float f = arm == Arm.RIGHT ? 1.0f : -1.0f;
        ModelPart modelPart = this.getArm(arm);
        modelPart.pivotX += f;
        modelPart.rotate(matrices);
        modelPart.pivotX -= f;
    }

    public List<ModelPart> getBodyAndHead(){
        if(this.bodyAndHead == null){
            this.rightTendril = this.head.getChild(EntityModelPartNames.RIGHT_TENDRIL);
            this.leftTendril = this.head.getChild(EntityModelPartNames.LEFT_TENDRIL);
            this.bodyAndHead = ImmutableList.of(this.body, this.getHead(), this.leftTendril, this.rightTendril);
        }
        return this.bodyAndHead;
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
