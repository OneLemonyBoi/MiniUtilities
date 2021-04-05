package onelemonyboi.miniutilities.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class Shotgun extends Enchantment {
    public Shotgun(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.BOW, slots);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 5;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 10000;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }


}
