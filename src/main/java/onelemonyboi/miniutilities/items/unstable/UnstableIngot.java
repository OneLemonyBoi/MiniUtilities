package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.world.Config;

import java.util.List;

@Mod.EventBusSubscriber
public class UnstableIngot extends Item {

    public static final DamageSource DIVIDE_BY_DIAMOND = (new DamageSource("divide_by_diamond").setDamageBypassesArmor().setDamageAllowedInCreativeMode());
    public static final DamageSource UNSTABLE_DIVISION = (new DamageSource("unstable_division").setDamageBypassesArmor().setDamageAllowedInCreativeMode());

    public UnstableIngot(Properties properties) {
        super(properties);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote){
            int type = Config.unstableIngotType.get();
            if (entity instanceof PlayerEntity) {
                PlayerEntity playerEntity = ((PlayerEntity) entity);
                if (type == 0) {
                    playerEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100));
                }
                if (type != 0) {
                    setDamage(stack, stack.getDamage() + 1);
                }
                if (stack.getDamage() == 200 && type != 0) {
                    if (type == 1) {
                        playerEntity.sendStatusMessage((new TranslationTextComponent("text.miniutilities.ingotisunstable").mergeStyle(TextFormatting.RED)), true);
                        stack.setDisplayName(new TranslationTextComponent("text.miniutilities.unstableingot"));
                        CompoundNBT compoundNBT = stack.getOrCreateTag();
                        compoundNBT.putInt("timeunstable", 0);
                    }
                    else if (type == 2) {
                        stack.shrink(1);
                        world.createExplosion(null, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), 1, Explosion.Mode.NONE);
                        playerEntity.attackEntityFrom(DIVIDE_BY_DIAMOND, Float.MAX_VALUE);
                    }
                }
                if (stack.getDamage() > 200 && type == 1) {
                    CompoundNBT compoundNBT = stack.getOrCreateTag();
                    compoundNBT.putInt("timeunstable", compoundNBT.getInt("timeunstable") + 1);
                    int unstable = compoundNBT.getInt("timeunstable");
                    if (unstable == 300 || unstable == 350 || unstable == 380) {
                        if (playerEntity.getHealth() < 1) {
                            stack.shrink(1);
                        }
                        playerEntity.attackEntityFrom(UNSTABLE_DIVISION, 1);
                    }
                    else if (unstable > 400 && ((unstable - 400) % 20) == 0) {
                        float subtractHealth = (float) Math.pow(2, Math.floor((unstable - 400) / 20.0));
                        if (playerEntity.getHealth() < subtractHealth) {
                            stack.shrink(1);
                        }
                        playerEntity.attackEntityFrom(UNSTABLE_DIVISION, subtractHealth);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void playertick(TickEvent.PlayerTickEvent e) {
        if (e.phase == TickEvent.Phase.START) return;
        Container container = e.player.openContainer;

        World world = e.player.world;

        if (world.isRemote) return;
        boolean explode = false;

        List<Slot> inventorySlots = container.inventorySlots;
        for (Slot slot : inventorySlots) {
            if (!(slot.getSlotIndex() == 0)) {
                ItemStack stack = slot.getStack();
                if ((stack.getItem() instanceof UnstableIngot)) {
                    stack.setDamage(stack.getDamage() - 1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemDrop(ItemTossEvent e) {
        PlayerEntity p = e.getPlayer();
        ItemEntity entityItem = e.getEntityItem();
        ItemStack stack = entityItem.getItem();
        if (stack.getItem() == ItemList.UnstableIngot.get().getItem() && Config.unstableIngotType.get() == 2) {
            p.world.createExplosion(null, p.getPosX(), p.getPosY(), p.getPosZ(), 1, Explosion.Mode.NONE);
            p.attackEntityFrom(DIVIDE_BY_DIAMOND, Float.MAX_VALUE);
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onContainerOpen(PlayerContainerEvent.Open e) {
        PlayerEntity playerEntity = e.getPlayer();
        Container c = e.getContainer();
        for (Slot slot : c.inventorySlots) {
            ItemStack stack = slot.getStack();
            int type = Config.unstableIngotType.get();
            if (type == 0) {
                playerEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100));
            }
            if (type != 0) {
                setDamage(stack, stack.getDamage() + 1);
            }
            if (stack.getDamage() == 200 && type != 0) {
                if (type == 1) {
                    playerEntity.sendStatusMessage((new TranslationTextComponent("text.miniutilities.ingotisunstable").mergeStyle(TextFormatting.RED)), true);
                    stack.setDisplayName(new TranslationTextComponent("text.miniutilities.unstableingot"));
                    CompoundNBT compoundNBT = stack.getOrCreateTag();
                    compoundNBT.putInt("timeunstable", 0);
                }
                else if (type == 2) {
                    stack.shrink(1);
                    e.getPlayer().world.createExplosion(null, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), 1, Explosion.Mode.NONE);
                    playerEntity.attackEntityFrom(DIVIDE_BY_DIAMOND, Float.MAX_VALUE);
                }
            }
            if (stack.getDamage() > 200 && type == 1) {
                CompoundNBT compoundNBT = stack.getOrCreateTag();
                compoundNBT.putInt("timeunstable", compoundNBT.getInt("timeunstable") + 1);
                int unstable = compoundNBT.getInt("timeunstable");
                if (unstable == 300 || unstable == 350 || unstable == 380) {
                    if (playerEntity.getHealth() < 1) {
                        stack.shrink(1);
                    }
                    playerEntity.attackEntityFrom(UNSTABLE_DIVISION, 1);
                }
                else if (unstable > 400 && ((unstable - 400) % 20) == 0) {
                    float subtractHealth = (float) Math.pow(2, Math.floor((unstable - 400) / 20.0));
                    if (playerEntity.getHealth() < subtractHealth) {
                        stack.shrink(1);
                    }
                    playerEntity.attackEntityFrom(UNSTABLE_DIVISION, subtractHealth);
                }
            }
        }
    }
}
