package net.izofar.sculkinfestation.init;

import net.izofar.sculkinfestation.SculkInfestation;
import net.izofar.sculkinfestation.block.SculkTntBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block SCULK_TNT = new SculkTntBlock(AbstractBlock.Settings.of(Material.TNT).breakInstantly().sounds(BlockSoundGroup.GRASS));

    public static void registerModBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(SculkInfestation.MOD_ID, "sculk_tnt"), SCULK_TNT);
    }
}
