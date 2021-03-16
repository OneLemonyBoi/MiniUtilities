package onelemonyboi.miniutilities.base;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

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
