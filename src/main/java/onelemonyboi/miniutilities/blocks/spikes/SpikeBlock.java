package onelemonyboi.miniutilities.blocks.spikes;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.UUID;

import net.minecraftforge.event.PlayLevelSoundEvent;
import org.apache.logging.log4j.core.util.ReflectionUtil;

public class SpikeBlock extends net.minecraft.world.level.block.Block {
    private int damage;
    private Boolean playerDamage;
    private Boolean expDropTrue;
    private Boolean dontKill;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static boolean cancelSounds = false;

    private static final GameProfile PROFILE = new GameProfile(UUID.fromString("5c32fc23-8c26-47fe-91ed-9c204b22f430"), "[Spikes]");
    private FakePlayer fakePlayer;

    public SpikeBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties properties, int damage, Boolean playerDamage, Boolean expDropTrue, Boolean dontKill) {
        super(properties);
        this.damage = damage;
        this.playerDamage = playerDamage;
        this.expDropTrue = expDropTrue;
        this.dontKill = dontKill;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Deprecated
    public net.minecraft.world.level.block.state.BlockState rotate(net.minecraft.world.level.block.state.BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Deprecated
    public BlockState mirror(net.minecraft.world.level.block.state.BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    public static final net.minecraft.world.phys.shapes.VoxelShape NorthShape = net.minecraft.world.level.block.Block.box(0, 0, 9, 16, 16, 16);
    public static final net.minecraft.world.phys.shapes.VoxelShape SouthShape = net.minecraft.world.level.block.Block.box(0, 0, 0, 16, 16, 7);
    public static final net.minecraft.world.phys.shapes.VoxelShape WestShape = Block.box(9, 0, 0, 16, 16, 16);
    public static final VoxelShape EastShape = net.minecraft.world.level.block.Block.box(0, 0, 0, 7, 16, 16);
    public static final net.minecraft.world.phys.shapes.VoxelShape UpShape = net.minecraft.world.level.block.Block.box(0, 0, 0, 16, 7, 16);
    public static final VoxelShape DownShape = net.minecraft.world.level.block.Block.box(0, 9, 0, 16, 16, 16);

    @Override
    public net.minecraft.world.phys.shapes.VoxelShape getShape(net.minecraft.world.level.block.state.BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(FACING);
        switch (dir) {
            case NORTH:
                return NorthShape;
            case SOUTH:
                return SouthShape;
            case WEST:
                return WestShape;
            case EAST:
                return EastShape;
            case UP:
                return UpShape;
            default:
                return DownShape;
        }
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (!(worldIn instanceof ServerLevel level) || !(entityIn instanceof LivingEntity entity)) return;
        if (this.dontKill && entity.getHealth() <= this.damage) {return;}
        if (this.playerDamage) {
            if(fakePlayer == null) {
                fakePlayer = new SpikeFakePlayer(FakePlayerFactory.get((ServerLevel) worldIn, PROFILE));
                fakePlayer.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(damage);
                fakePlayer.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(1000000D);
            }

            fakePlayer.setLevel(level);
            fakePlayer.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            cancelSounds = true;
            fakePlayer.attack(entityIn);
            cancelSounds = false;
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(0, 1, 0));
        }
        else {entityIn.hurt(DamageSource.CACTUS, this.damage);}
        if (this.expDropTrue) {
            try {
                ReflectionUtil.setFieldValue(LivingEntity.class.getDeclaredField("lastHurtByPlayerTime"), entity, 100);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        super.stepOn(worldIn, pos, state, entityIn);
    }

    public static void soundEvent(PlayLevelSoundEvent event) {
        if(cancelSounds) {
            SoundEvent e = event.getSound();

            if (e == SoundEvents.PLAYER_ATTACK_KNOCKBACK
                    || e == SoundEvents.PLAYER_ATTACK_SWEEP
                    || e == SoundEvents.PLAYER_ATTACK_CRIT
                    || e == SoundEvents.PLAYER_ATTACK_STRONG
                    || e == SoundEvents.PLAYER_ATTACK_WEAK
                    || e == SoundEvents.PLAYER_ATTACK_NODAMAGE) {
                event.setCanceled(true);
            }
        }
    }
}
