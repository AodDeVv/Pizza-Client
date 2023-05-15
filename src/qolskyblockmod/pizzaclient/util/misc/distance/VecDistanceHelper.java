// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.distance;

import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.Entity;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.Vec3;

public class VecDistanceHelper
{
    private final Vec3 pos;
    private double bestDist;
    public Vec3 closest;
    
    public VecDistanceHelper() {
        this.bestDist = 9.99999999E8;
        this.pos = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
    }
    
    public VecDistanceHelper(final Vec3 pos) {
        this.bestDist = 9.99999999E8;
        this.pos = pos;
    }
    
    public void compare(final Vec3 vecIn) {
        final double dist = this.pos.func_72436_e(vecIn);
        if (dist < this.bestDist) {
            this.bestDist = dist;
            this.closest = vecIn;
        }
    }
    
    public void compare(final Entity entity) {
        final Vec3 vecIn = new Vec3(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
        final double dist = this.pos.func_72436_e(vecIn);
        if (dist < this.bestDist) {
            this.bestDist = dist;
            this.closest = vecIn;
        }
    }
    
    public void compare(final MovingObjectPosition mop) {
        final double dist = this.pos.func_72436_e(mop.field_72307_f);
        if (dist < this.bestDist) {
            this.bestDist = dist;
            this.closest = mop.field_72307_f;
        }
    }
    
    public void compare(final BlockPos block) {
        final Vec3 vecIn = new Vec3(block.func_177958_n() + 0.5, block.func_177956_o() + 0.5, block.func_177952_p() + 0.5);
        final double dist = this.pos.func_72436_e(vecIn);
        if (dist < this.bestDist) {
            this.bestDist = dist;
            this.closest = vecIn;
        }
    }
    
    public boolean isNotNull() {
        return this.closest != null;
    }
}
