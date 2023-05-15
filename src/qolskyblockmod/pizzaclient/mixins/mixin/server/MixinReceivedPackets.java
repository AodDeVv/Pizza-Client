// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.server;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.features.player.VelocityHook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import qolskyblockmod.pizzaclient.util.SBInfo;
import net.minecraft.network.play.server.S27PacketExplosion;
import qolskyblockmod.pizzaclient.features.macros.ai.failsafes.Failsafes;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroState;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.features.player.AutoPowderChest;
import net.minecraft.util.EnumParticleTypes;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { NetHandlerPlayClient.class }, priority = 500)
public abstract class MixinReceivedPackets
{
    @Inject(method = { "handleParticles" }, at = { @At("HEAD") })
    private void onParticle(final S2APacketParticles packetIn, final CallbackInfo ci) {
        if (PizzaClient.mc.field_71462_r == null && PizzaClient.config.powderChestMacro && packetIn.func_179749_a() == EnumParticleTypes.CRIT) {
            AutoPowderChest.onParticle(packetIn);
        }
    }
    
    @Inject(method = { "handlePlayerPosLook" }, at = { @At("HEAD") })
    private void onPacketPosLook(final S08PacketPlayerPosLook packetIn, final CallbackInfo ci) {
        if (MacroBuilder.toggled && MacroBuilder.state == MacroState.ACTIVE) {
            float yaw = packetIn.func_148931_f();
            if (packetIn.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
                yaw += PizzaClient.mc.field_71439_g.field_70177_z;
            }
            if (PlayerUtil.getPositionEyes().func_72436_e(new Vec3(packetIn.func_148932_c(), packetIn.func_148928_d(), packetIn.func_148933_e())) < 16.0) {
                if (PizzaClient.config.rotationFailsafe) {
                    final float diffYaw = MathUtil.abs(PizzaClient.mc.field_71439_g.field_70177_z - yaw);
                    if (diffYaw > 10.0f) {
                        Failsafes.onPacketPosLook(diffYaw);
                    }
                }
            }
            else {
                MacroBuilder.updatePosition();
                Failsafes.onChangePosition();
            }
        }
    }
    
    @Inject(method = { "handleExplosion" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/network/play/server/S27PacketExplosion;func_149149_c()F") }, cancellable = true)
    private void modifyExplosionKB(final S27PacketExplosion packetIn, final CallbackInfo ci) {
        if (PizzaClient.config.antiKb && SBInfo.inSkyblock) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "handleEntityVelocity" }, at = { @At("HEAD") }, cancellable = true)
    private void cancelKB(final S12PacketEntityVelocity packetIn, final CallbackInfo ci) {
        if (packetIn.func_149412_c() == PizzaClient.mc.field_71439_g.func_145782_y() && SBInfo.inSkyblock) {
            if (this.shouldKeepKB()) {
                if (PizzaClient.config.increasedVelocity) {
                    VelocityHook.setupVelocity();
                }
            }
            else if (PizzaClient.config.antiKb) {
                ci.cancel();
            }
        }
    }
    
    private boolean shouldKeepKB() {
        if (PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v)).func_177230_c() == Blocks.field_150353_l) {
            return true;
        }
        final ItemStack held = PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g();
        if (held == null) {
            return false;
        }
        final String name = held.func_82833_r();
        return name.contains("Leaping Sword") || name.contains("Bonzo's Staff") || name.contains("Jerry-chine");
    }
}
