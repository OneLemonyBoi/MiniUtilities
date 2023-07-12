package onelemonyboi.miniutilities.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.function.Supplier;

public enum MUArmorMaterial implements ArmorMaterial {
    UNSTABLE(MiniUtilities.MOD_ID + ":unstable", 0, new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.1F, () -> {
        return Ingredient.of(ItemList.UnstableIngot.get());
    }),
    INFUSEDUNSTABLE(MiniUtilities.MOD_ID + ":infused", 0, new int[]{6, 12, 16, 6}, 50, net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_NETHERITE, 6.0F, 0.5F, () -> {
        return Ingredient.of(ItemList.UnstableIngot.get());
    }),
    BOOSTER("booster", 64, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return null;
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairMaterial;

    private MUArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, net.minecraft.sounds.SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = Lazy.of(repairMaterial);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return MAX_DAMAGE_ARRAY[pType.ordinal()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.damageReductionAmountArray[pType.ordinal()];
    }

    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
