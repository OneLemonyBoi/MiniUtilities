package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import onelemonyboi.miniutilities.base.StandardBlock;

public class BaseBlock extends StandardBlock {

    // Tool and Int
    public BaseBlock(Material material, Integer hardness, Integer resistance, Integer harvestLevel, ToolType toolType)
    {
        super(Properties
                .create(material)
                .hardnessAndResistance(hardness, resistance)
                .harvestLevel(harvestLevel)
                .harvestTool(toolType));
    }

    // Tool and Float
    public BaseBlock(Material material, Float hardness, Float resistance, Integer harvestLevel, ToolType toolType)
    {
        super(Properties
                .create(material)
                .hardnessAndResistance(hardness, resistance)
                .harvestLevel(harvestLevel)
                .harvestTool(toolType));
    }

    // No Tool and Int
    public BaseBlock(Material material, Integer hardness, Integer resistance, Integer harvestLevel)
    {
        super(Properties
                .create(material)
                .hardnessAndResistance(hardness, resistance)
                .harvestLevel(harvestLevel));
    }

    // No Tool and Float
    public BaseBlock(Material material, Float hardness, Float resistance, Integer harvestLevel)
    {
        super(Properties
                .create(material)
                .hardnessAndResistance(hardness, resistance)
                .harvestLevel(harvestLevel));
    }
}
