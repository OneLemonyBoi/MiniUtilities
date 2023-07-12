package onelemonyboi.miniutilities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.init.ItemList;
import org.joml.Quaternionf;

import static onelemonyboi.miniutilities.renderer.AngelRingCheck.*;

@OnlyIn(Dist.CLIENT)
public class AngelRingRendererRight extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public AngelRingRendererRight(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> playerModel) {
        super(playerModel);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Item item = net.minecraft.world.item.ItemStack.EMPTY.getItem();
        if (player.isCapeLoaded() && !player.isInvisible() && player.isModelPartShown(PlayerModelPart.CAPE) && isEquipped(player)) {
            if (isBaseEquipped(player)) {item = ItemStack.EMPTY.getItem();}
            else if (isGoldEquipped(player)) {item = ItemList.GoldWing.get();}
            else if (isFeatherEquipped(player)) {item = ItemList.FeatherWing.get();}
            else if (isBatEquipped(player)) {item = ItemList.BatWing.get();}
            else if (isPeacockEquipped(player)) {item = ItemList.PeacockWing.get();}
            else if (isEnderDragonEquipped(player)) {item = ItemList.EnderDragonWing.get();}
            matrixStack.pushPose();
            getParentModel().body.translateAndRotate(matrixStack);
            float zTranslate = (float) (player.getInventory().armor.get(2) == ItemStack.EMPTY ? 0.3 : 0.4);
            matrixStack.translate(-0.4, 0.2, 0.4);
            matrixStack.scale(0.9f, 0.9f, 0.9f);
            matrixStack.mulPose(new Quaternionf().rotateY((float) (Math.PI / 6)));
            matrixStack.scale(-1, -1, -1);
            Minecraft.getInstance().getItemRenderer().renderStatic(player, new ItemStack(item), ItemDisplayContext.NONE, false, matrixStack, buffer, player.level(), 0xF000F0, OverlayTexture.NO_OVERLAY, player.getId());
            matrixStack.popPose();
        }
    }
}