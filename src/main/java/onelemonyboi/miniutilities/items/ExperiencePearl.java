package onelemonyboi.miniutilities.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import onelemonyboi.miniutilities.world.Config;

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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.giveExperiencePoints(Config.exp_given_from_pearl.get() * (int) (Math.pow(8, this.compressed)));
        return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
    }
}
