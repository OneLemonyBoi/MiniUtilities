package onelemonyboi.miniutilities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import onelemonyboi.miniutilities.init.ItemList;

import static onelemonyboi.miniutilities.renderer.AngelRingCheck.*;

@OnlyIn(Dist.CLIENT)
public class AngelRingRendererLeft extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    public AngelRingRendererLeft(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> playerModel) {
        super(playerModel);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Item item = ItemStack.EMPTY.getItem();
        if (player.hasPlayerInfo() && !player.isInvisible() && player.isWearing(PlayerModelPart.CAPE) && isEquipped(player)) {
            if (isBaseEquipped(player)) {item = ItemStack.EMPTY.getItem();}
            else if (isGoldEquipped(player)) {item = ItemList.GoldWing.get();}
            else if (isFeatherEquipped(player)) {item = ItemList.FeatherWing.get();}
            else if (isBatEquipped(player)) {item = ItemList.BatWing.get();}
            else if (isPeacockEquipped(player)) {item = ItemList.PeacockWing.get();}
            else if (isEnderDragonEquipped(player)) {item = ItemList.EnderDragonWing.get();}
            matrixStack.push();
            getEntityModel().bipedBody.translateRotate(matrixStack);
            float zTranslate = (float) (player.inventory.armorInventory.get(2) == ItemStack.EMPTY ? 0.3 : 0.4);
            matrixStack.translate(0.4, 0.2, zTranslate);
            matrixStack.scale(1f, -1f, -0.25f);
            matrixStack.rotate(Vector3f.YP.rotationDegrees(45));
            Minecraft.getInstance().getItemRenderer().renderItem(player, new ItemStack(item), ItemCameraTransforms.TransformType.NONE, false, matrixStack, buffer, player.world, 0xF000F0, OverlayTexture.NO_OVERLAY);
            matrixStack.pop();
        }
    }
}