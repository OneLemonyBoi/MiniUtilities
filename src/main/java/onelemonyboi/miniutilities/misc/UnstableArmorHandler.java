package onelemonyboi.miniutilities.misc;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import onelemonyboi.miniutilities.items.MUArmorMaterial;

import java.util.UUID;

import static onelemonyboi.miniutilities.renderer.AngelRingCheck.isEquipped;

public class UnstableArmorHandler {
    private static final UUID SPEED_BOOST_ID = UUID.fromString("56df70de-2640-4698-906d-63bd2aae0ef2");
    private static final AttributeModifier SPEED_BOOST = new AttributeModifier(SPEED_BOOST_ID, "Infused Armor Speed Boost", 0.1, AttributeModifier.Operation.ADDITION);

    public static void unstableArmor(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            // if not armour slot, return
            if (!event.getSlot().isArmor()) return;

            long unstableCount = player.getInventory().armor.stream()
                    .filter(x -> x.getItem() instanceof ArmorItem)
                    .map(x -> (ArmorItem) x.getItem())
                    .filter(x -> x.getMaterial().equals(MUArmorMaterial.INFUSEDUNSTABLE))
                    .count();

            if (event.getFrom().getItem() instanceof ArmorItem) {
                ArmorItem armorItem = (ArmorItem) event.getFrom().getItem();

                if (armorItem.getMaterial().equals(MUArmorMaterial.INFUSEDUNSTABLE) && unstableCount < 4) {
                    if (!player.isCreative() && !player.isSpectator() && !isEquipped(player)) {
                        player.getAbilities().mayfly = false;
                        player.getAbilities().flying = false;
                    }
                    if (player.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(SPEED_BOOST_ID) != null) {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_BOOST);
                    }
                    player.onUpdateAbilities();
                }
            }
            if (event.getTo().getItem() instanceof ArmorItem) {
                ArmorItem armorItem = (ArmorItem) event.getTo().getItem();

                if (armorItem.getMaterial().equals(MUArmorMaterial.INFUSEDUNSTABLE) && unstableCount >= 4) {
                    player.getAbilities().mayfly = true;
                    player.getAbilities().flying = true;
                    if (player.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(SPEED_BOOST_ID) == null) {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(SPEED_BOOST);
                    }

                    player.onUpdateAbilities();
                }
            }
        }
    }
}