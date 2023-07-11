package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.ArrayList;
import java.util.List;

public class LaserPortTile extends TileBase implements RenderInfoIdentifier {
    @SaveInNBT(key = "IsInput")
    public boolean isInput;

    public LaserPortTile(BlockPos pos, BlockState state) {
        super(TEList.LaserPortTile.get(), pos, state, TileBehaviors.laserPort);
        this.isInput = true;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, LaserPortTile tile) {
        if (tile.isInput)
            tile.getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().outputToSide(level, pos, state.getValue(LaserPortBlock.FACING), Integer.MAX_VALUE);
        else
            tile.getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().inputFromSide(level, pos, state.getValue(LaserPortBlock.FACING), Integer.MAX_VALUE);


        level.sendBlockUpdated(pos, state, state, 2);
    }

    @Override
    public List<MutableComponent> getInfo() {
        List<MutableComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(Component.literal(""));
        output.add(Component.literal("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        output.add(Component.literal("I/O Mode: " + (this.isInput ? "Push to Machine" : "Pull from Machine")));
        return output;
    }
}
