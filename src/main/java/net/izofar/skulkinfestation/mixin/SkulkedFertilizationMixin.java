package net.izofar.skulkinfestation.mixin;

import net.izofar.skulkinfestation.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class SkulkedFertilizationMixin {
    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/item/ItemStack;decrement(I)V",
                ordinal = 0
            ),
            method = "useOnFertilizable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"
    )
    private static void skulkinfestation_grow(ItemStack stack, World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(pos);
        if (((Fertilizable) blockState.getBlock()).canGrow(world, world.random, pos, blockState)
                && stack.isOf(ModItems.SKULKED_BONE_MEAL)
                && world.getBlockState(pos).getBlock() instanceof CropBlock crop) {
            world.setBlockState(pos, crop.withAge(crop.getMaxAge()), 2);
        }
    }
}
