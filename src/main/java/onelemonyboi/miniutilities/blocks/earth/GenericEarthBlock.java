package onelemonyboi.miniutilities.blocks.earth;

import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
        MiniUtilities.blursedspreadable,
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
    private final TagKey<Block> spreadableBlocks;
    private final boolean spawnInPeaceful;
    private final IntValue spawnRadius;
    private final IntValue maxEntities;
    private final IntValue minWaitTimer;
    private final EntitySupplier entitySupplier;

    GenericEarthBlock(Properties properties, boolean lightDecay, TagKey<Block> spreadableBlocks, boolean spawnInPeaceful, IntValue spawnRadius, IntValue maxEntities, IntValue minWaitTimer, EntitySupplier entitySupplier) {
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
    public void randomTick(@Nonnull net.minecraft.world.level.block.state.BlockState state, @Nonnull ServerLevel world, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
        this.tick(state, world, pos, random);

        if (lightDecay && world.getMaxLocalRawBrightness(pos.above()) >= 7 && world.canSeeSkyFromBelowWater(pos)) {
            world.setBlockAndUpdate(pos, net.minecraft.world.level.block.Blocks.DIRT.defaultBlockState());
            return;
        }

        if (world.getBlockState(pos.above()).isAir()) {
            net.minecraft.world.level.block.state.BlockState blockstate = this.defaultBlockState();
            for (int i = 0; i < 4; ++i) {
                BlockPos pos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (world.getBlockState(pos1).is(spreadableBlocks) && world.getBlockState(pos1.above()).isAir()) {
                    world.setBlockAndUpdate(pos1, blockstate.setValue(SNOWY, world.getBlockState(pos1.above()).getBlock() == net.minecraft.world.level.block.Blocks.SNOW));
                }
            }
        }
    }

    @Override
    @Deprecated
    @SuppressWarnings({"deprecation", "DeprecatedIsStillUsed"})
    public void tick(@Nonnull net.minecraft.world.level.block.state.BlockState state, @Nonnull ServerLevel world, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
        // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        if (!world.isAreaLoaded(pos, 3)) return;
        if (!world.getFluidState(pos.above()).isEmpty()) return;
        if (!spawnInPeaceful && world.getLevelData().getDifficulty() == Difficulty.PEACEFUL) return;
        if (!world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING) || (!spawnInPeaceful && world.getLevelData().getDifficulty() == Difficulty.PEACEFUL)) return;
        if (lightDecay && world.getMaxLocalRawBrightness(pos.above()) > 6) return; //Prevents Spawn on light level higher than 6 in cursed Earth

        int r = spawnRadius.get(); // Radius to check around block
        int livingEntityCount = world.getEntitiesOfClass(LivingEntity.class, new AABB(pos.getX() - r, pos.getY(), pos.getZ() - r, pos.getX() + r, pos.getY() +1, pos.getZ() + r)).size();

        if (livingEntityCount > maxEntities.get()) return;

        world.scheduleTick(pos, this, world.random.nextInt(600) + minWaitTimer.get());
        net.minecraft.world.entity.Entity en = findMonsterToSpawn(world, pos.above(), random);
        if (en != null) {
            en.setPos(pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5);
            if (!world.noCollision(en) || !world.isUnobstructed(en)) return;
            world.addFreshEntity(en);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(@Nonnull BlockState stateIn, Level worldIn, BlockPos pos, @Nonnull RandomSource random) {
        Direction.Axis direction$axis = net.minecraft.core.Direction.UP.getAxis();
        double d1 = direction$axis == net.minecraft.core.Direction.Axis.X ? 0.5D + 0.5625D * (double) net.minecraft.core.Direction.UP.getStepX() : (double)random.nextFloat();
        double d2 = direction$axis == net.minecraft.core.Direction.Axis.Y ? 0.5D + 0.5625D * (double) net.minecraft.core.Direction.UP.getStepY() : (double)random.nextFloat();
        double d3 = direction$axis == net.minecraft.core.Direction.Axis.Z ? 0.5D + 0.5625D * (double) net.minecraft.core.Direction.UP.getStepZ() : (double)random.nextFloat();
        worldIn.addParticle(new DustParticleOptions(new Vector3f(1.0F, 1.0F, 1.0F), 1.0F), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean isValidBonemealTarget(@Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull net.minecraft.world.level.block.state.BlockState state, boolean p_176473_4_) {
        return false;//no
    }

    @Override
    public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_, BlockState p_221273_) {
        super.performBonemeal(p_221270_, p_221271_, p_221272_, p_221273_);
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull Level world, @Nonnull RandomSource random, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return false;//no
    }

    private Entity findMonsterToSpawn(ServerLevel world, BlockPos pos, RandomSource random) {
        WeightedRandomList<MobSpawnSettings.SpawnerData> spawnOptions = entitySupplier.getEntities(world.getChunkSource(), world, pos);
        //required to account for structure based mobs such as wither skeletons
        //there is nothing to spawn
        if (spawnOptions.unwrap().size() == 0) return null;
        MobSpawnSettings.SpawnerData entry = spawnOptions.getRandom(random).get();

        // can the mob actually spawn here naturally, filters out mobs such as slimes which have more specific spawn requirements but
        // still show up in spawnlist; ignore them when force spawning
        boolean canSpawn = SpawnPlacements.checkSpawnRules(entry.type, world, MobSpawnType.NATURAL, pos, world.random);
        if (!canSpawn && entry.type.is(MiniUtilities.blacklisted_entities)) return null;

        Entity ent = entry.type.create(world);
        if (ent instanceof Mob mob) {
            mob.finalizeSpawn(world, world.getCurrentDifficultyAt(pos), MobSpawnType.NATURAL, null, null);
        }

        return ent;
    }

    public static void convertCursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableCursedEarth.get()) return;
        handleConvertEarth(event, Items.WITHER_ROSE::equals, BlockList.CursedEarth.get());
    }

    public static void convertBlessed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableBlessedEarth.get()) return;
        handleConvertEarth(event, (item) -> item.getDefaultInstance().is(Tags.Items.STORAGE_BLOCKS_IRON), BlockList.BlessedEarth.get());
    }

    public static void convertBlursed(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.enableBlursedEarth.get()) return;
        handleConvertEarth(event, (item) -> ItemList.UnstableIngot.get().equals(item), BlockList.BlursedEarth.get());
    }

    private static void handleConvertEarth(PlayerInteractEvent.RightClickBlock event, ItemChecker itemChecker, net.minecraft.world.level.block.Block defaultState) {
        Player playerEntity = event.getEntity();
        Level world = playerEntity.level;
        BlockPos pos = event.getPos();

        if (playerEntity.isShiftKeyDown() && !world.isClientSide && itemChecker.isItemValid(event.getItemStack().getItem()) && world.getBlockState(pos).getBlock() == Blocks.DIRT) {
            world.setBlockAndUpdate(pos, defaultState.defaultBlockState());
        }
    }

    static WeightedRandomList<MobSpawnSettings.SpawnerData> getCursedEntities(ServerChunkCache chunkProvider, ServerLevel world, BlockPos pos) {
        return chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureManager(), MobCategory.MONSTER, pos);
    }
    static WeightedRandomList<MobSpawnSettings.SpawnerData> getBlursedEntities(ServerChunkCache chunkProvider, ServerLevel world, BlockPos pos) {
        List<MobSpawnSettings.SpawnerData> list = new ArrayList<>();

        for (MobCategory cat : MobCategory.values()) {
            if (cat.equals(MobCategory.MISC) || cat.equals(MobCategory.AXOLOTLS)) continue; // No killing axolotls 😡😡
            list.addAll(chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureManager(), cat, pos).unwrap());
        }

        return WeightedRandomList.create(list);
    }
    static WeightedRandomList<MobSpawnSettings.SpawnerData> getBlessedEntities(ServerChunkCache chunkProvider, ServerLevel world, BlockPos pos) {
        return chunkProvider.getGenerator().getMobsAt(world.getBiome(pos), world.structureManager(), MobCategory.CREATURE, pos);
    }

    @FunctionalInterface
    private interface EntitySupplier {
        WeightedRandomList<MobSpawnSettings.SpawnerData> getEntities(ServerChunkCache chunkProvider, ServerLevel world, BlockPos pos);
    }
    @FunctionalInterface
    private interface ItemChecker {
        boolean isItemValid(Item item);
    }
}
