package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;

public class UnstablePickaxe extends PickaxeItem {
    public UnstablePickaxe(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public float getDestroySpeed(net.minecraft.world.item.ItemStack stack, BlockState state) {
        net.minecraft.world.level.material.Material material = state.getMaterial();
        return material == Material.STONE ? this.speed * 5 : this.speed;
    }
}
