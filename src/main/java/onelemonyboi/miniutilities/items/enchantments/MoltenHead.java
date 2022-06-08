package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.text.ITextComponent;

import net.minecraft.enchantment.Enchantment.Rarity;

public class MoltenHead extends Enchantment {
    public MoltenHead(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.DIGGER, slots);
    }

    public int getMinCost(int enchantmentLevel) {
        return 2 * enchantmentLevel;
    }

    public int getMaxCost(int enchantmentLevel) {
        return 50 * enchantmentLevel;
    }
    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }
}
