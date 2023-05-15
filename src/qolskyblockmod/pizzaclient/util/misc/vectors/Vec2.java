// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.vectors;

import net.minecraft.util.Vec3;

public class Vec2
{
    public double x;
    public double y;
    
    public Vec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public double distanceTo(final Vec2 vec) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.y - this.y;
        return Math.sqrt(d0 * d0 + d2 * d2);
    }
    
    public double squareDistanceTo(final Vec2 vec) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.y - this.y;
        return d0 * d0 + d2 * d2;
    }
    
    public void scale(final float scale) {
        this.x *= scale;
        this.y *= scale;
    }
    
    public void scale(final double scale) {
        this.x *= scale;
        this.y *= scale;
    }
    
    public static Vec2 getXYVec(final Vec3 vecIn) {
        return new Vec2(vecIn.field_72450_a, vecIn.field_72448_b);
    }
    
    public static Vec2 getXZVec(final Vec3 vecIn) {
        return new Vec2(vecIn.field_72450_a, vecIn.field_72449_c);
    }
}
