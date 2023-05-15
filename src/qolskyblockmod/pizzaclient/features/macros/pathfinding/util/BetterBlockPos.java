// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.util;

import net.minecraft.util.Vec3i;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.util.BlockPos;

public class BetterBlockPos extends BlockPos
{
    public int field_177962_a;
    public int field_177960_b;
    public int field_177961_c;
    
    public BetterBlockPos(final int x, final int y, final int z) {
        super(0, 0, 0);
        this.field_177962_a = x;
        this.field_177960_b = y;
        this.field_177961_c = z;
    }
    
    public BetterBlockPos(final double x, final double y, final double z) {
        super(0, 0, 0);
        this.field_177962_a = MathUtil.floor(x);
        this.field_177960_b = MathUtil.floor(y);
        this.field_177961_c = MathUtil.floor(z);
    }
    
    public BetterBlockPos(final Vec3 vec) {
        super(0, 0, 0);
        this.field_177962_a = MathUtil.floor(vec.field_72450_a);
        this.field_177960_b = MathUtil.floor(vec.field_72448_b);
        this.field_177961_c = MathUtil.floor(vec.field_72449_c);
    }
    
    public BetterBlockPos(final BlockPos pos) {
        super(0, 0, 0);
        this.field_177962_a = pos.func_177958_n();
        this.field_177960_b = pos.func_177956_o();
        this.field_177961_c = pos.func_177952_p();
    }
    
    public static BetterBlockPos getPlayer() {
        return new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
    }
    
    public BetterBlockPos copyOf() {
        return new BetterBlockPos(this.field_177962_a, this.field_177960_b, this.field_177961_c);
    }
    
    public static BetterBlockPos copyOf(final BlockPos pos) {
        return new BetterBlockPos(pos);
    }
    
    public boolean isSameXandZ(final BetterBlockPos pos) {
        return pos.field_177962_a == this.field_177962_a && pos.field_177961_c == this.field_177961_c;
    }
    
    public boolean isSameXandZ(final BlockPos pos) {
        return pos.func_177958_n() == this.field_177962_a && pos.func_177952_p() == this.field_177961_c;
    }
    
    public Vec3 toVec() {
        return new Vec3(this.field_177962_a + 0.5, this.field_177960_b + 0.5, this.field_177961_c + 0.5);
    }
    
    public Vec3 toRawVec() {
        return new Vec3((double)this.field_177962_a, (double)this.field_177960_b, (double)this.field_177961_c);
    }
    
    public boolean equals(final BetterBlockPos pos) {
        return pos.field_177962_a == this.field_177962_a && pos.field_177960_b == this.field_177960_b && pos.field_177961_c == this.field_177961_c;
    }
    
    public boolean equals(final BlockPos pos) {
        return pos.func_177958_n() == this.field_177962_a && pos.func_177956_o() == this.field_177960_b && pos.func_177952_p() == this.field_177961_c;
    }
    
    public boolean equals(final PathNode node) {
        return node.x == this.field_177962_a && node.y == this.field_177960_b && node.z == this.field_177961_c;
    }
    
    public double distanceTo(final BetterBlockPos pos) {
        final double d0 = pos.field_177962_a - this.field_177962_a;
        final double d2 = pos.field_177960_b - this.field_177960_b;
        final double d3 = pos.field_177961_c - this.field_177961_c;
        return Math.sqrt(d0 * d0 + d2 * d2 + d3 * d3);
    }
    
    public double distanceToSq(final BetterBlockPos pos) {
        final double d0 = pos.field_177962_a - this.field_177962_a;
        final double d2 = pos.field_177960_b - this.field_177960_b;
        final double d3 = pos.field_177961_c - this.field_177961_c;
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public double distanceTo(final Vec3 pos) {
        final double d0 = pos.field_72450_a - this.field_177962_a;
        final double d2 = pos.field_72448_b - this.field_177960_b;
        final double d3 = pos.field_72449_c - this.field_177961_c;
        return Math.sqrt(d0 * d0 + d2 * d2 + d3 * d3);
    }
    
    public double distanceToSq(final Vec3 pos) {
        final double d0 = pos.field_72450_a - this.field_177962_a;
        final double d2 = pos.field_72448_b - this.field_177960_b;
        final double d3 = pos.field_72449_c - this.field_177961_c;
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public double getXDistance(final double x) {
        return MathUtil.abs(x - this.field_177962_a);
    }
    
    public double getYDistance(final double y) {
        return MathUtil.abs(y - this.field_177960_b);
    }
    
    public double getZDistance(final double z) {
        return MathUtil.abs(z - this.field_177961_c);
    }
    
    public double getXZDistance(final double x, final double z) {
        final double diffX = x - this.field_177962_a;
        final double diffZ = z - this.field_177961_c;
        return Math.sqrt(diffX * diffX + diffZ * diffZ);
    }
    
    public double getCost(final BetterBlockPos pos) {
        return MathUtil.abs(pos.field_177962_a - this.field_177962_a) + MathUtil.abs(pos.field_177960_b - this.field_177960_b) + MathUtil.abs(pos.field_177961_c - this.field_177961_c);
    }
    
    public double getXZDistanceSq(final double x, final double z) {
        final double diffX = x - this.field_177962_a;
        final double diffZ = z - this.field_177961_c;
        return diffX * diffX + diffZ * diffZ;
    }
    
    public boolean isInsideAABB(final AxisAlignedBB aabb) {
        return this.field_177962_a > aabb.field_72340_a && this.field_177962_a < aabb.field_72336_d && this.field_177960_b > aabb.field_72338_b && this.field_177960_b < aabb.field_72337_e && this.field_177961_c > aabb.field_72339_c && this.field_177961_c < aabb.field_72334_f;
    }
    
    public boolean isBlockLoaded() {
        return !PizzaClient.mc.field_71441_e.func_72863_F().func_73154_d(this.field_177962_a >> 4, this.field_177961_c >> 4).func_76621_g();
    }
    
    public Block getBlock() {
        return PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)this).func_177230_c();
    }
    
