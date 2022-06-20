package net.izofar.skulkinfestation.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.izofar.skulkinfestation.SkulkInfestation;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final FoodComponent SKULKED_FLESH_FOOD = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.7F).statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 0), 0.5F).statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 600, 0), 0.3F).meat().build();

    public static final Item SKULKED_FLESH = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(SKULKED_FLESH_FOOD));
    public static final Item SKULKED_BONE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item SKULKED_BONE_MEAL = new BoneMealItem(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item SKULKED_GUNPOWDER = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));

    public static void registerModItems(){
        Registry.register(Registry.ITEM, new Identifier(SkulkInfestation.MOD_ID, "skulked_flesh"), SKULKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier(SkulkInfestation.MOD_ID, "skulked_bone"), SKULKED_BONE);
        Registry.register(Registry.ITEM, new Identifier(SkulkInfestation.MOD_ID, "skulked_bone_meal"), SKULKED_BONE_MEAL);
        Registry.register(Registry.ITEM, new Identifier(SkulkInfestation.MOD_ID, "skulked_gunpowder"), SKULKED_GUNPOWDER);

    }
}
