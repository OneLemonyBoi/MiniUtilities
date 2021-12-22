package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SolarPanelController extends Block {
    public SolarPanelController() {
        super(Properties.create(Material.IRON).hardnessAndResistance(4f).sound(SoundType.METAL));
    }

    public void duplicateControllerChecker(World world, BlockPos pos, List<BlockPos> blockPosList) {
        for (Direction d : Direction.Plane.HORIZONTAL) {
            if (!world.isAreaLoaded(pos.offset(d), 1) || blockPosList.contains(pos.offset(d))) continue;
            BlockState state = world.getBlockState(pos.offset(d));
            if (state.getBlock() instanceof SolarPanelBlock || state.getBlock() instanceof LunarPanelBlock) {
                blockPosList.add(pos.offset(d));
                duplicateControllerChecker(world, pos.offset(d), blockPosList);
            }
            if (state.getBlock() instanceof SolarPanelController) world.destroyBlock(pos.offset(d), true);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        duplicateControllerChecker(world, pos, new ArrayList<BlockPos>(){{add(pos);}});
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TEList.SolarPanelControllerTile.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (worldIn.isRemote()) {
            return;
        }

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        ItemStack itemStack = new ItemStack(this);
        CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
        compoundNBT.remove("x");
        compoundNBT.remove("y");
        compoundNBT.remove("z");
        itemStack.setTagInfo("BlockEntityTag", compoundNBT);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}