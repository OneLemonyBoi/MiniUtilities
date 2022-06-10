package onelemonyboi.miniutilities.data;

import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;

public class BlockTags extends BlockTagsProvider {
    public BlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, MiniUtilities.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Blocks.ORES_ENDER).add(BlockList.EnderOre.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_ENDER);
        tag(ModTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL).add(BlockList.EnderPearlBlock.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL);
        tag(ModTags.Blocks.STORAGE_BLOCKS_UNSTABLE).add(BlockList.UnstableBlock.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_UNSTABLE);

        // LAPIS CAELESTIS
        tag(ModTags.Blocks.WHITE_LAPIS_CAELESTIS).add(BlockList.WhiteLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.WHITE_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.LIGHT_GRAY_LAPIS_CAELESTIS).add(BlockList.LightGrayLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.LIGHT_GRAY_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.GRAY_LAPIS_CAELESTIS).add(BlockList.GrayLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.GRAY_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.BLACK_LAPIS_CAELESTIS).add(BlockList.BlackLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.BLACK_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.RED_LAPIS_CAELESTIS).add(BlockList.RedLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.RED_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.ORANGE_LAPIS_CAELESTIS).add(BlockList.OrangeLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.ORANGE_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.YELLOW_LAPIS_CAELESTIS).add(BlockList.YellowLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.YELLOW_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.LIME_LAPIS_CAELESTIS).add(BlockList.LimeLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.LIME_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.GREEN_LAPIS_CAELESTIS).add(BlockList.GreenLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.GREEN_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.LIGHT_BLUE_LAPIS_CAELESTIS).add(BlockList.LightBlueLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.LIGHT_BLUE_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.CYAN_LAPIS_CAELESTIS).add(BlockList.CyanLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.CYAN_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.BLUE_LAPIS_CAELESTIS).add(BlockList.BlueLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.BLUE_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.PURPLE_LAPIS_CAELESTIS).add(BlockList.PurpleLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.PURPLE_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.MAGENTA_LAPIS_CAELESTIS).add(BlockList.MagentaLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.MAGENTA_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.PINK_LAPIS_CAELESTIS).add(BlockList.PinkLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.PINK_LAPIS_CAELESTIS);
        tag(ModTags.Blocks.BROWN_LAPIS_CAELESTIS).add(BlockList.BrownLapisCaelestis.get());
        tag(ModTags.Blocks.LAPIS_CAELESTIS).addTag(ModTags.Blocks.BROWN_LAPIS_CAELESTIS);

        tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(BlockList.EnderPearlBlock.get(), BlockList.AngelBlock.get(), BlockList.WoodenSpikes.get(), BlockList.StoneDrum.get());
        tag(Tags.Blocks.NEEDS_GOLD_TOOL).add(BlockList.GoldSpikes.get());
        tag(net.minecraft.tags.BlockTags.NEEDS_STONE_TOOL).add(BlockList.EnderOre.get(), BlockList.IronSpikes.get(), BlockList.SolarPanelController.get(), BlockList.LaserHub.get(), BlockList.LaserPort.get(), BlockList.MechanicalMiner.get(), BlockList.MechanicalPlacer.get());
        tag(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL).add(BlockList.UnstableBlock.get(), BlockList.IronDrum.get(), BlockList.DiamondSpikes.get());
        tag(net.minecraft.tags.BlockTags.NEEDS_DIAMOND_TOOL).add(BlockList.NetheriteSpikes.get(), BlockList.ReinforcedLargeDrum.get(), BlockList.NetheriteReinforcedDrum.get(), BlockList.QuantumQuarry.get());
        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(BlockList.UnstableDrum.get());

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(BlockList.EnderOre.get(), BlockList.UnstableBlock.get(), BlockList.EnderPearlBlock.get(), BlockList.GoldSpikes.get(), BlockList.IronSpikes.get(), BlockList.DiamondSpikes.get(), BlockList.NetheriteSpikes.get(), BlockList.StoneDrum.get(), BlockList.IronDrum.get(), BlockList.ReinforcedLargeDrum.get(), BlockList.NetheriteReinforcedDrum.get(), BlockList.UnstableDrum.get(), BlockList.SolarPanelController.get(), BlockList.LaserHub.get(), BlockList.LaserPort.get(), BlockList.MechanicalMiner.get(), BlockList.MechanicalPlacer.get(), BlockList.QuantumQuarry.get(), BlockList.RedstoneClockBlock.get());
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).add(BlockList.WoodenSpikes.get());
    }
}
