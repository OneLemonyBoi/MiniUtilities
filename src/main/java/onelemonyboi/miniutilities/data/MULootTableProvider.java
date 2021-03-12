package onelemonyboi.miniutilities.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.items.BlockList;
import onelemonyboi.miniutilities.items.ItemList;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MULootTableProvider extends LootTableProvider {
    public MULootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validateLootTable(validationtracker, p_218436_2_, p_218436_3_));
    }

    public static class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            // registerDropSelfLootTable(name);
            this.registerLootTable(BlockList.EnderOre.get(), (ender) -> { return droppingWithSilkTouch(ender, withExplosionDecay(ender, ItemLootEntry.builder(ItemList.EnderDust.get())
                    .acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 5.0F)))
                    .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
            });

            registerDropSelfLootTable(BlockList.CursedEarth.get());
            registerDropSelfLootTable(BlockList.BlessedEarth.get());
            registerDropSelfLootTable(BlockList.EnderPearlBlock.get());
            registerLootTable(BlockList.AngelBlock.get(), blockNoDrop());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModRegistry.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}