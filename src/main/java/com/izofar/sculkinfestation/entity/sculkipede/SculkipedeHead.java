package com.izofar.sculkinfestation.entity.sculkipede;

import com.izofar.sculkinfestation.entity.centipede.CentipedeHead;
import com.izofar.sculkinfestation.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SculkipedeHead extends CentipedeHead {
    public SculkipedeHead(EntityType type, Level worldIn) {
        super(type, worldIn, SculkipedeHead::createBody);
    }

    public static SculkipedeBody createBody(LivingEntity parent, boolean tail) {
        return tail ? SculkipedeBody.of(ModEntityTypes.SCULKIPEDE_TAIL.get(), parent, parent.getScale()) : SculkipedeBody.of(ModEntityTypes.SCULKIPEDE_BODY.get(), parent, parent.getScale());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 35.0D).add(Attributes.FOLLOW_RANGE, 32.0D).add(Attributes.ARMOR, 6.0D).add(Attributes.ATTACK_DAMAGE, 8.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5F).add(Attributes.MOVEMENT_SPEED, 0.22F);
    }
}
