package onelemonyboi.miniutilities.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.function.Supplier;

public interface IProxy {
    void preInit(FMLCommonSetupEvent event);

    void init(FMLCommonSetupEvent event);

    void postInit(FMLCommonSetupEvent event);

    <T extends Entity> void registerEntityRenderer(EntityType<T> entityClass, Supplier<IRenderFactory<T>> renderFactory);
}
