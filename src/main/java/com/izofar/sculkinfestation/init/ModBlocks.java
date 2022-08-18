package com.izofar.sculkinfestation.init;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.block.SculkTntBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModBlocks {

    public static final DeferredRegister<Block> MODDED_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SculkInfestation.MODID);

    public static final RegistryObject<Block> SCULK_TNT = MODDED_BLOCKS.register("sculk_tnt", () -> new SculkTntBlock(BlockBehaviour.Properties.of(Material.EXPLOSIVE).instabreak().sound(SoundType.GRASS)));

    public static void register(IEventBus eventBus) { MODDED_BLOCKS.register(eventBus); }

}
