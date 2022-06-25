package net.izofar.sculkinfestation.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.izofar.sculkinfestation.SculkInfestation;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final FoodComponent SCULKED_FLESH_FOOD = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.7F).statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 0), 0.5F).statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 600, 0), 0.3F).meat().build();

    public static final Item SCULKED_FLESH = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(SCULKED_FLESH_FOOD));
    public static final Item SCULKED_BONE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item SCULKED_BONE_MEAL = new BoneMealItem(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item SCULKED_GUNPOWDER = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item SCULK_TNT = new BlockItem(ModBlocks.SCULK_TNT, new Item.Settings().group(ItemGroup.REDSTONE));

    public static void registerModItems(){
        Registry.register(Registry.ITEM, new Identifier(SculkInfestation.MOD_ID, "sculked_flesh"), SCULKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier(SculkInfestation.MOD_ID, "sculked_bone"), SCULKED_BONE);
        Registry.register(Registry.ITEM, new Identifier(SculkInfestation.MOD_ID, "sculked_bone_meal"), SCULKED_BONE_MEAL);
        Registry.register(Registry.ITEM, new Identifier(SculkInfestation.MOD_ID, "sculked_gunpowder"), SCULKED_GUNPOWDER);
        Registry.register(Registry.ITEM, new Identifier(SculkInfestation.MOD_ID, "sculk_tnt"), SCULK_TNT);

        establishModProperties();

    }

    private static void establishModProperties(){
        // Make Sculked Flesh Compostable
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SCULKED_FLESH, 0.9F);
    }
}
