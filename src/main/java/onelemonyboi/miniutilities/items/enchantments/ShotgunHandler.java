package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import onelemonyboi.miniutilities.init.EnchantmentList;

public class ShotgunHandler {
    public static void handleBowShot(ArrowLooseEvent event) {
        int level = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(EnchantmentList.Shotgun.get(), event.getBow());
        if (event.hasAmmo()) {
            float charge = BowItem.getPowerForTime(event.getCharge());
            for (int x = 0; x < level; x++) {
                AbstractArrow arrowEntity = new Arrow(event.getWorld(), event.getPlayer());
                arrowEntity.shootFromRotation(event.getPlayer(), event.getPlayer().xRotO, event.getPlayer().yRotO, 0, charge * 3.0F, (float) Math.pow(x, 1.5));
                arrowEntity.setDeltaMovement(arrowEntity.getDeltaMovement().x, arrowEntity.getDeltaMovement().y + 0.1, arrowEntity.getDeltaMovement().z);
                if (charge == 1.0F) {
                    arrowEntity.setCritArrow(true);
                }

                int j = EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.POWER_ARROWS, event.getBow());
                if (j > 0) {
                    arrowEntity.setBaseDamage(arrowEntity.getBaseDamage() + (double) j * 0.5D + 0.5D);
                }

                int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, event.getBow());
                if (k > 0) {
                    arrowEntity.setKnockback(k);
                }

                if (net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.FLAMING_ARROWS, event.getBow()) > 0) {
                    arrowEntity.setSecondsOnFire(100);
                }

                event.getBow().hurtAndBreak(1, event.getPlayer(), (player) -> {
                    player.broadcastBreakEvent(event.getPlayer().getUsedItemHand());
                });
                arrowEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

                event.getWorld().addFreshEntity(arrowEntity);
            }
        }
    }
}
