package onelemonyboi.miniutilities.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.lemonlib.items.*;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.basic.AngelBlockItem;
import onelemonyboi.miniutilities.items.*;
import onelemonyboi.miniutilities.items.unstable.*;

public class ItemList {
    public static final RegistryObject<net.minecraft.world.item.Item> EnderDust = ModRegistry.ITEMS.register("ender_dust", () ->
            new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<Item> AngelBlockItem = ModRegistry.ITEMS.register("angel_block", () ->
            new AngelBlockItem(BlockList.AngelBlock.get()));
    public static final RegistryObject<net.minecraft.world.item.Item> Kikoku = ModRegistry.ITEMS.register("kikoku", () ->
            new Kikoku(Materials.KIKOKU,  13, -2.4F, (new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()))));
    public static final net.minecraftforge.registries.RegistryObject<Item> EnderLilySeeds = ModRegistry.ITEMS.register("ender_lily_seeds", () ->
            new ItemSeeds(BlockList.EnderLily.get(), CreativeTab.getInstance()));
    public static final net.minecraftforge.registries.RegistryObject<Item> FlameLilySeeds = ModRegistry.ITEMS.register("flame_lily_seeds", () ->
            new ItemSeeds(BlockList.FlameLily.get(), CreativeTab.getInstance()));
    public static final net.minecraftforge.registries.RegistryObject<Item> FlameLily = ModRegistry.ITEMS.register("flame_lily", () ->
            new FuelItems(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 2400));

