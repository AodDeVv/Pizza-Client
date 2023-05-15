// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.mining.nuker;

import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import net.minecraft.util.EnumFacing;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.Vec3;
import net.minecraft.util.BlockPos;

public interface INuker
{
    boolean isBlockValid(final BlockPos p0);
    
    boolean nuke(final boolean p0);
    
    boolean isVecValid();
    
    boolean findVec();
    
    default void onNoVecAvailable() {
    }
    
    default void onNukePre() {
    }
    
    default void enqueue() {
        NukerBase.nuker = this;
    }
    
    default boolean nukeSilent(final Vec3 vec) {
        if (vec != null) {
            final BlockPos block = new BlockPos(vec);
            if (!PizzaClient.mc.field_71439_g.func_71039_bw()) {
                if (PizzaClient.mc.field_71462_r == null) {
                    final EnumFacing facing = VecUtil.calculateEnumfacingLook(vec);
                    if (PizzaClient.mc.field_71442_b.func_180512_c(block, facing)) {
                        PizzaClient.mc.field_71452_i.func_180532_a(block, facing);
                        return true;
                    }
                }
                else {
                    PizzaClient.mc.field_71442_b.func_78767_c();
                }
            }
        }
        return false;
    }
    
    default boolean nuke(final Vec3 vec, final boolean noSwing) {
        if (vec != null) {
            FakeRotater.rotate(vec);
            final BlockPos block = new BlockPos(vec);
            if (!PizzaClient.mc.field_71439_g.func_71039_bw()) {
                if (PizzaClient.mc.field_71462_r == null) {
                    final EnumFacing facing = VecUtil.calculateEnumfacingLook(vec);
                    if (PizzaClient.mc.field_71442_b.func_180512_c(block, facing)) {
                        PizzaClient.mc.field_71452_i.func_180532_a(block, facing);
                        if (!noSwing) {
                            PizzaClient.mc.field_71439_g.func_71038_i();
                        }
                        return true;
                    }
                }
                else {
                    PizzaClient.mc.field_71442_b.func_78767_c();
                }
            }
        }
        return false;
    }
    
    default boolean nuke(final BlockPos pos, final boolean noSwing) {
        final Vec3 vec = this.getRandomAABBPoint(pos);
        if (vec != null) {
            FakeRotater.rotate(vec);
            final BlockPos block = new BlockPos(vec);
            if (!PizzaClient.mc.field_71439_g.func_71039_bw()) {
                if (PizzaClient.mc.field_71462_r == null) {
                    final EnumFacing facing = VecUtil.calculateEnumfacingLook(vec);
                    if (PizzaClient.mc.field_71442_b.func_180512_c(block, facing)) {
                        PizzaClient.mc.field_71452_i.func_180532_a(block, facing);
                        if (!noSwing) {
                            PizzaClient.mc.field_71439_g.func_71038_i();
                        }
                        return true;
                    }
                }
                else {
                    PizzaClient.mc.field_71442_b.func_78767_c();
                }
            }
        }
        return false;
    }
    
    default Vec3 getRandomAABBPoint(final BlockPos pos) {
        return MathUtil.randomAABBPoint(pos);
    }
    
    default boolean invalidate() {
        return true;
    }
}
