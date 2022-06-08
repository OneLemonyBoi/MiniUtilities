package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import onelemonyboi.miniutilities.init.EnchantmentList;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.Random;

public class ExperienceHarvesterHandler {
    public static void handleEntityKill(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() == null) {
            return;
        }
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentList.ExperienceHarvester.get(), event.getAttackingPlayer().getItemInHand(Hand.MAIN_HAND));
        if (!event.getEntity().getCommandSenderWorld().isClientSide() &&  enchantLevel > 0) { // Checks if running on server and enchant is on tool
            float chanceModifier = (float) (1.0 / enchantLevel);
            int randInt = new Random().nextInt(Math.round(chanceModifier * 20));
            if (randInt == 0) {
                InventoryHelper.dropItemStack(event.getAttackingPlayer().getCommandSenderWorld(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(ItemList.ExperiencePearl.get()));
            }
        }
    }
}