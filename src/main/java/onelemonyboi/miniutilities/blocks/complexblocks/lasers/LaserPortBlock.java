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

public class LaserPortBlock extends DirectionalBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;

    public LaserPortBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public static final VoxelShape UpShape = Stream.of(
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
        return UpShape;
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
