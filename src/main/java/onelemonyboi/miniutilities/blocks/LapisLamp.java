package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.common.thread.EffectiveSide;

public class LapisLamp extends Block {

    public LapisLamp() {
        super(Properties.create(Material.EARTH).sound(SoundType.GLASS).hardnessAndResistance(0.3F));
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
