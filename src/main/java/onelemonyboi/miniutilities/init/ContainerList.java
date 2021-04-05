package onelemonyboi.miniutilities.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
<<<<<<< Updated upstream
import onelemonyboi.miniutilities.tileentities.containers.MechanicalMinerContainer;
=======
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.containers.MechanicalMinerContainer;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.containers.MechanicalPlacerContainer;
>>>>>>> Stashed changes

public class ContainerList {
    public static final RegistryObject<ContainerType<MechanicalMinerContainer>> TestContainer = ModRegistry.CONTAINER.register("test_container", () -> IForgeContainerType.create(MechanicalMinerContainer::new));

    public static void register() {}
}
