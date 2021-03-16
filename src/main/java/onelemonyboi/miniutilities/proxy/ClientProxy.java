package onelemonyboi.miniutilities.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import onelemonyboi.miniutilities.renderer.AngelRingRenderer;

import java.util.Map;
import java.util.function.Supplier;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLCommonSetupEvent event) { // GUI/Container Stuff

    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        Map<String, PlayerRenderer> skinMap = Minecraft.getInstance().getRenderManager().getSkinMap();
        for (PlayerRenderer render : new PlayerRenderer[]{skinMap.get("default"), skinMap.get("slim")})
            render.addLayer(new AngelRingRenderer(render));
    }

    @Override
    public void postInit(FMLCommonSetupEvent event) {

    }

    @Override
    public <T extends Entity> void registerEntityRenderer(EntityType<T> entityClass, Supplier<IRenderFactory<T>> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory.get());
    }
}
