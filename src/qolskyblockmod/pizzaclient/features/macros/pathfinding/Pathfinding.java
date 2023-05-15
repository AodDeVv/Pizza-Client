// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding;

import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.Path;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class Pathfinding
{
    public static final Pathfinding instance;
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (BasePathfinder.path == null) {
            unregister();
            return;
        }
        if (BasePathfinder.path.moves.isEmpty()) {
            return;
        }
        try {
            RenderUtil.drawRainbowPath(BasePathfinder.path.moves, 2.0f);
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("caught exception"));
        }
    }
    
    public static void runAStarPathfinder(final BetterBlockPos goal) {
        final AStarPathfinder aStarPathfinder;
        new Thread(() -> {
            new AStarPathfinder(new Path(goal));
            aStarPathfinder.run();
        }).start();
    }
    
    public static void unregister() {
        MinecraftForge.EVENT_BUS.unregister((Object)Pathfinding.instance);
    }
    
    public static void register() {
        MinecraftForge.EVENT_BUS.register((Object)Pathfinding.instance);
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (BasePathfinder.path == null) {
            unregister();
            return;
        }
        if (BasePathfinder.path.shouldRecalcPath()) {
            BasePathfinder.path.recalculateMoves();
            BasePathfinder.path.updatePathfindTime();
        }
        if (BasePathfinder.path.moves.isEmpty()) {
            BasePathfinder.path.onEndMove();
            BasePathfinder.path.shutdown();
            this.shutdown();
            return;
        }
        if (PizzaClient.mc.field_71462_r != null) {
            Movement.disableMovement();
            if (PizzaClient.tickTask != null) {
                final long lastInteractTime = System.currentTimeMillis();
                EntityPlayerSP field_71439_g;
                final ChatComponentText chatComponentText;
                PizzaClient.tickTask = (() -> {
                    if (System.currentTimeMillis() - lastInteractTime >= 700L) {
                        field_71439_g = PizzaClient.mc.field_71439_g;
                        new ChatComponentText(Utils.SUCCESS_MESSAGE + "Somehow opened gui, closing it.");
                        field_71439_g.func_145747_a((IChatComponent)chatComponentText);
                        PlayerUtil.closeScreen();
                        PizzaClient.tickTask = null;
                    }
                });
            }
            return;
        }
        try {
            BasePathfinder.path.move();
        }
        catch (Exception e) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Caught an exception when moving. Please report this."));
            e.printStackTrace();
            Movement.disableMovement();
        }
    }
    
    public void shutdown() {
        BasePathfinder.path = null;
        unregister();
        Movement.disableMovement();
    }
    
    static {
        instance = new Pathfinding();
    }
}
