package onelemonyboi.miniutilities.init;

import com.mojang.serialization.Codec;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.items.enchantments.MoltenHeadLootModifier;

public class LootModifierList {
    public static final RegistryObject<Codec<MoltenHeadLootModifier>> MoltenHeadLootModifierSerializer = ModRegistry.LOOT_MODIFIERS.register("molten_head_loot_modifier", MoltenHeadLootModifier.CODEC);
    public static void register() {}
}