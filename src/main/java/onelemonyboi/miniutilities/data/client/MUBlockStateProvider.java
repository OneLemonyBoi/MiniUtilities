package onelemonyboi.miniutilities.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import onelemonyboi.miniutilities.MiniUtilities;
import onelemonyboi.miniutilities.init.BlockList;

public class MUBlockStateProvider extends BlockStateProvider {
    public MUBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MiniUtilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockList.EnderOre.get());
        simpleBlock(BlockList.EnderPearlBlock.get());
        simpleBlock(BlockList.AngelBlock.get());
    }
}
