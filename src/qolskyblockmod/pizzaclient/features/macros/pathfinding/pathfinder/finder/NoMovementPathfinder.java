// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder;

import net.minecraft.util.BlockPos;
import java.util.List;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.PathNodeOpenSet;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves.IMovement;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.Pathfinding;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;

public class NoMovementPathfinder extends BasePathfinder
{
    public NoMovementPathfinder(final PathBase path) {
        super(path);
    }
    
    @Override
    public boolean run(final boolean messages) {
        final long test = System.currentTimeMillis();
        if (NoMovementPathfinder.path == null) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Path returned null. Please report this."));
            return false;
        }
        if (NoMovementPathfinder.path.currentPos.equals(NoMovementPathfinder.path.goalPos)) {
            this.shutdown();
            return true;
        }
        final IMovement[] directions = NoMovementPathfinder.path.getDirections();
        final PathNodeOpenSet openSet = NoMovementPathfinder.path.openSet;
        openSet.insert(new PathNode());
        try {
            PathNode best = null;
            while (openSet.size != 0) {
                final PathNode currentNode = openSet.removeLowest();
                if (NoMovementPathfinder.path.goalPos.equals(currentNode)) {
                    best = currentNode;
                    break;
                }
                NoMovementPathfinder.path.checkCanGoUp(currentNode);
                for (final IMovement movement : directions) {
                    if (movement.addBlock(currentNode)) {
                        PathNode neighbor = new PathNode(NoMovementPathfinder.path.mutableNode.field_177962_a, NoMovementPathfinder.path.mutableNode.field_177960_b, NoMovementPathfinder.path.mutableNode.field_177961_c);
                        final int hash = neighbor.hashCode();
                        final PathNode existing = NoMovementPathfinder.path.checked.get(hash);
                        final double tentativeCost = currentNode.cost + NoMovementPathfinder.path.mutableNode.cost;
                        if (existing != null) {
                            if (existing.heapPosition != -1) {
                                neighbor = existing;
                                if (neighbor.cost > tentativeCost) {
                                    neighbor.previous = currentNode;
                                    neighbor.cost = tentativeCost;
                                    neighbor.combinedCost = tentativeCost + neighbor.estimatedCostToGoal;
                                    openSet.update(neighbor);
                                }
                            }
                        }
                        else {
                            NoMovementPathfinder.path.checked.put(hash, neighbor);
                            neighbor.previous = currentNode;
                            neighbor.cost = tentativeCost;
                            neighbor.combinedCost = tentativeCost + neighbor.estimatedCostToGoal;
                            openSet.insert(neighbor);
                        }
                    }
                }
            }
            if (best == null) {
                if (messages) {
                    PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "Failed to find a path!"));
                }
                return false;
            }
            clearCache();
            if (messages) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Path! " + (System.currentTimeMillis() - test)));
            }
            Pathfinding.register();
            NoMovementPathfinder.path.moves = best.calculateMoves();
            NoMovementPathfinder.path.updatePathfindTime();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "PizzaClient caught an logged an exception when calculating the path. Please report this."));
            this.shutdown();
            return false;
        }
    }
    
    public PathBase calculateAndGetPath() {
        if (this.run(true)) {
            return NoMovementPathfinder.path;
        }
        return null;
    }
    
    public PathBase calculateAndGetPath(final boolean messages) {
        if (this.run(messages)) {
            return NoMovementPathfinder.path;
        }
        return null;
    }
    
    public List<BlockPos> calculateAndGetMoves() {
        if (this.run(true)) {
            return NoMovementPathfinder.path.moves;
        }
        return null;
    }
    
    public List<BlockPos> calculateAndGetMoves(final boolean messages) {
        if (this.run(messages)) {
            return NoMovementPathfinder.path.moves;
        }
        return null;
    }
}
