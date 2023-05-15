// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.custom;

import qolskyblockmod.pizzaclient.util.graphics.font.renderer.CustomStringRenderer;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.graphics.custom.names.RainbowString;

public class ModMessageString implements CustomString
{
    public ModMessageString() {
    }
    
    public ModMessageString(final String text) {
        RainbowString.rgbList.put(text.hashCode(), this);
    }
    
    public static void addToList(final String text) {
        RainbowString.rgbList.put(text.hashCode(), new ModMessageString());
    }
    
    @Override
    public int render(final String text, final int x, final int y, final int color, final boolean shadow) {
        return PizzaClient.mc.field_71466_p.func_175065_a(text.substring(16), CustomStringRenderer.drawRainbowModMessage(x, y), (float)y, color, shadow);
    }
}
