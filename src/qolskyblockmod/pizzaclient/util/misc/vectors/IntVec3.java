// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.vectors;

import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import qolskyblockmod.pizzaclient.util.Utils;

public class IntVec3
{
    public final int x;
    public final int y;
    public final int z;
    
    public IntVec3(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public IntVec3(final double x, final double y, final double z) {
        this.x = (int)x;
        this.y = (int)y;
        this.z = (int)z;
    }
    
    public boolean isBlockLoaded() {
        return this.x >= -30000000 && this.z >= -30000000 && this.x < 30000000 && this.z < 30000000 && this.y >= 0 && this.y < 256 && Utils.isChunkLoaded(this.x >> 4, this.z >> 4);
    }
    
    public double distanceToSq(final BetterBlockPos pos) {
        final double d0 = this.x - pos.field_177962_a;
        final double d2 = this.y - pos.field_177960_b;
        final double d3 = this.z - pos.field_177961_c;
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    @Override
    public int hashCode() {
        return (int)(482263L * (751913L * this.x + this.y) + this.z);
    }
    
    @Override
    public boolean equals(final Object obj) {
        final IntVec3 other = (IntVec3)obj;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
    
    public boolean equals(final IntVec3 other) {
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
}
