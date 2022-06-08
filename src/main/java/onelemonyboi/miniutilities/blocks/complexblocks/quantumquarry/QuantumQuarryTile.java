package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.lemonlib.annotations.SaveInNBT;
import onelemonyboi.lemonlib.blocks.tile.TileBase;
import onelemonyboi.lemonlib.identifiers.RenderInfoIdentifier;
import onelemonyboi.lemonlib.trait.tile.TileTraits;
import onelemonyboi.miniutilities.init.TEList;
import onelemonyboi.miniutilities.startup.JSON.QuantumQuarryJSON;
import onelemonyboi.miniutilities.trait.TileBehaviors;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class QuantumQuarryTile extends TileBase implements INamedContainerProvider, RenderInfoIdentifier, ITickableTileEntity {
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

    public QuantumQuarryTile() {
        super(TEList.QuantumQuarryTile.get(), TileBehaviors.quantumQuarry);
        this.redstonemode = 1;
        this.timer = 0;
        this.waittime = 1200;
        this.insertStacks = generateItemStacks();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.miniutilities.quantum_quarry");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory player, PlayerEntity entity) {
        return new QuantumQuarryContainer(id, player, this);
    }

    public static int calcRFCost(int waittime) {
        return Math.round(4000000 / (float) Math.pow(waittime, 1.5F));
    }

    @Override
    public void tick() {
        if (level.isClientSide()) {return;}

        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);

        if(this.timer == 0 && !insertStacks.isEmpty()){
            tryInsert(insertStacks);
            return;
        }

        if ((!level.hasNeighborSignal(this.getBlockPos()) && this.redstonemode == 2) || (level.hasNeighborSignal(this.getBlockPos()) && this.redstonemode == 3)) {
            return;
        }
        if (!getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().checkedMachineConsume(calcRFCost(this.waittime))) {
            return;
        }
        if (insertStacks.isEmpty()) {
            insertStacks = generateItemStacks();
        }
        this.timer++;
        if (this.timer < this.waittime) {return;}
        this.timer = 0;
        tryInsert(insertStacks);
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

        String biomeStr = level.getBiome(getBlockPos()).getRegistryName().toString();
        String dimensionStr = level.dimension().location().toString();


        RandomChooser<QuantumQuarryJSON.OreInfo> randomOreChooser = QuantumQuarryJSON.randomOreChooser;
        Predicate<QuantumQuarryJSON.OreInfo> blacklist = (oreInfo) -> !oreInfo.biomes.isEmpty() && !oreInfo.biomes.contains(biomeStr);
        blacklist = blacklist.or((oreInfo) -> !oreInfo.dimensions.isEmpty() && !oreInfo.dimensions.contains(dimensionStr));

        Map<QuantumQuarryJSON.OreInfo, Integer> itemsOfHighestWorth = randomOreChooser.getItemsOfHighestWorth(blacklist);

        ArrayList<ItemStack> res = new ArrayList<>();
        if (itemsOfHighestWorth.size() > 0) {
            for (Map.Entry<QuantumQuarryJSON.OreInfo, Integer> entry : itemsOfHighestWorth.entrySet()) {
                ResourceLocation id = ResourceLocation.tryParse(entry.getKey().name);
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
    public List<ITextComponent> getInfo() {
        List<ITextComponent> output = new ArrayList<>();

        output.add(this.getBlockState().getBlock().getName());
        output.add(new StringTextComponent(""));
        switch (this.redstonemode) {
            case 1:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodeone"));
                break;
            case 2:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodetwo"));
                break;
            case 3:
                output.add(new TranslationTextComponent("text.miniutilities.redstonemodethree"));
                break;
        }
        output.add(new TranslationTextComponent("text.miniutilities.waittime")
                .append(this.waittime.toString() + " ticks(" + this.waittime.floatValue() / 20)
                .append(new TranslationTextComponent("text.miniutilities.seconds"))
                .append(")"));
        output.add(new StringTextComponent("Power: " + getBehaviour().getRequired(TileTraits.PowerTrait.class).getEnergyStorage().toString()));
        output.add(new StringTextComponent("FE/t Consumption: " + calcRFCost(this.waittime)));
        return output;
    }
}
