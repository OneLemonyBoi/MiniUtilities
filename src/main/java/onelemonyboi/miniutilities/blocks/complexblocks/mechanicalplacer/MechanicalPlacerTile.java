package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import onelemonyboi.lemonlib.blocks.TileBase;
import onelemonyboi.miniutilities.blocks.complexblocks.MUItemStackHandler;
import onelemonyboi.miniutilities.identifiers.RenderInfoIdentifier;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MechanicalPlacerTile extends TileBase implements INamedContainerProvider, RenderInfoIdentifier {
    public static int slots = 9;

    public final MUItemStackHandler itemSH = new MUItemStackHandler(9);
    private final LazyOptional<MUItemStackHandler> lazyItemStorage = LazyOptional.of(() -> itemSH);

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    public Integer redstonemode;
    public Integer timer;
    public Integer waittime;

    public MechanicalPlacerTile() {
        super(TEList.MechanicalPlacerTile.get());
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 20;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.miniutilities.mechanical_placer");
    }

    @Override
    public Container createMenu(int id, PlayerInventory player, PlayerEntity entity) {
        return new MechanicalPlacerContainer(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        tag.putInt("RedstoneMode", this.redstonemode);
        tag.putInt("WaitTime", this.waittime);
        tag.put("itemSH", itemSH.serializeNBT());
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        itemSH.deserializeNBT(tag.getCompound("itemSH"));
        this.redstonemode = tag.getInt("RedstoneMode");
        this.waittime = tag.getInt("WaitTime");
    }

    @Override
    public void tick() {
        if (world.isRemote()) {return;}

        this.timer++;
        if (this.timer != this.waittime) {return;}
        this.timer = 0;
        if (this.redstonemode == 1){
            blockPlacer();
        }
        else if (world.isBlockPowered(this.getPos()) && this.redstonemode == 2){
            blockPlacer();
        }
        else if (!world.isBlockPowered(this.getPos()) && this.redstonemode == 3){
            blockPlacer();
        }
    }

    protected void blockPlacer() {
        BlockPos blockPos = this.getPos().offset(this.getBlockState().get(BlockStateProperties.FACING));
        Boolean flag = false;
        for (int j = 0; j < slots && !flag; ++j) {
            ItemStack itemStack1 = itemSH.getStackInSlot(j);
            Item item1 = itemStack1.getItem();
            if (!itemStack1.isEmpty() && item1 instanceof BlockItem && world.isAirBlock(blockPos)) {
                world.setBlockState(blockPos, ((BlockItem) item1).getBlock().getDefaultState());
                itemStack1.shrink(1);
                flag = true;
            }
        }
        this.markDirty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemStorage.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        switch (this.redstonemode) {
            case 1:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtoone"));
                break;
            case 2:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtotwo"));
                break;
            case 3:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtothree"));
                break;
        }
        output.add(new TranslationTextComponent("text.miniutilities.waittime")
                .appendString(this.waittime.toString() + " ticks(" + String.valueOf(this.waittime.floatValue() / 20))
                .appendSibling(new TranslationTextComponent("text.miniutilities.seconds"))
                .appendString(")"));
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
