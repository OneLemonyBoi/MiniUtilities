package onelemonyboi.miniutilities.packets;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import onelemonyboi.miniutilities.MiniUtilities;

public class Packet {
    private static final String CHANNEL_NAME = "main";
    private static final String PROTOCOL_VERSION = "2";
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
                RedstoneModeUpdate.class,
                RedstoneModeUpdate::encode,
                RedstoneModeUpdate::decode,
                RedstoneModeUpdate::handle
        );
    }
}
