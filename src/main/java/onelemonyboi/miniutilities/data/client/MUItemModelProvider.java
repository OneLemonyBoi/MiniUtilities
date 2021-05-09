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
        withExistingParent("white_lapis_caelestis", modLoc("block/white_lapis_caelestis"));
        withExistingParent("light_gray_lapis_caelestis", modLoc("block/light_gray_lapis_caelestis"));
        withExistingParent("gray_lapis_caelestis", modLoc("block/gray_lapis_caelestis"));
        withExistingParent("black_lapis_caelestis", modLoc("block/black_lapis_caelestis"));
        withExistingParent("red_lapis_caelestis", modLoc("block/red_lapis_caelestis"));
        withExistingParent("orange_lapis_caelestis", modLoc("block/orange_lapis_caelestis"));
        withExistingParent("yellow_lapis_caelestis", modLoc("block/yellow_lapis_caelestis"));
        withExistingParent("lime_lapis_caelestis", modLoc("block/lime_lapis_caelestis"));
        withExistingParent("green_lapis_caelestis", modLoc("block/green_lapis_caelestis"));
        withExistingParent("light_blue_lapis_caelestis", modLoc("block/light_blue_lapis_caelestis"));
        withExistingParent("cyan_lapis_caelestis", modLoc("block/cyan_lapis_caelestis"));
        withExistingParent("blue_lapis_caelestis", modLoc("block/blue_lapis_caelestis"));
        withExistingParent("purple_lapis_caelestis", modLoc("block/purple_lapis_caelestis"));
        withExistingParent("magenta_lapis_caelestis", modLoc("block/magenta_lapis_caelestis"));
        withExistingParent("pink_lapis_caelestis", modLoc("block/pink_lapis_caelestis"));
        withExistingParent("brown_lapis_caelestis", modLoc("block/brown_lapis_caelestis"));

        withExistingParent("quantum_quarry", modLoc("block/quantum_quarry"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "ender_dust");
        builder(itemGenerated, "healing_axe");
        builder(itemGenerated, "reversing_hoe");
        builder(itemGenerated, "unstable_ingot");
        builder(itemGenerated, "destruction_pickaxe");
        builder(itemGenerated, "precision_shears");
        builder(itemGenerated, "erosion_shovel");
        builder(itemGenerated, "etheric_sword");

        builder(itemGenerated, "iron_opinium_core");
        builder(itemGenerated, "gold_opinium_core");
        builder(itemGenerated, "diamond_opinium_core");
        builder(itemGenerated, "netherite_opinium_core");
        builder(itemGenerated, "emerald_opinium_core");
        builder(itemGenerated, "chorus_opinium_core");
        builder(itemGenerated, "experience_opinium_core");
        builder(itemGenerated, "nether_star_opinium_core");
        builder(itemGenerated, "the_final_opinium_core");

        builder(itemGenerated, "unstable_helmet");
        builder(itemGenerated, "unstable_chestplate");
        builder(itemGenerated, "unstable_leggings");
        builder(itemGenerated, "unstable_boots");
        builder(itemGenerated, "infused_helmet");
        builder(itemGenerated, "infused_chestplate");
        builder(itemGenerated, "infused_leggings");
        builder(itemGenerated, "infused_boots");

        builder(itemGenerated, "angel_ring");
        builder(itemGenerated, "feather_angel_ring");
        builder(itemGenerated, "ender_dragon_angel_ring");
        builder(itemGenerated, "gold_angel_ring");
        builder(itemGenerated, "bat_angel_ring");
        builder(itemGenerated, "peacock_angel_ring");
        builder(itemGenerated, "feather_wing");
        builder(itemGenerated, "ender_dragon_wing");
        builder(itemGenerated, "gold_wing");
        builder(itemGenerated, "bat_wing");
        builder(itemGenerated, "peacock_wing");

        builder(itemGenerated, "ender_lily_seeds");
        builder(itemGenerated, "flame_lily_seeds");
        builder(itemGenerated, "flame_lily");

        builder(itemGenerated, "speed_upgrade");

        builder(itemGenerated, "experience_pearl");
        builder(itemGenerated, "experience_pearl_1x");
        builder(itemGenerated, "experience_pearl_2x");
        builder(itemGenerated, "experience_pearl_3x");
        builder(itemGenerated, "experience_pearl_4x");
        builder(itemGenerated, "experience_pearl_5x");
        builder(itemGenerated, "experience_pearl_6x");
        builder(itemGenerated, "experience_pearl_7x");
        builder(itemGenerated, "experience_pearl_8x");

        builder(itemGenerated, "magical_egg");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}
