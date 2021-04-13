package onelemonyboi.miniutilities.world;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "Settings";
    public static final String CATEGORY_ORE = "World Generation";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue enable_cursed_earth;
    public static ForgeConfigSpec.BooleanValue enable_blessed_earth;
    public static ForgeConfigSpec.BooleanValue enable_blursed_earth;
    public static ForgeConfigSpec.IntValue max_kikoku_multiplier;
    public static ForgeConfigSpec.IntValue unstable_ingot_type;

    public static ForgeConfigSpec.BooleanValue enable_ender_ore;
    public static ForgeConfigSpec.IntValue vein_size;
    public static ForgeConfigSpec.IntValue min_height;
    public static ForgeConfigSpec.IntValue max_height;
    public static ForgeConfigSpec.IntValue amount;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ore_chances;
    private static final List<String> ore_default = ImmutableList.of("minecraft:coal_ore:20", "minecraft:iron_ore:8", "minecraft:gold_ore:2", "minecraft:diamond_ore:1", "minecraft:lapis_ore:4");

    public static ForgeConfigSpec.BooleanValue enable_ender_lily;
    public static ForgeConfigSpec.BooleanValue enable_flame_lily;

    public static ForgeConfigSpec.IntValue exp_given_from_pearl;


    static {

        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.push(CATEGORY_GENERAL);
        CLIENT_BUILDER.pop();
        SERVER_BUILDER.push(CATEGORY_GENERAL);
        setupFirstBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);
        SERVER_BUILDER.push(CATEGORY_ORE);
        setupSecondBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);
        SERVER_BUILDER.pop();
        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupFirstBlockConfig(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        enable_cursed_earth = SERVER_BUILDER.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enable_blessed_earth = SERVER_BUILDER.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Iron Block on Grass
        enable_blursed_earth = SERVER_BUILDER.comment("Should Blursed Earth be Obtainable?").define("Obtain Blursed", true); // Get by right clicking Unstable Ingot on Grass
        max_kikoku_multiplier = SERVER_BUILDER.comment("What Should the Max Multiplier of Kikoku Enchant be?").defineInRange("Kikoku Max", 2, 1, 5000);
        unstable_ingot_type = SERVER_BUILDER.comment("What should the Unstable Ingot do when the time limit is reached?\n0: Slowness while holding\n1: After 10 seconds, slowly ramps up the damage\n2: Explodes after 10 seconds").defineInRange("Reaction Type", 1, 0, 2);

        exp_given_from_pearl = SERVER_BUILDER.comment("How much EXP should base experience pearls give?").defineInRange("EXP Given from EXP Pearls", 10, 1, 128);
    }

    private static void setupSecondBlockConfig(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        enable_ender_ore = SERVER_BUILDER.comment("Should Ender Ore Generate?").define("Ender Ore Generation", true);
        vein_size = SERVER_BUILDER.comment("What should the Vein Size of Ender Ore be?").defineInRange("Ender Ore Vein", 12, 0, 256);
        min_height = SERVER_BUILDER.comment("What should the minimum generation height be for Ender Ore?").defineInRange("Ender Ore Min", 0, 0, 256);
        max_height = SERVER_BUILDER.comment("What should the maximum generation height be for Ender Ore?").defineInRange("Ender Ore Max", 55, 0, 256);
        amount = SERVER_BUILDER.comment("How many times should Ender Ore try to generate?").defineInRange("Ender Ore Gen Attempts", 12, 0, 255);

        enable_ender_lily = SERVER_BUILDER.comment("Should Ender Lilies Generate?").define("Ender Lily Generation", true);
        enable_flame_lily = SERVER_BUILDER.comment("Should Flame Lilies Generate?").define("Flame Lily Generation", true);

        ore_chances = SERVER_BUILDER.comment("List ores and their weight, in this format: [minecraft:coal_ore:10]").defineList("ore_list", () -> ore_default, ore -> ore instanceof String);
    }
}
