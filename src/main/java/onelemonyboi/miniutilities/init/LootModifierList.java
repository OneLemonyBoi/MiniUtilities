package onelemonyboi.miniutilities.init;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.items.enchantments.MoltenHeadLootModifier;

public class LootModifierList {
    public static final RegistryObject<GlobalLootModifierSerializer<MoltenHeadLootModifier>> MoltenHeadLootModifierSerializer = ModRegistry.LOOT_MODIFIERS.register("molten_head_loot_modifier", MoltenHeadLootModifier.Serializer::new);
    public static void register() {}
}