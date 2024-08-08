package onelemonyboi.miniutilities.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import onelemonyboi.miniutilities.init.ItemList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AngelRing extends Item {
    public AngelRing() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundTag unused) {
        ICurio curio = new ICurio() {
            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return true;
            }

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public void onEquip(SlotContext slotContext, net.minecraft.world.item.ItemStack prevStack) {
                if (slotContext.entity() instanceof Player) {
                    startFlying((Player) slotContext.entity());
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, net.minecraft.world.item.ItemStack newStack) {
                if (slotContext.entity() instanceof Player) {
                    stopFlying((Player) slotContext.entity());
                }
            }

            private void startFlying(Player player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.getAbilities().mayfly = true;
                    player.onUpdateAbilities();
                }
            }

            private void stopFlying(Player player) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.getAbilities().flying = false;
                    player.getAbilities().mayfly = false;
                    player.onUpdateAbilities();
                }
            }

            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player) {
                    Player player = ((Player) slotContext.entity());
                    if (!player.getAbilities().mayfly) {
                        startFlying(player);
                    }
                }
            }

            @Override
            public boolean canEquip(SlotContext slotContext) {
                LivingEntity player = slotContext.entity();

                return CuriosApi.getCuriosHelper().findFirstCurio(player, ItemList.BaseAngelRing.get()).isEmpty()
                        && CuriosApi.getCuriosHelper().findFirstCurio(player, ItemList.BatAngelRing.get()).isEmpty()
                        && CuriosApi.getCuriosHelper().findFirstCurio(player, ItemList.GoldAngelRing.get()).isEmpty()
                        && CuriosApi.getCuriosHelper().findFirstCurio(player, ItemList.PeacockAngelRing.get()).isEmpty()
                        && CuriosApi.getCuriosHelper().findFirstCurio(player, ItemList.EnderDragonAngelRing.get()).isEmpty()
                        && CuriosApi.getCuriosHelper().findFirstCurio(player, ItemList.FeatherAngelRing.get()).isEmpty();
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