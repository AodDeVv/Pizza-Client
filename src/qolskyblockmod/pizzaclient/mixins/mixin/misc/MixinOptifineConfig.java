// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.misc;

import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = { "Config" }, remap = false)
public class MixinOptifineConfig
{
    @Overwrite
    public static boolean isFastRender() {
        return false;
    }
}
