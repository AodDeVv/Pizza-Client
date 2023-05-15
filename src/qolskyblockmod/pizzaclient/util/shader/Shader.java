// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader;

import net.minecraftforge.fml.common.eventhandler.Event;
import qolskyblockmod.pizzaclient.core.events.ResizeWindowEvent;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.ARBShaderObjects;
import qolskyblockmod.pizzaclient.util.shader.util.ShaderUtil;
import net.minecraft.client.renderer.OpenGlHelper;
import qolskyblockmod.pizzaclient.util.render.FastFramebuffer;

public abstract class Shader
{
    public final int program;
    public static final FastFramebuffer framebuffer;
    
    public Shader(final String vertex, final String fragment) {
        this.program = OpenGlHelper.func_153183_d();
        if (vertex != null) {
            OpenGlHelper.func_153178_b(this.program, ShaderUtil.loadVertex(vertex));
        }
        OpenGlHelper.func_153178_b(this.program, ShaderUtil.loadFragment(fragment));
        OpenGlHelper.func_153179_f(this.program);
        ShaderUtil.glValidateProgram(this.program);
        this.bindAttributes();
    }
    
    public Shader(final String location) {
        this(location, location);
    }
    
    public static void endCurrentShader() {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUseProgramObjectARB(0);
        }
        else {
            GL20.glUseProgram(0);
        }
    }
    
    public final void bindAttribute(final int attribute, final String name) {
        GL20.glBindAttribLocation(this.program, attribute, (CharSequence)name);
    }
    
    public void bindAttributes() {
    }
    
    public int getUniformLocation(final String name) {
        return ShaderUtil.ARB_SHADERS ? ARBShaderObjects.glGetUniformLocationARB(this.program, (CharSequence)name) : GL20.glGetUniformLocation(this.program, (CharSequence)name);
    }
    
    public void useShader() {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUseProgramObjectARB(this.program);
        }
        else {
            GL20.glUseProgram(this.program);
        }
    }
    
    public void endShader() {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUseProgramObjectARB(0);
        }
        else {
            GL20.glUseProgram(0);
        }
    }
    
    public static void updateFramebuffer() {
        if (Shader.framebuffer.framebufferWidth != PizzaClient.mc.field_71443_c || Shader.framebuffer.framebufferHeight != PizzaClient.mc.field_71440_d) {
            Shader.framebuffer.updateFramebuffer();
            MinecraftForge.EVENT_BUS.post((Event)new ResizeWindowEvent());
        }
    }
    
    static {
        framebuffer = new FastFramebuffer(PizzaClient.mc.field_71440_d, PizzaClient.mc.field_71443_c);
    }
}
