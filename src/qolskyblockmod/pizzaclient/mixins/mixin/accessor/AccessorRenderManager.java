// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.accessor;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderManager.class })
public interface AccessorRenderManager
{
    @Accessor("renderOutlines")
    boolean getRenderOutlines();
}
