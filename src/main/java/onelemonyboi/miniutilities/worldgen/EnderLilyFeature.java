package onelemonyboi.miniutilities.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.common.Tags;
import onelemonyboi.miniutilities.blocks.basic.EnderLily;
import onelemonyboi.miniutilities.init.BlockList;

public class EnderLilyFeature extends Feature<NoneFeatureConfiguration> {
    public EnderLilyFeature(Codec<NoneFeatureConfiguration> p_65360_) {
        super(p_65360_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159521_) {
        WorldGenLevel worldgenlevel = p_159521_.level();
        BlockPos blockpos = p_159521_.origin();
        RandomSource randomsource = p_159521_.random();
        if (worldgenlevel.isEmptyBlock(blockpos) && worldgenlevel.getBlockState(blockpos.below()).is(Tags.Blocks.END_STONES)) {
            worldgenlevel.setBlock(blockpos, BlockList.EnderLily.get().defaultBlockState().setValue(EnderLily.AGE, 7), 2);
            return true;
        } else {
            return false;
        }
    }
}