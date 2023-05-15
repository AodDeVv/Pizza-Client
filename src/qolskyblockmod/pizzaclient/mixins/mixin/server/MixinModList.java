// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.server;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import java.util.Map;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { FMLHandshakeMessage.ModList.class }, remap = false)
public class MixinModList
{
    @Shadow
    private Map<String, String> modTags;
    
    @Inject(method = { "<init>(Ljava/util/List;)V" }, at = { @At("RETURN") })
    private void removeMod(final CallbackInfo ci) {
        if (!PizzaClient.mc.func_71387_A()) {
            this.modTags.remove("pizzaclient");
        }
    }
}
