package onelemonyboi.miniutilities.packets;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import onelemonyboi.miniutilities.MiniUtilities;

public class Packet {
    private static final String CHANNEL_NAME = "main";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MiniUtilities.MOD_ID, CHANNEL_NAME),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void main() {
        int id = 0;
        INSTANCE.registerMessage(
                id++,
                KeyPressUpdate.class,
                KeyPressUpdate::encode,
                KeyPressUpdate::decode,
                KeyPressUpdate::handle
        );
    }

    public static void updateKey(Boolean key) {
        INSTANCE.sendToServer(new KeyPressUpdate(key));
    }
}
