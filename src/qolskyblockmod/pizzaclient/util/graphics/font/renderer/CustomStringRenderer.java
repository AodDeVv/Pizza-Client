// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.font.renderer;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.lwjgl.opengl.GL11;

public class CustomStringRenderer
{
    public static final char[] modMessageChars;
    public static float alpha;
    
    public static void setAlpha(final int color) {
        if ((color & 0xFC000000) == 0x0) {
            CustomStringRenderer.alpha = ((color | 0xFF000000) >> 24 & 0xFF) / 255.0f;
        }
        else {
            CustomStringRenderer.alpha = (color >> 24 & 0xFF) / 255.0f;
        }
    }
    
    public static float drawUkranianChar(final int ch, final boolean shadow) {
        final int l = FontUtil.CHAR_WIDTH[ch];
        final float width = l - 0.01f;
        final int i = ch % 16 * 8;
        final int j = ch / 16 * 8;
        if (shadow) {
            ++FontUtil.posX;
            ++FontUtil.posY;
            GL11.glBegin(7);
            GL11.glColor4f(0.0f, 0.0f, 0.25f, CustomStringRenderer.alpha);
            GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
            GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
            GL11.glColor4f(0.25f, 0.25f, 0.05f, CustomStringRenderer.alpha);
            GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
            GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
            GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
            GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY + 7.99f, 0.0f);
            GL11.glColor4f(0.0f, 0.0f, 0.25f, CustomStringRenderer.alpha);
            GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, j / 128.0f);
            GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY, 0.0f);
            GL11.glEnd();
            --FontUtil.posX;
            --FontUtil.posY;
        }
        GL11.glBegin(7);
        GL11.glColor4f(0.0f, 0.0f, 1.0f, CustomStringRenderer.alpha);
        GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 0.2f, CustomStringRenderer.alpha);
        GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY + 7.99f, 0.0f);
        GL11.glColor4f(0.0f, 0.0f, 1.0f, CustomStringRenderer.alpha);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY, 0.0f);
        GL11.glEnd();
        return (float)l;
    }
    
    public static void drawUkranianString(final String text, final int x, final int y, final boolean shadow) {
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        CustomStringRenderer.alpha = 1.0f;
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        GL11.glShadeModel(7425);
        for (int i = 0; i < text.length(); ++i) {
            final char ch = text.charAt(i);
            FontUtil.posX += drawUkranianChar(ch, shadow);
        }
        GL11.glShadeModel(7424);
    }
    
    public static float renderDefaultModMessageChar(final int ch) {
        final int i = ch % 16 * 8;
        final int j = ch / 16 * 8;
        final int l = FontUtil.CHAR_WIDTH[ch];
        final float f = l - 0.01f;
        ++FontUtil.posX;
        ++FontUtil.posY;
        GL11.glColor4f(0.0f, 0.25f, 0.25f, CustomStringRenderer.alpha);
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
        --FontUtil.posX;
        --FontUtil.posY;
        GL11.glColor4f(0.0f, 1.0f, 1.0f, CustomStringRenderer.alpha);
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
    
    public static float drawRainbowModMessage(final int x, final int y) {
        GlStateManager.func_179141_d();
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        for (final char c : CustomStringRenderer.modMessageChars) {
            FontUtil.posX += renderRainbowChar(c, time, true);
        }
        GL11.glShadeModel(7424);
        FontUtil.posX += 4.0f;
        final char c2 = '>';
        FontUtil.posX += renderDefaultModMessageChar(c2);
        GlStateManager.func_179117_G();
        GlStateManager.func_179118_c();
        return FontUtil.posX + 4.0f;
    }
    
    public static float renderRainbowChar(final int ch, final long time, final boolean shadow) {
        final int l = FontUtil.CHAR_WIDTH[ch];
        final float width = l - 0.01f;
        final long y = (long)(FontUtil.posY * 11.0f);
        long position = time - ((long)(FontUtil.posX * 11.0f) - y);
        int color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
        float red = 0.0f;
        float blue = 0.0f;
        float green = 0.0f;
        float red2 = 0.0f;
        float blue2 = 0.0f;
        float green2 = 0.0f;
        switch (PizzaClient.config.modMessageColor) {
            case 0: {
                red = (color >> 16 & 0xFF) / 255.0f;
                blue = (color >> 8 & 0xFF) / 255.0f;
                green = (color & 0xFF) / 255.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = (color >> 16 & 0xFF) / 255.0f;
                blue2 = (color >> 8 & 0xFF) / 255.0f;
                green2 = (color & 0xFF) / 255.0f;
                break;
            }
            case 1: {
                red = 1.0f;
                blue = (color >> 8 & 0xFF) / 255.0f;
                green = (color & 0xFF) / 255.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = 1.0f;
                blue2 = (color >> 8 & 0xFF) / 255.0f;
                green2 = (color & 0xFF) / 255.0f;
                break;
            }
            case 2: {
                red = (color >> 16 & 0xFF) / 255.0f;
                blue = 1.0f;
                green = (color & 0xFF) / 255.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = (color >> 16 & 0xFF) / 255.0f;
                blue2 = 1.0f;
                green2 = (color & 0xFF) / 255.0f;
                break;
            }
            case 3: {
                red = (color >> 16 & 0xFF) / 255.0f;
                blue = (color >> 8 & 0xFF) / 255.0f;
                green = 1.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = (color >> 16 & 0xFF) / 255.0f;
                blue2 = (color >> 8 & 0xFF) / 255.0f;
                green2 = 1.0f;
                break;
            }
            default: {
                return drawUkranianChar(ch, shadow);
            }
        }
        final int i = ch % 16 * 8;
        final int j = ch / 16 * 8;
        if (shadow) {
            ++FontUtil.posX;
            ++FontUtil.posY;
            GL11.glColor4f(red / 4.0f, green / 4.0f, blue / 4.0f, CustomStringRenderer.alpha);
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
        GL11.glColor4f(red, green, blue, CustomStringRenderer.alpha);
        GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
        GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
        GL11.glColor4f(red2, green2, blue2, CustomStringRenderer.alpha);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY + 7.99f, 0.0f);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY, 0.0f);
        GL11.glEnd();
        return (float)l;
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
        float red = 0.0f;
        float blue = 0.0f;
        float green = 0.0f;
        float red2 = 0.0f;
        float blue2 = 0.0f;
        float green2 = 0.0f;
        switch (rgb) {
            case 0: {
                red = (color >> 16 & 0xFF) / 255.0f;
                blue = (color >> 8 & 0xFF) / 255.0f;
                green = (color & 0xFF) / 255.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = (color >> 16 & 0xFF) / 255.0f;
                blue2 = (color >> 8 & 0xFF) / 255.0f;
                green2 = (color & 0xFF) / 255.0f;
                break;
            }
            case 1: {
                red = 1.0f;
                blue = (color >> 8 & 0xFF) / 255.0f;
                green = (color & 0xFF) / 255.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = 1.0f;
                blue2 = (color >> 8 & 0xFF) / 255.0f;
                green2 = (color & 0xFF) / 255.0f;
                break;
            }
            case 2: {
                red = (color >> 16 & 0xFF) / 255.0f;
                blue = 1.0f;
                green = (color & 0xFF) / 255.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = (color >> 16 & 0xFF) / 255.0f;
                blue2 = 1.0f;
                green2 = (color & 0xFF) / 255.0f;
                break;
            }
            default: {
                red = (color >> 16 & 0xFF) / 255.0f;
                blue = (color >> 8 & 0xFF) / 255.0f;
                green = 1.0f;
                position = time - ((long)((FontUtil.posX + l) * 11.0f) - y);
                color = Color.HSBtoRGB(position % 3000L / 3000.0f, PizzaClient.config.rgbBrightness, 1.0f);
                red2 = (color >> 16 & 0xFF) / 255.0f;
                blue2 = (color >> 8 & 0xFF) / 255.0f;
                green2 = 1.0f;
                break;
            }
        }
        final int i = ch % 16 * 8;
        final int j = ch / 16 * 8;
        if (shadow) {
            ++FontUtil.posX;
            ++FontUtil.posY;
            GL11.glColor4f(red / 4.0f, green / 4.0f, blue / 4.0f, CustomStringRenderer.alpha);
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
        GL11.glColor4f(red, green, blue, CustomStringRenderer.alpha);
        GL11.glTexCoord2f(i / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY, 0.0f);
        GL11.glTexCoord2f(i / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX, FontUtil.posY + 7.99f, 0.0f);
        GL11.glColor4f(red2, green2, blue2, CustomStringRenderer.alpha);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, (j + 7.99f) / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY + 7.99f, 0.0f);
        GL11.glTexCoord2f((i + width - 1.0f) / 128.0f, j / 128.0f);
        GL11.glVertex3f(FontUtil.posX + width - 1.0f, FontUtil.posY, 0.0f);
        GL11.glEnd();
        return (float)l;
    }
    
    public static void drawRainbowText(final String input, final float x, final float y) {
        drawRainbowText(input.toCharArray(), (int)x, (int)y);
    }
    
    public static void drawRainbowText(final String input, final int x, final int y) {
        drawRainbowText(input.toCharArray(), x, y);
    }
    
    public static void drawRainbowText(final char[] input, final int x, final int y) {
        GlStateManager.func_179141_d();
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        if (Character.isDigit(input[0])) {
            final int color = Character.getNumericValue(input[0]);
            for (int i = 1; i < input.length; ++i) {
                FontUtil.posX += renderRainbowChar(input[i], time, color, true);
            }
        }
        else {
            for (final char ch : input) {
                FontUtil.posX += renderRainbowChar(ch, time, 0, true);
            }
        }
        GL11.glShadeModel(7424);
        GlStateManager.func_179118_c();
        GlStateManager.func_179117_G();
    }
    
    public static void drawRainbowText(final char[] input, final int x, final int y, final boolean shadow) {
        GlStateManager.func_179141_d();
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        if (Character.isDigit(input[0])) {
            final int color = Character.getNumericValue(input[0]);
            for (int i = 1; i < input.length; ++i) {
                FontUtil.posX += renderRainbowChar(input[i], time, color, shadow);
            }
        }
        else {
            for (final char ch : input) {
                FontUtil.posX += renderRainbowChar(ch, time, 0, shadow);
            }
        }
        GL11.glShadeModel(7424);
        GlStateManager.func_179117_G();
    }
    
    public static float drawRainbowName(final char[] input, final int x, final int y) {
        GlStateManager.func_179141_d();
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        final long time = System.currentTimeMillis();
        final int color = Character.getNumericValue(input[0]);
        GL11.glShadeModel(7425);
        for (int i = 1; i < input.length; ++i) {
            FontUtil.posX += renderRainbowChar(input[i], time, color, true);
        }
        GL11.glShadeModel(7424);
        GlStateManager.func_179117_G();
        return FontUtil.posX;
    }
    
    public static float drawRainbowName(final char[] input, final int x, final int y, final boolean shadow) {
        GlStateManager.func_179141_d();
        PizzaClient.mc.field_71446_o.func_110577_a(FontUtil.fontRenderer.getLocationFontTexture());
        FontUtil.posX = (float)x;
        FontUtil.posY = (float)y;
        final long time = System.currentTimeMillis();
        final int color = Character.getNumericValue(input[0]);
        GL11.glShadeModel(7425);
        for (int i = 1; i < input.length; ++i) {
            FontUtil.posX += renderRainbowChar(input[i], time, color, shadow);
        }
        GL11.glShadeModel(7424);
        GlStateManager.func_179117_G();
        return FontUtil.posX;
    }
    
    static {
        modMessageChars = "PizzaClient".toCharArray();
    }
}
