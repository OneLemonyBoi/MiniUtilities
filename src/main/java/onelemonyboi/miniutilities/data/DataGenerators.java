package onelemonyboi.miniutilities.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.data.client.MUBlockStateProvider;
import onelemonyboi.miniutilities.data.client.MUItemModelProvider;

@Mod.EventBusSubscriber(modid = MiniUtilities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new MUBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new MUItemModelProvider(gen, existingFileHelper));

        MUBlockTagsProvider blockTags = new MUBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(blockTags);
        gen.addProvider(new MUItemTagsProvider(gen, blockTags, existingFileHelper));

        gen.addProvider(new MULootTableProvider(gen));
        gen.addProvider(new MURecipeProvider(gen));
    }
}
