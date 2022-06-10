package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.items.ItemHandlerHelper;
import onelemonyboi.miniutilities.init.EnchantmentList;

import java.util.List;
import java.util.Random;

public class MoltenHeadHandler {
    public static void handleBlockBreak(BreakEvent event) {
        if (!event.getWorld().isClientSide() && net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(EnchantmentList.MoltenHead.get(), event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND)) > 0) { // Checks if running on server and enchant is on tool
            ServerLevel serverWorld = ((ServerLevel) event.getWorld()); // Casts IWorld to ServerWorld
            net.minecraft.world.item.ItemStack pick = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            int fortuneAmount = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pick);
            int silkTouchAmount = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, pick);

            LootContext.Builder lootcontext$builder = new LootContext.Builder(serverWorld)
                    .withRandom(serverWorld.random)
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getPos()))
                    .withParameter(LootContextParams.TOOL, pick); // Makes lootcontext to calculate drops
            List<net.minecraft.world.item.ItemStack> drops = event.getState().getDrops(lootcontext$builder); // Calculates drops

            for (ItemStack item : drops) { // Iteration
                net.minecraft.world.item.ItemStack stack = serverWorld.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(item), serverWorld)
                        .map(SmeltingRecipe::getResultItem)
                        .filter(itemStack -> !itemStack.isEmpty())
                        .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, item.getCount() * itemStack.getCount()))
                        .orElse(item); // Recipe as var

                if (fortuneAmount >= 1 && silkTouchAmount <= 0 && !stack.sameItem(item)) {
                    int addedCount = new Random().nextInt(fortuneAmount + 2) - 1;
                    if (addedCount < 0) {
                        addedCount = 0;
                    }
                    addedCount++;
                    stack.setCount(stack.getCount() * addedCount);
                }

                Containers.dropItemStack(event.getPlayer().level, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack); // Spawns Itemstack
            }
            event.getPlayer().level.destroyBlock(event.getPos(), false); // Breaks block
            event.setResult(Event.Result.DENY);
        }
    }
}