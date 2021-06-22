package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeTileEntity;
import onelemonyboi.lemonlib.blocks.EnergyTileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LaserHubTile extends EnergyTileBase implements RenderInfoIdentifier {
    public LaserHubTile() {
        super(TEList.LaserHubTile.get(), 1048576, 1048576, 1048576);
    }

    @Override
    public void tick() {
        if (world.isRemote) {return;}

        List<BlockPos> blocks = getTargetedTEPosInRadius(LaserPortTile.class, 16);
        for (BlockPos pos : blocks) {
            LaserPortTile portTile = (LaserPortTile) world.getTileEntity(pos);
            if (portTile.isInput) {
                this.energy.outputToSide(world, portTile.getPos().offset(Direction.UP), Direction.DOWN, Integer.MAX_VALUE);
            }
            else {
                this.energy.inputFromSide(world, portTile.getPos().offset(Direction.UP), Direction.DOWN, Integer.MAX_VALUE);
            }
        }

        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    @Override
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getTranslatedName());
        output.add(new StringTextComponent(""));
        output.add(new StringTextComponent("Power: " + this.energy.toString()));
        return output;
    }

    public List<BlockPos> getTargetedTEPosInRadius(Class<?> clazz, int radius) {
        List<BlockPos> output = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    if (clazz.isInstance(world.getTileEntity(this.getPos().add(x, y, z)))) {
                        output.add(this.getPos().add(x, y, z));
                    }
                }
            }
        }

        return output;
    }

    public List<Vector3d> getTargetedTEVectorInRadius(Class<?> clazz, int radius) {
        List<Vector3d> output = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    if (clazz.isInstance(world.getTileEntity(this.getPos().add(x, y, z)))) {
                        output.add(Vector3d.copy(this.getPos().add(x, y, z)));
                    }
                }
            }
        }

        return output;
    }

    public List<Vector3d> getTargetedTEVectorInRadius(Class<?> clazz, int radius, double distanceFromCenter) {
        List<Vector3d> output = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos pos = this.getPos().add(x, y, z);
                    if (clazz.isInstance(world.getTileEntity(pos))) {
                        Vector3d v3d = Vector3d.copy(pos);
                        if (world.getBlockState(pos).hasProperty(LaserPortBlock.FACING)) {
                            switch (world.getBlockState(pos).get(LaserPortBlock.FACING)) {
                                case UP:
                                    v3d = v3d.add(0.5, -distanceFromCenter + 0.5, 0.5);
                                    break;
                                case DOWN:
                                    v3d = v3d.add(0.5, distanceFromCenter + 0.5, 0.5);
                                    break;
                                case NORTH:
                                    v3d = v3d.add(0.5, 0.5, distanceFromCenter + 0.5);
                                    break;
                                case SOUTH:
                                    v3d = v3d.add(0.5, 0.5, -distanceFromCenter + 0.5);
                                    break;
                                case WEST:
                                    v3d = v3d.add(distanceFromCenter + 0.5, 0.5, 0.5);
                                    break;
                                case EAST:
                                    v3d = v3d.add(-distanceFromCenter + 0.5, 0.5, 0.5);
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
