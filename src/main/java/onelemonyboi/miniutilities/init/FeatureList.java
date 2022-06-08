package onelemonyboi.miniutilities.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.RegistryEvent;
import onelemonyboi.miniutilities.startup.Config;

public class FeatureList {
    public static ConfiguredFeature<?, ?> ENDER_ORE;
    public static ConfiguredFeature<?, ?> ENDER_LILY;
    public static ConfiguredFeature<?, ?> FLAME_LILY;

    public static void addConfigFeatures(RegistryEvent.Register<Feature<?>> event) {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        ENDER_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.EnderOre.get().defaultBlockState(), 12))
                .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 55)))
                .squared().count(12);

        BlockClusterFeatureConfig ENDER_LILY_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockList.EnderLily.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(2).build();
        ENDER_LILY = Feature.RANDOM_PATCH.configured(ENDER_LILY_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1);

        BlockClusterFeatureConfig FLAME_LILY_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockList.FlameLily.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(1).build();
        FLAME_LILY = Feature.RANDOM_PATCH.configured(FLAME_LILY_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1);

        Registry.register(registry, "miniutilities:ender_ore", ENDER_ORE);
        Registry.register(registry, "miniutilities:ender_lily_patch_feature", ENDER_LILY);
        Registry.register(registry, "miniutilities:flame_lily_patch_feature", FLAME_LILY);
    }
}
