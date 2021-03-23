package onelemonyboi.miniutilities.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelItems extends Item {
    private int burnTime;
    public FuelItems(Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return this.burnTime;
    }
}
