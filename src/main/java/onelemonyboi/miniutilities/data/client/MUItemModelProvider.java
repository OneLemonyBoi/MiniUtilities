package onelemonyboi.miniutilities.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import onelemonyboi.miniutilities.MiniUtilities;

public class MUItemModelProvider extends ItemModelProvider {
    public MUItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MiniUtilities.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("ender_ore", modLoc("block/ender_ore"));
        withExistingParent("ender_pearl_block", modLoc("block/ender_pearl_block"));
        withExistingParent("angel_block", modLoc("block/angel_block"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "ender_dust");
        builder(itemGenerated, "kikoku");
        builder(itemGenerated, "healing_axe");
        builder(itemGenerated, "reversing_hoe");
        builder(itemGenerated, "unstable_ingot");
        builder(itemGenerated, "destruction_pickaxe");
        builder(itemGenerated, "precision_shears");
        builder(itemGenerated, "erosion_shovel");
        builder(itemGenerated, "etheric_sword");


    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}
