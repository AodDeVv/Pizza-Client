// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.renderer;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import java.util.Locale;
import net.minecraft.util.StringUtils;
import qolskyblockmod.pizzaclient.util.graphics.custom.CustomString;
import qolskyblockmod.pizzaclient.util.graphics.font.renderer.CustomStringRenderer;
import qolskyblockmod.pizzaclient.util.graphics.custom.ModMessageString;
import qolskyblockmod.pizzaclient.util.graphics.custom.names.RainbowString;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { FontRenderer.class }, priority = 999)
public class MixinFontRenderer
{
    @Inject(method = { "drawString(Ljava/lang/String;FFIZ)I" }, at = { @At("HEAD") }, cancellable = true)
    private void renderRainbowString(final String text, final float x, final float y, final int color, final boolean shadow, final CallbackInfoReturnable<Integer> cir) {
        if (text == null || text.length() == 0) {
            cir.setReturnValue((Object)(int)x);
            return;
        }
        if (PizzaClient.mc.field_71441_e != null) {
            final int index = RainbowString.rgbList.getIndex(text.hashCode());
            if (index == -1) {
                if (text.startsWith("§rPizzaClient > ")) {
                    ModMessageString.addToList(text);
                    CustomStringRenderer.setAlpha(color);
                    cir.setReturnValue((Object)RainbowString.rgbList.get(text.hashCode()).render(text, (int)x, (int)y, color, shadow));
                    return;
                }
                final String cleaned = text.replace("'", " '").replace(":", " : ");
                final String[] strings = cleaned.split(" ");
                if (strings.length == 0) {
                    final String lowerCase = StringUtils.func_76338_a(text).toLowerCase(Locale.ROOT);
                    if (RainbowString.rgbNames.containsKey(lowerCase)) {
                        try {
                            new RainbowString(text, cleaned, lowerCase);
                        }
                        catch (Exception e) {
                            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Caught and logged an exception when setting up rgb name. Please report this. String size: 0, String to render: " + text + ", cleaned: " + cleaned + ", lowercase: " + lowerCase));
                            e.printStackTrace();
                            return;
                        }
                        CustomStringRenderer.setAlpha(color);
                        cir.setReturnValue((Object)RainbowString.rgbList.get(text.hashCode()).render(text, (int)x, (int)y, color, shadow));
                        return;
                    }
                }
                else {
                    for (final String s : strings) {
                        if (s.length() > 2) {
                            final String lowerCase2 = StringUtils.func_76338_a(s).toLowerCase(Locale.ROOT);
                            if (RainbowString.rgbNames.containsKey(lowerCase2)) {
                                try {
                                    new RainbowString(text, s, lowerCase2);
                                }
                                catch (Exception e2) {
                                    PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Caught and logged an exception when setting up rgb name. Please report this. String to render: " + text + ", cleaned: " + cleaned + ", lowercase: " + lowerCase2));
                                    e2.printStackTrace();
                                    return;
                                }
                                CustomStringRenderer.setAlpha(color);
                                cir.setReturnValue((Object)RainbowString.rgbList.get(text.hashCode()).render(text, (int)x, (int)y, color, shadow));
                                return;
                            }
                        }
                    }
                }
                RainbowString.rgbList.put(text.hashCode(), null);
            }
            else {
                final CustomString custom = RainbowString.rgbList.getFromIndex(index);
                if (custom != null) {
                    CustomStringRenderer.setAlpha(color);
                    cir.setReturnValue((Object)custom.render(text, (int)x, (int)y, color, shadow));
                }
            }
        }
    }
}
