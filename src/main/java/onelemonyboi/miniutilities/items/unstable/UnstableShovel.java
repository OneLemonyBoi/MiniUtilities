package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.Sets;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import java.util.Set;

public class UnstableShovel extends ShovelItem {
    public UnstableShovel(IItemTier materialIn, float damage, float attackSpeed, Item.Properties properties) {
        super(materialIn, damage, attackSpeed, properties);
    }

    private static final Set<ToolType> shovel = Sets.newHashSet(ToolType.SHOVEL);

    @Nonnull
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return shovel;
    }
}
