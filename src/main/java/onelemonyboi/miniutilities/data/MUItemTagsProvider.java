package onelemonyboi.miniutilities.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.ItemList;

public class MUItemTagsProvider extends ItemTagsProvider {
    public MUItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, MiniUtilities.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        copy(ModTags.Blocks.ORES_ENDER, ModTags.Items.ORES_ENDER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL, ModTags.Items.STORAGE_BLOCKS_ENDER_PEARL);
        copy(ModTags.Blocks.STORAGE_BLOCKS_UNSTABLE, ModTags.Items.STORAGE_BLOCKS_UNSTABLE);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(ModTags.Blocks.LAPIS_CAELESTIS, ModTags.Items.LAPIS_CAELESTIS);
        copy(ModTags.Blocks.WHITE_LAPIS_CAELESTIS, ModTags.Items.WHITE_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.LIGHT_GRAY_LAPIS_CAELESTIS, ModTags.Items.LIGHT_GRAY_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.GRAY_LAPIS_CAELESTIS, ModTags.Items.GRAY_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.BLACK_LAPIS_CAELESTIS, ModTags.Items.BLACK_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.RED_LAPIS_CAELESTIS, ModTags.Items.RED_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.ORANGE_LAPIS_CAELESTIS, ModTags.Items.ORANGE_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.YELLOW_LAPIS_CAELESTIS, ModTags.Items.YELLOW_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.LIME_LAPIS_CAELESTIS, ModTags.Items.LIME_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.GREEN_LAPIS_CAELESTIS, ModTags.Items.GREEN_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.LIGHT_BLUE_LAPIS_CAELESTIS, ModTags.Items.LIGHT_BLUE_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.CYAN_LAPIS_CAELESTIS, ModTags.Items.CYAN_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.BLUE_LAPIS_CAELESTIS, ModTags.Items.BLUE_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.PURPLE_LAPIS_CAELESTIS, ModTags.Items.PURPLE_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.MAGENTA_LAPIS_CAELESTIS, ModTags.Items.MAGENTA_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.PINK_LAPIS_CAELESTIS, ModTags.Items.PINK_LAPIS_CAELESTIS);
        copy(ModTags.Blocks.BROWN_LAPIS_CAELESTIS, ModTags.Items.BROWN_LAPIS_CAELESTIS);

        getOrCreateBuilder(ModTags.Items.DUSTS_ENDER).add(ItemList.EnderDust.get());
        getOrCreateBuilder(Tags.Items.DUSTS).addTag(ModTags.Items.DUSTS_ENDER);
        getOrCreateBuilder(Tags.Items.RODS_BLAZE).add(ItemList.FlameLily.get());
        getOrCreateBuilder(ModTags.Items.ANGELRING).add(ItemList.BaseAngelRing.get());
        getOrCreateBuilder(ModTags.Items.ANGELRING).add(ItemList.BatAngelRing.get());
        getOrCreateBuilder(ModTags.Items.ANGELRING).add(ItemList.PeacockAngelRing.get());
        getOrCreateBuilder(ModTags.Items.ANGELRING).add(ItemList.EnderDragonAngelRing.get());
        getOrCreateBuilder(ModTags.Items.ANGELRING).add(ItemList.FeatherAngelRing.get());
        getOrCreateBuilder(ModTags.Items.ANGELRING).add(ItemList.GoldAngelRing.get());
    }
}
