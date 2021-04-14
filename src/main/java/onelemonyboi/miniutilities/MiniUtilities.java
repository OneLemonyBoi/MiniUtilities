package onelemonyboi.miniutilities;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import onelemonyboi.miniutilities.blocks.EarthBlocks;
import onelemonyboi.miniutilities.blocks.complexblocks.QuantumQuarryBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.QuantumQuarryScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.MechanicalMinerBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.MechanicalPlacerBlock;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;
import onelemonyboi.miniutilities.items.Kikoku;
import onelemonyboi.miniutilities.items.enchantments.MoltenHeadHandler;
import onelemonyboi.miniutilities.items.unstable.UnstableHoe;
import onelemonyboi.miniutilities.items.unstable.UnstableShears;
import onelemonyboi.miniutilities.misc.KeyBindings;
import onelemonyboi.miniutilities.misc.KeyBindingsHandler;
import onelemonyboi.miniutilities.rewards.PatreonRewards;
import onelemonyboi.miniutilities.packets.Packet;
import onelemonyboi.miniutilities.proxy.ClientProxy;
import onelemonyboi.miniutilities.proxy.IProxy;
import onelemonyboi.miniutilities.proxy.ServerProxy;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.screens.MechanicalMinerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalblocks.tileentities.screens.MechanicalPlacerScreen;
import onelemonyboi.miniutilities.world.Config;
import onelemonyboi.miniutilities.world.WorldGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod(MiniUtilities.MOD_ID)
public class MiniUtilities {
    public static final String MOD_ID = "miniutilities";
    public static final ITag<Block> cursedspreadable = BlockTags.makeWrapperTag(new ResourceLocation(MOD_ID, "cursedspreadable").toString());
    public static final ITag<Block> blessedspreadable = BlockTags.makeWrapperTag(new ResourceLocation(MOD_ID, "blessedspreadable").toString());
    public static final ITag<Block> blursedspreadable = BlockTags.makeWrapperTag(new ResourceLocation(MOD_ID, "blursedspreadable").toString());
    public static final ITag<EntityType<?>> blacklisted_entities = EntityTypeTags.getTagById(new ResourceLocation(MOD_ID, "blacklisted").toString());
    public static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public MiniUtilities()
    {
        ModRegistry.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        EVENT_BUS.register(this);
        EVENT_BUS.addListener(EarthBlocks::convertCursed);
        EVENT_BUS.addListener(EarthBlocks::convertBlessed);
        EVENT_BUS.addListener(EarthBlocks::convertBlursed);
        EVENT_BUS.addListener(UnstableShears::instantShear);
        EVENT_BUS.addListener(UnstableHoe::hoeTransformation);
        EVENT_BUS.addListener(WorldGen::generateOres);
        EVENT_BUS.addListener(WorldGen::generatePlants);
        EVENT_BUS.addListener(Kikoku::AnvilUpdateEvent);
        EVENT_BUS.addListener(Kikoku::AnvilRepairEvent);
        EVENT_BUS.addListener(KeyBindingsHandler::keybinds);
        EVENT_BUS.addListener(MechanicalMinerBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(MechanicalPlacerBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(QuantumQuarryBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(MoltenHeadHandler::handleBlockBreak);
        EVENT_BUS.addListener(PatreonRewards::PatreonRewardsHandling);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
        Packet.main();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        this.preInit(event);
        this.init(event);
        this.postInit(event);
    }
    private void doClientStuff(final FMLClientSetupEvent event) // Render Stuff HERE!!
    {
        RenderTypeLookup.setRenderLayer(BlockList.CursedEarth.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlessedEarth.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlursedEarth.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.EnderLily.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.FlameLily.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.WhiteLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.LightGrayLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.GrayLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlackLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.RedLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.OrangeLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.YellowLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.LimeLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.GreenLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.LightBlueLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.CyanLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BlueLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.PurpleLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.MagentaLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.PinkLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.BrownLapisCaelestis.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.WoodenSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.IronSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.GoldSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.DiamondSpikes.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockList.NetheriteSpikes.get(), RenderType.getCutout());

        ScreenManager.registerFactory(ContainerList.MinerContainer.get(), MechanicalMinerScreen::new);
        ScreenManager.registerFactory(ContainerList.PlacerContainer.get(), MechanicalPlacerScreen::new);
        ScreenManager.registerFactory(ContainerList.QuarryContainer.get(), QuantumQuarryScreen::new);

        KeyBindings.register();
    }

    private void enqueueIMC(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().build());
    }

    private void preInit(FMLCommonSetupEvent event) {
        proxy.preInit(event);
    }

    private void init(FMLCommonSetupEvent event) {
        proxy.init(event);
    }

    private void postInit(FMLCommonSetupEvent event) {
        proxy.postInit(event);
    }

    public static Logger getLogger()
    {
        return LOGGER;
    }
}
