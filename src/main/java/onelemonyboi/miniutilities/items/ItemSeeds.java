package onelemonyboi.miniutilities.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import onelemonyboi.miniutilities.CreativeTab;

public class ItemSeeds extends BlockNamedItem {
    public ItemSeeds(Block crop)
    {
        super(crop, new Item.Properties().maxStackSize(64).group(CreativeTab.getInstance()));
    }
}
