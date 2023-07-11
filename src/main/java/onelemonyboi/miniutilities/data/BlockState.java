package onelemonyboi.miniutilities.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;

public class BlockState extends BlockStateProvider {
    private ExistingFileHelper exFileHelper;

    public BlockState(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MiniUtilities.MOD_ID, exFileHelper);

        this.exFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockList.EnderOre.get());
        simpleBlock(BlockList.EnderPearlBlock.get());
        simpleBlock(BlockList.AngelBlock.get());

        // Lapis Caelestis
        simpleBlock(BlockList.WhiteLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.LightGrayLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.GrayLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.BlackLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.RedLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.OrangeLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.YellowLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.LimeLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.GreenLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.LightBlueLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.CyanLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.BlueLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.PurpleLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.MagentaLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.PinkLapisCaelestis.get(), "cutout");
        simpleBlock(BlockList.BrownLapisCaelestis.get(), "cutout");

        // Special Glass
        simpleBlock(BlockList.EtherealGlass.get(), "cutout");
        simpleBlock(BlockList.ReverseEtherealGlass.get(), "cutout");
        simpleBlock(BlockList.RedstoneGlass.get(), "cutout");
        simpleBlock(BlockList.GlowingGlass.get(), "cutout");
        simpleBlock(BlockList.DarkGlass.get(), "cutout");
        simpleBlock(BlockList.DarkEtherealGlass.get(), "cutout");
        simpleBlock(BlockList.DarkReverseEtherealGlass.get(), "cutout");

        simpleBlock(BlockList.LapisLamp.get());

        ModelFile modelOne = models().cubeAll("redstone_clock_off", modLoc("block/redstone_clock_off"));
        ModelFile modelTwo = models().cubeAll("redstone_clock_active", modLoc("block/redstone_clock_active"));

        getVariantBuilder(BlockList.RedstoneClockBlock.get())
                .partialState().with(net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED, true)
                .modelForState().modelFile(modelOne).addModel()
                .partialState().with(BlockStateProperties.POWERED, false)
                .modelForState().modelFile(modelTwo).addModel();
    }

    private void simpleBlock(Block block, String renderType) {
        BlockModelBuilder model = (BlockModelBuilder) cubeAll(block);
        simpleBlock(block, model.renderType(renderType));
    }
}
