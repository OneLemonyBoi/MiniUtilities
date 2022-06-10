package onelemonyboi.miniutilities.items.unstable;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.init.ItemList;
import onelemonyboi.miniutilities.startup.Config;

@Mod.EventBusSubscriber
public class UnstableIngot extends Item {

    public static final DamageSource DIVIDE_BY_DIAMOND = (new DamageSource("divide_by_diamond").bypassArmor().bypassInvul());
    public static final DamageSource UNSTABLE_DIVISION = (new DamageSource("unstable_division").bypassArmor().bypassInvul());

    public UnstableIngot(Properties properties) {
        super(properties);
    }

    public static void attackPlayer(Player playerEntity, net.minecraft.world.item.ItemStack stack, float damage) {
        if (playerEntity.getHealth() < damage) {
            stack.shrink(1);
        }
        playerEntity.hurt(UNSTABLE_DIVISION, damage);
    }

    @Override
    public boolean shouldCauseReequipAnimation(net.minecraft.world.item.ItemStack oldStack, net.minecraft.world.item.ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        if (world.isClientSide || !(entity instanceof Player)) {
            return;
        }
        Player playerEntity = (Player) entity;
        switch (Config.unstableIngotType.get()) {
            case NO_DAMAGE:
                playerEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100));
                break;
            case DAMAGE:
                // TODO: LOW PRIORITY Dont damage after hits 200
                setDamage(stack, stack.getDamageValue() + 1);
                CompoundTag compoundNBT = stack.getOrCreateTag();
                if (stack.getDamageValue() == 200) {
                    playerEntity.displayClientMessage((new TranslatableComponent("text.miniutilities.ingotisunstable").withStyle(ChatFormatting.RED)), true);
                    stack.setHoverName(new TranslatableComponent("text.miniutilities.unstableingot"));
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
                    world.explode(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 1, Explosion.BlockInteraction.NONE);
                    playerEntity.hurt(DIVIDE_BY_DIAMOND, Float.MAX_VALUE);
                }
                break;
        }
    }

    @SubscribeEvent
    public static void onItemDrop(ItemTossEvent e) {
        Player p = e.getPlayer();
        ItemEntity entityItem = e.getEntityItem();
        net.minecraft.world.item.ItemStack stack = entityItem.getItem();
        if (stack.getItem() == ItemList.UnstableIngot.get().asItem() && Config.unstableIngotType.get() != ReactionType.NO_DAMAGE) {
            p.level.explode(null, p.getX(), p.getY(), p.getZ(), 1, Explosion.BlockInteraction.NONE);
            p.hurt(DIVIDE_BY_DIAMOND, Float.MAX_VALUE);
            e.setCanceled(true);
        }
    }

    public enum ReactionType {
        NO_DAMAGE,DAMAGE,EXPLOSION
    }
}
