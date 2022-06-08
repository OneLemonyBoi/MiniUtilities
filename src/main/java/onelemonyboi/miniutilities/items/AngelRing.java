package onelemonyboi.miniutilities.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
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
        super(new Item.Properties().stacksTo(1).tab(CreativeTab.getInstance()));
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
                    player.abilities.mayfly = true;
                    player.onUpdateAbilities();
                }
            }

            private void stopFlying(PlayerEntity player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.abilities.flying = false;
                    player.abilities.mayfly = false;
                    player.onUpdateAbilities();
                }
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    PlayerEntity player = ((PlayerEntity) livingEntity);
                    if (!player.abilities.mayfly) {
                        startFlying(player);
                    }
                }
            }

            @Override
            public boolean canEquip(String identifier, LivingEntity entityLivingBase) {
                return !CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.BaseAngelRing.get(), entityLivingBase).isPresent()
                        && !CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.BatAngelRing.get(), entityLivingBase).isPresent()
                        && !CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.GoldAngelRing.get(), entityLivingBase).isPresent()
                        && !CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.PeacockAngelRing.get(), entityLivingBase).isPresent()
                        && !CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.EnderDragonAngelRing.get(), entityLivingBase).isPresent()
                        && !CuriosApi.getCuriosHelper().findEquippedCurio(ItemList.FeatherAngelRing.get(), entityLivingBase).isPresent();
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