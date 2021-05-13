package onelemonyboi.miniutilities.items.elytrabooster;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.items.MUArmorMaterial;

public class ElytraBooster extends ArmorItem {
    public ElytraBooster(Properties properties) {
        super(MUArmorMaterial.BOOSTER, EquipmentSlotType.CHEST, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isElytraFlying()) {
            Vector3d vector3d = player.getLookVec();
            double d0 = 1.5D;
            double d1 = 0.1D;
            Vector3d vector3d1 = player.getMotion();
            player.setMotion(vector3d1.add(vector3d.x * 0.1D + (vector3d.x * 1.5D - vector3d1.x) * 0.5D, vector3d.y * 0.1D + (vector3d.y * 1.5D - vector3d1.y) * 0.5D, vector3d.z * 0.1D + (vector3d.z * 1.5D - vector3d1.z) * 0.5D));
        }
    }

    // TODO: IMPLEMENT POWER N STUFF
}
