package onelemonyboi.miniutilities.trait.traits;

import lombok.Builder;
import net.minecraft.block.AbstractBlock;
import net.minecraftforge.common.ToolType;
import onelemonyboi.lemonlib.trait.block.BlockTraits.MaterialTrait;

public class BlockTraits {

    @Builder
    public static class ModularMaterialTrait extends MaterialTrait {
        final Integer hardness;
        final Integer resistance;
        final ToolType toolType;
        final Integer harvestLevel;
        final boolean requiresTool;

        @Override
        protected void tweakProperties(AbstractBlock.Properties properties) {
            properties.hardnessAndResistance(hardness, resistance).harvestTool(toolType);
            if (requiresTool) properties.setRequiresTool();
        }
    }
}
