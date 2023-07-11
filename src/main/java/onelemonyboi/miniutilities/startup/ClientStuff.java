package onelemonyboi.miniutilities.startup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
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
        MenuScreens.register(ContainerList.MinerContainer.get(), MechanicalMinerScreen::new);
        MenuScreens.register(ContainerList.PlacerContainer.get(), MechanicalPlacerScreen::new);
        MenuScreens.register(ContainerList.QuarryContainer.get(), QuantumQuarryScreen::new);

        BlockEntityRenderers.register(TEList.LaserHubTile.get(), LaserHubTESR::new);
    }

    public static void machineRender() {
        EVENT_BUS.addListener(MachineRenderer::blockRenderInfo);
    }
}
