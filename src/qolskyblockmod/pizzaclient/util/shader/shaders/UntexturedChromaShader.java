// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.shaders;

import qolskyblockmod.pizzaclient.util.shader.ChromaShader;

public class UntexturedChromaShader extends ChromaShader
{
    public static final UntexturedChromaShader instance;
    
    public UntexturedChromaShader() {
        super("chroma/chromaVertex", "chroma/chromaFragment");
    }
    
    static {
        instance = new UntexturedChromaShader();
    }
}
