package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraft.block.AbstractBlock.Properties;

public class AngelBlock extends Block {
    public AngelBlock(Material material, Float hardness, Float resistance, Integer harvestLevel) {
        super(Properties
                .of(material)
                .strength(hardness, resistance)
                .harvestLevel(harvestLevel));
    }

    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.playerWillDestroy(world, pos, state, player);
        if (!world.isClientSide && !player.abilities.instabuild) {
            player.addItem(new ItemStack(this));
        }
    }
}
