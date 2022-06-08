package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class LapisCaelestis extends Block {

    public LapisCaelestis() {
        super(AbstractBlock.Properties.of(Material.METAL).strength(5f).sound(SoundType.STONE).lightLevel((p) -> 1));
    }
}
