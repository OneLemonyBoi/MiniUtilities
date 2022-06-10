package onelemonyboi.miniutilities.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalminer.MechanicalMinerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.mechanicalplacer.MechanicalPlacerTile;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.QuantumQuarryTile;

import java.util.function.Supplier;

public class RedstoneModeUpdate {
    int redstoneMode;
    net.minecraft.core.BlockPos pos;

    public RedstoneModeUpdate(int redstoneMode, BlockPos pos) {
        this.redstoneMode = redstoneMode;
        this.pos = pos;
    }

    public static void encode(RedstoneModeUpdate packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.redstoneMode);
        buf.writeBlockPos(packet.pos);
    }

    public static RedstoneModeUpdate decode(FriendlyByteBuf buf) {
        return new RedstoneModeUpdate(buf.readInt(), buf.readBlockPos());
    }

    public static void handle(RedstoneModeUpdate msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()-> {
            BlockEntity te = ctx.get().getSender().getCommandSenderWorld().getBlockEntity(msg.pos);
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
