// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.blocks;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockCarpet;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.Block;

@Mixin({ BlockCarpet.class })
public abstract class MixinCarpet extends Block
{
    public MixinCarpet(final Material blockMaterialIn, final MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }
    
    @Inject(method = { "setBlockBoundsFromMeta" }, at = { @At("HEAD") }, cancellable = true)
    private void onSetBoundingBox(final CallbackInfo ci) {
        if (PizzaClient.config != null && PizzaClient.config.removeCarpets) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            ci.cancel();
        }
    }
}
