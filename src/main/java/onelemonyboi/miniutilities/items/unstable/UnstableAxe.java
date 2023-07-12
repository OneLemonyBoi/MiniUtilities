package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;

public class UnstableAxe extends AxeItem {

    public UnstableAxe(Tier materialIn, float damage, float attackSpeed, Properties properties) {
        super(materialIn, damage, attackSpeed, properties);
    }

    @Override
    public void inventoryTick(net.minecraft.world.item.ItemStack stack, Level worldIn, net.minecraft.world.entity.Entity entity, int itemSlot, boolean isSelected) {
        if (!isSelected || !(entity instanceof Player) || worldIn.isClientSide) return;
        ((Player) entity).getFoodData().eat(1, 0.2F);  }

    @Override
    public boolean onLeftClickEntity(net.minecraft.world.item.ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity) {
            if (((net.minecraft.world.entity.LivingEntity) entity).getMobType() == MobType.UNDEAD)
                entity.hurt(player.level().damageSources().playerAttack(player), 4);
            else {
                ((LivingEntity) entity).heal(8);
                return true;
            }
            player.hurt(player.level().damageSources().magic(), 2);
        }
        return false;
    }
}
