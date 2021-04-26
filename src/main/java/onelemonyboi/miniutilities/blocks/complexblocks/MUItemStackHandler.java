package onelemonyboi.miniutilities.blocks.complexblocks;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class MUItemStackHandler implements IItemHandler, IItemHandlerModifiable, INBTSerializable<CompoundNBT> {
    // TODO: MOVE TO LEMONLIB

    public final ItemStackHandler internal;

    public MUItemStackHandler(int slots) {
        internal = new ItemStackHandler(slots);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return internal.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        internal.deserializeNBT(nbt);
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        internal.setStackInSlot(slot, stack);
    }

    @Override
    public int getSlots() {
        return internal.getSlots();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return internal.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if(this.isItemValid(slot, stack)) {
            return internal.insertItem(slot, stack, simulate);
        }
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if(canExtractItem(slot, amount)) {
            return internal.extractItem(slot, amount, simulate);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        return internal.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }

    public boolean canExtractItem(int slot, int amount) {
        return true;
    }
}