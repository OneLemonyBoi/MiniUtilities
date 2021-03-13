package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import onelemonyboi.miniutilities.items.Kikoku;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.UUID;

import static onelemonyboi.miniutilities.items.Kikoku.*;

public class UnstableSword extends SwordItem {
    UUID ARMOR_PIERCING_MODIFER = UUID.fromString("c7097a94-17f9-481c-bde0-04c12b9d9a23");

    public UnstableSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Nonnull
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);

        ListMultimap<Attribute, AttributeModifier> multimaps = ArrayListMultimap.create();
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            multimaps.put(ARMOR_PIERCING_DAMAGE, new AttributeModifier(ARMOR_PIERCING_MODIFER, "Armor Piercing Damage Modifier", 1D, AttributeModifier.Operation.ADDITION));
        }
        for (Attribute attribute : multimap.keySet()) {
            multimaps.putAll(attribute, multimap.get(attribute));
        }
        return multimaps;
    }
}
