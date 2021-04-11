package onelemonyboi.miniutilities.blocks.complexblocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class DrumBlock extends ContainerBlock {
    public final int mb;

    public DrumBlock(int mb, Properties properties) {
        super(properties);
        this.mb = mb;
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack held = player.getHeldItem(hand);

        if (FluidUtil.interactWithFluidHandler(player, hand, world, pos, hit.getFace()) ||
                held.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new DrumTile(this.mb);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof DrumTile) {
            ItemStack itemStack = new ItemStack(this);
            double Randomizer = Math.random();
            CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
            itemStack.setTagInfo("BlockEntityTag", compoundNBT);
            ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + Randomizer, pos.getY(), pos.getZ() + Randomizer, itemStack);
            worldIn.addEntity(itemEntity);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}
