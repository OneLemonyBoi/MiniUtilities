package onelemonyboi.miniutilities.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.CreativeTab;
import onelemonyboi.miniutilities.init.ItemList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AngelRing extends Item {
    public AngelRing() {
        super(new Item.Properties().maxStackSize(1).group(CreativeTab.getInstance()));
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundNBT unused) {
        ICurio curio = new ICurio() {
            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return true;
            }

            @Override
            public void onEquip(SlotContext slotContext, ItemStack prevStack) {
                if (slotContext.getWearer() instanceof PlayerEntity) {
                    startFlying((PlayerEntity) slotContext.getWearer());
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack prevStack) {
                if (slotContext.getWearer() instanceof PlayerEntity) {
                    stopFlying((PlayerEntity) slotContext.getWearer());
                }
            }

            private void startFlying(PlayerEntity player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.abilities.allowFlying = true;
                    player.sendPlayerAbilities();
                }
            }

            private void stopFlying(PlayerEntity player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.abilities.isFlying = false;
                    player.abilities.allowFlying = false;
                    player.sendPlayerAbilities();
                }
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    PlayerEntity player = ((PlayerEntity) livingEntity);
                    if (!player.abilities.allowFlying) {
                        startFlying(player);
                    }
                }
            }

            @Override
            public boolean canEquip(String identifier, LivingEntity entityLivingBase) {
                return !CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.AngelRing.get(), entityLivingBase)
                        .isPresent();
            }
        };

        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };
    }
}