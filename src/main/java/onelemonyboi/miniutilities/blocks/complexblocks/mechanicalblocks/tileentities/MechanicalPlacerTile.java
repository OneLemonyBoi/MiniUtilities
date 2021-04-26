package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import onelemonyboi.lemonlib.blocks.TileBase;
import onelemonyboi.miniutilities.blocks.complexblocks.MUItemStackHandler;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.containers.MechanicalPlacerContainer;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MechanicalPlacerTile extends TileBase implements INamedContainerProvider {
    public static int slots = 9;

    public final MUItemStackHandler itemSH = new MUItemStackHandler(9);
    private final LazyOptional<MUItemStackHandler> lazyItemStorage = LazyOptional.of(() -> itemSH);

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    public Integer redstonemode;
    public Integer timer;
    public Integer waittime;
    public Boolean event;

    public MechanicalPlacerTile() {
        super(TEList.MechanicalPlacerTile.get());
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 20;
        this.event = false;
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
        this.event = false;
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
}
