package onelemonyboi.miniutilities.misc;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import onelemonyboi.miniutilities.packets.KeyPressUpdate;
import onelemonyboi.miniutilities.packets.Packet;

public class KeyBindingsHandler {
    public static Boolean keyBindingPressed = false;
    public static void keybinds(TickEvent.PlayerTickEvent event) {
        if (event.player.world.isRemote && KeyBindings.preventChange.isKeyDown()) {
            keyBindingPressed = !keyBindingPressed;
            Packet.INSTANCE.sendToServer(new KeyPressUpdate(keyBindingPressed));
        }
    }
}