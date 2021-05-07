package onelemonyboi.miniutilities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;

import java.util.ArrayList;
import java.util.List;

public class MachineRenderer {
    public static void blockRenderInfo(RenderGameOverlayEvent.Text event) {
        MatrixStack ms = event.getMatrixStack();

        RayTraceResult mouseOver = Minecraft.getInstance().objectMouseOver;
        if (!(mouseOver instanceof BlockRayTraceResult)) {
            return;
        }

        BlockRayTraceResult result = (BlockRayTraceResult) mouseOver;
        Minecraft mc = Minecraft.getInstance();
        ClientWorld world = mc.world;
        BlockPos pos = result.getPos();
        TileEntity te = world.getTileEntity(pos);

        if (!(te instanceof RenderInfoIdentifier)) {
            return;
        }

        ms.push();
        List<ITextComponent> tooltip = ((RenderInfoIdentifier) te).getInfo();

        int width = mc.getMainWindow().getScaledWidth();
        int height = mc.getMainWindow().getScaledHeight();
        float posX = width / 2F ;
        float posY = height / 2F + 10;

        posX = Math.min(posX, width - 20);
        posY = Math.min(posY, height - 20);

        int count = 0;
        int maxLen = 0;
        for (ITextComponent component : tooltip) {
            int len = mc.fontRenderer.getStringWidth(component.getString());
            mc.fontRenderer.drawTextWithShadow(ms, component, posX - (len / 2F), posY + count, 16777215);
            if (len > maxLen) {
                maxLen = mc.fontRenderer.getStringWidth(component.getString());
            }
            count += 12;
        }
        ms.pop();
    }
}
