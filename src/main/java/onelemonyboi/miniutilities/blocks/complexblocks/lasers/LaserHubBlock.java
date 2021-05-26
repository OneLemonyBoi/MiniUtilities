package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class LaserHubBlock extends Block {
    public LaserHubBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TEList.LaserHubTile.get().create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        ItemStack itemStack = new ItemStack(this);
        CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
        itemStack.setTagInfo("BlockEntityTag", compoundNBT);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World world, BlockPos pos, Random rand) {
        if (world.getTileEntity(pos) instanceof LaserHubTile) {
            LaserHubTile tile = (LaserHubTile) world.getTileEntity(pos);
            for (Vector3d vector3d : tile.getTargetedTEVectorInRadius(LaserPortTile.class, 16)) {

            }
        }
    }

    public static void drawLinesFromPoint(RenderWorldLastEvent event, Vector3d point, List<Vector3d> endPoints, Color color) {
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        IVertexBuilder builder = buffer.getBuffer(RenderType.LINES);

        //move where the line is in the world. otherwise it is drawn around origin 0,0,0 I think?
        Vector3d view = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();
        MatrixStack matrixStack = event.getMatrixStack();
        RenderSystem.lineWidth(3);

        matrixStack.push();
        matrixStack.translate(-view.x, -view.y, -view.z);
        Matrix4f matrix = matrixStack.getLast().getMatrix();

        for (Vector3d endPoint : endPoints)
        {
            builder.pos(matrix,(float) point.getX(), (float) point.getY(), (float) point.getZ())
                    .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                    .endVertex();

            builder.pos(matrix, (float) endPoint.getX(), (float) endPoint.getY(), (float) endPoint.getZ())
                    .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                    .endVertex();
        }

        matrixStack.pop();
        RenderSystem.disableDepthTest();
        buffer.finish(RenderType.LINES);

    }
}

//if (world.getTileEntity(pos) instanceof LaserHubTile) {
//            LaserHubTile tile = (LaserHubTile) world.getTileEntity(pos);
//            for (BlockPos blockPos : tile.getTargetedTEPosInRadius(LaserPortTile.class, 16)) {
//                if (!blockPos.equals(BlockPos.ZERO)){
//                    LaserPortTile port = (LaserPortTile) world.getTileEntity(blockPos);
//                    Vector3i vector =  port.getBlockState().get(LaserPortBlock.FACING).getOpposite().getDirectionVec();
//                    Vector3d vec = new Vector3d(pos.getX(), pos.getY(), pos.getZ());
//                    vec = vec.add(0.5, 0.5, 0.5).add(vector.getX() /2D, vector.getY()/2D, vector.getZ() / 2D);
//                    Vector3d velocity = vec.subtractReverse(new Vector3d(blockPos.getX(), blockPos.getY(), blockPos.getZ()).add(0.5, 0.5, 0.5));
//                    double vel = 10;
//                    world.addParticle(ParticleTypes.END_ROD, vec.getX(), vec.getY(), vec.getZ(), velocity.x /vel ,velocity.y/vel,velocity.z /vel);
//                }
//            }
//        }
