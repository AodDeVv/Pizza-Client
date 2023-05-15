// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.shaders;

import qolskyblockmod.pizzaclient.util.render.RenderType;
import java.awt.Color;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.shader.util.ShaderUtil;
import qolskyblockmod.pizzaclient.util.shader.Shader;

public class TintShader extends Shader
{
    public static final TintShader instance;
    public final int color_location;
    
    public TintShader() {
        super("defaultVertex", "tintFragment");
        this.color_location = ShaderUtil.glGetUniformLocation(this.program, "color");
    }
    
    public void applyRainbowTint(final float alpha) {
        this.useShader();
        final int color = Color.HSBtoRGB(System.currentTimeMillis() % 3500L / 3500.0f, PizzaClient.config.rgbBrightness, 1.0f);
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, alpha);
    }
    
    public void applyTint(final Color c) {
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, (color >> 24 & 0xFF) / 255.0f);
    }
    
    public void applyTintDefaultAlpha(final Color c) {
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 0.75f);
    }
    
    public void applyTintSlightAlpha(final Color c) {
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 0.5f);
    }
    
    public void applyTint(final Color c, final float alpha) {
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, alpha);
    }
    
    public void applyTint(final int color) {
        this.useShader();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, (color >> 24 & 0xFF) / 255.0f);
    }
    
    public void applyTint(final float r, final float g, final float b) {
        this.useShader();
        ShaderUtil.glUniform4f(this.color_location, r, g, b, 1.0f);
    }
    
    public void applyTint(final float r, final float g, final float b, final float a) {
        this.useShader();
        ShaderUtil.glUniform4f(this.color_location, r, g, b, a);
    }
    
    public void applyRainbowTintChams(final float alpha) {
        RenderType.renderTintChams();
        this.useShader();
        final int color = Color.HSBtoRGB(System.currentTimeMillis() % 3500L / 3500.0f, PizzaClient.config.rgbBrightness, 1.0f);
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, alpha);
    }
    
    public void applyTintChams(final Color c) {
        RenderType.renderTintChams();
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, (color >> 24 & 0xFF) / 255.0f);
    }
    
    public void applyTintChamsDefaultAlpha(final Color c) {
        RenderType.renderTintChams();
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 0.75f);
    }
    
    public void applyTintChamsSlightAlpha(final Color c) {
        RenderType.renderTintChams();
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 0.5f);
    }
    
    public void applyTintChams(final Color c, final float alpha) {
        RenderType.renderTintChams();
        this.useShader();
        final int color = c.getRGB();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, alpha);
    }
    
    public void applyTintChams(final int color) {
        RenderType.renderTintChams();
        this.useShader();
        ShaderUtil.glUniform4f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, (color >> 24 & 0xFF) / 255.0f);
    }
    
    public void applyTintChams(final float r, final float g, final float b) {
        RenderType.renderTintChams();
        this.useShader();
        ShaderUtil.glUniform4f(this.color_location, r, g, b, 1.0f);
    }
    
    public void applyTintChams(final float r, final float g, final float b, final float a) {
        RenderType.renderTintChams();
        this.useShader();
        ShaderUtil.glUniform4f(this.color_location, r, g, b, a);
    }
    
    static {
        instance = new TintShader();
    }
}
