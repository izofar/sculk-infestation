package com.izofar.sculkinfestation;

import com.izofar.sculkinfestation.event.ModEntityEvents;
import com.izofar.sculkinfestation.init.ModBiomeModifiers;
import com.izofar.sculkinfestation.init.ModBlocks;
import com.izofar.sculkinfestation.init.ModEntityTypes;
import com.izofar.sculkinfestation.init.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SculkInfestation.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SculkInfestation {
    public static final String MODID = "sculkinfestation";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SculkInfestation() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModBiomeModifiers.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ModEntityEvents.class);

    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(SculkInfestation::doCommonWork);
    }

    private static void doCommonWork(){
        ModItems.addNewCompostables();
    }
}
