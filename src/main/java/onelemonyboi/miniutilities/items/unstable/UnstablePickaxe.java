package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

public class UnstablePickaxe extends PickaxeItem {
    public UnstablePickaxe(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public float getDestroySpeed(net.minecraft.world.item.ItemStack stack, BlockState state) {
        return (state.is(Tags.Blocks.COBBLESTONE) || state.is(Tags.Blocks.STONE)) ? this.speed * 5 : this.speed;
    }
}
