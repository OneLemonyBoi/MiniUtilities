package onelemonyboi.miniutilities.blocks.basic;

import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.init.BlockList;

import java.util.Random;

public class EnderTileBlock extends Block {
    public EnderTileBlock() {
        super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(Material.GLASS).strength(4f).sound(SoundType.GLASS));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Shapes.join(Block.box(1, 1, 1, 15, 2, 15), Block.box(0, 0, 0, 16, 1, 16), BooleanOp.OR);
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, net.minecraft.core.BlockPos pos, Entity entityIn) {
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
    public void animateTick(BlockState stateIn, Level worldIn, net.minecraft.core.BlockPos pos, Random rand) {
        spawnParticles(worldIn, pos);
    }

    private static void spawnParticles(Level world, net.minecraft.core.BlockPos pos) {
        RandomSource random = world.random;
        Direction.Axis direction$axis = Direction.NORTH.getAxis();
        double d1 = direction$axis == Direction.Axis.X ? 0.5D: (double)random.nextFloat();
        double d2 = direction$axis == Direction.Axis.Y ? 0.5D : (double)random.nextFloat();
        double d3 = direction$axis == Direction.Axis.Z ? 0.5D : (double)random.nextFloat();
        world.addParticle(new DustParticleOptions(new Vector3f(0.25F, 0.25F, 1), 1), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
    }
}