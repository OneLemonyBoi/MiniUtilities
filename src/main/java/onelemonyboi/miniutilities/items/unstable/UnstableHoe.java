package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown() && context.getHand().equals(Hand.MAIN_HAND)) {
            Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
            if (block.equals(Blocks.STONE)) block = Blocks.COBBLESTONE;
            else if (block.equals(Blocks.COBBLESTONE)) block = Blocks.GRAVEL;
            else if (block.equals(Blocks.GRAVEL)) block = Blocks.COARSE_DIRT;
            else if (block.equals(Blocks.COARSE_DIRT)) block = Blocks.CLAY;
            else if (block.equals(Blocks.CLAY)) block = Blocks.SAND;
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), block.defaultBlockState());
            return ActionResultType.CONSUME;
        }
        return super.useOn(context);
    }

    private static final Set<ToolType> hoe = Sets.newHashSet(ToolType.HOE);

    @Nonnull
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return hoe;
    }
}
