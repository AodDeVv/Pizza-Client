// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.rotaters;

import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.MathHelper;
import qolskyblockmod.pizzaclient.PizzaClient;

public class FailsafeRotater implements IRotater
{
    private float divider;
    private long timeElapsed;
    private float yaw;
    private float pitch;
    private float startYaw;
    private float startPitch;
    private float goalYaw;
    private float goalPitch;
    private long lastRotation;
    private int nextRotation;
    private final int duration;
    private long totalTime;
    
    public FailsafeRotater() {
        this.duration = 4000;
    }
    
    public FailsafeRotater(final int duration) {
        this.duration = duration;
    }
    
    @Override
    public void rotate() {
        this.update();
        this.totalTime = System.currentTimeMillis();
        PizzaClient.rotater = this;
        Rotater.rotating = true;
    }
    
    @Override
    public void add() {
        final long millis = System.currentTimeMillis();
        final double elapsed = (double)(millis - this.timeElapsed);
        if (elapsed >= this.divider) {
            if (millis - this.totalTime >= this.duration) {
                this.shutdown();
                return;
            }
            PizzaClient.mc.field_71439_g.field_70177_z = this.goalYaw;
            PizzaClient.mc.field_71439_g.field_70125_A = MathHelper.func_76131_a(this.goalPitch, -90.0f, 90.0f);
            this.lastRotation = millis;
            this.timeElapsed = millis + millis;
            this.nextRotation = 800 + Utils.random.nextInt(300);
        }
        else {
            if (this.lastRotation != 0L) {
                if (millis - this.lastRotation >= this.nextRotation) {
                    this.update();
                    this.lastRotation = 0L;
                }
                return;
            }
            PizzaClient.mc.field_71439_g.field_70177_z = (float)(this.startYaw + this.yaw * elapsed);
            PizzaClient.mc.field_71439_g.field_70125_A = MathHelper.func_76131_a((float)(this.startPitch + this.pitch * elapsed), -90.0f, 90.0f);
        }
    }
    
    private void update() {
        this.yaw = 90.0f + MathUtil.positiveFloat(40.0f);
        this.goalPitch = MathUtil.randomFloat(20.0f) - 5.0f;
        this.pitch = this.goalPitch - PizzaClient.mc.field_71439_g.field_70125_A;
        this.startPitch = PizzaClient.mc.field_71439_g.field_70125_A;
        this.startYaw = PizzaClient.mc.field_71439_g.field_70177_z;
        this.divider = (float)(270 + Utils.random.nextInt(80));
        this.goalYaw = this.yaw + PizzaClient.mc.field_71439_g.field_70177_z;
        this.yaw /= this.divider;
        this.pitch /= this.divider;
        this.timeElapsed = System.currentTimeMillis();
    }
}
