package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import onelemonyboi.miniutilities.init.EnchantmentList;
import onelemonyboi.miniutilities.init.ItemList;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExperienceHarvesterHandler {
    public static void handleEntityKill(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() == null) {
            return;
        }
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentList.ExperienceHarvester.get(), event.getAttackingPlayer().getHeldItem(Hand.MAIN_HAND));
        List<ItemStack> generatedLoot = new ArrayList<>();
        if (!event.getEntity().getEntityWorld().isRemote() && enchantLevel > 0 && event.getEntity() instanceof MobEntity) {
            int randInt = new Random().nextInt(50);
            if (randInt < enchantLevel) {
                int experienceGiven = (int) Math.ceil((event.getEntityLiving().getMaxHealth() / 2.0));
                String octalExperience = Integer.toOctalString(experienceGiven);
                octalExperience = StringUtils.reverse(octalExperience);
                // JANK INCOMING
                int i = 0;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl1x.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl2x.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl3x.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl4x.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl5x.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl6x.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl7x.get(), octalExperience.charAt(i) - 48));
                i++;
                if (octalExperience.length() > i && octalExperience.charAt(i) != '0') generatedLoot.add(new ItemStack(ItemList.ExperiencePearl8x.get(), octalExperience.charAt(i) - 48));
            }
            for (ItemStack i : generatedLoot) {
                InventoryHelper.spawnItemStack(event.getAttackingPlayer().getEntityWorld(), event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), i);
            }
        }
    }
}