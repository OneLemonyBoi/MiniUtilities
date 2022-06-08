package onelemonyboi.miniutilities.blocks.spikes;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

import java.util.UUID;

import net.minecraft.block.AbstractBlock.Properties;

public class SpikeBlock extends Block {
    private int damage;
    private Boolean playerDamage;
    private Boolean expDropTrue;
    private Boolean dontKill;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static boolean cancelSounds = false;

    private static final GameProfile PROFILE = new GameProfile(UUID.fromString("5c32fc23-8c26-47fe-91ed-9c204b22f430"), "[Spikes]");
    private FakePlayer fakePlayer;

    public SpikeBlock(Properties properties, int damage, Boolean playerDamage, Boolean expDropTrue, Boolean dontKill) {
        super(properties);
        this.damage = damage;
        this.playerDamage = playerDamage;
        this.expDropTrue = expDropTrue;
        this.dontKill = dontKill;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Deprecated
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    public static final VoxelShape NorthShape = Block.box(0, 0, 9, 16, 16, 16);
    public static final VoxelShape SouthShape = Block.box(0, 0, 0, 16, 16, 7);
    public static final VoxelShape WestShape = Block.box(9, 0, 0, 16, 16, 16);
    public static final VoxelShape EastShape = Block.box(0, 0, 0, 7, 16, 16);
    public static final VoxelShape UpShape = Block.box(0, 0, 0, 16, 7, 16);
    public static final VoxelShape DownShape = Block.box(0, 9, 0, 16, 16, 16);

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn.isClientSide || !(entityIn instanceof LivingEntity)) return;

        if (this.dontKill && ((LivingEntity) entityIn).getHealth() <= this.damage) {return;}
        if (this.playerDamage) {
            if(fakePlayer == null) {
                fakePlayer = new SpikeFakePlayer(FakePlayerFactory.get((ServerWorld) worldIn, PROFILE));
                fakePlayer.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(damage);
                fakePlayer.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(1000000D);
            }

            fakePlayer.setLevel(worldIn);
            fakePlayer.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            cancelSounds = true;
            fakePlayer.attack(entityIn);
            cancelSounds = false;
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(0, 1, 0));
        }
        else {entityIn.hurt(DamageSource.CACTUS, this.damage);}
        if (this.expDropTrue) {
            ((LivingEntity)entityIn).lastHurtByPlayerTime = 100;
        }
        super.stepOn(worldIn, pos, entityIn);
    }

    public static void soundEvent(PlaySoundAtEntityEvent event) {
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
