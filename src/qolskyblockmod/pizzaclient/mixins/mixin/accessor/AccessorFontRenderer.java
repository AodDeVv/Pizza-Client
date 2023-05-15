// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.accessor;

import org.spongepowered.asm.mixin.gen.Invoker;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ FontRenderer.class })
public interface AccessorFontRenderer
{
    @Accessor("glyphWidth")
    byte[] getGlyphWidths();
    
    @Accessor("locationFontTexture")
    ResourceLocation getLocationFontTexture();
    
    @Invoker("getUnicodePageLocation")
    ResourceLocation getUnicodePageLocation(final int p0);
    
    @Accessor("colorCode")
    int[] getColorCodes();
    
    @Invoker("resetStyles")
    void invokeResetStyles();
    
    @Invoker("renderString")
    int invokeRenderString(final String p0, final float p1, final float p2, final int p3, final boolean p4);
}
