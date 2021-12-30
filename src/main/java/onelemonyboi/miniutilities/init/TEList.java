package onelemonyboi.miniutilities.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserHubTile;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserPortTile;
import onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock.RedstoneClockTile;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelControllerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;

public class TEList {
    public static final RegistryObject<TileEntityType<DrumTile>> DrumTile = ModRegistry.TE.register("drum", () -> TileEntityType.Builder.create(DrumTile::new, BlockList.StoneDrum.get(), BlockList.IronDrum.get(), BlockList.ReinforcedLargeDrum.get(), BlockList.NetheriteReinforcedDrum.get(), BlockList.UnstableDrum.get()).build(null));
    public static final RegistryObject<TileEntityType<MechanicalMinerTile>> MechanicalMinerTile = ModRegistry.TE.register("mechanical_miner", () -> TileEntityType.Builder.create(() -> new MechanicalMinerTile(), BlockList.MechanicalMiner.get()).build(null));
    public static final RegistryObject<TileEntityType<MechanicalPlacerTile>> MechanicalPlacerTile = ModRegistry.TE.register("mechanical_placer", () -> TileEntityType.Builder.create(() -> new MechanicalPlacerTile(), BlockList.MechanicalPlacer.get()).build(null));
    public static final RegistryObject<TileEntityType<QuantumQuarryTile>> QuantumQuarryTile = ModRegistry.TE.register("quantum_quarry", () -> TileEntityType.Builder.create(() -> new QuantumQuarryTile(), BlockList.QuantumQuarry.get()).build(null));
    public static final RegistryObject<TileEntityType<SolarPanelControllerTile>> SolarPanelControllerTile = ModRegistry.TE.register("solar_panel_controller", () -> TileEntityType.Builder.create(() -> new SolarPanelControllerTile(), BlockList.SolarPanelController.get()).build(null));
    public static final RegistryObject<TileEntityType<LaserHubTile>> LaserHubTile = ModRegistry.TE.register("laser_hub", () -> TileEntityType.Builder.create(() -> new LaserHubTile(), BlockList.LaserHub.get()).build(null));
    public static final RegistryObject<TileEntityType<LaserPortTile>> LaserPortTile = ModRegistry.TE.register("laser_port", () -> TileEntityType.Builder.create(() -> new LaserPortTile(), BlockList.LaserPort.get()).build(null));    public static void register() {}

    public static final RegistryObject<TileEntityType<RedstoneClockTile>> RedstoneClockTile = ModRegistry.TE.register("redstone_clock", () -> TileEntityType.Builder.create(() -> new RedstoneClockTile(), BlockList.RedstoneClockBlock.get()).build(null));
}
