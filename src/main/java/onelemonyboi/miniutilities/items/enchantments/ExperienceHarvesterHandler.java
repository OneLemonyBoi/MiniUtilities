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
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentList.ExperienceHarvester.get(), event.getAttackingPlayer().getHeldItem(Hand.MAIN_HAND));
        if (!event.getEntity().getEntityWorld().isRemote() &&  enchantLevel > 0) { // Checks if running on server and enchant is on tool
            float chanceModifier = (float) (1.0 / enchantLevel);
            int randInt = new Random().nextInt(Math.round(chanceModifier * 20));
            if (randInt == 0) {
                InventoryHelper.spawnItemStack(event.getAttackingPlayer().getEntityWorld(), event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), new ItemStack(ItemList.ExperiencePearl.get()));
            }
        }
    }
}