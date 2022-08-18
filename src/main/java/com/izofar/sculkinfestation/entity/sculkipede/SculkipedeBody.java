package com.izofar.sculkinfestation.entity.sculkipede;

import com.izofar.sculkinfestation.entity.centipede.CentipedeBody;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SculkipedeBody extends CentipedeBody {
    public SculkipedeBody(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    private SculkipedeBody(EntityType t, LivingEntity parent, float scale) {
        super(t, parent.level);
        this.setParent(parent);
        this.setScale(scale);
    }

    public static SculkipedeBody of(EntityType t, LivingEntity parent, float scale){
        return new SculkipedeBody(t, parent, scale);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FOLLOW_RANGE, 32.0D).add(Attributes.ARMOR, 6.0D).add(Attributes.ATTACK_DAMAGE, 8.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

}
