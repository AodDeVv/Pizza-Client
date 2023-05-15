// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.path;

import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
import java.util.Collection;
import net.minecraft.util.BlockPos;
import java.util.ArrayList;
import java.util.Collections;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.util.misc.vectors.IntVec3;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;

public final class Path extends PathBase
{
    public double lastUnloadedChunkDistance;
    public IntVec3 unloaded;
    
    public Path(final Vec3 from, final Vec3 to) {
        super(from, to);
        this.lastUnloadedChunkDistance = 9.99999999E8;
    }
    
    public Path(final BetterBlockPos from, final BetterBlockPos to) {
        super(from, to);
        this.lastUnloadedChunkDistance = 9.99999999E8;
    }
    
    public Path(final BetterBlockPos to) {
        super(to);
        this.lastUnloadedChunkDistance = 9.99999999E8;
    }
    
    public Path(final Vec3 to) {
        super(to);
        this.lastUnloadedChunkDistance = 9.99999999E8;
    }
    
    @Override
    public void update() {
        this.currentPos = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        this.checked.clear();
        this.moves = new ArrayList<BlockPos>(Collections.singletonList(new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v)));
        this.unloaded = null;
    }
    
    @Override
    public void update(final BetterBlockPos goalPos) {
        this.currentPos = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        this.goalPos = goalPos;
        this.checked.clear();
        this.moves = new ArrayList<BlockPos>(Collections.singletonList(new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v)));
        this.unloaded = null;
    }
    
    public void moveUntilLoaded() {
        this.onBeginMove();
        while (!this.unloaded.isBlockLoaded()) {
            this.moveTo();
        }
        this.onEndMove();
        Movement.disableMovement();
        Utils.sleep(50);
        this.update();
    }
    
    public void moveTo() {
        this.useDefaultMovement();
    }
    
    @Override
    public void move() {
        this.moveTo();
    }
}
