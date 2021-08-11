package onelemonyboi.miniutilities.data;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.StonecuttingRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.EnchantmentList;
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
                .build(consumer, modId("ender_dust_to_ender_pearl"));

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
                .key('X', ModTags.Items.EXPERIENCE_CONTAINERS)
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
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableAxe.get())
                .key('X', Items.OBSIDIAN)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("YY ")
                .patternLine("YX ")
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
        ShapedRecipeBuilder.shapedRecipe(BlockList.CursedEarth.get(), 8)
                .key('X', Items.DIRT)
                .key('Y', Items.WITHER_ROSE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlessedEarth.get(), 8)
                .key('X', Items.DIRT)
                .key('Y', Tags.Items.STORAGE_BLOCKS_IRON)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlursedEarth.get(), 8)
                .key('X', Items.DIRT)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // LAPIS CAELESTIS
        ShapedRecipeBuilder.shapedRecipe(BlockList.WhiteLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_WHITE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LightGrayLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_LIGHT_GRAY)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.GrayLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_GRAY)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlackLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_BLACK)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.RedLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_RED)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.OrangeLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_ORANGE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.YellowLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_YELLOW)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LimeLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_LIME)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.GreenLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_GREEN)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LightBlueLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_LIGHT_BLUE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.CyanLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_CYAN)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BlueLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_BLUE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.PurpleLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_PURPLE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.MagentaLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_MAGENTA)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.PinkLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_PINK)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.BrownLapisCaelestis.get(), 8)
                .key('X', ModTags.Items.LAPIS_CAELESTIS)
                .key('Y', Tags.Items.DYES_BROWN)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // ARMOUR
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableHelmet.get())
                .key('X', ItemList.UnstableIngot.get())
                .patternLine("XXX")
                .patternLine("X X")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableChestplate.get())
                .key('X', ItemList.UnstableIngot.get())
                .patternLine("X X")
                .patternLine("XXX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableLeggings.get())
                .key('X', ItemList.UnstableIngot.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("X X")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.UnstableBoots.get())
                .key('X', ItemList.UnstableIngot.get())
                .patternLine("X X")
                .patternLine("X X")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.InfusedHelmet.get())
                .key('X', ItemList.UnstableIngot.get())
                .key('Y', ItemList.EXPOpiniumCore.get())
                .patternLine("XXX")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.InfusedChestplate.get())
                .key('X', ItemList.UnstableIngot.get())
                .key('Y', ItemList.EXPOpiniumCore.get())
                .patternLine("XYX")
                .patternLine("XXX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.InfusedLeggings.get())
                .key('X', ItemList.UnstableIngot.get())
                .key('Y', ItemList.EXPOpiniumCore.get())
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("X X")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.InfusedBoots.get())
                .key('X', ItemList.UnstableIngot.get())
                .key('Y', ItemList.EXPOpiniumCore.get())
                .patternLine("X X")
                .patternLine("X X")
                .patternLine("Y Y")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // DRUMS
        ShapedRecipeBuilder.shapedRecipe(BlockList.StoneDrum.get())
                .key('X', Items.SMOOTH_STONE)
                .key('Y', Items.SMOOTH_STONE_SLAB)
                .key('Z', Items.BOWL)
                .patternLine("XYX")
                .patternLine("XZX")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.IronDrum.get())
                .key('X', Items.IRON_INGOT)
                .key('Y', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .key('Z', Items.CAULDRON)
                .patternLine("XYX")
                .patternLine("XZX")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.ReinforcedLargeDrum.get())
                .key('X', Tags.Items.GEMS_DIAMOND)
                .key('Y', Items.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .key('Z', BlockList.IronDrum.get())
                .patternLine("XYX")
                .patternLine("XZX")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.NetheriteReinforcedDrum.get())
                .key('X', Tags.Items.ORES_NETHERITE_SCRAP)
                .key('Y', Items.IRON_BLOCK)
                .key('Z', BlockList.ReinforcedLargeDrum.get())
                .patternLine("XYX")
                .patternLine("XZX")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.UnstableDrum.get())
                .key('X', ItemList.UnstableIngot.get())
                .key('Y', Tags.Items.INGOTS_NETHERITE)
                .key('Z', BlockList.NetheriteReinforcedDrum.get())
                .patternLine("XYX")
                .patternLine("XZX")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // Spikes
        ShapedRecipeBuilder.shapedRecipe(BlockList.WoodenSpikes.get())
                .key('X', Items.WOODEN_SWORD)
                .key('Y', Items.OAK_PLANKS)
                .key('Z', Items.OAK_LOG)
                .patternLine(" X ")
                .patternLine("XYX")
                .patternLine("YZY")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.IronSpikes.get())
                .key('X', Items.IRON_SWORD)
                .key('Y', Tags.Items.INGOTS_IRON)
                .key('Z', Tags.Items.STORAGE_BLOCKS_IRON)
                .patternLine(" X ")
                .patternLine("XYX")
                .patternLine("YZY")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.GoldSpikes.get())
                .key('X', Items.GOLDEN_SWORD)
                .key('Y', Tags.Items.INGOTS_GOLD)
                .key('Z', Tags.Items.STORAGE_BLOCKS_GOLD)
                .patternLine(" X ")
                .patternLine("XYX")
                .patternLine("YZY")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.DiamondSpikes.get())
                .key('X', Items.DIAMOND_SWORD)
                .key('Y', Tags.Items.GEMS_DIAMOND)
                .key('Z', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .patternLine(" X ")
                .patternLine("XYX")
                .patternLine("YZY")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        SmithingRecipeBuilder.smithingRecipe(Ingredient.fromItems(BlockList.DiamondSpikes.get()), Ingredient.fromItems(Items.NETHERITE_INGOT), BlockList.NetheriteSpikes.get().asItem()).addCriterion("has_cobblestone", hasItem(Items.COBBLESTONE)).build(consumer, Registry.ITEM.getKey(BlockList.NetheriteSpikes.get().asItem()).getPath() + "_smithing");

        // Angel Ring
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.BaseAngelRing.get())
                .addIngredient(Ingredient.fromTag(Tags.Items.GLASS_COLORLESS), 2)
                .addIngredient(ModTags.Items.ANGELRING)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.FeatherAngelRing.get())
                .addIngredient(Items.FEATHER, 2)
                .addIngredient(ModTags.Items.ANGELRING)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.EnderDragonAngelRing.get())
                .addIngredient(Items.LEATHER, 2)
                .addIngredient(Ingredient.fromTag(Tags.Items.DYES_BLACK), 2)
                .addIngredient(ModTags.Items.ANGELRING)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.GoldAngelRing.get())
                .addIngredient(Ingredient.fromTag(Tags.Items.INGOTS_GOLD), 2)
                .addIngredient(ModTags.Items.ANGELRING)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.BatAngelRing.get())
                .addIngredient(Items.COAL, 2)
                .addIngredient(ModTags.Items.ANGELRING)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.PeacockAngelRing.get())
                .addIngredient(Items.FEATHER, 2)
                .addIngredient(Ingredient.fromTag(Tags.Items.DYES_BLUE))
                .addIngredient(Ingredient.fromTag(Tags.Items.DYES_LIME))
                .addIngredient(ModTags.Items.ANGELRING)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // Block
        ShapedRecipeBuilder.shapedRecipe(BlockList.UnstableBlock.get())
                .key('X', ItemList.UnstableIngot.get())
                .patternLine("XXX")
                .patternLine("XXX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // Item
        ShapelessRecipeBuilder.shapelessRecipe(Items.BLAZE_POWDER, 2)
                .addIngredient(ItemList.FlameLily.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("flame_lily_to_blaze_powder"));

        // Machines
        ShapedRecipeBuilder.shapedRecipe(BlockList.MechanicalMiner.get())
                .key('X', Items.IRON_INGOT)
                .key('Y', Items.REDSTONE_BLOCK)
                .key('Z', Items.DIAMOND_PICKAXE)
                .key('A', Items.GOLD_INGOT)
                .patternLine("XZX")
                .patternLine("AYA")
                .patternLine("XAX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.MechanicalPlacer.get())
                .key('X', Items.GOLD_INGOT)
                .key('Y', Items.REDSTONE_BLOCK)
                .key('Z', Items.DISPENSER)
                .key('A', Items.DIAMOND)
                .patternLine("XZX")
                .patternLine("AYA")
                .patternLine("XAX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.QuantumQuarry.get())
                .key('X', Items.REDSTONE_BLOCK)
                .key('Y', ItemList.DiamondOpiniumCore.get())
                .key('Z', Items.NETHERITE_PICKAXE)
                .key('A', Items.NETHERITE_SHOVEL)
                .key('B', BlockList.MechanicalMiner.get())
                .key('C', BlockList.EnderPearlBlock.get())
                .key('D', Blocks.OBSERVER)
                .patternLine("YXY")
                .patternLine("ZBA")
                .patternLine("CDC")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.SpeedUpgrade.get())
                .key('X', Items.GOLD_INGOT)
                .key('Y', Items.REDSTONE)
                .patternLine("XYX")
                .patternLine("Y Y")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // Experience Pearls
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl1x.get())
                .key('X', ItemList.ExperiencePearl.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl2x.get())
                .key('X', ItemList.ExperiencePearl1x.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl3x.get())
                .key('X', ItemList.ExperiencePearl2x.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl4x.get())
                .key('X', ItemList.ExperiencePearl3x.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl5x.get())
                .key('X', ItemList.ExperiencePearl4x.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl6x.get())
                .key('X', ItemList.ExperiencePearl5x.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl7x.get())
                .key('X', ItemList.ExperiencePearl6x.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemList.ExperiencePearl8x.get())
                .key('X', ItemList.ExperiencePearl7x.get())
                .patternLine("XXX")
                .patternLine("X X")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl.get(), 8)
                .addIngredient(ItemList.ExperiencePearl1x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack1x"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl1x.get(), 8)
                .addIngredient(ItemList.ExperiencePearl2x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack2x"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl2x.get(), 8)
                .addIngredient(ItemList.ExperiencePearl3x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack3x"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl3x.get(), 8)
                .addIngredient(ItemList.ExperiencePearl4x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack4x"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl4x.get(), 8)
                .addIngredient(ItemList.ExperiencePearl5x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack5x"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl5x.get(), 8)
                .addIngredient(ItemList.ExperiencePearl6x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack6x"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl6x.get(), 8)
                .addIngredient(ItemList.ExperiencePearl7x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack7x"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemList.ExperiencePearl7x.get(), 8)
                .addIngredient(ItemList.ExperiencePearl8x.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("unpack8x"));

        ShapelessRecipeBuilder.shapelessRecipe(BlockList.ChorusTile.get(), 8)
                .addIngredient(Ingredient.fromTag(Tags.Items.GLASS_PANES), 4)
                .addIngredient(Items.CHORUS_FRUIT, 1)
                .addIngredient(Ingredient.fromTag(Tags.Items.GLASS_PANES), 4)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(BlockList.EnderTile.get(), 8)
                .addIngredient(Ingredient.fromTag(Tags.Items.GLASS_PANES), 4)
                .addIngredient(Ingredient.fromTag(Tags.Items.ENDER_PEARLS), 1)
                .addIngredient(Ingredient.fromTag(Tags.Items.GLASS_PANES), 4)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        // RF Generators
        ShapedRecipeBuilder.shapedRecipe(BlockList.SolarPanel.get(), 2)
                .key('X', Items.GOLD_INGOT)
                .key('Y', Items.IRON_INGOT)
                .key('Z', BlockList.EnderTile.get())
                .key('A', Items.GLOWSTONE)
                .patternLine("ZZZ")
                .patternLine("YAY")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LunarPanel.get(), 2)
                .key('X', Items.GOLD_INGOT)
                .key('Y', Items.IRON_INGOT)
                .key('Z', BlockList.ChorusTile.get())
                .key('A', Items.LAPIS_LAZULI)
                .patternLine("ZZZ")
                .patternLine("YAY")
                .patternLine("XYX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.SolarPanelController.get())
                .key('X', Items.IRON_INGOT)
                .key('Y', ItemList.GoldOpiniumCore.get())
                .key('Z', BlockList.EnderTile.get())
                .key('A', Blocks.REDSTONE_BLOCK)
                .patternLine("YZY")
                .patternLine("XAX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemList.MagicalEgg.get())
                .key('X', Items.EGG)
                .key('Y', ItemList.ExperiencePearl.get())
                .key('Z', ItemList.UnstableIngot.get())
                .patternLine(" Y ")
                .patternLine("ZXZ")
                .patternLine(" Y ")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlockList.LaserHub.get())
                .key('X', Items.BEACON)
                .key('Y', ItemList.EmeraldOpiniumCore.get())
                .key('Z', ItemList.UnstableIngot.get())
                .key('A', Items.NETHERITE_INGOT)
                .patternLine("ZYZ")
                .patternLine("YXY")
                .patternLine("AAA")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.LaserPort.get())
                .key('X', Items.GOLD_BLOCK)
                .key('Y', Items.REDSTONE)
                .key('Z', ItemList.IronOpiniumCore.get())
                .patternLine(" Y ")
                .patternLine("ZXZ")
                .patternLine("YYY")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BlockList.EtherealGlass.get(), 8)
                .key('X', Items.GLASS)
                .key('Y', ItemList.UnstableIngot.get())
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(BlockList.EtherealGlass.get())
                .addIngredient(BlockList.ReverseEtherealGlass.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("reverse_to_regular"));
        ShapelessRecipeBuilder.shapelessRecipe(BlockList.ReverseEtherealGlass.get())
                .addIngredient(BlockList.EtherealGlass.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("regular_to_reverse"));
        ShapedRecipeBuilder.shapedRecipe(BlockList.RedstoneGlass.get(), 8)
                .key('X', Items.GLASS)
                .key('Y', Items.REDSTONE_BLOCK)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.GlowingGlass.get(), 8)
                .key('X', Items.GLASS)
                .key('Y', Items.GLOWSTONE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.DarkGlass.get(), 8)
                .key('X', Items.GLASS)
                .key('Y', Items.BLACK_DYE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.DarkEtherealGlass.get(), 8)
                .key('X', BlockList.EtherealGlass.get())
                .key('Y', Items.BLACK_DYE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockList.DarkReverseEtherealGlass.get(), 8)
                .key('X', BlockList.ReverseEtherealGlass.get())
                .key('Y', Items.BLACK_DYE)
                .patternLine("XXX")
                .patternLine("XYX")
                .patternLine("XXX")
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(BlockList.DarkEtherealGlass.get())
                .addIngredient(BlockList.DarkReverseEtherealGlass.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("dark_reverse_to_dark_regular"));
        ShapelessRecipeBuilder.shapelessRecipe(BlockList.DarkReverseEtherealGlass.get())
                .addIngredient(BlockList.ReverseEtherealGlass.get())
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("dark_regular_to_dark_reverse"));

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockList.EnderOre.get()), ItemList.EnderDust.get(), 0.7f, 200)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("smelting/ender_ore"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(MiniUtilities.MOD_ID, path);
    }
}