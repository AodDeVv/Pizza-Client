// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.rotaters;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.util.Vec3;
import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.PizzaClient;

public class SnapRotater
{
    public static void snapTo(final float yaw, final float pitch) {
        PizzaClient.mc.field_71439_g.field_70177_z = yaw;
        PizzaClient.mc.field_71439_g.field_70125_A = pitch;
    }
    
    public static void addRotation(final float yaw, final float pitch) {
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += yaw;
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += pitch;
    }
    
    public static void snapTo(final Vec3 target) {
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
    }
    
    public static void snapTo(final Entity entity) {
        final Vec3 target = new Vec3(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v);
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
    }
    
    public static void snapTo(final float x, final float y, final float z) {
        final double diffX = x - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = y - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = z - PizzaClient.mc.field_71439_g.field_70161_v;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
    }
    
    public static void snapTo(final double x, final double y, final double z) {
        final double diffX = x - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = y - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = z - PizzaClient.mc.field_71439_g.field_70161_v;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
    }
    
    public static void snapTo(final int x, final int y, final int z) {
        final double diffX = x - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = y - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = z - PizzaClient.mc.field_71439_g.field_70161_v;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
    }
    
    public static void snapToLeftClick(final Vec3 target) {
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
        KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i());
    }
    
    public static void snapToRightClick(final Vec3 target) {
        final double diffX = target.field_72450_a - PizzaClient.mc.field_71439_g.field_70165_t;
        final double diffY = target.field_72448_b - (PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight());
        final double diffZ = target.field_72449_c - PizzaClient.mc.field_71439_g.field_70161_v;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        field_71439_g.field_70177_z += (float)MathHelper.func_76138_g(MathHelper.func_181159_b(diffZ, diffX) * 57.29577951308232 - 90.0 - PizzaClient.mc.field_71439_g.field_70177_z);
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        field_71439_g2.field_70125_A += (float)MathHelper.func_76138_g(-(MathHelper.func_181159_b(diffY, (double)MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ)) * 57.29577951308232) - PizzaClient.mc.field_71439_g.field_70125_A);
        KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
    }
}
