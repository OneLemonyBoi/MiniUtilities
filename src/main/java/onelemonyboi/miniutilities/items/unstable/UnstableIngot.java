package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.startup.Config;

import net.minecraft.item.Item.Properties;

@Mod.EventBusSubscriber
public class UnstableIngot extends Item {

    public static final DamageSource DIVIDE_BY_DIAMOND = (new DamageSource("divide_by_diamond").bypassArmor().bypassInvul());
    public static final DamageSource UNSTABLE_DIVISION = (new DamageSource("unstable_division").bypassArmor().bypassInvul());

    public UnstableIngot(Properties properties) {
        super(properties);
    }

    public static void attackPlayer(PlayerEntity playerEntity, ItemStack stack, float damage) {
        if (playerEntity.getHealth() < damage) {
            stack.shrink(1);
        }
        playerEntity.hurt(UNSTABLE_DIVISION, damage);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (world.isClientSide || !(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity) entity;
        switch (Config.unstableIngotType.get()) {
            case NO_DAMAGE:
                playerEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100));
                break;
            case DAMAGE:
                // TODO: LOW PRIORITY Dont damage after hits 200
                setDamage(stack, stack.getDamageValue() + 1);
                CompoundNBT compoundNBT = stack.getOrCreateTag();
                if (stack.getDamageValue() == 200) {
                    playerEntity.displayClientMessage((new TranslationTextComponent("text.miniutilities.ingotisunstable").withStyle(TextFormatting.RED)), true);
                    stack.setHoverName(new TranslationTextComponent("text.miniutilities.unstableingot"));
                    compoundNBT.putInt("timeunstable", 0);
                }
                else if (stack.getDamageValue() > 200) {
                    int unstable = compoundNBT.getInt("timeunstable") + 1;
                    compoundNBT.putInt("timeunstable", unstable);
                    if (unstable < 400 && unstable % 40 == 20) {
                        attackPlayer(playerEntity, stack, 1);
                    }
                    else if (unstable >= 400 && (unstable % 20) == 0) {
                        attackPlayer(playerEntity, stack, (float) Math.pow(2, (unstable - 400.0) / 40));
                    }
                }
                break;
            case EXPLOSION:
                setDamage(stack, stack.getDamageValue() + 1);
                if (stack.getDamageValue() == 200) {
                    stack.shrink(1);
                    world.explode(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 1, Explosion.Mode.NONE);
                    playerEntity.hurt(DIVIDE_BY_DIAMOND, Float.MAX_VALUE);
                }
                break;
        }
    }

    @SubscribeEvent
    public static void onItemDrop(ItemTossEvent e) {
        PlayerEntity p = e.getPlayer();
        ItemEntity entityItem = e.getEntityItem();
        ItemStack stack = entityItem.getItem();
        if (stack.getItem() == ItemList.UnstableIngot.get().getItem() && Config.unstableIngotType.get() != ReactionType.NO_DAMAGE) {
            p.level.explode(null, p.getX(), p.getY(), p.getZ(), 1, Explosion.Mode.NONE);
            p.hurt(DIVIDE_BY_DIAMOND, Float.MAX_VALUE);
            e.setCanceled(true);
        }
    }

    public enum ReactionType {
        NO_DAMAGE,DAMAGE,EXPLOSION
    }
}
