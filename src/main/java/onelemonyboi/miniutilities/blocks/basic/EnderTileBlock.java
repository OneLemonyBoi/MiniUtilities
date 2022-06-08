package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class EnderTileBlock extends Block {
    public EnderTileBlock() {
        super(Properties.of(Material.GLASS).strength(4f).sound(SoundType.GLASS));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.join(Block.box(1, 1, 1, 15, 2, 15), Block.box(0, 0, 0, 16, 1, 16), IBooleanFunction.OR);
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        int yPos = entityIn.blockPosition().getY();
        for (int x = 1; x <= (256 - yPos); x++) {
            Boolean blockCheck = !worldIn.isEmptyBlock(entityIn.blockPosition().above(x)) &&
                    worldIn.getBlockState(entityIn.blockPosition().above(x)).getBlock() != Blocks.BEDROCK &&
                    worldIn.getBlockState(entityIn.blockPosition().above(x)).getBlock() != BlockList.ChorusTile.get() &&
                    worldIn.isEmptyBlock(entityIn.blockPosition().above(x+1)) &&
                    worldIn.isEmptyBlock(entityIn.blockPosition().above(x+2));

            if (blockCheck){
                entityIn.teleportToWithTicket(entityIn.getX(), yPos + x + 1, entityIn.getZ());
                entityIn.playSound(SoundEvents.ENDERMAN_TELEPORT, 1, 1);
                return;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        spawnParticles(worldIn, pos);
    }

    private static void spawnParticles(World world, BlockPos pos) {
        Random random = world.random;
        Direction.Axis direction$axis = Direction.NORTH.getAxis();
        double d1 = direction$axis == Direction.Axis.X ? 0.5D: (double)random.nextFloat();
        double d2 = direction$axis == Direction.Axis.Y ? 0.5D : (double)random.nextFloat();
        double d3 = direction$axis == Direction.Axis.Z ? 0.5D : (double)random.nextFloat();
        world.addParticle(new RedstoneParticleData(0.25F, 0.25F, 1, 1), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
    }
}