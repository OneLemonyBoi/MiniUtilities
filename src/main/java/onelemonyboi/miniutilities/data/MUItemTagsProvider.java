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
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        getOrCreateBuilder(ModTags.Items.DUSTS_ENDER).add(ItemList.EnderDust.get());
        getOrCreateBuilder(Tags.Items.DUSTS).addTag(ModTags.Items.DUSTS_ENDER);
        getOrCreateBuilder(Tags.Items.RODS_BLAZE).add(ItemList.FlameLily.get());
    }
}
