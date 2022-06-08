package onelemonyboi.miniutilities.trait.traits;

import lombok.Builder;
import lombok.experimental.UtilityClass;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import onelemonyboi.lemonlib.trait.IHasProperty;
import onelemonyboi.lemonlib.trait.Trait;
import onelemonyboi.lemonlib.trait.block.BlockTraits.MaterialTrait;

@UtilityClass
public class MUBlockTraits {
    @Builder
    public static class ModularMaterialTrait extends MaterialTrait {
        final Integer hardness;
        final Integer resistance;
        final ToolType toolType;
        final Integer harvestLevel;
        final boolean requiresTool;
        final boolean isOpaque;

        @Override
        protected void tweakProperties(AbstractBlock.Properties properties) {
            properties.strength(hardness, resistance).harvestTool(toolType).isRedstoneConductor((a, b, c) -> isOpaque);
            if (requiresTool) properties.requiresCorrectToolForDrops();
        }
    }

    public static class RedstoneTrait extends Trait implements IHasProperty {
        @Override
        public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
            builder.add(BlockStateProperties.POWERED);
        }

        @Override
        public BlockState modifyDefaultState(BlockState blockState) {
            return blockState.setValue(BlockStateProperties.POWERED, false);
        }
    }
}
