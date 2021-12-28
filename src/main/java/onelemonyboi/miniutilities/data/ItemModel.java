package onelemonyboi.miniutilities.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import onelemonyboi.miniutilities.MiniUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemModel extends ItemModelProvider {
    public ItemModel(DataGenerator generator, ExistingFileHelper existingFileHelper) {
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
        withExistingParent("mechanical_miner", modLoc("block/mechanical_miner"));
        withExistingParent("mechanical_placer", modLoc("block/mechanical_placer"));
        withExistingParent("quantum_quarry", modLoc("block/quantum_quarry"));
        withExistingParent("stone_drum", modLoc("block/stone_drum"));
        withExistingParent("iron_drum", modLoc("block/iron_drum"));
        withExistingParent("reinforced_large_drum", modLoc("block/reinforced_large_drum"));
        withExistingParent("netherite_reinforced_drum", modLoc("block/netherite_reinforced_drum"));
        withExistingParent("unstable_drum", modLoc("block/unstable_drum"));
        withExistingParent("wooden_spikes", modLoc("block/wooden_spikes"));
        withExistingParent("iron_spikes", modLoc("block/iron_spikes"));
        withExistingParent("gold_spikes", modLoc("block/gold_spikes"));
        withExistingParent("diamond_spikes", modLoc("block/diamond_spikes"));
        withExistingParent("netherite_spikes", modLoc("block/netherite_spikes"));
        withExistingParent("unstable_block", modLoc("block/unstable_block"));
        withExistingParent("cursed_earth", new ResourceLocation("block/grass_block"));
        withExistingParent("blessed_earth", new ResourceLocation("block/grass_block"));
        withExistingParent("blursed_earth", new ResourceLocation("block/grass_block"));

        withExistingParent("ethereal_glass", modLoc("block/ethereal_glass"));
        withExistingParent("reverse_ethereal_glass", modLoc("block/reverse_ethereal_glass"));
        withExistingParent("redstone_glass", modLoc("block/redstone_glass"));
        withExistingParent("glowing_glass", modLoc("block/glowing_glass"));
        withExistingParent("dark_glass", modLoc("block/dark_glass"));
        withExistingParent("dark_ethereal_glass", modLoc("block/dark_ethereal_glass"));
        withExistingParent("dark_reverse_ethereal_glass", modLoc("block/dark_reverse_ethereal_glass"));

        withExistingParent("lapis_lamp", modLoc("block/lapis_lamp"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));

        String[] generated = new String[]{"ender_dust", "unstable_ingot", "iron_opinium_core", "gold_opinium_core", "diamond_opinium_core", "netherite_opinium_core", "emerald_opinium_core", "chorus_opinium_core", "experience_opinium_core", "nether_star_opinium_core", "the_final_opinium_core", "unstable_helmet", "unstable_chestplate", "unstable_leggings", "unstable_boots", "infused_helmet", "infused_chestplate", "infused_leggings", "infused_boots", "angel_ring", "feather_angel_ring", "ender_dragon_angel_ring", "gold_angel_ring", "bat_angel_ring", "peacock_angel_ring", "feather_wing", "ender_dragon_wing", "gold_wing", "bat_wing", "peacock_wing", "ender_lily_seeds", "flame_lily_seeds", "flame_lily", "speed_upgrade", "experience_pearl", "experience_pearl_1x", "experience_pearl_2x", "experience_pearl_3x", "experience_pearl_4x", "experience_pearl_5x", "experience_pearl_6x", "experience_pearl_7x", "experience_pearl_8x", "magical_egg", "golden_lasso"};
        String[] handheld = new String[]{"healing_axe", "reversing_hoe", "destruction_pickaxe", "precision_shears", "erosion_shovel", "etheric_sword"};

        buildAll(itemGenerated, generated);
        buildAll(itemHandheld, handheld);
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        System.out.print("\"" + name + "\"" + ", ");
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    private void buildAll(ModelFile itemGenerated, String... name) {
        Arrays.stream(name).forEach(n -> builder(itemGenerated, n));
    }

}
