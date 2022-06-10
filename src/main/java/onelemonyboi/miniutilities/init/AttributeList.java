package onelemonyboi.miniutilities.init;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;

public class AttributeList {
    public static final net.minecraftforge.registries.RegistryObject<Attribute> DivineDamage = ModRegistry.ATTRIBUTES.register("divine_damage", () -> new RangedAttribute("attribute.miniutilities.divinedamage", 0, 0, Integer.MAX_VALUE));
    public static final net.minecraftforge.registries.RegistryObject<Attribute> ArmorPiercingDamage = ModRegistry.ATTRIBUTES.register("armor_piercing_damage", () -> new RangedAttribute("attribute.miniutilities.armorpiercingdamage", 0, 0, Integer.MAX_VALUE));
    public static final RegistryObject<Attribute> SoulDamage = ModRegistry.ATTRIBUTES.register("soul_damage", () -> new RangedAttribute("attribute.miniutilities.souldamage", 0, 0, Integer.MAX_VALUE));

    public static void register() {}
}
