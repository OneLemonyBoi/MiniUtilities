package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import onelemonyboi.miniutilities.world.Configs;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class UnstableIngot extends Item implements IItemColor {

    public static final DamageSource DIVIDE_BY_DIAMOND = (new DamageSource("divide_by_diamond").setDamageBypassesArmor());
    public static final DamageSource ESCAPE_DIVIDE_BY_DIAMOND = (new DamageSource("escape_divide_by_diamond").setDamageBypassesArmor());

    public UnstableIngot(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()){
            tooltip.add(new StringTextComponent("Make sure not to blow up!").mergeStyle(TextFormatting.AQUA));
        }
    }

    @SubscribeEvent
    public static void playertick(TickEvent.PlayerTickEvent e) {

        if (e.phase == TickEvent.Phase.START) return;

        Container container = e.player.openContainer;
        ContainerType<?> type = ObfuscationReflectionHelper.getPrivateValue(Container.class,container,"field_216965_e");

        World world = e.player.world;

        if (world.isRemote) return;
        boolean explode = false;

        List<Slot> inventorySlots = container.inventorySlots;
        for (Slot slot : inventorySlots) {
            if (!(slot.getSlotIndex() == 0)) {
                ItemStack stack = slot.getStack();
                if ((stack.getItem() instanceof UnstableIngot)) {
                    if (stack.getDamage() == 20) {
                        slot.putStack(ItemStack.EMPTY);
                        explode = true;
                        continue;
                    }
                    if (e.player.world.getDayTime() % 20 == 0) {
                        stack.setDamage(stack.getDamage() + 1);
                        e.player.sendMessage(ITextComponent.getTextComponentOrEmpty(String.valueOf(stack.getDamage())), UUID.randomUUID());
                    }
                }
            }
        }
        if (!explode) return;
        PlayerEntity p = e.player;
        world.createExplosion(null, p.getPosX(), p.getPosY(), p.getPosZ(), 1, Explosion.Mode.NONE);
        p.attackEntityFrom(DIVIDE_BY_DIAMOND, Integer.MAX_VALUE);
    }

    @SubscribeEvent
    public static void onContainerClose(PlayerContainerEvent.Close e) {
        PlayerEntity p = e.getPlayer();
        Container c = e.getContainer();
        boolean explode = false;
        for (Slot slot : c.inventorySlots) {
            ItemStack stack = slot.getStack();
            if (!checkExplosion(stack) || slot instanceof CraftingResultSlot) continue;
            slot.putStack(ItemStack.EMPTY);
            explode = true;
        }
        if (!explode) return;
        p.world.createExplosion(null, p.getPosX(), p.getPosY(), p.getPosZ(), 1, Explosion.Mode.NONE);
        p.attackEntityFrom(ESCAPE_DIVIDE_BY_DIAMOND, 100);
    }

    @SubscribeEvent
    public static void onItemDrop(ItemTossEvent e) {
        PlayerEntity p = e.getPlayer();
        ItemEntity entityItem = e.getEntityItem();
        ItemStack stack = entityItem.getItem();
        if (checkExplosion(stack)) {
            p.world.createExplosion(null, p.getPosX(), p.getPosY(), p.getPosZ(), 1, Explosion.Mode.NONE);
            p.attackEntityFrom(ESCAPE_DIVIDE_BY_DIAMOND, 100);
            e.setCanceled(true);
        }
    }

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if (!stack.hasTag()) {
            return 0xffffff;
        } else {
            int color = stack.getDamage();
            double scale = color / 400d;

            int red, green, blue;

            if (scale >= .5) {
                red = green = 0xff;

                blue = (int) ((2 * scale - 1) * 0xff);
            } else if (scale >= .25) {
                red = 0xff;
                green = (int) (2 * scale * 0xff);
                blue = 0;
            } else {
                scale *= 256;
                scale = Math.floor(scale);
                scale %= 2;
                switch ((int) scale) {
                    case 0: {
                        red = 0xff;
                        green = blue = 0;
                        break;
                    }
                    case 1: {
                        red = green = 0xff;
                        blue = 0;
                        break;
                    }
                    default:
                        //this should never be anything other than 1 or 0
                        throw new IllegalStateException("hi boo");
                }
            }
            return (red << 16) + (green << 8) + blue;
        }
    }

    public static boolean checkExplosion(ItemStack stack) {
        return stack.getItem() instanceof UnstableIngot && stack.getDamage() < 20;
    }
}
