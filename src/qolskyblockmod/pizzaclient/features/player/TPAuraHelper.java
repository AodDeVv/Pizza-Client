// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.Pathfinding;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.NoMovementPathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import java.util.List;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.TPAuraPath;

public class TPAuraHelper
{
    public static final int FLY_DURATION = 1300;
    public static TPAuraPath path;
    private static long lastFlyDisable;
    
    public static boolean isFlyActive() {
        return System.currentTimeMillis() - TPAuraHelper.lastFlyDisable < 1300L;
    }
    
    public static void update() {
        TPAuraHelper.lastFlyDisable = System.currentTimeMillis();
    }
    
    public static void teleport() {
        if (TPAuraHelper.path.moves.size() == 0) {
            TPAuraHelper.path.onEnd();
            disable();
            return;
        }
        final List<BlockPos> subList = TPAuraHelper.path.moves.subList(0, MathUtil.min(TPAuraHelper.path.moves.size(), TPAuraHelper.path.getSpeed()));
        final BlockPos pos = subList.get(subList.size() - 1);
        PizzaClient.mc.field_71439_g.func_70107_b(pos.func_177958_n() + 0.5, (double)pos.func_177956_o(), pos.func_177952_p() + 0.5);
        subList.clear();
    }
    
    public static void runPathfinder(final BetterBlockPos pos) {
        final NoMovementPathfinder noMovementPathfinder;
        new Thread(() -> {
            new NoMovementPathfinder(new TPAuraPath(Utils.getClosestInRange(pos)));
            TPAuraHelper.path = (TPAuraPath)noMovementPathfinder.calculateAndGetPath();
        }).start();
    }
    
    public static void runPathfinder(final BetterBlockPos pos, final Runnable runnable) {
        final NoMovementPathfinder noMovementPathfinder;
        new Thread(() -> {
            new NoMovementPathfinder(new TPAuraPath(Utils.getClosestInRange(pos)).setRunnable(runnable));
            TPAuraHelper.path = (TPAuraPath)noMovementPathfinder.calculateAndGetPath();
        }).start();
    }
    
    public static void disable() {
        Pathfinding.unregister();
        BasePathfinder.path = null;
        TPAuraHelper.path = null;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        final double n = 0.0;
        field_71439_g2.field_70179_y = n;
        field_71439_g.field_70159_w = n;
    }
}
