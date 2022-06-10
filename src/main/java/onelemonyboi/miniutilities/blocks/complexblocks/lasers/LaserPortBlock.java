package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.*;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviors;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class LaserPortBlock extends BlockBase {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public LaserPortBlock(Properties properties) {
        super(properties, BlockBehaviors.laserPort);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(level, type, TEList.LaserPortTile.get(), LaserPortTile::serverTick, null);
    }

    public static final VoxelShape NorthShape = Stream.of(
            Block.box(0, 0, 0, 16, 16, 6),
            Block.box(7, 7, 11, 9, 9, 13),
            net.minecraft.world.level.block.Block.box(6, 6, 6, 10, 10, 7),
            net.minecraft.world.level.block.Block.box(6.5, 6.5, 7, 9.5, 9.5, 8),
            Block.box(10, 8.75, 6, 11.5, 9.25, 6.5),
            Block.box(9, 13.75, 6, 11.5, 14.25, 6.5),
            Block.box(11, 9.25, 6, 11.5, 13.75, 6.5),
            Block.box(7.25, 3.25, 6, 7.75, 6.25, 6.5),
            net.minecraft.world.level.block.Block.box(8.25, 3.25, 6, 8.75, 6.25, 6.5),
            net.minecraft.world.level.block.Block.box(9.9, 7, 6, 12.4, 7.5, 6.5),
            Block.box(3, 7.25, 6, 6.5, 7.75, 6.5),
            Block.box(7.5, 10, 10, 8.5, 11, 14),
            Block.box(6, 10, 11.5, 10, 11, 12.5),
            net.minecraft.world.level.block.Block.box(5, 7.5, 10, 6, 8.5, 14),
            Block.box(7.5, 5, 14, 8.5, 11, 15),
            Block.box(10, 5, 11.5, 11, 11, 12.5),
            Block.box(5, 7.5, 14, 11, 8.5, 15),
            Block.box(7.5, 5, 9, 8.5, 11, 10),
            net.minecraft.world.level.block.Block.box(5, 5, 11.5, 6, 11, 12.5),
            Block.box(5, 7.5, 9, 11, 8.5, 10),
            Block.box(7.5, 5, 10, 8.5, 6, 14),
            Block.box(6, 5, 11.5, 10, 6, 12.5),
            Block.box(10, 7.5, 10, 11, 8.5, 14)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    public static final VoxelShape SouthShape = Stream.of(
            Block.box(0, 0, 10, 16, 16, 16),
            net.minecraft.world.level.block.Block.box(7, 7, 3, 9, 9, 5),
            Block.box(6, 6, 9, 10, 10, 10),
            Block.box(6.5, 6.5, 8, 9.5, 9.5, 9),
            net.minecraft.world.level.block.Block.box(4.5, 8.75, 9.5, 6, 9.25, 10),
            net.minecraft.world.level.block.Block.box(4.5, 13.75, 9.5, 7, 14.25, 10),
            net.minecraft.world.level.block.Block.box(4.5, 9.25, 9.5, 5, 13.75, 10),
            Block.box(8.25, 3.25, 9.5, 8.75, 6.25, 10),
            Block.box(7.25, 3.25, 9.5, 7.75, 6.25, 10),
            Block.box(3.5, 7, 9.5, 6.1, 7.5, 10),
            net.minecraft.world.level.block.Block.box(9.5, 7.25, 9.5, 13, 7.75, 10),
            net.minecraft.world.level.block.Block.box(7.5, 10, 2, 8.5, 11, 6),
            net.minecraft.world.level.block.Block.box(6, 10, 3.5, 10, 11, 4.5),
            net.minecraft.world.level.block.Block.box(10, 7.5, 2, 11, 8.5, 6),
            Block.box(7.5, 5, 1, 8.5, 11, 2),
            net.minecraft.world.level.block.Block.box(5, 5, 3.5, 6, 11, 4.5),
            Block.box(5, 7.5, 1, 11, 8.5, 2),
            Block.box(7.5, 5, 6, 8.5, 11, 7),
            Block.box(10, 5, 3.5, 11, 11, 4.5),
            net.minecraft.world.level.block.Block.box(5, 7.5, 6, 11, 8.5, 7),
            Block.box(7.5, 5, 2, 8.5, 6, 6),
            net.minecraft.world.level.block.Block.box(6, 5, 3.5, 10, 6, 4.5),
            Block.box(5, 7.5, 2, 6, 8.5, 6)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    public static final VoxelShape WestShape = Stream.of(
            Block.box(0, 0, 0, 6, 16, 16),
            Block.box(11, 7, 7, 13, 9, 9),
            Block.box(6, 6, 6, 7, 10, 10),
            Block.box(7, 6.5, 6.5, 8, 9.5, 9.5),
            Block.box(6, 8.75, 4.5, 6.5, 9.25, 6),
            Block.box(6, 13.75, 4.5, 6.5, 14.25, 7),
            net.minecraft.world.level.block.Block.box(6, 9.25, 4.5, 6.5, 13.75, 5),
            Block.box(6, 3.25, 8.25, 6.5, 6.25, 8.75),
            Block.box(6, 3.25, 7.25, 6.5, 6.25, 7.75),
            net.minecraft.world.level.block.Block.box(6, 7, 3.5, 6.5, 7.5, 6.1),
            net.minecraft.world.level.block.Block.box(6, 7.25, 9.5, 6.5, 7.75, 13),
            Block.box(10, 10, 7.5, 14, 11, 8.5),
            Block.box(11.5, 10, 6, 12.5, 11, 10),
            net.minecraft.world.level.block.Block.box(10, 7.5, 10, 14, 8.5, 11),
            net.minecraft.world.level.block.Block.box(14, 5, 7.5, 15, 11, 8.5),
            Block.box(11.5, 5, 5, 12.5, 11, 6),
            Block.box(14, 7.5, 5, 15, 8.5, 11),
            Block.box(9, 5, 7.5, 10, 11, 8.5),
            Block.box(11.5, 5, 10, 12.5, 11, 11),
            net.minecraft.world.level.block.Block.box(9, 7.5, 5, 10, 8.5, 11),
            net.minecraft.world.level.block.Block.box(10, 5, 7.5, 14, 6, 8.5),
            net.minecraft.world.level.block.Block.box(11.5, 5, 6, 12.5, 6, 10),
            net.minecraft.world.level.block.Block.box(10, 7.5, 5, 14, 8.5, 6)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    public static final VoxelShape EastShape = Stream.of(
            net.minecraft.world.level.block.Block.box(10, 0, 0, 16, 16, 16),
            Block.box(3, 7, 7, 5, 9, 9),
            net.minecraft.world.level.block.Block.box(9, 6, 6, 10, 10, 10),
            Block.box(8, 6.5, 6.5, 9, 9.5, 9.5),
            Block.box(9.5, 8.75, 10, 10, 9.25, 11.5),
            Block.box(9.5, 13.75, 9, 10, 14.25, 11.5),
            Block.box(9.5, 9.25, 11, 10, 13.75, 11.5),
            Block.box(9.5, 3.25, 7.25, 10, 6.25, 7.75),
            Block.box(9.5, 3.25, 8.25, 10, 6.25, 8.75),
            Block.box(9.5, 7, 9.9, 10, 7.5, 12.4),
            net.minecraft.world.level.block.Block.box(9.5, 7.25, 3, 10, 7.75, 6.5),
            Block.box(2, 10, 7.5, 6, 11, 8.5),
            Block.box(3.5, 10, 6, 4.5, 11, 10),
            net.minecraft.world.level.block.Block.box(2, 7.5, 5, 6, 8.5, 6),
            Block.box(1, 5, 7.5, 2, 11, 8.5),
            Block.box(3.5, 5, 10, 4.5, 11, 11),
            net.minecraft.world.level.block.Block.box(1, 7.5, 5, 2, 8.5, 11),
            net.minecraft.world.level.block.Block.box(6, 5, 7.5, 7, 11, 8.5),
            Block.box(3.5, 5, 5, 4.5, 11, 6),
            net.minecraft.world.level.block.Block.box(6, 7.5, 5, 7, 8.5, 11),
            net.minecraft.world.level.block.Block.box(2, 5, 7.5, 6, 6, 8.5),
            net.minecraft.world.level.block.Block.box(3.5, 5, 6, 4.5, 6, 10),
            Block.box(2, 7.5, 10, 6, 8.5, 11)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    public static final VoxelShape UpShape = Stream.of(
            Block.box(0, 10, 0, 16, 16, 16),
            Block.box(7, 3, 7, 9, 5, 9),
            net.minecraft.world.level.block.Block.box(6, 9, 6, 10, 10, 10),
            net.minecraft.world.level.block.Block.box(6.5, 8, 6.5, 9.5, 9, 9.5),
            Block.box(4.5, 9.5, 6.75, 6, 10, 7.25),
            net.minecraft.world.level.block.Block.box(4.5, 9.5, 1.75, 7, 10, 2.25),
            net.minecraft.world.level.block.Block.box(4.5, 9.5, 2.25, 5, 10, 6.75),
            Block.box(8.25, 9.5, 9.75, 8.75, 10, 12.75),
            Block.box(7.25, 9.5, 9.75, 7.75, 10, 12.75),
            Block.box(3.5, 9.5, 8.5, 6.1, 10, 9),
            net.minecraft.world.level.block.Block.box(9.5, 9.5, 8.25, 13, 10, 8.75),
            Block.box(7.5, 2, 5, 8.5, 6, 6),
            Block.box(6, 3.5, 5, 10, 4.5, 6),
            Block.box(10, 2, 7.5, 11, 6, 8.5),
            net.minecraft.world.level.block.Block.box(7.5, 1, 5, 8.5, 2, 11),
            Block.box(5, 3.5, 5, 6, 4.5, 11),
            net.minecraft.world.level.block.Block.box(5, 1, 7.5, 11, 2, 8.5),
            Block.box(7.5, 6, 5, 8.5, 7, 11),
            net.minecraft.world.level.block.Block.box(10, 3.5, 5, 11, 4.5, 11),
            net.minecraft.world.level.block.Block.box(5, 6, 7.5, 11, 7, 8.5),
            Block.box(7.5, 2, 10, 8.5, 6, 11),
            Block.box(6, 3.5, 10, 10, 4.5, 11),
            net.minecraft.world.level.block.Block.box(5, 2, 7.5, 6, 6, 8.5)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    public static final VoxelShape DownShape = Stream.of(
            net.minecraft.world.level.block.Block.box(0, 0, 0, 16, 6, 16),
            net.minecraft.world.level.block.Block.box(7, 11, 7, 9, 13, 9),
            net.minecraft.world.level.block.Block.box(6, 6, 6, 10, 7, 10),
            net.minecraft.world.level.block.Block.box(6.5, 7, 6.5, 9.5, 8, 9.5),
            Block.box(4.5, 6, 8.75, 6, 6.5, 9.25),
            Block.box(4.5, 6, 13.75, 7, 6.5, 14.25),
            Block.box(4.5, 6, 9.25, 5, 6.5, 13.75),
            net.minecraft.world.level.block.Block.box(8.25, 6, 3.25, 8.75, 6.5, 6.25),
            net.minecraft.world.level.block.Block.box(7.25, 6, 3.25, 7.75, 6.5, 6.25),
            net.minecraft.world.level.block.Block.box(3.6, 6, 7, 6, 6.5, 7.5),
            Block.box(9.5, 6, 7.25, 13, 6.5, 7.75),
            net.minecraft.world.level.block.Block.box(7.5, 10, 10, 8.5, 14, 11),
            Block.box(6, 11.5, 10, 10, 12.5, 11),
            net.minecraft.world.level.block.Block.box(10, 10, 7.5, 11, 14, 8.5),
            Block.box(7.5, 14, 5, 8.5, 15, 11),
            Block.box(5, 11.5, 5, 6, 12.5, 11),
            net.minecraft.world.level.block.Block.box(5, 14, 7.5, 11, 15, 8.5),
            net.minecraft.world.level.block.Block.box(7.5, 9, 5, 8.5, 10, 11),
            Block.box(10, 11.5, 5, 11, 12.5, 11),
            Block.box(5, 9, 7.5, 11, 10, 8.5),
            Block.box(7.5, 10, 5, 8.5, 14, 6),
            Block.box(6, 11.5, 5, 10, 12.5, 6),
            net.minecraft.world.level.block.Block.box(5, 10, 7.5, 6, 14, 8.5)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    @Override
    public VoxelShape getShape(net.minecraft.world.level.block.state.BlockState state, BlockGetter worldIn, net.minecraft.core.BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(FACING);
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
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity te = worldIn.getBlockEntity(pos);
        if (!(te instanceof LaserPortTile)) {
            return InteractionResult.PASS;
        }
        if (worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        LaserPortTile laserPortTile = (LaserPortTile) te;
        laserPortTile.isInput = !laserPortTile.isInput;

        return InteractionResult.CONSUME;
    }
}
