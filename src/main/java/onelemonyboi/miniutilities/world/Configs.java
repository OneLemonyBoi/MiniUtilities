package onelemonyboi.miniutilities.world;

import net.minecraftforge.common.ForgeConfigSpec;

public class Configs {
    public static ForgeConfigSpec.BooleanValue enable_cursed_earth;
    public static ForgeConfigSpec.BooleanValue enable_blessed_earth;
    public static void init(ForgeConfigSpec.Builder config){
        enable_cursed_earth = config.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enable_blessed_earth = config.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Iron Block on Grass
    }
}
