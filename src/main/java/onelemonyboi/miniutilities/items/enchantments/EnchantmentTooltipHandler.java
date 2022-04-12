package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import onelemonyboi.miniutilities.init.EnchantmentList;

public class EnchantmentTooltipHandler {
    public static void onTooltipDisplay(ItemTooltipEvent event) {
        if (!(event.getItemStack().getItem() instanceof EnchantedBookItem)) return;
        for (Enchantment e : EnchantmentHelper.getEnchantments(event.getItemStack()).keySet()) {
            if (e.equals(EnchantmentList.Shotgun.get())) {
                event.getToolTip().add(new TranslationTextComponent("enchantment.miniutilities.shotgun.desc"));
            }
            else if (e.equals(EnchantmentList.MoltenHead.get())) {
                event.getToolTip().add(new TranslationTextComponent("enchantment.miniutilities.molten_head.desc"));
            }
            else if (e.equals(EnchantmentList.ExperienceHarvester.get())) {
                event.getToolTip().add(new TranslationTextComponent("enchantment.miniutilities.experience_harvester.desc"));
            }
        }
    }
}
