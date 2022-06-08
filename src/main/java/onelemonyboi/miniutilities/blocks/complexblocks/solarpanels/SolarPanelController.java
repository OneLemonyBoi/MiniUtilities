package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.trait.BlockBehaviours;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.block.AbstractBlock.Properties;

public class SolarPanelController extends BlockBase {
    public SolarPanelController() {
        super(Properties.of(Material.METAL), BlockBehaviours.solarPanelController);
    }

    public boolean controllerPresent(World world, BlockPos pos, List<BlockPos> blockPosList) {
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
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (controllerPresent(world, pos, Lists.newArrayList(pos))) world.destroyBlock(pos, true);
        super.setPlacedBy(world, pos, state, placer, stack);
    }
}