package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpecialGlass extends AbstractGlassBlock {
    private boolean ethereal;
    private boolean reverse;
    private boolean dark;
    private boolean redstone;
    private boolean glowing;


    public SpecialGlass(boolean ethereal, boolean reverse, boolean glowing, boolean dark, boolean redstone) {
        super(Properties.of().strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((a, b, c, d) -> false).isRedstoneConductor((a, b, c) -> false).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false).lightLevel(state -> glowing ? 15 : 0));
        this.glowing = glowing;
        this.ethereal = ethereal;
        this.reverse = reverse;
        this.dark = dark;
        this.redstone = redstone;
    }
    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return dark ? worldIn.getMaxLightLevel() : 0;
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return redstone ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, net.minecraft.core.BlockPos pos, Direction side) {
        return redstone ? 15 : 0;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (!(context instanceof EntityCollisionContext ectx)) return Shapes.block();
        return (ectx.getEntity() instanceof Player && ethereal) || (!(ectx.getEntity() instanceof Player) && reverse) ? Shapes.empty() : Shapes.block();
    }
}
