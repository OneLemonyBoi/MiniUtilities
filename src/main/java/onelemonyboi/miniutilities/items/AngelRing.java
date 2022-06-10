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
                if (slotContext.getWearer() instanceof Player) {
                    startFlying((Player) slotContext.getWearer());
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, net.minecraft.world.item.ItemStack prevStack) {
                if (slotContext.getWearer() instanceof Player) {
                    stopFlying((Player) slotContext.getWearer());
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
            public void curioTick(String identifier, int index, net.minecraft.world.entity.LivingEntity livingEntity) {
                if (livingEntity instanceof Player) {
                    Player player = ((Player) livingEntity);
                    if (!player.getAbilities().mayfly) {
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