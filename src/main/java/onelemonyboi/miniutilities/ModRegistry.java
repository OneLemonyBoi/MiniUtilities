package onelemonyboi.miniutilities;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;

public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MiniUtilities.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MiniUtilities.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MiniUtilities.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MiniUtilities.MOD_ID);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        TE.register(modEventBus);
        CONTAINER.register(modEventBus);

        BlockList.register();
        ItemList.register();
        TEList.register();
        ContainerList.register();
    }
}
