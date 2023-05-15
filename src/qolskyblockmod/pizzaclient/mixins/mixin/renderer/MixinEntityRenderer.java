// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.renderer;

import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityRenderer.class })
public class MixinEntityRenderer
{
    @ModifyVariable(method = { "setupCameraTransform" }, at = @At("STORE"), ordinal = 2)
    private float antiBlindness(final float f1) {
        return PizzaClient.config.antiBlindness ? -1.0f : f1;
    }
    
    @ModifyVariable(method = { "updateCameraAndRender" }, at = @At("STORE"), ordinal = 0)
    private boolean flag(final boolean bool) {
        return PizzaClient.config.noEscMenu || bool;
    }
    
    @Inject(method = { "renderHand" }, at = { @At("HEAD") }, cancellable = true)
    private void onRenderHand(final CallbackInfo ci) {
        if (PizzaClient.config.stopRenderingHand) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "orientCamera" }, at = { @At("HEAD") })
    private void onOrientCamera(final CallbackInfo ci) {
        if (FakeRotater.lastPitch != 420.0f) {
            if (FakeRotater.rotater != null) {
                FakeRotater.rotater.setRotationHeadYaw();
            }
            else {
                PizzaClient.mc.field_71439_g.field_70759_as = FakeRotater.lastYaw;
            }
        }
        if (PizzaClient.rotater != null) {
            if (PizzaClient.mc.field_71462_r == null) {
                PizzaClient.rotater.add();
            }
            else {
                PizzaClient.rotater = null;
                Rotater.rotating = false;
            }
        }
    }
    
    @Inject(method = { "getMouseOver" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getBlockReachDistance()F") }, cancellable = true)
    private void changeMouseOver(final float partialTicks, final CallbackInfo ci) {
        if (PizzaClient.config.ignoreEntities) {
            PizzaClient.mc.field_71476_x = PizzaClient.mc.func_175606_aa().func_174822_a(4.5, partialTicks);
            ci.cancel();
        }
    }
    
    @ModifyVariable(method = { "getMouseOver" }, at = @At("STORE"), ordinal = 2)
    private double changeDistance(final double value) {
        return PizzaClient.config.hitThroughBlocks ? 0.0 : value;
    }
}
