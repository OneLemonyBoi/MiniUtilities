package onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock;

import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviors;
import org.jetbrains.annotations.Nullable;

public class RedstoneClockBlock extends BlockBase {
    public RedstoneClockBlock() {
        super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(Material.METAL), BlockBehaviors.redstoneClock);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(level, type, TEList.RedstoneClockTile.get(), RedstoneClockTile::serverTick, null);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(BlockStateProperties.POWERED) ? 0 : ((RedstoneClockTile) blockAccess.getBlockEntity(pos)).power;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }
}
