package onelemonyboi.miniutilities.blocks;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.util.thread.EffectiveSide;

import javax.annotation.Nonnull;
import java.util.Random;

public class LapisLamp extends Block {

    public LapisLamp() {
        super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GLASS).strength(0.3F));
    }

    @Override
    public void animateTick(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos blockPos, @Nonnull RandomSource random) {
        if (world.isClientSide && world.getMaxLocalRawBrightness(blockPos) == 0) {
            world.getLightEngine().checkBlock(blockPos);
        }
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        if (EffectiveSide.get().isClient()) {
            return 15;
        } else {
            return 0;
        }
    }
}
