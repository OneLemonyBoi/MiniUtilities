package onelemonyboi.miniutilities.blocks.earth;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.startup.Config;

public class EarthBlocks {
    public static void convertCursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableCursedEarth.get()) return;
        handleConvertEarth(event, Items.WITHER_ROSE::equals, BlockList.CursedEarth.get());
    }

    public static void convertBlessed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableBlessedEarth.get()) return;
        handleConvertEarth(event, Tags.Items.STORAGE_BLOCKS_IRON::contains, BlockList.BlessedEarth.get());
    }

    public static void convertBlursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableBlursedEarth.get()) return;
        handleConvertEarth(event, (item) -> ItemList.UnstableIngot.get().equals(item), BlockList.BlursedEarth.get());
    }

    private static void handleConvertEarth(PlayerInteractEvent.RightClickBlock event, ItemChecker itemChecker, Block defaultState) {
        PlayerEntity playerEntity = event.getPlayer();
        World world = playerEntity.world;
        BlockPos pos = event.getPos();

        if (playerEntity.isSneaking() && !world.isRemote && itemChecker.isItemValid(event.getItemStack().getItem()) && world.getBlockState(pos).getBlock() == Blocks.DIRT) {
            world.setBlockState(pos, defaultState.getDefaultState());
        }
    }

    @FunctionalInterface
    private interface ItemChecker {
        boolean isItemValid(Item item);
    }
}
