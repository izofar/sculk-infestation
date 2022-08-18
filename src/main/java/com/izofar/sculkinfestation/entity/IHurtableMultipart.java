package com.izofar.sculkinfestation.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

/*
 * Credit to Alex-the-666 for his Centipede Entity from Alex's Mobs!
 * Some changes were done to make it more generic.
 * Source: https://github.com/Alex-the-666/AlexsMobs/blob/1.19/src/main/java/com/github/alexthe666/alexsmobs/entity/IHurtableMultipart.java
 */
public interface IHurtableMultipart {

    void onAttackedFromServer(LivingEntity parent, float damage, DamageSource damageSource);

    default Vec3 calcOffsetVec(float offsetZ, float xRot, float yRot){
        return new Vec3(0, 0, offsetZ).xRot(xRot * ((float)Math.PI / 180F)).yRot(-yRot * ((float)Math.PI / 180F));
    }

    default float limitAngle(float sourceAngle, float targetAngle, float maximumChange) {
        float f = Mth.wrapDegrees(targetAngle - sourceAngle);
        if (f > maximumChange) {
            f = maximumChange;
        }

        if (f < -maximumChange) {
            f = -maximumChange;
        }

        float f1 = sourceAngle + f;
        if (f1 < 0.0F) {
            f1 += 360.0F;
        } else if (f1 > 360.0F) {
            f1 -= 360.0F;
        }

        return f1;
    }
}
