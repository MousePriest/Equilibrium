package com.mouse.mousesmagics.block.entity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/*public class OvergrownSickleBlockRenderer extends GeoBlockRenderer<OvergrownSickleBlockEntity> {

    public OvergrownSickleBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new OvergrownSickleBlockModel());
    }

    @Override
    public AABB getRenderBoundingBox(OvergrownSickleBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();

        return new AABB(
                pos.getX() - 2, pos.getY(),     pos.getZ() - 2,
                pos.getX() + 3, pos.getY() + 4, pos.getZ() + 3);
    }
}*/
