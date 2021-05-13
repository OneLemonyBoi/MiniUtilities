package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.lemonlib.blocks.EnergyTileBase;
import onelemonyboi.lemonlib.MUItemStackHandler;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.world.Config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuantumQuarryTile extends EnergyTileBase implements INamedContainerProvider, RenderInfoIdentifier {
    public static int slots = 9;

    public final MUItemStackHandler itemSH = new MUItemStackHandler(9);
    private final LazyOptional<MUItemStackHandler> lazyItemStorage = LazyOptional.of(() -> itemSH);

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    public Integer redstonemode;
    public Integer timer;
    public Integer waittime;
    public List<Block> oreList;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public QuantumQuarryTile() {
        super(TEList.QuantumQuarryTile.get(), 10000000, 10000000, 0);
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 1200;
        this.oreList = new ArrayList<Block>();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.miniutilities.quantum_quarry");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory player, PlayerEntity entity) {
        return new QuantumQuarryContainer(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putInt("RedstoneMode", this.redstonemode);
        tag.putInt("WaitTime", this.waittime);
        tag.put("itemSH", itemSH.serializeNBT());
        energy.write(tag);
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        itemSH.deserializeNBT(tag.getCompound("itemSH"));
        this.redstonemode = tag.getInt("RedstoneMode");
        this.waittime = tag.getInt("WaitTime");
        energy.read(tag);
    }

    public static int calcRFCost(int waittime) {
        return Math.round(4000000 / (float) Math.pow(waittime, 1.5F));
    }

    @Override
    public void tick() {
        if (world.isRemote()) {return;}
        this.timer++;
        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
        if (!energy.checkedMachineConsume(calcRFCost(this.waittime))) {
            return;
        }
        if (this.timer < this.waittime) {return;}
        this.timer = 0;
        if (this.redstonemode == 1){
            oreGen();
        }
        else if (world.isBlockPowered(this.getPos()) && this.redstonemode == 2){
            oreGen();
        }
        else if (!world.isBlockPowered(this.getPos()) && this.redstonemode == 3){
            oreGen();
        }
    }

    protected void oreGen() {
        if (this.oreList.isEmpty()) {
            for (String str : Config.oreChances.get()) {
                String[] chancesSplit = str.split(":");
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(chancesSplit[0], chancesSplit[1]));
                for (int i = 0; i < Integer.parseInt(chancesSplit[2]); i++) {
                    this.oreList.add(block);
                }
            }
        }
        ItemStack insertStack = new ItemStack(Item.getItemFromBlock(this.oreList.get(new Random().nextInt(this.oreList.size()))));
        for (int i = 0; i < 9; i++) {
            insertStack = this.itemSH.insertItem(i, insertStack, false);
        }
        if (!insertStack.isEmpty()) {
            InventoryHelper.spawnItemStack(world, this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ(), insertStack);
        }
        this.markDirty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemStorage.cast();
        }
        if (cap == CapabilityEnergy.ENERGY && !world.isRemote) {
            return lazyEnergy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        super.remove();
        lazyEnergy.invalidate();
        lazyItemStorage.invalidate();
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        switch (this.redstonemode) {
            case 1:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodeone"));
                break;
            case 2:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodetwo"));
                break;
            case 3:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodethree"));
                break;
        }
        output.add(new TranslationTextComponent("text.miniutilities.waittime")
                .appendString(this.waittime.toString() + " ticks(" + String.valueOf(this.waittime.floatValue() / 20))
                .appendSibling(new TranslationTextComponent("text.miniutilities.seconds"))
                .appendString(")"));
        output.add(new StringTextComponent("Power: " + this.energy.toString()));
        output.add(new StringTextComponent("RF/t Consumption: " + calcRFCost(this.waittime)));
        return output;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), 514, this.write(new CompoundNBT()));
    }
}
