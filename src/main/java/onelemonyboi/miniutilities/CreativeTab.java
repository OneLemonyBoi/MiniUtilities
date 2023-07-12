package onelemonyboi.miniutilities;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.init.BlockList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTab {
    public static final HashMap<CreativeModeTab, List<Supplier<ItemLike>>> TAB_MAP = new HashMap<>();
    public static void setTab(Supplier<ItemLike> item, CreativeModeTab tab) {
        if (TAB_MAP.containsKey(tab)) {
            TAB_MAP.get(tab).add(item);
        }
        else {
            ArrayList<Supplier<ItemLike>> array = new ArrayList<>();
            array.add(item);
            TAB_MAP.put(tab, array);
        }
    }

    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        for (CreativeModeTab tab : TAB_MAP.keySet()) {
            if (event.getTab() == tab) {
                for (Supplier<ItemLike> item : TAB_MAP.get(tab)) {
                    event.accept(item.get().asItem());
                }
            }
        }
    }
}
