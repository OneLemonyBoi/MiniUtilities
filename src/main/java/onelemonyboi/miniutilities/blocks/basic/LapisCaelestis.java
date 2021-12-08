package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class LapisCaelestis extends Block {

    public LapisCaelestis() {
        super(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5f).sound(SoundType.STONE).setLightLevel((p) -> 1));
    }
}
