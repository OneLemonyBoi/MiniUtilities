package onelemonyboi.miniutilities;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import onelemonyboi.miniutilities.init.BlockList;

public class CreativeTab extends ItemGroup {
    private static final CreativeTab INSTANCE = new CreativeTab();

    public CreativeTab() { super(MiniUtilities.MOD_ID); }

    public static CreativeTab getInstance()
    {
        return INSTANCE;
    }

    public ItemStack createIcon()
    {
        return new ItemStack(BlockList.BlessedEarth.get());
    }
}
