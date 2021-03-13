package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import java.util.Set;

public class UnstablePickaxe extends PickaxeItem {
    public UnstablePickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    protected static final Set<ToolType> tooltypes = Sets.newHashSet(ToolType.PICKAXE);

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return material == Material.ROCK ? this.efficiency * 5 : this.efficiency;
    }

    @Nonnull
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return tooltypes;
    }
}
