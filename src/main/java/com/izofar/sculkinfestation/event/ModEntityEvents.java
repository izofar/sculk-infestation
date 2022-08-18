package com.izofar.sculkinfestation.event;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.renderer.*;
import com.izofar.sculkinfestation.client.renderer.sculkipede.SculkipedeBodyRenderer;
import com.izofar.sculkinfestation.client.renderer.sculkipede.SculkipedeHeadRenderer;
import com.izofar.sculkinfestation.client.renderer.sculkipede.SculkipedeTailRenderer;
import com.izofar.sculkinfestation.entity.*;
import com.izofar.sculkinfestation.entity.sculkipede.SculkipedeBody;
import com.izofar.sculkinfestation.entity.sculkipede.SculkipedeHead;
import com.izofar.sculkinfestation.init.ModEntityTypes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SculkInfestation.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ModEntityEvents {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.SCULKED_CREEPER.get(), SculkedCreeper.createAttributes().build());
        event.put(ModEntityTypes.SCULKED_SKELETON.get(), SculkedSkeleton.createAttributes().build());
        event.put(ModEntityTypes.SCULKED_SPIDER.get(), SculkedSpider.createAttributes().build());
        event.put(ModEntityTypes.SCULKED_ZOMBIE.get(), SculkedZombie.createAttributes().build());
        event.put(ModEntityTypes.SCULKIPEDE_HEAD.get(), SculkipedeHead.createAttributes().build());
        event.put(ModEntityTypes.SCULKIPEDE_BODY.get(), SculkipedeBody.createAttributes().build());
        event.put(ModEntityTypes.SCULKIPEDE_TAIL.get(), SculkipedeBody.createAttributes().build());
        event.put(ModEntityTypes.SCULK_HOUND.get(), SculkHound.createAttributes().build());
        event.put(ModEntityTypes.SCULK_LURKER.get(), SculkLurker.createAttributes().build());
        event.put(ModEntityTypes.SCULK_HUGGER.get(), SculkHugger.createAttributes().build());

    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.SCULKED_CREEPER.get(), SculkedCreeperRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULKED_SKELETON.get(), SculkedSkeletonRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULKED_SPIDER.get(), SculkedSpiderRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULKED_ZOMBIE.get(), SculkedZombieRenderer:: new);
        event.registerEntityRenderer(ModEntityTypes.SCULK_TNT.get(), SculkTntRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULKIPEDE_HEAD.get(), SculkipedeHeadRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULKIPEDE_BODY.get(), SculkipedeBodyRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULKIPEDE_TAIL.get(), SculkipedeTailRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULK_HOUND.get(), SculkHoundRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULK_LURKER.get(), SculkLurkerRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SCULK_HUGGER.get(), SculkHuggerRenderer::new);

    }
}
