package onelemonyboi.miniutilities.blocks.complexblocks.solarpanels;

import lombok.SneakyThrows;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.startup.Config;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolarPanelControllerTile extends TileBase implements RenderInfoIdentifier, ITickableTileEntity {
    @SaveInNBT(key = "panelsActive")
    public int activeSolarCount = 0;
    public List<BlockPos> posList = new ArrayList<>();
    @SaveInNBT(key = "power")
    public double power = 0;

    public SolarPanelControllerTile() {
        super(TEList.SolarPanelControllerTile.get(), TileBehaviors.solarPanelController);
    }

    @Override
    public void tick() {
        if (level.isClientSide()) {return;}

        solarPanelRecursion();
        power = level.isDay() ? Config.solarPanelGeneration.get() : Config.lunarPanelGeneration.get();
        power *= activeSolarCount;
        power *= activeSolarCount / (float) Config.panelMultiplier.get() + 1;
        getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().machineProduce((int) power);
        getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().outputToSide(level, worldPosition, Direction.UP, Config.solarPanelGeneration.get() * 4096);
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    public void solarPanelRecursion() {
        posList = new ArrayList<>();
        activeSolarCount = 0;
        solarPanelRecursion(this.getBlockPos());
    }

    public void solarPanelRecursion(BlockPos pos) {
        for (Direction d : Direction.Plane.HORIZONTAL) {
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
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new StringTextComponent(""));
        output.add(new StringTextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        output.add(new StringTextComponent("Active Panels: " + activeSolarCount));
        output.add(new StringTextComponent("FE/t Production: " + Math.round(power)));
        return output;
    }
}
