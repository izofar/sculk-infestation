package net.izofar.skulkinfestation.init;

import net.izofar.skulkinfestation.SkulkInfestation;
import net.izofar.skulkinfestation.block.SkulkTntBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block SKULK_TNT = new SkulkTntBlock(AbstractBlock.Settings.of(Material.TNT).breakInstantly().sounds(BlockSoundGroup.GRASS));

    public static void registerModBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(SkulkInfestation.MOD_ID, "skulk_tnt"), SKULK_TNT);
    }
}
