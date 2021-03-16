package onelemonyboi.miniutilities.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;

public class MUBlockTagsProvider extends BlockTagsProvider {
    public MUBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, MiniUtilities.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(ModTags.Blocks.ORES_ENDER).add(BlockList.EnderOre.get());
        getOrCreateBuilder(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_ENDER);
        getOrCreateBuilder(ModTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL).add(BlockList.EnderPearlBlock.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL);
    }
}
