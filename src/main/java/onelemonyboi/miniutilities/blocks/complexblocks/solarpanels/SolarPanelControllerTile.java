package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.startup.Config;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.ArrayList;
import java.util.List;

public class SolarPanelControllerTile extends TileBase implements RenderInfoIdentifier {
    @SaveInNBT(key = "panelsActive")
    public int activeSolarCount = 0;
    public List<net.minecraft.core.BlockPos> posList = new ArrayList<>();
    @SaveInNBT(key = "power")
    public double power = 0;

    public SolarPanelControllerTile(BlockPos pos, BlockState state) {
        super(TEList.SolarPanelControllerTile.get(), pos, state, TileBehaviors.solarPanelController);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SolarPanelControllerTile tile) {
        tile.solarPanelRecursion();
        tile.power = level.isDay() ? Config.solarPanelGeneration.get() : Config.lunarPanelGeneration.get();
        tile.power *= tile.activeSolarCount;
        tile.power *= tile.activeSolarCount / (float) Config.panelMultiplier.get() + 1;
        tile.getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().machineProduce((int) tile.power);
        tile.getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().outputToSide(level, tile.worldPosition, Direction.UP, Config.solarPanelGeneration.get() * 4096);
        level.sendBlockUpdated(pos, state, state, 2);
    }

    public void solarPanelRecursion() {
        posList = new ArrayList<>();
        activeSolarCount = 0;
        solarPanelRecursion(this.getBlockPos());
    }

    public void solarPanelRecursion(BlockPos pos) {
        for (net.minecraft.core.Direction d : net.minecraft.core.Direction.Plane.HORIZONTAL) {
            BlockState blockState = level.getBlockState(pos.relative(d));
            if (posList.contains(pos.relative(d)) || !level.canSeeSky(pos.relative(d)) || !level.isAreaLoaded(pos.relative(d), 1)) {
                continue;
            }
            if (blockState.getBlock() instanceof SolarPanelBlock) {
                if (level.isDay()) {
                    activeSolarCount++;
                }
                posList.add(pos.relative(d));
                solarPanelRecursion(pos.relative(d));
            } else if (blockState.getBlock() instanceof LunarPanelBlock) {
                if (level.isNight()) {
                    activeSolarCount++;
                }
                posList.add(pos.relative(d));
                solarPanelRecursion(pos.relative(d));
            }
        }
    }

    @Override
    public List<MutableComponent> getInfo() {
        List<MutableComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new TextComponent(""));
        output.add(new TextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        output.add(new TextComponent("Active Panels: " + activeSolarCount));
        output.add(new TextComponent("FE/t Production: " + Math.round(power)));
        return output;
    }
}
