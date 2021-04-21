package onelemonyboi.miniutilities.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.init.EnchantmentList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BowItem.class)
public class BowMixin {
    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        return arrow;
    }
    @Inject(at = @At(shift = At.Shift.BEFORE, value = "INVOKE", target = "Lnet/minecraft/world/World;addEntity(Lnet/minecraft/entity/Entity;)Z"), method = "Lnet/minecraft/item/BowItem;onPlayerStoppedUsing(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V", locals = LocalCapture.CAPTURE_FAILHARD)
    private void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft, CallbackInfo ci, PlayerEntity playerEntity, boolean bool1, ItemStack itemStack, int i, float f, boolean bool2, ArrowItem arrowItem, AbstractArrowEntity abstractarrowentity) {
        Integer inter = EnchantmentHelper.getEnchantmentLevel(EnchantmentList.Shotgun.get(), stack);
        for (int x = 0; x < inter; x++) {
            AbstractArrowEntity abstractarrowentity2 = arrowItem.createArrow(worldIn, itemStack, playerEntity);
            abstractarrowentity2 = customArrow(abstractarrowentity2);
            abstractarrowentity2.setDirectionAndMovement(playerEntity, (float) (playerEntity.rotationPitch), playerEntity.rotationYaw, 0.0F, f * 3.0F, 1.0F * x);
            if (f == 1.0F) {
                abstractarrowentity2.setIsCritical(true);
            }

            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
            if (j > 0) {
                abstractarrowentity2.setDamage(abstractarrowentity2.getDamage() + (double)j * 0.5D + 0.5D);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
            if (k > 0) {
                abstractarrowentity2.setKnockbackStrength(k);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                abstractarrowentity2.setFire(100);
            }

            stack.damageItem(1, playerEntity, (player) -> {
                player.sendBreakAnimation(playerEntity.getActiveHand());
            });
            abstractarrowentity2.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;

            worldIn.addEntity(abstractarrowentity2);
        }
    }
}
