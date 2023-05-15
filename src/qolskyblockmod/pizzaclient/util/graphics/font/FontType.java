// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.font;

import qolskyblockmod.pizzaclient.PizzaClient;
import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import net.minecraft.client.renderer.texture.DynamicTexture;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import qolskyblockmod.pizzaclient.util.graphics.font.renderer.CustomFontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class FontType
{
    public final CharData[] charData;
    public final int fontTexture;
    public int fontHeight;
    public float space;
    
    public FontType(final ResourceLocation location, final int size) {
        this.space = 0.01f;
        this.charData = new CharData[256];
        this.fontTexture = this.generateTexture(getFont(location, size), 512, true, true);
    }
    
    public FontType(final ResourceLocation location, final int size, final boolean antiAlias, final boolean fractionalMetrics) {
        this.space = 0.01f;
        this.charData = new CharData[256];
        this.fontTexture = this.generateTexture(getFont(location, size), 512, antiAlias, fractionalMetrics);
    }
    
    public void bindTexture() {
        GlStateManager.func_179144_i(this.fontTexture);
    }
    
    public float renderChar(final char ch) {
        return this.charData[ch].renderChar(CustomFontRenderer.posX, CustomFontRenderer.posY) + this.space;
    }
    
    public float getSpaceWidth() {
        return (float)this.charData[32].width;
    }
    
    private int generateTexture(final Font font, final int imgSize, final boolean antiAlias, final boolean fractionalMetrics) {
        final BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, 2);
        final Graphics2D graphics = (Graphics2D)bufferedImage.getGraphics();
        graphics.setFont(font);
        graphics.setColor(new Color(255, 255, 255, 0));
        graphics.fillRect(0, 0, imgSize, imgSize);
        graphics.setColor(Color.WHITE);
        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int charHeight = 0;
        int positionX = 0;
        int positionY = 1;
        for (int index = 0; index < this.charData.length; ++index) {
            final char c = (char)index;
            final CharData charData = new CharData();
            final Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(c), graphics);
            charData.width = dimensions.getBounds().width + 8;
            charData.height = dimensions.getBounds().height;
            if (positionX + charData.width >= imgSize) {
                positionX = 0;
                positionY += charHeight;
                charHeight = 0;
            }
            if (charData.height > charHeight) {
                charHeight = charData.height;
            }
            charData.storedX = positionX;
            charData.storedY = positionY;
            if (charData.height > this.fontHeight) {
                this.fontHeight = charData.height;
            }
            this.charData[index] = charData;
            graphics.drawString(String.valueOf(c), positionX + 2, positionY + fontMetrics.getAscent());
            positionX += charData.width;
        }
        final DynamicTexture texture = new DynamicTexture(bufferedImage);
        return texture.func_110552_b();
    }
    
    public static Font getFont(final ResourceLocation location, final int size) {
        try {
            return Font.createFont(0, PizzaClient.mc.func_110442_L().func_110536_a(location).func_110527_b()).deriveFont(0, (float)size);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
