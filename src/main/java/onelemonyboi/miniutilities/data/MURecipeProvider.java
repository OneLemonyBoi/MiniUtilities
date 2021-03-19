package onelemonyboi.miniutilities.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.StonecuttingRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class MURecipeProvider extends RecipeProvider {
    public MURecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(Items.ENDER_PEARL)
                .addIngredient(ModTags.Items.DUSTS_ENDER)
                .addIngredient(ModTags.Items.DUSTS_ENDER)
                .addIngredient(ModTags.Items.DUSTS_ENDER)
                .addIngredient(ModTags.Items.DUSTS_ENDER)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlockList.EnderPearlBlock.get())
                .key('#', Items.ENDER_PEARL)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.AngelBlockItem.get())
                .key('O', Tags.Items.OBSIDIAN)
                .key('F', Items.FEATHER)
                .key('G', Items.GOLD_INGOT)
                .patternLine(" G ")
                .patternLine("FOF")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableIngot.get())
                .key('I', Tags.Items.INGOTS_IRON)
                .key('S', Items.STICK)
                .key('D', Tags.Items.GEMS_DIAMOND)
                .patternLine(" I ")
                .patternLine(" S ")
                .patternLine(" D ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.EnderLilySeeds.get())
                .key('S', Tags.Items.SEEDS)
                .key('E', Tags.Items.ENDER_PEARLS)
                .patternLine("EEE")
                .patternLine("ESE")
                .patternLine("EEE")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // OPINIUM CORES

        ShapedRecipeBuilder.shapedRecipe(ItemList.IronOpiniumCore.get())
                .key('X', Tags.Items.INGOTS_IRON)
                .key('E', Items.COAL)
                .patternLine("XEX")
                .patternLine("E E")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.GoldOpiniumCore.get())
                .key('X', Tags.Items.INGOTS_GOLD)
                .key('E', ItemList.IronOpiniumCore.get())
                .patternLine("XEX")
                .patternLine("E E")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.DiamondOpiniumCore.get())
                .key('X', Tags.Items.GEMS_DIAMOND)
                .key('E', ItemList.GoldOpiniumCore.get())
                .patternLine("XEX")
                .patternLine("E E")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.NetheriteOpiniumCore.get())
                .key('X', Tags.Items.INGOTS_NETHERITE)
                .key('E', ItemList.DiamondOpiniumCore.get())
                .patternLine("XEX")
                .patternLine("E E")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.EmeraldOpiniumCore.get())
                .key('X', Tags.Items.GEMS_EMERALD)
                .key('E', ItemList.NetheriteOpiniumCore.get())
                .patternLine("XEX")
                .patternLine("E E")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.ChorusOpiniumCore.get())
                .key('X', Items.CHORUS_FLOWER)
                .key('E', ItemList.EmeraldOpiniumCore.get())
                .patternLine("XEX")
                .patternLine("E E")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.EXPOpiniumCore.get())
                .key('X', Items.EXPERIENCE_BOTTLE)
                .key('E', ItemList.ChorusOpiniumCore.get())
                .patternLine("XEX")
                .patternLine("E E")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.NetherStarOpiniumCore.get())
                .key('X', Items.NETHER_STAR)
                .key('E', ItemList.EXPOpiniumCore.get())
                .key('Z', ItemList.UnstableIngot.get())
                .patternLine("XEX")
                .patternLine("EZE")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.TheFinalOpiniumCore.get())
                .key('X', Tags.Items.STORAGE_BLOCKS_IRON)
                .key('E', ItemList.NetherStarOpiniumCore.get())
                .key('Z', ItemList.UnstableIngot.get())
                .patternLine("XEX")
                .patternLine("EZE")
                .patternLine("XEX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // KIKOKU
        ShapedRecipeBuilder.shapedRecipe(ItemList.Kikoku.get())
                .key('X', Items.STICK)
                .key('Y', ItemList.TheFinalOpiniumCore.get())
                .patternLine(" Y ")
                .patternLine(" Y ")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // UNSTABLE TOOLS
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableSword.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine(" Y ")
                .patternLine(" Y ")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstablePickaxe.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("YYY")
                .patternLine(" X ")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableHoe.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("YY ")
                .patternLine(" X ")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableHoe.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine(" YY")
                .patternLine(" X ")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableAxe.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("YY ")
                .patternLine("YX ")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableAxe.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine(" YY")
                .patternLine(" XY")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableShovel.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine(" Y ")
                .patternLine(" X ")
                .patternLine(" X ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableShears.get())
                .key('X', ItemList.AngelBlockItem.get())
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("XY ")
                .patternLine("YX ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);


        // EARTHS
        ShapedRecipeBuilder.shapedRecipe(BlockList.CursedEarth.get())
                .key('X', Items.DIRT)
                .key('Y', Items.WITHER_ROSE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlessedEarth.get())
                .key('X', Items.DIRT)
                .key('Y', Tags.Items.STORAGE_BLOCKS_IRON)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlursedEarth.get())
                .key('X', Items.DIRT)
                .key('Y', Tags.Items.NETHER_STARS)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // LAPIS CAELESTIS
        ShapedRecipeBuilder.shapedRecipe(BlockList.GrayLapisCaelestis.get())
                .key('X', Tags.Items.STONE)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlockList.WhiteLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_WHITE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LightGrayLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_LIGHT_GRAY)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.GrayLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_GRAY)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlackLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_BLACK)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.RedLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_RED)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.OrangeLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_ORANGE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.YellowLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_YELLOW)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LimeLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_LIME)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.GreenLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_GREEN)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LightBlueLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_LIGHT_BLUE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.CyanLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_CYAN)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlueLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_BLUE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.PurpleLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_PURPLE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.MagentaLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_MAGENTA)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.PinkLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_PINK)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BrownLapisCaelestis.get())
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_BROWN)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);



        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockList.EnderOre.get()), ItemList.EnderDust.get(), 0.7f, 200)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("smelting/ender_ore"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(MiniUtilities.MOD_ID, path);
    }
}