// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.rotation.fake;

import net.minecraft.util.MovingObjectPosition;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import qolskyblockmod.pizzaclient.util.rotation.Rotation;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.PizzaClient;

public abstract class FakeRotater
{
    public static float lastYaw;
    public static float lastPitch;
    public static FakeRotater rotater;
    public float realYaw;
    public float realPitch;
    
    public FakeRotater() {
        if (FakeRotater.lastPitch == 420.0f) {
            FakeRotater.lastPitch = PizzaClient.mc.field_71439_g.field_70125_A;
            FakeRotater.lastYaw = PizzaClient.mc.field_71439_g.field_70177_z;
        }
    }
    
    public void storeCurrentRotation() {
        this.realYaw = PizzaClient.mc.field_71439_g.field_70177_z;
        this.realPitch = PizzaClient.mc.field_71439_g.field_70125_A;
    }
    
    public void rotateBack() {
        FakeRotater.lastYaw = PizzaClient.mc.field_71439_g.field_70177_z;
        FakeRotater.lastPitch = PizzaClient.mc.field_71439_g.field_70125_A;
        PizzaClient.mc.field_71439_g.field_70125_A = this.realPitch;
        PizzaClient.mc.field_71439_g.field_70177_z = this.realYaw;
    }
    
    public void rotateToGoal(final float yaw, final float pitch) {
        PizzaClient.mc.field_71439_g.field_70177_z = yaw;
        PizzaClient.mc.field_71439_g.field_70125_A = pitch;
    }
    
    public void rotateToGoal(final Vec3 rotateVec) {
        final Rotation rotation = Rotation.getRotation(rotateVec);
        PizzaClient.mc.field_71439_g.field_70177_z = rotation.yaw;
        PizzaClient.mc.field_71439_g.field_70125_A = rotation.pitch;
    }
    
    public void onOpenGui() {
    }
    
    public void use() {
        FakeRotater.rotater = this;
    }
    
    public void terminate() {
        FakeRotater.rotater = null;
    }
    
    public abstract void onPlayerMovePre();
    
    public abstract void onPlayerMovePost();
    
    public static void leftClick(final Vec3 rotationVec) {
        FakeRotater.rotater = new NormalFakeRotater(rotationVec) {
            @Override
            public void interact() {
                PizzaClient.mc.field_71439_g.func_71038_i();
            }
        };
    }
    
    public static void rotate(final Vec3 rotationVec) {
        FakeRotater.rotater = new NormalFakeRotater(rotationVec) {
            @Override
            public void interact() {
            }
        };
    }
    
    public static void rotate(final Rotation rotation) {
        FakeRotater.rotater = new NormalFakeRotater(rotation) {
            @Override
            public void interact() {
            }
        };
    }
    
    public static void clickEntity(final Rotation rotation, final Entity entity) {
        FakeRotater.rotater = new NormalFakeRotater(rotation) {
            @Override
            public void interact() {
                PizzaClient.mc.field_71442_b.func_78768_b((EntityPlayer)PizzaClient.mc.field_71439_g, entity);
            }
        };
    }
    
    public static void clickEntity(final Vec3 rotationVec, final Entity entity) {
        FakeRotater.rotater = new NormalFakeRotater(rotationVec) {
            @Override
            public void interact() {
                PizzaClient.mc.field_71442_b.func_78768_b((EntityPlayer)PizzaClient.mc.field_71439_g, entity);
            }
        };
    }
    
    public static void rightClick(final Vec3 rotationVec, final BlockPos hitPos, final int clickAmount) {
        FakeRotater.rotater = new NormalFakeRotater(rotationVec) {
            @Override
            public void interact() {
                final MovingObjectPosition position = VecUtil.calculateInterceptLook(hitPos, rotationVec, 4.5f);
                if (position != null) {
                    for (int i = 0; i < clickAmount; ++i) {
                        if (PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), hitPos, position.field_178784_b, position.field_72307_f)) {
                            PizzaClient.mc.field_71439_g.func_71038_i();
                        }
                    }
                }
            }
        };
    }
    
    public static void rightClickWithItem(final Vec3 rotationVec, final BlockPos hitPos, final int itemSlot) {
        FakeRotater.rotater = new NormalFakeRotater(rotationVec) {
            @Override
            public void interact() {
                final MovingObjectPosition position = VecUtil.calculateInterceptLook(hitPos, rotationVec, 4.5f);
                if (position != null) {
                    final int lastSlot = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
                    PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = itemSlot;
                    if (PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), hitPos, position.field_178784_b, position.field_72307_f)) {
                        PizzaClient.mc.field_71439_g.func_71038_i();
                    }
                    PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = lastSlot;
                }
            }
        };
    }
    
    public static void rightClickWithItem(final Vec3 rotationVec, final BlockPos hitPos, final int clickAmount, final int itemSlot) {
        FakeRotater.rotater = new NormalFakeRotater(rotationVec) {
            @Override
            public void interact() {
                final MovingObjectPosition position = VecUtil.calculateInterceptLook(hitPos, rotationVec, 4.5f);
                if (position != null) {
                    final int lastSlot = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
                    PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = itemSlot;
                    for (int i = 0; i < clickAmount; ++i) {
                        if (PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), hitPos, position.field_178784_b, position.field_72307_f)) {
                            PizzaClient.mc.field_71439_g.func_71038_i();
                        }
                    }
                    PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = lastSlot;
                }
            }
        };
    }
    
    public void setRotationHeadYaw() {
        PizzaClient.mc.field_71439_g.field_70759_as = FakeRotater.lastYaw;
    }
    
    public float setRotationHeadPitch() {
        return FakeRotater.lastPitch / 57.29578f;
    }
    
    static {
        FakeRotater.lastPitch = 420.0f;
    }
}
