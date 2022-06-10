package onelemonyboi.miniutilities.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.items.enchantments.ExperienceHarvester;
import onelemonyboi.miniutilities.items.enchantments.MoltenHead;
import onelemonyboi.miniutilities.items.enchantments.Shotgun;

public class EnchantmentList {
    public static final RegistryObject<net.minecraft.world.item.enchantment.Enchantment> Shotgun = ModRegistry.ENCHANTMENTS.register("shotgun", () -> new Shotgun(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> MoltenHead = ModRegistry.ENCHANTMENTS.register("molten_head", () -> new MoltenHead(net.minecraft.world.item.enchantment.Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    public static final net.minecraftforge.registries.RegistryObject<Enchantment> ExperienceHarvester = ModRegistry.ENCHANTMENTS.register("experience_harvester", () -> new ExperienceHarvester(net.minecraft.world.item.enchantment.Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));

    public static void register() {}
}
