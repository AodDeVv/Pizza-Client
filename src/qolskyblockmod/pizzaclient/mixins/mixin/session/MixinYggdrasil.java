// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.session;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import qolskyblockmod.pizzaclient.features.misc.SessionProtection;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { YggdrasilMinecraftSessionService.class }, priority = Integer.MAX_VALUE, remap = false)
public class MixinYggdrasil
{
    @ModifyVariable(method = { "joinServer" }, at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private String onJoinServer(final String value) {
        return SessionProtection.changedToken ? SessionProtection.changed : value;
    }
}
