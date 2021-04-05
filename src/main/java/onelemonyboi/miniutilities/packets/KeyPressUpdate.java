package onelemonyboi.miniutilities.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyPressUpdate {
    public static Boolean isKeyPressed = false;
    Boolean keyPressed;

    public KeyPressUpdate(Boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public static void encode(KeyPressUpdate packet, PacketBuffer buf) {
        buf.writeBoolean(packet.keyPressed);
    }

    public static KeyPressUpdate decode(PacketBuffer buf) {
        return new KeyPressUpdate(buf.readBoolean());
    }

    public static void handle(KeyPressUpdate msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()-> {
            isKeyPressed = msg.keyPressed;
        });
    }
}
