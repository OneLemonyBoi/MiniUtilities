package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;

import java.util.Objects;


public class MechanicalMinerContainer extends AbstractContainerMenu {
    public final MechanicalMinerTile te;
    private final ContainerLevelAccess access;

    public MechanicalMinerContainer(final int windowId, final Inventory playerInv, FriendlyByteBuf buf) {
        this(windowId, playerInv, ContainerLevelAccess.create(playerInv.player.level(), buf.readBlockPos()));
    }

    public MechanicalMinerContainer(final int windowId, final Inventory playerInv, ContainerLevelAccess access) {
        super(ContainerList.MinerContainer.get(), windowId);
        this.access = access;
        this.te = (MechanicalMinerTile) access.evaluate(Level::getBlockEntity).get();

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

        // Pickaxe Slot
        this.addSlot(new SlotItemHandler(te.getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler(), 9, 62 + 4 * 18, 17));

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

    private static MechanicalMinerTile getTileEntity(final Inventory playerInv, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
        final BlockEntity te = playerInv.player.level().getBlockEntity(data.readBlockPos());
        if (te instanceof MechanicalMinerTile) {
            return (MechanicalMinerTile) te;
        }
        throw new IllegalStateException("Tile Entity Is Not Correct");
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(access, playerIn, BlockList.MechanicalMiner.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < MechanicalMinerTile.slots && !this.moveItemStackTo(stack1, MechanicalMinerTile.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, MechanicalMinerTile.slots, false)) {
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