package onelemonyboi.miniutilities.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.miniutilities.ModRegistry;
import onelemonyboi.miniutilities.testlmaoidkwhytfthisishere.TestTE;
import onelemonyboi.miniutilities.tileentities.DrumTile;

public class TEList {
    public static final RegistryObject<TileEntityType<DrumTile>> DrumTile = ModRegistry.TE.register("drum", () -> TileEntityType.Builder.create(DrumTile::new, BlockList.StoneDrum.get(), BlockList.IronDrum.get(), BlockList.ReinforcedLargeDrum.get(), BlockList.NetheriteReinforcedDrum.get(), BlockList.UnstableDrum.get()).build(null));
    public static final RegistryObject<TileEntityType<TestTE>> TestTEE = ModRegistry.TE.register("display_case", () -> TileEntityType.Builder.create(TestTE::new, BlockList.TestBlocks.get()).build(null));

    public static void register() {}
}
