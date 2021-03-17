package onelemonyboi.miniutilities.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.blocks.AngelBlockItem;
import onelemonyboi.miniutilities.items.AngelRing;
import onelemonyboi.miniutilities.items.ItemSeeds;
import onelemonyboi.miniutilities.items.ItemTier;
import onelemonyboi.miniutilities.items.Kikoku;
import onelemonyboi.miniutilities.items.unstable.*;

import static onelemonyboi.miniutilities.items.ItemTier.UNSTABLE;

public class ItemList {
    public static final RegistryObject<Item> EnderDust = ModRegistry.ITEMS.register("ender_dust", () ->
            new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> AngelBlockItem = ModRegistry.ITEMS.register("angel_block", () ->
            new AngelBlockItem(BlockList.AngelBlock.get()));
    public static final RegistryObject<Item> Kikoku = ModRegistry.ITEMS.register("kikoku", () ->
            new Kikoku(ItemTier.KIKOKU,  13, -2.4F, (new Item.Properties().group(CreativeTab.getInstance()))));
    public static final RegistryObject<Item> EnderLilySeeds = ModRegistry.ITEMS.register("ender_lily_seeds", () ->
            new ItemSeeds(BlockList.EnderLily.get()));
    public static final RegistryObject<Item> FlameLilySeeds = ModRegistry.ITEMS.register("flame_lily_seeds", () ->
            new ItemSeeds(BlockList.FlameLily.get()));
    public static final RegistryObject<Item> Cinderpearl = ModRegistry.ITEMS.register("cinderpearl", () ->
            new Item(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> BaseAngelRing = ModRegistry.ITEMS.register("angel_ring", AngelRing::new);
    public static final RegistryObject<Item> GoldAngelRing = ModRegistry.ITEMS.register("gold_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> EnderDragonAngelRing = ModRegistry.ITEMS.register("ender_dragon_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> FeatherAngelRing = ModRegistry.ITEMS.register("feather_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> BatAngelRing = ModRegistry.ITEMS.register("bat_angel_ring", AngelRing::new);
    public static final RegistryObject<Item> PeacockAngelRing = ModRegistry.ITEMS.register("peacock_angel_ring", AngelRing::new);


    // Unstable
    public static final RegistryObject<Item> UnstableAxe = ModRegistry.ITEMS.register("healing_axe", () ->
            new UnstableAxe(UNSTABLE, 1, -3, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> UnstableHoe = ModRegistry.ITEMS.register("reversing_hoe", () ->
            new UnstableHoe(UNSTABLE, -8, 0, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> UnstableIngot = ModRegistry.ITEMS.register("unstable_ingot", () ->
            new UnstableIngot(new Item.Properties().group(CreativeTab.getInstance()).setNoRepair().maxDamage(200)));
    public static final RegistryObject<Item> UnstablePickaxe = ModRegistry.ITEMS.register("destruction_pickaxe", () ->
            new UnstablePickaxe(UNSTABLE, -3, -2.8f, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> UnstableShears = ModRegistry.ITEMS.register("precision_shears", () ->
            new UnstableShears(new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> UnstableShovel = ModRegistry.ITEMS.register("erosion_shovel", () ->
            new UnstableShovel(UNSTABLE, -2.5f, -3, new Item.Properties().group(CreativeTab.getInstance())));
    public static final RegistryObject<Item> UnstableSword = ModRegistry.ITEMS.register("etheric_sword", () ->
            new UnstableSword(UNSTABLE, -1, -2.4f, new Item.Properties().group(CreativeTab.getInstance())));


    public static void register() {}
}
