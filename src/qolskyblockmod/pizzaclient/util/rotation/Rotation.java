// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation;

import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.util.MathHelper;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.Vec3;

public class Rotation
{
    public final float yaw;
    public final float pitch;
    
    public Rotation(final float yawIn, final float pitchIn) {
        this.yaw = yawIn;
        this.pitch = pitchIn;
    }
    
    public static Rotation getRotationDifference(final Vec3 target) {
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        return new Rotation((float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z), (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, dist) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A));
    }
    
    public static Rotation getRotationDifference(final Vec3 target, final float playerYaw, final float playerPitch) {
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        return new Rotation((float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - playerYaw), (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, dist) * 57.29577951308232) - playerPitch));
    }
    
    public static Rotation getRotation(final Vec3 target) {
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        return new Rotation((float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z) + PizzaClient.mc.field_71439_g.field_70177_z, (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, dist) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A) + PizzaClient.mc.field_71439_g.field_70125_A);
    }
    
    public static Rotation getRotation(final Vec3 position, final Vec3 target) {
        final double diffX = target.field_72450_a - position.field_72450_a;
        final double diffY = target.field_72448_b - position.field_72448_b;
        final double diffZ = target.field_72449_c - position.field_72449_c;
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        return new Rotation((float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z) + PizzaClient.mc.field_71439_g.field_70177_z, (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, dist) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A) + PizzaClient.mc.field_71439_g.field_70125_A);
    }
    
    public static float getYaw(final Vec3 target) {
        return (float)MathHelper.func_76138_g(MathHelper.func_181159_b(target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v, target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z) + PizzaClient.mc.field_71439_g.field_70177_z;
    }
    
    public static float getYawDifference(final Vec3 target) {
        return (float)MathHelper.func_76138_g(MathHelper.func_181159_b(target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v, target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
    }
    
    public float max() {
        return MathUtil.max(MathUtil.abs(this.yaw), MathUtil.abs(this.pitch));
    }
    
    public float min() {
        return MathUtil.min(MathUtil.abs(this.yaw), MathUtil.abs(this.pitch));
    }
    
    public float sum() {
        return MathUtil.abs(this.yaw) + MathUtil.abs(this.pitch);
    }
    
    public Vec3 getLook() {
        final float f2 = -MathHelper.func_76134_b(-this.pitch * 0.017453292f);
        return new Vec3((double)(MathHelper.func_76126_a(-this.yaw * 0.017453292f - 3.1415927f) * f2), (double)MathHelper.func_76126_a(-this.pitch * 0.017453292f), (double)(MathHelper.func_76134_b(-this.yaw * 0.017453292f - 3.1415927f) * f2));
    }
    
    @Override
    public String toString() {
        return "{yaw=" + this.yaw + ", pitch=" + this.pitch + "}";
    }
}
