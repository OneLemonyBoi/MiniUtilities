package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.Direction;
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
import onelemonyboi.miniutilities.startup.JSON.JSONLoader;
import onelemonyboi.miniutilities.startup.JSON.QuantumQuarryJSON;
import org.apache.commons.lang3.tuple.MutableTriple;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    public ItemStack insertStack;

    public QuantumQuarryTile() {
        super(TEList.QuantumQuarryTile.get(), 10000000, 10000000, 0);
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 1200;
        this.insertStack = generateItemStack();
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

        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);

        if (generateItemStack().isEmpty()) {
            insertStack = generateItemStack();
            return;
        }
        if (!canInput(insertStack)) {
            return;
        }
        if ((!world.isBlockPowered(this.getPos()) && this.redstonemode == 2) || (world.isBlockPowered(this.getPos()) && this.redstonemode == 3)) {
            return;
        }
        if (!energy.checkedMachineConsume(calcRFCost(this.waittime))) {
            return;
        }
        this.timer++;
        if (this.timer < this.waittime) {return;}
        this.timer = 0;
        oreGen(insertStack);
        insertStack = generateItemStack();
    }

    private void oreGen(ItemStack insertStack) {
        for (int i = 0; i < 9; i++) {
            insertStack = this.itemSH.insertItem(i, insertStack, false);
        }
        this.markDirty();
    }

    private boolean canInput(ItemStack insertStack) {
        for (int i = 0; i < 9; i++) {
            insertStack = this.itemSH.insertItem(i, insertStack, true);
        }

        return insertStack.isEmpty();
    }

    private ItemStack generateItemStack() {
        if (world == null) {
            return ItemStack.EMPTY;
        }

        String biomeStr = world.getBiome(getPos()).getRegistryName().getNamespace() + ":" + world.getBiome(getPos()).getRegistryName().getPath();
        String dimensionStr = world.getDimensionKey().getLocation().getNamespace() + ":" + world.getDimensionKey().getLocation().getPath();

        List<QuantumQuarryJSON.OreInfo> validOreList = QuantumQuarryJSON.oreList.stream()
                .filter((oreInfo) -> oreInfo.biomes.contains(biomeStr) || oreInfo.dimensions.contains(dimensionStr))
                .collect(Collectors.toList());

        if (validOreList.size() > 0) {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryCreate(validOreList.get(new Random().nextInt(validOreList.size())).name)));
        }

        return ItemStack.EMPTY;
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
        output.add(new StringTextComponent("FE/t Consumption: " + calcRFCost(this.waittime)));
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
