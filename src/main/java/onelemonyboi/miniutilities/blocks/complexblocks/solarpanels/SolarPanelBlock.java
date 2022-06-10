package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.stream.Stream;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SolarPanelBlock extends Block {
    public SolarPanelBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
        super(properties);
    }

    public static final net.minecraft.world.phys.shapes.VoxelShape UpShape = Stream.of(
            net.minecraft.world.level.block.Block.box(0, 0, 0, 16, 6, 16),
            net.minecraft.world.level.block.Block.box(1, 13, 1, 15, 15, 15),
            Block.box(0.75, 12.75, 6, 15.25, 14.75, 10),
            net.minecraft.world.level.block.Block.box(6, 12.75, 0.75, 10, 14.75, 15.25),
            net.minecraft.world.level.block.Block.box(0, 14, 0, 1, 15.25, 16),
            Block.box(15, 14, 0, 16, 15.25, 16),
            net.minecraft.world.level.block.Block.box(1, 14, 0, 15, 15.25, 1),
            Block.box(1, 14, 15, 15, 15.25, 16),
            Block.box(6, 12, 6, 10, 13, 10),
            net.minecraft.world.level.block.Block.box(6.5, 9, 6.5, 9.5, 12, 9.5),
            Block.box(6, 7, 6, 10, 9, 10),
            Block.box(4, 6, 4, 12, 7, 12),
            Block.box(11, 12, 7, 13, 13, 9),
            net.minecraft.world.level.block.Block.box(11.25, 11, 7.25, 11.75, 12, 7.75),
            Block.box(12.25, 6, 8.25, 12.75, 12, 8.75),
            Block.box(12, 6, 8, 13, 6.25, 9),
            Block.box(9.25, 10.5, 7.25, 11.75, 11, 7.75),
            net.minecraft.world.level.block.Block.box(9.25, 10.25, 7, 9.75, 11.25, 8)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return UpShape;
    }
}
