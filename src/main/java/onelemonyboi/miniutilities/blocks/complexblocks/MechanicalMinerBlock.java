package onelemonyboi.miniutilities.blocks.complexblocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.tileentities.DrumTile;
import onelemonyboi.miniutilities.tileentities.MechanicalMinerTile;
import org.lwjgl.glfw.GLFW;

import java.util.UUID;

public class MechanicalMinerBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static Boolean keyPressed = false;

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
            if (te instanceof MechanicalMinerTile && ModTags.Items.WRENCH.contains(player.getHeldItem(handIn).getItem()) && keyPressed) {
                MechanicalMinerTile TE = ((MechanicalMinerTile) te);
                player.sendMessage(new TranslationTextComponent("text.miniutilities.info"), UUID.randomUUID());
                if (TE.redstonemode == 1) {
                    player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtoone"), UUID.randomUUID());
                }
                else if (TE.redstonemode == 2) {
                    player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtotwo"), UUID.randomUUID());
                }
                else if (TE.redstonemode == 3) {
                    player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtothree"), UUID.randomUUID());
                }
                player.sendMessage(new TranslationTextComponent("text.miniutilities.waittime")
                        .appendString(TE.waittime.toString() + " ticks(" + String.valueOf(TE.waittime.floatValue() / 20))
                        .appendSibling(new TranslationTextComponent("text.miniutilities.seconds"))
                        .appendString(")"), UUID.randomUUID());
            }
            else if (te instanceof MechanicalMinerTile && ModTags.Items.WRENCH.contains(player.getHeldItem(handIn).getItem())) {
                MechanicalMinerTile TE = ((MechanicalMinerTile) te);
                switch (TE.redstonemode) {
                    case 1:
                        TE.redstonemode = 2;
                        player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtotwo"), UUID.randomUUID());
                        break;
                    case 2:
                        TE.redstonemode = 3;
                        player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtothree"), UUID.randomUUID());
                        break;
                    case 3:
                        TE.redstonemode = 1;
                        player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtoone"), UUID.randomUUID());
                        break;
                }
                return ActionResultType.CONSUME;
            }
            else if (te instanceof MechanicalMinerTile && ModTags.Items.UPGRADES_SPEED.contains(player.getHeldItem(handIn).getItem())) {
                MechanicalMinerTile TE = ((MechanicalMinerTile) te);
                if (TE.waittime > 5) {
                    TE.waittime = TE.waittime - 5;
                    TE.timer = 0;
                }
                else if (TE.waittime == 5){
                    TE.waittime = 1;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
            }
            else if (te instanceof MechanicalMinerTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (MechanicalMinerTile) te, pos);
                return ActionResultType.CONSUME;
            }
        }
        return ActionResultType.CONSUME;
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
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof MechanicalMinerTile) {
            ItemStack itemStack = new ItemStack(this);
            CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
            itemStack.setTagInfo("BlockEntityTag", compoundNBT);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        if (event.getKey() == GLFW.GLFW_KEY_LEFT_ALT) {
            if (event.getAction() == GLFW.GLFW_PRESS) {
                keyPressed = true;
            }
            else if (event.getAction() == GLFW.GLFW_RELEASE) {
                keyPressed = false;
            }
        }
    }
}
