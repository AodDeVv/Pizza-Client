// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.fake;

import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.rotation.Rotation;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.util.Vec3;

public abstract class SmoothFakeRotater extends FakeRotater
{
    protected Vec3 rotationVec;
    protected boolean rotatingBack;
    protected final int rotationAmount;
    protected boolean shouldInteract;
    protected boolean isFinished;
    
    public SmoothFakeRotater(final Vec3 rotationVec, final int rotationAmount) {
        this.rotationVec = rotationVec;
        this.rotationAmount = rotationAmount;
    }
    
    @Override
    public void onPlayerMovePre() {
        this.storeCurrentRotation();
        if (this.rotatingBack) {
            if (this.smoothRotateToVec()) {
                this.isFinished = true;
            }
        }
        else if (this.smoothRotateToVec()) {
            this.rotatingBack = true;
            this.rotationVec = PlayerUtil.getPositionEyes().func_178787_e(PlayerUtil.getLook(this.realYaw, this.realPitch, 4.5f));
            this.shouldInteract = true;
        }
    }
    
    @Override
    public void onPlayerMovePost() {
        this.rotateBack();
        if (this.shouldInteract) {
            this.interact();
            this.shouldInteract = false;
        }
        if (this.isFinished) {
            this.terminate();
        }
    }
    
    public abstract void interact();
    
    @Override
    public void onOpenGui() {
        this.rotateToGoal(this.rotationVec);
    }
    
    protected boolean smoothRotateToVec() {
        final Rotation rotation = Rotation.getRotationDifference(this.rotationVec, SmoothFakeRotater.lastYaw, SmoothFakeRotater.lastPitch);
        if (MathUtil.abs(rotation.yaw) < this.rotationAmount && MathUtil.abs(rotation.pitch) < this.rotationAmount) {
            this.rotateToGoal(SmoothFakeRotater.lastYaw + rotation.yaw, SmoothFakeRotater.lastPitch + rotation.pitch);
            return true;
        }
        float rotateYaw;
        float rotatePitch;
        if (MathUtil.abs(rotation.yaw) > MathUtil.abs(rotation.pitch)) {
            rotateYaw = PizzaClient.config.cropAuraSmoothRotation + MathUtil.randomFloat();
            rotatePitch = rotateYaw * MathUtil.abs(rotation.pitch / rotation.yaw);
        }
        else {
            rotatePitch = PizzaClient.config.cropAuraSmoothRotation + MathUtil.randomFloat();
            rotateYaw = rotatePitch * MathUtil.abs(rotation.yaw / rotation.pitch);
        }
        if (rotation.yaw < 0.0f) {
            rotateYaw = -rotateYaw;
        }
        if (rotation.pitch < 0.0f) {
            rotatePitch = -rotatePitch;
        }
        this.rotateToGoal(SmoothFakeRotater.lastYaw + rotateYaw, SmoothFakeRotater.lastPitch + rotatePitch);
        return false;
    }
    
    public boolean rotatingToGoal() {
        return !this.rotatingBack;
    }
    
    public boolean isRotatingBack() {
        return this.rotatingBack;
    }
    
    @Override
    public void setRotationHeadYaw() {
        PizzaClient.mc.field_71439_g.field_70759_as = SmoothFakeRotater.lastYaw + ((Rotation.getRotationDifference(this.rotationVec, SmoothFakeRotater.lastYaw, SmoothFakeRotater.lastPitch).yaw < 0.0f) ? ((float)(-this.rotationAmount)) : (this.rotationAmount * Utils.getPartialTicks()));
    }
    
    @Override
    public float setRotationHeadPitch() {
        return (SmoothFakeRotater.lastPitch + ((Rotation.getRotationDifference(this.rotationVec, SmoothFakeRotater.lastYaw, SmoothFakeRotater.lastPitch).pitch < 0.0f) ? ((float)(-this.rotationAmount)) : (this.rotationAmount * Utils.getPartialTicks()))) / 57.29578f;
    }
}
