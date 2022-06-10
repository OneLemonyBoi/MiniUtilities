package onelemonyboi.miniutilities.init;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.MiniUtilities;

@Mod.EventBusSubscriber(modid = MiniUtilities.MOD_ID)
public class FeatureList {
    public static Holder<PlacedFeature> ENDER_ORE;
    public static Holder<PlacedFeature> ENDER_LILY;
    public static Holder<PlacedFeature> FLAME_LILY;

    public static void register() {
        OreConfiguration enderOreConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockList.EnderOre.get().defaultBlockState(), 12);
        ENDER_ORE = registerPlacedFeature("overworld_ender_ore", new ConfiguredFeature<>(Feature.ORE, enderOreConfig),
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(32)));
        RandomPatchConfiguration enderLilyConfig = new RandomPatchConfiguration(1, 1, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockList.EnderLily.get()))));
        ENDER_LILY = registerPlacedFeature("overworld_ender_lily", new ConfiguredFeature<>(Feature.FLOWER, enderLilyConfig),
                CountPlacement.of(1),
                InSquarePlacement.spread());
        RandomPatchConfiguration flameLilyConfig = new RandomPatchConfiguration(1, 1, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockList.FlameLily.get()))));
        FLAME_LILY = registerPlacedFeature("overworld_flame_lily", new ConfiguredFeature<>(Feature.FLOWER, flameLilyConfig),
                CountPlacement.of(1),
                InSquarePlacement.spread());
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    @SubscribeEvent
    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
            // Nether Ores
        } else if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            // End Ores
        } else {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ENDER_ORE);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ENDER_LILY);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLAME_LILY);
        }
    }
}
