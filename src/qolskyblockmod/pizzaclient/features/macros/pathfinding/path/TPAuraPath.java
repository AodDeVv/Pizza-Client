// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.path;

import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves.FlyMoves;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves.IMovement;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;

public class TPAuraPath extends PathBase
{
    private Runnable runnable;
    
    public TPAuraPath(final Vec3 from, final Vec3 to) {
        super(from, to);
    }
    
    public TPAuraPath(final BetterBlockPos from, final BetterBlockPos to) {
        super(from, to);
    }
    
    public TPAuraPath(final BetterBlockPos to) {
        super(to);
    }
    
    public TPAuraPath(final Vec3 to) {
        super(to);
    }
    
    public TPAuraPath setRunnable(final Runnable runnable) {
        this.runnable = runnable;
        return this;
    }
    
    public void onEnd() {
        if (this.runnable != null) {
            this.runnable.run();
        }
    }
    
    public int getSpeed() {
        return 3;
    }
    
    @Override
    public IMovement[] getDirections() {
        return FlyMoves.values();
    }
    
    @Override
    public void move() {
    }
}
