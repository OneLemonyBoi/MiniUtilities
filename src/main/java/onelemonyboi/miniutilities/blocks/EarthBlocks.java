package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.items.BlockList;
import onelemonyboi.miniutilities.world.Configs;

public class EarthBlocks {
    public static void convertCursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Configs.enable_cursed_earth.get()) return;
        PlayerEntity playerEntity = event.getPlayer();
        World world = playerEntity.world;
        BlockPos pos = event.getPos();
        if (playerEntity.isSneaking() && !world.isRemote && event.getItemStack().getItem() == Items.WITHER_ROSE && world.getBlockState(pos).getBlock() == Blocks.DIRT) {
            world.setBlockState(pos, BlockList.BlessedEarth.get().getDefaultState());
        }
    }

    public static void convertBlessed(PlayerInteractEvent.RightClickBlock e) {
        if (!Configs.enable_blessed_earth.get()) return;
        PlayerEntity p = e.getPlayer();
        World w = p.world;
        BlockPos pos = e.getPos();
        if (p.isSneaking() && !w.isRemote && e.getItemStack().getItem() == Items.IRON_BLOCK && w.getBlockState(pos).getBlock() == Blocks.DIRT) {
            w.setBlockState(pos, BlockList.BlessedEarth.get().getDefaultState());
        }
    }
}
