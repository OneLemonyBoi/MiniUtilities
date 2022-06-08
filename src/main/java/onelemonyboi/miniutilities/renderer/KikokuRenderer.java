package onelemonyboi.miniutilities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.init.ItemList;

@OnlyIn(Dist.CLIENT)
public class KikokuRenderer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
    public KikokuRenderer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> playerModel) {
        super(playerModel);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        String name = player.getGameProfile().getName();
        Boolean inInvAndNotUsing = false;
        PlayerInventory inv = player.inventory;
        for (ItemStack itemStack : inv.items) {
            Item item = itemStack.getItem();
            if (item == ItemList.Kikoku.get()) {inInvAndNotUsing = true;}
        }
        for (ItemStack itemStack : inv.offhand) {
            Item item = itemStack.getItem();
            if (item == ItemList.Kikoku.get()) {inInvAndNotUsing = false;}
        }
        if (inv.getSelected().getItem() == ItemList.Kikoku.get()) {inInvAndNotUsing = false;}

        if (player.isCapeLoaded() && !player.isInvisible() && player.isModelPartShown(PlayerModelPart.CAPE) && inInvAndNotUsing) {
            matrixStack.pushPose();
            getParentModel().body.translateAndRotate(matrixStack);
            matrixStack.translate(0, 0.25, 0.2);
            matrixStack.scale(1f, -1f, -0.25f);
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(180));
            Minecraft.getInstance().getItemRenderer().renderStatic(player, ItemList.Kikoku.get().getDefaultInstance(), ItemCameraTransforms.TransformType.NONE, false, matrixStack, buffer, player.level, 0xF000F0, OverlayTexture.NO_OVERLAY);
            matrixStack.popPose();
        }
    }
}
