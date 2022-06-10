package onelemonyboi.miniutilities.trait;

import onelemonyboi.lemonlib.trait.block.BlockBehavior;
import onelemonyboi.lemonlib.trait.block.BlockPartialBehaviours;
import onelemonyboi.lemonlib.trait.block.BlockTraits;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumTile;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserHubTile;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserPortTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock.RedstoneClockTile;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelControllerTile;
import onelemonyboi.miniutilities.trait.traits.MUBlockTraits;
import onelemonyboi.miniutilities.trait.traits.MUBlockTraits.*;

public class BlockBehaviors {
    public static BlockBehavior drum = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity((a, b, c) -> new DrumTile(((DrumBlock) a).mb, b, c))
            .with(ModularMaterialTrait.builder().hardness(6).resistance(6).requiresTool(true).build())
            .build();

    public static BlockBehavior solarPanelController = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity((a, b, c) -> new SolarPanelControllerTile(b, c))
            .with(ModularMaterialTrait.builder().hardness(4).resistance(4).requiresTool(true).build())
            .build();

    public static BlockBehavior laserHub = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity((a, b, c) -> new LaserHubTile(b, c))
            .with(ModularMaterialTrait.builder().hardness(4).resistance(4).requiresTool(true).build())
            .build();

    public static BlockBehavior laserPort = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity((a, b, c) -> new LaserPortTile(b, c))
            .rotation(BlockTraits.RotationType.XYZ)
            .with(ModularMaterialTrait.builder().hardness(4).resistance(4).requiresTool(true).build())
            .build();

    public static BlockBehavior mechanicalMiner = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .tileEntity((a, b, c) -> new MechanicalMinerTile(b, c))
            .rotation(BlockTraits.RotationType.XYZ)
            .with(ModularMaterialTrait.builder().hardness(3).resistance(3).requiresTool(true).build())
            .build();

    public static BlockBehavior mechanicalPlacer = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity((a, b, c) -> new MechanicalPlacerTile(b, c))
            .rotation(BlockTraits.RotationType.XYZ)
            .with(ModularMaterialTrait.builder().hardness(3).resistance(3).requiresTool(true).build())
            .build();

    public static BlockBehavior quantumQuarry = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity((a, b, c) -> new QuantumQuarryTile(b, c))
            .with(ModularMaterialTrait.builder().hardness(6).resistance(6).requiresTool(true).build())
            .build();

    public static BlockBehavior redstoneClock = new BlockBehavior.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity((a, b, c) -> new RedstoneClockTile(b, c))
            .with(new MUBlockTraits.RedstoneTrait())
            .with(ModularMaterialTrait.builder().hardness(3).resistance(3).requiresTool(true).isOpaque(false).build())
            .build();
}
