// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.misc;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.listeners.SoundListener;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SoundManager.class })
public class MixinSoundManager
{
    @Inject(method = { "playSound" }, at = { @At("HEAD") }, cancellable = true)
    private void onPlaySound(final ISound p_sound, final CallbackInfo ci) {
        if (PizzaClient.mc.field_71441_e == null) {
            return;
        }
        if (SoundListener.onPlaySound(p_sound, p_sound.func_147650_b().func_110623_a())) {
            ci.cancel();
        }
    }
}
