package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import onelemonyboi.miniutilities.init.EnchantmentList;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

public class EnchantmentTooltipHandler {
    public static void register() {
        EVENT_BUS.addListener(EnchantmentTooltipHandler::onTooltipDisplay);
    }

    public static void onTooltipDisplay(ItemTooltipEvent event) {
        if (!(event.getItemStack().getItem() instanceof EnchantedBookItem)) return;
        for (Enchantment e : EnchantmentHelper.getEnchantments(event.getItemStack()).keySet()) {
            if (e.equals(EnchantmentList.Shotgun.get())) {
                event.getToolTip().add(new TranslatableComponent("enchantment.miniutilities.shotgun.desc"));
            }
            else if (e.equals(EnchantmentList.MoltenHead.get())) {
                event.getToolTip().add(new TranslatableComponent("enchantment.miniutilities.molten_head.desc"));
            }
            else if (e.equals(EnchantmentList.ExperienceHarvester.get())) {
                event.getToolTip().add(new TranslatableComponent("enchantment.miniutilities.experience_harvester.desc"));
            }
        }
    }
}
