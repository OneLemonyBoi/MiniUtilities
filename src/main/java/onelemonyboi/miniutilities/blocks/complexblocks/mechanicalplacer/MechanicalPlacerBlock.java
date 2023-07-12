package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.network.NetworkHooks;
import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.BlockBehaviors;
import org.jetbrains.annotations.Nullable;

public class MechanicalPlacerBlock extends BlockBase {
    public MechanicalPlacerBlock() {
        super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of().sound(SoundType.METAL), BlockBehaviors.mechanicalPlacer);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(level, type, TEList.MechanicalPlacerTile.get(), MechanicalPlacerTile::tick, null);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return (MenuProvider) level.getBlockEntity(pos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if (player.isShiftKeyDown()) {
                return InteractionResult.CONSUME;
            }
            if (te instanceof MechanicalPlacerTile mpTile && player.getItemInHand(handIn).is(ModTags.Items.UPGRADES_SPEED)) {
                if (mpTile.waittime > 5) {
                    mpTile.waittime = mpTile.waittime - 5;
                    player.getItemInHand(handIn).shrink(1);
                    mpTile.timer = 0;
                } else if (mpTile.waittime == 5) {
                    mpTile.waittime = 1;
                    player.getItemInHand(handIn).shrink(1);
                    mpTile.timer = 0;
                }
            } else if (te instanceof MechanicalPlacerTile) {
                NetworkHooks.openScreen((ServerPlayer) player, (MechanicalPlacerTile) te, pos);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.CONSUME;
    }

    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getLevel().getBlockEntity(event.getPos()) instanceof MechanicalPlacerTile && event.getEntity().isShiftKeyDown() && event.getEntity().getItemInHand(event.getHand()).isEmpty()) {
                MechanicalPlacerTile TE = (MechanicalPlacerTile) (event.getLevel().getBlockEntity(event.getPos()));
                if (TE.waittime > 1 && TE.waittime < 20) {
                    TE.waittime = TE.waittime + 5;
                    Containers.dropItemStack(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
                else if (TE.waittime == 1){
                    TE.waittime = 5;
                    Containers.dropItemStack(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new net.minecraft.world.item.ItemStack(ItemList.SpeedUpgrade.get()));
                    TE.timer = 0;
                }
            }
        }
    }
}
