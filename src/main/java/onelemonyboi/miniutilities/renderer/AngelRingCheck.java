package onelemonyboi.miniutilities.renderer;

import net.minecraft.client.player.AbstractClientPlayer;
import onelemonyboi.miniutilities.init.ItemList;
import top.theillusivec4.curios.api.CuriosApi;

public class AngelRingCheck {
    public static boolean isBaseEquipped(AbstractClientPlayer playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.BaseAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isBatEquipped(AbstractClientPlayer playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.BatAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isGoldEquipped(AbstractClientPlayer playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.GoldAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isPeacockEquipped(AbstractClientPlayer playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.PeacockAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isEnderDragonEquipped(AbstractClientPlayer playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.EnderDragonAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isFeatherEquipped(AbstractClientPlayer playerEntity) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.FeatherAngelRing.get(), playerEntity).isPresent();
    }

    public static boolean isEquipped(AbstractClientPlayer playerEntity) {
        return isBaseEquipped(playerEntity) || isBatEquipped(playerEntity) || isGoldEquipped(playerEntity)
                || isPeacockEquipped(playerEntity) || isEnderDragonEquipped(playerEntity) || isFeatherEquipped(playerEntity);
    }
}
