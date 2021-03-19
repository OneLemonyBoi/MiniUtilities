package onelemonyboi.miniutilities.init;


import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.tileentities.DrumTile;

import static onelemonyboi.miniutilities.MiniUtilities.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TERegister {
    @SubscribeEvent
    public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                DrumTile.TYPE.setRegistryName(new ResourceLocation(MOD_ID, "drum"))
        );
    }
}
