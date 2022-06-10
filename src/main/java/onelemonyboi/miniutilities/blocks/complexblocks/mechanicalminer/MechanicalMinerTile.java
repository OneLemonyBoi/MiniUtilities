package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.ArrayList;
import java.util.List;

public class MechanicalMinerTile extends TileBase implements MenuProvider, RenderInfoIdentifier {
    public static int slots = 10;

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    @SaveInNBT(key = "RedstoneMode")
    public Integer redstonemode;
    public Integer timer;
    @SaveInNBT(key = "WaitTime")
    public Integer waittime;

    public MechanicalMinerTile(BlockPos pos, BlockState state) {
        super(TEList.MechanicalMinerTile.get(), pos, state, TileBehaviors.mechanicalMiner);
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 20;
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("container.miniutilities.mechanical_miner");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
        return new MechanicalMinerContainer(id, player, ContainerLevelAccess.create(getLevel(), getBlockPos()));
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, MechanicalMinerTile tile) {
        level.sendBlockUpdated(pos, state, state, 2);
        tile.timer++;
        if (tile.timer < tile.waittime) {return;}
        tile.timer = 0;
        if (tile.redstonemode == 1) {
            tile.blockBreaker();
        }
        else if (level.hasNeighborSignal(pos) && tile.redstonemode == 2) {
            tile.blockBreaker();
        }
        else if (!level.hasNeighborSignal(pos) && tile.redstonemode == 3) {
            tile.blockBreaker();
        }
    }

    protected void blockBreaker() {
        BlockPos blockPos = this.getBlockPos().relative(this.getBlockState().getValue(BlockStateProperties.FACING));

        // Loot Generation
        BlockState state = level.getBlockState(blockPos);

        if (state.requiresCorrectToolForDrops() && !getPickaxe().isCorrectToolForDrops(state)) {return;}
        LootContext.Builder ctx = new LootContext.Builder((ServerLevel) level)
                .withRandom(this.level.random).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockPos))
                .withParameter(LootContextParams.TOOL, getPickaxe())
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, level.getBlockEntity(blockPos));
        List<ItemStack> lists = level.getBlockState(blockPos).getDrops(ctx);

        for (ItemStack itemStack : lists) {
            for (int i = 0; i < 9; i++) {
                itemStack = getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().insertItem(i, itemStack, false);
            }
            if (!itemStack.isEmpty()) {
                Containers.dropItemStack(level, this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ(), itemStack);
            }
        }
        level.destroyBlock(blockPos, false); // Very kool break animations!
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
        output.add(new TranslatableComponent("text.miniutilities.waittime").append(this.waittime.toString() + " ticks"));
        return output;
    }

    public ItemStack getPickaxe() {
        return getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().getStackInSlot(9).getItem() == Items.AIR ?
                new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.IRON_PICKAXE) :
                getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().getStackInSlot(9);
    }
}
