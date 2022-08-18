package com.izofar.sculkinfestation.init;

import com.izofar.sculkinfestation.SculkInfestation;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers implements BiomeModifier {

    private static final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, SculkInfestation.MODID);

    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(SculkInfestation.MODID, "sculkinfestation_mob_spawns"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, SculkInfestation.MODID);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if(phase == Phase.ADD){
            if (isBiome(biome, Biomes.DEEP_DARK)) {
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SCULKED_ZOMBIE.get(), 100, 4, 4));
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SCULKED_SKELETON.get(), 100, 4, 4));
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SCULKED_CREEPER.get(), 100, 4, 4));
                
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SCULKIPEDE_HEAD.get(), 20, 1, 1));
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<ModBiomeModifiers> makeCodec(){
        return Codec.unit(ModBiomeModifiers::new);
    }

    private static boolean isBiome(Holder<Biome> biome, ResourceKey<Biome> entry){
        return  biome.is(entry.location());
    }

    public static void register(IEventBus eventBus){
        biomeModifers.register(eventBus);
        biomeModifers.register("sculkinfestation_mob_spawns", ModBiomeModifiers::makeCodec);
    }

}
