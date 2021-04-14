package onelemonyboi.miniutilities.rewards;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class PatreonRewards {
    /**
     BLACK
     DARK_BLUE
     DARK_GREEN
     DARK_AQUA
     DARK_RED
     DARK_PURPLE
     GOLD
     GRAY
     DARK_GRAY
     BLUE
     GREEN
     AQUA
     RED
     LIGHT_PURPLE
     YELLOW
     WHITE
     OBFUSCATED
     BOLD
     STRIKETHROUGH
     UNDERLINE
     ITALIC

     RAINBOW:
     RED
     GOLD
     YELLOW
     GREEN
     BLUE
     LIGHT_PURPLE
     DARK_PURPLE
     */

    public static void PatreonRewardsHandling(TickEvent.PlayerTickEvent event) {
        // TODO: CREATE LIBRARY AND: ADD THIS CODE TO IT AND START WORK ON LONG FLUID HANDLING
        // TODO: LONG FLUID HANDLING CHECKLIST: FLUIDSTACK, FLUIDHANDLER, AND IFLUIDTANK?

        TextFormatting[] rainbowArray = {TextFormatting.RED, TextFormatting.GOLD, TextFormatting.YELLOW, TextFormatting.GREEN, TextFormatting.BLUE, TextFormatting.LIGHT_PURPLE, TextFormatting.DARK_BLUE};
        TextFormatting[] transArray = {TextFormatting.RED, TextFormatting.AQUA, TextFormatting.WHITE, TextFormatting.AQUA, TextFormatting.RED};
        TextFormatting[] biArray = {TextFormatting.LIGHT_PURPLE, TextFormatting.DARK_PURPLE, TextFormatting.BLUE};
        TextFormatting[] lesbianArray = {TextFormatting.RED, TextFormatting.GOLD, TextFormatting.WHITE, TextFormatting.LIGHT_PURPLE, TextFormatting.DARK_PURPLE};
        TextFormatting[] asexualArray = {TextFormatting.BLACK, TextFormatting.GRAY, TextFormatting.WHITE, TextFormatting.LIGHT_PURPLE};
        TextFormatting[] panArray = {TextFormatting.LIGHT_PURPLE, TextFormatting.GOLD, TextFormatting.AQUA};
        TextFormatting[] queerArray = {TextFormatting.LIGHT_PURPLE, TextFormatting.WHITE, TextFormatting.DARK_GREEN};
        TextFormatting[] nonBinaryArray = {TextFormatting.YELLOW, TextFormatting.WHITE, TextFormatting.DARK_PURPLE, TextFormatting.BLACK}; // TODO: FINISH THIS
        String name = event.player.getCustomName() == null ? event.player.getGameProfile().getName() : event.player.getCustomName().getUnformattedComponentText();
        IFormattableTextComponent iFormattableTextComponent = new StringTextComponent(name);
        String type = PatreonJSON.REWARD_MAP.getOrDefault(event.player.getGameProfile().getName(), "No Value");
        if (!type.equals("No Value")) {
            if (type.equals("Rainbow")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(rainbowArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 6 ? 0 : count + 1;
                }
            }
            else if (type.equals("TransFlag")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(transArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 4 ? 0 : count + 1;
                }
            }
            else if (type.equals("BiFlag")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(biArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 2 ? 0 : count + 1;
                }
            }
            else if (type.equals("LesbianFlag")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(lesbianArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 4 ? 0 : count + 1;
                }
            }
            else if (type.equals("AsexualFlag")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(asexualArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 3 ? 0 : count + 1;
                }
            }
            else if (type.equals("PansexualFlag")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(panArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 2 ? 0 : count + 1;
                }
            }
            else if (type.equals("GenderqueerFlag")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(queerArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 2 ? 0 : count + 1;
                }
            }
            else if (type.equals("NonBinaryFlag")) {
                iFormattableTextComponent = new StringTextComponent("");
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(nonBinaryArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 3 ? 0 : count + 1;
                }
            }
            else {
                iFormattableTextComponent.mergeStyle(TextFormatting.fromColorIndex(Integer.parseInt(type)));
            }
        }
        event.player.setCustomName(iFormattableTextComponent);
        ObfuscationReflectionHelper.setPrivateValue(PlayerEntity.class, event.player, iFormattableTextComponent, "displayname");
    }
}
