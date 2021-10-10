package onelemonyboi.miniutilities.startup;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import onelemonyboi.miniutilities.items.unstable.UnstableIngot;

import java.util.List;

@Mod.EventBusSubscriber
public class Config {
    public static final String CATEGORY_GENERAL = "General Settings";
    public static final String CATEGORY_ORE = "World Generation";
    public static final String CATEGORY_BLESSED_EARTH = "Blessed Earth Settings";
    public static final String CATEGORY_BLURSED_EARTH = "Blursed Earth Settings";
    public static final String CATEGORY_CURSED_EARTH = "Cursed Earth Settings";

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue enableCursedEarth;
    public static ForgeConfigSpec.BooleanValue enableBlessedEarth;
    public static ForgeConfigSpec.BooleanValue enableBlursedEarth;
    public static ForgeConfigSpec.IntValue maxKikokuMultiplier;
    public static ForgeConfigSpec.EnumValue<UnstableIngot.ReactionType> unstableIngotType;
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



    public static ForgeConfigSpec.IntValue blessedEarthMinWaitTimer;
    public static ForgeConfigSpec.IntValue blessedEarthCheckAreaSize;
    public static ForgeConfigSpec.IntValue blessedEarthCheckAreaMaxEntityCount;

    public static ForgeConfigSpec.IntValue blursedEarthMinWaitTimer;
    public static ForgeConfigSpec.IntValue blursedEarthCheckAreaSize;
    public static ForgeConfigSpec.IntValue blursedEarthCheckAreaMaxEntityCount;

    public static ForgeConfigSpec.IntValue cursedEarthMinWaitTimer;
    public static ForgeConfigSpec.IntValue cursedEarthCheckAreaSize;
    public static ForgeConfigSpec.IntValue cursedEarthCheckAreaMaxEntityCount;

    public static void register() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        setupFirstBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);
        setupSecondBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);
        setupBlessedBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);
        setupBlursedBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);
        setupCursedBlockConfig(COMMON_BUILDER, CLIENT_BUILDER);

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    private static void setupFirstBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.push(CATEGORY_GENERAL);

        enableCursedEarth = COMMON_BUILDER.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enableBlessedEarth = COMMON_BUILDER.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Iron Block on Grass
        enableBlursedEarth = COMMON_BUILDER.comment("Should Blursed Earth be Obtainable?").define("Obtain Blursed", true); // Get by right clicking Unstable Ingot on Grass
        maxKikokuMultiplier = COMMON_BUILDER.comment("What Should the Max Multiplier of Kikoku Enchant be?").defineInRange("Kikoku Max", 2, 1, 5000);
        unstableIngotType = COMMON_BUILDER.comment("What should the Unstable Ingot do when the time limit is reached?\nNO_DAMAGE: Slowness while holding\nDAMAGE: After 10 seconds, slowly ramps up the damage\nEXPLOSION: Explodes after 10 seconds").defineEnum("Reaction Type", UnstableIngot.ReactionType.DAMAGE);
        expGivenFromPearl = COMMON_BUILDER.comment("How much EXP should base experience pearls give?").defineInRange("EXP Given from EXP Pearls", 10, 1, 128);
        enableEnderLily = COMMON_BUILDER.comment("Should Ender Lilies Generate?").define("Ender Lily Generation", true);
        enableFlameLily = COMMON_BUILDER.comment("Should Flame Lilies Generate?").define("Flame Lily Generation", true);
        solarPanelGeneration = COMMON_BUILDER.comment("How much FE should the Solar Panel Generate?").defineInRange("Solar Panel Generation", 4, 0, 1000000);
        lunarPanelGeneration = COMMON_BUILDER.comment("How much FE should the Lunar Panel Generate?").defineInRange("Lunar Panel Generation", 4, 0, 1000000);
        panelMultiplier = COMMON_BUILDER.comment("How many Panels should it take to increase the multiplier by 1?").defineInRange("Panel Multiplier Amount", 50, 1, 1000000);

        COMMON_BUILDER.pop();
    }

    private static void setupSecondBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.push(CATEGORY_ORE);

        enableEnderOre = COMMON_BUILDER.comment("Should Ender Ore Generate?").define("Ender Ore Generation", true);
        enderOreVeinSize = COMMON_BUILDER.comment("What should the Vein Size of Ender Ore be?").defineInRange("Ender Ore Vein", 12, 0, 256);
        enderOreMinHeight = COMMON_BUILDER.comment("What should the minimum generation height be for Ender Ore?").defineInRange("Ender Ore Min", 0, 0, 256);
        enderOreMaxHeight = COMMON_BUILDER.comment("What should the maximum generation height be for Ender Ore?").defineInRange("Ender Ore Max", 55, 0, 256);
        enderOreAmount = COMMON_BUILDER.comment("How many times should Ender Ore try to generate?").defineInRange("Ender Ore Gen Attempts", 12, 0, 255);

        COMMON_BUILDER.pop();
    }

    private static void setupBlessedBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.push(CATEGORY_BLESSED_EARTH);

        blessedEarthMinWaitTimer = COMMON_BUILDER.comment("How long should it minimum wait before trying to spawn").defineInRange("Minimum Waiting Time", 200, 1, 2000);
        blessedEarthCheckAreaSize = COMMON_BUILDER.comment("How big of an area should the block check for mobs, 2 would be 2x1x2 area").defineInRange("Area Size for entity count", 2, 1, 5);
        blessedEarthCheckAreaMaxEntityCount = COMMON_BUILDER.comment("At what amount of entities in the area should mob spawning be halted").defineInRange("Mob cap for Area", 3, 1, 10);

        COMMON_BUILDER.pop();
    }

    private static void setupBlursedBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.push(CATEGORY_BLURSED_EARTH);

        blursedEarthMinWaitTimer = COMMON_BUILDER.comment("How long should it minimum wait before trying to spawn").defineInRange("Minimum Waiting Time", 200, 1, 2000);
        blursedEarthCheckAreaSize = COMMON_BUILDER.comment("How big of an area should the block check for mobs, 2 would be 2x1x2 area").defineInRange("Area Size for entity count", 2, 1, 5);
        blursedEarthCheckAreaMaxEntityCount = COMMON_BUILDER.comment("At what amount of entities in the area should mob spawning be halted").defineInRange("Mob cap for Area", 3, 1, 10);

        COMMON_BUILDER.pop();
    }

    private static void setupCursedBlockConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.push(CATEGORY_CURSED_EARTH);

        cursedEarthMinWaitTimer = COMMON_BUILDER.comment("How long should it minimum wait before trying to spawn").defineInRange("Minimum Waiting Time", 200, 1, 2000);
        cursedEarthCheckAreaSize = COMMON_BUILDER.comment("How big of an area should the block check for mobs, 2 would be 2x1x2 area").defineInRange("Area Size for entity count", 2, 1, 5);
        cursedEarthCheckAreaMaxEntityCount = COMMON_BUILDER.comment("At what amount of entities in the area should mob spawning be halted").defineInRange("Mob cap for Area", 3, 1, 10);

        COMMON_BUILDER.pop();
    }
}
