package onelemonyboi.miniutilities.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class GoldenLasso extends Item {
    public GoldenLasso(Properties properties) {
        super(properties);
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
        if (context.getWorld() instanceof ServerWorld && context.getItem().getTag() != null && context.getItem().getTag().contains("EntityTag")) {
            Entity entity = EntityType.byKey(context.getItem().getChildTag("EntityTag").getString("id")).get().spawn((ServerWorld) context.getWorld(), context.getItem().getTag(), null, null, context.getPos().offset(context.getFace()), SpawnReason.MOB_SUMMONED, false, false);
            context.getItem().setTag(new CompoundNBT());
            entity.setPosition(context.getPos().offset(context.getFace()).getX(), context.getPos().offset(context.getFace()).getY(), context.getPos().offset(context.getFace()).getZ());
            return ActionResultType.CONSUME;
        }
        return super.onItemUse(context);
    }
}
