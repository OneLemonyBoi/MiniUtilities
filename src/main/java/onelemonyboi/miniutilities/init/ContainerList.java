package onelemonyboi.miniutilities.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.tileentities.containers.MechanicalMinerContainer;
import onelemonyboi.miniutilities.tileentities.containers.MechanicalPlacerContainer;

public class ContainerList {
    public static final RegistryObject<ContainerType<MechanicalMinerContainer>> MinerContainer = ModRegistry.CONTAINER.register("miner_container", () -> IForgeContainerType.create(MechanicalMinerContainer::new));
    public static final RegistryObject<ContainerType<MechanicalPlacerContainer>> PlacerContainer = ModRegistry.CONTAINER.register("placer_container", () -> IForgeContainerType.create(MechanicalPlacerContainer::new));

    public static void register() {}
}
