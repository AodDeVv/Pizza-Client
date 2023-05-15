// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation;

import net.minecraft.util.Vec3;
import net.minecraft.util.MathHelper;
import qolskyblockmod.pizzaclient.PizzaClient;

public class RotationUtil
{
    public static float getYawDifference(final float yaw) {
        return (float)MathHelper.func_76138_g((double)(yaw - PizzaClient.mc.field_71439_g.field_70177_z));
    }
    
    public static float getPitchDifference(final float pitch) {
        return (float)MathHelper.func_76138_g((double)(pitch - PizzaClient.mc.field_71439_g.field_70125_A));
    }
    
    public static float getYawClosestTo90Degrees() {
        if (PizzaClient.mc.field_71439_g.field_70177_z > 0.0f) {
            final float i = PizzaClient.mc.field_71439_g.field_70177_z % 90.0f;
            return (i > 45.0f) ? (PizzaClient.mc.field_71439_g.field_70177_z + (90.0f - i)) : (PizzaClient.mc.field_71439_g.field_70177_z - i);
        }
        final float yaw = -PizzaClient.mc.field_71439_g.field_70177_z;
        final float j = yaw % 90.0f;
        return -((j > 45.0f) ? (yaw + (90.0f - j)) : (yaw - j));
    }
    
    public static float getYawClosestTo90Degrees(final float yawIn) {
        if (yawIn > 0.0f) {
            final float i = yawIn % 90.0f;
            return (i > 45.0f) ? (yawIn + (90.0f - i)) : (yawIn - i);
        }
        final float yaw = -yawIn;
        final float j = yaw % 90.0f;
        return -((j > 45.0f) ? (yaw + (90.0f - j)) : (yaw - j));
    }
    
    public static float getYawDifference(final Vec3 target) {
        final double yaw = MathHelper.func_181159_b(target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v, target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t) * 57.29577951308232 - 90.0;
        return (float)MathHelper.func_76138_g(yaw - PizzaClient.mc.field_71439_g.field_70177_z);
    }
    
    public static float getPitchDifference(final Vec3 target) {
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final double pitch = -(MathHelper.func_181159_b(target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PizzaClient.mc.field_71439_g.func_70047_e()), (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232);
        return (float)MathHelper.func_76138_g(pitch - PizzaClient.mc.field_71439_g.field_70125_A);
    }
}
