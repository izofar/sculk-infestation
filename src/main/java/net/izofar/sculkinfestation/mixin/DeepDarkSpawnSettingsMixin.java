package net.izofar.sculkinfestation.mixin;

import net.izofar.sculkinfestation.init.ModEntityTypes;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(OverworldBiomeCreator.class)
public class DeepDarkSpawnSettingsMixin {

    @ModifyVariable(method = "createDeepDark()Lnet/minecraft/world/biome/Biome;", at = @At("STORE"),  ordinal = 0)
    private static SpawnSettings.Builder sculkinfestation_createDeepDark(SpawnSettings.Builder builder) {
        addSculkedMonsters(builder);
        return builder;
    }

    private static void addSculkedMonsters(SpawnSettings.Builder builder){
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntityTypes.SCULKED_ZOMBIE, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntityTypes.SCULKED_SKELETON, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntityTypes.SCULKED_CREEPER, 100, 4, 4));
    }
}
