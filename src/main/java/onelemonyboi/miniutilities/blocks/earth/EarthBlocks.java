package onelemonyboi.miniutilities.blocks.earth;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.world.Config;

public class EarthBlocks {
    public static void convertCursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enable_cursed_earth.get()) return;
        PlayerEntity playerEntity = event.getPlayer();
        World world = playerEntity.world;
        BlockPos pos = event.getPos();
        if (playerEntity.isSneaking() && !world.isRemote && event.getItemStack().getItem() == Items.WITHER_ROSE && world.getBlockState(pos).getBlock() == Blocks.DIRT) {
            world.setBlockState(pos, BlockList.CursedEarth.get().getDefaultState());
        }
    }

    public static void convertBlessed(PlayerInteractEvent.RightClickBlock e) {
        if (!Config.enable_blessed_earth.get()) return;
        PlayerEntity p = e.getPlayer();
        World w = p.world;
        BlockPos pos = e.getPos();
        if (p.isSneaking() && !w.isRemote && Tags.Items.STORAGE_BLOCKS_IRON.contains(e.getItemStack().getItem()) && w.getBlockState(pos).getBlock() == Blocks.DIRT) {
            w.setBlockState(pos, BlockList.BlessedEarth.get().getDefaultState());
        }
    }

    public static void convertBlursed(PlayerInteractEvent.RightClickBlock e) {
        if (!Config.enable_blursed_earth.get()) return;
        PlayerEntity p = e.getPlayer();
        World w = p.world;
        BlockPos pos = e.getPos();
        if (p.isSneaking() && !w.isRemote && e.getItemStack().getItem() == ItemList.UnstableIngot.get() && w.getBlockState(pos).getBlock() == Blocks.DIRT) {
            w.setBlockState(pos, BlockList.BlursedEarth.get().getDefaultState());
        }
    }
}
