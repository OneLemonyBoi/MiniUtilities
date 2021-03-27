package onelemonyboi.miniutilities.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.tileentities.containers.MechanicalMinerContainer;

public class ContainerList {
    public static final RegistryObject<ContainerType<MechanicalMinerContainer>> TestContainer = ModRegistry.CONTAINER.register("test_container", () -> IForgeContainerType.create(MechanicalMinerContainer::new));

    public static void register() {}
}
