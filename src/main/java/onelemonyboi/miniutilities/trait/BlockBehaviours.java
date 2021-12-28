package onelemonyboi.miniutilities.trait;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.ToolType;
import onelemonyboi.lemonlib.handlers.MUItemStackHandler;
import onelemonyboi.lemonlib.trait.block.BlockBehaviour;
import onelemonyboi.lemonlib.trait.block.BlockPartialBehaviours;
import onelemonyboi.lemonlib.trait.block.BlockTraits;
import onelemonyboi.lemonlib.trait.tile.TilePartialBehaviours;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.drum.DrumTile;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserHubTile;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserPortTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.SolarPanelControllerTile;
import onelemonyboi.miniutilities.trait.traits.BlockTraits.*;

import javax.annotation.Nonnull;

public class BlockBehaviours {
    public static BlockBehaviour drum = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity(b -> new DrumTile(((DrumBlock) b).mb))
            .keepNBTOnBreak()
            .with(ModularMaterialTrait.builder().hardness(6).resistance(6).toolType(ToolType.PICKAXE).requiresTool(true).build())
            .build();

    public static BlockBehaviour solarPanelController = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity(b -> new SolarPanelControllerTile())
            .keepNBTOnBreak()
            .with(ModularMaterialTrait.builder().hardness(4).resistance(4).harvestLevel(1).toolType(ToolType.PICKAXE).requiresTool(true).build())
            .build();

    public static BlockBehaviour laserHub = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity(b -> new LaserHubTile())
            .keepNBTOnBreak()
            .with(ModularMaterialTrait.builder().hardness(4).resistance(4).harvestLevel(1).toolType(ToolType.PICKAXE).requiresTool(true).build())
            .build();

    public static BlockBehaviour laserPort = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity(b -> new LaserPortTile())
            .keepNBTOnBreak()
            .rotation(BlockTraits.RotationType.XYZ)
            .with(ModularMaterialTrait.builder().hardness(4).resistance(4).harvestLevel(1).toolType(ToolType.PICKAXE).requiresTool(true).build())
            .build();

    public static BlockBehaviour mechanicalMiner = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .tileEntity(t -> new MechanicalMinerTile())
            .keepNBTOnBreak()
            .rotation(BlockTraits.RotationType.XYZ)
            .with(ModularMaterialTrait.builder().hardness(3).resistance(3).harvestLevel(1).toolType(ToolType.PICKAXE).requiresTool(true).build())
            .build();

    public static BlockBehaviour mechanicalPlacer = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity(t -> new MechanicalPlacerTile())
            .keepNBTOnBreak()
            .rotation(BlockTraits.RotationType.XYZ)
            .with(ModularMaterialTrait.builder().hardness(3).resistance(3).harvestLevel(1).toolType(ToolType.PICKAXE).requiresTool(true).build())
            .build();

    public static BlockBehaviour quantumQuarry = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .tileEntity(t -> new QuantumQuarryTile())
            .keepNBTOnBreak()
            .with(ModularMaterialTrait.builder().hardness(6).resistance(6).harvestLevel(3).toolType(ToolType.PICKAXE).requiresTool(true).build())
            .build();
}
