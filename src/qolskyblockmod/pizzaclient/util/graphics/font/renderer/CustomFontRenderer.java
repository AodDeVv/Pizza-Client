// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.font.renderer;

import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.graphics.font.FontType;

public class CustomFontRenderer
{
    public static final FontType GREYCLIFF;
    public static final float RGB_SPEED = 3300.0f;
    public static float posX;
    public static float posY;
    public static float kerning;
    private static FontType fontType;
    
    public static float drawRainbowString(final String text, final float xCoord, final float yCoord) {
        beginRender();
        scaleChat();
        CustomFontRenderer.posX = xCoord;
        CustomFontRenderer.posY = yCoord;
        final long time = System.currentTimeMillis();
        for (int index = 0; index < text.length(); ++index) {
            final char ch = text.charAt(index);
            colorRainbow(time);
            CustomFontRenderer.posX += CustomFontRenderer.fontType.renderChar(ch) - CustomFontRenderer.kerning;
        }
        endRender();
        return CustomFontRenderer.posX;
    }
    
    private static void colorRainbow(final long time) {
        final int color = Color.HSBtoRGB((time - (int)(CustomFontRenderer.posX * 11.0f - CustomFontRenderer.posY * 11.0f)) % 3300L / 3300.0f, PizzaClient.config.rgbBrightness, 1.0f);
        GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 1.0f);
    }
    
    private static void colorRainbow() {
        final int color = Color.HSBtoRGB((System.currentTimeMillis() - (int)(CustomFontRenderer.posX * 11.0f - CustomFontRenderer.posY)) % 3300L / 3300.0f, PizzaClient.config.rgbBrightness, 1.0f);
        GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 1.0f);
    }
    
    private static void beginRender() {
        GL11.glPushMatrix();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179098_w();
        CustomFontRenderer.fontType.bindTexture();
        GL11.glTexParameteri(3553, 10241, 9728);
        GL11.glTexParameteri(3553, 10240, 9728);
    }
    
    private static void scaleChat() {
        final float scale = PizzaClient.mc.field_71474_y.field_96691_E;
    }
    
    private static void endRender() {
        GL11.glHint(3155, 4352);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179144_i(0);
    }
    
    public static void setFontType(final FontType type) {
        CustomFontRenderer.fontType = type;
    }
    
    public static void setGreycliffFont() {
        CustomFontRenderer.fontType = CustomFontRenderer.GREYCLIFF;
    }
    
    public static int getHeight() {
        return (CustomFontRenderer.fontType.fontHeight - 8) / 2;
    }
    
    static {
        GREYCLIFF = new FontType(new ResourceLocation("pizzaclient", "font/greycliff.ttf"), 18, false, true);
        CustomFontRenderer.kerning = 8.3f;
    }
}
