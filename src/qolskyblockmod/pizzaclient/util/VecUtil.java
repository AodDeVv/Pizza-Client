// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Collections;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.finder.BlockFinder;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.util.MathHelper;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.AxisAlignedBB;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class VecUtil
{
    public static Vec3 scaleVec(final Vec3 vec, final float scale) {
        return new Vec3(vec.field_72450_a * scale, vec.field_72448_b * scale, vec.field_72449_c * scale);
    }
    
    public static Vec3 scaleVec(final Vec3 vec, final double scale) {
        return new Vec3(vec.field_72450_a * scale, vec.field_72448_b * scale, vec.field_72449_c * scale);
    }
    
    public static Vec3 divideVec(final Vec3 vec, final float scale) {
        return new Vec3(vec.field_72450_a / scale, vec.field_72448_b / scale, vec.field_72449_c / scale);
    }
    
    public static Vec3 divideVec(final Vec3 vec, final double scale) {
        return new Vec3(vec.field_72450_a / scale, vec.field_72448_b / scale, vec.field_72449_c / scale);
    }
    
    public static Vec3 offsetVec(final Vec3 original, final double offset) {
        return new Vec3(original.field_72450_a + offset, original.field_72448_b + offset, original.field_72449_c + offset);
    }
    
    public static Vec3 offsetVec(final Vec3 original, final double xOffset, final double yOffset, final double zOffset) {
        return new Vec3(original.field_72450_a + xOffset, original.field_72448_b + yOffset, original.field_72449_c + zOffset);
    }
    
    public static Vec3 offsetVec(final Vec3 vecIn, final EnumFacing offset) {
        final Vec3i dir = offset.func_176730_m();
        return new Vec3(vecIn.field_72450_a + dir.func_177958_n(), vecIn.field_72448_b + dir.func_177956_o(), vecIn.field_72449_c + dir.func_177952_p());
    }
    
    public static Vec3 offsetVec(final Vec3 vecIn, final EnumFacing offset, final int amt) {
        if (amt == 0) {
            return vecIn;
        }
        final Vec3i dir = offset.func_176730_m();
        return new Vec3(vecIn.field_72450_a + dir.func_177958_n() * amt, vecIn.field_72448_b + dir.func_177956_o() * amt, vecIn.field_72449_c + dir.func_177952_p() * amt);
    }
    
    public static Vec3 offsetVecFloat(final Vec3 vecIn, final EnumFacing offset, final float amt) {
        if (amt == 0.0f) {
            return vecIn;
        }
        final Vec3i dir = offset.func_176730_m();
        return new Vec3(vecIn.field_72450_a + dir.func_177958_n() * amt, vecIn.field_72448_b + dir.func_177956_o() * amt, vecIn.field_72449_c + dir.func_177952_p() * amt);
    }
    
    public static Vec3 offsetVecDouble(final Vec3 vecIn, final EnumFacing offset, final double amt) {
        if (amt == 0.0) {
            return vecIn;
        }
        final Vec3i dir = offset.func_176730_m();
        return new Vec3(vecIn.field_72450_a + dir.func_177958_n() * amt, vecIn.field_72448_b + dir.func_177956_o() * amt, vecIn.field_72449_c + dir.func_177952_p() * amt);
    }
    
    public static Vec3 toVec(final BlockPos pos) {
        return new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
    }
    
    public static Vec3 toRawVec(final BlockPos pos) {
        return new Vec3((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p());
    }
    
    public static MovingObjectPosition rayTrace(final float range) {
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 vec4 = PlayerUtil.getLook();
        return fastRayTrace(vec3, vec3.func_72441_c(vec4.field_72450_a * range, vec4.field_72448_b * range, vec4.field_72449_c * range));
    }
    
    public static MovingObjectPosition rayTrace() {
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 vec4 = PlayerUtil.getLook();
        return fastRayTrace(vec3, vec3.func_72441_c(vec4.field_72450_a * 4.5, vec4.field_72448_b * 4.5, vec4.field_72449_c * 4.5));
    }
    
    public static MovingObjectPosition rayTraceStopLiquid(final float range) {
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 vec4 = PlayerUtil.getLook();
        return fastRayTrace(vec3, vec3.func_72441_c(vec4.field_72450_a * range, vec4.field_72448_b * range, vec4.field_72449_c * range), false);
    }
    
    public static MovingObjectPosition rayTraceLook(final Vec3 target, final float range) {
        final Vec3 pos = PlayerUtil.getPositionEyes();
        if (pos.func_72436_e(target) > range * range) {
            return null;
        }
        return fastRayTrace(pos, target);
    }
    
    public static MovingObjectPosition rayTraceLookStopLiquid(final Vec3 target, final float range) {
        final Vec3 pos = PlayerUtil.getPositionEyes();
        if (pos.func_72436_e(target) > range * range) {
            return null;
        }
        return fastRayTrace(pos, target, false);
    }
    
    public static MovingObjectPosition rayTraceLook(final Vec3 position, final Vec3 target, final float range) {
        if (position.func_72436_e(target) > range * range) {
            return null;
        }
        return fastRayTrace(position, target);
    }
    
    public static MovingObjectPosition rayTraceLook(final Vec3 position, final Vec3 target) {
        return fastRayTrace(position, target);
    }
    
    public static MovingObjectPosition rayTraceLook(final Vec3 target) {
        return fastRayTrace(PlayerUtil.getPositionEyes(), target);
    }
    
    public static boolean isRayTraceableLook(final Vec3 target, final BlockPos goal, final float range) {
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        return vec3.func_72436_e(target) <= range * range && rayTraceable(vec3, target, goal);
    }
    
    public static boolean isRayTraceableLook(final BlockPos goal, final float range) {
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 target = Utils.getMiddleOfAABB(goal);
        return vec3.func_72436_e(target) <= range * range && rayTraceable(vec3, target, goal);
    }
    
    public static boolean isRayTraceableLook(final Vec3 position, final Vec3 target, final BlockPos goal, final float range) {
        return position.func_72436_e(target) <= range * range && rayTraceable(position, target, goal);
    }
    
    public static boolean isRayTraceableLook(final Vec3 position, final Vec3 target, final BlockPos goal) {
        return rayTraceable(position, target, goal);
    }
    
    public static boolean isRayTraceableLook(final Vec3 target, final BlockPos goal) {
        return rayTraceable(PlayerUtil.getPositionEyes(), target, goal);
    }
    
    public static MovingObjectPosition rayTraceTopLayer(final BlockPos pos) {
        Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
        MovingObjectPosition position = rayTraceLook(vec, 4.5f);
        if (position != null && position.func_178782_a().equals((Object)pos)) {
            return position;
        }
        for (int x = 1; x < 6; ++x) {
            for (int z = 1; z < 6; ++z) {
                vec = new Vec3(pos.func_177958_n() + x / 5.0f - 0.1, pos.func_177956_o() + 0.99, pos.func_177952_p() + z / 5.0f - 0.1);
                position = rayTraceLook(vec, 4.5f);
                if (position != null && position.func_178782_a().equals((Object)pos)) {
                    return position;
                }
            }
        }
        return null;
    }
    
    public static Vec3 getHittableHitVec(final BlockPos pos) {
        Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
        MovingObjectPosition position = rayTraceLook(vec, 4.5f);
        if (position != null && position.func_178782_a().equals((Object)pos)) {
            return position.field_72307_f;
        }
        for (int x = 1; x < 5; ++x) {
            for (int y = 1; y < 5; ++y) {
                for (int z = 1; z < 5; ++z) {
                    vec = new Vec3(pos.func_177958_n() + x / 4.0f - 0.125, pos.func_177956_o() + y / 4.0f - 0.125, pos.func_177952_p() + z / 4.0f - 0.125);
                    position = rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos)) {
                        return position.field_72307_f;
                    }
                }
            }
        }
        return null;
    }
    
    public static Vec3 getVeryAccurateHittableHitVec(final BlockPos pos) {
        Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
        MovingObjectPosition position = rayTraceLook(vec);
        if (position != null && position.func_178782_a().equals((Object)pos)) {
            return position.field_72307_f;
        }
        for (int x = 1; x < 9; ++x) {
            for (int y = 1; y < 9; ++y) {
                for (int z = 1; z < 9; ++z) {
                    vec = new Vec3(pos.func_177958_n() + x / 8.0f - 0.0625, pos.func_177956_o() + y / 8.0f - 0.0625, pos.func_177952_p() + z / 8.0f - 0.0625);
                    position = rayTraceLook(vec);
                    if (position != null && position.func_178782_a().equals((Object)pos)) {
                        return position.field_72307_f;
                    }
                }
            }
        }
        return null;
    }
    
    public static MovingObjectPosition getHittableMovingObjectPosition(final BlockPos pos) {
        Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
        MovingObjectPosition position = rayTraceLook(vec, 4.5f);
        if (position != null && position.func_178782_a().equals((Object)pos)) {
            return position;
        }
        for (int x = 1; x < 5; ++x) {
            for (int y = 1; y < 5; ++y) {
                for (int z = 1; z < 5; ++z) {
                    vec = new Vec3(pos.func_177958_n() + x / 4.0f - 0.125, pos.func_177956_o() + y / 4.0f - 0.125, pos.func_177952_p() + z / 4.0f - 0.125);
                    position = rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos)) {
                        return position;
                    }
                }
            }
        }
        return null;
    }
    
    public static Vec3 getClosestHittablePosition(final BlockPos pos, final Vec3 goal) {
        double bestDist = 9.9999999E7;
        Vec3 bestHit = null;
        for (int x = 1; x < 5; ++x) {
            for (int y = 1; y < 5; ++y) {
                for (int z = 1; z < 5; ++z) {
                    final Vec3 vec = new Vec3(pos.func_177958_n() + x / 4.0f - 0.125, pos.func_177956_o() + y / 4.0f - 0.125, pos.func_177952_p() + z / 4.0f - 0.125);
                    final MovingObjectPosition position = rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos)) {
                        final double dist = position.field_72307_f.func_72436_e(goal);
                        if (dist < bestDist) {
                            bestDist = dist;
                            bestHit = position.field_72307_f;
                        }
                    }
                }
            }
        }
        return bestHit;
    }
    
    public static Vec3 getClosestHittableToMiddle(final BlockPos pos) {
        final Vec3 middle = new Vec3(pos.func_177958_n() + 0.5 + MathUtil.randomFloat() / 4.0f, pos.func_177956_o() + 0.5 + MathUtil.randomFloat() / 4.0f, pos.func_177952_p() + 0.5 + MathUtil.randomFloat() / 4.0f);
        double bestDist = 9.9999999E7;
        Vec3 bestHit = null;
        for (int x = 1; x < 6; ++x) {
            for (int y = 1; y < 6; ++y) {
                for (int z = 1; z < 6; ++z) {
                    final Vec3 vec = new Vec3((double)(pos.func_177958_n() + x / 5.0f - 0.127538f), (double)(pos.func_177956_o() + y / 5.0f - 0.127538f), (double)(pos.func_177952_p() + z / 5.0f - 0.127538f));
                    final MovingObjectPosition position = rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos)) {
                        final double dist = position.field_72307_f.func_72436_e(middle);
                        if (dist < bestDist) {
                            bestDist = dist;
                            bestHit = position.field_72307_f;
                        }
                    }
                }
            }
        }
        return bestHit;
    }
    
    public static Vec3 getAnyHittableToMiddle(final BlockPos pos) {
        final Vec3 middle = new Vec3(pos.func_177958_n() + 0.5 + MathUtil.randomFloat() / 4.0f, pos.func_177956_o() + 0.5 + MathUtil.randomFloat() / 4.0f, pos.func_177952_p() + 0.5 + MathUtil.randomFloat() / 4.0f);
        double bestDist = 9.9999999E7;
        Vec3 bestHit = null;
        for (int x = 1; x < 6; ++x) {
            for (int y = 1; y < 6; ++y) {
                for (int z = 1; z < 6; ++z) {
                    final Vec3 vec = new Vec3((double)(pos.func_177958_n() + x / 5.0f - 0.127538f), (double)(pos.func_177956_o() + y / 5.0f - 0.127538f), (double)(pos.func_177952_p() + z / 5.0f - 0.127538f));
                    final MovingObjectPosition position = rayTraceLook(vec, 4.5f);
                    if (position != null) {
                        final double dist = position.field_72307_f.func_72436_e(middle);
                        if (dist < bestDist) {
                            bestDist = dist;
                            bestHit = position.field_72307_f;
                        }
                    }
                }
            }
        }
        return bestHit;
    }
    
    public static Vec3 getRandomHittable(final BlockPos pos) {
        final List<Vec3> vecs = new ArrayList<Vec3>();
        for (int x = 1; x < 6; ++x) {
            for (int y = 1; y < 6; ++y) {
                for (int z = 1; z < 6; ++z) {
                    final Vec3 vec = new Vec3((double)(pos.func_177958_n() + x / 5.0f - 0.127538f), (double)(pos.func_177956_o() + y / 5.0f - 0.127538f), (double)(pos.func_177952_p() + z / 5.0f - 0.127538f));
                    final MovingObjectPosition position = rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos)) {
                        vecs.add(position.field_72307_f);
                    }
                }
            }
        }
        return (vecs.size() == 0) ? null : vecs.get(Utils.random.nextInt(vecs.size()));
    }
    
    public static Vec3 getRandomHittable(final BlockPos pos, final AxisAlignedBB aabb) {
        final List<Vec3> vecs = new ArrayList<Vec3>();
        for (int x = 1; x < 6; ++x) {
            for (int y = 1; y < 6; ++y) {
                for (int z = 1; z < 6; ++z) {
                    final Vec3 vec = new Vec3((double)(pos.func_177958_n() + x / 5.0f - 0.127538f), (double)(pos.func_177956_o() + y / 5.0f - 0.127538f), (double)(pos.func_177952_p() + z / 5.0f - 0.127538f));
                    if (aabb.func_72318_a(vec)) {
                        final MovingObjectPosition position = rayTraceLook(vec, 4.5f);
                        if (position != null && position.func_178782_a().equals((Object)pos)) {
                            vecs.add(position.field_72307_f);
                        }
                    }
                }
            }
        }
        return (vecs.size() == 0) ? null : vecs.get(Utils.random.nextInt(vecs.size()));
    }
    
    public static Vec3 getRandomHittableStopLiquid(final BlockPos pos, final AxisAlignedBB aabb) {
        final List<Vec3> vecs = new ArrayList<Vec3>();
        for (int x = 1; x < 6; ++x) {
            for (int y = 1; y < 6; ++y) {
                for (int z = 1; z < 6; ++z) {
                    final Vec3 vec = new Vec3((double)(pos.func_177958_n() + x / 5.0f - 0.127538f), (double)(pos.func_177956_o() + y / 5.0f - 0.127538f), (double)(pos.func_177952_p() + z / 5.0f - 0.127538f));
                    if (aabb.func_72318_a(vec)) {
                        final MovingObjectPosition position = rayTraceLookStopLiquid(vec, 100.0f);
                        if (position != null && position.func_178782_a().equals((Object)pos)) {
                            vecs.add(position.field_72307_f);
                        }
                    }
                }
            }
        }
        return (vecs.size() == 0) ? null : vecs.get(Utils.random.nextInt(vecs.size()));
    }
    
    public static List<Vec3> getAllHittablePosition(final BlockPos pos) {
        final List<Vec3> hittables = new ArrayList<Vec3>();
        for (int x = 1; x < 5; ++x) {
            for (int y = 1; y < 5; ++y) {
                for (int z = 1; z < 5; ++z) {
                    final Vec3 vec = new Vec3((double)(pos.func_177958_n() + x / 5.0f - 0.127538f), (double)(pos.func_177956_o() + y / 5.0f - 0.127538f), (double)(pos.func_177952_p() + z / 5.0f - 0.127538f));
                    final MovingObjectPosition position = rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos)) {
                        hittables.add(vec);
                    }
                }
            }
        }
        return hittables;
    }
    
    public static boolean isHittable(final BlockPos pos) {
        if (isRayTraceableLook(new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5), pos, 4.5f)) {
            return true;
        }
        for (int x = 1; x < 6; ++x) {
            for (int y = 1; y < 6; ++y) {
                for (int z = 1; z < 6; ++z) {
                    if (isRayTraceableLook(new Vec3((double)(pos.func_177958_n() + x / 5.0f - 0.127538f), (double)(pos.func_177956_o() + y / 5.0f - 0.127538f), (double)(pos.func_177952_p() + z / 5.0f - 0.127538f)), pos, 4.5f)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static MovingObjectPosition calculateInterceptAABBLook(final AxisAlignedBB aabb, final Vec3 look) {
        return aabb.func_72327_a(PlayerUtil.getPositionEyes(), look);
    }
    
    public static MovingObjectPosition calculateInterceptAABBLook(final AxisAlignedBB aabb, final Vec3 look, final float range) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        if (position.func_72436_e(look) > range * range) {
            return null;
        }
        return aabb.func_72327_a(position, look);
    }
    
    public static MovingObjectPosition calculateInterceptAABB(final AxisAlignedBB aabb, final float range) {
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 vec4 = PlayerUtil.getLook();
        return aabb.func_72327_a(vec3, vec3.func_72441_c(vec4.field_72450_a * range, vec4.field_72448_b * range, vec4.field_72449_c * range));
    }
    
    public static MovingObjectPosition calculateInterceptAABB(final AxisAlignedBB aabb) {
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 vec4 = PlayerUtil.getLook();
        return aabb.func_72327_a(vec3, vec3.func_72441_c(vec4.field_72450_a * 100.0, vec4.field_72448_b * 100.0, vec4.field_72449_c * 100.0));
    }
    
    public static MovingObjectPosition calculateIntercept(final BlockPos pos, final float range) {
        final AxisAlignedBB aabb = Utils.getBlockAABB(pos);
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 vec4 = PlayerUtil.getLook();
        return aabb.func_72327_a(vec3, vec3.func_72441_c(vec4.field_72450_a * range, vec4.field_72448_b * range, vec4.field_72449_c * range));
    }
    
    public static MovingObjectPosition calculateInterceptLook(final BlockPos pos, final Vec3 look, final float range) {
        final AxisAlignedBB aabb = Utils.getBlockAABB(pos);
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        if (vec3.func_72436_e(look) > range * range) {
            return null;
        }
        return aabb.func_72327_a(vec3, look);
    }
    
    public static MovingObjectPosition calculateInterceptLook(final BlockPos pos, final float range) {
        final AxisAlignedBB aabb = Utils.getBlockAABB(pos);
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 look = Utils.getMiddleOfAABB(aabb);
        if (vec3.func_72436_e(look) > range * range) {
            return null;
        }
        return aabb.func_72327_a(vec3, look);
    }
    
    public static MovingObjectPosition calculateInterceptLook(final Vec3 look, final float range) {
        final AxisAlignedBB aabb = Utils.getBlockAABB(new BlockPos(look));
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        if (vec3.func_72436_e(look) > range * range) {
            return null;
        }
        return aabb.func_72327_a(vec3, look);
    }
    
    public static MovingObjectPosition calculateIntercept(final Vec3 vec, final float range) {
        return calculateInterceptLook(new BlockPos(vec), vec, range);
    }
    
    public static EnumFacing calculateEnumfacingLook(final AxisAlignedBB aabb, final Vec3 look) {
        final MovingObjectPosition position = calculateInterceptAABBLook(aabb, look);
        return (position != null) ? position.field_178784_b : null;
    }
    
    public static EnumFacing calculateEnumfacingLook(final Vec3 look) {
        final MovingObjectPosition position = calculateInterceptAABBLook(Utils.getBlockAABB(new BlockPos(look)), look);
        return (position != null) ? position.field_178784_b : PizzaClient.mc.field_71439_g.func_174811_aO().func_176734_d();
    }
    
    public static EnumFacing calculateEnumfacingLook(final BlockPos look) {
        final MovingObjectPosition position = calculateInterceptAABBLook(Utils.getBlockAABB(look), new Vec3(look.func_177958_n() + 0.5, look.func_177956_o() + 0.5, look.func_177952_p() + 0.5));
        return (position != null) ? position.field_178784_b : PizzaClient.mc.field_71439_g.func_174811_aO().func_176734_d();
    }
    
    public static EnumFacing calculateEnumfacingLook(final BlockPos pos, final Vec3 look) {
        final MovingObjectPosition position = calculateInterceptAABBLook(Utils.getBlockAABB(pos), look);
        return (position != null) ? position.field_178784_b : null;
    }
    
    public static EnumFacing calculateEnumfacing(final AxisAlignedBB aabb) {
        final MovingObjectPosition position = calculateInterceptAABB(aabb);
        return (position != null) ? position.field_178784_b : null;
    }
    
    public static boolean isFacingAABB(final AxisAlignedBB aabb, final float range) {
        return isInterceptable(aabb, range);
    }
    
    public static boolean isXZFacingAABB(final AxisAlignedBB aabb, final float range) {
        return isInterceptable(aabb, range);
    }
    
    public static boolean isFacingAABBLook(final AxisAlignedBB aabb, final Vec3 target, final float range) {
        return isInterceptable(PlayerUtil.getPositionEyes(), target, aabb, range);
    }
    
    public static boolean facingBlock(final BlockPos block, final float range) {
        final AxisAlignedBB aabb = Utils.getBlockAABB(block);
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        final Vec3 vec4 = PlayerUtil.getLook();
        return isInterceptable(vec3, vec3.func_72441_c(vec4.field_72450_a * range, vec4.field_72448_b * range, vec4.field_72449_c * range), aabb);
    }
    
    public static boolean canReachBlock(final BlockPos pos, final float range) {
        final AxisAlignedBB aabb = Utils.getBlockAABB(pos);
        return isInterceptable(PlayerUtil.getPositionEyes(), Utils.getMiddleOfAABB(aabb), aabb, range);
    }
    
    public static boolean canReachBlock(final BlockPos pos) {
        final AxisAlignedBB aabb = Utils.getBlockAABB(pos);
        final Vec3 vec3 = PlayerUtil.getPositionEyes();
        return isInterceptable(vec3, Utils.getMiddleOfAABB(aabb), aabb, 4.5f);
    }
    
    private static MovingObjectPosition fastRayTrace(final Vec3 vec31, final Vec3 vec32) {
        return fastRayTrace(vec31, vec32, true);
    }
    
    private static MovingObjectPosition fastRayTrace(Vec3 vec31, final Vec3 vec32, final boolean hitLiquid) {
        final int i = MathHelper.func_76128_c(vec32.field_72450_a);
        final int j = MathHelper.func_76128_c(vec32.field_72448_b);
        final int k = MathHelper.func_76128_c(vec32.field_72449_c);
        int l = MathHelper.func_76128_c(vec31.field_72450_a);
        int i2 = MathHelper.func_76128_c(vec31.field_72448_b);
        int j2 = MathHelper.func_76128_c(vec31.field_72449_c);
        BlockPos blockpos = new BlockPos(l, i2, j2);
        final IBlockState iblockstate = PizzaClient.mc.field_71441_e.func_180495_p(blockpos);
        final Block block = iblockstate.func_177230_c();
        if (block.func_176209_a(iblockstate, hitLiquid)) {
            final MovingObjectPosition movingobjectposition = block.func_180636_a((World)PizzaClient.mc.field_71441_e, blockpos, vec31, vec32);
            if (movingobjectposition != null) {
                return movingobjectposition;
            }
        }
        int k2 = 200;
        while (k2-- >= 0) {
            if (l == i && i2 == j && j2 == k) {
                return null;
            }
            boolean flag2 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            double d0 = 999.0;
            double d2 = 999.0;
            double d3 = 999.0;
            if (i > l) {
                d0 = l + 1.0;
            }
            else if (i < l) {
                d0 = l + 0.0;
            }
            else {
                flag2 = false;
            }
            if (j > i2) {
                d2 = i2 + 1.0;
            }
            else if (j < i2) {
                d2 = i2 + 0.0;
            }
            else {
                flag3 = false;
            }
            if (k > j2) {
                d3 = j2 + 1.0;
            }
            else if (k < j2) {
                d3 = j2 + 0.0;
            }
            else {
                flag4 = false;
            }
            double d4 = 999.0;
            double d5 = 999.0;
            double d6 = 999.0;
            final double d7 = vec32.field_72450_a - vec31.field_72450_a;
            final double d8 = vec32.field_72448_b - vec31.field_72448_b;
            final double d9 = vec32.field_72449_c - vec31.field_72449_c;
            if (flag2) {
                d4 = (d0 - vec31.field_72450_a) / d7;
            }
            if (flag3) {
                d5 = (d2 - vec31.field_72448_b) / d8;
            }
            if (flag4) {
                d6 = (d3 - vec31.field_72449_c) / d9;
            }
            if (d4 == -0.0) {
                d4 = -1.0E-4;
            }
            if (d5 == -0.0) {
                d5 = -1.0E-4;
            }
            if (d6 == -0.0) {
                d6 = -1.0E-4;
            }
            if (d4 < d5 && d4 < d6) {
                vec31 = new Vec3(d0, vec31.field_72448_b + d8 * d4, vec31.field_72449_c + d9 * d4);
                if (i > l) {
                    l = MathHelper.func_76128_c(vec31.field_72450_a);
                }
                else {
                    l = MathHelper.func_76128_c(vec31.field_72450_a) - 1;
                }
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else if (d5 < d6) {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d5, d2, vec31.field_72449_c + d9 * d5);
                if (j > i2) {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                }
                else {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d6, vec31.field_72448_b + d8 * d6, d3);
                if (k > j2) {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c);
                }
                else {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
            }
            blockpos = new BlockPos(l, i2, j2);
            final IBlockState iblockstate2 = PizzaClient.mc.field_71441_e.func_180495_p(blockpos);
            final Block block2 = iblockstate2.func_177230_c();
            if (!block2.func_176209_a(iblockstate2, hitLiquid)) {
                continue;
            }
            final MovingObjectPosition movingobjectposition2 = block2.func_180636_a((World)PizzaClient.mc.field_71441_e, blockpos, vec31, vec32);
            if (movingobjectposition2 != null) {
                return movingobjectposition2;
            }
        }
        return null;
    }
    
    private static boolean rayTraceable(Vec3 vec31, final Vec3 vec32, final BlockPos goal) {
        final int i = MathHelper.func_76128_c(vec32.field_72450_a);
        final int j = MathHelper.func_76128_c(vec32.field_72448_b);
        final int k = MathHelper.func_76128_c(vec32.field_72449_c);
        int l = MathHelper.func_76128_c(vec31.field_72450_a);
        int i2 = MathHelper.func_76128_c(vec31.field_72448_b);
        int j2 = MathHelper.func_76128_c(vec31.field_72449_c);
        BlockPos blockpos = new BlockPos(l, i2, j2);
        final IBlockState iblockstate = PizzaClient.mc.field_71441_e.func_180495_p(blockpos);
        final Block block = iblockstate.func_177230_c();
        if (block.func_176209_a(iblockstate, false)) {
            final MovingObjectPosition movingobjectposition = block.func_180636_a((World)PizzaClient.mc.field_71441_e, blockpos, vec31, vec32);
            if (movingobjectposition != null) {
                return blockpos == goal || (l == goal.func_177958_n() && i2 == goal.func_177956_o() && j2 == goal.func_177952_p());
            }
        }
        int k2 = 200;
        while (k2-- >= 0) {
            if (l == i && i2 == j && j2 == k) {
                return false;
            }
            boolean flag2 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            double d0 = 999.0;
            double d2 = 999.0;
            double d3 = 999.0;
            if (i > l) {
                d0 = l + 1.0;
            }
            else if (i < l) {
                d0 = l + 0.0;
            }
            else {
                flag2 = false;
            }
            if (j > i2) {
                d2 = i2 + 1.0;
            }
            else if (j < i2) {
                d2 = i2 + 0.0;
            }
            else {
                flag3 = false;
            }
            if (k > j2) {
                d3 = j2 + 1.0;
            }
            else if (k < j2) {
                d3 = j2 + 0.0;
            }
            else {
                flag4 = false;
            }
            double d4 = 999.0;
            double d5 = 999.0;
            double d6 = 999.0;
            final double d7 = vec32.field_72450_a - vec31.field_72450_a;
            final double d8 = vec32.field_72448_b - vec31.field_72448_b;
            final double d9 = vec32.field_72449_c - vec31.field_72449_c;
            if (flag2) {
                d4 = (d0 - vec31.field_72450_a) / d7;
            }
            if (flag3) {
                d5 = (d2 - vec31.field_72448_b) / d8;
            }
            if (flag4) {
                d6 = (d3 - vec31.field_72449_c) / d9;
            }
            if (d4 == -0.0) {
                d4 = -1.0E-4;
            }
            if (d5 == -0.0) {
                d5 = -1.0E-4;
            }
            if (d6 == -0.0) {
                d6 = -1.0E-4;
            }
            EnumFacing enumfacing;
            if (d4 < d5 && d4 < d6) {
                vec31 = new Vec3(d0, vec31.field_72448_b + d8 * d4, vec31.field_72449_c + d9 * d4);
                if (i > l) {
                    enumfacing = EnumFacing.WEST;
                    l = MathHelper.func_76128_c(vec31.field_72450_a);
                }
                else {
                    enumfacing = EnumFacing.EAST;
                    l = MathHelper.func_76128_c(vec31.field_72450_a) - 1;
                }
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else if (d5 < d6) {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d5, d2, vec31.field_72449_c + d9 * d5);
                if (j > i2) {
                    enumfacing = EnumFacing.DOWN;
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                }
                else {
                    enumfacing = EnumFacing.UP;
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d6, vec31.field_72448_b + d8 * d6, d3);
                if (k > j2) {
                    enumfacing = EnumFacing.NORTH;
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c);
                }
                else {
                    enumfacing = EnumFacing.SOUTH;
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
            }
            blockpos = new BlockPos(l, i2, j2);
            final IBlockState iblockstate2 = PizzaClient.mc.field_71441_e.func_180495_p(blockpos);
            final Block block2 = iblockstate2.func_177230_c();
            if (!block2.func_176209_a(iblockstate2, false)) {
                continue;
            }
            final MovingObjectPosition movingobjectposition2 = block2.func_180636_a((World)PizzaClient.mc.field_71441_e, blockpos, vec31, vec32);
            if (movingobjectposition2 != null) {
                return (blockpos == goal || (l == goal.func_177958_n() && i2 == goal.func_177956_o() && j2 == goal.func_177952_p())) && PizzaClient.mc.field_71441_e.func_180495_p(blockpos.func_177972_a(enumfacing)).func_177230_c() == Blocks.field_150350_a;
            }
        }
        return false;
    }
    
    private static boolean rayTraceable(final Vec3 vec31, final Vec3 vec32) {
        return rayTraceable(vec31, vec32, new BlockPos(vec32));
    }
    
    public static boolean isInterceptable(final Vec3 start, final Vec3 goal, final AxisAlignedBB aabb) {
        return isVecInYZ(start.func_72429_b(goal, aabb.field_72340_a), aabb) || isVecInYZ(start.func_72429_b(goal, aabb.field_72336_d), aabb) || isVecInXZ(start.func_72435_c(goal, aabb.field_72338_b), aabb) || isVecInXZ(start.func_72435_c(goal, aabb.field_72337_e), aabb) || isVecInXY(start.func_72434_d(goal, aabb.field_72339_c), aabb) || isVecInXY(start.func_72434_d(goal, aabb.field_72334_f), aabb);
    }
    
    public static boolean isXZInterceptable(final Vec3 start, final Vec3 goal, final AxisAlignedBB aabb) {
        return isVecInZ(start.func_72429_b(goal, aabb.field_72340_a), aabb) || isVecInZ(start.func_72429_b(goal, aabb.field_72336_d), aabb) || isVecInX(start.func_72434_d(goal, aabb.field_72339_c), aabb) || isVecInX(start.func_72434_d(goal, aabb.field_72334_f), aabb) || isVecInXZ(start.func_72435_c(goal, 0.0), aabb) || isVecInXZ(start.func_72435_c(goal, 255.0), aabb);
    }
    
    public static boolean isXZInterceptable(final AxisAlignedBB aabb, final float range) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        final Vec3 look = PlayerUtil.getLook();
        return isXZInterceptable(position, position.func_72441_c(look.field_72450_a * range, look.field_72448_b * range, look.field_72449_c * range), aabb);
    }
    
    public static boolean isFacingXZAABB(final AxisAlignedBB aabb, final float range) {
        return isXZInterceptable(aabb, range);
    }
    
    public static boolean isInterceptable(final Vec3 start, final Vec3 goal, final AxisAlignedBB aabb, final float range) {
        return start.func_72436_e(goal) <= range * range && isInterceptable(start, goal, aabb);
    }
    
    public static boolean isInterceptable(final Vec3 goal, final AxisAlignedBB aabb, final float range) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        return position.func_72436_e(goal) <= range * range && isInterceptable(position, goal, aabb);
    }
    
    public static boolean isInterceptable(final AxisAlignedBB aabb, final float range) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        final Vec3 look = PlayerUtil.getLook();
        return isInterceptable(position, position.func_72441_c(look.field_72450_a * range, look.field_72448_b * range, look.field_72449_c * range), aabb);
    }
    
    public static boolean isInterceptable(final BlockPos goal, final float range) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        final Vec3 look = PlayerUtil.getLook();
        return isInterceptable(position, position.func_72441_c(look.field_72450_a * range, look.field_72448_b * range, look.field_72449_c * range), Utils.getBlockAABB(goal));
    }
    
    public static boolean isInterceptable(final Vec3 goal, final AxisAlignedBB aabb) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        return isInterceptable(position, goal, aabb);
    }
    
    public static boolean isVecInYZ(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.field_72448_b >= aabb.field_72338_b && vec.field_72448_b <= aabb.field_72337_e && vec.field_72449_c >= aabb.field_72339_c && vec.field_72449_c <= aabb.field_72334_f;
    }
    
    public static boolean isVecInX(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.field_72450_a >= aabb.field_72340_a && vec.field_72450_a <= aabb.field_72336_d;
    }
    
    public static boolean isVecInZ(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.field_72449_c >= aabb.field_72339_c && vec.field_72449_c <= aabb.field_72334_f;
    }
    
    public static boolean isVecInXZ(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.field_72450_a >= aabb.field_72340_a && vec.field_72450_a <= aabb.field_72336_d && vec.field_72449_c >= aabb.field_72339_c && vec.field_72449_c <= aabb.field_72334_f;
    }
    
    public static boolean isVecInXY(final Vec3 vec, final AxisAlignedBB aabb) {
        return vec != null && vec.field_72450_a >= aabb.field_72340_a && vec.field_72450_a <= aabb.field_72336_d && vec.field_72448_b >= aabb.field_72338_b && vec.field_72448_b <= aabb.field_72337_e;
    }
    
    public static boolean entityRayTrace(Vec3 vec31, final Vec3 vec32, AxisAlignedBB aabb) {
        aabb = aabb.func_72314_b(1.02, 1.02, 1.02);
        final int i = MathHelper.func_76128_c(vec32.field_72450_a);
        final int j = MathHelper.func_76128_c(vec32.field_72448_b);
        final int k = MathHelper.func_76128_c(vec32.field_72449_c);
        int l = MathHelper.func_76128_c(vec31.field_72450_a);
        int i2 = MathHelper.func_76128_c(vec31.field_72448_b);
        int j2 = MathHelper.func_76128_c(vec31.field_72449_c);
        if (isInsideAABB(l, i2, j2, aabb)) {
            return true;
        }
        if (PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(l, i2, j2)).func_177230_c() != Blocks.field_150350_a) {
            return false;
        }
        if (PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(l, i2 - 1, j2)).func_177230_c() == Blocks.field_150350_a && PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(l, i2 - 2, j2)).func_177230_c() == Blocks.field_150350_a) {
            return false;
        }
        int k2 = 200;
        while (k2-- >= 0) {
            if (l == i && i2 == j && j2 == k) {
                return false;
            }
            boolean flag2 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            double d0 = 999.0;
            double d2 = 999.0;
            double d3 = 999.0;
            if (i > l) {
                d0 = l + 1.0;
            }
            else if (i < l) {
                d0 = l + 0.0;
            }
            else {
                flag2 = false;
            }
            if (j > i2) {
                d2 = i2 + 1.0;
            }
            else if (j < i2) {
                d2 = i2 + 0.0;
            }
            else {
                flag3 = false;
            }
            if (k > j2) {
                d3 = j2 + 1.0;
            }
            else if (k < j2) {
                d3 = j2 + 0.0;
            }
            else {
                flag4 = false;
            }
            double d4 = 999.0;
            double d5 = 999.0;
            double d6 = 999.0;
            final double d7 = vec32.field_72450_a - vec31.field_72450_a;
            final double d8 = vec32.field_72448_b - vec31.field_72448_b;
            final double d9 = vec32.field_72449_c - vec31.field_72449_c;
            if (flag2) {
                d4 = (d0 - vec31.field_72450_a) / d7;
            }
            if (flag3) {
                d5 = (d2 - vec31.field_72448_b) / d8;
            }
            if (flag4) {
                d6 = (d3 - vec31.field_72449_c) / d9;
            }
            if (d4 == -0.0) {
                d4 = -1.0E-4;
            }
            if (d5 == -0.0) {
                d5 = -1.0E-4;
            }
            if (d6 == -0.0) {
                d6 = -1.0E-4;
            }
            if (d4 < d5 && d4 < d6) {
                vec31 = new Vec3(d0, vec31.field_72448_b + d8 * d4, vec31.field_72449_c + d9 * d4);
                if (i > l) {
                    l = MathHelper.func_76128_c(vec31.field_72450_a);
                }
                else {
                    l = MathHelper.func_76128_c(vec31.field_72450_a) - 1;
                }
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else if (d5 < d6) {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d5, d2, vec31.field_72449_c + d9 * d5);
                if (j > i2) {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                }
                else {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d6, vec31.field_72448_b + d8 * d6, d3);
                if (k > j2) {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c);
                }
                else {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
            }
            if (isInsideAABB(l, i2, j2, aabb)) {
                return true;
            }
            if (PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(l, i2, j2)).func_177230_c() != Blocks.field_150350_a) {
                return false;
            }
            if (PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(l, i2 - 1, j2)).func_177230_c() == Blocks.field_150350_a && PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(l, i2 - 2, j2)).func_177230_c() == Blocks.field_150350_a) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isInsideAABB(final BlockPos pos, final AxisAlignedBB aabb) {
        return pos.func_177958_n() > aabb.field_72340_a && pos.func_177958_n() < aabb.field_72336_d && pos.func_177956_o() > aabb.field_72338_b && pos.func_177956_o() < aabb.field_72337_e && pos.func_177952_p() > aabb.field_72339_c && pos.func_177952_p() < aabb.field_72334_f;
    }
    
    public static boolean isInsideAABB(final int x, final int y, final int z, final AxisAlignedBB aabb) {
        return x > aabb.field_72340_a && x < aabb.field_72336_d && y > aabb.field_72338_b && y < aabb.field_72337_e && z > aabb.field_72339_c && z < aabb.field_72334_f;
    }
    
    public static boolean isInsideAABB(final float x, final float y, final float z, final AxisAlignedBB aabb) {
        return x > aabb.field_72340_a && x < aabb.field_72336_d && y > aabb.field_72338_b && y < aabb.field_72337_e && z > aabb.field_72339_c && z < aabb.field_72334_f;
    }
    
    public static boolean isInsideAABB(final double x, final double y, final double z, final AxisAlignedBB aabb) {
        return x > aabb.field_72340_a && x < aabb.field_72336_d && y > aabb.field_72338_b && y < aabb.field_72337_e && z > aabb.field_72339_c && z < aabb.field_72334_f;
    }
    
    public static boolean entityRayTrace(final AxisAlignedBB aabb) {
        return entityRayTrace(PlayerUtil.getPositionEyes(), Utils.getEntityEyeHeightAABB(aabb), aabb);
    }
    
    public static boolean entityRayTrace(final Vec3 goal, final AxisAlignedBB aabb) {
        return entityRayTrace(PlayerUtil.getPositionEyes(), goal, aabb);
    }
    
    public static List<BlockPos> getAllFacingBlocks(Vec3 vec31, final Vec3 vec32) {
        final List<BlockPos> list = new ArrayList<BlockPos>();
        final int i = MathHelper.func_76128_c(vec32.field_72450_a);
        final int j = MathHelper.func_76128_c(vec32.field_72448_b);
        final int k = MathHelper.func_76128_c(vec32.field_72449_c);
        int l = MathHelper.func_76128_c(vec31.field_72450_a);
        int i2 = MathHelper.func_76128_c(vec31.field_72448_b);
        int j2 = MathHelper.func_76128_c(vec31.field_72449_c);
        int k2 = 200;
        while (k2-- >= 0) {
            list.add(new BlockPos(l, i2, j2));
            if (l == i && i2 == j && j2 == k) {
                return list;
            }
            boolean flag2 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            double d0 = 999.0;
            double d2 = 999.0;
            double d3 = 999.0;
            if (i > l) {
                d0 = l + 1.0;
            }
            else if (i < l) {
                d0 = l + 0.0;
            }
            else {
                flag2 = false;
            }
            if (j > i2) {
                d2 = i2 + 1.0;
            }
            else if (j < i2) {
                d2 = i2 + 0.0;
            }
            else {
                flag3 = false;
            }
            if (k > j2) {
                d3 = j2 + 1.0;
            }
            else if (k < j2) {
                d3 = j2 + 0.0;
            }
            else {
                flag4 = false;
            }
            double d4 = 999.0;
            double d5 = 999.0;
            double d6 = 999.0;
            final double d7 = vec32.field_72450_a - vec31.field_72450_a;
            final double d8 = vec32.field_72448_b - vec31.field_72448_b;
            final double d9 = vec32.field_72449_c - vec31.field_72449_c;
            if (flag2) {
                d4 = (d0 - vec31.field_72450_a) / d7;
            }
            if (flag3) {
                d5 = (d2 - vec31.field_72448_b) / d8;
            }
            if (flag4) {
                d6 = (d3 - vec31.field_72449_c) / d9;
            }
            if (d4 == -0.0) {
                d4 = -1.0E-4;
            }
            if (d5 == -0.0) {
                d5 = -1.0E-4;
            }
            if (d6 == -0.0) {
                d6 = -1.0E-4;
            }
            if (d4 < d5 && d4 < d6) {
                vec31 = new Vec3(d0, vec31.field_72448_b + d8 * d4, vec31.field_72449_c + d9 * d4);
                if (i > l) {
                    l = MathHelper.func_76128_c(vec31.field_72450_a);
                }
                else {
                    l = MathHelper.func_76128_c(vec31.field_72450_a) - 1;
                }
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else if (d5 < d6) {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d5, d2, vec31.field_72449_c + d9 * d5);
                if (j > i2) {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                }
                else {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d6, vec31.field_72448_b + d8 * d6, d3);
                if (k > j2) {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c);
                }
                else {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
            }
        }
        return list;
    }
    
    public static boolean getAllFacingBlocks(Vec3 vec31, final Vec3 vec32, final BlockFinder tick) {
        final int i = MathHelper.func_76128_c(vec32.field_72450_a);
        final int j = MathHelper.func_76128_c(vec32.field_72448_b);
        final int k = MathHelper.func_76128_c(vec32.field_72449_c);
        int l = MathHelper.func_76128_c(vec31.field_72450_a);
        int i2 = MathHelper.func_76128_c(vec31.field_72448_b);
        int j2 = MathHelper.func_76128_c(vec31.field_72449_c);
        int k2 = 200;
        while (k2-- >= 0) {
            if (tick.isValid(new BlockPos(l, i2, j2))) {
                return true;
            }
            if (l == i && i2 == j && j2 == k) {
                return false;
            }
            boolean flag2 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            double d0 = 999.0;
            double d2 = 999.0;
            double d3 = 999.0;
            if (i > l) {
                d0 = l + 1.0;
            }
            else if (i < l) {
                d0 = l + 0.0;
            }
            else {
                flag2 = false;
            }
            if (j > i2) {
                d2 = i2 + 1.0;
            }
            else if (j < i2) {
                d2 = i2 + 0.0;
            }
            else {
                flag3 = false;
            }
            if (k > j2) {
                d3 = j2 + 1.0;
            }
            else if (k < j2) {
                d3 = j2 + 0.0;
            }
            else {
                flag4 = false;
            }
            double d4 = 999.0;
            double d5 = 999.0;
            double d6 = 999.0;
            final double d7 = vec32.field_72450_a - vec31.field_72450_a;
            final double d8 = vec32.field_72448_b - vec31.field_72448_b;
            final double d9 = vec32.field_72449_c - vec31.field_72449_c;
            if (flag2) {
                d4 = (d0 - vec31.field_72450_a) / d7;
            }
            if (flag3) {
                d5 = (d2 - vec31.field_72448_b) / d8;
            }
            if (flag4) {
                d6 = (d3 - vec31.field_72449_c) / d9;
            }
            if (d4 == -0.0) {
                d4 = -1.0E-4;
            }
            if (d5 == -0.0) {
                d5 = -1.0E-4;
            }
            if (d6 == -0.0) {
                d6 = -1.0E-4;
            }
            if (d4 < d5 && d4 < d6) {
                vec31 = new Vec3(d0, vec31.field_72448_b + d8 * d4, vec31.field_72449_c + d9 * d4);
                if (i > l) {
                    l = MathHelper.func_76128_c(vec31.field_72450_a);
                }
                else {
                    l = MathHelper.func_76128_c(vec31.field_72450_a) - 1;
                }
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else if (d5 < d6) {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d5, d2, vec31.field_72449_c + d9 * d5);
                if (j > i2) {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b);
                }
                else {
                    i2 = MathHelper.func_76128_c(vec31.field_72448_b) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                j2 = MathHelper.func_76128_c(vec31.field_72449_c);
            }
            else {
                vec31 = new Vec3(vec31.field_72450_a + d7 * d6, vec31.field_72448_b + d8 * d6, d3);
                if (k > j2) {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c);
                }
                else {
                    j2 = MathHelper.func_76128_c(vec31.field_72449_c) - 1;
                }
                l = MathHelper.func_76128_c(vec31.field_72450_a);
                i2 = MathHelper.func_76128_c(vec31.field_72448_b);
            }
        }
        return false;
    }
    
    public static List<BlockPos> getAllFacingBlocks() {
        final Vec3 position = PlayerUtil.getPositionEyes();
        final Vec3 look = scaleVec(PlayerUtil.getLook(), 4.5f);
        return getAllFacingBlocks(position, position.func_72441_c(look.field_72450_a, look.field_72448_b, look.field_72449_c));
    }
    
    public static List<BlockPos> getAllFacingBlocks(final float range) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        final Vec3 look = scaleVec(PlayerUtil.getLook(), range);
        return getAllFacingBlocks(position, position.func_72441_c(look.field_72450_a, look.field_72448_b, look.field_72449_c));
    }
    
    public static List<BlockPos> getAllFacingBlocksLook(final Vec3 look) {
        return getAllFacingBlocks(PlayerUtil.getPositionEyes(), look);
    }
    
    public static List<BlockPos> getAllFacingBlocksLook(final Vec3 look, final float range) {
        final Vec3 position = PlayerUtil.getPositionEyes();
        if (position.func_72436_e(look) > range * range) {
            return Collections.emptyList();
        }
        return getAllFacingBlocks(PlayerUtil.getPositionEyes(), look);
    }
    
    public static boolean isLookingAtAABB(final EntityPlayer player, final AxisAlignedBB entity) {
        final AxisAlignedBB aabb = entity.func_72314_b(1.01, 1.01, 1.01);
        final Vec3 position = PlayerUtil.getPositionEyes(player);
        final Vec3 look = scaleVec(player.func_70676_i(1.0f), 20.0f);
        return getAllFacingBlocks(position, position.func_72441_c(look.field_72450_a, look.field_72448_b, look.field_72449_c), pos -> isInsideAABB(pos, aabb));
    }
    
    public static boolean isLookingAtPlayer(final EntityPlayer player) {
        return isLookingAtAABB(player, PizzaClient.mc.field_71439_g.func_174813_aQ());
    }
    
    public static boolean isPlayerBeingLookedAt(final EntityPlayer player) {
        return isPlayerBeingLookedAt(player, RenderUtil.setupFrustrum());
    }
    
    public static boolean isPlayerBeingLookedAt(final EntityPlayer player, final Frustum frustum) {
        return frustum.func_78546_a(player.func_174813_aQ()) && isLookingAtAABB(player, PizzaClient.mc.field_71439_g.func_174813_aQ().func_72314_b(0.25, 0.25, 0.25));
    }
}
