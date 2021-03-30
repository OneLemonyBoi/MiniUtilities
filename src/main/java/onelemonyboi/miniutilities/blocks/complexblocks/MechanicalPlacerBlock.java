package onelemonyboi.miniutilities.blocks.complexblocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import onelemonyboi.miniutilities.data.ModTags;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.tileentities.MechanicalPlacerTile;
import org.lwjgl.glfw.GLFW;

import java.util.UUID;

public class MechanicalPlacerBlock extends DirectionalMechanicalMachine {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static Boolean keyPressed = false;

    public MechanicalPlacerBlock() {
        super(Properties.create(Material.IRON).hardnessAndResistance(3F)
                .sound(SoundType.METAL));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TEList.MechanicalPlacerTile.get().create();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            Boolean wrenchCheck = ModTags.Items.WRENCH.contains(player.getHeldItem(handIn).getItem()) || ModTags.Items.WRENCHES.contains(player.getHeldItem(handIn).getItem()) || ModTags.Items.TOOLS_WRENCH.contains(player.getHeldItem(handIn).getItem());
            if (te instanceof MechanicalPlacerTile && wrenchCheck && keyPressed) {
                MechanicalPlacerTile TE = ((MechanicalPlacerTile) te);
                player.sendMessage(new TranslationTextComponent("text.miniutilities.info"), UUID.randomUUID());
                if (TE.redstonemode == 1) {
                    player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtoone"), UUID.randomUUID());
                }
                else if (TE.redstonemode == 2) {
                    player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtotwo"), UUID.randomUUID());
                }
                else if (TE.redstonemode == 3) {
                    player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtothree"), UUID.randomUUID());
                }
                player.sendMessage(new TranslationTextComponent("text.miniutilities.waittime")
                        .appendString(TE.waittime.toString() + " ticks(" + String.valueOf(TE.waittime.floatValue() / 20))
                        .appendSibling(new TranslationTextComponent("text.miniutilities.seconds"))
                        .appendString(")"), UUID.randomUUID());
            }
            else if (te instanceof MechanicalPlacerTile && wrenchCheck) {
                MechanicalPlacerTile TE = ((MechanicalPlacerTile) te);
                switch (TE.redstonemode) {
                    case 1:
                        TE.redstonemode = 2;
                        player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtotwo"), UUID.randomUUID());
                        break;
                    case 2:
                        TE.redstonemode = 3;
                        player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtothree"), UUID.randomUUID());
                        break;
                    case 3:
                        TE.redstonemode = 1;
                        player.sendMessage(new TranslationTextComponent("text.miniutilities.redstonemodeswitchedtoone"), UUID.randomUUID());
                        break;
                }
                return ActionResultType.CONSUME;
            }
            else if (te instanceof MechanicalPlacerTile && ModTags.Items.UPGRADES_SPEED.contains(player.getHeldItem(handIn).getItem())) {
                MechanicalPlacerTile TE = ((MechanicalPlacerTile) te);
                if (TE.waittime > 5) {
                    TE.waittime = TE.waittime - 5;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
                else if (TE.waittime == 5){
                    TE.waittime = 1;
                    player.getHeldItem(handIn).shrink(1);
                    TE.timer = 0;
                }
            }
            else if (te instanceof MechanicalPlacerTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (MechanicalPlacerTile) te, pos);
                return ActionResultType.CONSUME;
            }
        }
        return ActionResultType.CONSUME;
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        if (event.getKey() == GLFW.GLFW_KEY_LEFT_ALT) {
            if (event.getAction() == GLFW.GLFW_PRESS) {
                keyPressed = true;
            }
            else if (event.getAction() == GLFW.GLFW_RELEASE) {
                keyPressed = false;
            }
        }
    }
}
