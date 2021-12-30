package onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.trait.BlockBehaviours;
import onelemonyboi.miniutilities.trait.traits.MUBlockTraits;

public class RedstoneClockBlock extends BlockBase {
    public RedstoneClockBlock() {
        super(Properties.create(Material.IRON), BlockBehaviours.redstoneClock);
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.get(BlockStateProperties.POWERED) ? 0 : ((RedstoneClockTile) blockAccess.getTileEntity(pos)).power;
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return true;
    }
}
