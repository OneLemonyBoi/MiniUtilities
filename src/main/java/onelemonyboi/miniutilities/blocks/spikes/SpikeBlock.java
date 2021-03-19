package onelemonyboi.miniutilities.blocks.spikes;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.UUID;

public class SpikeBlock extends Block {
    private int damage;
    private Boolean playerDamage;
    private Boolean expDropTrue;
    private Boolean dontKill;

    private static GameProfile PROFILE = new GameProfile(UUID.fromString("5c32fc23-8c26-47fe-91ed-9c204b22f430"), "[FakeOneLemonyBoi]");

    public SpikeBlock(Properties properties, int damage, Boolean playerDamage, Boolean expDropTrue, Boolean dontKill) {
        super(properties);
        this.damage = damage;
        this.playerDamage = playerDamage;
        this.expDropTrue = expDropTrue;
        this.dontKill = dontKill;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn.isRemote || !(entityIn instanceof LivingEntity)) return;
        FakePlayer fakePlayer = new SpikeFakePlayer(FakePlayerFactory.get((ServerWorld) worldIn, PROFILE));
        if (this.dontKill && ((LivingEntity) entityIn).getHealth() <= this.damage) {return;}
        if (this.playerDamage) {entityIn.attackEntityFrom(DamageSource.causePlayerDamage(fakePlayer), this.damage);}
        else {entityIn.attackEntityFrom(DamageSource.CACTUS, this.damage);}
        if (this.expDropTrue) {
            ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, (LivingEntity) entityIn, 100, "field_70718_bc");}
        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
