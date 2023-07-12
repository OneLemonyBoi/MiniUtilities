package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.init.ItemList;

public class UnstableShears extends ShearsItem {
    public UnstableShears(Item.Properties builder) {
        super(builder);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 1;
    }

    public static void instantShear(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getItemStack().getItem() instanceof TieredItem item)) return;
        if (event.getLevel().isClientSide()) return;
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        Level world = player.level();
        BlockPos pos = event.getPos();
        if (player.isShiftKeyDown() && stack.getItem() == ItemList.UnstableShears.get() && TierSortingRegistry.isCorrectTierForDrops(item.getTier(), world.getBlockState(pos))) {
            player.addItem(new ItemStack(world.getBlockState(pos).getBlock()));
            world.destroyBlock(pos, false);
        }
    }
}
