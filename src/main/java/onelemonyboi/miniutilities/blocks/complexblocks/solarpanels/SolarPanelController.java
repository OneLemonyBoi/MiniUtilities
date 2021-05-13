package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nullable;

public class SolarPanelController extends Block {
    public SolarPanelController() {
        super(Properties.create(Material.IRON).hardnessAndResistance(4f).sound(SoundType.METAL));
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
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        ItemStack itemStack = new ItemStack(this);
        CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
        itemStack.setTagInfo("BlockEntityTag", compoundNBT);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}