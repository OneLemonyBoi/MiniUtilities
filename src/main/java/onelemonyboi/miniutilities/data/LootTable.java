package onelemonyboi.miniutilities.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.basic.EnderLily;
import onelemonyboi.miniutilities.blocks.basic.FlameLily;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LootTable extends LootTableProvider {
    public LootTable(DataGenerator generator) {
        super(generator.getPackOutput(), Collections.emptySet(), ImmutableList.of(new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)));
    }

    public static class ModBlockLootTables extends BlockLootSubProvider {
        protected ModBlockLootTables() {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            // registerDropSelfLootTable(name);
            this.add(BlockList.EnderOre.get(), (ender) -> { return createSilkTouchDispatchTable(ender, applyExplosionDecay(ender, LootItem.lootTableItem(ItemList.EnderDust.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
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

            LootItemBlockStatePropertyCondition.Builder ilootcondition = LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(BlockList.EnderLily.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EnderLily.AGE, 7));
            add(BlockList.EnderLily.get(), applyExplosionDecay(BlockList.EnderLily.get(), net.minecraft.world.level.storage.loot.LootTable.lootTable().withPool(net.minecraft.world.level.storage.loot.LootPool.lootPool().add(LootItem.lootTableItem(ItemList.EnderLilySeeds.get()))).withPool(net.minecraft.world.level.storage.loot.LootPool.lootPool().when(ilootcondition).add(LootItem.lootTableItem(Items.ENDER_PEARL))).withPool(net.minecraft.world.level.storage.loot.LootPool.lootPool().when(ilootcondition).add(LootItem.lootTableItem(ItemList.EnderLilySeeds.get()).when(LootItemRandomChanceCondition.randomChance(0.01F))))));
            LootItemBlockStatePropertyCondition.Builder ilootcondition1 = LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(BlockList.FlameLily.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FlameLily.AGE, 7));
            add(BlockList.FlameLily.get(), applyExplosionDecay(BlockList.FlameLily.get(), net.minecraft.world.level.storage.loot.LootTable.lootTable().withPool(net.minecraft.world.level.storage.loot.LootPool.lootPool().add(LootItem.lootTableItem(ItemList.FlameLilySeeds.get()))).withPool(net.minecraft.world.level.storage.loot.LootPool.lootPool().when(ilootcondition1).add(LootItem.lootTableItem(ItemList.FlameLily.get()))).withPool(net.minecraft.world.level.storage.loot.LootPool.lootPool().when(ilootcondition1).add(LootItem.lootTableItem(ItemList.FlameLilySeeds.get()).when(LootItemRandomChanceCondition.randomChance(0.01F))))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModRegistry.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }

        public net.minecraft.world.level.storage.loot.LootTable.Builder dropWithTags(net.minecraft.world.level.block.Block block, String... tags) {
            CopyNbtFunction.Builder tagNbtBuilder = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);
            for (String tag : tags) {
                tagNbtBuilder = tagNbtBuilder.copy(tag, "BlockEntityTag." + tag);
            }
            return net.minecraft.world.level.storage.loot.LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(tagNbtBuilder))));
        }
    }
}