package onelemonyboi.miniutilities.startup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.client.ClientRegistry;
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
        ItemBlockRenderTypes.setRenderLayer(BlockList.CursedEarth.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.BlessedEarth.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.BlursedEarth.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.EnderLily.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.FlameLily.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.WhiteLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.LightGrayLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.GrayLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.BlackLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.RedLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.OrangeLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.YellowLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.LimeLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.GreenLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.LightBlueLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.CyanLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.BlueLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.PurpleLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.MagentaLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.PinkLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.BrownLapisCaelestis.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.WoodenSpikes.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.IronSpikes.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.GoldSpikes.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.DiamondSpikes.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.NetheriteSpikes.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockList.EtherealGlass.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.ReverseEtherealGlass.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.GlowingGlass.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.RedstoneGlass.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.DarkGlass.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.DarkEtherealGlass.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockList.DarkReverseEtherealGlass.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockList.LaserHub.get(), RenderType.cutout());

        MenuScreens.register(ContainerList.MinerContainer.get(), MechanicalMinerScreen::new);
        MenuScreens.register(ContainerList.PlacerContainer.get(), MechanicalPlacerScreen::new);
        MenuScreens.register(ContainerList.QuarryContainer.get(), QuantumQuarryScreen::new);

        BlockEntityRenderers.register(TEList.LaserHubTile.get(), LaserHubTESR::new);
    }

    public static void machineRender() {
        EVENT_BUS.addListener(MachineRenderer::blockRenderInfo);
    }
}
