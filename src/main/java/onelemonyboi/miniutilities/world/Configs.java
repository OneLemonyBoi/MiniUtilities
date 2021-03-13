package onelemonyboi.miniutilities.world;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class Configs {
    public static ForgeConfigSpec.BooleanValue enable_cursed_earth;
    public static ForgeConfigSpec.BooleanValue enable_blessed_earth;
    public static ForgeConfigSpec.IntValue max_kikoku_level;
    public static ForgeConfigSpec.ConfigValue<List<String>> allowed_containers;
    public static void init(ForgeConfigSpec.Builder config){
        List<String> strings = new ArrayList<>();
        strings.add(ContainerType.CRAFTING.getRegistryName().toString());
        strings.add("minecraft:crafting_table");
        strings.add("fastbench:fastbench");
        enable_cursed_earth = config.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enable_blessed_earth = config.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Iron Block on Grass
        max_kikoku_level = config.comment("Max Level of Kikoku Enchant Adding?").defineInRange("Kikoku OP", 100, 1, 32767);
        allowed_containers = config.comment("Allowed Container Types").define("containers", strings, List.class::isInstance);
    }
}
