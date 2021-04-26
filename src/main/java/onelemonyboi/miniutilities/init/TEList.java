package onelemonyboi.miniutilities.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelControllerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.MechanicalPlacerTile;

public class TEList {
    public static final RegistryObject<TileEntityType<DrumTile>> DrumTile = ModRegistry.TE.register("drum", () -> TileEntityType.Builder.create(DrumTile::new, BlockList.StoneDrum.get(), BlockList.IronDrum.get(), BlockList.ReinforcedLargeDrum.get(), BlockList.NetheriteReinforcedDrum.get(), BlockList.UnstableDrum.get()).build(null));
    public static final RegistryObject<TileEntityType<MechanicalMinerTile>> MechanicalMinerTile = ModRegistry.TE.register("mechanical_miner", () -> TileEntityType.Builder.create(() -> new MechanicalMinerTile(), BlockList.MechanicalMiner.get()).build(null));
    public static final RegistryObject<TileEntityType<MechanicalPlacerTile>> MechanicalPlacerTile = ModRegistry.TE.register("mechanical_placer", () -> TileEntityType.Builder.create(() -> new MechanicalPlacerTile(), BlockList.MechanicalPlacer.get()).build(null));
    public static final RegistryObject<TileEntityType<QuantumQuarryTile>> QuantumQuarryTile = ModRegistry.TE.register("quantum_quarry", () -> TileEntityType.Builder.create(() -> new QuantumQuarryTile(), BlockList.QuantumQuarry.get()).build(null));

    public static final RegistryObject<TileEntityType<SolarPanelControllerTile>> SolarPanelControllerTile = ModRegistry.TE.register("solar_panel_controller", () -> TileEntityType.Builder.create(() -> new SolarPanelControllerTile(), BlockList.SolarPanelController.get()).build(null));
    public static void register() {}
}
