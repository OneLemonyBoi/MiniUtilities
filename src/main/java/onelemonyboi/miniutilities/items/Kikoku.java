package onelemonyboi.miniutilities.items;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import onelemonyboi.miniutilities.world.Configs;

import javax.annotation.Nonnull;
import javax.smartcardio.ATR;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Kikoku extends SwordItem {
    public static Attribute DIVINE_DAMAGE = new RangedAttribute("miniutilities.divinedamage", 0.0D, 0.0D, Double.MAX_VALUE);
    public static Attribute ARMOR_PIERCING_DAMAGE = new RangedAttribute("miniutilities.armorpiercingdamage", 0.0D, 0.0D, Double.MAX_VALUE);
    public static Attribute SOUL_DAMAGE = new RangedAttribute("miniutilities.souldamage", 0.0D, 0.0D, Double.MAX_VALUE);
    public static UUID SOUL_DAMAGE_MODIFIER = UUID.fromString("d2928c01-5d7d-41c5-bd3a-9ca8f43c8ff8");

    public static final DamageSource DIVINE_DAMAGE_SOURCE = (new DamageSource("divineDamage")).setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute();
    public static final DamageSource ARMOR_PIERCING_DAMAGE_SOURCE = (new DamageSource("divineDamage")).setDamageBypassesArmor().setDamageIsAbsolute();

    public TextureAtlasSprite sprite;
    private ModelRenderer.ModelBox sword;

    public Kikoku(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
        MinecraftForge.EVENT_BUS.register(new OPAnvilHandler(this));
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);

        ListMultimap<Attribute, AttributeModifier> multimaps = ArrayListMultimap.create();
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            multimaps.put(ARMOR_PIERCING_DAMAGE, new AttributeModifier(Item.ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 3D, AttributeModifier.Operation.ADDITION));
            multimaps.put(DIVINE_DAMAGE, new AttributeModifier(Item.ATTACK_DAMAGE_MODIFIER, "God Damage Modifier", 2, AttributeModifier.Operation.ADDITION));
            multimaps.put(SOUL_DAMAGE, new AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage Modifier", 10 / 39.0, AttributeModifier.Operation.ADDITION));
        }
        for (Attribute attribute: multimap.keySet()) {
            multimaps.putAll(attribute, multimap.get(attribute));
        }
        return multimaps;
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
                target.attackEntityFrom(DIVINE_DAMAGE_SOURCE, 3);
        }
        else {
            target.attackEntityFrom(ARMOR_PIERCING_DAMAGE_SOURCE, 4);
        }
        target.setHealth(target.getHealth() - 0.25f);
        return true;
    }

    private void drainHealth(LivingEntity target) {
        double health = 0;
        AttributeModifier attributeModifier = target.getAttribute(Attributes.MAX_HEALTH).getModifier(SOUL_DAMAGE_MODIFIER);
        if (attributeModifier != null) {
            health = attributeModifier.getAmount();
            if (health == -1) return;
        }

        health -= 1 / 39F;

        if (health < -1) {health = -1;}

        target.getAttribute(Attributes.MAX_HEALTH).applyPersistentModifier(new AttributeModifier(SOUL_DAMAGE_MODIFIER, "Soul Damage", health, AttributeModifier.Operation.ADDITION));
        if (health <= -1) {
            target.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
        }

    }

    public static class OPAnvilHandler {

        @Nonnull
        private final Item item;

        public OPAnvilHandler(@Nonnull Item item) {
            this.item = item;
        }

        @SubscribeEvent
        public void anvil(AnvilUpdateEvent event) {
            ItemStack left = event.getLeft();
            ItemStack right = event.getRight();
            if (left == null || left.getItem() != item || right == null)
                return;

            Map<Enchantment, Integer> map1 = EnchantmentHelper.getEnchantments(left);
            Map<Enchantment, Integer> map2 = EnchantmentHelper.getEnchantments(right);
            if (map2.isEmpty()) return;

            Map<Enchantment, Integer> map3 = new HashMap<>(map1);

            int cost = 0;

            for (Map.Entry<Enchantment, Integer> entry : map2.entrySet()) {
                Enchantment enchantment = entry.getKey();
                if (enchantment == null) continue;

                Integer curValue = map1.get(entry.getKey());
                Integer addValue = entry.getValue();
                if (curValue == null) {
                    cost += addValue;
                    map3.put(entry.getKey(), addValue);
                } else {
                    int value = Math.min(curValue + addValue, Configs.max_kikoku_level.get());
                    cost += value - curValue;
                    map3.put(entry.getKey(), value);
                }
            }

            event.setCost(cost * 2);

            ItemStack copy = left.copy();
            EnchantmentHelper.setEnchantments(map3, copy);
            event.setOutput(copy);
        }
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