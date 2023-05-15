// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.shaders;

import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.shader.util.ShaderUtil;
import qolskyblockmod.pizzaclient.util.shader.Shader;

public class ColoredOutlineShader extends Shader
{
    public static final ColoredOutlineShader instance;
    public final int offset_location;
    public final int color_location;
    
    public ColoredOutlineShader() {
        super("defaultVertex", "coloredOutlineFragment");
        this.offset_location = ShaderUtil.glGetUniformLocation(this.program, "offset");
        this.color_location = ShaderUtil.glGetUniformLocation(this.program, "color");
    }
    
    public void renderFirst(final int color) {
        this.useShader();
        ShaderUtil.glUniform3f(this.color_location, (color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f);
        ShaderUtil.glUniform2f(this.offset_location, 1.0f / PizzaClient.mc.field_71443_c, 0.0f);
    }
    
    public void renderSecond() {
        ShaderUtil.glUniform2f(this.offset_location, 0.0f, 1.0f / PizzaClient.mc.field_71440_d);
    }
    
    static {
        instance = new ColoredOutlineShader();
    }
}
