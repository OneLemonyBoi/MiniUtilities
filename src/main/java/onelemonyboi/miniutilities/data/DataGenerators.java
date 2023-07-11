package onelemonyboi.miniutilities.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.MiniUtilities;

@Mod.EventBusSubscriber(modid = MiniUtilities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeServer(), new BlockState(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new ItemModel(gen, existingFileHelper));
        BlockTags blockTags = new BlockTags(gen, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ItemTags(gen, blockTags, existingFileHelper));
        gen.addProvider(event.includeServer(), new LootTable(gen));
        gen.addProvider(event.includeServer(), new Recipe(gen));
        gen.addProvider(event.includeServer(), new LootModifier(gen));
    }
}