    // Angel Wings
    public static final net.minecraftforge.registries.RegistryObject<Item> BaseAngelRing = ModRegistry.ITEMS.register("angel_ring", AngelRing::new);
    public static final net.minecraftforge.registries.RegistryObject<Item> GoldAngelRing = ModRegistry.ITEMS.register("gold_angel_ring", AngelRing::new);
    public static final net.minecraftforge.registries.RegistryObject<Item> EnderDragonAngelRing = ModRegistry.ITEMS.register("ender_dragon_angel_ring", AngelRing::new);
    public static final RegistryObject<net.minecraft.world.item.Item> FeatherAngelRing = ModRegistry.ITEMS.register("feather_angel_ring", AngelRing::new);
    public static final RegistryObject<net.minecraft.world.item.Item> BatAngelRing = ModRegistry.ITEMS.register("bat_angel_ring", AngelRing::new);
    public static final net.minecraftforge.registries.RegistryObject<Item> PeacockAngelRing = ModRegistry.ITEMS.register("peacock_angel_ring", AngelRing::new);
    public static final net.minecraftforge.registries.RegistryObject<Item> GoldWing = ModRegistry.ITEMS.register("gold_wing", () ->
            new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties()));
    public static final RegistryObject<net.minecraft.world.item.Item> EnderDragonWing = ModRegistry.ITEMS.register("ender_dragon_wing", () ->
            new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties()));
    public static final net.minecraftforge.registries.RegistryObject<Item> FeatherWing = ModRegistry.ITEMS.register("feather_wing", () ->
            new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties()));
    public static final net.minecraftforge.registries.RegistryObject<Item> BatWing = ModRegistry.ITEMS.register("bat_wing", () ->
            new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties()));
    public static final net.minecraftforge.registries.RegistryObject<Item> PeacockWing = ModRegistry.ITEMS.register("peacock_wing", () ->
            new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties()));



    // Unstable Tools + Ingot
    public static final RegistryObject<AxeItem> UnstableAxe = ModRegistry.ITEMS.register("healing_axe", () ->
            new UnstableAxe(Materials.UNSTABLE, 1, -3, new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<HoeItem> UnstableHoe = ModRegistry.ITEMS.register("reversing_hoe", () ->
            new UnstableHoe(Materials.UNSTABLE, -8, 0, new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.Item> UnstableIngot = ModRegistry.ITEMS.register("unstable_ingot", () ->
            new UnstableIngot(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()).setNoRepair().durability(200)));
    public static final RegistryObject<PickaxeItem> UnstablePickaxe = ModRegistry.ITEMS.register("destruction_pickaxe", () ->
            new UnstablePickaxe(Materials.UNSTABLE, -3, -2.8f, new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<ShearsItem> UnstableShears = ModRegistry.ITEMS.register("precision_shears", () ->
            new UnstableShears(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final RegistryObject<ShovelItem> UnstableShovel = ModRegistry.ITEMS.register("erosion_shovel", () ->
            new UnstableShovel(Materials.UNSTABLE, -2.5f, -3, new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<SwordItem> UnstableSword = ModRegistry.ITEMS.register("etheric_sword", () ->
            new UnstableSword(Materials.UNSTABLE, -1, -2.4f, new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));

    // Unstable Armour + Infused Armor

    public static final net.minecraftforge.registries.RegistryObject<ArmorItem> UnstableHelmet = ModRegistry.ITEMS.register("unstable_helmet", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlot.HEAD, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<ArmorItem> UnstableChestplate = ModRegistry.ITEMS.register("unstable_chestplate", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlot.CHEST, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<ArmorItem> UnstableLeggings = ModRegistry.ITEMS.register("unstable_leggings", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlot.LEGS, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<ArmorItem> UnstableBoots = ModRegistry.ITEMS.register("unstable_boots", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.UNSTABLE, EquipmentSlot.FEET, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));

    public static final RegistryObject<net.minecraft.world.item.ArmorItem> InfusedHelmet = ModRegistry.ITEMS.register("infused_helmet", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlot.HEAD, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));
    public static final RegistryObject<ArmorItem> InfusedChestplate = ModRegistry.ITEMS.register("infused_chestplate", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlot.CHEST, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.ArmorItem> InfusedLeggings = ModRegistry.ITEMS.register("infused_leggings", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlot.LEGS, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.ArmorItem> InfusedBoots = ModRegistry.ITEMS.register("infused_boots", () -> new net.minecraft.world.item.ArmorItem(MUArmorMaterial.INFUSEDUNSTABLE, EquipmentSlot.FEET, (new net.minecraft.world.item.Item.Properties()).tab(CreativeTab.getInstance())));

    // Opinium Cores

    public static final net.minecraftforge.registries.RegistryObject<Item> IronOpiniumCore = ModRegistry.ITEMS.register("iron_opinium_core", () -> new Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.Item> GoldOpiniumCore = ModRegistry.ITEMS.register("gold_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.Item> DiamondOpiniumCore = ModRegistry.ITEMS.register("diamond_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<Item> NetheriteOpiniumCore = ModRegistry.ITEMS.register("netherite_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.Item> EmeraldOpiniumCore = ModRegistry.ITEMS.register("emerald_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<Item> ChorusOpiniumCore = ModRegistry.ITEMS.register("chorus_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.Item> EXPOpiniumCore = ModRegistry.ITEMS.register("experience_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final RegistryObject<net.minecraft.world.item.Item> NetherStarOpiniumCore = ModRegistry.ITEMS.register("nether_star_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<Item> TheFinalOpiniumCore = ModRegistry.ITEMS.register("the_final_opinium_core", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));

    public static final net.minecraftforge.registries.RegistryObject<Item> SpeedUpgrade = ModRegistry.ITEMS.register("speed_upgrade", () -> new net.minecraft.world.item.Item(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));

    public static final net.minecraftforge.registries.RegistryObject<Item> ExperiencePearl = ModRegistry.ITEMS.register("experience_pearl", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));
    public static final net.minecraftforge.registries.RegistryObject<Item> ExperiencePearl1x = ModRegistry.ITEMS.register("experience_pearl_1x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 1));
    public static final RegistryObject<net.minecraft.world.item.Item> ExperiencePearl2x = ModRegistry.ITEMS.register("experience_pearl_2x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 2));
    public static final RegistryObject<net.minecraft.world.item.Item> ExperiencePearl3x = ModRegistry.ITEMS.register("experience_pearl_3x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 3));
    public static final net.minecraftforge.registries.RegistryObject<Item> ExperiencePearl4x = ModRegistry.ITEMS.register("experience_pearl_4x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 4));
    public static final RegistryObject<net.minecraft.world.item.Item> ExperiencePearl5x = ModRegistry.ITEMS.register("experience_pearl_5x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 5));
    public static final net.minecraftforge.registries.RegistryObject<Item> ExperiencePearl6x = ModRegistry.ITEMS.register("experience_pearl_6x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 6));
    public static final net.minecraftforge.registries.RegistryObject<Item> ExperiencePearl7x = ModRegistry.ITEMS.register("experience_pearl_7x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 7));
    public static final RegistryObject<net.minecraft.world.item.Item> ExperiencePearl8x = ModRegistry.ITEMS.register("experience_pearl_8x", () -> new ExperiencePearl(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance()), 8));

    public static final net.minecraftforge.registries.RegistryObject<Item> MagicalEgg = ModRegistry.ITEMS.register("magical_egg", () -> new MagicalEgg(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));

    public static final net.minecraftforge.registries.RegistryObject<Item> GoldenLasso = ModRegistry.ITEMS.register("golden_lasso", () -> new GoldenLasso(new net.minecraft.world.item.Item.Properties().tab(CreativeTab.getInstance())));

    public static void register() {}
}
