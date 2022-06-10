package onelemonyboi.miniutilities.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import onelemonyboi.miniutilities.startup.Config;

import net.minecraft.world.item.Item.Properties;

public class ExperiencePearl extends Item {
    int compressed = 0;
    public ExperiencePearl(Properties properties, int compressed) {
        super(properties);
        this.compressed = compressed;
    }

    public ExperiencePearl(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        playerIn.giveExperiencePoints(Config.expGivenFromPearl.get() * (int) (Math.pow(8, this.compressed)));
        playerIn.getItemInHand(handIn).shrink(1);
        return InteractionResultHolder.consume(playerIn.getItemInHand(handIn));
    }
}
