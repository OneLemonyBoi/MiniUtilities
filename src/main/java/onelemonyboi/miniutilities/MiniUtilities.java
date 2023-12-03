package onelemonyboi.miniutilities;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
import onelemonyboi.miniutilities.items.enchantments.ShotgunHandler;
import onelemonyboi.miniutilities.items.unstable.UnstableShears;
import onelemonyboi.miniutilities.misc.KeyBindingsHandler;
import onelemonyboi.miniutilities.packets.Packet;
import onelemonyboi.miniutilities.proxy.ClientProxy;
import onelemonyboi.miniutilities.proxy.IProxy;
import onelemonyboi.miniutilities.proxy.ServerProxy;
import onelemonyboi.miniutilities.startup.ClientStuff;
import onelemonyboi.miniutilities.startup.JSON.JSONLoader;
import onelemonyboi.miniutilities.startup.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod(MiniUtilities.MOD_ID)
public class MiniUtilities {
    public static final String MOD_ID = "miniutilities";
    public static final TagKey<Block> cursedspreadable = BlockTags.create(new ResourceLocation(MOD_ID, "cursedspreadable"));
    public static final TagKey<Block> blessedspreadable = BlockTags.create(new ResourceLocation(MOD_ID, "blessedspreadable"));
    public static final TagKey<Block> blursedspreadable = BlockTags.create(new ResourceLocation(MOD_ID, "blursedspreadable"));
    public static final TagKey<EntityType<?>> blacklisted_entities = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(MOD_ID, "blacklisted"));
    public static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public MiniUtilities()
    {
        ModRegistry.register();
        Config.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerEntityRenderers);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->this::doInClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CreativeTab::buildContents);
        JSONLoader.loadJSON();
        EVENT_BUS.register(this);
        EVENT_BUS.addListener(GenericEarthBlock::convertCursed);
        EVENT_BUS.addListener(GenericEarthBlock::convertBlessed);
        EVENT_BUS.addListener(GenericEarthBlock::convertBlursed);
        EVENT_BUS.addListener(UnstableShears::instantShear);
        EVENT_BUS.addListener(Kikoku::AnvilUpdateEvent);
        EVENT_BUS.addListener(Kikoku::AnvilRepairEvent);
        EVENT_BUS.addListener(KeyBindingsHandler::keybinds);
        EVENT_BUS.addListener(GoldenLasso::onRightClick);
        EVENT_BUS.addListener(MechanicalMinerBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(MechanicalPlacerBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(QuantumQuarryBlock::PlayerInteractEvent);
        EVENT_BUS.addListener(ShotgunHandler::handleBowShot);
        EVENT_BUS.addListener(ExperienceHarvesterHandler::handleEntityKill);
        EVENT_BUS.addListener(SpikeBlock::soundEvent);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientStuff::machineRender);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> EnchantmentTooltipHandler::register);
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

    private void doInClient(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerEntityLayers);
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

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        proxy.registerEntityRenderers(event);
    }

    public void registerEntityLayers(EntityRenderersEvent.AddLayers event) {
        proxy.registerEntityLayers(event);
    }
}
