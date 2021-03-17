package onelemonyboi.miniutilities.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import onelemonyboi.miniutilities.renderer.AngelRingRendererLeft;
import onelemonyboi.miniutilities.renderer.AngelRingRendererRight;
import onelemonyboi.miniutilities.renderer.KikokuRenderer;

import java.util.Map;
import java.util.function.Supplier;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLCommonSetupEvent event) { // GUI/Container Stuff

    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        Map<String, PlayerRenderer> skinMapAngelWingLeft = Minecraft.getInstance().getRenderManager().getSkinMap();
        for (PlayerRenderer render : new PlayerRenderer[]{skinMapAngelWingLeft.get("default"), skinMapAngelWingLeft.get("slim")})
            render.addLayer(new AngelRingRendererLeft(render));

        Map<String, PlayerRenderer> skinMapAngelWingRight = Minecraft.getInstance().getRenderManager().getSkinMap();
        for (PlayerRenderer render : new PlayerRenderer[]{skinMapAngelWingRight.get("default"), skinMapAngelWingRight.get("slim")})
            render.addLayer(new AngelRingRendererRight(render));

        Map<String, PlayerRenderer> skinMapKikoku = Minecraft.getInstance().getRenderManager().getSkinMap();
        for (PlayerRenderer render : new PlayerRenderer[]{skinMapKikoku.get("default"), skinMapKikoku.get("slim")})
            render.addLayer(new KikokuRenderer(render));
    }

    @Override
    public void postInit(FMLCommonSetupEvent event) {

    }

    @Override
    public <T extends Entity> void registerEntityRenderer(EntityType<T> entityClass, Supplier<IRenderFactory<T>> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory.get());
    }
}
