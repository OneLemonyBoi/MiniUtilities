package onelemonyboi.miniutilities.world;

import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import onelemonyboi.miniutilities.blocks.EnderLily;
import onelemonyboi.miniutilities.blocks.FlameLily;
import onelemonyboi.miniutilities.init.BlockList;

public class WorldGen {
    public static void generateOres(final BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND)) && Config.enableEnderOre.get()) {
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                    BlockList.EnderOre.get().getDefaultState(), Config.enderOreVeinSize.get(), Config.enderOreMinHeight.get(), Config.enderOreMaxHeight.get(), Config.enderOreAmount.get());
        }
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
                                    int veinSize, int minHeight, int maxHeight, int amount) {
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .square().count(amount));
    }

    public static void generatePlants(final BiomeLoadingEvent event) {
        if (event.getCategory().equals(Biome.Category.THEEND) && Config.enableEnderLily.get()) {
            generatePlant(event.getGeneration(), "ender_lily_patch_feature", BlockList.EnderLily.get().getDefaultState().with(EnderLily.AGE, 7), 2, 1);
        }

        if (event.getClimate().temperature >= 2 && Config.enableFlameLily.get()) {
            generatePlant(event.getGeneration(), "flame_lily_patch_feature", BlockList.FlameLily.get().getDefaultState().with(FlameLily.AGE, 7), 1, 1);
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    private static void generatePlant(BiomeGenerationSettingsBuilder settings, String key, BlockState state, int tries, int count) {
        BlockClusterFeatureConfig PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(state), SimpleBlockPlacer.PLACER)).tries(tries).build();
        ConfiguredFeature<?, ?> PATCH_FEATURE = register(key, Feature.RANDOM_PATCH.withConfiguration(PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).count(count));
        settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, PATCH_FEATURE);
    }
}