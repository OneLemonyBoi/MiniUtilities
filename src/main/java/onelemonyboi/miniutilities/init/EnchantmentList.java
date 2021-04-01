package onelemonyboi.miniutilities.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.items.MultiShot;

public class EnchantmentList {
    public static final RegistryObject<Enchantment> MultiShot = ModRegistry.ENCHANTMENTS.register("multishot", () -> new MultiShot(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));

    public static void register() {}
}
