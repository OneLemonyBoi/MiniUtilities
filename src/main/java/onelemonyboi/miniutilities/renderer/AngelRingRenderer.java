package onelemonyboi.miniutilities.renderer;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.init.ItemList;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static onelemonyboi.miniutilities.MiniUtilities.MOD_ID;

public class AngelRingRenderer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    public AngelRingRenderer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> playerModel) {
        super(playerModel);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        String name = player.getGameProfile().getName();
        if (player.hasPlayerInfo() && !player.isInvisible() && player.isWearing(PlayerModelPart.CAPE)) {
            matrixStack.push();
            getEntityModel().bipedBody.translateRotate(matrixStack);
            matrixStack.translate(0, 0.25, 0.25);
            matrixStack.scale(1f, -1f, -0.25f);
            Minecraft.getInstance().getItemRenderer().renderItem(player, ItemList.EnderDust.get().getDefaultInstance(), ItemCameraTransforms.TransformType.NONE, false, matrixStack, buffer, player.world, 0xF000F0, OverlayTexture.NO_OVERLAY);
            matrixStack.pop();
        }
    }
}