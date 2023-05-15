// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.shaders;

import qolskyblockmod.pizzaclient.util.shader.util.ShaderUtil;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.shader.Shader;

public class BlurShader extends Shader
{
    public static final BlurShader instance;
    public final int offset_Location;
    
    public BlurShader() {
        super("defaultVertex", "blurFragment");
        this.offset_Location = this.getUniformLocation("offset");
    }
    
    public static void renderBlurryBackground() {
        BlurShader.framebuffer.clearAndBindFramebuffer();
        GlStateManager.func_179144_i(PizzaClient.mc.func_147110_a().field_147617_g);
        BlurShader.instance.renderFirst();
        RenderUtil.drawQuad();
        GlStateManager.func_179144_i(BlurShader.framebuffer.framebufferTexture);
        PizzaClient.mc.func_147110_a().func_147610_a(true);
        BlurShader.instance.renderSecond();
        RenderUtil.drawQuad();
        endCurrentShader();
    }
    
    public void renderFirst() {
        this.useShader();
        ShaderUtil.glUniform2f(this.offset_Location, 0.0f, 1.0f / PizzaClient.mc.field_71440_d);
    }
    
    public void renderSecond() {
        ShaderUtil.glUniform2f(this.offset_Location, 1.0f / PizzaClient.mc.field_71443_c, 0.0f);
    }
    
    static {
        instance = new BlurShader();
    }
}
