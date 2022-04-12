package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.init.ItemList;

import javax.annotation.Nonnull;
import java.util.Set;

public class UnstableShovel extends ShovelItem {
    public UnstableShovel(IItemTier materialIn, float damage, float attackSpeed, Item.Properties properties) {
        super(materialIn, damage, attackSpeed, properties);
    }

    public static void shovelTransformation(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getPlayer().isSneaking() && event.getItemStack().getItem() == ItemList.UnstableShovel.get()) {
            Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
            if (block.equals(Blocks.COBBLESTONE)) block = Blocks.STONE;
            else if (block.equals(Blocks.GRAVEL)) block = Blocks.COBBLESTONE;
            else if (block.equals(Blocks.COARSE_DIRT)) block = Blocks.GRAVEL;
            else if (block.equals(Blocks.CLAY)) block = Blocks.COARSE_DIRT;
            else if (block.equals(Blocks.SAND)) block = Blocks.CLAY;
            event.getWorld().setBlockState(event.getPos(), block.getDefaultState());
        }
    }

    private static final Set<ToolType> shovel = Sets.newHashSet(ToolType.SHOVEL);

    @Nonnull
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return shovel;
    }
}
