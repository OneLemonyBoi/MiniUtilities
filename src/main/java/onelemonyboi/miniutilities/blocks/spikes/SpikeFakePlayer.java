package onelemonyboi.miniutilities.blocks.spikes;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.util.FakePlayer;

public class SpikeFakePlayer extends FakePlayer {
    public SpikeFakePlayer(ServerLevel world, GameProfile name) {
        super(world, name);
    }

    public SpikeFakePlayer(FakePlayer fakePlayer){
        this((ServerLevel) fakePlayer.level(), fakePlayer.getGameProfile());
    }
}
