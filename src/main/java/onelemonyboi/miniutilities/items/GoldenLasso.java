package onelemonyboi.miniutilities.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.Item.Properties;

public class GoldenLasso extends Item {
    public GoldenLasso(Properties properties) {
        super(properties);
    }

    @Override
    public ITextComponent getHighlightTip(ItemStack item, ITextComponent displayName) {
        String text = displayName.getContents();
        text = text.equals("") ? new TranslationTextComponent("item.miniutilities.golden_lasso").getContents() : text;
        return new StringTextComponent(text);
        //if (item.getTag() == null || !item.getTag().contains("EntityTag")) return displayName;
//        return new StringTextComponent(displayName.getUnformattedComponentText() + " - " + EntityType.byKey(item.getChildTag("EntityTag").getString("id")).get().getName());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.getTag() != null && stack.getTag().contains("EntityTag")) {
            tooltip.add(new StringTextComponent("Contains: ").append(EntityType.byString(stack.getTagElement("EntityTag").getString("id")).get().getDescription()));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    public static void onRightClick(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getItemStack().getItem() instanceof GoldenLasso)) return;
        CompoundNBT nbt = event.getTarget().serializeNBT();
        if (event.getItemStack().getTag() == null) event.getItemStack().setTag(new CompoundNBT());
        event.getItemStack().getTag().put("EntityTag", nbt);
        event.getTarget().remove();
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if (!context.getLevel().isClientSide && context.getItemInHand().getTag() != null && context.getItemInHand().getTag().contains("EntityTag")) {
            BlockPos newPos = context.getClickedPos().relative(context.getClickedFace());
            context.getItemInHand().getTagElement("EntityTag").remove("Pos");
            context.getItemInHand().getTagElement("EntityTag").put("Pos", newDoubleNBTList(newPos.getX(), newPos.getY(), newPos.getZ()));
            Entity entity = EntityType.byString(context.getItemInHand().getTagElement("EntityTag").getString("id")).get().spawn((ServerWorld) context.getLevel(), context.getItemInHand().getTag(), null, null, newPos, SpawnReason.MOB_SUMMONED, false, false);
            entity.load(context.getItemInHand().getTagElement("EntityTag"));
            context.getItemInHand().setTag(new CompoundNBT());
            return ActionResultType.CONSUME;
        }
        return super.useOn(context);
    }

    protected ListNBT newDoubleNBTList(double... numbers) {
        ListNBT listnbt = new ListNBT();
        for (double d0 : numbers) {
            listnbt.add(DoubleNBT.valueOf(d0));
        }
        return listnbt;
    }

}
