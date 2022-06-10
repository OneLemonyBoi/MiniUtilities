package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import com.google.common.collect.Lists;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviors;

import javax.annotation.Nullable;
import java.util.List;

public class SolarPanelController extends BlockBase {
    public SolarPanelController() {
        super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(Material.METAL), BlockBehaviors.solarPanelController);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(level, type, TEList.SolarPanelControllerTile.get(), SolarPanelControllerTile::serverTick, null);
    }

    public boolean controllerPresent(Level world, BlockPos pos, List<BlockPos> blockPosList) {
        for (Direction d : Direction.Plane.HORIZONTAL) {
            if (!world.isAreaLoaded(pos.relative(d), 1) || blockPosList.contains(pos.relative(d))) continue;
            BlockState state = world.getBlockState(pos.relative(d));
            if (state.getBlock() instanceof SolarPanelBlock || state.getBlock() instanceof LunarPanelBlock) {
                blockPosList.add(pos.relative(d));
                return controllerPresent(world, pos.relative(d), blockPosList);
            }
            if (state.getBlock() instanceof SolarPanelController) return true;
        }
        return false;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (controllerPresent(world, pos, Lists.newArrayList(pos))) world.destroyBlock(pos, true);
        super.setPlacedBy(world, pos, state, placer, stack);
    }
}