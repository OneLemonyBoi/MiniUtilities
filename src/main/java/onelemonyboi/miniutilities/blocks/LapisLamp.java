package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import javax.annotation.Nonnull;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class LapisLamp extends Block {

    public LapisLamp() {
        super(Properties.of(Material.DIRT).sound(SoundType.GLASS).strength(0.3F));
    }

    @Override
    public void animateTick(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull Random random) {
        if (world.isClientSide && world.getMaxLocalRawBrightness(blockPos) == 0) {
            world.getLightEngine().checkBlock(blockPos);
        }
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (EffectiveSide.get().isClient()) {
            return 15;
        } else {
            return 0;
        }
    }
}
