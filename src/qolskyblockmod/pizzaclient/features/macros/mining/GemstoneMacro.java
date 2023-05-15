// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.mining;

import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.GemstonePath;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.AdvancedMiningBot;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.skills.WorldScanner;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

public class GemstoneMacro extends Macro
{
    public static BlockPos getBestGemstoneVein() {
        double bestCost = 9.9999999E7;
        BlockPos bestPos = null;
        for (final BlockPos pos : WorldScanner.markedBlocks.keySet()) {
            int count = 0;
            for (final BlockPos adjacent : BlockPos.func_177980_a(new BlockPos(pos.func_177958_n() - 4, pos.func_177956_o() - 3, pos.func_177952_p() - 4), new BlockPos(pos.func_177958_n() + 4, pos.func_177956_o() + 4, pos.func_177952_p()))) {
                if (PizzaClient.mc.field_71441_e.func_180495_p(adjacent).func_177230_c() == Blocks.field_150399_cn) {
                    ++count;
                }
            }
            final double cost = AdvancedMiningBot.getEstimatedCost(pos, count);
            if (cost < bestCost) {
                bestCost = cost;
                bestPos = pos;
            }
        }
        return bestPos;
    }
    
    public static void runGemstonePathfinder() {
        final GemstonePath path;
        final AStarPathfinder aStarPathfinder;
        new Thread(() -> {
            // new(qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder.class)
            new GemstonePath(new BetterBlockPos(getBestGemstoneVein()));
            new AStarPathfinder(path);
            aStarPathfinder.run(true);
        }).start();
    }
    
    @Override
    public void onTick() {
        if (PizzaClient.location != Locations.CHOLLOWS) {
            return;
        }
        if (!(BasePathfinder.path instanceof GemstonePath)) {
            runGemstonePathfinder();
        }
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        this.addToggleMessage("Gemstone Macro");
    }
    
    @Override
    public boolean applyFailsafes() {
        return true;
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return true;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return false;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return PizzaClient.config.stopWhenNearPlayer;
    }
}
