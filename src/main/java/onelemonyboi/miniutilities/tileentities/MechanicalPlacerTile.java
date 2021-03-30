package onelemonyboi.miniutilities.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.tileentities.containers.MechanicalPlacerContainer;

import java.util.List;

public class MechanicalPlacerTile extends LockableLootTileEntity implements ITickableTileEntity {
    public static int slots = 9;

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    public Integer redstonemode;
    public Integer timer;
    public Integer waittime;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public MechanicalPlacerTile() {
        super(TEList.MechanicalPlacerTile.get());
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 20;
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
        return new TranslationTextComponent("container.miniutilities.mechanical_placer");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new MechanicalPlacerContainer(id, player, this);
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
        this.timer++;
        if (this.timer != this.waittime) {return;}
        this.timer = 0;
        if (!world.isRemote && this.redstonemode == 1){
            blockPlacer();
        }
        else if (!world.isRemote && world.isBlockPowered(this.getPos()) && this.redstonemode == 2){
            blockPlacer();
        }
        else if (!world.isRemote && !world.isBlockPowered(this.getPos()) && this.redstonemode == 3){
            blockPlacer();
        }
    }

    protected void blockPlacer() {
        BlockPos blockPos = this.getPos().offset(this.getBlockState().get(BlockStateProperties.FACING));
        IInventory iinventory = (IInventory) this.getTileEntity();
        if (iinventory == null) {
            List<Entity> list = world.getEntitiesInAABBexcluding(null, new AxisAlignedBB(this.getPos().getX() - 0.5D, this.getPos().getY() - 0.5D, this.getPos().getZ() - 0.5D, this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D), EntityPredicates.HAS_INVENTORY);
            if (!list.isEmpty()) {
                iinventory = (IInventory) list.get(world.rand.nextInt(list.size()));
            }
        }
        Boolean flag = false;
        int i = iinventory.getSizeInventory();
        for (int j = 0; j < i && !flag; ++j) {
            ItemStack itemStack1 = iinventory.getStackInSlot(j);
            Item item1 = itemStack1.getItem();
            Boolean airChecker = world.getBlockState(blockPos) == Blocks.AIR.getDefaultState() || world.getBlockState(blockPos) == Blocks.CAVE_AIR.getDefaultState() || world.getBlockState(blockPos) == Blocks.VOID_AIR.getDefaultState();
            if (!itemStack1.isEmpty() && item1 instanceof BlockItem && airChecker) {
                world.setBlockState(blockPos, ((BlockItem) item1).getBlock().getDefaultState());
                itemStack1.shrink(1);
                flag = true;
            }
        }
        iinventory.markDirty();
    }
}
