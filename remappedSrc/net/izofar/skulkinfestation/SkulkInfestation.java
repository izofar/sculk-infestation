package net.izofar.skulkinfestation;

import net.fabricmc.api.ModInitializer;
import net.izofar.skulkinfestation.init.ModEntityTypes;
import net.izofar.skulkinfestation.init.ModItems;
import net.izofar.skulkinfestation.init.ModModelLayers;
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
