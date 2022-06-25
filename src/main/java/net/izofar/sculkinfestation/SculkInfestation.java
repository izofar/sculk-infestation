package net.izofar.sculkinfestation;

import net.fabricmc.api.ModInitializer;
import net.izofar.sculkinfestation.init.ModBlocks;
import net.izofar.sculkinfestation.init.ModEntityTypes;
import net.izofar.sculkinfestation.init.ModItems;
import net.izofar.sculkinfestation.init.ModModelLayers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SculkInfestation implements ModInitializer {

	public static final String MOD_ID = "sculkinfestation";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModEntityTypes.registerModEntities();
		ModModelLayers.registerModelRenderers();
	}
}
