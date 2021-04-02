package onelemonyboi.miniutilities.items;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.world.Config;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Kikoku extends SwordItem {
    public static Attribute DIVINE_DAMAGE = new RangedAttribute("attribute.miniutilities.divinedamage", 0.0D, 0.0D, Double.MAX_VALUE);
    public static Attribute ARMOR_PIERCING_DAMAGE = new RangedAttribute("attribute.miniutilities.armorpiercingdamage", 0.0D, 0.0D, Double.MAX_VALUE);
    public static Attribute SOUL_DAMAGE = new RangedAttribute("attribute.miniutilities.souldamage", 0.0D, 0.0D, Double.MAX_VALUE);
    public static UUID SOUL_DAMAGE_MODIFIER = UUID.fromString("d2928c01-5d7d-41c5-bd3a-9ca8f43c8ff8");

    public static final DamageSource DIVINE_DAMAGE_SOURCE = (new DamageSource("divineDamage")).setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute();
    public static final DamageSource ARMOR_PIERCING_DAMAGE_SOURCE = (new DamageSource("divineDamage")).setDamageBypassesArmor().setDamageIsAbsolute();

    public Kikoku(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);

        ListMultimap<Attribute, AttributeModifier> multimaps = ArrayListMultimap.create();
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            multimaps.put(ARMOR_PIERCING_DAMAGE, new AttributeModifier(Item.ATTACK_DAMAGE_MODIFIER, "Armor Piercing Damage Modifier", 3D, AttributeModifier.Operation.ADDITION));
            multimaps.put(DIVINE_DAMAGE, new AttributeModifier(Item.ATTACK_DAMAGE_MODIFIER, "God Damage Modifier", 2, AttributeModifier.Operation.ADDITION));
            multimaps.put(SOUL_DAMAGE, new AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage Modifier", 0.25, AttributeModifier.Operation.ADDITION));
        }
        for (Attribute attribute : multimap.keySet()) {
            multimaps.putAll(attribute, multimap.get(attribute));
        }
        return multimaps;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target == null || !target.canBeAttackedWithItem()) return false;
        if (attacker.world.isRemote) return false;

        if (target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) target;
            if (player.isCreative() && target.isInvulnerableTo(DamageSource.ANVIL))
                target.attackEntityFrom(DIVINE_DAMAGE_SOURCE, 3);
        } else {
            target.attackEntityFrom(ARMOR_PIERCING_DAMAGE_SOURCE, 4);
        }
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
        ItemStack sword = event.getLeft();
        ItemStack book = event.getRight();
        if (sword == null || sword.getItem() != ItemList.Kikoku.get() || book == null) {
            return;
        }

        Map<Enchantment, Integer> swordMap = EnchantmentHelper.getEnchantments(sword);
        Map<Enchantment, Integer> bookMap = EnchantmentHelper.getEnchantments(book);
        if (bookMap.isEmpty()) {
            return;
        }
        Map<Enchantment, Integer> outputMap = new HashMap<>(swordMap);
        int costCounter = 0;

        for (Map.Entry<Enchantment, Integer> entry : bookMap.entrySet()) {
            Enchantment enchantment = entry.getKey();
            if (enchantment == null) {continue;}
            Integer curValue = swordMap.get(entry.getKey());
            Integer addValue = entry.getValue();
            if (curValue == null) {
                outputMap.put(entry.getKey(), addValue);
            } else {
                int value = Math.min(curValue + addValue, enchantment.getMaxLevel() * Config.max_kikoku_multiplier.get());
                outputMap.put(entry.getKey(), value);
            }
            costCounter += enchantment.getMaxLevel() * 5;

        }
        event.setCost(costCounter);

        ItemStack enchantedSword = sword.copy();
        EnchantmentHelper.setEnchantments(outputMap, enchantedSword);
        event.setOutput(enchantedSword);
    }
}

/*

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target == null || !target.canBeAttackedWithItem()) return false;
        if (attacker.world.isRemote) return false;

        boolean flag = !target.isInvulnerableTo(DamageSource.ANVIL);
        boolean cKill = false;
        if (target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) target;
            if (player.isCreative() && target.isInvulnerableTo(DamageSource.ANVIL))
                target.attackEntityFrom(DamageSource.OUT_OF_WORLD, 3);
        }
        else {
            target.attackEntityFrom(DamageSource.OUT_OF_WORLD, 4);
        }
        target.setHealth(target.getHealth() - 0.25f);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("miniutilities.armorpiercing"));
        tooltip.add(new TranslationTextComponent("miniutilities.divinedamage"));
        tooltip.add(new TranslationTextComponent("miniutilities.permanenthealthloss").mergeStyle(TextFormatting.BLUE));
    }
*/