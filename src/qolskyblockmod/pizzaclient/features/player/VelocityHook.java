// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.PizzaClient;

public class VelocityHook
{
    public static double motionMultiplier;
    public static int velocityTicks;
    
    public static void onTick() {
        if (VelocityHook.velocityTicks != 0) {
            if (VelocityHook.velocityTicks == 1) {
                final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
                field_71439_g.field_70159_w *= VelocityHook.motionMultiplier;
                final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
                field_71439_g2.field_70179_y *= VelocityHook.motionMultiplier;
                VelocityHook.velocityTicks = 0;
            }
            else {
                --VelocityHook.velocityTicks;
            }
        }
    }
    
    public static void setupVelocity() {
        VelocityHook.velocityTicks = 3;
        VelocityHook.motionMultiplier = calculateMotion(getMotionMultiplier());
    }
    
    private static double getMotionMultiplier() {
        final ItemStack held = PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g();
        final String name = held.func_82833_r();
        if (name.contains("Leaping Sword")) {
            return 1.100000023841858;
        }
        if (name.contains("Bonzo's Staff")) {
            return PizzaClient.config.increasedVelocityAmount;
        }
        if (name.contains("Jerry-chine")) {
            return 1.0;
        }
        return 1.2999999523162842;
    }
    
    private static double calculateMotion(final double multiplier) {
        return 1.0 + (multiplier - 1.0) * (PlayerUtil.getSpeedModifier() * 2.5);
    }
}
