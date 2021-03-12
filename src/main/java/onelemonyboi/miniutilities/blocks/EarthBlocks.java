package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.items.BlockList;
import onelemonyboi.miniutilities.world.Configs;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

public class EarthBlocks {
    public static void convertCursed(PlayerInteractEvent.RightClickBlock e) {
        if (!Configs.enable_cursed_earth.get()) return;
        PlayerEntity p = e.getPlayer();
        World w = p.world;
        BlockPos pos = e.getPos();
        if (p.isSneaking() && !w.isRemote && e.getItemStack().getItem() == Items.WITHER_ROSE && w.getBlockState(pos).getBlock() == Blocks.DIRT) {
            w.setBlockState(pos, BlockList.BlessedEarth.get().getDefaultState());
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
