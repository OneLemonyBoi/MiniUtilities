package onelemonyboi.miniutilities.items.enchantments;

import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MoltenHeadLootModifier extends LootModifier {
    public MoltenHeadLootModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        List<ItemStack> smeltedLoot = new ArrayList<>();
        for (ItemStack item : generatedLoot) {
            Optional<FurnaceRecipe> recipe = context.getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(item), context.getWorld());
            smeltedLoot.add(recipe.map(AbstractCookingRecipe::getRecipeOutput).orElse(item));
        }
        return smeltedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<MoltenHeadLootModifier> {
        @Override
        public MoltenHeadLootModifier read(ResourceLocation name, JsonObject json, ILootCondition[] conditionsIn) {
            return new MoltenHeadLootModifier(conditionsIn);
        }

        @Override
        public JsonObject write(MoltenHeadLootModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
