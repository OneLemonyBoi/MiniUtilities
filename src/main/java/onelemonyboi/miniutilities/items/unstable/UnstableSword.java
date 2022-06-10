package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import static onelemonyboi.miniutilities.items.Kikoku.*;

public class UnstableSword extends SwordItem {
    public UnstableSword(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        entity.hurt(ARMOR_PIERCING_DAMAGE_SOURCE, 1);
        return false;
    }
}