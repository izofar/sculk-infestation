package com.izofar.sculkinfestation.block;

import com.izofar.sculkinfestation.entity.PrimedSculkTnt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SculkTntBlock extends TntBlock {

    public SculkTntBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            PrimedSculkTnt tntEntity = PrimedSculkTnt.of(level, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, explosion.getSourceMob());
            int i = tntEntity.getFuse();
            tntEntity.setFuse((short)(level.random.nextInt(i / 4) + i / 8));
            level.addFreshEntity(tntEntity);
        }
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter){
        explode(level, pos, igniter);
    }

    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    public static void explode(Level level, BlockPos pos) {
        explode(level, pos, null);
    }

    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    private static void explode(Level level, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            PrimedSculkTnt primedtnt = PrimedSculkTnt.of(level, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, igniter);
            level.addFreshEntity(primedtnt);
            level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }
}
