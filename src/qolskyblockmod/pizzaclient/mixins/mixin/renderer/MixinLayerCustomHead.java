// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.renderer;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ LayerCustomHead.class })
public class MixinLayerCustomHead
{
    @Inject(method = { "doRenderLayer" }, at = { @At("HEAD") }, cancellable = true)
    private void onRender(final EntityLivingBase entity, final float f2, final float s, final float nbttagcompound, final float f3, final float gameprofile, final float item, final float minecraft, final CallbackInfo ci) {
        if (PizzaClient.config.auschwitzSimulator && entity instanceof EntityPlayer) {
            ci.cancel();
        }
    }
}
