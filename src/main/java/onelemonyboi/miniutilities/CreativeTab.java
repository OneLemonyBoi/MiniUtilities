package onelemonyboi.miniutilities;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import onelemonyboi.miniutilities.init.BlockList;

public class CreativeTab extends CreativeModeTab {
    private static final CreativeTab INSTANCE = new CreativeTab();

    public CreativeTab() { super(MiniUtilities.MOD_ID); }

    public static CreativeTab getInstance()
    {
        return INSTANCE;
    }

    public net.minecraft.world.item.ItemStack makeIcon()
    {
        return new ItemStack(BlockList.AngelBlock.get());
    }
}
