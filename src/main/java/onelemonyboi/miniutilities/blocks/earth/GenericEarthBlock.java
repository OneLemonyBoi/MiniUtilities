package onelemonyboi.miniutilities.blocks.earth;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.startup.Config;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import net.minecraft.block.AbstractBlock.Properties;

public class GenericEarthBlock extends GrassBlock {
    public static final Function<Properties, GenericEarthBlock> CURSED_EARTH = properties -> new GenericEarthBlock(
        properties, true,
        MiniUtilities.cursedspreadable,
        false,
        Config.cursedEarthCheckAreaSize,
        Config.cursedEarthCheckAreaMaxEntityCount,
        Config.cursedEarthMinWaitTimer,
        GenericEarthBlock::getCursedEntities
    );

    public static final Function<Properties, GenericEarthBlock> BLURSED_EARTH = properties -> new GenericEarthBlock(
        properties, true,
        MiniUtilities.cursedspreadable,
        true,
        Config.blursedEarthCheckAreaSize,
        Config.blursedEarthCheckAreaMaxEntityCount,
        Config.blursedEarthMinWaitTimer,
        GenericEarthBlock::getBlursedEntities
    );

    public static final Function<Properties, GenericEarthBlock> BLESSED_EARTH = properties -> new GenericEarthBlock(
        properties, false,
        MiniUtilities.blessedspreadable,
        true,
        Config.blessedEarthCheckAreaSize,
        Config.blessedEarthCheckAreaMaxEntityCount,
        Config.blessedEarthMinWaitTimer,
        GenericEarthBlock::getBlessedEntities
    );

    private final boolean lightDecay;
    private final ITag<Block> spreadableBlocks;
    private final boolean spawnInPeaceful;
    private final IntValue spawnRadius;
    private final IntValue maxEntities;
    private final IntValue minWaitTimer;
    private final EntitySupplier entitySupplier;

    GenericEarthBlock(Properties properties, boolean lightDecay, ITag<Block> spreadableBlocks, boolean spawnInPeaceful, IntValue spawnRadius, IntValue maxEntities, IntValue minWaitTimer, EntitySupplier entitySupplier) {
        super(properties);

        this.lightDecay = lightDecay;
        this.spreadableBlocks = spreadableBlocks;
        this.spawnInPeaceful = spawnInPeaceful;
        this.spawnRadius = spawnRadius;
        this.maxEntities = maxEntities;
        this.minWaitTimer = minWaitTimer;
        this.entitySupplier = entitySupplier;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random random) {
        this.tick(state, world, pos, random);

        if (lightDecay && world.getMaxLocalRawBrightness(pos.above()) >= 7 && world.canSeeSkyFromBelowWater(pos)) {
            world.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
            return;
        }

        if (world.getBlockState(pos.above()).isAir()) {
            BlockState blockstate = this.defaultBlockState();
            for (int i = 0; i < 4; ++i) {
                BlockPos pos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (world.getBlockState(pos1).getBlock().is(spreadableBlocks) && world.getBlockState(pos1.above()).isAir(world, pos1.above())) {
                    world.setBlockAndUpdate(pos1, blockstate.setValue(SNOWY, world.getBlockState(pos1.above()).getBlock() == Blocks.SNOW));
                }
            }
        }
    }

