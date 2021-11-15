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
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.EnderLily;
import onelemonyboi.miniutilities.blocks.FlameLily;
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
            registerDropSelfLootTable(BlockList.BlursedEarth.get());
            registerDropSelfLootTable(BlockList.EnderPearlBlock.get());
            registerDropSelfLootTable(BlockList.UnstableBlock.get());
            registerLootTable(BlockList.AngelBlock.get(), blockNoDrop());
            registerDropSelfLootTable(BlockList.WhiteLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.LightGrayLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.GrayLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.BlackLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.RedLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.OrangeLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.YellowLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.LimeLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.GreenLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.LightBlueLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.CyanLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.BlueLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.PurpleLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.MagentaLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.PinkLapisCaelestis.get());
            registerDropSelfLootTable(BlockList.BrownLapisCaelestis.get());
            registerLootTable(BlockList.StoneDrum.get(), blockNoDrop());
            registerLootTable(BlockList.IronDrum.get(), blockNoDrop());
            registerLootTable(BlockList.ReinforcedLargeDrum.get(), blockNoDrop());
            registerLootTable(BlockList.NetheriteReinforcedDrum.get(), blockNoDrop());
            registerLootTable(BlockList.UnstableDrum.get(), blockNoDrop());
            registerDropSelfLootTable(BlockList.WoodenSpikes.get());
            registerDropSelfLootTable(BlockList.IronSpikes.get());
            registerDropSelfLootTable(BlockList.GoldSpikes.get());
            registerDropSelfLootTable(BlockList.DiamondSpikes.get());
            registerDropSelfLootTable(BlockList.NetheriteSpikes.get());
            registerLootTable(BlockList.MechanicalMiner.get(), blockNoDrop());
            registerLootTable(BlockList.MechanicalPlacer.get(), blockNoDrop());
            registerLootTable(BlockList.QuantumQuarry.get(), blockNoDrop());
            registerLootTable(BlockList.SolarPanelController.get(), blockNoDrop());
            registerDropSelfLootTable(BlockList.SolarPanel.get());
            registerDropSelfLootTable(BlockList.LunarPanel.get());
            registerDropSelfLootTable(BlockList.EnderTile.get());
            registerDropSelfLootTable(BlockList.ChorusTile.get());

            registerLootTable(BlockList.LaserHub.get(), blockNoDrop());
            registerLootTable(BlockList.LaserPort.get(), blockNoDrop());

            registerDropSelfLootTable(BlockList.EtherealGlass.get());
            registerDropSelfLootTable(BlockList.ReverseEtherealGlass.get());
            registerDropSelfLootTable(BlockList.RedstoneGlass.get());
            registerDropSelfLootTable(BlockList.GlowingGlass.get());
            registerDropSelfLootTable(BlockList.DarkGlass.get());
            registerDropSelfLootTable(BlockList.DarkEtherealGlass.get());
            registerDropSelfLootTable(BlockList.DarkReverseEtherealGlass.get());

            registerDropSelfLootTable(BlockList.LapisLamp.get());

            ILootCondition.IBuilder ilootcondition = BlockStateProperty.builder(BlockList.EnderLily.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(EnderLily.AGE, 7));
            registerLootTable(BlockList.EnderLily.get(), withExplosionDecay(BlockList.EnderLily.get(), net.minecraft.loot.LootTable.builder().addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(ItemList.EnderLilySeeds.get()))).addLootPool(LootPool.builder().acceptCondition(ilootcondition).addEntry(ItemLootEntry.builder(Items.ENDER_PEARL))).addLootPool(LootPool.builder().acceptCondition(ilootcondition).addEntry(ItemLootEntry.builder(ItemList.EnderLilySeeds.get()).acceptCondition(RandomChance.builder(0.01F))))));
            ILootCondition.IBuilder ilootcondition1 = BlockStateProperty.builder(BlockList.FlameLily.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(FlameLily.AGE, 7));
            registerLootTable(BlockList.FlameLily.get(), withExplosionDecay(BlockList.FlameLily.get(), net.minecraft.loot.LootTable.builder().addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(ItemList.FlameLilySeeds.get()))).addLootPool(LootPool.builder().acceptCondition(ilootcondition1).addEntry(ItemLootEntry.builder(ItemList.FlameLily.get()))).addLootPool(LootPool.builder().acceptCondition(ilootcondition1).addEntry(ItemLootEntry.builder(ItemList.FlameLilySeeds.get()).acceptCondition(RandomChance.builder(0.01F))))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModRegistry.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}