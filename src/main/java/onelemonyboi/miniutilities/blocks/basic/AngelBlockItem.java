package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.init.BlockList;

public class AngelBlockItem extends BlockItem {
    public AngelBlockItem(Block block)
    {
        super(block, new Item.Properties().stacksTo(64).tab(CreativeTab.getInstance()));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        if (!world.isClientSide){
            double x, y, z;
            switch(Direction.getNearest(player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z))
            {
                case DOWN:
                    x = player.getX() + 3 * player.getLookAngle().x;
                    y = player.getEyeHeight() + player.getY() + 3 * player.getLookAngle().y + 1;
                    z = player.getZ() + 3 * player.getLookAngle().z;
                    break;
                case UP:
                    x = player.getX() + 3 * player.getLookAngle().x;
                    y = player.getEyeHeight() + player.getY() + 3 * player.getLookAngle().y - 1;
                    z = player.getZ() + 3 * player.getLookAngle().z;
                    break;
                case NORTH:
                    x = player.getX() + 3 * player.getLookAngle().x;
                    y = player.getEyeHeight() + player.getY() + 3 * player.getLookAngle().y;
                    z = player.getZ() + 3 * player.getLookAngle().z + 1;
                    break;
                case SOUTH:
                    x = player.getX() + 3 * player.getLookAngle().x;
                    y = player.getEyeHeight() + player.getY() + 3 * player.getLookAngle().y;
                    z = player.getZ() + 3 * player.getLookAngle().z - 1;
                    break;
                case WEST:
                    x = player.getX() + 3 * player.getLookAngle().x + 1;
                    y = player.getEyeHeight() + player.getY() + 3 * player.getLookAngle().y;
                    z = player.getZ() + 3 * player.getLookAngle().z;
                    break;
                case EAST:
                    x = player.getX() + 3 * player.getLookAngle().x - 1;
                    y = player.getEyeHeight() + player.getY() + 3 * player.getLookAngle().y;
                    z = player.getZ() + 3 * player.getLookAngle().z;
                    break;
                default:
                    x = player.getX() + 3 * player.getLookAngle().x;
                    y = player.getEyeHeight() + player.getY() + 3 * player.getLookAngle().y;
                    z = player.getZ() + 3 * player.getLookAngle().z;
            }
            BlockPos pos = new BlockPos(x,y,z);

            if (world.isEmptyBlock(pos) || !world.getFluidState(pos).isEmpty()) {
                world.setBlockAndUpdate(pos, BlockList.AngelBlock.get().defaultBlockState());
                if (!player.abilities.instabuild)
                    player.getItemInHand(hand).shrink(1);
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getItemInHand(hand));
    }
}
