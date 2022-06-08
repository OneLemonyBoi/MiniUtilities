package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.trait.BlockBehaviours;

import javax.annotation.Nullable;
import java.util.stream.Stream;

import net.minecraft.block.AbstractBlock.Properties;

public class LaserHubBlock extends BlockBase {
    public LaserHubBlock(Properties properties) {
        super(properties, BlockBehaviours.laserHub);
    }

    public static final VoxelShape shape = Stream.of(
            Block.box(0, 0, 0, 16, 7, 16),
            Block.box(6.5, 10, 6.5, 9.5, 15, 9.5),
            Block.box(6, 8, 6, 10, 9, 10),
            Block.box(5, 7, 5, 11, 8, 11),
            Block.box(5, 9, 5, 11, 10, 11),
            Block.box(5, 16, 5, 11, 16, 11),
            Block.box(3, 7, 8, 5.5, 7.5, 8.5),
            Block.box(6, 7, 3.75, 6.5, 7.5, 5.25),
            Block.box(13.5, 7, 8.5, 14, 7.5, 10),
            Block.box(2.5, 7, 3.75, 3, 7.5, 7.25),
            Block.box(3, 7, 3.75, 6, 7.5, 4.25),
            Block.box(10.5, 7, 7.25, 13, 7.5, 7.75),
            Block.box(11, 7, 9.5, 13.5, 7.5, 10),
            Block.box(11, 9, 7, 11.5, 10, 9),
            Block.box(4.5, 9, 7, 5, 10, 9),
            Block.box(13, 6.75, 7, 15, 7.75, 9),
            Block.box(1, 6.75, 7, 3, 7.75, 9),
            Block.box(11, 10, 5, 11, 16, 11),
            Block.box(5, 10, 5, 11, 16, 5),
            Block.box(5, 10, 11, 11, 16, 11),
            Block.box(5, 10, 5, 5, 16, 11)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (world.getBlockEntity(pos) instanceof LaserHubTile) {
            LaserHubTile tileEntity = (LaserHubTile) world.getBlockEntity(pos);
            if (tileEntity.getTEsInRadius(LaserHubTile.class, 16).size() > 0) world.destroyBlock(pos, true);
        }
        super.setPlacedBy(world, pos, state, placer, stack);
    }
}