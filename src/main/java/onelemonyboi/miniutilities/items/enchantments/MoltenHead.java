package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.text.ITextComponent;

public class MoltenHead extends Enchantment {
    public MoltenHead(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.DIGGER, slots);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 2 * enchantmentLevel;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50 * enchantmentLevel;
    }
    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }
}
