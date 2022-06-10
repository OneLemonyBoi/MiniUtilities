package onelemonyboi.miniutilities.blocks.complexblocks.drum;

import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
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

    public DrumTile(BlockPos pos, BlockState state) {
        this(0, pos, state);
    }

    public DrumTile(int mb, BlockPos pos, BlockState state) {
        super(TEList.DrumTile.get(), pos, state, TileBehaviors.base);
        this.drum = new FluidTank(mb) {
            @Override
            protected void onContentsChanged() {
                sendToClients();
            }
        };
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        drum.readFromNBT(tag);
        drum.setCapacity(tag.getInt("Capacity"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        drum.writeToNBT(tag);
        tag.putInt("Capacity", drum.getCapacity());
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return holder.cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        holder.invalidate();
    }

    @Override
    public List<MutableComponent> getInfo() {
        List<MutableComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new TextComponent(""));
        output.add(new TranslatableComponent("text.miniutilities.fluidname").append(": " + getDrum().getFluid().getDisplayName().getString()));
        output.add(new TranslatableComponent("text.miniutilities.drumamount").append(": " + this.drum.getFluidAmount()));
        output.add(new TranslatableComponent("text.miniutilities.drumcapacity").append(": " + this.drum.getCapacity()));
        return output;
    }
}