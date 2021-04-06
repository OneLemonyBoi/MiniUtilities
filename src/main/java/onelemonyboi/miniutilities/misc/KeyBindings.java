package onelemonyboi.miniutilities.misc;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {
    public static KeyBinding blockInfo = new KeyBinding("key.info", 342, "key.gameplay");
    public static void register() {
        ClientRegistry.registerKeyBinding(blockInfo);
    }
}
