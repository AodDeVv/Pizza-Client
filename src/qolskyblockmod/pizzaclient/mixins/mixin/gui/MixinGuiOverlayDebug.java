// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.gui;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.client.gui.GuiOverlayDebug;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiOverlayDebug.class })
public class MixinGuiOverlayDebug
{
    @Redirect(method = { "call" }, at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 5))
    private String redirectWorldDate(final String s, final Object[] objects) {
        return "Local Difficulty: " + Utils.DECIMAL_FORMAT.format((float)objects[0]) + " (Day " + Utils.DECIMAL_FORMAT.format(Utils.getExactDay()) + ")";
    }
}
