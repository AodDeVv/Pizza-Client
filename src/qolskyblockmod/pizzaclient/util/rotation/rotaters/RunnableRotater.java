// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.rotaters;

import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.Vec3;

public class RunnableRotater extends Rotater
{
    private final Runnable runnable;
    
    public RunnableRotater(final Vec3 target, final Runnable runnable) {
        super(target);
        this.runnable = runnable;
    }
    
    public RunnableRotater(final float yaw, final float pitch, final Runnable runnable) {
        super(yaw, pitch);
        this.runnable = runnable;
    }
    
    @Override
    public void add() {
        final float elapsed = (float)(System.currentTimeMillis() - this.timeElapsed);
        if (elapsed >= this.changedDivider) {
            PizzaClient.mc.field_71439_g.field_70177_z = this.goalYaw;
            PizzaClient.mc.field_71439_g.field_70125_A = MathUtil.clampPitch(this.goalPitch);
            PizzaClient.rotater = null;
            this.runnable.run();
            return;
        }
        if (elapsed > 80.0f) {
            final float diff = (float)Math.sqrt((elapsed - 80.0f) / this.changedDivider + 1.0f) * 1.25f;
            this.changedYaw = this.yaw / diff;
            this.changedPitch = this.pitch / diff;
            this.changedDivider = this.divider * diff;
        }
        PizzaClient.mc.field_71439_g.field_70177_z = this.startYaw + this.changedYaw * elapsed;
        PizzaClient.mc.field_71439_g.field_70125_A = MathUtil.clampPitch(this.startPitch + this.changedPitch * elapsed);
    }
}
