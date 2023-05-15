// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.path;

import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.AiMining;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.finder.BlockFinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.AdvancedMiningBot;

public class GemstonePath extends AdvancedMiningBot
{
    public GemstonePath(final BetterBlockPos to) {
        super(to);
    }
    
    @Override
    public BlockFinder getFinder() {
        return pos -> AiMining.mineables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c());
    }
    
    @Override
    public double getMiningCost(final BlockPos pos, final Block block) {
        if (AiMining.mineables.contains(block)) {
            return 2.0;
        }
        return Utils.isUncollidable(pos, block) ? 0.0 : 2.0;
    }
}
