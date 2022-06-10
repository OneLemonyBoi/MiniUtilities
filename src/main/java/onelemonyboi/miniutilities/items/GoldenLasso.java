package onelemonyboi.miniutilities.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.List;

public class GoldenLasso extends Item {
    public GoldenLasso(Properties properties) {
        super(properties);
    }

    @Override
    public Component getHighlightTip(ItemStack item, Component displayName) {
        String text = displayName.getContents();
        text = text.equals("") ? new TranslatableComponent("item.miniutilities.golden_lasso").getContents() : text;
        return new TextComponent(text);
        // if (item.getTag() == null || !item.getTag().contains("EntityTag")) return displayName;
        // return new StringTextComponent(displayName.getUnformattedComponentText() + " - " + EntityType.byKey(item.getChildTag("EntityTag").getString("id")).get().getName());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (stack.getTag() != null && stack.getTag().contains("EntityTag")) {
            tooltip.add(new TextComponent("Contains: ").append(EntityType.byString(stack.getTagElement("EntityTag").getString("id")).get().getDescription()));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    public static void onRightClick(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getItemStack().getItem() instanceof GoldenLasso)) return;
        CompoundTag nbt = event.getTarget().serializeNBT();
        if (event.getItemStack().getTag() == null) event.getItemStack().setTag(new CompoundTag());
        event.getItemStack().getTag().put("EntityTag", nbt);
        event.getTarget().remove(Entity.RemovalReason.DISCARDED);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide && context.getItemInHand().getTag() != null && context.getItemInHand().getTag().contains("EntityTag")) {
            BlockPos newPos = context.getClickedPos().relative(context.getClickedFace());
            context.getItemInHand().getTagElement("EntityTag").remove("Pos");
            context.getItemInHand().getTagElement("EntityTag").put("Pos", newDoubleNBTList(newPos.getX(), newPos.getY(), newPos.getZ()));
            Entity entity = net.minecraft.world.entity.EntityType.byString(context.getItemInHand().getTagElement("EntityTag").getString("id")).get().spawn((ServerLevel) context.getLevel(), context.getItemInHand().getTag(), null, null, newPos, MobSpawnType.MOB_SUMMONED, false, false);
            entity.load(context.getItemInHand().getTagElement("EntityTag"));
            context.getItemInHand().setTag(new CompoundTag());
            return InteractionResult.CONSUME;
        }
        return super.useOn(context);
    }

    protected ListTag newDoubleNBTList(double... numbers) {
        ListTag listnbt = new ListTag();
        for (double d0 : numbers) {
            listnbt.add(DoubleTag.valueOf(d0));
        }
        return listnbt;
    }

}
