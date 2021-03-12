package onelemonyboi.miniutilities.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;

public class StandardBlock extends net.minecraft.block.Block implements IItemProvider {
    private BlockItem itemBlock;

    public StandardBlock(Properties property)
    {
        super(property);
    }

    @Override
    public BlockItem createItemBlock() {
        return itemBlock;
    }

    public StandardBlock setItemBlock(Class<? extends BlockItem> itemBlock)
    {
        try { this.itemBlock = itemBlock.getDeclaredConstructor(Block.class).newInstance(this); }
        catch (Exception ignored) { }
        return this;
    }
}
