package onelemonyboi.miniutilities.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock.RedstoneClockTile;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelControllerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;

public class TEList {
    public static final RegistryObject<BlockEntityType<DrumTile>> DrumTile = ModRegistry.TE.register("drum", () -> BlockEntityType.Builder.of(DrumTile::new, BlockList.StoneDrum.get(), BlockList.IronDrum.get(), BlockList.ReinforcedLargeDrum.get(), BlockList.NetheriteReinforcedDrum.get(), BlockList.UnstableDrum.get()).build(null));
    public static final RegistryObject<BlockEntityType<MechanicalMinerTile>> MechanicalMinerTile = ModRegistry.TE.register("mechanical_miner", () -> BlockEntityType.Builder.of(MechanicalMinerTile::new, BlockList.MechanicalMiner.get()).build(null));
    public static final RegistryObject<BlockEntityType<MechanicalPlacerTile>> MechanicalPlacerTile = ModRegistry.TE.register("mechanical_placer", () -> BlockEntityType.Builder.of(MechanicalPlacerTile::new, BlockList.MechanicalPlacer.get()).build(null));
    public static final RegistryObject<BlockEntityType<QuantumQuarryTile>> QuantumQuarryTile = ModRegistry.TE.register("quantum_quarry", () -> BlockEntityType.Builder.of(QuantumQuarryTile::new, BlockList.QuantumQuarry.get()).build(null));
    public static final RegistryObject<BlockEntityType<SolarPanelControllerTile>> SolarPanelControllerTile = ModRegistry.TE.register("solar_panel_controller", () -> BlockEntityType.Builder.of(SolarPanelControllerTile::new, BlockList.SolarPanelController.get()).build(null));
    public static final RegistryObject<BlockEntityType<RedstoneClockTile>> RedstoneClockTile = ModRegistry.TE.register("redstone_clock", () -> BlockEntityType.Builder.of(RedstoneClockTile::new, BlockList.RedstoneClockBlock.get()).build(null));

    public static void register() {}
}
