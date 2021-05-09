package onelemonyboi.miniutilities.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import onelemonyboi.lemonlib.blocks.TileBase;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;

import java.util.function.Supplier;

public class RedstoneModeUpdate {
    int redstoneMode;
    BlockPos pos;

    public RedstoneModeUpdate(int redstoneMode, BlockPos pos) {
        this.redstoneMode = redstoneMode;
        this.pos = pos;
    }

    public static void encode(RedstoneModeUpdate packet, PacketBuffer buf) {
        buf.writeInt(packet.redstoneMode);
        buf.writeBlockPos(packet.pos);
    }

    public static RedstoneModeUpdate decode(PacketBuffer buf) {
        return new RedstoneModeUpdate(buf.readInt(), buf.readBlockPos());
    }

    public static void handle(RedstoneModeUpdate msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()-> {
            TileEntity te = ctx.get().getSender().getEntityWorld().getTileEntity(msg.pos);
            if (te == null) {
                return;
            }
            if (te instanceof QuantumQuarryTile) {
                QuantumQuarryTile tile = (QuantumQuarryTile) te;
                tile.redstonemode = msg.redstoneMode;
            }
            else if (te instanceof MechanicalMinerTile) {
                MechanicalMinerTile tile = (MechanicalMinerTile) te;
                tile.redstonemode = msg.redstoneMode;
            }
            else if (te instanceof MechanicalPlacerTile) {
                MechanicalPlacerTile tile = (MechanicalPlacerTile) te;
                tile.redstonemode = msg.redstoneMode;
            }
        });
    }
}
