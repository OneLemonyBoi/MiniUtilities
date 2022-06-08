package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.ItemList;

import java.util.Random;


import net.minecraft.block.AbstractBlock.Properties;

public class EnderLily extends CropsBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public EnderLily()
    {
        super(Properties.of(Material.PLANT).noCollission().randomTicks().sound(SoundType.CROP));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return Tags.Blocks.END_STONES.contains(state.getBlock()) || state.getBlock() instanceof SpreadableSnowyDirtBlock || ModTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL.contains(state.getBlock()) || Tags.Blocks.DIRT.contains(state.getBlock()) || state.is(Blocks.END_STONE);
    }

    @OnlyIn(Dist.CLIENT)
    protected IItemProvider getBaseSeedId()
    {
        return ItemList.EnderLilySeeds.get();
    }

    @OnlyIn(Dist.CLIENT)
    public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state)
    {
        return new ItemStack(this.getBaseSeedId());
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.ENDERMAN) {
            if (!worldIn.isClientSide && state.getValue(AGE) > 0 && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
                double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
                double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    entityIn.hurt(DamageSource.CACTUS, 1.0F);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.getValue(AGE) != 7) {
            return ActionResultType.PASS;
        } else if (state.getValue(AGE) == 7) {
            popResource(worldIn, pos, new ItemStack(Items.ENDER_PEARL, 1));
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENDER_PEARL_THROW, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
            worldIn.setBlock(pos, state.setValue(AGE, 4), 2);
            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        } else {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        // Only here to implement new growth chance getter and make lily grow slower

        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        int i = this.getAge(state);
        if (i < this.getMaxAge()) {
            float f = getLilyGrowthChance(this, worldIn, pos);
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int)((25.0F / f) * 4) + 1) == 0)) {
                worldIn.setBlock(pos, this.getStateForAge(i + 1), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
            }
        }
    }

    protected static float getLilyGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
        // This is only a thing so that lilies grow faster on End Stone
        float f = 1.0F;
        BlockPos blockpos = pos.below();
        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.offset(i, 0, j), net.minecraft.util.Direction.UP, (net.minecraftforge.common.IPlantable) blockIn)) {
                    f1 = 1.0F;
                    if (Tags.Blocks.END_STONES.contains(blockIn)) {
                        f1 = 3.0F;
                    }
                    else if (ModTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL.contains(blockIn)) {
                        f1 = 7.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }
        // Weird Minecraft planting efficiency stuff that is very dumb

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }
        return f;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this)
            return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
        return this.mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }
}