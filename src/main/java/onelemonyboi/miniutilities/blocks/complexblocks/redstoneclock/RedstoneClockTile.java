package onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock;

import net.minecraft.block.LeverBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.Arrays;

public class RedstoneClockTile extends TileBase implements ITickableTileEntity {
    @SaveInNBT(key = "power")
    int power;

    public RedstoneClockTile() {
        super(TEList.RedstoneClockTile.get(), TileBehaviors.base);
    }

    @Override
    public void tick() {
        if (world.isRemote()) return;
        if (isPowered()) {
            world.setBlockState(getPos(), world.getBlockState(getPos()).with(BlockStateProperties.POWERED, true));
            power = 0;
            return;
        }
        else {
            world.setBlockState(getPos(), world.getBlockState(getPos()).with(BlockStateProperties.POWERED, false));
        }

        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
        world.notifyNeighborsOfStateChange(pos, this.getBlockState().getBlock());

        boolean enablePower = Util.milliTime() / 50 % 20 == 0;
        power = enablePower ? 15 : 0;
    }

    public boolean isPowered() {
        return Arrays.stream(Direction.values())
                .map(d -> world.getBlockState(pos.offset(d)))
                .anyMatch(b -> b.getBlock() instanceof LeverBlock && b.hasProperty(LeverBlock.POWERED) && b.get(LeverBlock.POWERED));
    }
}