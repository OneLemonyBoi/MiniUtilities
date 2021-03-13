package onelemonyboi.miniutilities.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import javax.annotation.Nonnull;

public class ArmorMaterial {
    public static class UnstableArmorMaterial implements IArmorMaterial {

        private static int[] array = new int[]{4, 7, 9, 4};

        @Override
        public int getDurability(@Nonnull EquipmentSlotType slotIn) {
            return 0;
        }

        @Override
        public int getDamageReductionAmount(@Nonnull EquipmentSlotType slot) {
            return array[slot.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return 25;
        }

        @Nonnull
        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
        }

        @Nonnull
        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(ItemList.UnstableIngot.get());
        }

        @Nonnull
        @Override
        public String getName() {
            return "unstable";
        }

        @Override
        public float getToughness() {
            return 5;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    }

    public static class OpiniumArmorMaterial implements IArmorMaterial {

        private static int[] array = new int[]{20, 35, 45, 20};

        @Override
        public int getDurability(@Nonnull EquipmentSlotType slotIn) {
            return 0;
        }

        @Override
        public int getDamageReductionAmount(@Nonnull EquipmentSlotType slot) {
            return array[slot.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return 100;
        }

        @Nonnull
        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
        }

        @Nonnull
        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(ItemList.UnstableIngot.get());
        }

        @Nonnull
        @Override
        public String getName() {
            return "opinium";
        }

        @Override
        public float getToughness() {
            return 20;
        }

        @Override
        public float getKnockbackResistance() {
            return 12;
        }
    }
}
