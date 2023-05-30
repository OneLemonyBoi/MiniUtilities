package onelemonyboi.miniutilities.data;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.EnchantmentList;
import onelemonyboi.miniutilities.init.LootModifierList;
import onelemonyboi.miniutilities.items.enchantments.MoltenHeadLootModifier;

public class LootModifier extends GlobalLootModifierProvider {
    public LootModifier(DataGenerator gen) {
        super(gen, MiniUtilities.MOD_ID);
    }

    @Override
    protected void start() {
        add("molten_head_loot_modifier", LootModifierList.MoltenHeadLootModifierSerializer.get(), new MoltenHeadLootModifier(new LootItemCondition[]{
                MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(EnchantmentList.MoltenHead.get(), MinMaxBounds.Ints.atLeast(1)))).build()
        }));
    }
}
