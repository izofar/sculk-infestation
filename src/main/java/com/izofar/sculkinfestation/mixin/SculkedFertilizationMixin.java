package com.izofar.sculkinfestation.mixin;

import com.izofar.sculkinfestation.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class SculkedFertilizationMixin {
    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V",
                ordinal = 0
            ),
            method = "applyBonemeal(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;)Z"
    )
    private static void sculkinfestation_grow(ItemStack stack, Level level, BlockPos pos, Player player, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = level.getBlockState(pos);
        if (((BonemealableBlock) blockState.getBlock()).isBonemealSuccess(level, level.random, pos, blockState)
                && stack.is(ModItems.SCULKED_BONE_MEAL.get())
                && level.getBlockState(pos).getBlock() instanceof CropBlock crop) {
            level.setBlock(pos, crop.getStateForAge(crop.getMaxAge()), 2);
        }
    }
}
