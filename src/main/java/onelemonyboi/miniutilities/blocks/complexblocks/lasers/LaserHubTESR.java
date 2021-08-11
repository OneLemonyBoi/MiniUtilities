package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;

import java.awt.*;
import java.util.List;

public class LaserHubTESR extends TileEntityRenderer<LaserHubTile> {
    public LaserHubTESR(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LaserHubTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        List<Vector3d> vectorList = tile.getTargetedTEVectorInRadius(LaserPortTile.class, 16, 0.25);
        drawLinesFromPoint(matrixStack, Vector3d.copy(tile.getPos()), vectorList, new Color(255, 0, 0, 255), buffer, new Vector3d(0.5, 0.75, 0.5));
    }

    public static void drawLinesFromPoint(MatrixStack matrixStack, Vector3d pos, List<Vector3d> endPoints, Color color, IRenderTypeBuffer buffer, Vector3d startingPosOffset) {
        // Modified code from Owmii because I don't know how to use Blaze3D
        // Not anymore, completely changed up the code because it didn't my needs. That's what I get for taking the easy way
        // BTW, fuck rendering, it can go fuck itself in 4K UHD

        for (Vector3d portPos : endPoints) {
            matrixStack.push();
            Vector3d endPoint = pos.subtract(portPos);
            IVertexBuilder builder = buffer.getBuffer(RenderType.LINES);
            laser(builder, matrixStack, color, endPoint, startingPosOffset);
            matrixStack.pop();
            RenderSystem.disableDepthTest();
        }
    }

    private static void pos(IVertexBuilder builder, Matrix4f matrix4f, Matrix3f matrix3f, float x, float y, float z, int r, int g, int b) {
        builder.pos(matrix4f, x, y, z).color(r, g, b,255).tex(1, 1).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880 / 2).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    private static void laser(IVertexBuilder builder, MatrixStack matrixStack, Color color, Vector3d endPoint, Vector3d startPointOffset) {
        Matrix4f matrix4f = matrixStack.getLast().getMatrix();
        Matrix3f matrix3f = matrixStack.getLast().getNormal();

        pos(builder, matrix4f, matrix3f, (float) startPointOffset.x, (float) startPointOffset.y, (float) startPointOffset.z, color.getRed(), color.getGreen(), color.getBlue());
        pos(builder, matrix4f, matrix3f, (float) -endPoint.x, (float) -endPoint.y, (float) -endPoint.z, color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public boolean isGlobalRenderer(LaserHubTile laserHubTile) {
        return true;
    }
}
