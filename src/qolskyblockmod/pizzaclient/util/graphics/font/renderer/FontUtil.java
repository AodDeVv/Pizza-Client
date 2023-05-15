// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.font.renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.mixins.mixin.accessor.AccessorFontRenderer;

public class FontUtil
{
    public static final int[] CHAR_WIDTH;
    public static final float RGB_SPEED = 3000.0f;
    public static final AccessorFontRenderer fontRenderer;
    public static final int[] colorCode;
    public static float posX;
    public static float posY;
    
    public static void drawCenteredString(final String text, final float x, final float y, final int color) {
        PizzaClient.mc.field_71466_p.func_175065_a(text, x - PizzaClient.mc.field_71466_p.func_78256_a(text) / 2.0f, y, color, false);
    }
    
    public static void drawCenteredStringNoEvent(final String text, final float x, final float y, final int color) {
        renderString(text, (int)(x - PizzaClient.mc.field_71466_p.func_78256_a(text) / 2.0f), (int)y, color, false);
    }
    
    public static void drawCenteredStringNoEvent(final String text, final int x, final int y, final int color) {
        renderString(text, (int)(x - PizzaClient.mc.field_71466_p.func_78256_a(text) / 2.0f), y, color, false);
    }
    
    public static void drawString(final String text, final float x, final float y, final int color) {
        PizzaClient.mc.field_71466_p.func_175065_a(text, x, y, color, false);
    }
    
    public static void drawBackground(final int width, final int height) {
        Gui.func_73734_a(0, 0, width, height, 1509949440);
    }
    
    public static void drawBackground(final int width, final int height, final int alpha) {
        Gui.func_73734_a(0, 0, width, height, new Color(0, 0, 0, alpha).getRGB());
    }
    
    public static void drawBackground(final float width, final float height) {
        Gui.func_73734_a(0, 0, (int)width, (int)height, 1509949440);
    }
    
    public static void drawRect(final double left, final double top, final double right, final double bottom, final int color) {
        Gui.func_73734_a((int)left, (int)top, (int)right, (int)bottom, color);
    }
    
    public static void drawRect(final double left, final double top, final double right, final double bottom, final Color color) {
        Gui.func_73734_a((int)left, (int)top, (int)right, (int)bottom, color.getRGB());
    }
    
    public static void renderChar(final char ch) {
        if (ch == ' ') {
            return;
        }
        if (ch == ' ') {
            return;
        }
        renderDefaultChar(ch);
    }
    
    public static void renderCharNoReturn(final char ch) {
        if (ch == ' ') {
            return;
        }
        if (ch == ' ') {
            return;
        }
        renderDefaultCharNoReturn(ch);
    }
    
