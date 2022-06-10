package onelemonyboi.miniutilities.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.List;

public class Materials {
    public static final TagKey<Block> UNSTABLE_TAG = ModTags.Blocks.mod("needs_unstable_tool");
    public static final Tier UNSTABLE = TierSortingRegistry.registerTier(new ForgeTier(4, 0, 8, 8, 20, UNSTABLE_TAG, () -> Ingredient.of(ItemList.UnstableIngot.get())),
            new ResourceLocation("miniutilities:unstable"),
            List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
    public static final TagKey<Block> KIKOKU_TAG = ModTags.Blocks.mod("needs_kikoku_tool");
    public static final Tier KIKOKU = TierSortingRegistry.registerTier(new ForgeTier(5, 0, 0, 16, 250, KIKOKU_TAG, () -> Ingredient.of(ItemList.UnstableIngot.get())),
            new ResourceLocation("miniutilities:kikoku"),
            List.of(Tiers.NETHERITE), List.of());
}
