package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;

import java.util.UUID;

import static onelemonyboi.miniutilities.misc.KeyBindingsHandler.keyBindingPressed;

public class QuantumQuarryBlock extends Block {
    public static Boolean keyPressed = false;

    public QuantumQuarryBlock() {
        super(Properties.create(Material.IRON).hardnessAndResistance(3F)
                .sound(SoundType.METAL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TEList.QuantumQuarryTile.get().create();
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        super.onBlockClicked(state, worldIn, pos, player);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof QuantumQuarryTile && keyBindingPressed) {
                QuantumQuarryTile TE = ((QuantumQuarryTile) te);
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
            else if (te instanceof QuantumQuarryTile && ModTags.Items.UPGRADES_SPEED.contains(player.getHeldItem(handIn).getItem())) {
                QuantumQuarryTile TE = ((QuantumQuarryTile) te);
                if (TE.waittime > 25) {
                    TE.waittime = TE.waittime - 25;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
                else if (TE.waittime == 25){
                    TE.waittime = 1;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
            }
            else if (te instanceof QuantumQuarryTile) {
                QuantumQuarryTile TE = ((QuantumQuarryTile) te);
                if (!TE.event) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (QuantumQuarryTile) te, pos);
                    return ActionResultType.CONSUME;
                }
            }
        }
        return ActionResultType.CONSUME;
    }

    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.getWorld().isRemote()) {
            if (event.getWorld().getTileEntity(event.getPos()) instanceof QuantumQuarryTile && Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown()) {
                QuantumQuarryTile TE = (QuantumQuarryTile) (event.getWorld().getTileEntity(event.getPos()));
                PlayerEntity player = event.getPlayer();
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
                TE.event = true;
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof QuantumQuarryTile) {
            ItemStack itemStack = new ItemStack(this);
            CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemList.SpeedUpgrade.get(), Math.round((1200 - compoundNBT.getInt("WaitTime")) / 25.0F)));
            compoundNBT.putInt("WaitTime", 1200);
            itemStack.setTagInfo("BlockEntityTag", compoundNBT);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}
