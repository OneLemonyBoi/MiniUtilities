package onelemonyboi.miniutilities.tileentities.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;
import onelemonyboi.miniutilities.tileentities.MechanicalMinerTile;

import java.util.Objects;


public class MechanicalMinerContainer extends Container {
    public final MechanicalMinerTile te;
    private final IWorldPosCallable canInteractWithCallable;

    public MechanicalMinerContainer(final int windowId, final PlayerInventory playerInv, final MechanicalMinerTile te) {
        super(ContainerList.TestContainer.get(), windowId);
        this.te = te;
        this.canInteractWithCallable = IWorldPosCallable.of(te.getWorld(), te.getPos());

        // Tile Entity
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                switch (row) {
                    case 0:
                        this.addSlot(new Slot((IInventory) te, col + row * 3, 62 + col * 18, 17));
                        break;
                    case 1:
                        this.addSlot(new Slot((IInventory) te, col + row * 3, 62 + col * 18, 35));
                        break;
                    case 2:
                        this.addSlot(new Slot((IInventory) te, col + row * 3, 62 + col * 18, 53));
                        break;
                }
            }
        }

        // Main Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // Player Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    public MechanicalMinerContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    private static MechanicalMinerTile getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
        final TileEntity te = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (te instanceof MechanicalMinerTile) {
            return (MechanicalMinerTile) te;
        }
        throw new IllegalStateException("Tile Entity Is Not Correct");
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockList.MechanicalMiner.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            if (index < MechanicalMinerTile.slots
                    && !this.mergeItemStack(stack1, MechanicalMinerTile.slots, this.inventorySlots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.mergeItemStack(stack1, 0, MechanicalMinerTile.slots, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return stack;
    }
}
