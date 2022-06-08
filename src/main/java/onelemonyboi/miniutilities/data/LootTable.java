package onelemonyboi.miniutilities.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.basic.EnderLily;
import onelemonyboi.miniutilities.blocks.basic.FlameLily;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LootTable extends LootTableProvider {
    public LootTable(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, net.minecraft.loot.LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, net.minecraft.loot.LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
    }

    public static class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            // registerDropSelfLootTable(name);
            this.add(BlockList.EnderOre.get(), (ender) -> { return createSilkTouchDispatchTable(ender, applyExplosionDecay(ender, ItemLootEntry.lootTableItem(ItemList.EnderDust.get())
                    .apply(SetCount.setCount(RandomValueRange.between(2.0F, 5.0F)))
                    .apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
            });

            dropSelf(BlockList.CursedEarth.get());
            dropSelf(BlockList.BlessedEarth.get());
            dropSelf(BlockList.BlursedEarth.get());
            dropSelf(BlockList.EnderPearlBlock.get());
            dropSelf(BlockList.UnstableBlock.get());
            add(BlockList.AngelBlock.get(), noDrop());
            dropSelf(BlockList.WhiteLapisCaelestis.get());
            dropSelf(BlockList.LightGrayLapisCaelestis.get());
            dropSelf(BlockList.GrayLapisCaelestis.get());
            dropSelf(BlockList.BlackLapisCaelestis.get());
            dropSelf(BlockList.RedLapisCaelestis.get());
            dropSelf(BlockList.OrangeLapisCaelestis.get());
            dropSelf(BlockList.YellowLapisCaelestis.get());
            dropSelf(BlockList.LimeLapisCaelestis.get());
            dropSelf(BlockList.GreenLapisCaelestis.get());
            dropSelf(BlockList.LightBlueLapisCaelestis.get());
            dropSelf(BlockList.CyanLapisCaelestis.get());
            dropSelf(BlockList.BlueLapisCaelestis.get());
            dropSelf(BlockList.PurpleLapisCaelestis.get());
            dropSelf(BlockList.MagentaLapisCaelestis.get());
            dropSelf(BlockList.PinkLapisCaelestis.get());
            dropSelf(BlockList.BrownLapisCaelestis.get());
            add(BlockList.StoneDrum.get(), (block) -> dropWithTags(block, "FluidName", "Amount", "Tag", "Capacity"));
            add(BlockList.IronDrum.get(), (block) -> dropWithTags(block, "FluidName", "Amount", "Tag", "Capacity"));
            add(BlockList.ReinforcedLargeDrum.get(), (block) -> dropWithTags(block, "FluidName", "Amount", "Tag", "Capacity"));
            add(BlockList.NetheriteReinforcedDrum.get(), (block) -> dropWithTags(block, "FluidName", "Amount", "Tag", "Capacity"));
            add(BlockList.UnstableDrum.get(), (block) -> dropWithTags(block, "FluidName", "Amount", "Tag", "Capacity"));
            dropSelf(BlockList.WoodenSpikes.get());
            dropSelf(BlockList.IronSpikes.get());
            dropSelf(BlockList.GoldSpikes.get());
            dropSelf(BlockList.DiamondSpikes.get());
            dropSelf(BlockList.NetheriteSpikes.get());
            add(BlockList.MechanicalMiner.get(), (block) -> dropWithTags(block, "RedstoneMode", "WaitTime"));
            add(BlockList.MechanicalPlacer.get(), (block) -> dropWithTags(block, "RedstoneMode", "WaitTime"));
            add(BlockList.QuantumQuarry.get(), (block) -> dropWithTags(block, "RedstoneMode", "WaitTime"));
            add(BlockList.SolarPanelController.get(), (block) -> dropWithTags(block, "panelsActive", "power"));
            dropSelf(BlockList.SolarPanel.get());
            dropSelf(BlockList.LunarPanel.get());
            dropSelf(BlockList.EnderTile.get());
            dropSelf(BlockList.ChorusTile.get());

            add(BlockList.LaserHub.get(), (block) -> dropWithTags(block, "IsInput"));
            add(BlockList.LaserPort.get(), (block) -> dropWithTags(block, "IsInput"));

            dropSelf(BlockList.EtherealGlass.get());
            dropSelf(BlockList.ReverseEtherealGlass.get());
            dropSelf(BlockList.RedstoneGlass.get());
            dropSelf(BlockList.GlowingGlass.get());
            dropSelf(BlockList.DarkGlass.get());
            dropSelf(BlockList.DarkEtherealGlass.get());
            dropSelf(BlockList.DarkReverseEtherealGlass.get());

            dropSelf(BlockList.LapisLamp.get());
            dropSelf(BlockList.RedstoneClockBlock.get());

            ILootCondition.IBuilder ilootcondition = BlockStateProperty.hasBlockStateProperties(BlockList.EnderLily.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EnderLily.AGE, 7));
            add(BlockList.EnderLily.get(), applyExplosionDecay(BlockList.EnderLily.get(), net.minecraft.loot.LootTable.lootTable().withPool(LootPool.lootPool().add(ItemLootEntry.lootTableItem(ItemList.EnderLilySeeds.get()))).withPool(LootPool.lootPool().when(ilootcondition).add(ItemLootEntry.lootTableItem(Items.ENDER_PEARL))).withPool(LootPool.lootPool().when(ilootcondition).add(ItemLootEntry.lootTableItem(ItemList.EnderLilySeeds.get()).when(RandomChance.randomChance(0.01F))))));
            ILootCondition.IBuilder ilootcondition1 = BlockStateProperty.hasBlockStateProperties(BlockList.FlameLily.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FlameLily.AGE, 7));
            add(BlockList.FlameLily.get(), applyExplosionDecay(BlockList.FlameLily.get(), net.minecraft.loot.LootTable.lootTable().withPool(LootPool.lootPool().add(ItemLootEntry.lootTableItem(ItemList.FlameLilySeeds.get()))).withPool(LootPool.lootPool().when(ilootcondition1).add(ItemLootEntry.lootTableItem(ItemList.FlameLily.get()))).withPool(LootPool.lootPool().when(ilootcondition1).add(ItemLootEntry.lootTableItem(ItemList.FlameLilySeeds.get()).when(RandomChance.randomChance(0.01F))))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModRegistry.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }

        public static net.minecraft.loot.LootTable.Builder dropWithTags(Block block, String... tags) {
            CopyNbt.Builder tagNbtBuilder = CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY);
            for (String tag : tags) {
                tagNbtBuilder = tagNbtBuilder.copy(tag, "BlockEntityTag." + tag);
            }
            return net.minecraft.loot.LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(block).apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY)).apply(tagNbtBuilder))));
        }
    }
}