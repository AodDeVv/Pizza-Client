// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder;

import qolskyblockmod.pizzaclient.features.macros.pathfinding.Pathfinding;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;

public abstract class BasePathfinder
{
    public static PathBase path;
    
    public BasePathfinder(final PathBase path) {
        BasePathfinder.path = path;
        MinecraftForge.EVENT_BUS.unregister((Object)Pathfinding.instance);
    }
    
    public void run() {
        this.run(true);
    }
    
    public void runNewThread() {
        new Thread(this::run).start();
    }
    
    public void runNewThread(final boolean messages) {
        new Thread(() -> this.run(messages)).start();
    }
    
    public void shutdown() {
        BasePathfinder.path = null;
        Pathfinding.unregister();
    }
    
    public static void clearCache() {
        BasePathfinder.path.checked.clear();
        BasePathfinder.path.openSet.clear();
    }
    
    public abstract boolean run(final boolean p0);
}
