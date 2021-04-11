package onelemonyboi.miniutilities.blocks.complexblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ByteArrayNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntArrayNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.misc.CustomEnergyStorage;
import onelemonyboi.miniutilities.misc.InventoryHandling;
import onelemonyboi.miniutilities.world.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuantumQuarryTile extends LockableLootTileEntity implements ITickableTileEntity {
    public static int slots = 9;
    protected final CustomEnergyStorage energy = new CustomEnergyStorage(10000000, 10000000, 0, true, false);
    private LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> energy);

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    public Integer redstonemode;
    public Integer timer;
    public Integer waittime;
    public Boolean event;
    public List<Block> oreList;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public QuantumQuarryTile() {
        super(TEList.QuantumQuarryTile.get());
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 1200;
        this.event = false;
        this.oreList = new ArrayList<Block>();
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
        return new QuantumQuarryContainer(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putInt("RedstoneMode", this.redstonemode);
        tag.putInt("WaitTime", this.waittime);
        if(!this.checkLootAndWrite(tag)) {
            ItemStackHelper.saveAllItems(tag, this.items);
        }
        energy.write(tag);
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        this.redstonemode = tag.getInt("RedstoneMode");
        this.waittime = tag.getInt("WaitTime");
        energy.read(tag);
        if (!this.checkLootAndRead(tag)) {
            ItemStackHelper.loadAllItems(tag, this.items);
        }
    }

    public static int calcRFCost(int waittime) {
        return Math.round((float) (Math.pow(1200F / waittime, 1.5F) * 100F));
    }

    /**
     * Insert the specified stack to the specified inventory and return any leftover items
     * Includes modified form of HopperTileEntity#insertSlot
     */

    @Override
    public void tick() {
        this.event = false;
        this.timer++;
        if (this.timer < this.waittime) {return;}
        this.timer = 0;
        if (calcRFCost(this.waittime) <= this.energy.getEnergyStored()) {
            this.energy.setEnergy(this.energy.getEnergyStored() - calcRFCost(this.waittime));
            if (!world.isRemote && this.redstonemode == 1){
                oreGen();
            }
            else if (!world.isRemote && world.isBlockPowered(this.getPos()) && this.redstonemode == 2){
                oreGen();
            }
            else if (!world.isRemote && !world.isBlockPowered(this.getPos()) && this.redstonemode == 3){
                oreGen();
            }
        }
    }

    protected void oreGen() {
        if (this.oreList.isEmpty()) {
            for (String str : Config.ore_chances.get()) {
                String[] chancesSplit = str.split(":");
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(chancesSplit[0], chancesSplit[1]));
                for (int i = 0; i < Integer.parseInt(chancesSplit[2]); i++) {
                    this.oreList.add(block);
                }
            }
        }
        IInventory iinventory = (IInventory) this.getTileEntity();
        List<ItemStack> lists = new ArrayList<ItemStack>();
        lists.add(new ItemStack(Item.getItemFromBlock(this.oreList.get(new Random().nextInt(this.oreList.size())))));
        InventoryHandling.InventoryInsert(lists, iinventory, world, this);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(cap == CapabilityEnergy.ENERGY && !world.isRemote)
            return lazyEnergy.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        super.remove();
        lazyEnergy.invalidate();
    }
}
