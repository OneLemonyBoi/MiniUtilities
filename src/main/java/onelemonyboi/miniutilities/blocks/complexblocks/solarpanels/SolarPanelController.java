package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
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
}