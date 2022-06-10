package onelemonyboi.miniutilities.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.MiniUtilities;

public class ModTags {
    public static final class Blocks {
        public static final TagKey<Block> ORES_ENDER = forge("ores/ender");
        public static final TagKey<Block> STORAGE_BLOCKS_ENDER_PEARL = forge("storage_blocks/ender_pearl");
        public static final TagKey<Block> STORAGE_BLOCKS_UNSTABLE = forge("storage_blocks/unstable");

        // LAPIS CAELESTIS
        public static final TagKey<Block> LAPIS_CAELESTIS = forge("lapis_caelestis");
        public static final TagKey<Block> WHITE_LAPIS_CAELESTIS = forge("lapis_caelestis/white");
        public static final TagKey<Block> LIGHT_GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/light_gray");
        public static final TagKey<Block> GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/gray");
        public static final TagKey<Block> BLACK_LAPIS_CAELESTIS = forge("lapis_caelestis/black");
        public static final TagKey<Block> RED_LAPIS_CAELESTIS = forge("lapis_caelestis/red");
        public static final TagKey<Block> ORANGE_LAPIS_CAELESTIS = forge("lapis_caelestis/orange");
        public static final TagKey<Block> YELLOW_LAPIS_CAELESTIS = forge("lapis_caelestis/yellow");
        public static final TagKey<Block> LIME_LAPIS_CAELESTIS = forge("lapis_caelestis/lime");
        public static final TagKey<Block> GREEN_LAPIS_CAELESTIS = forge("lapis_caelestis/green");
        public static final TagKey<Block> LIGHT_BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/light_blue");
        public static final TagKey<Block> CYAN_LAPIS_CAELESTIS = forge("lapis_caelestis/cyan");
        public static final TagKey<Block> BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/blue");
        public static final TagKey<Block> PURPLE_LAPIS_CAELESTIS = forge("lapis_caelestis/purple");
        public static final TagKey<Block> MAGENTA_LAPIS_CAELESTIS = forge("lapis_caelestis/magenta");
        public static final TagKey<Block> PINK_LAPIS_CAELESTIS = forge("lapis_caelestis/pink");
        public static final TagKey<Block> BROWN_LAPIS_CAELESTIS = forge("lapis_caelestis/brown");

        public static TagKey<Block> forge(String path) {
            return BlockTags.create(new ResourceLocation("forge", path));
        }

        public static TagKey<Block> mod(String path) {
            return BlockTags.create(new ResourceLocation(MiniUtilities.MOD_ID, path));
        }
    }

    public static final class Items {
        public static final TagKey<Item> ORES_ENDER = forge("ores/ender");
        public static final TagKey<Item> DUSTS_ENDER = forge("dusts/ender");
        public static final TagKey<Item> STORAGE_BLOCKS_ENDER_PEARL = forge("storage_blocks/ender_pearl");
        public static final TagKey<Item> STORAGE_BLOCKS_UNSTABLE = forge("storage_blocks/unstable");
        public static final TagKey<Item> ANGELRING = forge("angelring");

        // LAPIS CAELESTIS
        public static final TagKey<Item> LAPIS_CAELESTIS = forge("lapis_caelestis");
        public static final TagKey<Item> WHITE_LAPIS_CAELESTIS = forge("lapis_caelestis/white");
        public static final TagKey<Item> LIGHT_GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/light_gray");
        public static final TagKey<Item> GRAY_LAPIS_CAELESTIS = forge("lapis_caelestis/gray");
        public static final TagKey<Item> BLACK_LAPIS_CAELESTIS = forge("lapis_caelestis/black");
        public static final TagKey<Item> RED_LAPIS_CAELESTIS = forge("lapis_caelestis/red");
        public static final TagKey<Item> ORANGE_LAPIS_CAELESTIS = forge("lapis_caelestis/orange");
        public static final TagKey<Item> YELLOW_LAPIS_CAELESTIS = forge("lapis_caelestis/yellow");
        public static final TagKey<Item> LIME_LAPIS_CAELESTIS = forge("lapis_caelestis/lime");
        public static final TagKey<Item> GREEN_LAPIS_CAELESTIS = forge("lapis_caelestis/green");
        public static final TagKey<Item> LIGHT_BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/light_blue");
        public static final TagKey<Item> CYAN_LAPIS_CAELESTIS = forge("lapis_caelestis/cyan");
        public static final TagKey<Item> BLUE_LAPIS_CAELESTIS = forge("lapis_caelestis/blue");
        public static final TagKey<Item> PURPLE_LAPIS_CAELESTIS = forge("lapis_caelestis/purple");
        public static final TagKey<Item> MAGENTA_LAPIS_CAELESTIS = forge("lapis_caelestis/magenta");
        public static final TagKey<Item> PINK_LAPIS_CAELESTIS = forge("lapis_caelestis/pink");
        public static final TagKey<Item> BROWN_LAPIS_CAELESTIS = forge("lapis_caelestis/brown");

        public static final TagKey<Item> UPGRADES_SPEED = forge("upgrades/speed");
        public static final TagKey<Item> UPGRADES = forge("upgrades");

        public static final TagKey<Item> EXPERIENCE_CONTAINERS = forge("experience_containers");

        private static TagKey<Item> forge(String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(new ResourceLocation(MiniUtilities.MOD_ID, path));
        }
    }
}
