package onelemonyboi.miniutilities.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.LapisLamp;
import onelemonyboi.miniutilities.blocks.basic.*;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserHubBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserPortBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock.RedstoneClockBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.LunarPanelBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelController;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumBlock;
import onelemonyboi.miniutilities.blocks.basic.*;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerBlock;
import onelemonyboi.miniutilities.blocks.earth.GenericEarthBlock;
import onelemonyboi.miniutilities.blocks.spikes.SpikeBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerBlock;

import java.util.function.Supplier;

public class BlockList {
    public static final RegistryObject<Block> CursedEarth = register("cursed_earth", () -> GenericEarthBlock.CURSED_EARTH.apply(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> BlessedEarth = register("blessed_earth", () -> GenericEarthBlock.BLESSED_EARTH.apply(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> BlursedEarth = register("blursed_earth", () -> GenericEarthBlock.BLURSED_EARTH.apply(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> EnderOre = register("ender_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(2).strength(3).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> UnstableBlock = register("unstable_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(3).strength(12).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> EnderPearlBlock = register("ender_pearl_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(1).strength(3).harvestTool(ToolType.PICKAXE)));
    // ANGEL BLOCK TAKES 1 TICK TO BREAK
    public static final RegistryObject<Block> AngelBlock = registerNoItem("angel_block", () -> new AngelBlock(Material.STONE, 0.01F, 3F, 1));
    public static final RegistryObject<Block> EnderLily = registerNoTab("ender_lily_block", () -> new EnderLily());
    public static final RegistryObject<Block> FlameLily = registerNoTab("flame_lily_block", () -> new FlameLily());
    public static final RegistryObject<Block> WhiteLapisCaelestis = register("white_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> LightGrayLapisCaelestis = register("light_gray_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> GrayLapisCaelestis = register("gray_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> BlackLapisCaelestis = register("black_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> RedLapisCaelestis = register("red_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> OrangeLapisCaelestis = register("orange_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> YellowLapisCaelestis = register("yellow_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> LimeLapisCaelestis = register("lime_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> GreenLapisCaelestis = register("green_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> LightBlueLapisCaelestis = register("light_blue_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> CyanLapisCaelestis = register("cyan_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> BlueLapisCaelestis = register("blue_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> PurpleLapisCaelestis = register("purple_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> MagentaLapisCaelestis = register("magenta_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> PinkLapisCaelestis = register("pink_lapis_caelestis", LapisCaelestis::new);
    public static final RegistryObject<Block> BrownLapisCaelestis = register("brown_lapis_caelestis", LapisCaelestis::new);

    public static final RegistryObject<Block> StoneDrum = register("stone_drum", () -> new DrumBlock(16000, AbstractBlock.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> IronDrum = register("iron_drum", () -> new DrumBlock(256000, AbstractBlock.Properties.of(Material.METAL)));
    public static final RegistryObject<Block> ReinforcedLargeDrum = register("reinforced_large_drum", () -> new DrumBlock(4096000, AbstractBlock.Properties.of(Material.METAL)));
    public static final RegistryObject<Block> NetheriteReinforcedDrum = register("netherite_reinforced_drum", () -> new DrumBlock(65536000, AbstractBlock.Properties.of(Material.METAL)));
    public static final RegistryObject<Block> UnstableDrum = register("unstable_drum", () -> new DrumBlock(2147483647, AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> WoodenSpikes = register("wooden_spikes", () -> new SpikeBlock(AbstractBlock.Properties.of(Material.WOOD).harvestTool(ToolType.AXE).strength(2.0F).harvestLevel(0), 1, false, false, true));
    public static final RegistryObject<Block> IronSpikes = register("iron_spikes", () -> new SpikeBlock(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).strength(4.0F).harvestLevel(1), 2, false, false, false));
    public static final RegistryObject<Block> GoldSpikes = register("gold_spikes", () -> new SpikeBlock(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).strength(6.0F).harvestLevel(1), 4, false, true, false));
    public static final RegistryObject<Block> DiamondSpikes = register("diamond_spikes", () -> new SpikeBlock(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).strength(8.0F).harvestLevel(2), 8, true, false, false));
    public static final RegistryObject<Block> NetheriteSpikes = register("netherite_spikes", () -> new SpikeBlock(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).strength(16.0F).harvestLevel(3), 16, true, true, false));

    public static final RegistryObject<Block> MechanicalMiner = register("mechanical_miner", MechanicalMinerBlock::new);
    public static final RegistryObject<Block> MechanicalPlacer = register("mechanical_placer", MechanicalPlacerBlock::new);
    public static final RegistryObject<Block> QuantumQuarry = register("quantum_quarry", QuantumQuarryBlock::new);

    public static final RegistryObject<Block> SolarPanel = register("solar_panel", () -> new SolarPanelBlock(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> LunarPanel = register("lunar_panel", () -> new LunarPanelBlock(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> SolarPanelController = register("solar_panel_controller", SolarPanelController::new);
    public static final RegistryObject<Block> EnderTile = register("ender_tile", EnderTileBlock::new);
    public static final RegistryObject<Block> ChorusTile = register("chorus_tile", ChorusTileBlock::new);

    public static final RegistryObject<Block> LaserHub = register("laser_hub", () -> new LaserHubBlock(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> LaserPort = register("laser_port", () -> new LaserPortBlock(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> EtherealGlass = register("ethereal_glass", () -> new SpecialGlass(true, false, false, false, false));
    public static final RegistryObject<Block> ReverseEtherealGlass = register("reverse_ethereal_glass", () -> new SpecialGlass(false, true, false, false, false));
    public static final RegistryObject<Block> RedstoneGlass = register("redstone_glass", () -> new SpecialGlass(false, false, false, false, true));
    public static final RegistryObject<Block> GlowingGlass = register("glowing_glass", () -> new SpecialGlass(false, false, true, false, false));
    public static final RegistryObject<Block> DarkGlass = register("dark_glass", () -> new SpecialGlass(false, false, false, true, false));
    public static final RegistryObject<Block> DarkEtherealGlass = register("dark_ethereal_glass", () -> new SpecialGlass(true, false, false, true, false));
    public static final RegistryObject<Block> DarkReverseEtherealGlass = register("dark_reverse_ethereal_glass", () -> new SpecialGlass(false, true, false, true, false));

    public static final RegistryObject<Block> LapisLamp = register("lapis_lamp", LapisLamp::new);

    public static final RegistryObject<Block> RedstoneClockBlock = register("redstone_clock", RedstoneClockBlock::new);

    public static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return ModRegistry.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerNoTab(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(CreativeTab.getInstance())));
        return ret;
    }
}