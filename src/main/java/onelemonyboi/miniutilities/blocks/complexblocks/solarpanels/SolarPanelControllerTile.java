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
        if (world.isRemote()) {return;}

        solarPanelRecursion();
        power = world.isDaytime() ? Config.solarPanelGeneration.get() : Config.lunarPanelGeneration.get();
        power *= activeSolarCount;
        power *= activeSolarCount / (float) Config.panelMultiplier.get() + 1;
        getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().machineProduce((int) power);
        getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().outputToSide(world, pos, Direction.UP, Config.solarPanelGeneration.get() * 4096);
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    public void solarPanelRecursion() {
        posList = new ArrayList<>();
        activeSolarCount = 0;
        solarPanelRecursion(this.getPos());
    }

    public void solarPanelRecursion(BlockPos pos) {
        for (Direction d : Direction.Plane.HORIZONTAL) {
            BlockState blockState = world.getBlockState(pos.offset(d));
             if (posList.contains(pos.offset(d)) || !world.isAreaLoaded(pos.offset(d), 1)) {
                continue;
            }
            if (blockState.getBlock() instanceof SolarPanelBlock) {
                if (world.isDaytime() && world.canSeeSky(pos.offset(d))) {
                    activeSolarCount++;
                }
                posList.add(pos.offset(d));
                solarPanelRecursion(pos.offset(d));
            }
            else if (blockState.getBlock() instanceof LunarPanelBlock) {
                if (world.isNightTime() && world.canSeeSky(pos.offset(d))) {
                    activeSolarCount++;
                }
                posList.add(pos.offset(d));
                solarPanelRecursion(pos.offset(d));
            }
            else if (blockState.getBlock() instanceof SolarPanelController && !pos.offset(d).equals(this.getPos())) {
                world.destroyBlock(pos.offset(d), true);
                solarPanelRecursion(pos.offset(d));
            }
        }
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        output.add(new StringTextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        output.add(new StringTextComponent("Active Panels: " + activeSolarCount));
        output.add(new StringTextComponent("FE/t Production: " + Math.round(power)));
        return output;
    }
}
