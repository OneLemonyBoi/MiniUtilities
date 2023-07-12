package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import onelemonyboi.lemonlib.gui.ItemStackButton;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.packets.Packet;
import onelemonyboi.miniutilities.packets.RedstoneModeUpdate;

import java.util.Optional;

public class MechanicalPlacerScreen extends AbstractContainerScreen<MechanicalPlacerContainer> {
    private static final ResourceLocation TestDisplay = new net.minecraft.resources.ResourceLocation(MiniUtilities.MOD_ID,
            "textures/gui/base.png");
    public ItemStackButton redstoneButton;

    public MechanicalPlacerScreen(MechanicalPlacerContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);

        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 175;
        this.imageHeight = 201;
    }

    @Override
    protected void init() {
        super.init();
        Item baseItem = net.minecraft.world.item.Items.REDSTONE;
        switch (menu.te.redstonemode) {
            case 1:
                baseItem = net.minecraft.world.item.Items.REDSTONE;
                break;
            case 2:
                baseItem = net.minecraft.world.item.Items.GLOWSTONE_DUST;
                break;
            case 3:
                baseItem = net.minecraft.world.item.Items.GUNPOWDER;
                break;
        }

        redstoneButton = new ItemStackButton(this.leftPos + 156, this.topPos + 4, 16, 16, Component.literal(""), this::changeRedstone, baseItem);
        addRenderableWidget(redstoneButton);
    }

    @Override
    public Component getTitle() {
        return Component.literal("Mechanical Placer");
    }

    @Override
    public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (redstoneButton.isHovered()) displayMode(redstoneButton, matrixStack, mouseX, mouseY);
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TestDisplay);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        matrixStack.blit(TestDisplay, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    public void changeRedstone(Button x) {
        if (redstoneButton.item == Items.REDSTONE) {
            redstoneButton.item = net.minecraft.world.item.Items.GLOWSTONE_DUST;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(2, this.menu.te.getBlockPos()));
        }
        else if (redstoneButton.item == net.minecraft.world.item.Items.GLOWSTONE_DUST) {
            redstoneButton.item = net.minecraft.world.item.Items.GUNPOWDER;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(3, this.menu.te.getBlockPos()));
        }
        else if (redstoneButton.item == Items.GUNPOWDER) {
            redstoneButton.item = net.minecraft.world.item.Items.REDSTONE;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(1, this.menu.te.getBlockPos()));
        }
    }

    public void displayMode(Button buttons, GuiGraphics matrixStack, int mouseX, int mouseY) {
        Component tooltip;
        if (redstoneButton.item == net.minecraft.world.item.Items.REDSTONE) {
            tooltip = Component.translatable("text.miniutilities.redstonemodeone");
        }
        else if (redstoneButton.item == net.minecraft.world.item.Items.GLOWSTONE_DUST) {
            tooltip = Component.translatable("text.miniutilities.redstonemodetwo");
        }
        else if (redstoneButton.item == net.minecraft.world.item.Items.GUNPOWDER) {
            tooltip = Component.translatable("text.miniutilities.redstonemodethree");
        }
        else {
            tooltip = Component.translatable("text.miniutilities.redstonemodeone");
        }
        matrixStack.renderTooltip(this.font, ImmutableList.of(tooltip), Optional.empty(), mouseX, mouseY);
    }
}
