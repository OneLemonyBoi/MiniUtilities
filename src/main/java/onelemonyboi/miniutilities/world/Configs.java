package onelemonyboi.miniutilities.world;

import net.minecraftforge.common.ForgeConfigSpec;

public class Configs {
    public static ForgeConfigSpec.BooleanValue enable_cursed_earth;
    public static ForgeConfigSpec.BooleanValue enable_blessed_earth;
    public static ForgeConfigSpec.IntValue max_kikoku_level;
    public static void init(ForgeConfigSpec.Builder config){
        enable_cursed_earth = config.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enable_blessed_earth = config.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Iron Block on Grass
        max_kikoku_level = config.comment("Max Level of Kikoku Enchant Adding?").defineInRange("Kikoku OP", 100, 1, 32767);
    }
}
