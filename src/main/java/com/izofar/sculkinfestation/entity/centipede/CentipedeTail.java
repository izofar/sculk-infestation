package com.izofar.sculkinfestation.entity.centipede;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;

/*
 * Credit to Alex-the-666 for his Centipede Entity from Alex's Mobs!
 * Some changes were done to make it more generic.
 * Source: https://github.com/Alex-the-666/AlexsMobs/blob/1.19/src/main/java/com/github/alexthe666/alexsmobs/entity/EntityCentipedeTail.java
 */
public abstract class CentipedeTail extends CentipedeBody {

    protected CentipedeTail(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

}