    public static float renderDefaultChar(final int ch) {
        final int i = ch % 16 * 8;
        final int j = ch / 16 * 8;
        final int l = FontUtil.CHAR_WIDTH[ch];
        final float f = l - 0.01f;
        GL11.glBegin(5);
        GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
        GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
        GL11.glTexCoord2f((i + f - 1.0f) / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX + f - 1.0f, FontUtil.posY, 0.0f);
        GL11.glTexCoord2f((i + f - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX + f - 1.0f, FontUtil.posY + 7.99f, 0.0f);
        GL11.glEnd();
        return (float)l;
    }
    
    public static void renderDefaultCharNoReturn(final int ch) {
        final int i = ch % 16 * 8;
        final int j = ch / 16 * 8;
        final float f = FontUtil.CHAR_WIDTH[ch] - 0.01f;
        GL11.glBegin(5);
        GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
        GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
        GL11.glTexCoord2f((i + f - 1.0f) / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX + f - 1.0f, FontUtil.posY, 0.0f);
        GL11.glTexCoord2f((i + f - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX + f - 1.0f, FontUtil.posY + 7.99f, 0.0f);
        GL11.glEnd();
    }
    
    public static int getStringWidth(final char[] chars) {
        int i = 0;
        boolean flag = false;
        for (int j = 0; j < chars.length; ++j) {
            char c0 = chars[j];
            int k = getCharWidth(c0);
            if (k < 0 && j < chars.length - 1) {
                ++j;
                c0 = chars[j];
                if (c0 != 'l' && c0 != 'L') {
                    if (c0 == 'r' || c0 == 'R') {
                        flag = false;
                    }
                }
                else {
                    flag = true;
                }
                k = 0;
            }
            i += k;
            if (flag && k > 0) {
                ++i;
            }
        }
        return i;
    }
    
    public static int getRainbowStringWidth(final char[] chars) {
        int i = 0;
        boolean flag = false;
        for (int j = 1; j < chars.length; ++j) {
            char c0 = chars[j];
            int k = getCharWidth(c0);
            if (k < 0 && j < chars.length - 1) {
                ++j;
                c0 = chars[j];
                if (c0 != 'l' && c0 != 'L') {
                    if (c0 == 'r' || c0 == 'R') {
                        flag = false;
                    }
                }
                else {
                    flag = true;
                }
                k = 0;
            }
            i += k;
            if (flag && k > 0) {
                ++i;
            }
        }
        return i;
    }
    
    public static int getCharWidth(final char ch) {
        if (ch == ' ') {
            return 4;
        }
        if (ch > '\u00ff') {
            return FontUtil.fontRenderer.getGlyphWidths()[ch];
        }
        return FontUtil.CHAR_WIDTH[ch];
    }
    
    public static void bindUnicodeTexture(final int page) {
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getUnicodePageLocation(page));
    }
    
    public static float renderShadowedString(final String text, final int x, final int y, final int color) {
        GlStateManager.func_179141_d();
        FontUtil.fontRenderer.invokeResetStyles();
        return (float)MathUtil.max(FontUtil.fontRenderer.invokeRenderString(text, x + 1.0f, y + 1.0f, color, true), FontUtil.fontRenderer.invokeRenderString(text, (float)x, (float)y, color, false));
    }
    
    public static float renderString(final String text, final int x, final int y, final int color, final boolean shadow) {
        GlStateManager.func_179141_d();
        FontUtil.fontRenderer.invokeResetStyles();
        if (shadow) {
            return (float)MathUtil.max(FontUtil.fontRenderer.invokeRenderString(text, x + 1.0f, y + 1.0f, color, true), FontUtil.fontRenderer.invokeRenderString(text, (float)x, (float)y, color, false));
        }
        return (float)FontUtil.fontRenderer.invokeRenderString(text, (float)x, (float)y, color, false);
    }
    
    public static void drawRainbowText(final char[] input, final int x, final int y) {
        GlStateManager.func_179141_d();
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        for (final char ch : input) {
            FontUtil.posX += renderRainbowChar(ch, time, 0, true);
        }
        GL11.glShadeModel(7424);
        GlStateManager.func_179118_c();
        GlStateManager.func_179117_G();
    }
    
    public static void drawRainbowText(final String text, final int x, final int y) {
        GlStateManager.func_179141_d();
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        for (int i = 0; i < text.length(); ++i) {
            FontUtil.posX += renderRainbowChar(text.charAt(i), time, 0, true);
        }
        GL11.glShadeModel(7424);
        GlStateManager.func_179118_c();
        GlStateManager.func_179117_G();
    }
    
    public static float renderRainbowChar(final int ch, final long time, final int rgb, final boolean shadow) {
        if (ch == 32 || ch == 160) {
            return 4.0f;
        }
        if (ch > 255) {
            return 0.0f;
        }
        final int l = FontUtil.CHAR_WIDTH[ch];
        final float width = l - 0.01f;
        final long y = (long)(FontUtil.posY * 11.0f);
        long position = time - ((long)(FontUtil.posX * 11.0f) - y);
        int color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float blue = (color >> 8 & 0xFF) / 255.0f;
        final float green = (color & 0xFF) / 255.0f;
        position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
        color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
        final float red2 = (color >> 16 & 0xFF) / 255.0f;
        final float blue2 = (color >> 8 & 0xFF) / 255.0f;
        final float green2 = (color & 0xFF) / 255.0f;
        final int i = ch % 16 * 8;
        final int j = ch / 16 * 8;
        if (shadow) {
            ++FontUtil.posX;
            ++FontUtil.posY;
            GL11.glColor4f(red / 4.0f, green / 4.0f, blue / 4.0f, 1.0f);
            GL11.glBegin(5);
            GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
            GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
            GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
            GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
            GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, j / 128.0f);
            GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY, 0.0f);
            GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
            GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY + 7.99f, 0.0f);
            GL11.glEnd();
            --FontUtil.posX;
            --FontUtil.posY;
        }
        GL11.glBegin(7);
        GL11.glColor4f(red, green, blue, 1.0f);
        GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
        GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
        GL11.glColor4f(red2, green2, blue2, 1.0f);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY + 7.99f, 0.0f);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY, 0.0f);
        GL11.glEnd();
        return (float)l;
    }
    
    public static int generateTextureID(final ResourceLocation location) {
        final SimpleTexture texture = new SimpleTexture(location);
        PizzaClient.mc.field_71446_o.func_110579_a(location, (ITextureObject)texture);
        return texture.func_110552_b();
    }
    
    static {
        CHAR_WIDTH = new int[256];
        fontRenderer = (AccessorFontRenderer)PizzaClient.mc.field_71466_p;
        colorCode = FontUtil.fontRenderer.getColorCodes();
        BufferedImage bufferedimage;
        try {
            bufferedimage = Utils.readBufferedImage(new ResourceLocation("textures/font/ascii.png"));
        }
        catch (IOException ioexception) {
            throw new RuntimeException(ioexception);
        }
        final int h = bufferedimage.getWidth();
        final int j = bufferedimage.getHeight();
        final int[] aint = new int[h * j];
        bufferedimage.getRGB(0, 0, h, j, aint, 0, h);
        final int k = j / 16;
        final int l = h / 16;
        final float f = 8.0f / l;
        for (int j2 = 0; j2 < 256; ++j2) {
            final int k2 = j2 % 16;
            final int l2 = j2 / 16;
            if (j2 == 32) {
                FontUtil.CHAR_WIDTH[j2] = 4;
            }
            int i2;
            for (i2 = l - 1; i2 >= 0; --i2) {
                final int j3 = k2 * l + i2;
                boolean flag = true;
                for (int k3 = 0; k3 < k; ++k3) {
                    final int l3 = (l2 * l + k3) * h;
                    if ((aint[j3 + l3] >> 24 & 0xFF) != 0x0) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            ++i2;
            FontUtil.CHAR_WIDTH[j2] = (int)(0.5 + i2 * f) + 1;
        }
    }
}
