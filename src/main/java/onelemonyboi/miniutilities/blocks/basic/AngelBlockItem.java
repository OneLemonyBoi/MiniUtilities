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
        super(block, new Item.Properties().maxStackSize(64).group(CreativeTab.getInstance()));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if (!world.isRemote){
            double x, y, z;
            switch(Direction.getFacingFromVector(player.getLookVec().x, player.getLookVec().y, player.getLookVec().z))
            {
                case DOWN:
                    x = player.getPosX() + 3 * player.getLookVec().x;
                    y = player.getEyeHeight() + player.getPosY() + 3 * player.getLookVec().y + 1;
                    z = player.getPosZ() + 3 * player.getLookVec().z;
                    break;
                case UP:
                    x = player.getPosX() + 3 * player.getLookVec().x;
                    y = player.getEyeHeight() + player.getPosY() + 3 * player.getLookVec().y - 1;
                    z = player.getPosZ() + 3 * player.getLookVec().z;
                    break;
                case NORTH:
                    x = player.getPosX() + 3 * player.getLookVec().x;
                    y = player.getEyeHeight() + player.getPosY() + 3 * player.getLookVec().y;
                    z = player.getPosZ() + 3 * player.getLookVec().z + 1;
                    break;
                case SOUTH:
                    x = player.getPosX() + 3 * player.getLookVec().x;
                    y = player.getEyeHeight() + player.getPosY() + 3 * player.getLookVec().y;
                    z = player.getPosZ() + 3 * player.getLookVec().z - 1;
                    break;
                case WEST:
                    x = player.getPosX() + 3 * player.getLookVec().x + 1;
                    y = player.getEyeHeight() + player.getPosY() + 3 * player.getLookVec().y;
                    z = player.getPosZ() + 3 * player.getLookVec().z;
                    break;
                case EAST:
                    x = player.getPosX() + 3 * player.getLookVec().x - 1;
                    y = player.getEyeHeight() + player.getPosY() + 3 * player.getLookVec().y;
                    z = player.getPosZ() + 3 * player.getLookVec().z;
                    break;
                default:
                    x = player.getPosX() + 3 * player.getLookVec().x;
                    y = player.getEyeHeight() + player.getPosY() + 3 * player.getLookVec().y;
                    z = player.getPosZ() + 3 * player.getLookVec().z;
            }
            BlockPos pos = new BlockPos(x,y,z);

            if (world.isAirBlock(pos) || !world.getFluidState(pos).isEmpty()) {
                world.setBlockState(pos, BlockList.AngelBlock.get().getDefaultState());
                if (!player.abilities.isCreativeMode)
                    player.getHeldItem(hand).shrink(1);
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}
