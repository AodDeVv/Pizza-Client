// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.gui;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import qolskyblockmod.pizzaclient.core.events.SendChatMessageEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.Gui;

@Mixin({ GuiScreen.class })
public abstract class MixinGuiScreen extends Gui
{
    @Inject(method = { "sendChatMessage(Ljava/lang/String;Z)V" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendChatMessage(final String message, final boolean addToChat, final CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post((Event)new SendChatMessageEvent(message))) {
            ci.cancel();
        }
    }
}