    public IBlockState getBlockState() {
        return PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)this);
    }
    
    public Vec3 getPositionEyes() {
        return new Vec3(this.field_177962_a + 0.5, (double)(this.field_177960_b + 1.62f), this.field_177961_c + 0.5);
    }
    
    public BetterBlockPos up() {
        return new BetterBlockPos(this.field_177962_a, this.field_177960_b + 1, this.field_177961_c);
    }
    
    public BetterBlockPos up(final int amt) {
        return (amt == 0) ? this : new BetterBlockPos(this.field_177962_a, this.field_177960_b + amt, this.field_177961_c);
    }
    
    public BetterBlockPos down() {
        return new BetterBlockPos(this.field_177962_a, this.field_177960_b - 1, this.field_177961_c);
    }
    
    public BetterBlockPos down(final int amt) {
        return (amt == 0) ? this : new BetterBlockPos(this.field_177962_a, this.field_177960_b - amt, this.field_177961_c);
    }
    
    public BetterBlockPos offset(final EnumFacing dir) {
        final Vec3i vec = dir.func_176730_m();
        return new BetterBlockPos(this.field_177962_a + dir.func_82601_c(), this.field_177960_b + vec.func_177956_o(), this.field_177961_c + vec.func_177952_p());
    }
    
    public BetterBlockPos offset(final EnumFacing dir, final int dist) {
        if (dist == 0) {
            return this;
        }
        final Vec3i vec = dir.func_176730_m();
        return new BetterBlockPos(this.field_177962_a + vec.func_177958_n() * dist, this.field_177960_b + vec.func_177956_o() * dist, this.field_177961_c + vec.func_177952_p() * dist);
    }
    
    public BetterBlockPos north() {
        return new BetterBlockPos(this.field_177962_a, this.field_177960_b, this.field_177961_c - 1);
    }
    
    public BetterBlockPos north(final int amt) {
        if (amt == 0) {
            return this;
        }
        return new BetterBlockPos(this.field_177962_a, this.field_177960_b, this.field_177961_c - amt);
    }
    
    public BetterBlockPos south() {
        return new BetterBlockPos(this.field_177962_a, this.field_177960_b, this.field_177961_c + 1);
    }
    
    public BetterBlockPos south(final int amt) {
        if (amt == 0) {
            return this;
        }
        return new BetterBlockPos(this.field_177962_a, this.field_177960_b, this.field_177961_c + amt);
    }
    
    public BetterBlockPos east() {
        return new BetterBlockPos(this.field_177962_a + 1, this.field_177960_b, this.field_177961_c);
    }
    
    public BetterBlockPos east(final int amt) {
        if (amt == 0) {
            return this;
        }
        return new BetterBlockPos(this.field_177962_a + amt, this.field_177960_b, this.field_177961_c);
    }
    
    public BetterBlockPos west() {
        return new BetterBlockPos(this.field_177962_a - 1, this.field_177960_b, this.field_177961_c);
    }
    
    public BetterBlockPos west(final int amt) {
        if (amt == 0) {
            return this;
        }
        return new BetterBlockPos(this.field_177962_a - amt, this.field_177960_b, this.field_177961_c);
    }
    
    public BetterBlockPos add(final int x, final int y, final int z) {
        return new BetterBlockPos(this.field_177962_a + x, this.field_177960_b + y, this.field_177961_c + z);
    }
    
    public BetterBlockPos add(final Vec3 vecIn) {
        return new BetterBlockPos(this.field_177962_a + vecIn.field_72450_a, this.field_177960_b + vecIn.field_72448_b, this.field_177961_c + vecIn.field_72449_c);
    }
    
    public String toString() {
        return "{x=" + this.field_177962_a + ", y=" + this.field_177960_b + ", z=" + this.field_177961_c + "}";
    }
    
    public int hashCode() {
        return (int)(482263L * (751913L * this.field_177962_a + this.field_177960_b) + this.field_177961_c);
    }
    
    public static int longHash(final int x, final int y, final int z) {
        return (int)(482263L * (751913L * x + y) + z);
    }
    
    public int func_177958_n() {
        return this.field_177962_a;
    }
    
    public int func_177956_o() {
        return this.field_177960_b;
    }
    
    public int func_177952_p() {
        return this.field_177961_c;
    }
}
