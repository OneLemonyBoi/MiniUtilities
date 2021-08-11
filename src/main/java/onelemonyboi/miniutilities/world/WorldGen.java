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
import onelemonyboi.miniutilities.startup.Config;

import onelemonyboi.miniutilities.init.FeatureList;

public class WorldGen {
    public static void generate(final BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND)) && Config.enableEnderOre.get()) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureList.ENDER_ORE);
        }

        if (event.getCategory().equals(Biome.Category.THEEND) && Config.enableEnderLily.get()) {
            event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureList.ENDER_LILY);
        }

        if (event.getClimate().temperature >= 2 && Config.enableFlameLily.get()) {
            event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureList.FLAME_LILY);
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}