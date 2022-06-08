package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
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
import java.util.stream.Stream;

public class MechanicalMinerTile extends TileBase implements INamedContainerProvider, RenderInfoIdentifier, ITickableTileEntity {
    public static int slots = 10;

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    @SaveInNBT(key = "RedstoneMode")
    public Integer redstonemode;
    public Integer timer;
    @SaveInNBT(key = "WaitTime")
    public Integer waittime;

    public MechanicalMinerTile() {
        super(TEList.MechanicalMinerTile.get(), TileBehaviors.mechanicalMiner);
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 20;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.miniutilities.mechanical_miner");
    }

    @Override
    public Container createMenu(int id, PlayerInventory player, PlayerEntity entity) {
        return new MechanicalMinerContainer(id, player, this);
    }

    @Override
    public void tick() {
        if (level.isClientSide()) {return;}
        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.timer++;
        if (this.timer < this.waittime) {return;}
        this.timer = 0;
        if (this.redstonemode == 1){
            blockBreaker();
        }
        else if (level.hasNeighborSignal(this.getBlockPos()) && this.redstonemode == 2){
            blockBreaker();
        }
        else if (!level.hasNeighborSignal(this.getBlockPos()) && this.redstonemode == 3){
            blockBreaker();
        }
    }

    protected void blockBreaker() {
        BlockPos blockPos = this.getBlockPos().relative(this.getBlockState().getValue(BlockStateProperties.FACING));

        // Loot Generation
        BlockState state = level.getBlockState(blockPos);
        if (getPickaxe().getHarvestLevel(ToolType.PICKAXE, null, state) < state.getBlock().getHarvestLevel(state)) {return;}
        LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) level)).withRandom(this.level.random).withParameter(LootParameters.ORIGIN, Vector3d.atCenterOf(blockPos)).withParameter(LootParameters.TOOL, getPickaxe()).withOptionalParameter(LootParameters.BLOCK_ENTITY, this.getTileEntity());
        List<ItemStack> lists = level.getBlockState(blockPos).getDrops(lootcontext$builder);

        for (ItemStack itemStack : lists) {
            for (int i = 0; i < 9; i++) {
                itemStack = getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().insertItem(i, itemStack, false);
            }
            if (!itemStack.isEmpty()) {
                InventoryHelper.dropItemStack(level, this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ(), itemStack);
            }
        }
        level.destroyBlock(blockPos, false); // Very kool break animations!
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
        output.add(new TranslationTextComponent("text.miniutilities.waittime").append(this.waittime.toString() + " ticks"));
        return output;
    }

    public ItemStack getPickaxe() {
        return getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().getStackInSlot(9).getItem() == Items.AIR ?
                new ItemStack(Items.IRON_PICKAXE) :
                getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().getStackInSlot(9);
    }
}
