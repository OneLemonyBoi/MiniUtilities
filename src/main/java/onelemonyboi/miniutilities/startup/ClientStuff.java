package onelemonyboi.miniutilities.startup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserHubTESR;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryScreen;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;
import onelemonyboi.miniutilities.init.EntityList;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.renderer.MachineRenderer;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

public class ClientStuff {
    public static void clientStuff() {
        RenderTypeLookup.setRenderLayer(BlockList.CursedEarth.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlessedEarth.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlursedEarth.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.EnderLily.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.FlameLily.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.WhiteLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.LightGrayLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.GrayLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlackLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.RedLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.OrangeLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.YellowLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.LimeLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.GreenLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.LightBlueLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.CyanLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlueLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.PurpleLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.MagentaLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.PinkLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.BrownLapisCaelestis.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.WoodenSpikes.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.IronSpikes.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.GoldSpikes.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.DiamondSpikes.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.NetheriteSpikes.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(BlockList.EtherealGlass.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.ReverseEtherealGlass.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.GlowingGlass.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.RedstoneGlass.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.DarkGlass.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.DarkEtherealGlass.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockList.DarkReverseEtherealGlass.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(BlockList.LaserHub.get(), RenderType.cutout());

        ScreenManager.register(ContainerList.MinerContainer.get(), MechanicalMinerScreen::new);
        ScreenManager.register(ContainerList.PlacerContainer.get(), MechanicalPlacerScreen::new);
        ScreenManager.register(ContainerList.QuarryContainer.get(), QuantumQuarryScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityList.SpecialEgg.get(), (manager) -> new SpriteRenderer(manager, Minecraft.getInstance().getItemRenderer()));

        ClientRegistry.bindTileEntityRenderer(TEList.LaserHubTile.get(), LaserHubTESR::new);
    }

    public static void machineRender() {
        EVENT_BUS.addListener(MachineRenderer::blockRenderInfo);
    }
}
