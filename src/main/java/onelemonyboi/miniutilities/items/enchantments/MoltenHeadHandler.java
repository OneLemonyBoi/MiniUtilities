package onelemonyboi.miniutilities.items.enchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.items.ItemHandlerHelper;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.EnchantmentList;

import java.util.List;
import java.util.Random;

public class MoltenHeadHandler {
    public static void handleBlockBreak(BreakEvent event) {
        if (!event.getWorld().isClientSide() && EnchantmentHelper.getItemEnchantmentLevel(EnchantmentList.MoltenHead.get(), event.getPlayer().getItemInHand(Hand.MAIN_HAND)) > 0) { // Checks if running on server and enchant is on tool
            ServerWorld serverWorld = ((ServerWorld) event.getWorld()); // Casts IWorld to ServerWorld
            ItemStack pick = event.getPlayer().getItemInHand(Hand.MAIN_HAND);
            int fortuneAmount = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pick);
            int silkTouchAmount = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, pick);

            LootContext.Builder lootcontext$builder = (new LootContext.Builder(serverWorld)
                    .withRandom(serverWorld.random)
                    .withParameter(LootParameters.ORIGIN, Vector3d.atCenterOf(event.getPos()))
                    .withParameter(LootParameters.TOOL, pick)); // Makes lootcontext to calculate drops
            List<ItemStack> drops = event.getState().getDrops(lootcontext$builder); // Calculates drops

            for (ItemStack item : drops) { // Iteration
                ItemStack stack = serverWorld.getRecipeManager().getRecipeFor(IRecipeType.SMELTING, new Inventory(item), serverWorld)
                        .map(FurnaceRecipe::getResultItem)
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

                InventoryHelper.dropItemStack(event.getPlayer().level, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack); // Spawns Itemstack
            }
            event.getPlayer().level.destroyBlock(event.getPos(), false); // Breaks block
            event.setResult(Event.Result.DENY);
        }
    }
}