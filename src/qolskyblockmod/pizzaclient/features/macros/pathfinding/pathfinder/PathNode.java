// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder;

import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.block.Block;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;

public class PathNode
{
    public static final double COST_INF = 1000000.0;
    public final int x;
    public final int y;
    public final int z;
    public final double estimatedCostToGoal;
    public double cost;
    public double combinedCost;
    public PathNode previous;
    public int heapPosition;
    
    public PathNode(final BetterBlockPos pos) {
        this.heapPosition = -1;
        this.x = pos.field_177962_a;
        this.y = pos.field_177960_b;
        this.z = pos.field_177961_c;
        this.cost = 1000000.0;
        this.estimatedCostToGoal = MathUtil.abs(this.x - BasePathfinder.path.goalPos.field_177962_a) + MathUtil.abs(this.y - BasePathfinder.path.goalPos.field_177960_b) + MathUtil.abs(this.z - BasePathfinder.path.goalPos.field_177961_c);
    }
    
    public PathNode(final int x, final int y, final int z) {
        this.heapPosition = -1;
        this.x = x;
        this.y = y;
        this.z = z;
        this.cost = 1000000.0;
        this.estimatedCostToGoal = MathUtil.abs(x - BasePathfinder.path.goalPos.field_177962_a) + MathUtil.abs(y - BasePathfinder.path.goalPos.field_177960_b) + MathUtil.abs(z - BasePathfinder.path.goalPos.field_177961_c);
    }
    
    public PathNode() {
        this.heapPosition = -1;
        this.x = BasePathfinder.path.currentPos.field_177962_a;
        this.y = BasePathfinder.path.currentPos.field_177960_b;
        this.z = BasePathfinder.path.currentPos.field_177961_c;
        this.estimatedCostToGoal = MathUtil.abs(this.x - BasePathfinder.path.goalPos.field_177962_a) + MathUtil.abs(this.y - BasePathfinder.path.goalPos.field_177960_b) + MathUtil.abs(this.z - BasePathfinder.path.goalPos.field_177961_c);
        this.combinedCost = this.estimatedCostToGoal;
    }
    
    public BlockPos toBlockPos() {
        return new BlockPos(this.x, this.y, this.z);
    }
    
    public BetterBlockPos toBetterBlockPos() {
        return new BetterBlockPos(this.x, this.y, this.z);
    }
    
    public boolean isBlockLoaded() {
        return this.x >= -30000000 && this.z >= -30000000 && this.x < 30000000 && this.z < 30000000 && this.y >= 0 && this.y < 256 && Utils.isChunkLoaded(this.x >> 4, this.z >> 4);
    }
    
    public boolean isSameXandZ(final BetterBlockPos pos) {
        return pos.field_177962_a == this.x && pos.field_177961_c == this.z;
    }
    
    public boolean isSameXandZ(final int posX, final int posZ) {
        return posX == this.x && posZ == this.z;
    }
    
    @Override
    public int hashCode() {
        return (int)(482263L * (751913L * this.x + this.y) + this.z);
    }
    
    @Override
    public boolean equals(final Object obj) {
        final PathNode other = (PathNode)obj;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
    
    public boolean equals(final PathNode other) {
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
    
    public double heuristic() {
        return MathUtil.abs(this.x - BasePathfinder.path.goalPos.field_177962_a) + MathUtil.abs(this.y - BasePathfinder.path.goalPos.field_177960_b) + MathUtil.abs(this.z - BasePathfinder.path.goalPos.field_177961_c);
    }
    
    public List<BlockPos> calculateMoves() {
        PathNode current = this;
        final LinkedList<BlockPos> tempNodes = new LinkedList<BlockPos>();
        while (current != null) {
            tempNodes.addFirst(new BlockPos(current.x, current.y, current.z));
            current = current.previous;
        }
        return new ArrayList<BlockPos>(tempNodes);
    }
    
    public Block getBlock() {
        return PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(this.x, this.y, this.z)).func_177230_c();
    }
    
    public BlockPos getPos() {
        return new BlockPos(this.x, this.y, this.z);
    }
}
