package onelemonyboi.miniutilities.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import onelemonyboi.miniutilities.MiniUtilities;

public class ModTags {
    public static final class Blocks {
        public static final ITag.INamedTag<Block> ORES_ENDER = forge("ores/ender");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_ENDER_PEARL = forge("storage_blocks/ender_pearl");

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

        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation(MiniUtilities.MOD_ID, path).toString());
        }
    }
}
