package onelemonyboi.miniutilities.misc;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class InventoryHandling {
    public static void InventoryInsert(List<ItemStack> lists, IInventory iinventory, World world, Object o) {
        if (o instanceof TileEntity) {
            TileEntity obj = (TileEntity) (o);
            for (ItemStack itemStack : lists) {
                int i = iinventory.getSizeInventory();
                for (int j = 0; j < i && !itemStack.isEmpty(); ++j) {
                    ItemStack itemStack1 = iinventory.getStackInSlot(j);
                    if (itemStack1.isEmpty()) {
                        iinventory.setInventorySlotContents(j, itemStack);
                        itemStack = ItemStack.EMPTY;
                    } else if (canCombine(itemStack, itemStack1)) {
                        int x = itemStack.getMaxStackSize() - itemStack1.getCount();
                        int y = Math.min(itemStack.getCount(), x);
                        itemStack.shrink(y);
                        itemStack1.grow(y);
                    }
                }
                iinventory.markDirty();
                if (!itemStack.isEmpty()) {
                    InventoryHelper.spawnItemStack(world, obj.getPos().getX(), obj.getPos().getY() + 1, obj.getPos().getZ(), itemStack); // Hidden Gem
                }
            }
        }
    }

    private static boolean canCombine(ItemStack stack1, ItemStack stack2) {
        if (stack1.getItem() != stack2.getItem()) {
            return false;
        } else if (stack1.getDamage() != stack2.getDamage()) {
            return false;
        } else if (stack1.getCount() > stack1.getMaxStackSize()) {
            return false;
        } else {
            Boolean buffer = ItemStack.areItemStackTagsEqual(stack1, stack2);
            return buffer;
        }
    }
}
