package net.izofar.sculkinfestation.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.izofar.sculkinfestation.SkulkInfestation;
import net.izofar.sculkinfestation.entity.SkulkedCreeper;
import net.izofar.sculkinfestation.entity.SkulkedSkeleton;
import net.izofar.sculkinfestation.entity.SkulkedZombie;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntityTypes {

    public static final EntityType<SkulkedCreeper> SKULKED_CREEPER = Registry.register(Registry.ENTITY_TYPE, new Identifier(SkulkInfestation.MOD_ID, "skulked_creeper"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SkulkedCreeper::new).dimensions(EntityDimensions.fixed(0.6f, 1.7f)).trackRangeChunks(8).build());
    public static final EntityType<SkulkedSkeleton> SKULKED_SKELETON = Registry.register(Registry.ENTITY_TYPE, new Identifier(SkulkInfestation.MOD_ID, "skulked_skeleton"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SkulkedSkeleton::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).trackRangeChunks(8).build());
    public static final EntityType<SkulkedZombie> SKULKED_ZOMBIE = Registry.register(Registry.ENTITY_TYPE, new Identifier(SkulkInfestation.MOD_ID, "skulked_zombie"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SkulkedZombie::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).trackRangeChunks(8).build());

    public static void registerModEntities() {
        FabricDefaultAttributeRegistry.register(SKULKED_CREEPER, SkulkedCreeper.createMobAttributes());
        FabricDefaultAttributeRegistry.register(SKULKED_SKELETON, SkulkedSkeleton.createMobAttributes());
        FabricDefaultAttributeRegistry.register(SKULKED_ZOMBIE, SkulkedZombie.createMobAttributes());
    }
}
