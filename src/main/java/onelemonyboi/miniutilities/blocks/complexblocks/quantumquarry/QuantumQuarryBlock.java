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
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviours;

import java.util.UUID;

import static onelemonyboi.miniutilities.misc.KeyBindingsHandler.keyBindingPressed;

public class QuantumQuarryBlock extends BlockBase {
    public QuantumQuarryBlock() {
        super(Properties.create(Material.IRON).sound(SoundType.METAL), BlockBehaviours.quantumQuarry);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (player.isSneaking()) {return ActionResultType.CONSUME;}
            if (te instanceof QuantumQuarryTile && ModTags.Items.UPGRADES_SPEED.contains(player.getHeldItem(handIn).getItem())) {
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
                NetworkHooks.openGui((ServerPlayerEntity) player, (QuantumQuarryTile) te, pos);
                return ActionResultType.CONSUME;
            }
        }
        return ActionResultType.CONSUME;
    }

    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.getWorld().isRemote()) {
            if (event.getWorld().getTileEntity(event.getPos()) instanceof QuantumQuarryTile && event.getPlayer().isSneaking() && event.getPlayer().getHeldItem(event.getHand()).isEmpty()) {
                QuantumQuarryTile TE = (QuantumQuarryTile) event.getWorld().getTileEntity(event.getPos());
                if (TE.waittime > 1 && TE.waittime < 1200) {
                    TE.waittime = TE.waittime + 25;
                    InventoryHelper.spawnItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
                else if (TE.waittime == 1){
                    TE.waittime = 25;
                    InventoryHelper.spawnItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
            }
        }
    }
}
