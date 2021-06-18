package onelemonyboi.miniutilities.blocks.complexblocks.lasers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import onelemonyboi.miniutilities.init.TEList;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class LaserHubBlock extends Block {
    public LaserHubBlock(Properties properties) {
        super(properties);
    }

    public static final VoxelShape UpShape = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 7, 16),
            Block.makeCuboidShape(6.5, 10, 6.5, 9.5, 15, 9.5),
            Block.makeCuboidShape(6, 8, 6, 10, 9, 10),
            Block.makeCuboidShape(5, 7, 5, 11, 8, 11),
            Block.makeCuboidShape(5, 9, 5, 11, 10, 11),
            Block.makeCuboidShape(5, 16, 5, 11, 16, 11),
            Block.makeCuboidShape(3, 7, 8, 5.5, 7.5, 8.5),
            Block.makeCuboidShape(6, 7, 3.75, 6.5, 7.5, 5.25),
            Block.makeCuboidShape(13.5, 7, 8.5, 14, 7.5, 10),
            Block.makeCuboidShape(2.5, 7, 3.75, 3, 7.5, 7.25),
            Block.makeCuboidShape(3, 7, 3.75, 6, 7.5, 4.25),
            Block.makeCuboidShape(10.5, 7, 7.25, 13, 7.5, 7.75),
            Block.makeCuboidShape(11, 7, 9.5, 13.5, 7.5, 10),
            Block.makeCuboidShape(11, 9, 7, 11.5, 10, 9),
            Block.makeCuboidShape(4.5, 9, 7, 5, 10, 9),
            Block.makeCuboidShape(13, 6.75, 7, 15, 7.75, 9),
            Block.makeCuboidShape(1, 6.75, 7, 3, 7.75, 9),
            Block.makeCuboidShape(11, 10, 5, 11, 16, 11),
            Block.makeCuboidShape(5, 10, 5, 11, 16, 5),
            Block.makeCuboidShape(5, 10, 11, 11, 16, 11),
            Block.makeCuboidShape(5, 10, 5, 5, 16, 11)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return UpShape;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TEList.LaserHubTile.get().create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (worldIn.isRemote()) {
            return;
        }

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        ItemStack itemStack = new ItemStack(this);
        CompoundNBT compoundNBT = tileEntity.write(new CompoundNBT());
        compoundNBT.remove("x");
        compoundNBT.remove("y");
        compoundNBT.remove("z");
        itemStack.setTagInfo("BlockEntityTag", compoundNBT);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}