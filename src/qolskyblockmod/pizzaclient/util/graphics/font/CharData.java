// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.font;

import org.lwjgl.opengl.GL11;

public class CharData
{
    public int width;
    public int height;
    public int storedX;
    public int storedY;
    
    public float renderChar(final float x, final float y) {
        final float renderSRCX = this.storedX / 512.0f;
        final float renderSRCY = this.storedY / 512.0f;
        final float renderSRCWidth = this.width / 512.0f;
        final float renderSRCHeight = this.height / 512.0f;
        GL11.glBegin(4);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d((double)(x + this.width), (double)y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d((double)x, (double)(y + this.height));
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d((double)x, (double)(y + this.height));
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d((double)(x + this.width), (double)(y + this.height));
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d((double)(x + this.width), (double)y);
        GL11.glEnd();
        return (float)this.width;
    }
    
    public float renderChar(final float x, final float y, final float imgSize) {
        final float renderSRCX = this.storedX / imgSize;
        final float renderSRCY = this.storedY / imgSize;
        final float renderSRCWidth = this.width / imgSize;
        final float renderSRCHeight = this.height / imgSize;
        GL11.glBegin(4);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d((double)(x + this.width), (double)y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d((double)x, (double)(y + this.height));
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d((double)x, (double)(y + this.height));
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d((double)(x + this.width), (double)(y + this.height));
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d((double)(x + this.width), (double)y);
        GL11.glEnd();
        return (float)this.width;
    }
}
