package net.izofar.sculkinfestation;

import net.fabricmc.api.ModInitializer;
import net.izofar.sculkinfestation.init.ModEntityTypes;
import net.izofar.sculkinfestation.init.ModItems;
import net.izofar.sculkinfestation.init.ModModelLayers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkulkInfestation implements ModInitializer {

	public static final String MOD_ID = "skulkinfestation";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEntityTypes.registerModEntities();
		ModModelLayers.registerModelRenderers();
	}
}
