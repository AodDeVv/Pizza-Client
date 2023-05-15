// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader;

import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import qolskyblockmod.pizzaclient.util.shader.uniform.FloatUniform;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.util.ArrayList;
import qolskyblockmod.pizzaclient.util.shader.uniform.IUniform;
import java.util.List;

public abstract class ChromaShader extends Shader
{
    public final List<IUniform> uniforms;
    private static float chromaSize;
    
    public ChromaShader(final String vertex, final String fragment) {
        super(vertex, fragment);
        this.uniforms = new ArrayList<IUniform>();
        this.registerUniforms();
    }
    
    public ChromaShader(final String location) {
        super(location, location);
        this.uniforms = new ArrayList<IUniform>();
        this.registerUniforms();
    }
    
    private void registerUniforms() {
        this.uniforms.add(new FloatUniform(this.program, "chromaSize", () -> ChromaShader.chromaSize * (PizzaClient.mc.field_71443_c / 100.0f)));
        this.uniforms.add(new FloatUniform(this.program, "timeOffset", () -> (TickTimer.ticks + Utils.getPartialTicks()) / 65.0f));
        this.uniforms.add(new FloatUniform(this.program, "saturation", () -> PizzaClient.config.rgbBrightness));
    }
    
    public void applyShader() {
        this.useShader();
        for (final IUniform uniform : this.uniforms) {
            uniform.update();
        }
    }
    
    public static void endShaderResetSize() {
        endCurrentShader();
        ChromaShader.chromaSize = 35.0f;
    }
    
    public static void setSize(final float size) {
        ChromaShader.chromaSize = size;
    }
    
    public static void scaleSize(final float scale) {
        ChromaShader.chromaSize = 35.0f * scale;
    }
    
    public static void resetSize() {
        ChromaShader.chromaSize = 35.0f;
    }
    
    static {
        ChromaShader.chromaSize = 35.0f;
    }
}
