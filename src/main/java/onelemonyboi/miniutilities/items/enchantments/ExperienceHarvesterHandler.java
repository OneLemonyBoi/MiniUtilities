package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import onelemonyboi.miniutilities.init.EnchantmentList;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.Random;

public class ExperienceHarvesterHandler {
    public static void handleEntityKill(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() == null) {
            return;
        }
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentList.ExperienceHarvester.get(), event.getAttackingPlayer().getItemInHand(InteractionHand.MAIN_HAND));
        if (!event.getEntity().getCommandSenderWorld().isClientSide() &&  enchantLevel > 0) { // Checks if running on server and enchant is on tool
            float chanceModifier = (float) (1.0 / enchantLevel);
            int randInt = new Random().nextInt(Math.round(chanceModifier * 20));
            if (randInt == 0) {
                Containers.dropItemStack(event.getAttackingPlayer().getCommandSenderWorld(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(ItemList.ExperiencePearl.get()));
            }
        }
    }
}