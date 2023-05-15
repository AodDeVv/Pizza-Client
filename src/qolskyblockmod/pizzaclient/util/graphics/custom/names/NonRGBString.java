// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.custom.names;

import qolskyblockmod.pizzaclient.util.graphics.font.renderer.FontUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.StringUtils;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.graphics.custom.CustomString;

public class NonRGBString implements CustomString
{
    public String name;
    public final String oldName;
    public final boolean renderRank;
    public String stringAfter;
    public String stringBefore;
    
    public NonRGBString(final String text, final String custom, final String lowerCase) {
        this.oldName = custom;
        this.name = new String(RainbowString.rgbNames.get(lowerCase));
        RainbowString.rgbList.put(text.hashCode(), this);
        int index = text.indexOf(custom);
        this.stringBefore = text.substring(0, index);
        final String colorCode = Utils.getColorCode(custom);
        this.stringAfter = ((colorCode != null) ? (colorCode + text.substring(index + custom.length())) : text.substring(index + custom.length()));
        if (this.name.charAt(2) == '[') {
            final String stripped = StringUtils.func_76338_a(text);
            if (stripped.indexOf(93) != -1 && (stripped.contains("[VIP") || stripped.contains("[MVP"))) {
                index = text.indexOf(58);
                if (stripped.contains("] " + StringUtils.func_76338_a(this.oldName)) && (index == -1 || index > text.indexOf(lowerCase))) {
                    this.renderRank = true;
                }
                else {
                    this.name = NonRGBString.RANK_PATTERN.matcher(this.name).replaceAll("");
                    this.renderRank = false;
                }
                return;
            }
        }
        this.name = NonRGBString.RANK_PATTERN.matcher(this.name).replaceAll("");
        this.renderRank = false;
    }
    
    @Override
    public int render(final String text, final int x, final int y, final int color, final boolean shadow) {
        if (this.renderRank) {
            return PizzaClient.mc.field_71466_p.func_175065_a(this.stringAfter, FontUtil.renderString(this.name, PizzaClient.mc.field_71466_p.func_175065_a(NonRGBString.RANK_PATTERN.matcher(this.stringBefore).replaceAll(""), (float)x, (float)y, color, shadow), y, color, shadow), (float)y, color, shadow);
        }
        return PizzaClient.mc.field_71466_p.func_175065_a(this.stringAfter, FontUtil.renderString(this.name, PizzaClient.mc.field_71466_p.func_175065_a(this.stringBefore, (float)x, (float)y, color, shadow), y, color, shadow), (float)y, color, shadow);
    }
}
