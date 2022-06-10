package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;

public class UnstableHoe extends HoeItem {
    public UnstableHoe(Tier materialIn, int damage, float attackSpeed, Item.Properties properties) {
        super(materialIn, damage, attackSpeed, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown() && context.getHand().equals(InteractionHand.MAIN_HAND)) {
            Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
            if (block.equals(Blocks.STONE)) block = net.minecraft.world.level.block.Blocks.COBBLESTONE;
            else if (block.equals(net.minecraft.world.level.block.Blocks.COBBLESTONE)) block = Blocks.GRAVEL;
            else if (block.equals(Blocks.GRAVEL)) block = net.minecraft.world.level.block.Blocks.COARSE_DIRT;
            else if (block.equals(Blocks.COARSE_DIRT)) block = net.minecraft.world.level.block.Blocks.CLAY;
            else if (block.equals(net.minecraft.world.level.block.Blocks.CLAY)) block = Blocks.SAND;
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), block.defaultBlockState());
            return InteractionResult.CONSUME;
        }
        return super.useOn(context);
    }
}
