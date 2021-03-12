package onelemonyboi.miniutilities.blocks;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.MiniUtilities;

public class BaseItem extends net.minecraft.item.Item{
    public BaseItem(String name)
    {
        super(new Properties().group(CreativeTab.getInstance()));
        this.setRegistryName(new ResourceLocation(MiniUtilities.MOD_ID, name));
    }
}
