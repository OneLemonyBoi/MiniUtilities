package onelemonyboi.miniutilities.init;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.entities.MagicalEggEntity;

public class EntityList {
    public static final RegistryObject<EntityType<MagicalEggEntity>> SpecialEgg = ModRegistry.ENTITIES.register("magical_egg", () -> EntityType.Builder.<MagicalEggEntity>of(MagicalEggEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("magical_egg"));

    public static void register() {}
}
