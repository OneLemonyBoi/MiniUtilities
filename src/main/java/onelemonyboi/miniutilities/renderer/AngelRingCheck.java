package onelemonyboi.miniutilities.renderer;

import net.minecraft.world.entity.LivingEntity;
import onelemonyboi.miniutilities.init.ItemList;
import top.theillusivec4.curios.api.CuriosApi;

public class AngelRingCheck {
    public static boolean isBaseEquipped(LivingEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ItemList.BaseAngelRing.get()).isPresent();
    }

    public static boolean isBatEquipped(LivingEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ItemList.BatAngelRing.get()).isPresent();
    }

    public static boolean isGoldEquipped(LivingEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ItemList.GoldAngelRing.get()).isPresent();
    }

    public static boolean isPeacockEquipped(LivingEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ItemList.PeacockAngelRing.get()).isPresent();
    }

    public static boolean isEnderDragonEquipped(LivingEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ItemList.EnderDragonAngelRing.get()).isPresent();
    }

    public static boolean isFeatherEquipped(LivingEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findFirstCurio(playerEntity, ItemList.FeatherAngelRing.get()).isPresent();
    }

    public static boolean isEquipped(LivingEntity playerEntity) {
        return isBaseEquipped(playerEntity) || isBatEquipped(playerEntity) || isGoldEquipped(playerEntity)
                || isPeacockEquipped(playerEntity) || isEnderDragonEquipped(playerEntity) || isFeatherEquipped(playerEntity);
    }
}
