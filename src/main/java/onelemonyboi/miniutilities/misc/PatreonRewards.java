package onelemonyboi.miniutilities.misc;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.MiniUtilities;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        TextFormatting[] rainbowArray = {TextFormatting.RED, TextFormatting.GOLD, TextFormatting.YELLOW, TextFormatting.GREEN, TextFormatting.BLUE, TextFormatting.LIGHT_PURPLE, TextFormatting.DARK_BLUE};
        String name = event.player.getName().getUnformattedComponentText();
        IFormattableTextComponent iFormattableTextComponent = new StringTextComponent(name);
        MiniUtilities.LOGGER.debug(event.player.getGameProfile().getName());
        String type = PatreonJSON.REWARD_MAP.getOrDefault(event.player.getGameProfile().getName(), "No Value");
        if (!type.equals("No Value")) {
            iFormattableTextComponent = new StringTextComponent("");
            if (type.equals("Rainbow")) {
                int count = 0;
                for (Character c : name.toCharArray()) {
                    IFormattableTextComponent tempFTC = new StringTextComponent(c.toString()).mergeStyle(rainbowArray[count]);
                    iFormattableTextComponent.appendSibling(tempFTC);
                    count = count == 6 ? 0 : count + 1;
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
