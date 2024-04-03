package onelemonyboi.miniutilities.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import onelemonyboi.miniutilities.MiniUtilities;

@Mod.EventBusSubscriber(modid = MiniUtilities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new BlockState(gen, existingFileHelper));
        gen.addProvider(new ItemModel(gen, existingFileHelper));
        BlockTags blockTags = new BlockTags(gen, existingFileHelper);
        gen.addProvider(blockTags);
        gen.addProvider(new ItemTags(gen, blockTags, existingFileHelper));
        gen.addProvider(new LootTable(gen));
        gen.addProvider(new Recipe(gen));
        gen.addProvider(new LootModifier(gen));
    }
}
