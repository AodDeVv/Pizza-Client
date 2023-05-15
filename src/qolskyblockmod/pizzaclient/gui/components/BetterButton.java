// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.gui.components;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class BetterButton extends GuiButton
{
    public BetterButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }
    
    public void func_146112_a(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.field_146125_m) {
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
            this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
            func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, this.field_146123_n ? 1515541845 : 1426392325);
            this.func_146119_b(mc, mouseX, mouseY);
            int j = 14737632;
            if (this.packedFGColour != 0) {
                j = this.packedFGColour;
            }
            else if (!this.field_146124_l) {
                j = 10526880;
            }
            else if (this.field_146123_n) {
                j = 16777120;
            }
            this.func_73732_a(mc.field_71466_p, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, j);
        }
    }
}
