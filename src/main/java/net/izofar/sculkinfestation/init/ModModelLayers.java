package net.izofar.sculkinfestation.init;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.izofar.sculkinfestation.SculkInfestation;
import net.izofar.sculkinfestation.client.model.SculkedCreeperModel;
import net.izofar.sculkinfestation.client.model.SculkedSkeletonModel;
import net.izofar.sculkinfestation.client.model.SculkedZombieModel;
import net.izofar.sculkinfestation.client.renderer.SculkTntEntityRenderer;
import net.izofar.sculkinfestation.client.renderer.SculkedCreeperRenderer;
import net.izofar.sculkinfestation.client.renderer.SculkedSkeletonRenderer;
import net.izofar.sculkinfestation.client.renderer.SculkedZombieRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer SCULKED_CREEPER = new EntityModelLayer(new Identifier(SculkInfestation.MOD_ID, "sculked_creeper"), "main");
    public static final EntityModelLayer SCULKED_SKELETON = new EntityModelLayer(new Identifier(SculkInfestation.MOD_ID, "sculked_skeleton"), "main");
    public static final EntityModelLayer SCULKED_ZOMBIE = new EntityModelLayer(new Identifier(SculkInfestation.MOD_ID, "sculked_zombie"), "main");
    public static final EntityModelLayer SCULK_TNT = new EntityModelLayer(new Identifier(SculkInfestation.MOD_ID, "sculk_tnt"), "main");

    public static void registerModelRenderers() {
        EntityRendererRegistry.register(ModEntityTypes.SCULKED_CREEPER, SculkedCreeperRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SCULKED_SKELETON, SculkedSkeletonRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SCULKED_ZOMBIE, SculkedZombieRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SCULK_TNT, SculkTntEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SCULKED_CREEPER, SculkedCreeperModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SCULKED_SKELETON, SculkedSkeletonModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SCULKED_ZOMBIE, SculkedZombieModel::getTexturedModelData);
    }
}
