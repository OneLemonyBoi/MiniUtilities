package onelemonyboi.miniutilities.items;

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
import onelemonyboi.miniutilities.blocks.*;

import java.util.function.Supplier;

public class BlockList {
    public static final RegistryObject<Block> CursedEarth = register("cursed_earth", () -> new CursedEarthBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> BlessedEarth = register("blessed_earth", () -> new BlessedEarthBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> EnderOre = register("ender_ore", () -> new BaseBlock(Material.ROCK, 3, 3, 2, ToolType.PICKAXE));
    public static final RegistryObject<Block> EnderPearlBlock = register("ender_pearl_block", () -> new BaseBlock(Material.ROCK, 3, 3, 1, ToolType.PICKAXE));
    // ANGEL BLOCK TAKES 1 TICK TO BREAK
    public static final RegistryObject<Block> AngelBlock = registerNoItem("angel_block", () -> new AngelBlock(Material.ROCK, 0.01F, 3F, 1));
    public static final RegistryObject<Block> EnderLily = registerNoTab("ender_lily", () -> new EnderLily());
    public static final RegistryObject<Block> FlameLily = registerNoTab("flame_lily", () -> new FlameLily());

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
