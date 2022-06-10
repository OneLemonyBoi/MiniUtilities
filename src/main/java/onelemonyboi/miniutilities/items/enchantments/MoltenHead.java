package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class MoltenHead extends net.minecraft.world.item.enchantment.Enchantment {
    public MoltenHead(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.DIGGER, slots);
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
