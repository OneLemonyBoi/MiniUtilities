package onelemonyboi.miniutilities.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.lemonlib.items.*;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.AngelBlockItem;
import onelemonyboi.miniutilities.items.*;
import onelemonyboi.miniutilities.items.ItemTier;
import onelemonyboi.miniutilities.items.unstable.*;

import static onelemonyboi.miniutilities.MiniUtilities.MOD_ID;
import static onelemonyboi.miniutilities.items.ItemTier.UNSTABLE;

public class ItemList {
    public static final RegistryObject<Item> EnderDust = ModRegistry.ITEMS.register("ender_dust", () ->
            new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> AngelBlockItem = ModRegistry.ITEMS.register("angel_block", () ->
            new AngelBlockItem(BlockList.AngelBlock.get()));
    public static final RegistryObject<Item> Kikoku = ModRegistry.ITEMS.register("kikoku", () ->
            new Kikoku(ItemTier.KIKOKU,  13, -2.4F, (new Item.Properties().group(CreativeTab.getInstance()))));
    public static final RegistryObject<Item> EnderLilySeeds = ModRegistry.ITEMS.register("ender_lily_seeds", () ->
            new ItemSeeds(BlockList.EnderLily.get(), CreativeTab.getInstance()));
    public static final RegistryObject<Item> FlameLilySeeds = ModRegistry.ITEMS.register("flame_lily_seeds", () ->
            new ItemSeeds(BlockList.FlameLily.get(), CreativeTab.getInstance()));
    public static final RegistryObject<Item> FlameLily = ModRegistry.ITEMS.register("flame_lily", () ->
            new FuelItems(new Item.Properties().group(CreativeTab.getInstance()), 2400));

    // Angel Wings
    public static final RegistryObject<Item> BaseAngelRing = ModRegistry.ITEMS.register("angel_ring", AngelRing::new);
    public static final RegistryObject<Item> GoldAngelRing = ModRegistry.ITEMS.register("gold_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> EnderDragonAngelRing = ModRegistry.ITEMS.register("ender_dragon_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> FeatherAngelRing = ModRegistry.ITEMS.register("feather_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> BatAngelRing = ModRegistry.ITEMS.register("bat_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> PeacockAngelRing = ModRegistry.ITEMS.register("peacock_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> GoldWing = ModRegistry.ITEMS.register("gold_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> EnderDragonWing = ModRegistry.ITEMS.register("ender_dragon_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> FeatherWing = ModRegistry.ITEMS.register("feather_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> BatWing = ModRegistry.ITEMS.register("bat_wing", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> PeacockWing = ModRegistry.ITEMS.register("peacock_wing", () ->
            new Item(new Item.Properties()));



    // Unstable Tools + Ingot
    public static final RegistryObject<AxeItem> UnstableAxe = ModRegistry.ITEMS.register("healing_axe", () ->
            new UnstableAxe(UNSTABLE, 1, -3, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<HoeItem> UnstableHoe = ModRegistry.ITEMS.register("reversing_hoe", () ->
            new UnstableHoe(UNSTABLE, -8, 0, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> UnstableIngot = ModRegistry.ITEMS.register("unstable_ingot", () ->
            new UnstableIngot(new Item.Properties().group(CreativeTab.getInstance()).setNoRepair().maxDamage(200)));
    public static final RegistryObject<PickaxeItem> UnstablePickaxe = ModRegistry.ITEMS.register("destruction_pickaxe", () ->
            new UnstablePickaxe(UNSTABLE, -3, -2.8f, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<ShearsItem> UnstableShears = ModRegistry.ITEMS.register("precision_shears", () ->
            new UnstableShears(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<ShovelItem> UnstableShovel = ModRegistry.ITEMS.register("erosion_shovel", () ->
            new UnstableShovel(UNSTABLE, -2.5f, -3, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<SwordItem> UnstableSword = ModRegistry.ITEMS.register("etheric_sword", () ->
            new UnstableSword(UNSTABLE, -1, -2.4f, new Item.Properties().group(CreativeTab.getInstance())));

    // Unstable Armour + Infused Armor

    public static final RegistryObject<ArmorItem> UnstableHelmet = ModRegistry.ITEMS.register("unstable_helmet", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlotType.HEAD, (new Item.Properties()).group(CreativeTab.getInstance())));
    public static final RegistryObject<ArmorItem> UnstableChestplate = ModRegistry.ITEMS.register("unstable_chestplate", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlotType.CHEST, (new Item.Properties()).group(CreativeTab.getInstance())));
    public static final RegistryObject<ArmorItem> UnstableLeggings = ModRegistry.ITEMS.register("unstable_leggings", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlotType.LEGS, (new Item.Properties()).group(CreativeTab.getInstance())));
    public static final RegistryObject<ArmorItem> UnstableBoots = ModRegistry.ITEMS.register("unstable_boots", () -> new ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlotType.FEET, (new Item.Properties()).group(CreativeTab.getInstance())));

    public static final RegistryObject<ArmorItem> InfusedHelmet = ModRegistry.ITEMS.register("infused_helmet", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlotType.HEAD, (new Item.Properties()).group(CreativeTab.getInstance())));
    public static final RegistryObject<ArmorItem> InfusedChestplate = ModRegistry.ITEMS.register("infused_chestplate", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlotType.CHEST, (new Item.Properties()).group(CreativeTab.getInstance())));
    public static final RegistryObject<ArmorItem> InfusedLeggings = ModRegistry.ITEMS.register("infused_leggings", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlotType.LEGS, (new Item.Properties()).group(CreativeTab.getInstance())));
    public static final RegistryObject<ArmorItem> InfusedBoots = ModRegistry.ITEMS.register("infused_boots", () -> new ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlotType.FEET, (new Item.Properties()).group(CreativeTab.getInstance())));

    // Opinium Cores

    public static final RegistryObject<Item> IronOpiniumCore = ModRegistry.ITEMS.register("iron_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> GoldOpiniumCore = ModRegistry.ITEMS.register("gold_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> DiamondOpiniumCore = ModRegistry.ITEMS.register("diamond_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> NetheriteOpiniumCore = ModRegistry.ITEMS.register("netherite_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> EmeraldOpiniumCore = ModRegistry.ITEMS.register("emerald_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> ChorusOpiniumCore = ModRegistry.ITEMS.register("chorus_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> EXPOpiniumCore = ModRegistry.ITEMS.register("experience_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> NetherStarOpiniumCore = ModRegistry.ITEMS.register("nether_star_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> TheFinalOpiniumCore = ModRegistry.ITEMS.register("the_final_opinium_core", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));

    public static final RegistryObject<Item> SpeedUpgrade = ModRegistry.ITEMS.register("speed_upgrade", () -> new Item(new Item.Properties().group(CreativeTab.getInstance())));

    public static final RegistryObject<Item> ExperiencePearl = ModRegistry.ITEMS.register("experience_pearl", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> ExperiencePearl1x = ModRegistry.ITEMS.register("experience_pearl_1x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 1));
    public static final RegistryObject<Item> ExperiencePearl2x = ModRegistry.ITEMS.register("experience_pearl_2x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 2));
    public static final RegistryObject<Item> ExperiencePearl3x = ModRegistry.ITEMS.register("experience_pearl_3x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 3));
    public static final RegistryObject<Item> ExperiencePearl4x = ModRegistry.ITEMS.register("experience_pearl_4x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 4));
    public static final RegistryObject<Item> ExperiencePearl5x = ModRegistry.ITEMS.register("experience_pearl_5x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 5));
    public static final RegistryObject<Item> ExperiencePearl6x = ModRegistry.ITEMS.register("experience_pearl_6x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 6));
    public static final RegistryObject<Item> ExperiencePearl7x = ModRegistry.ITEMS.register("experience_pearl_7x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 7));
    public static final RegistryObject<Item> ExperiencePearl8x = ModRegistry.ITEMS.register("experience_pearl_8x", () -> new ExperiencePearl(new Item.Properties().group(CreativeTab.getInstance()), 8));

    public static final RegistryObject<Item> MagicalEgg = ModRegistry.ITEMS.register("magical_egg", () -> new MagicalEgg(new Item.Properties().group(CreativeTab.getInstance())));

    public static void register() {}
}
