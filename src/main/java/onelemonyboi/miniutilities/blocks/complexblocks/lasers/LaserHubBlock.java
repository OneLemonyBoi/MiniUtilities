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
            Block.makeCuboidShape(7, 13, 7, 9, 15, 9),
            Block.makeCuboidShape(6, 8, 6, 10, 9, 10),
            Block.makeCuboidShape(6.5, 9, 6.5, 9.5, 13, 9.5),
            Block.makeCuboidShape(5.5, 11, 5.5, 10.5, 12, 10.5),
            Block.makeCuboidShape(6, 16, 6, 10, 16, 10),
            Block.makeCuboidShape(10, 7.5, 10, 10.75, 12.5, 10.75),
            Block.makeCuboidShape(5.25, 7.5, 10, 6, 12.5, 10.75),
            Block.makeCuboidShape(5.25, 11.05, 7.65, 6, 12.5, 8.4),
            Block.makeCuboidShape(10, 11.05, 7.65, 10.75, 12.5, 8.4),
            Block.makeCuboidShape(10, 7.5, 5.25, 10.75, 12.5, 6),
            Block.makeCuboidShape(5.25, 7.5, 5.25, 6, 12.5, 6),
            Block.makeCuboidShape(5, 7, 5, 11, 8, 11),
            Block.makeCuboidShape(3.6, 8.25, 8, 6, 8.75, 8.5),
            Block.makeCuboidShape(6.5, 7, 3.75, 7, 7.5, 5.25),
            Block.makeCuboidShape(3, 7, 3.75, 3.5, 7.5, 7.25),
            Block.makeCuboidShape(3.5, 7, 3.75, 6.5, 7.5, 4.25),
            Block.makeCuboidShape(9.5, 8, 7.25, 12, 8.5, 7.75),
            Block.makeCuboidShape(9.5, 8.25, 8.25, 12, 8.75, 8.75),
            Block.makeCuboidShape(12, 7, 6.5, 14, 9, 9.5),
            Block.makeCuboidShape(2, 7, 6.5, 4, 9, 9.5),
            Block.makeCuboidShape(6, 12, 6, 10, 16, 6),
            Block.makeCuboidShape(6, 12, 6, 6, 16, 10),
            Block.makeCuboidShape(6, 12, 10, 10, 16, 10),
            Block.makeCuboidShape(10, 12, 6, 10, 16, 10)
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