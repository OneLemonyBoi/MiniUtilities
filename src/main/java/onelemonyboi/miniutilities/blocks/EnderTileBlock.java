package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.MiniUtilities;

import java.util.Random;

public class EnderTileBlock extends Block {
    public EnderTileBlock() {
        super(Properties.create(Material.GLASS).hardnessAndResistance(4f).sound(SoundType.GLASS));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0, 0, 0, 16, 2, 16);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        int yPos = entityIn.getPosition().getY();
        for (int x = 1; x <= (256 - yPos); x++) {
            Boolean blockCheck = !worldIn.isAirBlock(entityIn.getPosition().up(x)) && worldIn.getBlockState(entityIn.getPosition().up(x)).getBlock() != Blocks.BEDROCK && worldIn.isAirBlock(entityIn.getPosition().up(x+1)) && worldIn.isAirBlock(entityIn.getPosition().up(x+2));
            if (blockCheck){
                MiniUtilities.LOGGER.debug(worldIn.getBlockState(entityIn.getPosition().up(x)).toString());
                MiniUtilities.LOGGER.debug(worldIn.getBlockState(entityIn.getPosition().up(x+1)).toString());
                MiniUtilities.LOGGER.debug(worldIn.getBlockState(entityIn.getPosition().up(x+2)).toString());
                entityIn.teleportKeepLoaded(entityIn.getPosX(), yPos + x + 1, entityIn.getPosZ());
                entityIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                return;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        spawnParticles(worldIn, pos);
    }

    private static void spawnParticles(World world, BlockPos pos) {
        Random random = world.rand;
        Direction.Axis direction$axis = Direction.NORTH.getAxis();
        double d1 = direction$axis == Direction.Axis.X ? 0.5D: (double)random.nextFloat();
        double d2 = direction$axis == Direction.Axis.Y ? 0.5D : (double)random.nextFloat();
        double d3 = direction$axis == Direction.Axis.Z ? 0.5D : (double)random.nextFloat();
        world.addParticle(ParticleTypes.PORTAL, (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
    }
}