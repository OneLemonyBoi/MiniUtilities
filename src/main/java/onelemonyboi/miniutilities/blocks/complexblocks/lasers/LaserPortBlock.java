package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nullable;
import java.util.stream.Stream;

import static net.minecraft.block.BarrelBlock.PROPERTY_FACING;

public class LaserPortBlock extends DirectionalBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;

    public LaserPortBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public static final VoxelShape NorthShape = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 16, 6),
            Block.makeCuboidShape(7, 7, 11, 9, 9, 13),
            Block.makeCuboidShape(6, 6, 6, 10, 10, 7),
            Block.makeCuboidShape(6.5, 6.5, 7, 9.5, 9.5, 8),
            Block.makeCuboidShape(10, 8.75, 6, 11.5, 9.25, 6.5),
            Block.makeCuboidShape(9, 13.75, 6, 11.5, 14.25, 6.5),
            Block.makeCuboidShape(11, 9.25, 6, 11.5, 13.75, 6.5),
            Block.makeCuboidShape(7.25, 3.25, 6, 7.75, 6.25, 6.5),
            Block.makeCuboidShape(8.25, 3.25, 6, 8.75, 6.25, 6.5),
            Block.makeCuboidShape(9.9, 7, 6, 12.4, 7.5, 6.5),
            Block.makeCuboidShape(3, 7.25, 6, 6.5, 7.75, 6.5),
            Block.makeCuboidShape(7.5, 10, 10, 8.5, 11, 14),
            Block.makeCuboidShape(6, 10, 11.5, 10, 11, 12.5),
            Block.makeCuboidShape(5, 7.5, 10, 6, 8.5, 14),
            Block.makeCuboidShape(7.5, 5, 14, 8.5, 11, 15),
            Block.makeCuboidShape(10, 5, 11.5, 11, 11, 12.5),
            Block.makeCuboidShape(5, 7.5, 14, 11, 8.5, 15),
            Block.makeCuboidShape(7.5, 5, 9, 8.5, 11, 10),
            Block.makeCuboidShape(5, 5, 11.5, 6, 11, 12.5),
            Block.makeCuboidShape(5, 7.5, 9, 11, 8.5, 10),
            Block.makeCuboidShape(7.5, 5, 10, 8.5, 6, 14),
            Block.makeCuboidShape(6, 5, 11.5, 10, 6, 12.5),
            Block.makeCuboidShape(10, 7.5, 10, 11, 8.5, 14)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape SouthShape = Stream.of(
            Block.makeCuboidShape(0, 0, 10, 16, 16, 16),
            Block.makeCuboidShape(7, 7, 3, 9, 9, 5),
            Block.makeCuboidShape(6, 6, 9, 10, 10, 10),
            Block.makeCuboidShape(6.5, 6.5, 8, 9.5, 9.5, 9),
            Block.makeCuboidShape(4.5, 8.75, 9.5, 6, 9.25, 10),
            Block.makeCuboidShape(4.5, 13.75, 9.5, 7, 14.25, 10),
            Block.makeCuboidShape(4.5, 9.25, 9.5, 5, 13.75, 10),
            Block.makeCuboidShape(8.25, 3.25, 9.5, 8.75, 6.25, 10),
            Block.makeCuboidShape(7.25, 3.25, 9.5, 7.75, 6.25, 10),
            Block.makeCuboidShape(3.5, 7, 9.5, 6.1, 7.5, 10),
            Block.makeCuboidShape(9.5, 7.25, 9.5, 13, 7.75, 10),
            Block.makeCuboidShape(7.5, 10, 2, 8.5, 11, 6),
            Block.makeCuboidShape(6, 10, 3.5, 10, 11, 4.5),
            Block.makeCuboidShape(10, 7.5, 2, 11, 8.5, 6),
            Block.makeCuboidShape(7.5, 5, 1, 8.5, 11, 2),
            Block.makeCuboidShape(5, 5, 3.5, 6, 11, 4.5),
            Block.makeCuboidShape(5, 7.5, 1, 11, 8.5, 2),
            Block.makeCuboidShape(7.5, 5, 6, 8.5, 11, 7),
            Block.makeCuboidShape(10, 5, 3.5, 11, 11, 4.5),
            Block.makeCuboidShape(5, 7.5, 6, 11, 8.5, 7),
            Block.makeCuboidShape(7.5, 5, 2, 8.5, 6, 6),
            Block.makeCuboidShape(6, 5, 3.5, 10, 6, 4.5),
            Block.makeCuboidShape(5, 7.5, 2, 6, 8.5, 6)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape WestShape = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 6, 16, 16),
            Block.makeCuboidShape(11, 7, 7, 13, 9, 9),
            Block.makeCuboidShape(6, 6, 6, 7, 10, 10),
            Block.makeCuboidShape(7, 6.5, 6.5, 8, 9.5, 9.5),
            Block.makeCuboidShape(6, 8.75, 4.5, 6.5, 9.25, 6),
            Block.makeCuboidShape(6, 13.75, 4.5, 6.5, 14.25, 7),
            Block.makeCuboidShape(6, 9.25, 4.5, 6.5, 13.75, 5),
            Block.makeCuboidShape(6, 3.25, 8.25, 6.5, 6.25, 8.75),
            Block.makeCuboidShape(6, 3.25, 7.25, 6.5, 6.25, 7.75),
            Block.makeCuboidShape(6, 7, 3.5, 6.5, 7.5, 6.1),
            Block.makeCuboidShape(6, 7.25, 9.5, 6.5, 7.75, 13),
            Block.makeCuboidShape(10, 10, 7.5, 14, 11, 8.5),
            Block.makeCuboidShape(11.5, 10, 6, 12.5, 11, 10),
            Block.makeCuboidShape(10, 7.5, 10, 14, 8.5, 11),
            Block.makeCuboidShape(14, 5, 7.5, 15, 11, 8.5),
            Block.makeCuboidShape(11.5, 5, 5, 12.5, 11, 6),
            Block.makeCuboidShape(14, 7.5, 5, 15, 8.5, 11),
            Block.makeCuboidShape(9, 5, 7.5, 10, 11, 8.5),
            Block.makeCuboidShape(11.5, 5, 10, 12.5, 11, 11),
            Block.makeCuboidShape(9, 7.5, 5, 10, 8.5, 11),
            Block.makeCuboidShape(10, 5, 7.5, 14, 6, 8.5),
            Block.makeCuboidShape(11.5, 5, 6, 12.5, 6, 10),
            Block.makeCuboidShape(10, 7.5, 5, 14, 8.5, 6)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape EastShape = Stream.of(
            Block.makeCuboidShape(10, 0, 0, 16, 16, 16),
            Block.makeCuboidShape(3, 7, 7, 5, 9, 9),
            Block.makeCuboidShape(9, 6, 6, 10, 10, 10),
            Block.makeCuboidShape(8, 6.5, 6.5, 9, 9.5, 9.5),
            Block.makeCuboidShape(9.5, 8.75, 10, 10, 9.25, 11.5),
            Block.makeCuboidShape(9.5, 13.75, 9, 10, 14.25, 11.5),
            Block.makeCuboidShape(9.5, 9.25, 11, 10, 13.75, 11.5),
            Block.makeCuboidShape(9.5, 3.25, 7.25, 10, 6.25, 7.75),
            Block.makeCuboidShape(9.5, 3.25, 8.25, 10, 6.25, 8.75),
            Block.makeCuboidShape(9.5, 7, 9.9, 10, 7.5, 12.4),
            Block.makeCuboidShape(9.5, 7.25, 3, 10, 7.75, 6.5),
            Block.makeCuboidShape(2, 10, 7.5, 6, 11, 8.5),
            Block.makeCuboidShape(3.5, 10, 6, 4.5, 11, 10),
            Block.makeCuboidShape(2, 7.5, 5, 6, 8.5, 6),
            Block.makeCuboidShape(1, 5, 7.5, 2, 11, 8.5),
            Block.makeCuboidShape(3.5, 5, 10, 4.5, 11, 11),
            Block.makeCuboidShape(1, 7.5, 5, 2, 8.5, 11),
            Block.makeCuboidShape(6, 5, 7.5, 7, 11, 8.5),
            Block.makeCuboidShape(3.5, 5, 5, 4.5, 11, 6),
            Block.makeCuboidShape(6, 7.5, 5, 7, 8.5, 11),
            Block.makeCuboidShape(2, 5, 7.5, 6, 6, 8.5),
            Block.makeCuboidShape(3.5, 5, 6, 4.5, 6, 10),
            Block.makeCuboidShape(2, 7.5, 10, 6, 8.5, 11)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape UpShape = Stream.of(
            Block.makeCuboidShape(0, 10, 0, 16, 16, 16),
            Block.makeCuboidShape(7, 3, 7, 9, 5, 9),
            Block.makeCuboidShape(6, 9, 6, 10, 10, 10),
            Block.makeCuboidShape(6.5, 8, 6.5, 9.5, 9, 9.5),
            Block.makeCuboidShape(4.5, 9.5, 6.75, 6, 10, 7.25),
            Block.makeCuboidShape(4.5, 9.5, 1.75, 7, 10, 2.25),
            Block.makeCuboidShape(4.5, 9.5, 2.25, 5, 10, 6.75),
            Block.makeCuboidShape(8.25, 9.5, 9.75, 8.75, 10, 12.75),
            Block.makeCuboidShape(7.25, 9.5, 9.75, 7.75, 10, 12.75),
            Block.makeCuboidShape(3.5, 9.5, 8.5, 6.1, 10, 9),
            Block.makeCuboidShape(9.5, 9.5, 8.25, 13, 10, 8.75),
            Block.makeCuboidShape(7.5, 2, 5, 8.5, 6, 6),
            Block.makeCuboidShape(6, 3.5, 5, 10, 4.5, 6),
            Block.makeCuboidShape(10, 2, 7.5, 11, 6, 8.5),
            Block.makeCuboidShape(7.5, 1, 5, 8.5, 2, 11),
            Block.makeCuboidShape(5, 3.5, 5, 6, 4.5, 11),
            Block.makeCuboidShape(5, 1, 7.5, 11, 2, 8.5),
            Block.makeCuboidShape(7.5, 6, 5, 8.5, 7, 11),
            Block.makeCuboidShape(10, 3.5, 5, 11, 4.5, 11),
            Block.makeCuboidShape(5, 6, 7.5, 11, 7, 8.5),
            Block.makeCuboidShape(7.5, 2, 10, 8.5, 6, 11),
            Block.makeCuboidShape(6, 3.5, 10, 10, 4.5, 11),
            Block.makeCuboidShape(5, 2, 7.5, 6, 6, 8.5)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape DownShape = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 6, 16),
            Block.makeCuboidShape(7, 11, 7, 9, 13, 9),
            Block.makeCuboidShape(6, 6, 6, 10, 7, 10),
            Block.makeCuboidShape(6.5, 7, 6.5, 9.5, 8, 9.5),
            Block.makeCuboidShape(4.5, 6, 8.75, 6, 6.5, 9.25),
            Block.makeCuboidShape(4.5, 6, 13.75, 7, 6.5, 14.25),
            Block.makeCuboidShape(4.5, 6, 9.25, 5, 6.5, 13.75),
            Block.makeCuboidShape(8.25, 6, 3.25, 8.75, 6.5, 6.25),
            Block.makeCuboidShape(7.25, 6, 3.25, 7.75, 6.5, 6.25),
            Block.makeCuboidShape(3.6, 6, 7, 6, 6.5, 7.5),
            Block.makeCuboidShape(9.5, 6, 7.25, 13, 6.5, 7.75),
            Block.makeCuboidShape(7.5, 10, 10, 8.5, 14, 11),
            Block.makeCuboidShape(6, 11.5, 10, 10, 12.5, 11),
            Block.makeCuboidShape(10, 10, 7.5, 11, 14, 8.5),
            Block.makeCuboidShape(7.5, 14, 5, 8.5, 15, 11),
            Block.makeCuboidShape(5, 11.5, 5, 6, 12.5, 11),
            Block.makeCuboidShape(5, 14, 7.5, 11, 15, 8.5),
            Block.makeCuboidShape(7.5, 9, 5, 8.5, 10, 11),
            Block.makeCuboidShape(10, 11.5, 5, 11, 12.5, 11),
            Block.makeCuboidShape(5, 9, 7.5, 11, 10, 8.5),
            Block.makeCuboidShape(7.5, 10, 5, 8.5, 14, 6),
            Block.makeCuboidShape(6, 11.5, 5, 10, 12.5, 6),
            Block.makeCuboidShape(5, 10, 7.5, 6, 14, 8.5)
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
    public boolean isSlimeBlock(BlockState state) {
        return false;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(te instanceof LaserPortTile)) {
            return ActionResultType.PASS;
        }
        if (worldIn.isRemote()) {
            return ActionResultType.SUCCESS;
        }

        LaserPortTile laserPortTile = (LaserPortTile) te;
        laserPortTile.isInput = !laserPortTile.isInput;

        return ActionResultType.CONSUME;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TEList.LaserPortTile.get().create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (worldIn.isRemote()) {
            return;
        }

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        ItemStack itemStack = new ItemStack(this);
        CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
        compoundNBT.remove("x");
        compoundNBT.remove("y");
        compoundNBT.remove("z");
        itemStack.setTagInfo("BlockEntityTag", compoundNBT);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection());
    }

    @Deprecated
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }
}
