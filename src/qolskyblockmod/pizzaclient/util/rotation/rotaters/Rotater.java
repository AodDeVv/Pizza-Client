// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.rotaters;

import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.rotation.RotationUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.Vec3;

public class Rotater implements IRotater
{
    public static boolean rotating;
    protected float divider;
    protected float changedDivider;
    protected long timeElapsed;
    protected float yaw;
    protected float pitch;
    protected float changedYaw;
    protected float changedPitch;
    protected float startYaw;
    protected float startPitch;
    protected float goalYaw;
    protected float goalPitch;
    
    public Rotater(final Vec3 target) {
        this.divider = 190.0f;
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        this.yaw = (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        this.pitch = (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
        this.startPitch = PizzaClient.mc.field_71439_g.field_70125_A;
        this.startYaw = PizzaClient.mc.field_71439_g.field_70177_z;
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        this.goalPitch = this.pitch + PizzaClient.mc.field_71439_g.field_70125_A;
    }
    
    public Rotater(final BlockPos pos) {
        this.divider = 190.0f;
        final Vec3 target = new Vec3(pos.func_177958_n() + 0.5, PizzaClient.mc.field_71439_g.field_70163_u + 1.6200000047683716, pos.func_177952_p() + 0.5);
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        this.yaw = RotationUtil.getYawClosestTo90Degrees((float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z));
        this.pitch = (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
        this.startPitch = PizzaClient.mc.field_71439_g.field_70125_A;
        this.startYaw = PizzaClient.mc.field_71439_g.field_70177_z;
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        this.goalPitch = this.pitch + PizzaClient.mc.field_71439_g.field_70125_A;
    }
    
    public Rotater(final float yawIn, final float pitchIn) {
        this.divider = 190.0f;
        this.yaw = yawIn;
        this.pitch = pitchIn;
        this.startPitch = PizzaClient.mc.field_71439_g.field_70125_A;
        this.startYaw = PizzaClient.mc.field_71439_g.field_70177_z;
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        this.goalPitch = this.pitch + PizzaClient.mc.field_71439_g.field_70125_A;
    }
    
    public static Rotater rotateTo(final float yaw, final float pitch) {
        return new Rotater(yaw - PizzaClient.mc.field_71439_g.field_70177_z, MathUtil.clampPitch(pitch - PizzaClient.mc.field_71439_g.field_70125_A));
    }
    
    public Rotater setPitch(final float pitch) {
        this.pitch = pitch;
        this.goalPitch = pitch + PizzaClient.mc.field_71439_g.field_70125_A;
        return this;
    }
    
    public Rotater setYaw(final float yaw) {
        this.yaw = yaw;
        this.goalYaw = yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        return this;
    }
    
    public Rotater addPitch(final float pitch) {
        this.pitch += pitch;
        this.goalPitch += pitch;
        return this;
    }
    
    public Rotater addYaw(final float yaw) {
        this.yaw += yaw;
        this.goalYaw += yaw;
        return this;
    }
    
    public Rotater randomPitch() {
        this.pitch = MathUtil.randomFloat();
        this.goalPitch = this.pitch + PizzaClient.mc.field_71439_g.field_70125_A;
        return this;
    }
    
    public Rotater randomYaw() {
        this.yaw = MathUtil.randomFloat();
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        return this;
    }
    
    public Rotater setRotationAmount(final int rotation) {
        this.divider = (float)(rotation * 10);
        return this;
    }
    
    @Override
    public void rotate() {
        Rotater.rotating = true;
        this.changedDivider = this.divider;
        this.yaw /= this.divider;
        this.pitch /= this.divider;
        this.changedPitch = this.pitch;
        this.changedYaw = this.yaw;
        PizzaClient.rotater = this;
        this.timeElapsed = System.currentTimeMillis();
    }
    
    @Override
    public void add() {
        final float elapsed = (float)(System.currentTimeMillis() - this.timeElapsed);
        if (elapsed >= this.changedDivider) {
            PizzaClient.mc.field_71439_g.field_70177_z = this.goalYaw;
            PizzaClient.mc.field_71439_g.field_70125_A = MathUtil.clampPitch(this.goalPitch);
            PizzaClient.rotater = null;
            return;
        }
        if (elapsed > 80.0f) {
            final float progress = 1.0f + (elapsed - 80.0f) / this.changedDivider;
            final float diff = progress * progress / 1.8f;
            this.changedYaw = this.yaw / diff;
            this.changedPitch = this.pitch / diff;
            this.changedDivider = this.divider * diff;
        }
        PizzaClient.mc.field_71439_g.field_70177_z = this.startYaw + this.changedYaw * elapsed;
        PizzaClient.mc.field_71439_g.field_70125_A = MathUtil.clampPitch(this.startPitch + this.changedPitch * elapsed);
    }
    
    public Rotater addRandom() {
        this.yaw += MathUtil.randomFloat();
        this.pitch += MathUtil.randomFloat();
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        this.goalPitch = this.pitch + PizzaClient.mc.field_71439_g.field_70125_A;
        return this;
    }
    
    public Rotater addRandomRotateAmount() {
        this.divider += MathUtil.floor(Utils.random.nextGaussian() * 8.0);
        return this;
    }
    
    public Rotater addSlightRandomRotateAmount() {
        this.divider += MathUtil.floor(Utils.random.nextGaussian() * 5.0);
        return this;
    }
    
    public Rotater addBigRandomRotateAmount() {
        this.divider += MathUtil.floor(Utils.random.nextGaussian() * 13.0);
        return this;
    }
    
    public Rotater addRandomRotateAmount(final float multiplier) {
        this.divider += MathUtil.floor(Utils.random.nextGaussian() * multiplier);
        return this;
    }
    
    public Rotater randomYaw(final float min, final float rand, final boolean positive) {
        this.yaw += (positive ? (MathUtil.positiveFloat() * rand + min) : (MathUtil.negativeFloat() * rand - min));
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        return this;
    }
    
    public Rotater randomPitch(final float min, final float rand, final boolean positive) {
        final float nextPitch = positive ? (MathUtil.positiveFloat() * rand + min) : (MathUtil.negativeFloat() * rand - min + this.pitch);
        final float nextGoalPitch = nextPitch + PizzaClient.mc.field_71439_g.field_70125_A;
        if (nextGoalPitch > 90.0f || nextGoalPitch < -90.0f) {
            return this.randomPitch(min, rand, !positive);
        }
        this.pitch += nextPitch;
        this.goalPitch = nextGoalPitch;
        return this;
    }
    
    public Rotater randomYaw(final float min, final float rand) {
        this.yaw += (Utils.random.nextBoolean() ? (MathUtil.positiveFloat() * rand + min) : (MathUtil.negativeFloat() * rand - min));
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        return this;
    }
    
    public Rotater randomPitch(final float min, final float rand) {
        this.pitch += (Utils.random.nextBoolean() ? (MathUtil.positiveFloat() * rand + min) : (MathUtil.negativeFloat() * rand - min));
        this.goalPitch = this.pitch + PizzaClient.mc.field_71439_g.field_70125_A;
        return this;
    }
    
    public Rotater antiSus(final float amt) {
        this.divider = (MathUtil.round((MathUtil.abs(this.yaw) + MathUtil.abs(this.pitch) + MathUtil.randomFloat()) / 8.0f) + amt + 2.0f) * 10.0f;
        return this;
    }
    
    public Rotater antiSus(final float divider, final float amt) {
        this.divider = (MathUtil.round((MathUtil.abs(this.yaw) + MathUtil.abs(this.pitch) + MathUtil.randomFloat()) / divider) + amt + 2.0f) * 10.0f;
        return this;
    }
    
    public Rotater antiSus() {
        this.divider = (float)((MathUtil.round((MathUtil.abs(this.yaw) + MathUtil.abs(this.pitch) + MathUtil.randomFloat()) / 8.0f) + 22) * 10);
        return this;
    }
}
