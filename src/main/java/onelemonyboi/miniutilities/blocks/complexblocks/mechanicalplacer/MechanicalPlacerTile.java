package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.ArrayList;
import java.util.List;

public class MechanicalPlacerTile extends TileBase implements MenuProvider, RenderInfoIdentifier {
    public static int slots = 9;

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    @SaveInNBT(key = "RedstoneMode")
    public Integer redstonemode;
    public Integer timer;
    @SaveInNBT(key = "WaitTime")
    public Integer waittime;

    public MechanicalPlacerTile(BlockPos pos, BlockState state) {
        super(TEList.MechanicalPlacerTile.get(), pos, state, TileBehaviors.mechanicalPlacer);
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 20;
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("container.miniutilities.mechanical_placer");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
        return new MechanicalPlacerContainer(id, player, ContainerLevelAccess.create(getLevel(), getBlockPos()));
    }

    public void serverTick(Level level, BlockPos pos, BlockState state, MechanicalMinerTile tile) {
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
            if (!itemStack1.isEmpty() && item1 instanceof net.minecraft.world.item.BlockItem && level.isEmptyBlock(blockPos)) {
                level.setBlockAndUpdate(blockPos, ((BlockItem) item1).getBlock().defaultBlockState());
                itemStack1.shrink(1);
                flag = true;
            }
        }
        this.setChanged();
    }

    @Override
    public List<MutableComponent> getInfo() {
        List<MutableComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new TextComponent(""));
        switch (this.redstonemode) {
            case 1:
                output.add(new TranslatableComponent("text.miniutilities.redstonemodeone"));
                break;
            case 2:
                output.add(new TranslatableComponent("text.miniutilities.redstonemodetwo"));
                break;
            case 3:
                output.add(new TranslatableComponent("text.miniutilities.redstonemodethree"));
                break;
        }
        output.add(new TranslatableComponent("text.miniutilities.waittime")
                .append(this.waittime.toString() + " ticks(" + this.waittime.floatValue() / 20)
                .append(new TranslatableComponent("text.miniutilities.seconds"))
                .append(")"));
        return output;
    }
}
