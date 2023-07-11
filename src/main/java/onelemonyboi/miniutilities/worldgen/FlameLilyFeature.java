package onelemonyboi.miniutilities.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import onelemonyboi.miniutilities.blocks.basic.EnderLily;
import onelemonyboi.miniutilities.init.BlockList;

public class FlameLilyFeature extends Feature<NoneFeatureConfiguration> {
    public FlameLilyFeature(Codec<NoneFeatureConfiguration> p_65360_) {
        super(p_65360_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159521_) {
        WorldGenLevel worldgenlevel = p_159521_.level();
        BlockPos blockpos = p_159521_.origin();
        RandomSource randomsource = p_159521_.random();
        if (worldgenlevel.isEmptyBlock(blockpos) && worldgenlevel.getBlockState(blockpos.below()).is(BlockTags.SAND)) {
            worldgenlevel.setBlock(blockpos, BlockList.FlameLily.get().defaultBlockState().setValue(EnderLily.AGE, 7), 2);
            return true;
        } else {
            return false;
        }
    }
}