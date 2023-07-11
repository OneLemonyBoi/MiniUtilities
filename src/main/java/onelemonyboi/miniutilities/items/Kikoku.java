package onelemonyboi.miniutilities.items;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageSource;
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

    public static final DamageSource DIVINE_DAMAGE_SOURCE = (new DamageSource("divineDamage")).bypassArmor().bypassInvul().bypassMagic();
    public static final net.minecraft.world.damagesource.DamageSource ARMOR_PIERCING_DAMAGE_SOURCE = (new net.minecraft.world.damagesource.DamageSource("armourPiercingDamage")).bypassArmor().bypassMagic();

    public Kikoku(Tier tier, int attackDamageIn, float attackSpeedIn, net.minecraft.world.item.Item.Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Nonnull
    @Override
    public Multimap<net.minecraft.world.entity.ai.attributes.Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        Multimap<net.minecraft.world.entity.ai.attributes.Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> multimap = super.getDefaultAttributeModifiers(equipmentSlot);
        ListMultimap<Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> multimaps = ArrayListMultimap.create();
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            multimaps.put(AttributeList.ArmorPiercingDamage.get(), new net.minecraft.world.entity.ai.attributes.AttributeModifier(net.minecraft.world.item.Item.BASE_ATTACK_DAMAGE_UUID, "Armor Piercing Damage Modifier", 3, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
            multimaps.put(AttributeList.DivineDamage.get(), new net.minecraft.world.entity.ai.attributes.AttributeModifier(Item.BASE_ATTACK_DAMAGE_UUID, "Divine Damage Modifier", 1, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
            multimaps.put(AttributeList.SoulDamage.get(), new net.minecraft.world.entity.ai.attributes.AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage Modifier", 0.25, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
        }
        for (net.minecraft.world.entity.ai.attributes.Attribute attribute : multimap.keySet()) {
            multimaps.putAll(attribute, multimap.get(attribute));
        }
        return multimaps;
    }

    @Override
    public boolean hurtEnemy(net.minecraft.world.item.ItemStack stack, LivingEntity target, net.minecraft.world.entity.LivingEntity attacker) {
        if (target == null || !target.isAttackable() || attacker.level.isClientSide) {
            return false;
        }
        Map<net.minecraft.world.item.enchantment.Enchantment, Integer> stackEnchantments = EnchantmentHelper.getEnchantments(stack);
        Integer sharpnessLevel = stackEnchantments.get(Enchantments.SHARPNESS) == null ? -1 : stackEnchantments.get(Enchantments.SHARPNESS);
        if (target instanceof Player) {
            Player player = (Player) target;
            if (player.isCreative()) {
                target.invulnerableTime = 0;
                target.hurt(DIVINE_DAMAGE_SOURCE, (sharpnessLevel * 0.5F) + 2.5F);
            }
        }
        target.invulnerableTime = 0;
        target.hurt(ARMOR_PIERCING_DAMAGE_SOURCE, ((sharpnessLevel * 0.5F) + 4.5F));
        drainHealth(target);
        return true;
    }

    private void drainHealth(LivingEntity target) {
        if (target.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getModifier(SOUL_DAMAGE_MODIFIER) == null) {
            net.minecraft.world.entity.ai.attributes.AttributeModifier attributeModifier = new net.minecraft.world.entity.ai.attributes.AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage", -0.25, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION);
            target.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(attributeModifier);
        }
        else {
            net.minecraft.world.entity.ai.attributes.AttributeModifier attributeModifier = new net.minecraft.world.entity.ai.attributes.AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage", -0.25 + target.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getModifier(SOUL_DAMAGE_MODIFIER).getAmount(), net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION);
            target.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).removePermanentModifier(SOUL_DAMAGE_MODIFIER);
            target.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).addPermanentModifier(attributeModifier);
        }
    }

    public static void AnvilUpdateEvent(AnvilUpdateEvent event) {
        if (!event.getPlayer().isEffectiveAi()) {
            return;
        }
        net.minecraft.world.item.ItemStack sword = event.getLeft();
        ItemStack book = event.getRight();
        if (sword == null || sword.getItem() != ItemList.Kikoku.get() || book == null || book.getItem() != Items.ENCHANTED_BOOK) {
            return;
        }
        Map<net.minecraft.world.item.enchantment.Enchantment, Integer> swordMap = net.minecraft.world.item.enchantment.EnchantmentHelper.getEnchantments(sword);
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
        net.minecraft.world.item.ItemStack enchantedSword = sword.copy();
        EnchantmentHelper.setEnchantments(outputMap, enchantedSword);
        event.setOutput(enchantedSword);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    public static void AnvilRepairEvent(AnvilRepairEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide) {
            return;
        }
        if (event.getOutput().getItem() == ItemList.Kikoku.get() && event.getEntity() instanceof AbstractClientPlayer) {
            event.getEntity().playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
        }
    }
}