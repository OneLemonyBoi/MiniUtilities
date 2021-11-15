package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import javax.annotation.Nonnull;
import java.util.Random;

public class LapisLamp extends Block {

    public LapisLamp() {
        super(Properties.create(Material.EARTH).sound(SoundType.GLASS).hardnessAndResistance(0.3F));
    }

    @Override
    public void animateTick(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull Random random) {
        if (world.isRemote && world.getLight(blockPos) == 0) {
            world.getLightManager().checkBlock(blockPos);
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
