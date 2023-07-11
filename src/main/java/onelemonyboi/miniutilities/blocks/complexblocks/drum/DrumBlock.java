package onelemonyboi.miniutilities.blocks.complexblocks.drum;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import java.util.List;

import onelemonyboi.lemonlib.blocks.block.BlockBase;
import onelemonyboi.miniutilities.blocks.complexblocks.lasers.LaserHubTile;
import onelemonyboi.miniutilities.trait.BlockBehaviors;

public class DrumBlock extends BlockBase {
    public final int mb;

    public DrumBlock(int mb, net.minecraft.world.level.block.state.BlockBehaviour.Properties properties) {
        super(properties, BlockBehaviors.drum);
        this.mb = mb;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, BlockGetter blockReader, List<Component> textComponents, TooltipFlag tooltipFlag) {
        CompoundTag nbt = stack.getTagElement("BlockEntityTag");

        Component fluidName = Component.empty().copy();
        int fluidAmount = 0;

        if (nbt != null) {
            FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(nbt);
            fluidName = fluidStack.getFluid().getFluidType().getDescription(fluidStack);
            fluidAmount = fluidStack.getAmount();
        }

        textComponents.add(Component.translatable("text.miniutilities.tooltip.drum_fluid", fluidName));
        textComponents.add(Component.translatable("text.miniutilities.tooltip.drum_amount", fluidAmount, mb));

        super.appendHoverText(stack, blockReader, textComponents, tooltipFlag);
    }

    @Override
    public InteractionResult use(net.minecraft.world.level.block.state.BlockState state, Level world, net.minecraft.core.BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);

        if (FluidUtil.interactWithFluidHandler(player, hand, world, pos, hit.getDirection()) ||
                held.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(net.minecraft.world.level.block.state.BlockState blockState, Level worldIn, BlockPos pos) {
        DrumTile tile = (DrumTile) worldIn.getBlockEntity(pos);
        double percent = (double) tile.getDrum().getFluidAmount() / (double) tile.getDrum().getCapacity();
        // Multiplying by 15 allows for 0 and 15 to be exclusively for empty and full
        int out = (int) Math.ceil(percent * 15);
        if (percent >= 1) out = 16;
        return out;
    }
}
