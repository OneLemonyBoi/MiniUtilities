package onelemonyboi.miniutilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryScreen;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;
import onelemonyboi.miniutilities.init.EntityList;
import onelemonyboi.miniutilities.renderer.MachineRenderer;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

public class ClientStuff {
    public static void clientStuff() {
        RenderTypeLookup.setRenderLayer(BlockList.CursedEarth.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlessedEarth.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlursedEarth.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.EnderLily.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.FlameLily.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.WhiteLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.LightGrayLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.GrayLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlackLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.RedLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.OrangeLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.YellowLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.LimeLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.GreenLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.LightBlueLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.CyanLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlueLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.PurpleLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.MagentaLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.PinkLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BrownLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.WoodenSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.IronSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.GoldSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.DiamondSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.NetheriteSpikes.get(), RenderType.getCutout());

        ScreenManager.registerFactory(ContainerList.MinerContainer.get(), MechanicalMinerScreen::new);
        ScreenManager.registerFactory(ContainerList.PlacerContainer.get(), MechanicalPlacerScreen::new);
        ScreenManager.registerFactory(ContainerList.QuarryContainer.get(), QuantumQuarryScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityList.SpecialEgg.get(), (manager) -> new SpriteRenderer(manager, Minecraft.getInstance().getItemRenderer()));
    }

    public static void machineRender() {
        EVENT_BUS.addListener(MachineRenderer::blockRenderInfo);
    }
}
