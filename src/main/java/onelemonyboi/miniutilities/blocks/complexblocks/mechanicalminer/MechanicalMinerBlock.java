package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;

import java.util.UUID;

import static onelemonyboi.miniutilities.misc.KeyBindingsHandler.keyBindingPressed;

public class MechanicalMinerBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public MechanicalMinerBlock() {
        super(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3F)
                .sound(SoundType.METAL));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TEList.MechanicalMinerTile.get().create();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (player.isSneaking()) {return ActionResultType.CONSUME;}

            if (!(te instanceof MechanicalMinerTile)) {return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);}
            MechanicalMinerTile TE = ((MechanicalMinerTile) te);
            if (ModTags.Items.UPGRADES_SPEED.contains(player.getHeldItem(handIn).getItem())) {
                if (TE.waittime > 5) {
                    TE.waittime = TE.waittime - 5;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
                else if (TE.waittime == 5){
                    TE.waittime = 1;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
            }
            else {
                NetworkHooks.openGui((ServerPlayerEntity) player, (MechanicalMinerTile) te, pos);
                return ActionResultType.CONSUME;
            }
        }
        return ActionResultType.CONSUME;
    }

    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.getWorld().isRemote()) {
            if (event.getWorld().getTileEntity(event.getPos()) instanceof MechanicalMinerTile && event.getPlayer().isSneaking() && event.getPlayer().getHeldItem(event.getHand()).isEmpty()) {
                MechanicalMinerTile TE = (MechanicalMinerTile) (event.getWorld().getTileEntity(event.getPos()));
                if (TE.waittime > 1 && TE.waittime < 20) {
                    TE.waittime = TE.waittime + 5;
                    InventoryHelper.spawnItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                } else if (TE.waittime == 1) {
                    TE.waittime = 5;
                    InventoryHelper.spawnItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
            }
        }
    }

    @Deprecated
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (worldIn.isRemote()) {
            return;
        }

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        ItemStack itemStack = new ItemStack(this);
        CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
        compoundNBT.remove("x");
        compoundNBT.remove("y");
        compoundNBT.remove("z");
        itemStack.setTagInfo("BlockEntityTag", compoundNBT);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}
