package club.someoneice.mython.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockTool extends Block {
    protected BlockTool(float hardness) {
        super(Material.rock);
        this.blockHardness = hardness;

    }
}
