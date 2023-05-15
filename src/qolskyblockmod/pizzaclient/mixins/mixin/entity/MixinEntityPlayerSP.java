// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.entity;

import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.player.TPAuraHelper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { EntityPlayerSP.class }, priority = 999)
public abstract class MixinEntityPlayerSP
{
    @Shadow
    private boolean field_175171_bO;
    @Shadow
    private boolean field_175170_bN;
    @Shadow
    private float field_175164_bL;
    @Shadow
    private double field_175166_bJ;
    @Shadow
    private double field_175172_bI;
    @Shadow
    private double field_175167_bK;
    @Shadow
    private int field_175168_bP;
    @Shadow
    private float field_175165_bM;
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    private void onPlayerWalkPre(final CallbackInfo ci) {
        if (TPAuraHelper.path != null && PizzaClient.mc.field_71462_r == null) {
            if (TPAuraHelper.isFlyActive()) {
                TPAuraHelper.teleport();
            }
            else {
                TPAuraHelper.disable();
            }
        }
        if (FakeRotater.rotater != null) {
            FakeRotater.rotater.onPlayerMovePre();
            final boolean flag = PizzaClient.mc.field_71439_g.func_70051_ag();
            if (flag != this.field_175171_bO) {
                if (flag) {
                    PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)PizzaClient.mc.field_71439_g, C0BPacketEntityAction.Action.START_SPRINTING));
                }
                else {
                    PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)PizzaClient.mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SPRINTING));
                }
                this.field_175171_bO = flag;
            }
            final boolean flag2 = PizzaClient.mc.field_71439_g.func_70093_af();
            if (flag2 != this.field_175170_bN) {
                if (flag2) {
                    PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)PizzaClient.mc.field_71439_g, C0BPacketEntityAction.Action.START_SNEAKING));
                }
                else {
                    PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)PizzaClient.mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SNEAKING));
                }
                this.field_175170_bN = flag2;
            }
            if (PizzaClient.mc.func_175606_aa() == PizzaClient.mc.field_71439_g) {
                final double d0 = PizzaClient.mc.field_71439_g.field_70165_t - this.field_175172_bI;
                final double d2 = PizzaClient.mc.field_71439_g.func_174813_aQ().field_72338_b - this.field_175166_bJ;
                final double d3 = PizzaClient.mc.field_71439_g.field_70161_v - this.field_175167_bK;
                final double d4 = PizzaClient.mc.field_71439_g.field_70177_z - this.field_175164_bL;
                final double d5 = PizzaClient.mc.field_71439_g.field_70125_A - this.field_175165_bM;
                boolean flag3 = d0 * d0 + d2 * d2 + d3 * d3 > 9.0E-4 || this.field_175168_bP >= 20;
                final boolean flag4 = d4 != 0.0 || d5 != 0.0;
                if (PizzaClient.mc.field_71439_g.field_70154_o == null) {
                    if (flag3 && flag4) {
                        PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.func_174813_aQ().field_72338_b, PizzaClient.mc.field_71439_g.field_70161_v, PizzaClient.mc.field_71439_g.field_70177_z, PizzaClient.mc.field_71439_g.field_70125_A, PizzaClient.mc.field_71439_g.field_70122_E));
                    }
                    else if (flag3) {
                        PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C04PacketPlayerPosition(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.func_174813_aQ().field_72338_b, PizzaClient.mc.field_71439_g.field_70161_v, PizzaClient.mc.field_71439_g.field_70122_E));
                    }
                    else if (flag4) {
                        PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C05PacketPlayerLook(PizzaClient.mc.field_71439_g.field_70177_z, PizzaClient.mc.field_71439_g.field_70125_A, PizzaClient.mc.field_71439_g.field_70122_E));
                    }
                    else {
                        PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C03PacketPlayer(PizzaClient.mc.field_71439_g.field_70122_E));
                    }
                }
                else {
                    PizzaClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(PizzaClient.mc.field_71439_g.field_70159_w, -999.0, PizzaClient.mc.field_71439_g.field_70179_y, PizzaClient.mc.field_71439_g.field_70177_z, PizzaClient.mc.field_71439_g.field_70125_A, PizzaClient.mc.field_71439_g.field_70122_E));
                    flag3 = false;
                }
                ++this.field_175168_bP;
                if (flag3) {
                    this.field_175172_bI = PizzaClient.mc.field_71439_g.field_70165_t;
                    this.field_175166_bJ = PizzaClient.mc.field_71439_g.func_174813_aQ().field_72338_b;
                    this.field_175167_bK = PizzaClient.mc.field_71439_g.field_70161_v;
                    this.field_175168_bP = 0;
                }
                if (flag4) {
                    this.field_175164_bL = PizzaClient.mc.field_71439_g.field_70177_z;
                    this.field_175165_bM = PizzaClient.mc.field_71439_g.field_70125_A;
                }
            }
            FakeRotater.rotater.onPlayerMovePost();
            ci.cancel();
        }
        else {
            FakeRotater.lastPitch = 420.0f;
        }
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;doesGuiPauseGame()Z"))
    private boolean useChatInPortal(final GuiScreen gui) {
        return !(gui instanceof GuiContainer) || gui.func_73868_f();
    }
}
