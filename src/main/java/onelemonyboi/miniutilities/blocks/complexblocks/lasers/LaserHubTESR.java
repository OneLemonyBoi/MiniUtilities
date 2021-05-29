package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import onelemonyboi.miniutilities.MiniUtilities;
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
        drawLinesFromPoint(matrixStack, Vector3d.copy(tile.getPos().add(0.5, 0.5, 0.5)), vectorList, new Color(255, 0, 0, 255), buffer);
    }

    public static void drawLinesFromPoint(MatrixStack matrixStack, Vector3d pos, List<Vector3d> endPoints, Color color, IRenderTypeBuffer buffer) {
        // Modified code from Owmii because I don't know how to use Blaze3D
        for (Vector3d portPos : endPoints) {
            matrixStack.push();

            // PUSHES TO MIDDLE
            matrixStack.translate(0.5D, 0.5D, 0.5D);

            // Gets relative pos?
            Vector3d vec3d2 = pos.subtract(portPos);

            // Gets length
            float d0 = (float) vec3d2.length();

            // Normalizes so is between 1
            vec3d2 = vec3d2.normalize();

            // Normalize sometimes gives vector with length > 1, which breaks acos if the y component is < -1 or > 1
            float f5 = (float) Math.acos(MathHelper.clamp(vec3d2.y, -1.0, 1.0));
            float f6 = (float) MathHelper.atan2(vec3d2.z, vec3d2.x);

            // Rotates matrixstack to align with the block
            matrixStack.rotate(Vector3f.YP.rotationDegrees((((float) Math.PI / 2F) - f6) * (180F / (float) Math.PI)));
            matrixStack.rotate(Vector3f.XP.rotationDegrees(f5 * (180F / (float) Math.PI)));

            // Magic multiplication
            float d23 = d0 * 4.55F;

            // Rendertype for lines
            IVertexBuilder builder = buffer.getBuffer(RenderType.LINES);

            // Renders laser
            laser(builder, matrixStack, d0, color, d23);

            // Pop goes the weasel
            matrixStack.pop();
        }
    }

    private static void pos(IVertexBuilder builder, Matrix4f matrix4f, Matrix3f matrix3f, float x, float y, float z, int r, int g, int b, float u, float v) {
        builder.pos(matrix4f, x, y, z).color(r, g, b,255).tex(u, v).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880 / 2).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    private static void laser(IVertexBuilder builder, MatrixStack matrixStack, float length, Color color, float mx) {
        MatrixStack.Entry last = matrixStack.getLast();
        Matrix4f matrix4f = last.getMatrix();
        Matrix3f matrix3f = last.getNormal();

        pos(builder, matrix4f, matrix3f, 0, 0.0F, 0, color.getRed(), color.getGreen(), color.getBlue(), 1, mx);
        pos(builder, matrix4f, matrix3f, 0, -length, 0, color.getRed(), color.getGreen(), color.getBlue(),1, -0.5F);
    }

    @Override
    public boolean isGlobalRenderer(LaserHubTile laserHubTile) {
        return true;
    }
}
