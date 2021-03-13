package onelemonyboi.miniutilities.items.unstable;

import com.google.common.collect.Sets;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.UUID;

public class UnstableAxe extends AxeItem {

    public UnstableAxe(IItemTier materialIn, float damage, float attackSpeed, Properties properties) {
        super(materialIn, damage, attackSpeed, properties);
    }

    private static final Set<ToolType> axe = Sets.newHashSet(ToolType.AXE);

    @Nonnull
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return axe;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
        if (!isSelected || !(entity instanceof PlayerEntity) || worldIn.isRemote) return;
        ((PlayerEntity) entity).getFoodStats().addStats(1, 0.2F);  }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity instanceof LivingEntity) {
            if (((LivingEntity) entity).getCreatureAttribute() == CreatureAttribute.UNDEAD)
                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
            else ((LivingEntity) entity).heal(8);
            player.attackEntityFrom(DamageSource.MAGIC, 2);
        }
        return true;
    }
}
