package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import onelemonyboi.lemonlib.gui.ItemStackButton;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.packets.Packet;
import onelemonyboi.miniutilities.packets.RedstoneModeUpdate;

public class QuantumQuarryScreen extends ContainerScreen<QuantumQuarryContainer> {
    private static final ResourceLocation Base = new ResourceLocation(MiniUtilities.MOD_ID,
            "textures/gui/base.png");
    public ItemStackButton redstoneButton;

    public QuantumQuarryScreen(QuantumQuarryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        Item baseItem = Items.REDSTONE;
        switch (menu.te.redstonemode) {
            case 1:
                baseItem = Items.REDSTONE;
                break;
            case 2:
                baseItem = Items.GLOWSTONE_DUST;
                break;
            case 3:
                baseItem = Items.GUNPOWDER;
                break;
        }

        redstoneButton = new ItemStackButton(this.leftPos + 156, this.topPos + 4, 16, 16, new StringTextComponent(""), this::changeRedstone, baseItem, this::displayMode);
        addButton(redstoneButton);
    }

    @Override
    public ITextComponent getTitle() {
        return new StringTextComponent("Quantum Quarry").withStyle(TextFormatting.BLUE);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        this.font.draw(matrixStack, this.inventory.getDisplayName(), (float) this.inventoryLabelX,
                (float) this.inventoryLabelY, 4210752);
        this.font.draw(matrixStack, this.getTitle(), (float) this.inventoryLabelX,
                6, 4210752);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bind(Base);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    public void changeRedstone(Button x) {
        if (redstoneButton.item == Items.REDSTONE) {
            redstoneButton.item = Items.GLOWSTONE_DUST;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(2, this.menu.te.getBlockPos()));
        }
        else if (redstoneButton.item == Items.GLOWSTONE_DUST) {
            redstoneButton.item = Items.GUNPOWDER;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(3, this.menu.te.getBlockPos()));
        }
        else if (redstoneButton.item == Items.GUNPOWDER) {
            redstoneButton.item = Items.REDSTONE;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(1, this.menu.te.getBlockPos()));
        }
    }

    public void displayMode(Button buttons, MatrixStack matrixStack, int mouseX, int mouseY) {
        TranslationTextComponent tooltip;
        if (redstoneButton.item == Items.REDSTONE) {
            tooltip = new TranslationTextComponent("text.miniutilities.redstonemodeone");
        }
        else if (redstoneButton.item == Items.GLOWSTONE_DUST) {
            tooltip = new TranslationTextComponent("text.miniutilities.redstonemodetwo");
        }
        else if (redstoneButton.item == Items.GUNPOWDER) {
            tooltip = new TranslationTextComponent("text.miniutilities.redstonemodethree");
        }
        else {
            tooltip = new TranslationTextComponent("text.miniutilities.redstonemodeone");
        }
        this.renderTooltip(matrixStack, tooltip, mouseX, mouseY);
    }
}