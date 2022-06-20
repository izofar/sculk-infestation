package net.izofar.skulkinfestation.init;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.izofar.skulkinfestation.SkulkInfestation;
import net.izofar.skulkinfestation.client.model.SkulkedCreeperModel;
import net.izofar.skulkinfestation.client.model.SkulkedSkeletonModel;
import net.izofar.skulkinfestation.client.model.SkulkedZombieModel;
import net.izofar.skulkinfestation.client.renderer.SkulkedCreeperRenderer;
import net.izofar.skulkinfestation.client.renderer.SkulkedSkeletonRenderer;
import net.izofar.skulkinfestation.client.renderer.SkulkedZombieRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer SKULKED_CREEPER = new EntityModelLayer(new Identifier(SkulkInfestation.MOD_ID, "skulked_creeper"), "main");
    public static final EntityModelLayer SKULKED_SKELETON = new EntityModelLayer(new Identifier(SkulkInfestation.MOD_ID, "skulked_skeleton"), "main");
    public static final EntityModelLayer SKULKED_ZOMBIE = new EntityModelLayer(new Identifier(SkulkInfestation.MOD_ID, "skulked_zombie"), "main");

    public static void registerModelRenderers() {
        EntityRendererRegistry.register(ModEntityTypes.SKULKED_CREEPER, SkulkedCreeperRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SKULKED_SKELETON, SkulkedSkeletonRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SKULKED_ZOMBIE, SkulkedZombieRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SKULKED_CREEPER, SkulkedCreeperModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SKULKED_SKELETON, SkulkedSkeletonModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SKULKED_ZOMBIE, SkulkedZombieModel::getTexturedModelData);
    }
}
