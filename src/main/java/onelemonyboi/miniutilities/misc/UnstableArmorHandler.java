package onelemonyboi.miniutilities.misc;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import onelemonyboi.miniutilities.items.MUArmorMaterial;

public class UnstableArmorHandler {

    //save whether we swat to and/or from unstable armor as an array [to, from]
    private static boolean[] isInfusedArmor(ItemStack to, ItemStack from) {
        boolean[] isInfused = new boolean[2];
        if (to.getItem() instanceof ArmorItem armorTo) {
            isInfused[0] = armorTo.getMaterial().equals(MUArmorMaterial.INFUSEDUNSTABLE);
        }
        if (from.getItem() instanceof ArmorItem armorFrom) {
            isInfused[1] = armorFrom.getMaterial().equals(MUArmorMaterial.INFUSEDUNSTABLE);
        }
        return isInfused;
    }

    public static void unstableArmor(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getSlot() == EquipmentSlot.CHEST
                || event.getSlot() == EquipmentSlot.LEGS
                || event.getSlot() == EquipmentSlot.FEET
                || event.getSlot() == EquipmentSlot.HEAD) {
                boolean[] isInfused = isInfusedArmor(event.getTo(), event.getFrom());
                if (isInfused[0] || isInfused[1]) {
                    long unstableCount = player.getInventory().armor.stream()
                        .filter(x -> x.getItem() instanceof ArmorItem)
                        .map(x -> (ArmorItem) x.getItem())
                        .filter(x -> x.getMaterial().equals(MUArmorMaterial.INFUSEDUNSTABLE))
                        .count();
                    if (unstableCount >= 4) {
                        if (isInfused[0]) {
                            player.getAbilities().mayfly = true;
                            player.getAbilities().flying = true;
                            player.getAbilities().setWalkingSpeed(0.2f);
                            player.onUpdateAbilities();
                        }
                    } else {
                        if (isInfused[1]) {
                            player.getAbilities().mayfly = false;
                            player.getAbilities().flying = false;
                            player.getAbilities().setWalkingSpeed(0.1f);
                            player.onUpdateAbilities();
                        }
                    }
                }
            }
        }
    }
}