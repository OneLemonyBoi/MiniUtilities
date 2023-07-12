package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.awt.*;
import java.util.List;

// I have no idea how this code works.
public class LaserHubTESR implements BlockEntityRenderer<LaserHubTile> {
    public LaserHubTESR(BlockEntityRendererProvider.Context provider) {

    }

    @Override
    public void render(LaserHubTile tile, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        List<Vec3> vectorList = tile.getTEVectorsInRadius(LaserPortTile.class, 16, 0.25);
        drawLinesFromPoint(matrixStack, Vec3.atLowerCornerOf(tile.getBlockPos()), vectorList, new Color(255, 0, 0, 255), buffer, new Vec3(0.5, 0.75, 0.5));
    }

    public static void drawLinesFromPoint(PoseStack matrixStack, Vec3 pos, List<Vec3> endPoints, Color color, MultiBufferSource buffer, Vec3 startingPosOffset) {
        for (Vec3 portPos : endPoints) {
            matrixStack.pushPose();
            Vec3 endPoint = pos.subtract(portPos);
            VertexConsumer builder = buffer.getBuffer(RenderType.LINES);
            laser(builder, matrixStack, color, endPoint, startingPosOffset);
            matrixStack.popPose();
            RenderSystem.disableDepthTest();
        }
    }

    private static void pos(VertexConsumer builder, Matrix4f matrix4f, Matrix3f matrix3f, float x, float y, float z, int r, int g, int b) {
        builder.vertex(matrix4f, x, y, z).color(r, g, b,255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880 / 2).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    private static void laser(VertexConsumer builder, PoseStack matrixStack, Color color, Vec3 endPoint, Vec3 startPointOffset) {
        Matrix4f matrix4f = matrixStack.last().pose();
        Matrix3f matrix3f = matrixStack.last().normal();

        pos(builder, matrix4f, matrix3f, (float) startPointOffset.x, (float) startPointOffset.y, (float) startPointOffset.z, color.getRed(), color.getGreen(), color.getBlue());
        pos(builder, matrix4f, matrix3f, (float) -endPoint.x, (float) -endPoint.y, (float) -endPoint.z, color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public boolean shouldRenderOffScreen(LaserHubTile laserHubTile) {
        return true;
    }
}
