package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviours;

import java.util.UUID;

import static onelemonyboi.miniutilities.misc.KeyBindingsHandler.keyBindingPressed;

public class MechanicalPlacerBlock extends BlockBase {
    public MechanicalPlacerBlock() {
        super(Properties.create(Material.IRON).sound(SoundType.METAL), BlockBehaviours.mechanicalPlacer);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (player.isSneaking()) {
                return ActionResultType.CONSUME;
            }
            if (te instanceof MechanicalPlacerTile && ModTags.Items.UPGRADES_SPEED.contains(player.getHeldItem(handIn).getItem())) {
                MechanicalPlacerTile TE = ((MechanicalPlacerTile) te);
                if (TE.waittime > 5) {
                    TE.waittime = TE.waittime - 5;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                } else if (TE.waittime == 5) {
                    TE.waittime = 1;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
            } else if (te instanceof MechanicalPlacerTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (MechanicalPlacerTile) te, pos);
                return ActionResultType.CONSUME;
            }
        }
        return ActionResultType.CONSUME;
    }

    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.getWorld().isRemote()) {
            if (event.getWorld().getTileEntity(event.getPos()) instanceof MechanicalPlacerTile && event.getPlayer().isSneaking() && event.getPlayer().getHeldItem(event.getHand()).isEmpty()) {
                MechanicalPlacerTile TE = (MechanicalPlacerTile) (event.getWorld().getTileEntity(event.getPos()));
                if (TE.waittime > 1 && TE.waittime < 20) {
                    TE.waittime = TE.waittime + 5;
                    InventoryHelper.spawnItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
                else if (TE.waittime == 1){
                    TE.waittime = 5;
                    InventoryHelper.spawnItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
            }
        }
    }
}
