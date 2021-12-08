package onelemonyboi.miniutilities.trait;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.util.LazyOptional;
import onelemonyboi.lemonlib.handlers.MUItemStackHandler;
import onelemonyboi.lemonlib.trait.tile.TileBehaviour;
import onelemonyboi.lemonlib.trait.tile.TileBehaviours;
import onelemonyboi.lemonlib.trait.tile.TilePartialBehaviours;

import javax.annotation.Nonnull;

public class TileBehaviors {
    public static TileBehaviour laserPort = new TileBehaviour.Builder()
            .composeFrom(TilePartialBehaviours.partialEnergyTile)
            .powerTrait(1048576, 1048576, 1048576)
            .build();

    public static TileBehaviour laserHub = new TileBehaviour.Builder()
            .composeFrom(TilePartialBehaviours.partialEnergyTile)
            .powerTrait(1048576, 1048576, 1048576)
            .build();

    public static TileBehaviour mechanicalMiner = new TileBehaviour.Builder()
            .composeFrom(TilePartialBehaviours.partialBaseTile)
            .itemTrait(new MUItemStackHandler(10) {
                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    return (slot == 9) == (stack.getItem() instanceof PickaxeItem);
                }
            })
            .build();

    public static TileBehaviour mechanicalPlacer = new TileBehaviour.Builder()
            .composeFrom(TilePartialBehaviours.partialBaseTile)
            .itemTrait(10)
            .build();

    public static TileBehaviour quantumQuarry = new TileBehaviour.Builder()
            .composeFrom(TilePartialBehaviours.partialEnergyTile)
            .powerTrait(10000000, 10000000, 0)
            .build();

    public static TileBehaviour solarPanelController = new TileBehaviour.Builder()
            .composeFrom(TilePartialBehaviours.partialEnergyTile)
            .powerTrait(65536, 0, 65536)
            .build();
}
