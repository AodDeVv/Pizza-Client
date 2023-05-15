// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder;

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

public class AStarPathfinder extends BasePathfinder
{
    public AStarPathfinder(final PathBase path) {
        super(path);
    }
    
    @Override
    public boolean run(final boolean messages) {
        final long test = System.currentTimeMillis();
        if (AStarPathfinder.path == null) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Path returned null. Please report this."));
            return false;
        }
        if (AStarPathfinder.path.currentPos.equals(AStarPathfinder.path.goalPos)) {
            this.shutdown();
            return true;
        }
        final IMovement[] directions = AStarPathfinder.path.getDirections();
        final PathNodeOpenSet openSet = AStarPathfinder.path.openSet;
        openSet.insert(new PathNode());
        try {
            PathNode best = null;
            while (openSet.size != 0) {
                final PathNode currentNode = openSet.removeLowest();
                if (AStarPathfinder.path.goalPos.equals(currentNode)) {
                    best = currentNode;
                    break;
                }
                AStarPathfinder.path.checkCanGoUp(currentNode);
                for (final IMovement movement : directions) {
                    if (movement.addBlock(currentNode)) {
                        PathNode neighbor = new PathNode(AStarPathfinder.path.mutableNode.field_177962_a, AStarPathfinder.path.mutableNode.field_177960_b, AStarPathfinder.path.mutableNode.field_177961_c);
                        final int hash = neighbor.hashCode();
                        final PathNode existing = AStarPathfinder.path.checked.get(hash);
                        if (existing != null) {
                            if (existing.heapPosition != -1) {
                                final double tentativeCost = currentNode.cost + AStarPathfinder.path.mutableNode.cost;
                                if (existing.cost > tentativeCost) {
                                    neighbor = existing;
                                    neighbor.previous = currentNode;
                                    neighbor.cost = tentativeCost;
                                    neighbor.combinedCost = tentativeCost + neighbor.estimatedCostToGoal;
                                    openSet.update(neighbor);
                                }
                            }
                        }
                        else {
                            final double tentativeCost = currentNode.cost + AStarPathfinder.path.mutableNode.cost;
                            AStarPathfinder.path.checked.put(hash, neighbor);
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
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Path! Calculating took " + (System.currentTimeMillis() - test) + "ms!"));
            }
            Pathfinding.register();
            AStarPathfinder.path.moves = best.calculateMoves();
            AStarPathfinder.path.onBeginMove();
            AStarPathfinder.path.updatePathfindTime();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "PizzaClient caught an logged an exception when calculating the path. Please report this."));
            this.shutdown();
            return false;
        }
    }
}
