package onelemonyboi.miniutilities.blocks.spikes;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;

public class SpikeFakePlayer extends FakePlayer {
    public SpikeFakePlayer(ServerWorld world, GameProfile name) {
        super(world, name);
    }

    public SpikeFakePlayer(FakePlayer fakePlayer){
        this((ServerWorld) fakePlayer.world, fakePlayer.getGameProfile());
    }
}
