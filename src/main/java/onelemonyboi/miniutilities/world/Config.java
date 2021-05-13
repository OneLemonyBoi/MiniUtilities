package onelemonyboi.miniutilities.world;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import onelemonyboi.miniutilities.blocks.complexblocks.solarpanels.LunarPanelBlock;

import java.util.List;

@Mod.EventBusSubscriber
public class Config {
    public static final String CATEGORY_GENERAL = "General Settings";
    public static final String CATEGORY_ORE = "World Generation";

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue enableCursedEarth;
    public static ForgeConfigSpec.BooleanValue enableBlessedEarth;
    public static ForgeConfigSpec.BooleanValue enableBlursedEarth;
    public static ForgeConfigSpec.IntValue maxKikokuMultiplier;
    public static ForgeConfigSpec.IntValue unstableIngotType;
    public static ForgeConfigSpec.BooleanValue enableEnderLily;
    public static ForgeConfigSpec.BooleanValue enableFlameLily;
    public static ForgeConfigSpec.IntValue expGivenFromPearl;
    public static ForgeConfigSpec.IntValue solarPanelGeneration;
    public static ForgeConfigSpec.IntValue lunarPanelGeneration;
    public static ForgeConfigSpec.IntValue panelMultiplier;

    public static ForgeConfigSpec.BooleanValue enableEnderOre;
    public static ForgeConfigSpec.IntValue enderOreVeinSize;
    public static ForgeConfigSpec.IntValue enderOreMinHeight;
    public static ForgeConfigSpec.IntValue enderOreMaxHeight;
    public static ForgeConfigSpec.IntValue enderOreAmount;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> oreChances;
    private static final List<String> oreDefaults = ImmutableList.of("minecraft:coal_ore:4", "minecraft:iron_ore:3", "minecraft:gold_ore:1", "minecraft:diamond_ore:1", "minecraft:lapis_ore:2", "minecraft:redstone_ore:4", "minecraft:emerald_ore:1");


    public static void register() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        setupFirstBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);
        setupSecondBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    private static void setupFirstBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
//        CLIENT_BUILDER.push(CATEGORY_GENERAL);
        COMMON_BUILDER.push(CATEGORY_GENERAL);

        enableCursedEarth = COMMON_BUILDER.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enableBlessedEarth = COMMON_BUILDER.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Iron Block on Grass
        enableBlursedEarth = COMMON_BUILDER.comment("Should Blursed Earth be Obtainable?").define("Obtain Blursed", true); // Get by right clicking Unstable Ingot on Grass
        maxKikokuMultiplier = COMMON_BUILDER.comment("What Should the Max Multiplier of Kikoku Enchant be?").defineInRange("Kikoku Max", 2, 1, 5000);
        unstableIngotType = COMMON_BUILDER.comment("What should the Unstable Ingot do when the time limit is reached?\n0: Slowness while holding\n1: After 10 seconds, slowly ramps up the damage\n2: Explodes after 10 seconds").defineInRange("Reaction Type", 1, 0, 2);
        expGivenFromPearl = COMMON_BUILDER.comment("How much EXP should base experience pearls give?").defineInRange("EXP Given from EXP Pearls", 10, 1, 128);
        enableEnderLily = COMMON_BUILDER.comment("Should Ender Lilies Generate?").define("Ender Lily Generation", true);
        enableFlameLily = COMMON_BUILDER.comment("Should Flame Lilies Generate?").define("Flame Lily Generation", true);
        solarPanelGeneration = COMMON_BUILDER.comment("How much RF should the Solar Panel Generate?").defineInRange("Solar Panel Generation", 4, 0, 1000000);
        lunarPanelGeneration = COMMON_BUILDER.comment("How much RF should the Lunar Panel Generate?").defineInRange("Lunar Panel Generation", 4, 0, 1000000);
        panelMultiplier = COMMON_BUILDER.comment("How many Panels should it take to increase the multiplier by 1?").defineInRange("Panel Multiplier Amount", 50, 1, 1000000);
        oreChances = COMMON_BUILDER.comment("List ores and their weight, in this format: [minecraft:coal_ore:10]").defineList("ore_list", () -> oreDefaults, ore -> ore instanceof String);
//        CLIENT_BUILDER.pop();
        COMMON_BUILDER.pop();
    }

    private static void setupSecondBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
//        CLIENT_BUILDER.push(CATEGORY_ORE);
        COMMON_BUILDER.push(CATEGORY_ORE);

        enableEnderOre = COMMON_BUILDER.comment("Should Ender Ore Generate?").define("Ender Ore Generation", true);
        enderOreVeinSize = COMMON_BUILDER.comment("What should the Vein Size of Ender Ore be?").defineInRange("Ender Ore Vein", 12, 0, 256);
        enderOreMinHeight = COMMON_BUILDER.comment("What should the minimum generation height be for Ender Ore?").defineInRange("Ender Ore Min", 0, 0, 256);
        enderOreMaxHeight = COMMON_BUILDER.comment("What should the maximum generation height be for Ender Ore?").defineInRange("Ender Ore Max", 55, 0, 256);
        enderOreAmount = COMMON_BUILDER.comment("How many times should Ender Ore try to generate?").defineInRange("Ender Ore Gen Attempts", 12, 0, 255);
//        CLIENT_BUILDER.pop();
        COMMON_BUILDER.pop();
    }
}
