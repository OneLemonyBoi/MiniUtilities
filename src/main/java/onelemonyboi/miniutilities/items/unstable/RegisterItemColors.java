package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public interface RegisterItemColors {
    default void addItemColors(ItemColors itemColors, BlockColors blockColors) {

    }
}
