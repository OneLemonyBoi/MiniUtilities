package onelemonyboi.miniutilities.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserHubTile;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserPortTile;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelControllerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;

import java.util.function.Supplier;

public class TEList {
    public static final RegistryObject<TileEntityType<DrumTile>> DrumTile =
            register("drum", DrumTile::new, BlockList.StoneDrum.get(), BlockList.IronDrum.get(), BlockList.ReinforcedLargeDrum.get(), BlockList.NetheriteReinforcedDrum.get(), BlockList.UnstableDrum.get());
    public static final RegistryObject<TileEntityType<MechanicalMinerTile>> MechanicalMinerTile =
            register("mechanical_miner", MechanicalMinerTile::new, BlockList.MechanicalMiner.get());
    public static final RegistryObject<TileEntityType<MechanicalPlacerTile>> MechanicalPlacerTile =
            register("mechanical_placer", MechanicalPlacerTile::new, BlockList.MechanicalPlacer.get());
    public static final RegistryObject<TileEntityType<QuantumQuarryTile>> QuantumQuarryTile =
            register("quantum_quarry", QuantumQuarryTile::new, BlockList.QuantumQuarry.get());
    public static final RegistryObject<TileEntityType<SolarPanelControllerTile>> SolarPanelControllerTile =
            register("solar_panel_controller", SolarPanelControllerTile::new, BlockList.SolarPanelController.get());
    public static final RegistryObject<TileEntityType<LaserHubTile>> LaserHubTile =
            register("laser_hub", LaserHubTile::new, BlockList.LaserHub.get());
    public static final RegistryObject<TileEntityType<LaserPortTile>> LaserPortTile =
            register("laser_port", LaserPortTile::new, BlockList.LaserPort.get());

    public static void register() { }

    public static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factoryIn, Block... validBlocks) {
        return ModRegistry.TE.register(name, () -> TileEntityType.Builder.create(factoryIn, validBlocks).build(null));
    }
}
