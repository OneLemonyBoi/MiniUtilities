package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.startup.JSON.QuantumQuarryJSON;
import onelemonyboi.miniutilities.trait.TileBehaviors;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class QuantumQuarryTile extends TileBase implements MenuProvider, RenderInfoIdentifier {
    public static int slots = 9;

    // 1: Always on
    // 2: Redstone to Enable
    // 3: Redstone to Disable
    @SaveInNBT(key = "RedstoneMode")
    public Integer redstonemode;
    public Integer timer;
    @SaveInNBT(key = "WaitTime")
    public Integer waittime;
    public ArrayList<ItemStack> insertStacks;

    public QuantumQuarryTile(BlockPos pos, BlockState state) {
        super(TEList.QuantumQuarryTile.get(), pos, state, TileBehaviors.quantumQuarry);
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 1200;
        this.insertStacks = generateItemStacks();
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("container.miniutilities.quantum_quarry");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new QuantumQuarryContainer(id, inventory, ContainerLevelAccess.create(getLevel(), getBlockPos()));
    }

    public static int calcRFCost(int waittime) {
        return Math.round(4000000 / (float) Math.pow(waittime, 1.5F));
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, QuantumQuarryTile tile) {
        level.sendBlockUpdated(pos, state, state, 2);

        if (tile.timer == 0 && !tile.insertStacks.isEmpty()){
            tile.tryInsert(tile.insertStacks);
            return;
        }
        if ((!level.hasNeighborSignal(pos) && tile.redstonemode == 2) || (level.hasNeighborSignal(pos) && tile.redstonemode == 3)) {
            return;
        }
        if (!tile.getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().checkedMachineConsume(calcRFCost(tile.waittime))) {
            return;
        }
        if (tile.insertStacks.isEmpty()) {
            tile.insertStacks = tile.generateItemStacks();
        }
        tile.timer++;
        if (tile.timer < tile.waittime) {return;}
        tile.timer = 0;
        tile.tryInsert(tile.insertStacks);
    }

    private void tryInsert(ArrayList<ItemStack> insertStacks) {
        for (int j = 0; j < insertStacks.size(); j++) {
            ItemStack insertStack = insertStacks.get(j);
            for (int i = 0; i < 9; i++) {
                insertStack = getBehaviour().getRequired(TileTraits.ItemTrait.class).getItemStackHandler().insertItem(i, insertStack, false);
            }
            if(insertStack.isEmpty()){
                insertStacks.remove(j);
                j--;
            }
        }
        this.setChanged();
    }

    private ArrayList<ItemStack> generateItemStacks() {
        if (level == null) return new ArrayList<>();

        String biomeStr = level.getBiome(getBlockPos()).value().getRegistryName().toString();
        String dimensionStr = level.dimension().location().toString();


        RandomChooser<QuantumQuarryJSON.OreInfo> randomOreChooser = QuantumQuarryJSON.randomOreChooser;
        Predicate<QuantumQuarryJSON.OreInfo> blacklist = (oreInfo) -> !oreInfo.biomes.isEmpty() && !oreInfo.biomes.contains(biomeStr);
        blacklist = blacklist.or((oreInfo) -> !oreInfo.dimensions.isEmpty() && !oreInfo.dimensions.contains(dimensionStr));

        Map<QuantumQuarryJSON.OreInfo, Integer> itemsOfHighestWorth = randomOreChooser.getItemsOfHighestWorth(blacklist);

        ArrayList<ItemStack> res = new ArrayList<>();
        if (itemsOfHighestWorth.size() > 0) {
            for (Map.Entry<QuantumQuarryJSON.OreInfo, Integer> entry : itemsOfHighestWorth.entrySet()) {
                net.minecraft.resources.ResourceLocation id = ResourceLocation.tryParse(entry.getKey().name);
                Item item = ForgeRegistries.ITEMS.getValue(id);
                int count = entry.getValue();
                while (count > item.getMaxStackSize()){
                    count -= item.getMaxStackSize();
                    res.add(new ItemStack(item, item.getMaxStackSize()));
                }
                res.add(new ItemStack(item, count));
            }
        }
        return res;
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }

    @Override
    public List<MutableComponent> getInfo() {
        List<MutableComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new TextComponent(""));
        switch (this.redstonemode) {
            case 1:
                output.add(new TranslatableComponent("text.miniutilities.redstonemodeone"));
                break;
            case 2:
                output.add(new TranslatableComponent("text.miniutilities.redstonemodetwo"));
                break;
            case 3:
                output.add(new TranslatableComponent("text.miniutilities.redstonemodethree"));
                break;
        }
        output.add(new TranslatableComponent("text.miniutilities.waittime")
                .append(this.waittime.toString() + " ticks(" + this.waittime.floatValue() / 20)
                .append(new TranslatableComponent("text.miniutilities.seconds"))
                .append(")"));
        output.add(new TextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        output.add(new TextComponent("FE/t Consumption: " + calcRFCost(this.waittime)));
        return output;
    }
}
