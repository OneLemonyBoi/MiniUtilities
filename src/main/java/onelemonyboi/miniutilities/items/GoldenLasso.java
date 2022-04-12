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
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.List;

public class GoldenLasso extends Item {
    public GoldenLasso(Properties properties) {
        super(properties);
    }

    @Override
    public ITextComponent getHighlightTip(ItemStack item, ITextComponent displayName) {
        String text = displayName.getUnformattedComponentText();
        text = text.equals("") ? new TranslationTextComponent("item.miniutilities.golden_lasso").getUnformattedComponentText() : text;
        return new StringTextComponent(text);
        //if (item.getTag() == null || !item.getTag().contains("EntityTag")) return displayName;
//        return new StringTextComponent(displayName.getUnformattedComponentText() + " - " + EntityType.byKey(item.getChildTag("EntityTag").getString("id")).get().getName());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.getTag() != null && stack.getTag().contains("EntityTag")) {
            tooltip.add(new StringTextComponent("Contains: ").appendSibling(EntityType.byKey(stack.getChildTag("EntityTag").getString("id")).get().getName()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public static void onRightClick(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getItemStack().getItem() instanceof GoldenLasso)) return;
        CompoundNBT nbt = event.getTarget().serializeNBT();
        if (event.getItemStack().getTag() == null) event.getItemStack().setTag(new CompoundNBT());
        event.getItemStack().getTag().put("EntityTag", nbt);
        event.getTarget().remove();
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote && context.getItem().getTag() != null && context.getItem().getTag().contains("EntityTag")) {
            BlockPos newPos = context.getPos().offset(context.getFace());
            context.getItem().getChildTag("EntityTag").remove("Pos");
            context.getItem().getChildTag("EntityTag").put("Pos", newDoubleNBTList(newPos.getX(), newPos.getY(), newPos.getZ()));
            Entity entity = EntityType.byKey(context.getItem().getChildTag("EntityTag").getString("id")).get().spawn((ServerWorld) context.getWorld(), context.getItem().getTag(), null, null, newPos, SpawnReason.MOB_SUMMONED, false, false);
            entity.read(context.getItem().getChildTag("EntityTag"));
            context.getItem().setTag(new CompoundNBT());
            return ActionResultType.CONSUME;
        }
        return super.onItemUse(context);
    }

    protected ListNBT newDoubleNBTList(double... numbers) {
        ListNBT listnbt = new ListNBT();
        for (double d0 : numbers) {
            listnbt.add(DoubleNBT.valueOf(d0));
        }
        return listnbt;
    }

}
