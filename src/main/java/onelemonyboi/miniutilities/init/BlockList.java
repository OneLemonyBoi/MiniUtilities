package onelemonyboi.miniutilities.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.Tanks.DrumBlock;
import onelemonyboi.miniutilities.blocks.*;

import java.util.function.Supplier;

public class BlockList {
    public static final RegistryObject<Block> CursedEarth = register("cursed_earth", () -> new CursedEarthBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> BlessedEarth = register("blessed_earth", () -> new BlessedEarthBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> BlursedEarth = register("blursed_earth", () -> new BlursedEarthBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> EnderOre = register("ender_ore", () -> new BaseBlock(Material.ROCK, 3, 3, 2, ToolType.PICKAXE));
    public static final RegistryObject<Block> UnstableBlock = register("unstable_block", () -> new BaseBlock(Material.ROCK, 12, 12, 3, ToolType.PICKAXE));
    public static final RegistryObject<Block> EnderPearlBlock = register("ender_pearl_block", () -> new BaseBlock(Material.ROCK, 3, 3, 1, ToolType.PICKAXE));
    // ANGEL BLOCK TAKES 1 TICK TO BREAK
    public static final RegistryObject<Block> AngelBlock = registerNoItem("angel_block", () -> new AngelBlock(Material.ROCK, 0.01F, 3F, 1));
    public static final RegistryObject<Block> EnderLily = registerNoTab("ender_lily", () -> new EnderLily());
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

    public static final RegistryObject<Block> StoneDrum = register("stone_drum", () -> new DrumBlock(16000, Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> IronDrum = register("iron_drum", () -> new DrumBlock(256000, Block.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).hardnessAndResistance(4.0F)));
    public static final RegistryObject<Block> ReinforcedLargeDrum = register("reinforced_large_drum", () -> new DrumBlock(4096000, Block.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).hardnessAndResistance(6.0F)));
    // So unstable it creates a new dimension :)
    public static final RegistryObject<Block> UnstableDrum = register("unstable_drum", () -> new DrumBlock(65536000, Block.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).hardnessAndResistance(8.0F)));
    public static final RegistryObject<Block> InfusedDrum = register("infused_drum", () -> new DrumBlock(2147483647, Block.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).hardnessAndResistance(12.0F)));


    public static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return ModRegistry.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().group(CreativeTab.getInstance())));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> registerNoTab(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
        return ret;
    }
}
