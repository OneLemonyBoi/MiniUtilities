package onelemonyboi.miniutilities.testlmaoidkwhytfthisishere;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.VanillaInventoryCodeHooks;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TestTE extends LockableLootTileEntity implements ITickableTileEntity {
    public static int slots = 9;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public TestTE() {
        super(TEList.TestTEE.get());
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
        return new TranslationTextComponent("container.testmini.display_case");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new TestContainer(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if(!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public void tick() {
        if (!world.isRemote && !world.isBlockPowered(this.getPos())){
            BlockPos blockPos = this.getPos().offset(this.getBlockState().get(BlockStateProperties.FACING));

            // Temp Code
            // if (!world.isRemote && !world.isBlockPowered(this.getPos())){
            //    this.world.destroyBlock(blockPos, true);
            //}

            // Get Inventory
            IInventory iinventory = (IInventory) this.getTileEntity();
            if (iinventory == null) {
                List<Entity> list = world.getEntitiesInAABBexcluding(null, new AxisAlignedBB(this.getPos().getX() - 0.5D, this.getPos().getY() - 0.5D, this.getPos().getZ() - 0.5D, this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D), EntityPredicates.HAS_INVENTORY);
                if (!list.isEmpty()) {
                    iinventory = (IInventory) list.get(world.rand.nextInt(list.size()));
                }
            }

            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.world)).withRandom(this.world.rand).withParameter(LootParameters.ORIGIN, Vector3d.copyCentered(blockPos)).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withNullableParameter(LootParameters.BLOCK_ENTITY, this.getTileEntity());
            List<ItemStack> lists = world.getBlockState(blockPos).getDrops(lootcontext$builder);
            for (ItemStack itemStack : lists) {
                int i = iinventory.getSizeInventory();
                for (int j = 0; j < i && !itemStack.isEmpty(); ++j) {
                    // itemStack = insertStack(source, iinventory, itemStack, j, Direction.NORTH);
                    ItemStack itemStack1 = iinventory.getStackInSlot(j);
                    if (itemStack1.isEmpty()) {
                        iinventory.setInventorySlotContents(j, itemStack);
                        itemStack = ItemStack.EMPTY;
                    } else if (canCombine(itemStack, itemStack1)) {
                        int x = itemStack.getMaxStackSize() - itemStack1.getCount();
                        int y = Math.min(itemStack.getCount(), i);
                        itemStack.shrink(j);
                        itemStack1.grow(j);
                    }
                }
                iinventory.markDirty();
                if (!itemStack.isEmpty()) {
                    InventoryHelper.spawnItemStack(world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStack);
                }
            }
            // TODO: Fix the bug where only one item goes into 1st slot. Suspect is the canCombine function, use Breakpoints to find values

            world.destroyBlock(blockPos, false);
        }
    }

    private static boolean canCombine(ItemStack stack1, ItemStack stack2) {
        if (stack1.getItem() != stack2.getItem()) {
            return false;
        } else if (stack1.getDamage() != stack2.getDamage()) {
            return false;
        } else if (stack1.getCount() > stack1.getMaxStackSize()) {
            return false;
        } else {
            return ItemStack.areItemStackTagsEqual(stack1, stack2);
        }
    }
}
