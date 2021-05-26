package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
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

public class LaserHubTESR extends TileEntityRenderer<LaserHubTile> {
    public LaserHubTESR(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LaserHubTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        List<Vector3d> vectorList = tile.getTargetedTEVectorInRadius(LaserPortTile.class, 16);
        drawLinesFromPoint(matrixStack, Vector3d.copy(tile.getPos().add(0.5, 0.5, 0.5)), vectorList, new Color(255, 255, 255, 255), buffer);
    }

    public static void drawLinesFromPoint(MatrixStack matrixStack, Vector3d point, List<Vector3d> endPoints, Color color, IRenderTypeBuffer buffer) {
        IVertexBuilder builder = buffer.getBuffer(RenderType.getTripwire());

        //move where the line is in the world. otherwise it is drawn around origin 0,0,0 I think?
        Vector3d view = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();

        matrixStack.push();
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
    }

    @Override
    public boolean isGlobalRenderer(LaserHubTile tileEntityMBE21) {
        return true;
    }
}
