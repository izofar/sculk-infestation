package net.izofar.skulkinfestation.mixin;

import net.izofar.skulkinfestation.init.ModEntityTypes;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(OverworldBiomeCreator.class)
public class DeepDarkSpawnSettingsMixin {

    @ModifyVariable(method = "createDeepDark()Lnet/minecraft/world/biome/Biome;", at = @At("STORE"),  ordinal = 0)
    private static SpawnSettings.Builder skulkinfestation_createDeepDark(SpawnSettings.Builder builder) {
        addSkulkedMonsters(builder);
        return builder;
    }

    private static void addSkulkedMonsters(SpawnSettings.Builder builder){
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntityTypes.SKULKED_ZOMBIE, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntityTypes.SKULKED_SKELETON, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntityTypes.SKULKED_CREEPER, 100, 4, 4));
    }
}
