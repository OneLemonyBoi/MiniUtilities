package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;

import java.util.stream.Stream;

import static net.minecraft.block.BarrelBlock.PROPERTY_FACING;

public class SolarPanelBlock extends Block {
    public SolarPanelBlock(Properties properties) {
        super(properties);
    }

    public static final VoxelShape UpShape = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 6, 16),
            Block.makeCuboidShape(1, 13, 1, 15, 15, 15),
            Block.makeCuboidShape(0.75, 12.75, 6, 15.25, 14.75, 10),
            Block.makeCuboidShape(6, 12.75, 0.75, 10, 14.75, 15.25),
            Block.makeCuboidShape(0, 14, 0, 1, 15.25, 16),
            Block.makeCuboidShape(15, 14, 0, 16, 15.25, 16),
            Block.makeCuboidShape(1, 14, 0, 15, 15.25, 1),
            Block.makeCuboidShape(1, 14, 15, 15, 15.25, 16),
            Block.makeCuboidShape(6, 12, 6, 10, 13, 10),
            Block.makeCuboidShape(6.5, 9, 6.5, 9.5, 12, 9.5),
            Block.makeCuboidShape(6, 7, 6, 10, 9, 10),
            Block.makeCuboidShape(4, 6, 4, 12, 7, 12),
            Block.makeCuboidShape(11, 12, 7, 13, 13, 9),
            Block.makeCuboidShape(11.25, 11, 7.25, 11.75, 12, 7.75),
            Block.makeCuboidShape(12.25, 6, 8.25, 12.75, 12, 8.75),
            Block.makeCuboidShape(12, 6, 8, 13, 6.25, 9),
            Block.makeCuboidShape(9.25, 10.5, 7.25, 11.75, 11, 7.75),
            Block.makeCuboidShape(9.25, 10.25, 7, 9.75, 11.25, 8)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return UpShape;
    }
}
