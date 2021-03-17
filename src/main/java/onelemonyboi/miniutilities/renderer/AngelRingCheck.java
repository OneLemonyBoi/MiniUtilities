package onelemonyboi.miniutilities.renderer;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import onelemonyboi.miniutilities.init.ItemList;
import top.theillusivec4.curios.api.CuriosApi;

public class AngelRingCheck {
    public static boolean isBaseEquipped(AbstractClientPlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.BaseAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isBatEquipped(AbstractClientPlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.BatAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isGoldEquipped(AbstractClientPlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.GoldAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isPeacockEquipped(AbstractClientPlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.PeacockAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isEnderDragonEquipped(AbstractClientPlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.EnderDragonAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isFeatherEquipped(AbstractClientPlayerEntity playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.FeatherAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isEquipped(AbstractClientPlayerEntity playerEntity) {
        return isBaseEquipped(playerEntity) || isBatEquipped(playerEntity) || isGoldEquipped(playerEntity)
                || isPeacockEquipped(playerEntity) || isEnderDragonEquipped(playerEntity) || isFeatherEquipped(playerEntity);
    }
}
