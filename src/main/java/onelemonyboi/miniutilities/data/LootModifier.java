package onelemonyboi.miniutilities.data;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.EnchantmentList;
import onelemonyboi.miniutilities.init.LootModifierList;
import onelemonyboi.miniutilities.items.enchantments.MoltenHeadLootModifier;

public class LootModifier extends GlobalLootModifierProvider {
    public LootModifier(DataGenerator gen) {
        super(gen, MiniUtilities.MOD_ID);
    }

    @Override
    protected void start() {
        add("molten_head_loot_modifier", LootModifierList.MoltenHeadLootModifierSerializer.get(), new MoltenHeadLootModifier(new ILootCondition[]{
                MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(EnchantmentList.MoltenHead.get(), MinMaxBounds.IntBound.atLeast(1)))).build()
        }));
    }
}
