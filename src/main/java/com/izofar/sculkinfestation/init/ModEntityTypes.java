package com.izofar.sculkinfestation.init;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.entity.*;
import com.izofar.sculkinfestation.entity.sculkipede.SculkipedeBody;
import com.izofar.sculkinfestation.entity.sculkipede.SculkipedeHead;
import com.izofar.sculkinfestation.entity.sculkipede.SculkipedeTail;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> MOD_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SculkInfestation.MODID);

    public static final RegistryObject<EntityType<SculkedCreeper>> SCULKED_CREEPER = MOD_ENTITY_TYPES.register("sculked_creeper", () -> EntityType.Builder.of(SculkedCreeper::new, MobCategory.MONSTER).sized(0.6f, 1.7f).clientTrackingRange(8).build(new ResourceLocation(SculkInfestation.MODID, "sculked_creeper").toString()));
    public static final RegistryObject<EntityType<SculkedSkeleton>> SCULKED_SKELETON = MOD_ENTITY_TYPES.register("sculked_skeleton", () -> EntityType.Builder.of(SculkedSkeleton::new, MobCategory.MONSTER).sized(0.6f, 1.99f).clientTrackingRange(8).build(new ResourceLocation(SculkInfestation.MODID, "sculked_skeleton").toString()));
    public static final RegistryObject<EntityType<SculkedSpider>> SCULKED_SPIDER = MOD_ENTITY_TYPES.register("sculked_spider", () -> EntityType.Builder.of(SculkedSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).clientTrackingRange(8).build(new ResourceLocation(SculkInfestation.MODID, "sculked_spider").toString()));
    public static final RegistryObject<EntityType<SculkedZombie>> SCULKED_ZOMBIE = MOD_ENTITY_TYPES.register("sculked_zombie", () -> EntityType.Builder.of(SculkedZombie::new, MobCategory.MONSTER).sized(0.6f, 1.95f).clientTrackingRange(8).build(new ResourceLocation(SculkInfestation.MODID, "sculked_zombie").toString()));

    public static final RegistryObject<EntityType<SculkipedeHead>> SCULKIPEDE_HEAD = MOD_ENTITY_TYPES.register("sculkipede_head", () -> EntityType.Builder.of(SculkipedeHead::new, MobCategory.MONSTER).sized(0.45F, 0.45F).clientTrackingRange(8).build(new ResourceLocation(SculkInfestation.MODID,"sculkipede_head").toString()));
    public static final RegistryObject<EntityType<SculkipedeBody>> SCULKIPEDE_BODY = MOD_ENTITY_TYPES.register("sculkipede_body", () -> EntityType.Builder.of(SculkipedeBody::new, MobCategory.CREATURE).sized(0.45F, 0.45F).clientTrackingRange(8).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).build(new ResourceLocation(SculkInfestation.MODID,"sculkipede_body").toString()));
    public static final RegistryObject<EntityType<SculkipedeTail>> SCULKIPEDE_TAIL = MOD_ENTITY_TYPES.register("sculkipede_tail", () -> EntityType.Builder.of(SculkipedeTail::new, MobCategory.CREATURE).sized(0.45F, 0.45F).clientTrackingRange(8).fireImmune().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).build(new ResourceLocation(SculkInfestation.MODID,"sculkipede_tail").toString()));

    public static final RegistryObject<EntityType<SculkHound>> SCULK_HOUND = MOD_ENTITY_TYPES.register("sculk_hound", () -> EntityType.Builder.of(SculkHound::new, MobCategory.MONSTER).sized(0.6F, 0.85F).fireImmune().clientTrackingRange(10).build(new ResourceLocation(SculkInfestation.MODID,"sculk_hound").toString()));
    public static final RegistryObject<EntityType<SculkLurker>> SCULK_LURKER = MOD_ENTITY_TYPES.register("sculk_lurker", () -> EntityType.Builder.of(SculkLurker::new, MobCategory.MONSTER).sized(0.7F, 0.5F).clientTrackingRange(8).build(new ResourceLocation(SculkInfestation.MODID, "sculk_lurker").toString()));
    public static final RegistryObject<EntityType<SculkHugger>> SCULK_HUGGER = MOD_ENTITY_TYPES.register("sculked_hugger", () -> EntityType.Builder.of(SculkHugger::new, MobCategory.MONSTER).sized(1.4F, 0.9F).clientTrackingRange(8).build(new ResourceLocation(SculkInfestation.MODID, "sculked_hugger").toString()));

    public static final RegistryObject<EntityType<PrimedSculkTnt>> SCULK_TNT = MOD_ENTITY_TYPES.register("sculk_tnt", () -> EntityType.Builder.of(PrimedSculkTnt::new, MobCategory.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build(new ResourceLocation(SculkInfestation.MODID, "sculk_tnt").toString()));

    public static void register(IEventBus eventBus) { MOD_ENTITY_TYPES.register(eventBus); }

}
