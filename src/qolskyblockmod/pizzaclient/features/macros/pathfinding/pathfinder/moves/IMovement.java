// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves;

import net.minecraft.block.Block;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.MutableBlockPosNode;
import net.minecraft.block.BlockLiquid;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;

public interface IMovement
{
    public static final double ACTION_COST = 0.9999;
    
    boolean addBlock(final PathNode p0);
    
    default double calculatePos() {
        final MutableBlockPosNode mutableNode;
        final MutableBlockPosNode pos = mutableNode = BasePathfinder.path.mutableNode;
        --mutableNode.field_177960_b;
        Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
        if (!Utils.isUncollidable(pos, block)) {
            ++pos.field_177960_b;
            return 0.9999;
        }
        if (block instanceof BlockLiquid) {
            ++pos.field_177960_b;
            return 4.9999;
        }
        final MutableBlockPosNode mutableBlockPosNode = pos;
        --mutableBlockPosNode.field_177960_b;
        while (Utils.isUncollidable(block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c(), pos)) {
            if (block instanceof BlockLiquid) {
                return 5.9999;
            }
            final MutableBlockPosNode mutableBlockPosNode2 = pos;
            --mutableBlockPosNode2.field_177960_b;
        }
        ++pos.field_177960_b;
        return 1.3999;
    }
}
