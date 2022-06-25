package net.izofar.sculkinfestation.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.izofar.sculkinfestation.SculkInfestation;
import net.izofar.sculkinfestation.entity.SculkedSkeleton;
import net.izofar.sculkinfestation.entity.SculkTntEntity;
import net.izofar.sculkinfestation.entity.SculkedCreeper;
import net.izofar.sculkinfestation.entity.SculkedZombie;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntityTypes {

    public static final EntityType<SculkedCreeper> SCULKED_CREEPER = Registry.register(Registry.ENTITY_TYPE, new Identifier(SculkInfestation.MOD_ID, "sculked_creeper"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SculkedCreeper::new).dimensions(EntityDimensions.fixed(0.6f, 1.7f)).trackRangeChunks(8).build());
    public static final EntityType<SculkedSkeleton> SCULKED_SKELETON = Registry.register(Registry.ENTITY_TYPE, new Identifier(SculkInfestation.MOD_ID, "sculked_skeleton"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SculkedSkeleton::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).trackRangeChunks(8).build());
    public static final EntityType<SculkedZombie> SCULKED_ZOMBIE = Registry.register(Registry.ENTITY_TYPE, new Identifier(SculkInfestation.MOD_ID, "sculked_zombie"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SculkedZombie::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).trackRangeChunks(8).build());
    public static final EntityType<SculkTntEntity> SCULK_TNT = Registry.register(Registry.ENTITY_TYPE, new Identifier(SculkInfestation.MOD_ID, "sculk_tnt"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, SculkTntEntity::new).fireImmune().dimensions(EntityDimensions.fixed(0.98F, 0.98F)).trackRangeChunks(10).trackedUpdateRate(10).build());


    public static void registerModEntities() {
        FabricDefaultAttributeRegistry.register(SCULKED_CREEPER, SculkedCreeper.createMobAttributes());
        FabricDefaultAttributeRegistry.register(SCULKED_SKELETON, SculkedSkeleton.createMobAttributes());
        FabricDefaultAttributeRegistry.register(SCULKED_ZOMBIE, SculkedZombie.createMobAttributes());
    }
}