    @Override
    @Deprecated
    @SuppressWarnings({"deprecation", "DeprecatedIsStillUsed"})
    public void tick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random random) {
        // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        if (!world.isAreaLoaded(pos, 3)) return;
        if (!world.getFluidState(pos.above()).isEmpty()) return;
        if (!spawnInPeaceful && world.getLevelData().getDifficulty() == Difficulty.PEACEFUL) return;
        if (!world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING) || (!spawnInPeaceful && world.getLevelData().getDifficulty() == Difficulty.PEACEFUL)) return;
        if (lightDecay && world.getMaxLocalRawBrightness(pos.above()) > 6) return; //Prevents Spawn on light level higher than 6 in cursed Earth

        int r = spawnRadius.get(); // Radius to check around block
        int livingEntityCount = world.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(pos.getX() - r, pos.getY(), pos.getZ() - r, pos.getX() + r, pos.getY() +1, pos.getZ() + r)).size();

        if (livingEntityCount > maxEntities.get()) return;

        world.getBlockTicks().scheduleTick(pos, this, world.random.nextInt(600) + minWaitTimer.get());
        Entity en = findMonsterToSpawn(world, pos.above(), random);
        if (en != null) {
            en.setPos(pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5);
            if (!world.noCollision(en) || !world.isUnobstructed(en)) return;
            world.addFreshEntity(en);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(@Nonnull BlockState stateIn, World worldIn, BlockPos pos, @Nonnull Random rand) {
        Random random = worldIn.random;
        Direction.Axis direction$axis = Direction.UP.getAxis();
        double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)Direction.UP.getStepX() : (double)random.nextFloat();
        double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)Direction.UP.getStepY() : (double)random.nextFloat();
        double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)Direction.UP.getStepZ() : (double)random.nextFloat();
        worldIn.addParticle(new RedstoneParticleData(1.0F, 1.0F, 1.0F, 1.0F), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean isValidBonemealTarget(@Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, boolean p_176473_4_) {
        return false;//no
    }

    @Override
    public void performBonemeal(@Nonnull ServerWorld world, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        //no
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull World world, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return false;//no
    }

    private Entity findMonsterToSpawn(ServerWorld world, BlockPos pos, Random rand) {
        List<MobSpawnInfo.Spawners> spawnOptions = entitySupplier.getEntities(world.getChunkSource(), world, pos);
        //required to account for structure based mobs such as wither skeletons
        //there is nothing to spawn
        if (spawnOptions.size() == 0) return null;

        int found = rand.nextInt(spawnOptions.size());
        MobSpawnInfo.Spawners entry = spawnOptions.get(found);

        // can the mob actually spawn here naturally, filters out mobs such as slimes which have more specific spawn requirements but
        // still show up in spawnlist; ignore them when force spawning
        boolean canSpawn = EntitySpawnPlacementRegistry.checkSpawnRules(entry.type, world, SpawnReason.NATURAL, pos, world.random);
        if (!canSpawn && MiniUtilities.blacklisted_entities.contains(entry.type)) return null;

        Entity ent = entry.type.create(world);
        if (ent instanceof MobEntity) {
            ((MobEntity) ent).finalizeSpawn(world, world.getCurrentDifficultyAt(pos), SpawnReason.NATURAL, null, null);
        }

        return ent;
    }

    public static void convertCursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableCursedEarth.get()) return;
        handleConvertEarth(event, Items.WITHER_ROSE::equals, BlockList.CursedEarth.get());
    }

    public static void convertBlessed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableBlessedEarth.get()) return;
        handleConvertEarth(event, Tags.Items.STORAGE_BLOCKS_IRON::contains, BlockList.BlessedEarth.get());
    }

    public static void convertBlursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableBlursedEarth.get()) return;
        handleConvertEarth(event, (item) -> ItemList.UnstableIngot.get().equals(item), BlockList.BlursedEarth.get());
    }

    private static void handleConvertEarth(PlayerInteractEvent.RightClickBlock event, ItemChecker itemChecker, Block defaultState) {
        PlayerEntity playerEntity = event.getPlayer();
        World world = playerEntity.level;
        BlockPos pos = event.getPos();

        if (playerEntity.isShiftKeyDown() && !world.isClientSide && itemChecker.isItemValid(event.getItemStack().getItem()) && world.getBlockState(pos).getBlock() == Blocks.DIRT) {
            world.setBlockAndUpdate(pos, defaultState.defaultBlockState());
        }
    }

    static List<MobSpawnInfo.Spawners> getCursedEntities(ServerChunkProvider chunkProvider, ServerWorld world, BlockPos pos) {
        //required to account for structure based mobs such as wither skeletons
        return chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureFeatureManager(), EntityClassification.MONSTER, pos);
    }
    static List<MobSpawnInfo.Spawners> getBlursedEntities(ServerChunkProvider chunkProvider, ServerWorld world, BlockPos pos) {
        //required to account for structure based mobs such as wither skeletons
        List<MobSpawnInfo.Spawners> spawnOptions = new ArrayList<>();

        spawnOptions.addAll(chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureFeatureManager(), EntityClassification.CREATURE, pos));
        spawnOptions.addAll(chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureFeatureManager(), EntityClassification.WATER_CREATURE, pos));
        spawnOptions.addAll(chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureFeatureManager(), EntityClassification.WATER_AMBIENT, pos));
        spawnOptions.addAll(chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureFeatureManager(), EntityClassification.MONSTER, pos));
        spawnOptions.addAll(chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureFeatureManager(), EntityClassification.AMBIENT, pos));

        return spawnOptions;
    }
    static List<MobSpawnInfo.Spawners> getBlessedEntities(ServerChunkProvider chunkProvider, ServerWorld world, BlockPos pos) {
        //required to account for structure based mobs such as wither skeletons
        return chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureFeatureManager(), EntityClassification.CREATURE, pos);
    }

    @FunctionalInterface
    private interface EntitySupplier {
        List<MobSpawnInfo.Spawners> getEntities(ServerChunkProvider chunkProvider, ServerWorld world, BlockPos pos);
    }
    @FunctionalInterface
    private interface ItemChecker {
        boolean isItemValid(Item item);
    }

    @Override
    public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        super.fallOn(worldIn, pos, entityIn, fallDistance);
    }
}
