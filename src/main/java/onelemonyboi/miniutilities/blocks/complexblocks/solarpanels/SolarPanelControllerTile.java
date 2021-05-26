package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import onelemonyboi.lemonlib.blocks.EnergyTileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.world.Config;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SolarPanelControllerTile extends EnergyTileBase implements RenderInfoIdentifier {
    public static int activeSolarCount = 0;
    public static List<BlockPos> posList = new ArrayList<BlockPos>();
    public static double power = 0;

    public SolarPanelControllerTile() {
        super(TEList.SolarPanelControllerTile.get(), 65536, 0, 65536);
    }

    @Override
    public void tick() {
        if (world.isRemote()) {return;}

        posList = new ArrayList<BlockPos>();
        if (world.getDayTime() % 20 == 0) {
            activeSolarCount = 0;
            solarPanelRecursion(this.getPos());
        }
        power = Config.solarPanelGeneration.get();
        if (world.isNightTime()) {
            power = Config.lunarPanelGeneration.get();
        }
        power *= activeSolarCount;
        power *= activeSolarCount / (float) Config.panelMultiplier.get() + 1;
        energy.machineProduce((int) power);
        energy.outputToSide(world, pos, Direction.UP, Config.solarPanelGeneration.get() * 4096);
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    public void solarPanelRecursion(BlockPos pos) {
        for (Direction d : Direction.values()) {
            if (d == Direction.UP || d == Direction.DOWN) {continue;}
            BlockState blockState = world.getBlockState(pos.offset(d));
            if (posList.contains(pos.offset(d)) || !world.canSeeSky(pos.offset(d)) || !world.isAreaLoaded(pos.offset(d), 1)) {continue;}
            if (blockState.getBlock() instanceof SolarPanelBlock) {
                if (world.isDaytime()) {
                    activeSolarCount++;
                }
                posList.add(pos.offset(d));
                solarPanelRecursion(pos.offset(d));
            }
            else if (blockState.getBlock() instanceof LunarPanelBlock) {
                if (world.isNightTime()) {
                    activeSolarCount++;
                }
                posList.add(pos.offset(d));
                solarPanelRecursion(pos.offset(d));
            }
            else if (blockState.getBlock() instanceof SolarPanelController && pos.offset(d) != getPos()) {
                world.destroyBlock(pos.offset(d), true);
                activeSolarCount = 0;
            }
        }
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        if (energy.getEnergyStored() == (int) power){
            output.add(new StringTextComponent("Power: 0/" + energy.getMaxEnergyStored()));
        }
        else {
            output.add(new StringTextComponent("Power: " + energy.toString()));
        }
        output.add(new StringTextComponent("Active Panels: " + activeSolarCount));
        output.add(new StringTextComponent("FE/t Production: " + Math.round(power)));
        return output;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), 514, this.write(new CompoundNBT()));
    }
}
