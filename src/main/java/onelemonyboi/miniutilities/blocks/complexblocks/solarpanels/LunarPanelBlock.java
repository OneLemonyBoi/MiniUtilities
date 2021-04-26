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
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.MechanicalPlacerTile;

import static net.minecraft.block.BarrelBlock.PROPERTY_FACING;

public class LunarPanelBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public LunarPanelBlock(Properties properties) {
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

    public static final VoxelShape NorthShape = Block.makeCuboidShape(0, 0, 8, 16, 16, 16);
    public static final VoxelShape SouthShape = Block.makeCuboidShape(0, 0, 0, 16, 16, 8);
    public static final VoxelShape WestShape = Block.makeCuboidShape(8, 0, 0, 16, 16, 16);
    public static final VoxelShape EastShape = Block.makeCuboidShape(0, 0, 0, 8, 16, 16);
    public static final VoxelShape UpShape = Block.makeCuboidShape(0, 0, 0, 16, 8, 16);
    public static final VoxelShape DownShape = Block.makeCuboidShape(0, 8, 0, 16, 16, 16);

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
