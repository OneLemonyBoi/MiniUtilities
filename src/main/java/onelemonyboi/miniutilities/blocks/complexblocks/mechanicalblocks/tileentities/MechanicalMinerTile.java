package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.containers.MechanicalMinerContainer;

import java.util.List;

public class MechanicalMinerTile extends LockableLootTileEntity implements ITickableTileEntity {
    public static int slots = 9;

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    public Integer redstonemode;
    public Integer timer;
    public Integer waittime;
    public Boolean event;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public MechanicalMinerTile() {
        super(TEList.MechanicalMinerTile.get());
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 20;
        this.event = false;
    }

    @Override
    public int getSizeInventory() {
        return slots;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.miniutilities.mechanical_miner");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new MechanicalMinerContainer(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putInt("RedstoneMode", this.redstonemode);
        tag.putInt("WaitTime", this.waittime);
        if(!this.checkLootAndWrite(tag)) {
            ItemStackHelper.saveAllItems(tag, this.items);
        }
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        this.redstonemode = tag.getInt("RedstoneMode");
        this.waittime = tag.getInt("WaitTime");
        if (!this.checkLootAndRead(tag)) {
            ItemStackHelper.loadAllItems(tag, this.items);
        }
    }

    /**
     * Insert the specified stack to the specified inventory and return any leftover items
     * Includes modified form of HopperTileEntity#insertSlot
     */

    @Override
    public void tick() {
        this.timer++;
        if (this.timer != this.waittime) {return;}
        this.timer = 0;
        if (!world.isRemote && this.redstonemode == 1){
            blockBreaker();
        }
        else if (!world.isRemote && world.isBlockPowered(this.getPos()) && this.redstonemode == 2){
            blockBreaker();
        }
        else if (!world.isRemote && !world.isBlockPowered(this.getPos()) && this.redstonemode == 3){
            blockBreaker();
        }
        this.event = false;
    }

    private static boolean canCombine(ItemStack stack1, ItemStack stack2) {
        if (stack1.getItem() != stack2.getItem()) {
            return false;
        } else if (stack1.getDamage() != stack2.getDamage()) {
            return false;
        } else if (stack1.getCount() > stack1.getMaxStackSize()) {
            return false;
        } else {
            Boolean buffer = ItemStack.areItemStackTagsEqual(stack1, stack2);
            return buffer;
        }
    }

    protected void blockBreaker() {
        BlockPos blockPos = this.getPos().offset(this.getBlockState().get(BlockStateProperties.FACING));
        IInventory iinventory = (IInventory) this.getTileEntity();
        if (iinventory == null) {
            List<Entity> list = world.getEntitiesInAABBexcluding(null, new AxisAlignedBB(this.getPos().getX() - 0.5D, this.getPos().getY() - 0.5D, this.getPos().getZ() - 0.5D, this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D), EntityPredicates.HAS_INVENTORY);
            if (!list.isEmpty()) {
                iinventory = (IInventory) list.get(world.rand.nextInt(list.size()));
            }
        }
        // Loot Generation
        LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.world)).withRandom(this.world.rand).withParameter(LootParameters.ORIGIN, Vector3d.copyCentered(blockPos)).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withNullableParameter(LootParameters.BLOCK_ENTITY, this.getTileEntity());
        List<ItemStack> lists = world.getBlockState(blockPos).getDrops(lootcontext$builder);

        // Iteration UwU
        for (ItemStack itemStack : lists) {
            int i = iinventory.getSizeInventory();
            for (int j = 0; j < i && !itemStack.isEmpty(); ++j) {
                ItemStack itemStack1 = iinventory.getStackInSlot(j);
                if (itemStack1.isEmpty()) {
                    iinventory.setInventorySlotContents(j, itemStack);
                    itemStack = ItemStack.EMPTY;
                } else if (canCombine(itemStack, itemStack1)) {
                    int x = itemStack.getMaxStackSize() - itemStack1.getCount();
                    int y = Math.min(itemStack.getCount(), x);
                    itemStack.shrink(y);
                    itemStack1.grow(y);
                }
            }
            iinventory.markDirty();
            if (!itemStack.isEmpty()) {
                InventoryHelper.spawnItemStack(world, this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ(), itemStack); // Hidden Gem
            }
        }
        world.destroyBlock(blockPos, false); // Very kool break animations!
    }
}
