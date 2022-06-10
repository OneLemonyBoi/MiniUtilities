package onelemonyboi.miniutilities.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import onelemonyboi.miniutilities.entities.MagicalEggEntity;
import onelemonyboi.miniutilities.init.EntityList;
import onelemonyboi.miniutilities.renderer.AngelRingRendererLeft;
import onelemonyboi.miniutilities.renderer.AngelRingRendererRight;
import onelemonyboi.miniutilities.renderer.KikokuRenderer;

import java.util.Map;
import java.util.function.Supplier;

public class ClientProxy implements IProxy {
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
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityList.SpecialEgg.get(), ThrownItemRenderer::new);
    }

    @Override
    public void registerEntityLayers(EntityRenderersEvent.AddLayers event) {
        EntityRenderer<? extends Player> def = event.getSkin("default");
        EntityRenderer<? extends Player> slim = event.getSkin("slim");

        if (def instanceof PlayerRenderer playerRendererA) {
            playerRendererA.addLayer(new AngelRingRendererLeft(playerRendererA));
            playerRendererA.addLayer(new AngelRingRendererRight(playerRendererA));
            playerRendererA.addLayer(new KikokuRenderer(playerRendererA));
        }
        if (slim instanceof PlayerRenderer playerRendererB) {
            playerRendererB.addLayer(new AngelRingRendererLeft(playerRendererB));
            playerRendererB.addLayer(new AngelRingRendererRight(playerRendererB));
            playerRendererB.addLayer(new KikokuRenderer(playerRendererB));
        }
    }
}
