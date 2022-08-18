package com.izofar.sculkinfestation.mixin;

import com.izofar.sculkinfestation.entity.ISculkEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public class WardenTargetMixin {
	@Inject(at = @At("RETURN"), cancellable = true, method = "canTargetEntity(Lnet/minecraft/world/entity/Entity;)Z")
	private void sculkinfestation_isValidTarget(Entity entity, CallbackInfoReturnable<Boolean> cir) {
		if(entity instanceof ISculkEntity) cir.setReturnValue(false);
	}
}
