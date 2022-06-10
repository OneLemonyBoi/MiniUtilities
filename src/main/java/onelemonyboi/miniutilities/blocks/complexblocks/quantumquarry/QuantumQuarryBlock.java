package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.network.NetworkHooks;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviors;
import org.jetbrains.annotations.Nullable;

public class QuantumQuarryBlock extends BlockBase {
    public QuantumQuarryBlock() {
        super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL), BlockBehaviors.quantumQuarry);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(level, type, TEList.QuantumQuarryTile.get(), QuantumQuarryTile::serverTick, null);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if (player.isShiftKeyDown()) {return InteractionResult.CONSUME;}
            if (te instanceof QuantumQuarryTile qqTe && player.getItemInHand(handIn).is(ModTags.Items.UPGRADES_SPEED)) {
                if (qqTe.waittime > 25) {
                    qqTe.waittime = qqTe.waittime - 25;
                    player.getItemInHand(handIn).shrink(1);
                    qqTe.timer = 0;
                }
                else if (qqTe.waittime == 25){
                    qqTe.waittime = 1;
                    player.getItemInHand(handIn).shrink(1);
                    qqTe.timer = 0;
                }
            }
            else if (te instanceof QuantumQuarryTile) {
                NetworkHooks.openGui((ServerPlayer) player, (QuantumQuarryTile) te, pos);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.CONSUME;
    }

    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.getWorld().isClientSide()) {
            if (event.getWorld().getBlockEntity(event.getPos()) instanceof QuantumQuarryTile && event.getPlayer().isShiftKeyDown() && event.getPlayer().getItemInHand(event.getHand()).isEmpty()) {
                QuantumQuarryTile TE = (QuantumQuarryTile) event.getWorld().getBlockEntity(event.getPos());
                if (TE.waittime > 1 && TE.waittime < 1200) {
                    TE.waittime = TE.waittime + 25;
                    Containers.dropItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new net.minecraft.world.item.ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
                else if (TE.waittime == 1){
                    TE.waittime = 25;
                    Containers.dropItemStack(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
            }
        }
    }
}
