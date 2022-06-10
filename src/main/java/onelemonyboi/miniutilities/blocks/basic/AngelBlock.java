package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class AngelBlock extends Block {
    public AngelBlock(Material material, Float hardness, Float resistance) {
        super(Properties.of(material).strength(hardness, resistance));
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(world, pos, state, player);
        if (!world.isClientSide && !player.getAbilities().instabuild) {
            player.addItem(new ItemStack(this));
        }
    }
}
