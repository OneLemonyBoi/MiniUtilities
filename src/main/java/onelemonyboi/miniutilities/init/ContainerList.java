package onelemonyboi.miniutilities.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.testlmaoidkwhytfthisishere.TestContainer;

public class ContainerList {
    public static final RegistryObject<ContainerType<TestContainer>> TestContainer = ModRegistry.CONTAINER.register("test_container", () -> IForgeContainerType.create(TestContainer::new));

    public static void register() {}
}
