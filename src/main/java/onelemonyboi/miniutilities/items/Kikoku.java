package onelemonyboi.miniutilities.items;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import onelemonyboi.miniutilities.init.AttributeList;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.startup.Config;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Kikoku extends SwordItem {
    public static UUID SOUL_DAMAGE_MODIFIER = UUID.fromString("d2928c01-5d7d-41c5-bd3a-9ca8f43c8ff8");

    public static final DamageSource DIVINE_DAMAGE_SOURCE = (new DamageSource("divineDamage")).setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute();
    public static final DamageSource ARMOR_PIERCING_DAMAGE_SOURCE = (new DamageSource("armourPiercingDamage")).setDamageBypassesArmor().setDamageIsAbsolute();

    public Kikoku(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
        ListMultimap<Attribute, AttributeModifier> multimaps = ArrayListMultimap.create();
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            multimaps.put(AttributeList.ArmorPiercingDamage.get(), new AttributeModifier(Item.ATTACK_DAMAGE_MODIFIER, "Armor Piercing Damage Modifier", 3, AttributeModifier.Operation.ADDITION));
            multimaps.put(AttributeList.DivineDamage.get(), new AttributeModifier(Item.ATTACK_DAMAGE_MODIFIER, "Divine Damage Modifier", 1, AttributeModifier.Operation.ADDITION));
            multimaps.put(AttributeList.SoulDamage.get(), new AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage Modifier", 0.25, AttributeModifier.Operation.ADDITION));
        }
        for (Attribute attribute : multimap.keySet()) {
            multimaps.putAll(attribute, multimap.get(attribute));
        }
        return multimaps;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target == null || !target.canBeAttackedWithItem() || attacker.world.isRemote) {
            return false;
        }
        Map<Enchantment, Integer> stackEnchantments = EnchantmentHelper.getEnchantments(stack);
        Integer sharpnessLevel = stackEnchantments.get(Enchantments.SHARPNESS) == null ? -1 : stackEnchantments.get(Enchantments.SHARPNESS);
        if (target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) target;
            if (player.isCreative()) {
                target.hurtResistantTime = 0;
                target.attackEntityFrom(DIVINE_DAMAGE_SOURCE, (sharpnessLevel * 0.5F) + 2.5F);
            }
        }
        target.hurtResistantTime = 0;
        target.attackEntityFrom(ARMOR_PIERCING_DAMAGE_SOURCE, ((sharpnessLevel * 0.5F) + 4.5F));
        drainHealth(target);
        return true;
    }

    private void drainHealth(LivingEntity target) {
        if (target.getAttribute(Attributes.MAX_HEALTH).getModifier(SOUL_DAMAGE_MODIFIER) == null) {
            AttributeModifier attributeModifier = new AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage", -0.25, AttributeModifier.Operation.ADDITION);
            target.getAttribute(Attributes.MAX_HEALTH).applyPersistentModifier(attributeModifier);
        }
        else {
            AttributeModifier attributeModifier = new AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage", -0.25 + target.getAttribute(Attributes.MAX_HEALTH).getModifier(SOUL_DAMAGE_MODIFIER).getAmount(), AttributeModifier.Operation.ADDITION);
            target.getAttribute(Attributes.MAX_HEALTH).removePersistentModifier(SOUL_DAMAGE_MODIFIER);
            target.getAttribute(Attributes.MAX_HEALTH).applyPersistentModifier(attributeModifier);
        }
    }

    public static void AnvilUpdateEvent(AnvilUpdateEvent event) {
        if (!event.getPlayer().isServerWorld()) {
            return;
        }
        ItemStack sword = event.getLeft();
        ItemStack book = event.getRight();
        if (sword == null || sword.getItem() != ItemList.Kikoku.get() || book == null || book.getItem() != Items.ENCHANTED_BOOK) {
            return;
        }
        Map<Enchantment, Integer> swordMap = EnchantmentHelper.getEnchantments(sword);
        Map<Enchantment, Integer> bookMap = EnchantmentHelper.getEnchantments(book);
        if (bookMap.isEmpty()) { return; }
        Map<Enchantment, Integer> outputMap = new HashMap<>(swordMap);
        int costCounter = 0;
        for (Map.Entry<Enchantment, Integer> entry : bookMap.entrySet()) {
            Enchantment enchantment = entry.getKey();
            if (enchantment == null) {continue;}
            Integer currentValue = swordMap.get(entry.getKey());
            Integer addValue = entry.getValue();
            if (currentValue == null) {
                outputMap.put(entry.getKey(), addValue);
                costCounter += addValue * 5;
            }
            else {
                int value = Math.min(currentValue + addValue, enchantment.getMaxLevel() * Config.maxKikokuMultiplier.get());
                outputMap.put(entry.getKey(), value);
                costCounter += (currentValue + addValue) * 5;
            }
        }
        event.setCost(costCounter);
        ItemStack enchantedSword = sword.copy();
        EnchantmentHelper.setEnchantments(outputMap, enchantedSword);
        event.setOutput(enchantedSword);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    public static void AnvilRepairEvent(AnvilRepairEvent event) {
        if (!event.getPlayer().getEntityWorld().isRemote) {
            return;
        }
        if (event.getItemResult().getItem() == ItemList.Kikoku.get() && event.getPlayer() instanceof ClientPlayerEntity) {
            event.getPlayer().playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
        }
    }
}