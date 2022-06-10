package onelemonyboi.miniutilities.proxy;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IProxy {
    void preInit(FMLCommonSetupEvent event);

    void init(FMLCommonSetupEvent event);

    void postInit(FMLCommonSetupEvent event);

    void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event);

    void registerEntityLayers(EntityRenderersEvent.AddLayers event);
}
