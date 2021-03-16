package onelemonyboi.miniutilities.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.function.Supplier;

public class ServerProxy implements IProxy {
    @Override
    public void preInit(FMLCommonSetupEvent event) {

    }

    @Override
    public void init(FMLCommonSetupEvent event) {

    }

    @Override
    public void postInit(FMLCommonSetupEvent event) {

    }

    @Override
    public <T extends Entity> void registerEntityRenderer(EntityType<T> entityClass, Supplier<IRenderFactory<T>> renderFactory) {

    }
}
