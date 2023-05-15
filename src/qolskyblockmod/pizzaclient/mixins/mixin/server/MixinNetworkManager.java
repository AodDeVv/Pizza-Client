// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.server;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.util.PacketUtil;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.network.Packet;
import io.netty.channel.SimpleChannelInboundHandler;

@Mixin(value = { NetworkManager.class }, priority = 2000)
public abstract class MixinNetworkManager extends SimpleChannelInboundHandler<Packet<?>>
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendPacket(final Packet<?> packetIn, final CallbackInfo ci) {
        if (PacketUtil.stopPackets) {
            PacketUtil.pausedPackets.add(packetIn);
            ci.cancel();
        }
    }
}
