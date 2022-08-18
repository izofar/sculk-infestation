package com.izofar.sculkinfestation.entity;

import com.izofar.sculkinfestation.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PrimedSculkTnt extends PrimedTnt {

    @Nullable
    private LivingEntity owner;
    private final float power = 8.0F;

    public PrimedSculkTnt(EntityType<? extends PrimedSculkTnt> entityType, Level level) { super(entityType, level); }

    private PrimedSculkTnt(Level level, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntityTypes.SCULK_TNT.get(), level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = igniter;
    }

    // Used in SculkTntBlock
    // Implemented so that I can register entity with PrimedSculkTnt::new
    public static PrimedSculkTnt of(Level level, double x, double y, double z, @Nullable LivingEntity igniter){
        return new PrimedSculkTnt(level, x, y, z, igniter);
    }

    @Override
    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.power, Explosion.BlockInteraction.BREAK);
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return this.owner;
    }
}
