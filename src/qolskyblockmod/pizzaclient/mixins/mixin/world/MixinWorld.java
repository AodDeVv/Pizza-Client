// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.world;

import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ World.class })
public class MixinWorld
{
    @Final
    @Overwrite
    public MovingObjectPosition func_147447_a(Vec3 vec31, final Vec3 vec32, final boolean stopOnLiquid, final boolean ignoreBlockWithoutBoundingBox, final boolean returnLastUncollidableBlock) {
        final int i = MathHelper.func_76128_c(vec32.field_72450_a);
        final int j = MathHelper.func_76128_c(vec32.field_72448_b);
        final int k = MathHelper.func_76128_c(vec32.field_72449_c);
        int l = MathHelper.func_76128_c(vec31.field_72450_a);
        int i2 = MathHelper.func_76128_c(vec31.field_72448_b);
        int j2 = MathHelper.func_76128_c(vec31.field_72449_c);
        BlockPos blockpos = new BlockPos(l, i2, j2);
        final IBlockState iblockstate = PizzaClient.mc.field_71441_e.func_180495_p(blockpos);
        final Block block = iblockstate.func_177230_c();
        if ((!ignoreBlockWithoutBoundingBox || block.func_180640_a((World)PizzaClient.mc.field_71441_e, blockpos, iblockstate) != null) && block.func_176209_a(iblockstate, stopOnLiquid)) {
            final MovingObjectPosition movingobjectposition = block.func_180636_a((World)PizzaClient.mc.field_71441_e, blockpos, vec31, vec32);
            if (movingobjectposition != null) {
                return movingobjectposition;
            }
        }
        MovingObjectPosition movingobjectposition2 = null;
        int k2 = 200;
        while (k2-- >= 0) {
            if (l == i && i2 == j && j2 == k) {
                return returnLastUncollidableBlock ? movingobjectposition2 : null;
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
            if (ignoreBlockWithoutBoundingBox && block2.func_180640_a((World)PizzaClient.mc.field_71441_e, blockpos, iblockstate2) == null) {
                continue;
            }
            if (block2.func_176209_a(iblockstate2, stopOnLiquid)) {
                final MovingObjectPosition movingobjectposition3 = block2.func_180636_a((World)PizzaClient.mc.field_71441_e, blockpos, vec31, vec32);
                if (movingobjectposition3 != null) {
                    return movingobjectposition3;
                }
                continue;
            }
            else {
                movingobjectposition2 = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec31, enumfacing, blockpos);
            }
        }
        return returnLastUncollidableBlock ? movingobjectposition2 : null;
    }
}
