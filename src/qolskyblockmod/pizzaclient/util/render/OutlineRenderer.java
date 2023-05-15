// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.render;

import net.minecraft.entity.Entity;
import java.awt.Color;

public class OutlineRenderer
{
    public final FastFramebuffer framebuffer;
    public int renderColor;
    
    public OutlineRenderer(final Color color) {
        this.framebuffer = new FastFramebuffer();
        this.renderColor = color.getRGB();
    }
    
    public OutlineRenderer(final int color) {
        this.framebuffer = new FastFramebuffer();
        this.renderColor = color;
    }
    
    public OutlineRenderer() {
        this.framebuffer = new FastFramebuffer();
    }
    
    public void setColor(final Color c) {
        this.renderColor = c.getRGB();
    }
    
    public void setColor(final int color) {
        this.renderColor = color;
    }
    
    public void updateFramebuffer() {
        this.framebuffer.updateFramebuffer();
    }
    
    public void addEntity(final Entity entity) {
        RenderType.addEntity(this, entity);
    }
}
