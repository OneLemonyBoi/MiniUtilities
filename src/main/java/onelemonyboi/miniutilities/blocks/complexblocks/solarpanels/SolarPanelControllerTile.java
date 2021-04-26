package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import onelemonyboi.lemonlib.blocks.EnergyTileBase;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.world.Config;

import java.util.ArrayList;
import java.util.List;

public class SolarPanelControllerTile extends EnergyTileBase {
    public static int activeSolarCount = 0;
    public static List<BlockPos> posList = new ArrayList<BlockPos>();

    public SolarPanelControllerTile() {
        super(TEList.SolarPanelControllerTile.get(), Config.solar_panel_generation.get() * 1024, 0, Config.solar_panel_generation.get() * 256);
    }

    @Override
    public void tick() {
        activeSolarCount = 0;
        posList = new ArrayList<BlockPos>();
        if (world.getDayTime() % 20 == 0) {
            solarPanelRecursion(this.getPos());
        }
        double power = Config.solar_panel_generation.get();
        power *= activeSolarCount;
        power *= activeSolarCount/50.0 + 1;
        energy.setMaxRecieve(Integer.MAX_VALUE);
        energy.internalProduceEnergy((int) power);
        energy.setMaxRecieve(0);
        energy.outputToSide(world, pos, Direction.UP, Config.solar_panel_generation.get() * 256);
    }

    public void solarPanelRecursion(BlockPos pos) {
        for (Direction d : Direction.values()) {
            if (d == Direction.UP || d == Direction.DOWN) {continue;}
            BlockState blockState = world.getBlockState(pos.offset(d));
            if (posList.contains(pos.offset(d))) {continue;}
            if (blockState.getBlock() instanceof SolarPanelBlock && world.isDaytime()) {
                posList.add(pos.offset(d));
                activeSolarCount++;
                solarPanelRecursion(pos.offset(d));
            }
            else if (blockState.getBlock() instanceof LunarPanelBlock && world.isNightTime()) {
                posList.add(pos.offset(d));
                activeSolarCount++;
                solarPanelRecursion(pos.offset(d));
            }
            else if (blockState.getBlock() instanceof SolarPanelController && !pos.offset(d).getCoordinatesAsString().equals(getPos().getCoordinatesAsString())) {
                world.destroyBlock(pos.offset(d), true);
                activeSolarCount = 0;
            }
        }
    }
}
