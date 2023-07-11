package onelemonyboi.miniutilities.blocks.earth;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.init.BlockList;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockColorManager {
    @SubscribeEvent
    public static void onItemColorsInit(RegisterColorHandlersEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();

        ItemColor itemBlockColourHandler = (stack, tintIndex) ->
        {
            BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(state, null, null, tintIndex);
        };
        if (itemBlockColourHandler != null) {
            event.register(itemBlockColourHandler, BlockList.CursedEarth.get());
            event.register(itemBlockColourHandler, BlockList.BlessedEarth.get());
            event.register(itemBlockColourHandler, BlockList.BlursedEarth.get());
        }
    }

    @SubscribeEvent
    public static void onBlockColorsInit(RegisterColorHandlersEvent.Block event) {
        final BlockColors blockColors = event.getBlockColors();

        BlockColor iBlockColor = (blockState, iEnviromentBlockReader, blockPos, i) -> Integer.decode("#222222");
        BlockColor iBlockColorBlessed = (blockState, iEnviromentBlockReader, blockPos, i) -> Integer.decode("#FFFFFF");
        BlockColor iBlockColorBlursed = (blockState, iEnviromentBlockReader, blockPos, i) -> Integer.decode("#919191");
        event.register(iBlockColor, BlockList.CursedEarth.get());
        event.register(iBlockColorBlessed, BlockList.BlessedEarth.get());
        event.register(iBlockColorBlursed, BlockList.BlursedEarth.get());
    }
}