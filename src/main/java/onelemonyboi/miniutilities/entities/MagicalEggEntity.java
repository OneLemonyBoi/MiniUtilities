package onelemonyboi.miniutilities.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import onelemonyboi.miniutilities.init.EntityList;
import onelemonyboi.miniutilities.init.ItemList;

public class MagicalEggEntity extends ProjectileItemEntity {
    public MagicalEggEntity(EntityType<MagicalEggEntity> p_i50154_1_, World p_i50154_2_) {
        super(p_i50154_1_, p_i50154_2_);
    }

    public MagicalEggEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityList.SpecialEgg.get(), throwerIn, worldIn);
    }

    public MagicalEggEntity(World worldIn, double x, double y, double z) {
        super(EntityList.SpecialEgg.get(), x, y, z, worldIn);
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItem()), this.getPosX(), this.getPosY(), this.getPosZ(), ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }

    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        if (!(result.getEntity() instanceof LivingEntity) || result.getEntity() instanceof PlayerEntity) {
            return;
        }
        LivingEntity entity = (LivingEntity) result.getEntity();
        try {
            InventoryHelper.spawnItemStack(this.getEntityWorld(), entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(SpawnEggItem.getEgg(entity.getType())));
            entity.remove();
        }
        catch (Exception e) {

        }
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    protected Item getDefaultItem() {
        return ItemList.MagicalEgg.get();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
