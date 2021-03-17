package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;
import onelemonyboi.miniutilities.MiniUtilities;

import java.util.List;
import java.util.Random;

// CREDIT FOR CODE BASE: TFARCENIM

public class BlursedEarthBlock extends GrassBlock {
    public BlursedEarthBlock(Properties properties) {
        super(properties);
    }

    // SPAWN RANGE: 200 - 800 (Similar to Spawner)

    @Deprecated
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(state, world, pos, oldState, isMoving);
        int i = 200;
        if (i == 0) {
            i = 1;
        }
        world.getPendingBlockTicks().scheduleTick(pos, this, world.rand.nextInt(600) + i);
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isRemote) {
            int j = 200;
            if (j == 0) {
                j = 1;
            }
            world.getPendingBlockTicks().scheduleTick(pos, this, world.rand.nextInt(600) + j);
            if (!world.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (false) {
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

            world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), random.nextInt(601));
            if (!world.getFluidState(pos.up()).isEmpty()) return;
            long mobcount = world.getEntities().filter(IMob.class::isInstance).count();
            if (mobcount > 250) return;
            int r = 1;
            if (world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(-r, -r, -r, r, r, r)).size() > 0)
                return;
            Entity en = findMonsterToSpawn(world, pos.up(), random);
            if (en != null) {
                en.setPosition(pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5);
                if (!world.hasNoCollisions(en) || !world.checkNoEntityCollision(en)) return;
                world.addEntity(en);
            }
        }
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

    public static boolean isInDaylight(World world, BlockPos pos) {
        return world.isDaytime() && world.getBrightness(pos.up()) > 0.5F;
    }

    private Entity findMonsterToSpawn(ServerWorld world, BlockPos pos, Random rand) {
        //required to account for structure based mobs such as wither skeletons
        ServerChunkProvider s = world.getChunkProvider();
        List<MobSpawnInfo.Spawners> spawnOptions = s.getChunkGenerator().func_230353_a_(world.getBiome(pos), world.getStructureManager(), EntityClassification.CREATURE, pos);
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
}