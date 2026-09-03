package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MMBlockEntityRegistries {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MousesMagics.MOD_ID);

    //public static final Supplier<BlockEntityType<OvergrownSickleBlockEntity>> OVERGROWN_SICKLE_BLOCK =
    //        BLOCK_ENTITIES.register("overgrown_sickle_block", () -> BlockEntityType.Builder.of(
    //                OvergrownSickleBlockEntity::new, MMBlocks.OVERGROWN_SICKLE_BLOCK.get()).build(null));

    public static void register(IEventBus modEventBus){
        BLOCK_ENTITIES.register(modEventBus);
    }
}

