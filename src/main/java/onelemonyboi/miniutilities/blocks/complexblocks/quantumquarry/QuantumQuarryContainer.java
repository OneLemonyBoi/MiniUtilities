package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;

import java.util.Objects;


public class QuantumQuarryContainer extends AbstractContainerMenu {
    public final QuantumQuarryTile te;
    private final ContainerLevelAccess access;

    public QuantumQuarryContainer(final int windowId, final Inventory playerInv, FriendlyByteBuf buf) {
        this(windowId, playerInv, ContainerLevelAccess.create(playerInv.player.level, buf.readBlockPos()));
    }

    public QuantumQuarryContainer(final int windowId, final Inventory playerInv, ContainerLevelAccess access) {
        super(ContainerList.QuarryContainer.get(), windowId);
        this.access = access;
        this.te = (QuantumQuarryTile) access.evaluate(Level::getBlockEntity).get();

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

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(access, playerIn, BlockList.QuantumQuarry.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        net.minecraft.world.item.ItemStack stack = net.minecraft.world.item.ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < QuantumQuarryTile.slots && !this.moveItemStackTo(stack1, QuantumQuarryTile.slots, this.slots.size(), true)) {
                return net.minecraft.world.item.ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, QuantumQuarryTile.slots, false)) {
                return net.minecraft.world.item.ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.set(net.minecraft.world.item.ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }
}