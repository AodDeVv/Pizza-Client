// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base;

import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.Pathfinding;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves.Moves;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves.IMovement;

public interface IPath
{
    void move();
    
    default IMovement[] getDirections() {
        return Moves.values();
    }
    
    default void onBeginMove() {
    }
    
    default void onEndMove() {
    }
    
    default void shutdown() {
        Movement.disableMovement();
        AStarPathfinder.path = null;
        Pathfinding.unregister();
        PizzaClient.rotater = null;
    }
    
    default void clearLists() {
    }
}
