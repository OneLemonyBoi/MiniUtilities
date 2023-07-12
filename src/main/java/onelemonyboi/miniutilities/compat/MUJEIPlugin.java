package onelemonyboi.miniutilities.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.ModRegistry;

@JeiPlugin
public class MUJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MiniUtilities.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IModPlugin.super.registerRecipes(registration);
        for (RegistryObject<Item> item : ModRegistry.ITEMS.getEntries()) {
            ItemStack st = new ItemStack(item.get());
            if (!st.isEmpty()) {
                registration.addIngredientInfo(st, VanillaTypes.ITEM_STACK, Component.translatable(item.get().getDescriptionId() + ".desc"));
            }
        }
    }
}
