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
import onelemonyboi.miniutilities.trait.traits.BlockTraits.*;

import javax.annotation.Nonnull;

public class BlockBehaviors {
    public static BlockBehaviour drum = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialBaseBlock)
            .staticModel()
            .tileEntity(b -> new DrumTile(((DrumBlock) b).mb))
            .keepNBTOnBreak()
            .with(ModularMaterialTrait.builder().hardness(6).resistance(6).toolType(ToolType.PICKAXE).build())
            .build();

    public static BlockBehaviour laserPort = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .build();

    public static BlockBehaviour laserHub = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .build();

    public static BlockBehaviour mechanicalMiner = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .build();

    public static BlockBehaviour mechanicalPlacer = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .build();

    public static BlockBehaviour quantumQuarry = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .build();

    public static BlockBehaviour solarPanelController = new BlockBehaviour.Builder()
            .composeFrom(BlockPartialBehaviours.partialMaterial)
            .build();
}
