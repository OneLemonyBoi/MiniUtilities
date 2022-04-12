package onelemonyboi.miniutilities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import onelemonyboi.miniutilities.blocks.earth.GenericEarthBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerBlock;
import onelemonyboi.miniutilities.blocks.spikes.SpikeBlock;
import onelemonyboi.miniutilities.init.FeatureList;
import onelemonyboi.miniutilities.items.GoldenLasso;
import onelemonyboi.miniutilities.items.Kikoku;
import onelemonyboi.miniutilities.items.enchantments.EnchantmentTooltipHandler;
import onelemonyboi.miniutilities.items.enchantments.ExperienceHarvesterHandler;
import onelemonyboi.miniutilities.items.enchantments.MoltenHeadHandler;
import onelemonyboi.miniutilities.items.enchantments.ShotgunHandler;
import onelemonyboi.miniutilities.items.unstable.UnstableHoe;
import onelemonyboi.miniutilities.items.unstable.UnstableShears;
import onelemonyboi.miniutilities.items.unstable.UnstableShovel;
import onelemonyboi.miniutilities.misc.KeyBindingsHandler;
import onelemonyboi.miniutilities.packets.Packet;
import onelemonyboi.miniutilities.proxy.ClientProxy;
import onelemonyboi.miniutilities.proxy.IProxy;
import onelemonyboi.miniutilities.proxy.ServerProxy;
import onelemonyboi.miniutilities.startup.ClientStuff;
import onelemonyboi.miniutilities.startup.JSON.JSONLoader;
import onelemonyboi.miniutilities.startup.Config;
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
        Config.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        JSONLoader.loadJSON();
        EVENT_BUS.register(this);
        EVENT_BUS.addListener(GenericEarthBlock::convertCursed);
        EVENT_BUS.addListener(GenericEarthBlock::convertBlessed);
        EVENT_BUS.addListener(GenericEarthBlock::convertBlursed);
        EVENT_BUS.addListener(UnstableShears::instantShear);
        EVENT_BUS.addListener(UnstableHoe::hoeTransformation);
        EVENT_BUS.addListener(UnstableShovel::shovelTransformation);
        EVENT_BUS.addListener(WorldGen::generate);
        EVENT_BUS.addListener(Kikoku::AnvilUpdateEvent);
        EVENT_BUS.addListener(Kikoku::AnvilRepairEvent);
        EVENT_BUS.addListener(KeyBindingsHandler::keybinds);
        EVENT_BUS.addListener(GoldenLasso::onRightClick);
        EVENT_BUS.addListener(MechanicalMinerBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(MechanicalPlacerBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(QuantumQuarryBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(MoltenHeadHandler::handleBlockBreak);
        EVENT_BUS.addListener(ShotgunHandler::handleBowShot);
        EVENT_BUS.addListener(ExperienceHarvesterHandler::handleEntityKill);
        EVENT_BUS.addListener(SpikeBlock::soundEvent);
        EVENT_BUS.addListener(EnchantmentTooltipHandler::onTooltipDisplay);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Feature.class, EventPriority.LOW, FeatureList::addConfigFeatures);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientStuff::machineRender);
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
        ClientStuff.clientStuff();
//        KeyBindings.register();
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
}
