package onelemonyboi.miniutilities;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import onelemonyboi.miniutilities.blocks.earth.EarthBlocks;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerBlock;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerBlock;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ContainerList;
import onelemonyboi.miniutilities.init.EntityList;
import onelemonyboi.miniutilities.items.Kikoku;
import onelemonyboi.miniutilities.items.enchantments.ExperienceHarvesterHandler;
import onelemonyboi.miniutilities.items.enchantments.MoltenHeadHandler;
import onelemonyboi.miniutilities.items.unstable.UnstableHoe;
import onelemonyboi.miniutilities.items.unstable.UnstableShears;
import onelemonyboi.miniutilities.misc.KeyBindingsHandler;
import onelemonyboi.miniutilities.renderer.MachineRenderer;
import onelemonyboi.miniutilities.packets.Packet;
import onelemonyboi.miniutilities.proxy.ClientProxy;
import onelemonyboi.miniutilities.proxy.IProxy;
import onelemonyboi.miniutilities.proxy.ServerProxy;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerScreen;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerScreen;
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
        Config.register();
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
        EVENT_BUS.addListener(ExperienceHarvesterHandler::handleEntityKill);
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

    public static Logger getLogger()
    {
        return LOGGER;
    }
}
