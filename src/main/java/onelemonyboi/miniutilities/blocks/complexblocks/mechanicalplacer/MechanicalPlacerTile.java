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
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MechanicalPlacerTile extends TileBase implements INamedContainerProvider, RenderInfoIdentifier, ITickableTileEntity {
    public static int slots = 9;

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    @SaveInNBT(key = "RedstoneMode")
    public Integer redstonemode;
    public Integer timer;
    @SaveInNBT(key = "WaitTime")
    public Integer waittime;

    public MechanicalPlacerTile() {
        super(TEList.MechanicalPlacerTile.get(), TileBehaviors.mechanicalPlacer);
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
    public void tick() {
        if (level.isClientSide()) {return;}
        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.timer++;
        if (this.timer < this.waittime) {return;}
        this.timer = 0;
        if (this.redstonemode == 1){
            blockPlacer();
        }
        else if (level.hasNeighborSignal(this.getBlockPos()) && this.redstonemode == 2){
            blockPlacer();
        }
        else if (!level.hasNeighborSignal(this.getBlockPos()) && this.redstonemode == 3){
            blockPlacer();
        }
    }

    protected void blockPlacer() {
        BlockPos blockPos = this.getBlockPos().relative(this.getBlockState().getValue(BlockStateProperties.FACING));
        Boolean flag = false;
        for (int j = 0; j < slots && !flag; ++j) {
            ItemStack itemStack1 = getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().getStackInSlot(j);
            Item item1 = itemStack1.getItem();
            if (!itemStack1.isEmpty() && item1 instanceof BlockItem && level.isEmptyBlock(blockPos)) {
                level.setBlockAndUpdate(blockPos, ((BlockItem) item1).getBlock().defaultBlockState());
                itemStack1.shrink(1);
                flag = true;
            }
        }
        this.setChanged();
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
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
                .append(this.waittime.toString() + " ticks(" + String.valueOf(this.waittime.floatValue() / 20))
                .append(new TranslationTextComponent("text.miniutilities.seconds"))
                .append(")"));
        return output;
    }
}
