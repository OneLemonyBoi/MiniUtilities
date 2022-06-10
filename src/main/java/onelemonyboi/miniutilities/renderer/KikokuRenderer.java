package onelemonyboi.miniutilities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Item;
import com.mojang.math.Vector3f;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.init.ItemList;

@OnlyIn(Dist.CLIENT)
public class KikokuRenderer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public KikokuRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> playerModel) {
        super(playerModel);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        String name = player.getGameProfile().getName();
        Boolean inInvAndNotUsing = false;
        Inventory inv = player.getInventory();
        for (ItemStack itemStack : inv.items) {
            Item item = itemStack.getItem();
            if (item == ItemList.Kikoku.get()) {inInvAndNotUsing = true;}
        }
        for (net.minecraft.world.item.ItemStack itemStack : inv.offhand) {
            net.minecraft.world.item.Item item = itemStack.getItem();
            if (item == ItemList.Kikoku.get()) {inInvAndNotUsing = false;}
        }
        if (inv.getSelected().getItem() == ItemList.Kikoku.get()) {inInvAndNotUsing = false;}

        if (player.isCapeLoaded() && !player.isInvisible() && player.isModelPartShown(PlayerModelPart.CAPE) && inInvAndNotUsing) {
            matrixStack.pushPose();
            getParentModel().body.translateAndRotate(matrixStack);
            matrixStack.translate(0, 0.25, 0.2);
            matrixStack.scale(1f, -1f, -0.25f);
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(180));
            Minecraft.getInstance().getItemRenderer().renderStatic(player, ItemList.Kikoku.get().getDefaultInstance(), ItemTransforms.TransformType.NONE, false, matrixStack, buffer, player.level, 0xF000F0, OverlayTexture.NO_OVERLAY, player.getId());
            matrixStack.popPose();
        }
    }
}
