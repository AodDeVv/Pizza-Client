// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.core.guioverlay;

import qolskyblockmod.pizzaclient.util.RenderUtil;

public class GuiLocation
{
    public float x;
    public float y;
    
    public GuiLocation(final int x, final int y) {
        this(x / (float)RenderUtil.resolution.func_78328_b(), y / (float)RenderUtil.resolution.func_78328_b());
    }
    
    public GuiLocation(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(final GuiLocation loc) {
        return loc.x == this.x && loc.y == this.y;
    }
    
    @Override
    public String toString() {
        return "{x=" + this.x + ", y=" + this.y + "}";
    }
}
