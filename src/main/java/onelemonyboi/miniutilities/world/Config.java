package onelemonyboi.miniutilities.world;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "Settings";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue enable_cursed_earth;
    public static ForgeConfigSpec.BooleanValue enable_blessed_earth;
    public static ForgeConfigSpec.BooleanValue enable_blursed_earth;
    public static ForgeConfigSpec.IntValue max_kikoku_level;
    public static ForgeConfigSpec.IntValue unstable_ingot_type;


    static {

        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.push(CATEGORY_GENERAL);
        CLIENT_BUILDER.pop();
        SERVER_BUILDER.push(CATEGORY_GENERAL);
        setupFirstBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);
        SERVER_BUILDER.pop();
        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupFirstBlockConfig(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        enable_cursed_earth = SERVER_BUILDER.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enable_blessed_earth = SERVER_BUILDER.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Unstable Ingot on Grass
        enable_blursed_earth = SERVER_BUILDER.comment("Should Blursed Earth be Obtainable?").define("Obtain Blursed", true); // Get by right clicking Nether Star on Grass
        max_kikoku_level = SERVER_BUILDER.comment("Max Level of Kikoku Enchant Adding?").defineInRange("Kikoku Max", 100, 1, 32767);
        unstable_ingot_type = SERVER_BUILDER.comment("What should the Unstable Ingot do when the time limit is reached?\n0: Slowness while holding\n1: After 10 seconds, slowly ramps up the damage\n2: Explodes after 10 seconds").defineInRange("Reaction Type", 1, 0, 2);
    }
/*    public static final ForgeConfigSpec COMMONCONFIG;
    public static final ForgeConfigSpec CLIENTCONFIG;

    static {
         final ForgeConfigSpec.Builder COMMONBUILDER = new ForgeConfigSpec.Builder();
         final ForgeConfigSpec.Builder CLIENTBUILDER = new ForgeConfigSpec.Builder();

        CLIENTBUILDER.push("Settings");
        CLIENTBUILDER.pop();
        COMMONBUILDER.push("Settings");
        COMMONBUILDER.pop();

        enable_cursed_earth = COMMONBUILDER.comment("Should Cursed Earth be Obtainable?").define("Obtain Cursed", true); // Get by right clicking Wither Rose on Grass
        enable_blessed_earth = COMMONBUILDER.comment("Should Blessed Earth be Obtainable?").define("Obtain Blessed", true); // Get by right clicking Iron Block on Grass
        max_kikoku_level = COMMONBUILDER.comment("Max Level of Kikoku Enchant Adding?").defineInRange("Kikoku Max", 100, 1, 32767);
        unstable_ingot_type = COMMONBUILDER.comment("What should the Unstable Ingot do when the time limit is reached?\n0: Slowness while holding\n1: After 10 seconds, slowly ramps up the damage\n2: Explodes after 10 seconds").defineInRange("Reaction Type", 1, 0, 2);

        COMMONBUILDER.pop();

        COMMONCONFIG = COMMONBUILDER.build();
        CLIENTCONFIG = CLIENTBUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }*/
}
