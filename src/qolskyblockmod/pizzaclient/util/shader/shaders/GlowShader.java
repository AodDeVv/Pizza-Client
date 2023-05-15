// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.shaders;

import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.shader.util.ShaderUtil;
import qolskyblockmod.pizzaclient.util.shader.Shader;

public class GlowShader extends Shader
{
    public static final GlowShader instance;
    public final int color_location;
    public final int offset_location;
    public final int yOffset_location;
    
    public GlowShader() {
        super("defaultVertex", "glowFragment");
        this.color_location = ShaderUtil.glGetUniformLocation(this.program, "color");
        this.offset_location = ShaderUtil.glGetUniformLocation(this.program, "offset");
        this.yOffset_location = ShaderUtil.glGetUniformLocation(this.program, "yOffset");
    }
    
    public void renderFirst(final int color) {
        this.useShader();
        ShaderUtil.glUniform3f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f);
        ShaderUtil.glUniform2f(this.offset_location, 1.0f / PizzaClient.mc.field_71443_c, 0.0f);
        ShaderUtil.glUniform1i(this.yOffset_location, 0);
    }
    
    public void renderSecond() {
        ShaderUtil.glUniform2f(this.offset_location, 0.0f, 1.0f / PizzaClient.mc.field_71440_d);
        ShaderUtil.glUniform1i(this.yOffset_location, 1);
    }
    
    static {
        instance = new GlowShader();
    }
}
