package onelemonyboi.miniutilities;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ObjectHolder;
import onelemonyboi.miniutilities.blocks.EarthBlocks;
import onelemonyboi.miniutilities.items.BlockList;
import onelemonyboi.miniutilities.world.Config;
import onelemonyboi.miniutilities.world.OreGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod(MiniUtilities.MOD_ID)
public class MiniUtilities {
    public static final String MOD_ID = "miniutilities";
    public static final ITag<Block> cursedspreadable = BlockTags.makeWrapperTag(new ResourceLocation(MOD_ID, "cursedspreadable").toString());
    public static final ITag<Block> blessedspreadable = BlockTags.makeWrapperTag(new ResourceLocation(MOD_ID, "blessedspreadable").toString());
    public static final ITag<EntityType<?>> blacklisted_entities = EntityTypeTags.getTagById(new ResourceLocation(MOD_ID, "blacklisted").toString());
    public static final Logger LOGGER = LogManager.getLogger();

    public MiniUtilities()
    {
        ModRegistry.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        EVENT_BUS.register(this);
        EVENT_BUS.addListener(EarthBlocks::convertCursed);
        EVENT_BUS.addListener(EarthBlocks::convertBlessed);
        EVENT_BUS.addListener(OreGen::generateOres);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.CONFIG, "MiniUtilities.toml");
        Config.loadConfig(Config.CONFIG, FMLPaths.CONFIGDIR.get().resolve("MiniUtilities.toml").toString());
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
    private void doClientStuff(final FMLClientSetupEvent event) // Render Stuff HERE!!
    {
        RenderTypeLookup.setRenderLayer(BlockList.CursedEarth.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlessedEarth.get(), RenderType.getCutout());
    }

    public static Logger getLogger()
    {
        return LOGGER;
    }
}
