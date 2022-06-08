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
        int level = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentList.Shotgun.get(), event.getBow());
        if (event.hasAmmo()) {
            float charge = BowItem.getPowerForTime(event.getCharge());
            for (int x = 0; x < level; x++) {
                AbstractArrowEntity arrowEntity = new ArrowEntity(event.getWorld(), event.getPlayer());
                arrowEntity.shootFromRotation(event.getPlayer(), event.getPlayer().xRot, event.getPlayer().yRot, 0, charge * 3.0F, (float) Math.pow(x, 1.5));
                arrowEntity.setDeltaMovement(arrowEntity.getDeltaMovement().x, arrowEntity.getDeltaMovement().y + 0.1, arrowEntity.getDeltaMovement().z);
                if (charge == 1.0F) {
                    arrowEntity.setCritArrow(true);
                }

                int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, event.getBow());
                if (j > 0) {
                    arrowEntity.setBaseDamage(arrowEntity.getBaseDamage() + (double) j * 0.5D + 0.5D);
                }

                int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, event.getBow());
                if (k > 0) {
                    arrowEntity.setKnockback(k);
                }

                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, event.getBow()) > 0) {
                    arrowEntity.setSecondsOnFire(100);
                }

                event.getBow().hurtAndBreak(1, event.getPlayer(), (player) -> {
                    player.broadcastBreakEvent(event.getPlayer().getUsedItemHand());
                });
                arrowEntity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;

                event.getWorld().addFreshEntity(arrowEntity);
            }
        }
    }
}
