package onelemonyboi.miniutilities.items;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.AngelBlockItem;

public class ItemList {
    public static final RegistryObject<Item> EnderDust = ModRegistry.ITEMS.register("ender_dust", () ->
            new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> AngelBlockItem = ModRegistry.ITEMS.register("angel_block", () ->
            new AngelBlockItem(BlockList.AngelBlock.get()));
    public static final RegistryObject<Item> Kikoku = ModRegistry.ITEMS.register("kikoku", () ->
            new Kikoku(ItemTier.KIKOKU,  13, -2.4F, (new Item.Properties().group(CreativeTab.getInstance()))));

    public static void register() {}
}
