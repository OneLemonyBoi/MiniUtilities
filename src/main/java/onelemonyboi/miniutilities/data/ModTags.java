package onelemonyboi.miniutilities.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.MiniUtilities;

public class ModTags {
    public static final class Blocks {
        public static final ITag.INamedTag<Block> ORES_ENDER = forge("ores/ender");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_ENDER_PEARL = forge("storage_blocks/ender_pearl");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_UNSTABLE = forge("storage_blocks/unstable");

        // LAPIS CAELESTIS
        public static final ITag.INamedTag<Block> LAPIS_CAELESTIS = forge("lapis_caelestis");
        public static final ITag.INamedTag<Block> WHITE_LAPIS_CAELESTIS = forge("lapis_caelestis/white");
        public static final ITag.INamedTag<Block> LIGHT_GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/light_gray");
        public static final ITag.INamedTag<Block> GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/gray");
        public static final ITag.INamedTag<Block> BLACK_LAPIS_CAELESTIS = forge("lapis_caelestis/black");
        public static final ITag.INamedTag<Block> RED_LAPIS_CAELESTIS = forge("lapis_caelestis/red");
        public static final ITag.INamedTag<Block> ORANGE_LAPIS_CAELESTIS = forge("lapis_caelestis/orange");
        public static final ITag.INamedTag<Block> YELLOW_LAPIS_CAELESTIS = forge("lapis_caelestis/yellow");
        public static final ITag.INamedTag<Block> LIME_LAPIS_CAELESTIS = forge("lapis_caelestis/lime");
        public static final ITag.INamedTag<Block> GREEN_LAPIS_CAELESTIS = forge("lapis_caelestis/green");
        public static final ITag.INamedTag<Block> LIGHT_BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/light_blue");
        public static final ITag.INamedTag<Block> CYAN_LAPIS_CAELESTIS = forge("lapis_caelestis/cyan");
        public static final ITag.INamedTag<Block> BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/blue");
        public static final ITag.INamedTag<Block> PURPLE_LAPIS_CAELESTIS = forge("lapis_caelestis/purple");
        public static final ITag.INamedTag<Block> MAGENTA_LAPIS_CAELESTIS = forge("lapis_caelestis/magenta");
        public static final ITag.INamedTag<Block> PINK_LAPIS_CAELESTIS = forge("lapis_caelestis/pink");
        public static final ITag.INamedTag<Block> BROWN_LAPIS_CAELESTIS = forge("lapis_caelestis/brown");

        private static ITag.INamedTag<Block> forge(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Block> mod(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation(MiniUtilities.MOD_ID, path).toString());
        }
    }

    public static final class Items {
        public static final ITag.INamedTag<Item> ORES_ENDER = forge("ores/ender");
        public static final ITag.INamedTag<Item> DUSTS_ENDER = forge("dusts/ender");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_ENDER_PEARL = forge("storage_blocks/ender_pearl");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_UNSTABLE = forge("storage_blocks/unstable");
        public static final ITag.INamedTag<Item> ANGELRING = forge("angelring");

        // LAPIS CAELESTIS
        public static final ITag.INamedTag<Item> LAPIS_CAELESTIS = forge("lapis_caelestis");
        public static final ITag.INamedTag<Item> WHITE_LAPIS_CAELESTIS = forge("lapis_caelestis/white");
        public static final ITag.INamedTag<Item> LIGHT_GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/light_gray");
        public static final ITag.INamedTag<Item> GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/gray");
        public static final ITag.INamedTag<Item> BLACK_LAPIS_CAELESTIS = forge("lapis_caelestis/black");
        public static final ITag.INamedTag<Item> RED_LAPIS_CAELESTIS = forge("lapis_caelestis/red");
        public static final ITag.INamedTag<Item> ORANGE_LAPIS_CAELESTIS = forge("lapis_caelestis/orange");
        public static final ITag.INamedTag<Item> YELLOW_LAPIS_CAELESTIS = forge("lapis_caelestis/yellow");
        public static final ITag.INamedTag<Item> LIME_LAPIS_CAELESTIS = forge("lapis_caelestis/lime");
        public static final ITag.INamedTag<Item> GREEN_LAPIS_CAELESTIS = forge("lapis_caelestis/green");
        public static final ITag.INamedTag<Item> LIGHT_BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/light_blue");
        public static final ITag.INamedTag<Item> CYAN_LAPIS_CAELESTIS = forge("lapis_caelestis/cyan");
        public static final ITag.INamedTag<Item> BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/blue");
        public static final ITag.INamedTag<Item> PURPLE_LAPIS_CAELESTIS = forge("lapis_caelestis/purple");
        public static final ITag.INamedTag<Item> MAGENTA_LAPIS_CAELESTIS = forge("lapis_caelestis/magenta");
        public static final ITag.INamedTag<Item> PINK_LAPIS_CAELESTIS = forge("lapis_caelestis/pink");
        public static final ITag.INamedTag<Item> BROWN_LAPIS_CAELESTIS = forge("lapis_caelestis/brown");

        public static final ITag.INamedTag<Item> WRENCH = forge("wrench");

        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation(MiniUtilities.MOD_ID, path).toString());
        }
    }
}
