package onelemonyboi.miniutilities.blocks.spikes;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import onelemonyboi.miniutilities.MiniUtilities;

import java.util.UUID;

import static net.minecraft.block.BarrelBlock.PROPERTY_FACING;

public class SpikeBlock extends Block {
    private int damage;
    private Boolean playerDamage;
    private Boolean expDropTrue;
    private Boolean dontKill;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private static GameProfile PROFILE = new GameProfile(UUID.fromString("5c32fc23-8c26-47fe-91ed-9c204b22f430"), "[Spikes]");

    public SpikeBlock(Properties properties, int damage, Boolean playerDamage, Boolean expDropTrue, Boolean dontKill) {
        super(properties);
        this.damage = damage;
        this.playerDamage = playerDamage;
        this.expDropTrue = expDropTrue;
        this.dontKill = dontKill;
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Deprecated
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getFace());
    }

    public static final VoxelShape NorthShape = Block.makeCuboidShape(0, 0, 9, 16, 16, 16);
    public static final VoxelShape SouthShape = Block.makeCuboidShape(0, 0, 0, 16, 16, 7);
    public static final VoxelShape WestShape = Block.makeCuboidShape(9, 0, 0, 16, 16, 16);
    public static final VoxelShape EastShape = Block.makeCuboidShape(0, 0, 0, 7, 16, 16);
    public static final VoxelShape UpShape = Block.makeCuboidShape(0, 0, 0, 16, 7, 16);
    public static final VoxelShape DownShape = Block.makeCuboidShape(0, 9, 0, 16, 16, 16);

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction dir = state.get(PROPERTY_FACING);
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
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn.isRemote || !(entityIn instanceof LivingEntity)) return;
        FakePlayer fakePlayer = new SpikeFakePlayer(FakePlayerFactory.get((ServerWorld) worldIn, PROFILE));
        if (this.dontKill && ((LivingEntity) entityIn).getHealth() <= this.damage) {return;}
        if (this.playerDamage) {
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(fakePlayer), this.damage);
            entityIn.setVelocity(0, 0, 0);
        }
        else {entityIn.attackEntityFrom(DamageSource.CACTUS, this.damage);}
        if (this.expDropTrue) {
            ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, (LivingEntity) entityIn, 100, "field_70718_bc");}
        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
