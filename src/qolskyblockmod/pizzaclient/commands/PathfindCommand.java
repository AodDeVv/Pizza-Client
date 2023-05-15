// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.GemstonePath;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import qolskyblockmod.pizzaclient.features.player.TPAuraHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;

public class PathfindCommand extends CommandBase
{
    public String func_71517_b() {
        return "pathfind";
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return null;
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        if (args.length < 3) {
            return;
        }
        final int x = Integer.parseInt(args[0]);
        final int y = Integer.parseInt(args[1]);
        final int z = Integer.parseInt(args[2]);
        if (args.length == 4) {
            TPAuraHelper.update();
            TPAuraHelper.runPathfinder(new BetterBlockPos(x, y, z));
            return;
        }
        if (args.length == 3) {
            final PathBase path;
            final int x2;
            final int y2;
            final int z2;
            final AStarPathfinder aStarPathfinder;
            new Thread(() -> {
                // new(qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder.class)
                new GemstonePath(new BetterBlockPos(x2, y2, z2));
                new AStarPathfinder(path);
                aStarPathfinder.run(true);
            }).start();
        }
    }
}
