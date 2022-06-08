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
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviours;

import java.util.UUID;

import static onelemonyboi.miniutilities.misc.KeyBindingsHandler.keyBindingPressed;

public class MechanicalMinerBlock extends BlockBase {
    public MechanicalMinerBlock() {
        super(AbstractBlock.Properties.of(Material.METAL).sound(SoundType.METAL), BlockBehaviours.mechanicalMiner);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide()) {
            TileEntity te = worldIn.getBlockEntity(pos);
            if (player.isShiftKeyDown()) {return ActionResultType.CONSUME;}

            if (!(te instanceof MechanicalMinerTile)) {return super.use(state, worldIn, pos, player, handIn, hit);}
            MechanicalMinerTile TE = ((MechanicalMinerTile) te);
            if (ModTags.Items.UPGRADES_SPEED.contains(player.getItemInHand(handIn).getItem())) {
                if (TE.waittime > 5) {
                    TE.waittime = TE.waittime - 5;
                    player.getItemInHand(handIn).shrink(1);
                    TE.timer = 0;
                }
                else if (TE.waittime == 5){
                    TE.waittime = 1;
                    player.getItemInHand(handIn).shrink(1);
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
        if (!event.getWorld().isClientSide()) {
            if (event.getWorld().getBlockEntity(event.getPos()) instanceof MechanicalMinerTile && event.getPlayer().isShiftKeyDown() && event.getPlayer().getItemInHand(event.getHand()).isEmpty()) {
                MechanicalMinerTile TE = (MechanicalMinerTile) (event.getWorld().getBlockEntity(event.getPos()));
                if (TE.waittime > 1 && TE.waittime < 20) {
                    TE.waittime = TE.waittime + 5;
                    InventoryHelper.dropItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                } else if (TE.waittime == 1) {
                    TE.waittime = 5;
                    InventoryHelper.dropItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
            }
        }
    }
}
