package onelemonyboi.miniutilities.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.items.enchantments.ExperienceHarvester;
import onelemonyboi.miniutilities.items.enchantments.MoltenHead;
import onelemonyboi.miniutilities.items.enchantments.Shotgun;

public class EnchantmentList {
    public static final RegistryObject<Enchantment> Shotgun = ModRegistry.ENCHANTMENTS.register("shotgun", () -> new Shotgun(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> MoltenHead = ModRegistry.ENCHANTMENTS.register("molten_head", () -> new MoltenHead(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> ExperienceHarvester = ModRegistry.ENCHANTMENTS.register("experience_harvester", () -> new ExperienceHarvester(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));

    public static void register() {}
}
