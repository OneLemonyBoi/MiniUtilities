package onelemonyboi.miniutilities.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryContainer;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerContainer;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerContainer;

public class ContainerList {
    public static final net.minecraftforge.registries.RegistryObject<MenuType<MechanicalMinerContainer>> MinerContainer = ModRegistry.CONTAINERS.register("miner_container", () -> IForgeMenuType.create(MechanicalMinerContainer::new));
    public static final net.minecraftforge.registries.RegistryObject<MenuType<MechanicalPlacerContainer>> PlacerContainer = ModRegistry.CONTAINERS.register("placer_container", () -> IForgeMenuType.create(MechanicalPlacerContainer::new));
    public static final RegistryObject<MenuType<QuantumQuarryContainer>> QuarryContainer = ModRegistry.CONTAINERS.register("quarry_container", () -> IForgeMenuType.create(QuantumQuarryContainer::new));


    public static void register() {}
}
