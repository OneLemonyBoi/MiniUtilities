package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.init.ItemList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class UnstableShears extends ShearsItem {
    public UnstableShears(Item.Properties builder) {
        super(builder);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, @Nonnull ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        return 1;
    }

    private static final Set<ToolType> pickaxe = Sets.newHashSet(ToolType.PICKAXE);


    @Nonnull
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return pickaxe;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 20;
    }

    public static void instantShear(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        PlayerEntity player = event.getPlayer();
        World world = player.world;
        BlockPos pos = event.getPos();
        if (player.isSneaking() && !world.isRemote && stack.getItem() == ItemList.UnstableShears.get() && world.getBlockState(pos).getHarvestLevel() <= 2) {
            player.addItemStackToInventory(new ItemStack(world.getBlockState(pos).getBlock()));
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }
}
