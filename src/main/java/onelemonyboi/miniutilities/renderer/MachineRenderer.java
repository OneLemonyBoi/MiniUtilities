package onelemonyboi.miniutilities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.GuiUtils;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class MachineRenderer {
    public static void blockRenderInfo(RenderGameOverlayEvent.Text event) {
        PoseStack ms = event.getMatrixStack();

        HitResult mouseOver = Minecraft.getInstance().hitResult;
        if (!(mouseOver instanceof BlockHitResult)) {
            return;
        }

        BlockHitResult result = (BlockHitResult) mouseOver;
        Minecraft mc = Minecraft.getInstance();
        ClientLevel world = mc.level;
        BlockPos pos = result.getBlockPos();
        BlockEntity te = world.getBlockEntity(pos);

        if (!(te instanceof RenderInfoIdentifier)) {
            return;
        }

        ms.pushPose();
        List<MutableComponent> tooltip = ((RenderInfoIdentifier) te).getInfo();

        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        float posX = width / 2F ;
        float posY = height / 2F + 10;

        posX = Math.min(posX, width - 20);
        posY = Math.min(posY, height - 20);

        int count = 0;
        int maxLen = 0;
        for (MutableComponent component : tooltip) {
            int len = mc.font.width(component.getString());
            mc.font.drawShadow(ms, component, posX - (len / 2F), posY + count, 16777215);
            if (len > maxLen) {
                maxLen = mc.font.width(component.getString());
            }
            count += 12;
        }
        ms.popPose();
    }
}
