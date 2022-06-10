package onelemonyboi.miniutilities.entities;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.network.NetworkHooks;
import onelemonyboi.miniutilities.init.EntityList;
import onelemonyboi.miniutilities.init.ItemList;

public class MagicalEggEntity extends ThrowableItemProjectile {
    public MagicalEggEntity(EntityType<MagicalEggEntity> p_i50154_1_, Level p_i50154_2_) {
        super(p_i50154_1_, p_i50154_2_);
    }

    public MagicalEggEntity(Level worldIn, net.minecraft.world.entity.LivingEntity throwerIn) {
        super(EntityList.SpecialEgg.get(), throwerIn, worldIn);
    }

    public MagicalEggEntity(Level worldIn, double x, double y, double z) {
        super(EntityList.SpecialEgg.get(), x, y, z, worldIn);
    }

    /**
     * Handler for {@link Level#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double) this.random.nextFloat() - 0.5D) * 0.08D, ((double) this.random.nextFloat() - 0.5D) * 0.08D, ((double) this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!(result.getEntity() instanceof LivingEntity) || result.getEntity() instanceof Player) {
            return;
        }
        net.minecraft.world.entity.LivingEntity entity = (net.minecraft.world.entity.LivingEntity) result.getEntity();
        try {
            Containers.dropItemStack(this.getCommandSenderWorld(), this.getX(), this.getY(), this.getZ(), new ItemStack(ForgeSpawnEggItem.fromEntityType(entity.getType())));
            entity.remove(RemovalReason.DISCARDED);
        }
        catch (Exception e) {

        }
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove(RemovalReason.DISCARDED);
        }
    }

    protected Item getDefaultItem() {
        return ItemList.MagicalEgg.get();
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
