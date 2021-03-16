package onelemonyboi.miniutilities.data;

import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;

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


        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockList.EnderOre.get()), ItemList.EnderDust.get(), 0.7f, 200)
                .addCriterion("has_item", hasItem(Items.COBBLESTONE))
                .build(consumer, modId("smelting/ender_ore"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(MiniUtilities.MOD_ID, path);
    }
}