package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.extensions.IForgeTileEntity;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.ArrayList;
import java.util.List;

public class LaserHubTile extends TileBase implements RenderInfoIdentifier, ITickableTileEntity {
    public LaserHubTile() {
        super(TEList.LaserHubTile.get(), TileBehaviors.laserHub);
    }

    @Override
    public void tick() {
        if (world.isRemote) {return;}

        List<LaserPortTile> blocks = getTEsInRadius(LaserPortTile.class, 16);
        for (LaserPortTile portTile : blocks) {
            if (portTile.isInput) getBehaviour()
                        .getRequired(TileTraits.PowerTrait.class)
                        .getEnergyStorage()
                        .outputToSide(world, portTile.getPos().offset(Direction.UP), Direction.DOWN, Integer.MAX_VALUE);

            else getBehaviour()
                    .getRequired(TileTraits.PowerTrait.class)
                    .getEnergyStorage()
                    .inputFromSide(world, portTile.getPos().offset(Direction.UP), Direction.DOWN, Integer.MAX_VALUE);

        }

        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        output.add(new StringTextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        return output;
    }

    public <T extends TileEntity> List<T> getTEsInRadius(Class<T> clazz, int radius) {
        List<T> output = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    if (clazz.isInstance(world.getTileEntity(getPos().add(x, y, z)))) {
                        output.add((T) world.getTileEntity(getPos().add(x, y, z)));
                    }
                }
            }
        }

        return output;
    }

    public List<Vector3d> getTEVectorsInRadius(Class<?> clazz, int radius, double distanceFromCenter) {
        List<Vector3d> output = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos pos = getPos().add(x, y, z);
                    if (clazz.isInstance(world.getTileEntity(pos))) {
                        Vector3d v3d = Vector3d.copy(pos);
                        v3d.add(0.5, 0.5, 0.5);
                        if (world.getBlockState(pos).hasProperty(LaserPortBlock.FACING)) {
                            switch (world.getBlockState(pos).get(LaserPortBlock.FACING)) {
                                case UP:
                                    v3d = v3d.add(0, -distanceFromCenter, 0);
                                    break;
                                case DOWN:
                                    v3d = v3d.add(0, distanceFromCenter, 0);
                                    break;
                                case NORTH:
                                    v3d = v3d.add(0, 0, distanceFromCenter);
                                    break;
                                case SOUTH:
                                    v3d = v3d.add(0, 0, -distanceFromCenter);
                                    break;
                                case WEST:
                                    v3d = v3d.add(distanceFromCenter, 0, 0);
                                    break;
                                case EAST:
                                    v3d = v3d.add(-distanceFromCenter, 0.5, 0.5);
                                    break;
                            }
                        }
                        output.add(v3d);
                    }
                }
            }
        }

        return output;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return IForgeTileEntity.INFINITE_EXTENT_AABB;
    }
}
