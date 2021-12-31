package onelemonyboi.miniutilities.blocks.complexblocks.drum;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.trait.BlockBehaviours;

public class DrumBlock extends BlockBase {
    public final int mb;

    public DrumBlock(int mb, Properties properties) {
        super(properties, BlockBehaviours.drum);
        this.mb = mb;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable IBlockReader blockReader, @Nonnull List<ITextComponent> textComponents, @Nonnull ITooltipFlag tooltipFlag) {
        CompoundNBT nbt = stack.getChildTag("BlockEntityTag");

        ITextComponent fluidName = ITextComponent.getTextComponentOrEmpty("Empty");
        int fluidAmount = 0;

        if (nbt != null) {
            FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(nbt);
            fluidName = fluidStack.getFluid().getAttributes().getDisplayName(fluidStack);
            fluidAmount = fluidStack.getAmount();
        }

        textComponents.add(new TranslationTextComponent("text.miniutilities.tooltip.drum_fluid", fluidName));
        textComponents.add(new TranslationTextComponent("text.miniutilities.tooltip.drum_amount", fluidAmount, mb));

        super.addInformation(stack, blockReader, textComponents, tooltipFlag);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack held = player.getHeldItem(hand);

        if (FluidUtil.interactWithFluidHandler(player, hand, world, pos, hit.getFace()) ||
                held.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        DrumTile tile = (DrumTile) worldIn.getTileEntity(pos);
        double percent = (double) tile.getDrum().getFluidAmount() / (double) tile.getDrum().getCapacity();
        // Multiplying by 15 allows for 0 and 15 to be exclusively for empty and full
        int out = (int) Math.ceil(percent * 15);
        if (percent >= 1) out = 16;
        return out;
    }
}
