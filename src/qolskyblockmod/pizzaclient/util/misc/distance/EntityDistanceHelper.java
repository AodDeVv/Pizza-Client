// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.distance;

import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class EntityDistanceHelper
{
    private final Vec3 pos;
    private double bestDist;
    public Entity closest;
    
    public EntityDistanceHelper() {
        this.pos = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        this.bestDist = 9.99999999E8;
    }
    
    public EntityDistanceHelper(final Vec3 pos) {
        this.pos = pos;
    }
    
    public void compare(final Entity entity) {
        final double dist = this.pos.func_72436_e(new Vec3(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v));
        if (dist < this.bestDist) {
            this.bestDist = dist;
            this.closest = entity;
        }
    }
    
    public boolean isNotNull() {
        return this.closest != null;
    }
}
