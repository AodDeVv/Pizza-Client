// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.shaders;

import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.shader.util.ShaderUtil;
import qolskyblockmod.pizzaclient.util.shader.Shader;

public class OutlineShader extends Shader
{
    public static final OutlineShader instance;
    public final int offset_location;
    
    public OutlineShader() {
        super("defaultVertex", "outlineFragment");
        this.offset_location = ShaderUtil.glGetUniformLocation(this.program, "offset");
    }
    
    public void renderFirst() {
        this.useShader();
        ShaderUtil.glUniform2f(this.offset_location, 0.0f, 1.0f / PizzaClient.mc.field_71440_d);
    }
    
    public void renderSecond() {
        ShaderUtil.glUniform2f(this.offset_location, 1.0f / PizzaClient.mc.field_71443_c, 0.0f);
    }
    
    static {
        instance = new OutlineShader();
    }
}
