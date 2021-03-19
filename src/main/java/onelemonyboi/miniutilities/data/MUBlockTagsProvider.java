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

        // LAPIS CAELESTIS
        getOrCreateBuilder(ModTags.Blocks.WHITE_LAPIS_CAELESTIS).add(BlockList.WhiteLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.WHITE_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.LIGHT_GRAY_LAPIS_CAELESTIS).add(BlockList.LightGrayLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.LIGHT_GRAY_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.GRAY_LAPIS_CAELESTIS).add(BlockList.GrayLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.GRAY_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.BLACK_LAPIS_CAELESTIS).add(BlockList.BlackLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.BLACK_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.RED_LAPIS_CAELESTIS).add(BlockList.RedLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.RED_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.ORANGE_LAPIS_CAELESTIS).add(BlockList.OrangeLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.ORANGE_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.YELLOW_LAPIS_CAELESTIS).add(BlockList.YellowLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.YELLOW_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.LIME_LAPIS_CAELESTIS).add(BlockList.LimeLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.LIME_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.GREEN_LAPIS_CAELESTIS).add(BlockList.GreenLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.GREEN_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.LIGHT_BLUE_LAPIS_CAELESTIS).add(BlockList.LightBlueLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.LIGHT_BLUE_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.CYAN_LAPIS_CAELESTIS).add(BlockList.CyanLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.CYAN_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.BLUE_LAPIS_CAELESTIS).add(BlockList.BlueLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.BLUE_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.PURPLE_LAPIS_CAELESTIS).add(BlockList.PurpleLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.PURPLE_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.MAGENTA_LAPIS_CAELESTIS).add(BlockList.MagentaLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.MAGENTA_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.PINK_LAPIS_CAELESTIS).add(BlockList.PinkLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.PINK_LAPIS_CAELESTIS);
        getOrCreateBuilder(ModTags.Blocks.BROWN_LAPIS_CAELESTIS).add(BlockList.BrownLapisCaelestis.get());
        getOrCreateBuilder(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.BROWN_LAPIS_CAELESTIS);
    }
}
