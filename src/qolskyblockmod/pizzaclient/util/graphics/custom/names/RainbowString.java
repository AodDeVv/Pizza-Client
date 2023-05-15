// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.custom.names;

import java.util.HashMap;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Locale;
import com.google.gson.JsonElement;
import qolskyblockmod.pizzaclient.util.graphics.font.renderer.CustomStringRenderer;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.collections.fastutil.maps.IntObjectOpenHashMap;
import java.util.Map;
import qolskyblockmod.pizzaclient.util.graphics.custom.CustomString;

public class RainbowString implements CustomString
{
    public String stringBefore;
    public char[] rainbowChars;
    public String stringAfter;
    public static final Map<String, char[]> rgbNames;
    public static final IntObjectOpenHashMap<CustomString> rgbList;
    private static Thread fetcher;
    
    public RainbowString(final String msg, final String rainbowMessage, final String lowerCase) {
        this.rainbowChars = RainbowString.rgbNames.get(lowerCase);
        if (this.rainbowChars[0] == '§') {
            new NonRGBString(msg, rainbowMessage, lowerCase);
            return;
        }
        final int index = msg.indexOf(rainbowMessage);
        this.stringBefore = msg.substring(0, index);
        if (this.rainbowChars[1] == '[') {
            if (this.stringBefore.indexOf(93) == -1) {
                this.replaceRankInChars();
            }
            else if (this.stringBefore.indexOf(58) == -1 && (this.stringBefore.contains("[VIP") || this.stringBefore.contains("[MVP"))) {
                this.stringBefore = this.stringBefore.split("\\[")[0];
            }
        }
        final String colorCode = Utils.getColorCode(rainbowMessage);
        this.stringAfter = ((colorCode != null) ? (colorCode + msg.substring(index + rainbowMessage.length())) : msg.substring(index + rainbowMessage.length()));
        RainbowString.rgbList.put(msg.hashCode(), this);
    }
    
    private void replaceRankInChars() {
        for (int i = 2; i < this.rainbowChars.length; ++i) {
            final char ch = this.rainbowChars[i];
            if (ch == ']') {
                final int from = i + 2;
                final int newLength = this.rainbowChars.length - from;
                final char[] copy = new char[newLength + 1];
                System.arraycopy(this.rainbowChars, from, copy, 1, newLength);
                copy[0] = this.rainbowChars[0];
                this.rainbowChars = copy;
                break;
            }
        }
    }
    
    @Override
    public int render(final String text, final int x, final int y, final int color, final boolean shadow) {
        return PizzaClient.mc.field_71466_p.func_175065_a(this.stringAfter, CustomStringRenderer.drawRainbowName(this.rainbowChars, PizzaClient.mc.field_71466_p.func_175065_a(this.stringBefore, (float)x, (float)y, color, shadow), y, shadow), (float)y, color, shadow);
    }
    
    public static void updateList() {
        if (!RainbowString.fetcher.isAlive()) {
            final JsonObject respose;
            final Iterator<Map.Entry<String, JsonElement>> iterator;
            Map.Entry<String, JsonElement> jsons;
            (RainbowString.fetcher = new Thread(() -> {
                respose = Utils.getJson("https://gist.githubusercontent.com/PizzaboiBestLegit/c65896b963454b679eb68a29435ccb19/raw/gistfile1.txt").getAsJsonObject();
                RainbowString.rgbNames.clear();
                respose.entrySet().iterator();
                while (iterator.hasNext()) {
                    jsons = iterator.next();
                    RainbowString.rgbNames.put(jsons.getKey().toLowerCase(Locale.ROOT), jsons.getValue().getAsString().replace("\u00c2", "").toCharArray());
                }
            })).start();
        }
    }
    
    static {
        rgbNames = new HashMap<String, char[]>();
        rgbList = new IntObjectOpenHashMap<CustomString>(2048, 0.7f);
        RainbowString.fetcher = new Thread(() -> {});
    }
}
