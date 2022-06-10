package onelemonyboi.miniutilities.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import onelemonyboi.miniutilities.entities.MagicalEggEntity;

import net.minecraft.world.item.Item.Properties;

import java.util.Random;

public class MagicalEgg extends net.minecraft.world.item.Item {
    public MagicalEgg(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        net.minecraft.world.item.ItemStack itemstack = playerIn.getItemInHand(handIn);
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isClientSide) {
            MagicalEggEntity eggentity = new MagicalEggEntity(worldIn, playerIn);
            eggentity.shootFromRotation(playerIn, playerIn.xRotO, playerIn.yRotO, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(eggentity);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        if (!playerIn.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}
