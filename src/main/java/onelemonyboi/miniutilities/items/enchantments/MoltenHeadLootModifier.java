package onelemonyboi.miniutilities.items.enchantments;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoltenHeadLootModifier extends LootModifier {
    public MoltenHeadLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        List<ItemStack> smeltedLoot = new ArrayList<>();
        for (ItemStack item : generatedLoot) {
            Optional<SmeltingRecipe> recipe = context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(item), context.getLevel());
            smeltedLoot.add(recipe.map(AbstractCookingRecipe::getResultItem).orElse(item));
        }
        return smeltedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<MoltenHeadLootModifier> {
        @Override
        public MoltenHeadLootModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditionsIn) {
            return new MoltenHeadLootModifier(conditionsIn);
        }

        @Override
        public JsonObject write(MoltenHeadLootModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}