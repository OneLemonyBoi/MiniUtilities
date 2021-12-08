package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.EntitySelectionContext;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.MiniUtilities;

public class SpecialGlass extends AbstractGlassBlock {
    private boolean ethereal;
    private boolean reverse;
    private boolean dark;
    private boolean redstone;
    private boolean glowing;


    public SpecialGlass(boolean ethereal, boolean reverse, boolean glowing, boolean dark, boolean redstone) {
        super(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn((a, b, c, d) -> false).setOpaque((a, b, c) -> false).setSuffocates((a, b, c) -> false).setBlocksVision((a, b, c) -> false).setLightLevel(state -> glowing ? 15 : 0));
        this.glowing = glowing;
        this.ethereal = ethereal;
        this.reverse = reverse;
        this.dark = dark;
        this.redstone = redstone;
    }
    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return dark ? worldIn.getMaxLightLevel() : 0;
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return redstone ? 15 : 0;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return redstone ? 15 : 0;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return (context.getEntity() instanceof PlayerEntity && ethereal) || (!(context.getEntity() instanceof PlayerEntity) && reverse) ? VoxelShapes.empty() : state.getShape(worldIn, pos);
    }
}
