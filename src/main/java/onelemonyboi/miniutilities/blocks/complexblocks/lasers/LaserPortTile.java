package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import onelemonyboi.lemonlib.blocks.EnergyTileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LaserPortTile extends EnergyTileBase implements RenderInfoIdentifier {
    public boolean isInput;

    public LaserPortTile() {
        super(TEList.LaserPortTile.get(), 1048576, 1048576, 1048576);
        this.isInput = true;
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            return;
        }

        if (isInput) {
            energy.outputToSide(world, getPos(), getBlockState().get(LaserPortBlock.FACING), Integer.MAX_VALUE);
        }
        else {
            energy.inputFromSide(world, getPos(), getBlockState().get(LaserPortBlock.FACING), Integer.MAX_VALUE);
        }

        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    public CompoundNBT write(CompoundNBT nbt) {
        this.energy.write(nbt);
        nbt.putBoolean("IsInput", this.isInput);
        return super.write(nbt);
    }

    public void read(BlockState state, CompoundNBT nbt) {
        this.energy.read(nbt);
        this.isInput = nbt.getBoolean("IsInput");
        super.read(state, nbt);
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        output.add(new StringTextComponent("Power: " + this.energy.toString()));
        output.add(new StringTextComponent("I/O Mode: " + (this.isInput ? "Pull from Machine" : "Push to Machine")));
        return output;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), 514, this.write(new CompoundNBT()));
    }
}
