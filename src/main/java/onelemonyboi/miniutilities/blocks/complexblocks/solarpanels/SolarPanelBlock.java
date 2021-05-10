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
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public SolarPanelBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Deprecated
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
    }

    public static final VoxelShape NorthShape = Stream.of(
            Block.makeCuboidShape(0, 0, 10, 16, 16, 16),
            Block.makeCuboidShape(1, 1, 1, 15, 15, 3),
            Block.makeCuboidShape(0.75, 6, 1.25, 15.25, 10, 3.25),
            Block.makeCuboidShape(6, 0.75, 1.25, 10, 15.25, 3.25),
            Block.makeCuboidShape(0, 0, 0.75, 1, 16, 2),
            Block.makeCuboidShape(15, 0, 0.75, 16, 16, 2),
            Block.makeCuboidShape(1, 0, 0.75, 15, 1, 2),
            Block.makeCuboidShape(1, 15, 0.75, 15, 16, 2),
            Block.makeCuboidShape(6, 6, 3, 10, 10, 4),
            Block.makeCuboidShape(6.5, 6.5, 4, 9.5, 9.5, 7),
            Block.makeCuboidShape(6, 6, 7, 10, 10, 9),
            Block.makeCuboidShape(4, 4, 9, 12, 12, 10),
            Block.makeCuboidShape(11, 7, 3, 13, 9, 4),
            Block.makeCuboidShape(11.25, 7.25, 4, 11.75, 7.75, 5),
            Block.makeCuboidShape(12.25, 8.25, 4, 12.75, 8.75, 10),
            Block.makeCuboidShape(12, 8, 9.75, 13, 9, 10),
            Block.makeCuboidShape(9.25, 7.25, 5, 11.75, 7.75, 5.5),
            Block.makeCuboidShape(9.25, 7, 4.75, 9.75, 8, 5.75)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    public static final VoxelShape SouthShape = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 16, 6),
            Block.makeCuboidShape(1, 1, 13, 15, 15, 15),
            Block.makeCuboidShape(0.75, 6, 12.75, 15.25, 10, 14.75),
            Block.makeCuboidShape(6, 0.75, 12.75, 10, 15.25, 14.75),
            Block.makeCuboidShape(15, 0, 14, 16, 16, 15.25),
            Block.makeCuboidShape(0, 0, 14, 1, 16, 15.25),
            Block.makeCuboidShape(1, 0, 14, 15, 1, 15.25),
            Block.makeCuboidShape(1, 15, 14, 15, 16, 15.25),
            Block.makeCuboidShape(6, 6, 12, 10, 10, 13),
            Block.makeCuboidShape(6.5, 6.5, 9, 9.5, 9.5, 12),
            Block.makeCuboidShape(6, 6, 7, 10, 10, 9),
            Block.makeCuboidShape(4, 4, 6, 12, 12, 7),
            Block.makeCuboidShape(3, 7, 12, 5, 9, 13),
            Block.makeCuboidShape(4.25, 7.25, 11, 4.75, 7.75, 12),
            Block.makeCuboidShape(3.25, 8.25, 6, 3.75, 8.75, 12),
            Block.makeCuboidShape(3, 8, 6, 4, 9, 6.25),
            Block.makeCuboidShape(4.25, 7.25, 10.5, 6.75, 7.75, 11),
            Block.makeCuboidShape(6.25, 7, 10.25, 6.75, 8, 11.25)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    public static final VoxelShape WestShape = Stream.of(
            Block.makeCuboidShape(10, 0, 0, 16, 16, 16),
            Block.makeCuboidShape(1, 1, 1, 3, 15, 15),
            Block.makeCuboidShape(1.25, 6, 0.75, 3.25, 10, 15.25),
            Block.makeCuboidShape(1.25, 0.75, 6, 3.25, 15.25, 10),
            Block.makeCuboidShape(0.75, 0, 15, 2, 16, 16),
            Block.makeCuboidShape(0.75, 0, 0, 2, 16, 1),
            Block.makeCuboidShape(0.75, 0, 1, 2, 1, 15),
            Block.makeCuboidShape(0.75, 15, 1, 2, 16, 15),
            Block.makeCuboidShape(3, 6, 6, 4, 10, 10),
            Block.makeCuboidShape(4, 6.5, 6.5, 7, 9.5, 9.5),
            Block.makeCuboidShape(7, 6, 6, 9, 10, 10),
            Block.makeCuboidShape(9, 4, 4, 10, 12, 12),
            Block.makeCuboidShape(3, 7, 3, 4, 9, 5),
            Block.makeCuboidShape(4, 7.25, 4.25, 5, 7.75, 4.75),
            Block.makeCuboidShape(4, 8.25, 3.25, 10, 8.75, 3.75),
            Block.makeCuboidShape(9.75, 8, 3, 10, 9, 4),
            Block.makeCuboidShape(5, 7.25, 4.25, 5.5, 7.75, 6.75),
            Block.makeCuboidShape(4.75, 7, 6.25, 5.75, 8, 6.75)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    public static final VoxelShape EastShape = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 6, 16, 16),
            Block.makeCuboidShape(13, 1, 1, 15, 15, 15),
            Block.makeCuboidShape(12.75, 6, 0.75, 14.75, 10, 15.25),
            Block.makeCuboidShape(12.75, 0.75, 6, 14.75, 15.25, 10),
            Block.makeCuboidShape(14, 0, 0, 15.25, 16, 1),
            Block.makeCuboidShape(14, 0, 15, 15.25, 16, 16),
            Block.makeCuboidShape(14, 0, 1, 15.25, 1, 15),
            Block.makeCuboidShape(14, 15, 1, 15.25, 16, 15),
            Block.makeCuboidShape(12, 6, 6, 13, 10, 10),
            Block.makeCuboidShape(9, 6.5, 6.5, 12, 9.5, 9.5),
            Block.makeCuboidShape(7, 6, 6, 9, 10, 10),
            Block.makeCuboidShape(6, 4, 4, 7, 12, 12),
            Block.makeCuboidShape(12, 7, 11, 13, 9, 13),
            Block.makeCuboidShape(11, 7.25, 11.25, 12, 7.75, 11.75),
            Block.makeCuboidShape(6, 8.25, 12.25, 12, 8.75, 12.75),
            Block.makeCuboidShape(6, 8, 12, 6.25, 9, 13),
            Block.makeCuboidShape(10.5, 7.25, 9.25, 11, 7.75, 11.75),
            Block.makeCuboidShape(10.25, 7, 9.25, 11.25, 8, 9.75)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
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
    public static final VoxelShape DownShape = Stream.of(
            Block.makeCuboidShape(0, 10, 0, 16, 16, 16),
            Block.makeCuboidShape(1, 1, 1, 15, 3, 15),
            Block.makeCuboidShape(0.75, 1.25, 6, 15.25, 3.25, 10),
            Block.makeCuboidShape(6, 1.25, 0.75, 10, 3.25, 15.25),
            Block.makeCuboidShape(0, 0.75, 0, 1, 2, 16),
            Block.makeCuboidShape(15, 0.75, 0, 16, 2, 16),
            Block.makeCuboidShape(1, 0.75, 15, 15, 2, 16),
            Block.makeCuboidShape(1, 0.75, 0, 15, 2, 1),
            Block.makeCuboidShape(6, 3, 6, 10, 4, 10),
            Block.makeCuboidShape(6.5, 4, 6.5, 9.5, 7, 9.5),
            Block.makeCuboidShape(6, 7, 6, 10, 9, 10),
            Block.makeCuboidShape(4, 9, 4, 12, 10, 12),
            Block.makeCuboidShape(11, 3, 7, 13, 4, 9),
            Block.makeCuboidShape(11.25, 4, 8.25, 11.75, 5, 8.75),
            Block.makeCuboidShape(12.25, 4, 7.25, 12.75, 10, 7.75),
            Block.makeCuboidShape(12, 9.75, 7, 13, 10, 8),
            Block.makeCuboidShape(9.25, 5, 8.25, 11.75, 5.5, 8.75),
            Block.makeCuboidShape(9.25, 4.75, 8, 9.75, 5.75, 9)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction dir = state.get(PROPERTY_FACING);
        switch (dir) {
            case NORTH:
                return NorthShape;
            case SOUTH:
                return SouthShape;
            case WEST:
                return WestShape;
            case EAST:
                return EastShape;
            case UP:
                return UpShape;
            default:
                return DownShape;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof MechanicalPlacerTile) {
            ItemStack itemStack = new ItemStack(this);
            CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
            itemStack.setTagInfo("BlockEntityTag", compoundNBT);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}
