package onelemonyboi.miniutilities.blocks.basic;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.Block;

public class LapisCaelestis extends Block {

    public LapisCaelestis() {
        super(Properties.of(Material.METAL).strength(5f).sound(SoundType.STONE).lightLevel((p) -> 1));
    }
}
