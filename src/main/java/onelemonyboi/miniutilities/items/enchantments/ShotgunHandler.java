package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import onelemonyboi.miniutilities.init.EnchantmentList;

public class ShotgunHandler {
    public static void handleBowShot(ArrowLooseEvent event) {
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentList.Shotgun.get(), event.getBow());
        if (event.hasAmmo()) {
            float charge = BowItem.getArrowVelocity(event.getCharge());
            for (int x = 0; x < level; x++) {
                AbstractArrowEntity arrowEntity = new ArrowEntity(event.getWorld(), event.getPlayer());
                arrowEntity.setDirectionAndMovement(event.getPlayer(), event.getPlayer().rotationPitch, event.getPlayer().rotationYaw, 0, charge * 3.0F, (float) Math.pow(x, 1.5));
                arrowEntity.setMotion(arrowEntity.getMotion().x, arrowEntity.getMotion().y + 0.1, arrowEntity.getMotion().z);
                if (charge == 1.0F) {
                    arrowEntity.setIsCritical(true);
                }

                int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, event.getBow());
                if (j > 0) {
                    arrowEntity.setDamage(arrowEntity.getDamage() + (double) j * 0.5D + 0.5D);
                }

                int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, event.getBow());
                if (k > 0) {
                    arrowEntity.setKnockbackStrength(k);
                }

                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, event.getBow()) > 0) {
                    arrowEntity.setFire(100);
                }

                event.getBow().damageItem(1, event.getPlayer(), (player) -> {
                    player.sendBreakAnimation(event.getPlayer().getActiveHand());
                });
                arrowEntity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;

                event.getWorld().addEntity(arrowEntity);
            }
        }
    }
}
