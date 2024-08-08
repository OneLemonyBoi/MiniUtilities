package onelemonyboi.miniutilities.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.lemonlib.items.*;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.basic.AngelBlockItem;
import onelemonyboi.miniutilities.items.*;
import onelemonyboi.miniutilities.items.unstable.*;

import java.util.function.Supplier;

public class ItemList {
    public static final RegistryObject<Item> EnderDust = register("ender_dust", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> AngelBlockItem = register("angel_block", () ->
            new AngelBlockItem(BlockList.AngelBlock.get()));
    public static final RegistryObject<Item> Kikoku = register("kikoku", () ->
            new Kikoku(Materials.KIKOKU,  13, -2.4F, (new Item.Properties())));
    public static final RegistryObject<Item> EnderLilySeeds = register("ender_lily_seeds", () ->
            new ItemSeeds(BlockList.EnderLily.get()));
    public static final RegistryObject<Item> FlameLilySeeds = register("flame_lily_seeds", () ->
            new ItemSeeds(BlockList.FlameLily.get()));
    public static final RegistryObject<Item> FlameLily = register("flame_lily", () ->
            new FuelItems(new Item.Properties(), 2400));

    // Angel Wings
    public static final RegistryObject<Item> BaseAngelRing = register("angel_ring", AngelRing::new);
    public static final RegistryObject<Item> GoldAngelRing = register("gold_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> EnderDragonAngelRing = register("ender_dragon_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> FeatherAngelRing = register("feather_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> BatAngelRing = register("bat_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> PeacockAngelRing = register("peacock_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> GoldWing = register("gold_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> EnderDragonWing = register("ender_dragon_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> FeatherWing = register("feather_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> BatWing = register("bat_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> PeacockWing = register("peacock_wing", () ->
            new Item(new Item.Properties()));



    // Unstable Tools + Ingot
    public static final RegistryObject<Item> UnstableAxe = register("healing_axe", () ->
            new UnstableAxe(Materials.UNSTABLE, 1, -3, new Item.Properties()));
    public static final RegistryObject<Item> UnstableHoe = register("reversing_hoe", () ->
            new UnstableHoe(Materials.UNSTABLE, -8, 0, new Item.Properties()));
    public static final RegistryObject<Item> UnstableIngot = register("unstable_ingot", () ->
            new UnstableIngot(new Item.Properties().setNoRepair().durability(200)));
    public static final RegistryObject<Item> UnstablePickaxe = register("destruction_pickaxe", () ->
            new UnstablePickaxe(Materials.UNSTABLE, -3, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> UnstableShears = register("precision_shears", () ->
            new UnstableShears(new Item.Properties()));
    public static final RegistryObject<Item> UnstableShovel = register("erosion_shovel", () ->
            new UnstableShovel(Materials.UNSTABLE, -2.5f, -3, new Item.Properties()));
    public static final RegistryObject<Item> UnstableSword = register("etheric_sword", () ->
            new UnstableSword(Materials.UNSTABLE, -1, -2.4f, new Item.Properties()));

    // Unstable Armour + Infused Armor

    public static final RegistryObject<Item> UnstableHelmet = register("unstable_helmet", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final RegistryObject<Item> UnstableChestplate = register("unstable_chestplate", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final RegistryObject<Item> UnstableLeggings = register("unstable_leggings", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final RegistryObject<Item> UnstableBoots = register("unstable_boots", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static final RegistryObject<Item> InfusedHelmet = register("infused_helmet", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final RegistryObject<Item> InfusedChestplate = register("infused_chestplate", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final RegistryObject<Item> InfusedLeggings = register("infused_leggings", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final RegistryObject<Item> InfusedBoots = register("infused_boots", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, ArmorItem.Type.BOOTS, (new Item.Properties())));

    // Opinium Cores

    public static final RegistryObject<Item> IronOpiniumCore = register("iron_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GoldOpiniumCore = register("gold_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DiamondOpiniumCore = register("diamond_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NetheriteOpiniumCore = register("netherite_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EmeraldOpiniumCore = register("emerald_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ChorusOpiniumCore = register("chorus_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EXPOpiniumCore = register("experience_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NetherStarOpiniumCore = register("nether_star_opinium_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TheFinalOpiniumCore = register("the_final_opinium_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SpeedUpgrade = register("speed_upgrade", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ExperiencePearl = register("experience_pearl", () -> new ExperiencePearl(new Item.Properties()));
    public static final RegistryObject<Item> ExperiencePearl1x = register("experience_pearl_1x", () -> new ExperiencePearl(new Item.Properties(), 1));
    public static final RegistryObject<Item> ExperiencePearl2x = register("experience_pearl_2x", () -> new ExperiencePearl(new Item.Properties(), 2));
    public static final RegistryObject<Item> ExperiencePearl3x = register("experience_pearl_3x", () -> new ExperiencePearl(new Item.Properties(), 3));
    public static final RegistryObject<Item> ExperiencePearl4x = register("experience_pearl_4x", () -> new ExperiencePearl(new Item.Properties(), 4));
    public static final RegistryObject<Item> ExperiencePearl5x = register("experience_pearl_5x", () -> new ExperiencePearl(new Item.Properties(), 5));
    public static final RegistryObject<Item> ExperiencePearl6x = register("experience_pearl_6x", () -> new ExperiencePearl(new Item.Properties(), 6));
    public static final RegistryObject<Item> ExperiencePearl7x = register("experience_pearl_7x", () -> new ExperiencePearl(new Item.Properties(), 7));
    public static final RegistryObject<Item> ExperiencePearl8x = register("experience_pearl_8x", () -> new ExperiencePearl(new Item.Properties(), 8));

    public static final RegistryObject<Item> MagicalEgg = register("magical_egg", () -> new MagicalEgg(new Item.Properties()));

    public static final RegistryObject<Item> GoldenLasso = register("golden_lasso", () -> new GoldenLasso(new Item.Properties().stacksTo(1)));

    public static void register() {}

    public static RegistryObject<Item> register(final String name, final Supplier<Item> sup) {
        RegistryObject<Item> item = ModRegistry.ITEMS.register(name, sup);
        CreativeTab.setTab(item::get, BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.TOOLS_AND_UTILITIES));

        return item;
    }
}
