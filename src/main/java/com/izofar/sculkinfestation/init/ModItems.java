package com.izofar.sculkinfestation.init;

import com.izofar.sculkinfestation.SculkInfestation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModItems {

    public static final FoodProperties SCULKED_FLESH_FOOD = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.7F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 0.5F)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 600, 0), 0.3F)
            .meat().build();

    public static final DeferredRegister<Item> MODDED_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SculkInfestation.MODID);

    public static final RegistryObject<Item> SCULKED_CREEPER_SPAWN_EGG = MODDED_ITEMS.register("sculked_creeper_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULKED_CREEPER, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULKED_SKELETON_SPAWN_EGG = MODDED_ITEMS.register("sculked_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULKED_SKELETON, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULKED_SPIDER_SPAWN_EGG = MODDED_ITEMS.register("sculked_spider_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULKED_SPIDER, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULKED_ZOMBIE_SPAWN_EGG = MODDED_ITEMS.register("sculked_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULKED_ZOMBIE, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULKIPEDE_SPAWN_EGG = MODDED_ITEMS.register("sculkipede_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULKIPEDE_HEAD, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULK_HOUND_SPAWN_EGG = MODDED_ITEMS.register("sculk_hound_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULK_HOUND, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULK_LURKER_SPAWN_EGG = MODDED_ITEMS.register("sculk_lurker_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULK_LURKER, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULK_HUGGER_SPAWN_EGG = MODDED_ITEMS.register("sculk_hugger_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SCULK_HUGGER, 0x0f4649, 0x39d6e0, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SCULKED_FLESH = MODDED_ITEMS.register("sculked_flesh", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(SCULKED_FLESH_FOOD)));
    public static final RegistryObject<Item> SCULKED_BONE = MODDED_ITEMS.register("sculked_bone", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SCULKED_BONE_MEAL = MODDED_ITEMS.register("sculked_bone_meal", () -> new BoneMealItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SCULKED_GUNPOWDER = MODDED_ITEMS.register("sculked_gunpowder", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> SCULK_TNT = MODDED_ITEMS.register("sculk_tnt", () -> new BlockItem(ModBlocks.SCULK_TNT.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static void register(IEventBus eventBus) { MODDED_ITEMS.register(eventBus); }

    public static void addNewCompostables(){
        // Make Sculked Flesh Compostable
        ComposterBlock.COMPOSTABLES.put(SCULKED_FLESH.get(), 0.9F);
    }

}
