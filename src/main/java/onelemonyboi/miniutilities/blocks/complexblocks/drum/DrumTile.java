package onelemonyboi.miniutilities.blocks.complexblocks.drum;

import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DrumTile extends TileBase implements RenderInfoIdentifier {
    @Getter
    private FluidTank drum;
    private final LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> drum);

    public DrumTile() {
        this(0);
    }

    public DrumTile(int mb) {
        super(TEList.DrumTile.get(), TileBehaviors.base);
        this.drum = new FluidTank(mb) {
            @Override
            protected void onContentsChanged() {
                sendToClients();
            }
        };
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        drum.readFromNBT(tag);
        drum.setCapacity(tag.getInt("Capacity"));
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag = super.save(tag);
        drum.writeToNBT(tag);
        tag.putInt("Capacity", drum.getCapacity());
        return tag;
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return holder.cast();
        return super.getCapability(capability, facing);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        holder.invalidate();
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new StringTextComponent(""));
        output.add(new TranslationTextComponent("text.miniutilities.fluidname").append(": " + getDrum().getFluid().getDisplayName().getString()));
        output.add(new TranslationTextComponent("text.miniutilities.drumamount").append(": " + this.drum.getFluidAmount()));
        output.add(new TranslationTextComponent("text.miniutilities.drumcapacity").append(": " + this.drum.getCapacity()));
        return output;
    }
}