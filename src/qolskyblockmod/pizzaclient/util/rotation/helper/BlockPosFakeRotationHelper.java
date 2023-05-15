// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.helper;

import net.minecraft.util.MovingObjectPosition;
import qolskyblockmod.pizzaclient.util.rotation.Rotation;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.util.Vec3;
import net.minecraft.util.BlockPos;

public class BlockPosFakeRotationHelper
{
    public BlockPos bestPos;
    private double bestRotation;
    private final float maxDist;
    private final Vec3 player;
    
    public BlockPosFakeRotationHelper() {
        this.bestRotation = 9.9999999E7;
        this.maxDist = 20.25f;
        this.player = PlayerUtil.getPositionEyes();
        if (FakeRotater.lastPitch == 420.0f) {
            FakeRotater.lastYaw = PizzaClient.mc.field_71439_g.field_70177_z;
            FakeRotater.lastPitch = PizzaClient.mc.field_71439_g.field_70125_A;
        }
    }
    
    public void compare(final Vec3 vecIn) {
        if (this.player.func_72436_e(vecIn) <= this.maxDist) {
            final double dist = Rotation.getRotationDifference(vecIn, FakeRotater.lastYaw, FakeRotater.lastPitch).sum();
            if (dist < this.bestRotation) {
                this.bestPos = new BlockPos(vecIn);
                this.bestRotation = dist;
            }
        }
    }
    
    public void compare(final MovingObjectPosition pos) {
        if (this.player.func_72436_e(pos.field_72307_f) <= this.maxDist) {
            final double dist = Rotation.getRotationDifference(pos.field_72307_f, FakeRotater.lastYaw, FakeRotater.lastPitch).sum();
            if (dist < this.bestRotation) {
                this.bestPos = pos.func_178782_a();
                this.bestRotation = dist;
            }
        }
    }
    
    public void compare(final BlockPos pos) {
        final Vec3 vecIn = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
        if (this.player.func_72436_e(vecIn) <= this.maxDist) {
            final double dist = Rotation.getRotationDifference(vecIn, FakeRotater.lastYaw, FakeRotater.lastPitch).sum();
            if (dist < this.bestRotation) {
                this.bestPos = pos;
                this.bestRotation = dist;
            }
        }
    }
    
    public boolean isNotNull() {
        return this.bestPos != null;
    }
}
