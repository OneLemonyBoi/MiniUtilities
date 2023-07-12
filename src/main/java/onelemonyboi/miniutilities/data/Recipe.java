package onelemonyboi.miniutilities.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.function.Consumer;

public class Recipe extends RecipeProvider {
    public Recipe(DataGenerator generatorIn) {
        super(generatorIn.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_PEARL)
                .requires(ModTags.Items.DUSTS_ENDER)
                .requires(ModTags.Items.DUSTS_ENDER)
                .requires(ModTags.Items.DUSTS_ENDER)
                .requires(ModTags.Items.DUSTS_ENDER)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("ender_dust_to_ender_pearl"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.EnderPearlBlock.get())
                .define('#', Items.ENDER_PEARL)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.AngelBlockItem.get())
                .define('O', Tags.Items.OBSIDIAN)
                .define('F', Items.FEATHER)
                .define('G', Items.GOLD_INGOT)
                .pattern(" G ")
                .pattern("FOF")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableIngot.get())
                .define('I', Tags.Items.INGOTS_IRON)
                .define('S', Items.STICK)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" D ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.EnderLilySeeds.get())
                .define('S', Tags.Items.SEEDS)
                .define('E', Tags.Items.ENDER_PEARLS)
                .pattern("EEE")
                .pattern("ESE")
                .pattern("EEE")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // OPINIUM CORES

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.IronOpiniumCore.get(), 2)
                .define('X', Tags.Items.INGOTS_IRON)
                .define('E', Items.COAL)
                .pattern("XEX")
                .pattern("E E")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.GoldOpiniumCore.get(), 2)
                .define('X', Tags.Items.INGOTS_GOLD)
                .define('E', ItemList.IronOpiniumCore.get())
                .pattern("XEX")
                .pattern("E E")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.DiamondOpiniumCore.get(), 2)
                .define('X', Tags.Items.GEMS_DIAMOND)
                .define('E', ItemList.GoldOpiniumCore.get())
                .pattern("XEX")
                .pattern("E E")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.NetheriteOpiniumCore.get(), 2)
                .define('X', Tags.Items.INGOTS_NETHERITE)
                .define('E', ItemList.DiamondOpiniumCore.get())
                .pattern("XEX")
                .pattern("E E")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.EmeraldOpiniumCore.get(), 2)
                .define('X', Tags.Items.GEMS_EMERALD)
                .define('E', ItemList.NetheriteOpiniumCore.get())
                .pattern("XEX")
                .pattern("E E")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ChorusOpiniumCore.get(), 2)
                .define('X', Items.CHORUS_FLOWER)
                .define('E', ItemList.EmeraldOpiniumCore.get())
                .pattern("XEX")
                .pattern("E E")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.EXPOpiniumCore.get(), 2)
                .define('X', ModTags.Items.EXPERIENCE_CONTAINERS)
                .define('E', ItemList.ChorusOpiniumCore.get())
                .pattern("XEX")
                .pattern("E E")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.NetherStarOpiniumCore.get(), 2)
                .define('X', Items.NETHER_STAR)
                .define('E', ItemList.EXPOpiniumCore.get())
                .define('Z', ItemList.UnstableIngot.get())
                .pattern("XEX")
                .pattern("EZE")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.TheFinalOpiniumCore.get(), 2)
                .define('X', Tags.Items.STORAGE_BLOCKS_IRON)
                .define('E', ItemList.NetherStarOpiniumCore.get())
                .define('Z', ItemList.UnstableIngot.get())
                .pattern("XEX")
                .pattern("EZE")
                .pattern("XEX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // KIKOKU
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.Kikoku.get())
                .define('X', Items.STICK)
                .define('Y', ItemList.TheFinalOpiniumCore.get())
                .pattern(" Y ")
                .pattern(" Y ")
                .pattern(" X ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // UNSTABLE TOOLS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableSword.get())
                .define('X', Items.OBSIDIAN)
                .define('Y', ItemList.UnstableIngot.get())
                .pattern(" Y ")
                .pattern(" Y ")
                .pattern(" X ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstablePickaxe.get())
                .define('X', Items.OBSIDIAN)
                .define('Y', ItemList.UnstableIngot.get())
                .pattern("YYY")
                .pattern(" X ")
                .pattern(" X ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableHoe.get())
                .define('X', Items.OBSIDIAN)
                .define('Y', ItemList.UnstableIngot.get())
                .pattern("YY ")
                .pattern(" X ")
                .pattern(" X ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableAxe.get())
                .define('X', Items.OBSIDIAN)
                .define('Y', ItemList.UnstableIngot.get())
                .pattern("YY ")
                .pattern("YX ")
                .pattern(" X ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableShovel.get())
                .define('X', Items.OBSIDIAN)
                .define('Y', ItemList.UnstableIngot.get())
                .pattern(" Y ")
                .pattern(" X ")
                .pattern(" X ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableShears.get())
                .define('X', ItemList.AngelBlockItem.get())
                .define('Y', ItemList.UnstableIngot.get())
                .pattern("XY ")
                .pattern("YX ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);


        // EARTHS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.CursedEarth.get(), 8)
                .define('X', Items.DIRT)
                .define('Y', Items.WITHER_ROSE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.BlessedEarth.get(), 8)
                .define('X', Items.DIRT)
                .define('Y', Tags.Items.STORAGE_BLOCKS_IRON)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.BlursedEarth.get(), 8)
                .define('X', Items.DIRT)
                .define('Y', ItemList.UnstableIngot.get())
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // LAPIS CAELESTIS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.WhiteLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_WHITE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.LightGrayLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_LIGHT_GRAY)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.GrayLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_GRAY)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.BlackLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_BLACK)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.RedLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_RED)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.OrangeLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_ORANGE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.YellowLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_YELLOW)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.LimeLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_LIME)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.GreenLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_GREEN)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.LightBlueLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_LIGHT_BLUE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.CyanLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_CYAN)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.BlueLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_BLUE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.PurpleLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_PURPLE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.MagentaLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_MAGENTA)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.PinkLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_PINK)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.BrownLapisCaelestis.get(), 8)
                .define('X', ModTags.Items.LAPIS_CAELESTIS)
                .define('Y', Tags.Items.DYES_BROWN)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // ARMOUR
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableHelmet.get())
                .define('X', ItemList.UnstableIngot.get())
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableChestplate.get())
                .define('X', ItemList.UnstableIngot.get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableLeggings.get())
                .define('X', ItemList.UnstableIngot.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.UnstableBoots.get())
                .define('X', ItemList.UnstableIngot.get())
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.InfusedHelmet.get())
                .define('X', ItemList.UnstableIngot.get())
                .define('Y', ItemList.EXPOpiniumCore.get())
                .pattern("XXX")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.InfusedChestplate.get())
                .define('X', ItemList.UnstableIngot.get())
                .define('Y', ItemList.EXPOpiniumCore.get())
                .pattern("XYX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.InfusedLeggings.get())
                .define('X', ItemList.UnstableIngot.get())
                .define('Y', ItemList.EXPOpiniumCore.get())
                .pattern("XXX")
                .pattern("XYX")
                .pattern("X X")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.InfusedBoots.get())
                .define('X', ItemList.UnstableIngot.get())
                .define('Y', ItemList.EXPOpiniumCore.get())
                .pattern("X X")
                .pattern("X X")
                .pattern("Y Y")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // DRUMS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.StoneDrum.get())
                .define('X', Items.SMOOTH_STONE)
                .define('Y', Items.SMOOTH_STONE_SLAB)
                .define('Z', Items.BOWL)
                .pattern("XYX")
                .pattern("XZX")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.IronDrum.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .define('Z', Items.CAULDRON)
                .pattern("XYX")
                .pattern("XZX")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.ReinforcedLargeDrum.get())
                .define('X', Tags.Items.GEMS_DIAMOND)
                .define('Y', Items.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .define('Z', BlockList.IronDrum.get())
                .pattern("XYX")
                .pattern("XZX")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.NetheriteReinforcedDrum.get())
                .define('X', Tags.Items.ORES_NETHERITE_SCRAP)
                .define('Y', Items.IRON_BLOCK)
                .define('Z', BlockList.ReinforcedLargeDrum.get())
                .pattern("XYX")
                .pattern("XZX")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.UnstableDrum.get())
                .define('X', ItemList.UnstableIngot.get())
                .define('Y', Tags.Items.INGOTS_NETHERITE)
                .define('Z', BlockList.NetheriteReinforcedDrum.get())
                .pattern("XYX")
                .pattern("XZX")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // Spikes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.WoodenSpikes.get())
                .define('X', Items.WOODEN_SWORD)
                .define('Y', Items.OAK_PLANKS)
                .define('Z', Items.OAK_LOG)
                .pattern(" X ")
                .pattern("XYX")
                .pattern("YZY")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.IronSpikes.get())
                .define('X', Items.IRON_SWORD)
                .define('Y', Tags.Items.INGOTS_IRON)
                .define('Z', Tags.Items.STORAGE_BLOCKS_IRON)
                .pattern(" X ")
                .pattern("XYX")
                .pattern("YZY")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.GoldSpikes.get())
                .define('X', Items.GOLDEN_SWORD)
                .define('Y', Tags.Items.INGOTS_GOLD)
                .define('Z', Tags.Items.STORAGE_BLOCKS_GOLD)
                .pattern(" X ")
                .pattern("XYX")
                .pattern("YZY")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.DiamondSpikes.get())
                .define('X', Items.DIAMOND_SWORD)
                .define('Y', Tags.Items.GEMS_DIAMOND)
                .define('Z', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .pattern(" X ")
                .pattern("XYX")
                .pattern("YZY")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(BlockList.DiamondSpikes.get()), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.COMBAT, BlockList.NetheriteSpikes.get().asItem()).unlocks("has_cobblestone", has(Items.COBBLESTONE)).save(consumer, ForgeRegistries.ITEMS.getKey(BlockList.NetheriteSpikes.get().asItem()).getPath() + "_smithing");

        // Angel Ring
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.BaseAngelRing.get())
                .requires(Ingredient.of(Tags.Items.GLASS_COLORLESS), 2)
                .requires(ModTags.Items.ANGELRING)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.FeatherAngelRing.get())
                .requires(Items.FEATHER, 2)
                .requires(ModTags.Items.ANGELRING)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.EnderDragonAngelRing.get())
                .requires(Items.LEATHER, 2)
                .requires(Ingredient.of(Tags.Items.DYES_BLACK), 2)
                .requires(ModTags.Items.ANGELRING)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.GoldAngelRing.get())
                .requires(Ingredient.of(Tags.Items.INGOTS_GOLD), 2)
                .requires(ModTags.Items.ANGELRING)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.BatAngelRing.get())
                .requires(Items.COAL, 2)
                .requires(ModTags.Items.ANGELRING)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.PeacockAngelRing.get())
                .requires(Items.FEATHER, 2)
                .requires(Ingredient.of(Tags.Items.DYES_BLUE))
                .requires(Ingredient.of(Tags.Items.DYES_LIME))
                .requires(ModTags.Items.ANGELRING)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.UnstableBlock.get())
                .define('X', ItemList.UnstableIngot.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // Item
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLAZE_POWDER, 2)
                .requires(ItemList.FlameLily.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("flame_lily_to_blaze_powder"));

        // Machines
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.MechanicalMiner.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', Items.DIAMOND_PICKAXE)
                .define('A', Items.GOLD_INGOT)
                .pattern("XZX")
                .pattern("AYA")
                .pattern("XAX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.MechanicalPlacer.get())
                .define('X', Items.GOLD_INGOT)
                .define('Y', Items.REDSTONE_BLOCK)
                .define('Z', Items.DISPENSER)
                .define('A', Items.DIAMOND)
                .pattern("XZX")
                .pattern("AYA")
                .pattern("XAX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.QuantumQuarry.get())
                .define('X', Items.REDSTONE_BLOCK)
                .define('Y', ItemList.DiamondOpiniumCore.get())
                .define('Z', Items.NETHERITE_PICKAXE)
                .define('A', Items.NETHERITE_SHOVEL)
                .define('B', BlockList.MechanicalMiner.get())
                .define('C', BlockList.EnderPearlBlock.get())
                .define('D', Blocks.OBSERVER)
                .pattern("YXY")
                .pattern("ZBA")
                .pattern("CDC")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.SpeedUpgrade.get())
                .define('X', Items.GOLD_INGOT)
                .define('Y', Items.REDSTONE)
                .pattern("XYX")
                .pattern("Y Y")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // Experience Pearls
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl1x.get())
                .define('X', ItemList.ExperiencePearl.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl2x.get())
                .define('X', ItemList.ExperiencePearl1x.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl3x.get())
                .define('X', ItemList.ExperiencePearl2x.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl4x.get())
                .define('X', ItemList.ExperiencePearl3x.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl5x.get())
                .define('X', ItemList.ExperiencePearl4x.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl6x.get())
                .define('X', ItemList.ExperiencePearl5x.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl7x.get())
                .define('X', ItemList.ExperiencePearl6x.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.ExperiencePearl8x.get())
                .define('X', ItemList.ExperiencePearl7x.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl.get(), 8)
                .requires(ItemList.ExperiencePearl1x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack1x"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl1x.get(), 8)
                .requires(ItemList.ExperiencePearl2x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack2x"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl2x.get(), 8)
                .requires(ItemList.ExperiencePearl3x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack3x"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl3x.get(), 8)
                .requires(ItemList.ExperiencePearl4x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack4x"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl4x.get(), 8)
                .requires(ItemList.ExperiencePearl5x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack5x"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl5x.get(), 8)
                .requires(ItemList.ExperiencePearl6x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack6x"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl6x.get(), 8)
                .requires(ItemList.ExperiencePearl7x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack7x"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemList.ExperiencePearl7x.get(), 8)
                .requires(ItemList.ExperiencePearl8x.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("unpack8x"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockList.ChorusTile.get(), 8)
                .requires(Ingredient.of(Tags.Items.GLASS_PANES), 4)
                .requires(Items.CHORUS_FRUIT, 1)
                .requires(Ingredient.of(Tags.Items.GLASS_PANES), 4)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockList.EnderTile.get(), 8)
                .requires(Ingredient.of(Tags.Items.GLASS_PANES), 4)
                .requires(Ingredient.of(Tags.Items.ENDER_PEARLS), 1)
                .requires(Ingredient.of(Tags.Items.GLASS_PANES), 4)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        // RF Generators
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.SolarPanel.get(), 2)
                .define('X', Items.GOLD_INGOT)
                .define('Y', Items.IRON_INGOT)
                .define('Z', BlockList.EnderTile.get())
                .define('A', Items.GLOWSTONE)
                .pattern("ZZZ")
                .pattern("YAY")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.LunarPanel.get(), 2)
                .define('X', Items.GOLD_INGOT)
                .define('Y', Items.IRON_INGOT)
                .define('Z', BlockList.ChorusTile.get())
                .define('A', Items.LAPIS_LAZULI)
                .pattern("ZZZ")
                .pattern("YAY")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.SolarPanelController.get())
                .define('X', Items.IRON_INGOT)
                .define('Y', ItemList.GoldOpiniumCore.get())
                .define('Z', BlockList.EnderTile.get())
                .define('A', net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK)
                .pattern("YZY")
                .pattern("XAX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.MagicalEgg.get())
                .define('X', Items.EGG)
                .define('Y', ItemList.ExperiencePearl.get())
                .define('Z', ItemList.UnstableIngot.get())
                .pattern(" Y ")
                .pattern("ZXZ")
                .pattern(" Y ")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.LaserHub.get())
                .define('X', Items.BEACON)
                .define('Y', ItemList.EmeraldOpiniumCore.get())
                .define('Z', ItemList.UnstableIngot.get())
                .define('A', Items.NETHERITE_INGOT)
                .pattern("ZYZ")
                .pattern("YXY")
                .pattern("AAA")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.LaserPort.get())
                .define('X', Items.GOLD_BLOCK)
                .define('Y', Items.REDSTONE)
                .define('Z', ItemList.IronOpiniumCore.get())
                .pattern(" Y ")
                .pattern("ZXZ")
                .pattern("YYY")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.EtherealGlass.get(), 8)
                .define('X', Items.GLASS)
                .define('Y', ItemList.UnstableIngot.get())
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockList.EtherealGlass.get())
                .requires(BlockList.ReverseEtherealGlass.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("reverse_to_regular"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockList.ReverseEtherealGlass.get())
                .requires(BlockList.EtherealGlass.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("regular_to_reverse"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.RedstoneGlass.get(), 8)
                .define('X', Items.GLASS)
                .define('Y', Items.REDSTONE_BLOCK)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.GlowingGlass.get(), 8)
                .define('X', Items.GLASS)
                .define('Y', Items.GLOWSTONE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.DarkGlass.get(), 8)
                .define('X', Items.BLACK_STAINED_GLASS)
                .define('Y', Items.BLACK_DYE)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXY")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.DarkEtherealGlass.get(), 8)
                .define('X', BlockList.EtherealGlass.get())
                .define('Y', Items.BLACK_DYE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.DarkReverseEtherealGlass.get(), 8)
                .define('X', BlockList.ReverseEtherealGlass.get())
                .define('Y', Items.BLACK_DYE)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockList.DarkEtherealGlass.get())
                .requires(BlockList.DarkReverseEtherealGlass.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("dark_reverse_to_dark_regular"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockList.DarkReverseEtherealGlass.get())
                .requires(BlockList.ReverseEtherealGlass.get())
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("dark_regular_to_dark_reverse"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.LapisLamp.get())
            .define('X', Items.LAPIS_LAZULI)
            .define('Y', Items.REDSTONE_LAMP)
            .pattern(" X ")
            .pattern("XYX")
            .pattern(" X ")
            .unlockedBy("has_item", has(Items.COBBLESTONE))
            .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemList.GoldenLasso.get())
                .define('X', Items.GOLD_INGOT)
                .define('Y', Items.STRING)
                .pattern("XYX")
                .pattern("Y Y")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockList.RedstoneClockBlock.get())
                .define('X', Items.STONE)
                .define('Y', Items.REDSTONE)
                .pattern("XYX")
                .pattern("Y Y")
                .pattern("XYX")
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockList.EnderOre.get()), RecipeCategory.MISC, ItemList.EnderDust.get(), 0.7f, 200)
                .unlockedBy("has_item", has(Items.COBBLESTONE))
                .save(consumer, modId("smelting/ender_ore"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(MiniUtilities.MOD_ID, path);
    }
}
