//package onelemonyboi.miniutilities.recipes;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.crafting.IRecipe;
//import net.minecraft.item.crafting.IRecipeSerializer;
//import net.minecraft.item.crafting.IRecipeType;
//import net.minecraft.item.crafting.Ingredient;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.util.JSONUtils;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.world.World;
//import net.minecraftforge.registries.ForgeRegistryEntry;
//import onelemonyboi.lemonlib.handlers.MUItemStackHandler;
//import onelemonyboi.miniutilities.init.BlockList;
//import onelemonyboi.miniutilities.init.RecipeList;
//
//import java.util.Collection;
//
//public class TestRecipe implements IRecipe<MUItemStackHandler> {
//    public static Collection<TestRecipe> getAllRecipes(World world) {
//        return world.getRecipeManager().getRecipesForType(RecipeList.QuantumQuarryType.get());
//    }
//
//    private final ResourceLocation id;
//    private final ItemStack item;
//    private final long weight;
//
//    public TestRecipe(ResourceLocation id, ItemStack item, long weight)
//    {
//        this.id = id;
//        this.item = item;
//        this.weight = weight;
//    }
//
//    @Override
//    public boolean canFit(int width, int height)
//    {
//        return true;
//    }
//
//    @Override
//    public NonNullList<Ingredient> getIngredients() {
//        return NonNullList.from(Ingredient.EMPTY);
//    }
//
//    @Override
//    public boolean matches(MUItemStackHandler inv, World worldIn) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getCraftingResult(MUItemStackHandler inv) {
//        return item;
//    }
//
//    @Override
//    public ItemStack getRecipeOutput()
//    {
//        return this.item;
//    }
//
//    @Override
//    public ResourceLocation getId()
//    {
//        return id;
//    }
//
//    @Override
//    public IRecipeSerializer<?> getSerializer()
//    {
//        return RecipeList.QuantumQuarry.get();
//    }
//
//    @Override
//    public IRecipeType<?> getType()
//    {
//        return RecipeList.QuantumQuarryType.get();
//    }
//
//    @Override
//    public ItemStack getIcon()
//    {
//        return new ItemStack(BlockList.QuantumQuarry.get());
//    }
//
//    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
//            implements IRecipeSerializer<TestRecipe>
//    {
//        @Override
//        public TestRecipe read(ResourceLocation recipeId, JsonObject json)
//        {
//            JsonElement jsonelement = JSONUtils.isJsonArray(json, "ore")
//                    ? JSONUtils.getJsonArray(json, "ore")
//                    : JSONUtils.getJsonObject(json, "ore");
//            Ingredient ore = Ingredient.deserialize(jsonelement);
//            long weight = JSONUtils.getLong(json, "weight", 1);
//            return new TestRecipe(recipeId, ore, weight);
//        }
//
//        @Override
//        public TestRecipe read(ResourceLocation recipeId, PacketBuffer buffer)
//        {
//            Ingredient ingredient = Ingredient.read(buffer);
//            long weight = buffer.readVarLong();
//            return new TestRecipe(recipeId, ingredient, weight);
//        }
//
//        @Override
//        public void write(PacketBuffer buffer, TestRecipe recipe)
//        {
//            recipe.input.write(buffer);
//            buffer.writeVarLong(recipe.weight);
//        }
//    }
//}