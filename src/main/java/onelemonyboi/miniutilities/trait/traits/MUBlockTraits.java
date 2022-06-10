package onelemonyboi.miniutilities.trait.traits;

import lombok.Builder;
import lombok.experimental.UtilityClass;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import onelemonyboi.lemonlib.trait.IHasProperty;
import onelemonyboi.lemonlib.trait.Trait;
import onelemonyboi.lemonlib.trait.block.BlockTraits.MaterialTrait;

@UtilityClass
public class MUBlockTraits {
    @Builder
    public static class ModularMaterialTrait extends MaterialTrait {
        final Integer hardness;
        final Integer resistance;
        final boolean requiresTool;
        final boolean isOpaque;

        @Override
        protected void tweakProperties(Properties properties) {
            properties.strength(hardness, resistance).isRedstoneConductor((a, b, c) -> isOpaque);
            if (requiresTool) properties.requiresCorrectToolForDrops();
        }
    }

    public static class RedstoneTrait extends Trait implements IHasProperty {
        @Override
        public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(BlockStateProperties.POWERED);
        }

        @Override
        public BlockState modifyDefaultState(net.minecraft.world.level.block.state.BlockState blockState) {
            return blockState.setValue(BlockStateProperties.POWERED, false);
        }
    }
}
