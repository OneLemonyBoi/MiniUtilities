package onelemonyboi.miniutilities.blocks.earth;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.*;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.startup.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// CREDIT FOR CODE BASE: TFARCENIM

public class BlursedEarthBlock extends GrassBlock {

    public BlursedEarthBlock(Properties properties) {
        super(properties);
    }

    // SPAWN RANGE: 200 - 800 (Similar to Spawner)

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tick(state, world, pos, random);
        if (world.getLight(pos.up()) >= 7 && world.canBlockSeeSky(pos)) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
        } else {
            if (world.getBlockState(pos.up()).isAir()) {
                BlockState blockstate = this.getDefaultState();
                for (int i = 0; i < 4; ++i) {
                    BlockPos pos1 = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (world.getBlockState(pos1).getBlock().isIn(MiniUtilities.blursedspreadable) && world.getBlockState(pos1.up()).isAir(world, pos1.up())) {
                        world.setBlockState(pos1, blockstate.with(SNOWY, world.getBlockState(pos1.up()).getBlock() == Blocks.SNOW));
                    }
                }
            }
        }
    }

    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 3))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading

        if (!world.getFluidState(pos.up()).isEmpty()) {
            return;
        }

        int r = Config.blursedEarthCheckAreaSize.get(); // Radius to check around block
        int livingEntityCount = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - r, pos.getY(), pos.getZ() - r, pos.getX() + r, pos.getY() +1, pos.getZ() + r)).size();
        if (livingEntityCount > Config.blursedEarthCheckAreaMaxEntityCount.get()) {
            return;
        }

        world.getPendingBlockTicks().scheduleTick(pos, this, world.rand.nextInt(600) + Config.blursedEarthMinWaitTimer.get());

        Entity en = findMonsterToSpawn(world, pos.up(), random);
        if (en != null) {
            en.setPosition(pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5);
            if (!world.hasNoCollisions(en) || !world.checkNoEntityCollision(en)) return;
            world.addEntity(en);
        }
    }


    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        spawnParticles(worldIn, pos);
    }

    private static void spawnParticles(World world, BlockPos pos) {
        Random random = world.rand;
        Direction.Axis direction$axis = Direction.UP.getAxis();
        double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)Direction.UP.getXOffset() : (double)random.nextFloat();
        double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)Direction.UP.getYOffset() : (double)random.nextFloat();
        double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)Direction.UP.getZOffset() : (double)random.nextFloat();
        world.addParticle(new RedstoneParticleData(0.568627451F, 0.568627451F, 0.568627451F, 1.0F), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean p_176473_4_) {
        return false;//no
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        //no
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos pos, BlockState state) {
        return false;//no
    }

    private Entity findMonsterToSpawn(ServerWorld world, BlockPos pos, Random rand) {
        //required to account for structure based mobs such as wither skeletons
        ServerChunkProvider s = world.getChunkProvider();
        List<MobSpawnInfo.Spawners> spawnOptions = new ArrayList<>();
        spawnOptions.addAll(s.getChunkGenerator().func_230353_a_(world.getBiome(pos), world.getStructureManager(), EntityClassification.CREATURE, pos));
        spawnOptions.addAll(s.getChunkGenerator().func_230353_a_(world.getBiome(pos), world.getStructureManager(), EntityClassification.WATER_CREATURE, pos));
        spawnOptions.addAll(s.getChunkGenerator().func_230353_a_(world.getBiome(pos), world.getStructureManager(), EntityClassification.WATER_AMBIENT, pos));
        spawnOptions.addAll(s.getChunkGenerator().func_230353_a_(world.getBiome(pos), world.getStructureManager(), EntityClassification.MONSTER, pos));
        spawnOptions.addAll(s.getChunkGenerator().func_230353_a_(world.getBiome(pos), world.getStructureManager(), EntityClassification.AMBIENT, pos));
        //required to account for structure based mobs such as wither skeletons
        //there is nothing to spawn
        if (spawnOptions.size() == 0) {
            return null;
        }
        int found = rand.nextInt(spawnOptions.size());
        MobSpawnInfo.Spawners entry = spawnOptions.get(found);
        //can the mob actually spawn here naturally, filters out mobs such as slimes which have more specific spawn requirements but
        // still show up in spawnlist; ignore them when force spawning
        if (!EntitySpawnPlacementRegistry.canSpawnEntity(entry.type, world, SpawnReason.NATURAL, pos, world.rand)
                && MiniUtilities.blacklisted_entities.contains(entry.type))
            return null;
        EntityType<?> type = entry.type;
        Entity ent = type.create(world);
        if (ent instanceof MobEntity)
            ((MobEntity) ent).onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.NATURAL, null, null);
        return ent;
    }

    public Tuple<Integer, Direction> modifiedGetRedstonePower(BlockPos pos, World world) {
        Tuple<Integer, Direction> tuple = new Tuple<>(0, null);

        for(Direction direction : Direction.values()) {
            int j = world.getRedstonePower(pos.offset(direction), direction);
            if (j >= 15) {
                tuple = new Tuple<>(15, direction);
                return tuple;
            }

            if (j > tuple.getA()) {
                tuple = new Tuple<>(j, direction);
            }
        }

        return tuple;
    }
}