// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.mining.finder;

import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import net.minecraft.block.Block;
import java.util.Set;

public class MultipleBlockFinder implements BlockFinder
{
    public final Set<Block> blocks;
    
    public MultipleBlockFinder(final Set<Block> blocks) {
        this.blocks = blocks;
    }
    
    public MultipleBlockFinder(final Block... blocks) {
        this.blocks = new HashSet<Block>(Arrays.asList(blocks));
    }
    
    @Override
    public boolean isValid(final BlockPos pos) {
        return this.blocks.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c());
    }
}
