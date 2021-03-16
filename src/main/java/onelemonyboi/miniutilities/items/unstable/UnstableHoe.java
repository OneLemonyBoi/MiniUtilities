package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.Sets;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.init.ItemList;

import javax.annotation.Nonnull;
import java.util.Set;

public class UnstableHoe extends HoeItem {
    public UnstableHoe(IItemTier materialIn, int damage, float attackSpeed, Item.Properties properties) {
        super(materialIn, damage, attackSpeed, properties);
    }

    public static void hoeTransformation(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        PlayerEntity player = event.getPlayer();
        World world = player.world;
        BlockPos pos = event.getPos();
        if (!player.isSneaking() && stack.getItem() == ItemList.UnstableHoe.get()){
            if (world.getBlockState(pos).getBlock() == Blocks.COBBLESTONE && !world.isRemote){ world.setBlockState(pos, Blocks.STONE.getDefaultState()); }
        }
    }

    private static final Set<ToolType> hoe = Sets.newHashSet(ToolType.HOE);

    @Nonnull
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return hoe;
    }
}
