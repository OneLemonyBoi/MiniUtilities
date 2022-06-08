package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.SlotItemHandler;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;

import java.util.Objects;


public class QuantumQuarryContainer extends Container {
    public final QuantumQuarryTile te;
    private final IWorldPosCallable canInteractWithCallable;

    public QuantumQuarryContainer(final int windowId, final PlayerInventory playerInv, final QuantumQuarryTile te) {
        super(ContainerList.QuarryContainer.get(), windowId);
        this.te = te;
        this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

        // Tile Entity
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                switch (row) {
                    case 0:
                        this.addSlot(new SlotItemHandler(te.getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler(), col + row * 3, 62 + col * 18, 17));
                        break;
                    case 1:
                        this.addSlot(new SlotItemHandler(te.getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler(), col + row * 3, 62 + col * 18, 35));
                        break;
                    case 2:
                        this.addSlot(new SlotItemHandler(te.getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler(), col + row * 3, 62 + col * 18, 53));
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

    public QuantumQuarryContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    private static QuantumQuarryTile getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
        final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
        if (te instanceof QuantumQuarryTile) {
            return (QuantumQuarryTile) te;
        }
        throw new IllegalStateException("Tile Entity Is Not Correct");
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(canInteractWithCallable, playerIn, BlockList.QuantumQuarry.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < QuantumQuarryTile.slots && !this.moveItemStackTo(stack1, QuantumQuarryTile.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, QuantumQuarryTile.slots, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }
}