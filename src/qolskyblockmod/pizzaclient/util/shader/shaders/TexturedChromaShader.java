// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.shader.shaders;

import qolskyblockmod.pizzaclient.util.shader.ChromaShader;

public class TexturedChromaShader extends ChromaShader
{
    public static final TexturedChromaShader instance;
    
    public TexturedChromaShader() {
        super("chroma/texturedVertex", "chroma/texturedChromaFragment");
    }
    
    static {
        instance = new TexturedChromaShader();
    }
}
