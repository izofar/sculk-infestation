package net.izofar.sculkinfestation.mixin;

import net.izofar.sculkinfestation.entity.SculkEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.WardenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WardenEntity.class)
public class WardenTargetMixin {
	@Inject(at = @At("RETURN"), cancellable = true, method = "isValidTarget(Lnet/minecraft/entity/Entity;)Z")
	private void sculkinfestation_isValidTarget(Entity entity, CallbackInfoReturnable<Boolean> cir) {
		if(entity instanceof SculkEntity) cir.setReturnValue(false);
	}
}
