package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import java.util.ArrayList;
import java.util.List;

public class LaserHubTile extends TileBase implements RenderInfoIdentifier {
    public LaserHubTile(BlockPos pos, BlockState state) {
        super(TEList.LaserHubTile.get(), pos, state, TileBehaviors.laserHub);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, LaserHubTile tile) {
        List<LaserPortTile> blocks = tile.getTEsInRadius(LaserPortTile.class, 16);
        for (LaserPortTile portTile : blocks) {
            if (portTile.isInput) tile.getBehaviour()
                        .getRequired(TileTraits.PowerTrait.class)
                        .getEnergyStorage()
                        .outputToSide(level, portTile.getBlockPos().relative(net.minecraft.core.Direction.UP), net.minecraft.core.Direction.DOWN, Integer.MAX_VALUE);

            else tile.getBehaviour()
                    .getRequired(TileTraits.PowerTrait.class)
                    .getEnergyStorage()
                    .inputFromSide(level, portTile.getBlockPos().relative(net.minecraft.core.Direction.UP), Direction.DOWN, Integer.MAX_VALUE);

        }

        level.sendBlockUpdated(pos, state, state, 2);
    }

    @Override
    public List<MutableComponent> getInfo() {
        List<MutableComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new TextComponent(""));
        output.add(new TextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        return output;
    }

    public <T extends BlockEntity> List<T> getTEsInRadius(Class<T> clazz, int radius) {
        List<T> output = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    if (clazz.isInstance(level.getBlockEntity(getBlockPos().offset(x, y, z)))) {
                        output.add((T) level.getBlockEntity(getBlockPos().offset(x, y, z)));
                    }
                }
            }
        }

        return output;
    }

    public List<Vec3> getTEVectorsInRadius(Class<?> clazz, int radius, double distanceFromCenter) {
        List<Vec3> output = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos pos = getBlockPos().offset(x, y, z);
                    if (clazz.isInstance(level.getBlockEntity(pos))) {
                        Vec3 v3d = Vec3.atLowerCornerOf(pos);
                        v3d.add(0.5, 0.5, 0.5);
                        if (level.getBlockState(pos).hasProperty(LaserPortBlock.FACING)) {
                            switch (level.getBlockState(pos).getValue(LaserPortBlock.FACING)) {
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
    public AABB getRenderBoundingBox() {
        return IForgeBlockEntity.INFINITE_EXTENT_AABB;
    }
}
