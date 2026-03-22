package onelemonyboi.miniutilities.startup;

import net.minecraft.client.gui.screens.MenuScreens;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryScreen;
import onelemonyboi.miniutilities.init.ContainerList;
import onelemonyboi.miniutilities.renderer.MachineRenderer;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

public class ClientStuff {
    public static void clientStuff() {
        MenuScreens.register(ContainerList.MinerContainer.get(), MechanicalMinerScreen::new);
        MenuScreens.register(ContainerList.PlacerContainer.get(), MechanicalPlacerScreen::new);
        MenuScreens.register(ContainerList.QuarryContainer.get(), QuantumQuarryScreen::new);
    }

    public static void machineRender() {
        EVENT_BUS.addListener(MachineRenderer::blockRenderInfo);
    }
}
