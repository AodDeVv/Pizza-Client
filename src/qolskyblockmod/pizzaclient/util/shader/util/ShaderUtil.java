// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.util;

import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.BufferUtils;
import qolskyblockmod.pizzaclient.util.Utils;
import java.io.BufferedInputStream;
import net.minecraft.util.ResourceLocation;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUtil
{
    public static final boolean ARB_SHADERS;
    public static final int framebufferType;
    
    public static void glUniform1f(final int location, final float v0) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform1fARB(location, v0);
        }
        else {
            GL20.glUniform1f(location, v0);
        }
    }
    
    public static void glUniform1i(final int location, final int v0) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform1iARB(location, v0);
        }
        else {
            GL20.glUniform1i(location, v0);
        }
    }
    
    public static void glUniform1f(final int location, final double v0) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform1fARB(location, (float)v0);
        }
        else {
            GL20.glUniform1f(location, (float)v0);
        }
    }
    
    public static void glUniform1(final int location, final FloatBuffer buffer) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform1ARB(location, buffer);
        }
        else {
            GL20.glUniform1(location, buffer);
        }
    }
    
    public static void glUniform3f(final int location, final float v0, final float v1, final float v2) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform3fARB(location, v0, v1, v2);
        }
        else {
            GL20.glUniform3f(location, v0, v1, v2);
        }
    }
    
    public static void glUniform4f(final int location, final float f, final float f1, final float f2, final float f3) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform4fARB(location, f, f1, f2, f3);
        }
        else {
            GL20.glUniform4f(location, f, f1, f2, f3);
        }
    }
    
    public static void glUniform2f(final int location, final float v0, final float v1) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform2fARB(location, v0, v1);
        }
        else {
            GL20.glUniform2f(location, v0, v1);
        }
    }
    
    public static void glUniformBool(final int location, final int value) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glUniform1iARB(location, value);
        }
        else {
            GL20.glUniform1i(location, value);
        }
    }
    
    public static void glValidateProgram(final int program) {
        if (ShaderUtil.ARB_SHADERS) {
            ARBShaderObjects.glValidateProgramARB(program);
        }
        else {
            GL20.glValidateProgram(program);
        }
    }
    
    public static int glGetUniformLocation(final int program, final ByteBuffer buffer) {
        if (ShaderUtil.ARB_SHADERS) {
            return ARBShaderObjects.glGetUniformLocationARB(program, buffer);
        }
        return GL20.glGetUniformLocation(program, buffer);
    }
    
    public static int glGetUniformLocation(final int programObj, final CharSequence name) {
        return ShaderUtil.ARB_SHADERS ? ARBShaderObjects.glGetUniformLocationARB(programObj, name) : GL20.glGetUniformLocation(programObj, name);
    }
    
    public static int getUniformBool(final boolean bool) {
        return bool ? 1 : 0;
    }
    
    public static int loadVertex(final String vertex) {
        int shaderID;
        try {
            final byte[] bytes = Utils.toByteArray(new BufferedInputStream(PizzaClient.mc.func_110442_L().func_110536_a(new ResourceLocation("pizzaclient", "shaders/" + vertex + ".vsh")).func_110527_b()));
            final ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
            buffer.put(bytes);
            buffer.position(0);
            shaderID = OpenGlHelper.func_153195_b(OpenGlHelper.field_153209_q);
            OpenGlHelper.func_153169_a(shaderID, buffer);
            OpenGlHelper.func_153170_c(shaderID);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (OpenGlHelper.func_153157_c(shaderID, OpenGlHelper.field_153208_p) == 0) {
            throw new RuntimeException("Failed to load shader vertex " + vertex);
        }
        return shaderID;
    }
    
    public static int loadFragment(final String fragment) {
        int shaderID;
        try {
            final ResourceLocation resourceLocation = new ResourceLocation("pizzaclient", "shaders/" + fragment + ".fsh");
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(PizzaClient.mc.func_110442_L().func_110536_a(resourceLocation).func_110527_b());
            final byte[] bytes = Utils.toByteArray(bufferedInputStream);
            final ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
            buffer.put(bytes);
            buffer.position(0);
            shaderID = OpenGlHelper.func_153195_b(OpenGlHelper.field_153210_r);
            OpenGlHelper.func_153169_a(shaderID, buffer);
            OpenGlHelper.func_153170_c(shaderID);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (OpenGlHelper.func_153157_c(shaderID, OpenGlHelper.field_153208_p) == 0) {
            throw new RuntimeException("Failed to load shader fragment " + fragment);
        }
        return shaderID;
    }
    
    static {
        final ContextCapabilities contextcapabilities = GLContext.getCapabilities();
        ARB_SHADERS = !contextcapabilities.OpenGL21;
        if (contextcapabilities.OpenGL30) {
            framebufferType = 0;
        }
        else if (contextcapabilities.GL_ARB_framebuffer_object) {
            framebufferType = 1;
        }
        else if (contextcapabilities.GL_EXT_framebuffer_object) {
            framebufferType = 2;
        }
        else {
            framebufferType = -1;
        }
    }
}
