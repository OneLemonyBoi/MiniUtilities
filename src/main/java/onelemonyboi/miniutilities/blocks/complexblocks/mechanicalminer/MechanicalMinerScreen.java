package onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer;

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
import net.minecraft.util.text.TranslationTextComponent;
import onelemonyboi.lemonlib.blocks.ItemStackButton;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.packets.Packet;
import onelemonyboi.miniutilities.packets.RedstoneModeUpdate;

public class MechanicalMinerScreen extends ContainerScreen<MechanicalMinerContainer> {
    private static final ResourceLocation TestDisplay = new ResourceLocation(MiniUtilities.MOD_ID,
            "textures/gui/mechanical_miner.png");
    public ItemStackButton redstoneButton;

    public MechanicalMinerScreen(MechanicalMinerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 201;
    }

    @Override
    protected void init() {
        super.init();
        Item baseItem = Items.REDSTONE;
        switch (container.te.redstonemode) {
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

        redstoneButton = new ItemStackButton(this.guiLeft + 156, this.guiTop + 4, 16, 16, new StringTextComponent(""), this::changeRedstone, baseItem, this::displayMode);
        addButton(redstoneButton);
    }

    @Override
    public ITextComponent getTitle() {
        return new StringTextComponent("Mechanical Miner");
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        this.font.drawText(matrixStack, this.playerInventory.getDisplayName(), (float) this.playerInventoryTitleX,
                (float) this.playerInventoryTitleY, 4210752);
        this.font.drawText(matrixStack, this.getTitle(), (float) this.playerInventoryTitleX,
                6, 4210752);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bindTexture(TestDisplay);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
    }

    public void changeRedstone(Button x) {
        if (redstoneButton.item == Items.REDSTONE) {
            redstoneButton.item = Items.GLOWSTONE_DUST;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(2, this.container.te.getPos()));
        }
        else if (redstoneButton.item == Items.GLOWSTONE_DUST) {
            redstoneButton.item = Items.GUNPOWDER;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(3, this.container.te.getPos()));
        }
        else if (redstoneButton.item == Items.GUNPOWDER) {
            redstoneButton.item = Items.REDSTONE;
            Packet.INSTANCE.sendToServer(new RedstoneModeUpdate(1, this.container.te.getPos()));
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
