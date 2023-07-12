package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer;

import net.minecraft.world.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.network.NetworkHooks;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviors;
import org.jetbrains.annotations.Nullable;

public class MechanicalMinerBlock extends BlockBase {
    public MechanicalMinerBlock() {
        super(Properties.of().sound(SoundType.METAL), BlockBehaviors.mechanicalMiner);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return (MenuProvider) level.getBlockEntity(pos);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(level, type, TEList.MechanicalMinerTile.get(), MechanicalMinerTile::serverTick, null);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if (player.isShiftKeyDown()) {return InteractionResult.CONSUME;}

            if (!(te instanceof MechanicalMinerTile)) {return super.use(state, worldIn, pos, player, handIn, hit);}
            MechanicalMinerTile TE = ((MechanicalMinerTile) te);
            if (player.getItemInHand(handIn).is(ModTags.Items.UPGRADES_SPEED)) {
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
                NetworkHooks.openScreen((ServerPlayer) player, (MechanicalMinerTile) te, pos);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.CONSUME;
    }

    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getLevel().getBlockEntity(event.getPos()) instanceof MechanicalMinerTile && event.getEntity().isShiftKeyDown() && event.getEntity().getItemInHand(event.getHand()).isEmpty()) {
                MechanicalMinerTile TE = (MechanicalMinerTile) (event.getLevel().getBlockEntity(event.getPos()));
                if (TE.waittime > 1 && TE.waittime < 20) {
                    TE.waittime = TE.waittime + 5;
                    Containers.dropItemStack(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                } else if (TE.waittime == 1) {
                    TE.waittime = 5;
                    Containers.dropItemStack(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new net.minecraft.world.item.ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
            }
        }
    }
}
