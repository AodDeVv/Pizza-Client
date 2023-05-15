// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.gui.components;

import net.minecraft.client.audio.SoundHandler;
import qolskyblockmod.pizzaclient.util.graphics.font.renderer.FontUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiElement;
import net.minecraft.client.gui.GuiButton;

public class LocationButton extends GuiButton
{
    private float x;
    private float y;
    private float x2;
    private float y2;
    public GuiElement element;
    
    public LocationButton(final GuiElement element) {
        super(-1, 0, 0, (String)null);
        this.element = element;
        this.x = this.element.getActualX() - 5.0f;
        this.y = this.element.getActualY() - 5.0f;
        this.x2 = this.x + this.element.getWidth() + 5.0f;
        this.y2 = this.y + this.element.getHeight() + 5.0f;
    }
    
    private void refreshLocations() {
        this.x = this.element.getActualX() - 2.0f;
        this.y = this.element.getActualY() - 2.0f;
        this.x2 = this.x + this.element.getWidth() + 4.0f;
        this.y2 = this.y + this.element.getHeight() + 4.0f;
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        this.refreshLocations();
        this.field_146123_n = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x2 && mouseY < this.y2);
        final Color c = new Color(250, 250, 250, this.field_146123_n ? 120 : 60);
        FontUtil.drawRect(this.x, this.y, this.x2, this.y2, c.getRGB());
        this.element.demoRender();
    }
    
    public boolean func_146116_c(final Minecraft mc, final int mouseX, final int mouseY) {
        return this.field_146124_l && this.field_146125_m && this.field_146123_n;
    }
    
    public void func_146113_a(final SoundHandler soundHandlerIn) {
    }
    
    public GuiElement getElement() {
        return this.element;
    }
}
