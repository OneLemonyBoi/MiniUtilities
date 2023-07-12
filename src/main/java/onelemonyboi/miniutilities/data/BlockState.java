package onelemonyboi.miniutilities.data;

import net.minecraft.core.Registry;
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
        super(gen.getPackOutput(), MiniUtilities.MOD_ID, exFileHelper);

        this.exFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockList.EnderOre.get());
        simpleBlock(BlockList.EnderPearlBlock.get());
        simpleBlock(BlockList.AngelBlock.get());

        // Lapis Caelestis
        simpleEmissiveBlock(BlockList.WhiteLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.LightGrayLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.GrayLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.BlackLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.RedLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.OrangeLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.YellowLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.LimeLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.GreenLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.LightBlueLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.CyanLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.BlueLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.PurpleLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.MagentaLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.PinkLapisCaelestis.get(), "cutout");
        simpleEmissiveBlock(BlockList.BrownLapisCaelestis.get(), "cutout");

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

    private void simpleEmissiveBlock(Block block, String renderType) {
        BlockModelBuilder model = (BlockModelBuilder) cubeAll(block);
        model = model.renderType(renderType);
        model.element().allFaces((direction, builder) -> {
            builder.ao(false);
            builder.emissivity(15, 0);
            builder.texture("#all");
        });
        simpleBlock(block, model);
    }
}
