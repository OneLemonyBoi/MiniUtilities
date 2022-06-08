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
        if (level.isClientSide()) return;
        if (isPowered()) {
            level.setBlockAndUpdate(getBlockPos(), level.getBlockState(getBlockPos()).setValue(BlockStateProperties.POWERED, true));
            power = 0;
            return;
        }
        else {
            level.setBlockAndUpdate(getBlockPos(), level.getBlockState(getBlockPos()).setValue(BlockStateProperties.POWERED, false));
        }

        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        level.updateNeighborsAt(worldPosition, this.getBlockState().getBlock());

        boolean enablePower = Util.getMillis() / 50 % 20 == 0;
        power = enablePower ? 15 : 0;
    }

    public boolean isPowered() {
        return Arrays.stream(Direction.values())
                .map(d -> level.getBlockState(worldPosition.relative(d)))
                .anyMatch(b -> b.getBlock() instanceof LeverBlock && b.hasProperty(LeverBlock.POWERED) && b.getValue(LeverBlock.POWERED));
    }
}