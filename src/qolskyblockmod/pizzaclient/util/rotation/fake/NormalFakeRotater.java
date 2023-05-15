// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.fake;

import qolskyblockmod.pizzaclient.util.rotation.Rotation;
import net.minecraft.util.Vec3;

public abstract class NormalFakeRotater extends FakeRotater
{
    public float rotateYaw;
    public float rotatePitch;
    
    public NormalFakeRotater(final Vec3 rotationVec) {
        final Rotation rotation = Rotation.getRotation(rotationVec);
        this.rotateYaw = rotation.yaw;
        this.rotatePitch = rotation.pitch;
    }
    
    public NormalFakeRotater(final Rotation rotation) {
        this.rotateYaw = rotation.yaw;
        this.rotatePitch = rotation.pitch;
    }
    
    @Override
    public void onPlayerMovePre() {
        this.storeCurrentRotation();
        this.rotateToGoal(this.rotateYaw, this.rotatePitch);
    }
    
    @Override
    public void onPlayerMovePost() {
        this.interact();
        this.rotateBack();
        this.terminate();
    }
    
    public abstract void interact();
}
