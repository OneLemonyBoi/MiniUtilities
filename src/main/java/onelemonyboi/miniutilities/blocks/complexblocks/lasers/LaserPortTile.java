package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LaserPortTile extends TileBase implements RenderInfoIdentifier, ITickableTileEntity {
    @SaveInNBT(key = "IsInput")
    public boolean isInput;

    public LaserPortTile() {
        super(TEList.LaserPortTile.get(), TileBehaviors.laserPort);
        this.isInput = true;
    }

    @Override
    public void tick() {
        if (world.isRemote) return;

        if (isInput)
            getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().outputToSide(world, getPos(), getBlockState().get(LaserPortBlock.FACING), Integer.MAX_VALUE);
        else
            getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().inputFromSide(world, getPos(), getBlockState().get(LaserPortBlock.FACING), Integer.MAX_VALUE);


        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        output.add(new StringTextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        output.add(new StringTextComponent("I/O Mode: " + (this.isInput ? "Push to Machine" : "Pull from Machine")));
        return output;
    }
}
