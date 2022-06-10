package onelemonyboi.miniutilities.blocks.complexblocks.redstoneclock;

import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class RedstoneClockTile extends TileBase {
    @SaveInNBT(key = "power")
    int power;

    public RedstoneClockTile(BlockPos pos, BlockState state) {
        super(TEList.RedstoneClockTile.get(), pos, state, TileBehaviors.base);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, RedstoneClockTile tile) {
        if (tile.isPowered()) {
            level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED, true));
            tile.power = 0;
            return;
        }
        else {
            level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(BlockStateProperties.POWERED, false));
        }

        level.sendBlockUpdated(pos, state, state, 3);
        level.updateNeighborsAt(tile.worldPosition, state.getBlock());

        boolean enablePower = Util.getMillis() / 50 % 20 == 0;
        tile.power = enablePower ? 15 : 0;
    }

    public boolean isPowered() {
        Stream<BlockState> redstoneEmitters = Arrays.stream(Direction.values())
                .map(d -> level.getBlockState(worldPosition.relative(d)))
                .filter(b -> {
                    if (b.getBlock() instanceof LeverBlock || b.getBlock() instanceof ButtonBlock || b.getBlock() instanceof PressurePlateBlock) {
                        return b.hasProperty(BlockStateProperties.POWERED) && b.getValue(BlockStateProperties.POWERED);
                    }
                    return false;
                });
        return redstoneEmitters.count() > 0;
    }
}