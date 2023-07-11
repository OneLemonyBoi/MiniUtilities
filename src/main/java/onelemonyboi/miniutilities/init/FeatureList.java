package onelemonyboi.miniutilities.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.worldgen.EnderLilyFeature;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.worldgen.FlameLilyFeature;

@Mod.EventBusSubscriber(modid = MiniUtilities.MOD_ID)
public class FeatureList {
//    private static final Supplier<RandomPatchConfiguration> flameLilyConfig = () -> new RandomPatchConfiguration(2, 1, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockList.FlameLily.get()))));
//    private static final Supplier<RandomPatchConfiguration> enderLilyConfig = () -> new RandomPatchConfiguration(6, 1, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockList.EnderLily.get()))));
//    private static final Supplier<OreConfiguration> enderOreConfig = () -> new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockList.EnderOre.get().defaultBlockState(), 128);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> ENDER_ORE_CONFIGURED_FEATURE = ModRegistry.CONFIGURED_FEATURES.register("overworld_ender_ore", () ->
//            new ConfiguredFeature<>(Feature.ORE, enderOreConfig.get())
//    );
//    public static final RegistryObject<ConfiguredFeature<?, ?>> ENDER_LILY_CONFIGURED_FEATURE = ModRegistry.CONFIGURED_FEATURES.register("overworld_ender_lily", () ->
//            new ConfiguredFeature<>(Feature.FLOWER, enderLilyConfig.get())
//    );
//    public static final RegistryObject<ConfiguredFeature<?, ?>> FLAME_LILY_CONFIGURED_FEATURE = ModRegistry.CONFIGURED_FEATURES.register("overworld_flame_lily", () ->
//            new ConfiguredFeature<>(Feature.FLOWER, flameLilyConfig.get())
//    );
//    public static final RegistryObject<PlacedFeature> ENDER_ORE_PLACED_FEATURE = ModRegistry.PLACED_FEATURES.register("overworld_ender_ore", () ->
//            new PlacedFeature(ENDER_ORE_CONFIGURED_FEATURE.getHolder().get(), List.of(CountPlacement.of(128), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(32)), BiomeFilter.biome()))
//    );
//    public static final RegistryObject<PlacedFeature> ENDER_LILY_PLACED_FEATURE = ModRegistry.PLACED_FEATURES.register("overworld_ender_lily", () ->
//            new PlacedFeature(ENDER_LILY_CONFIGURED_FEATURE.getHolder().get(), List.of(CountPlacement.of(6), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(256)), BiomeFilter.biome()))
//    );
//    public static final RegistryObject<PlacedFeature> FLAME_LILY_PLACED_FEATURE = ModRegistry.PLACED_FEATURES.register("overworld_flame_lily", () ->
//            new PlacedFeature(FLAME_LILY_CONFIGURED_FEATURE.getHolder().get(), List.of(CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(256)), BiomeFilter.biome()))
//    );

    public static final RegistryObject<Feature<?>> ENDER_LILY_FEATURE = ModRegistry.FEATURES.register("ender_lily", () ->
            new EnderLilyFeature(NoneFeatureConfiguration.CODEC)
    );

    public static final RegistryObject<Feature<?>> FLAME_LILY_FEATURE = ModRegistry.FEATURES.register("flame_lily", () ->
            new FlameLilyFeature(NoneFeatureConfiguration.CODEC)
    );

    public static void register() {

    }
}
