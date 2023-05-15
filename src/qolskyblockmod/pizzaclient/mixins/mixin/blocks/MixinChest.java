// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.blocks;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockChest;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.BlockContainer;

@Mixin({ BlockChest.class })
public abstract class MixinChest extends BlockContainer
{
    public MixinChest(final Material blockMaterialIn, final MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }
    
    @Inject(method = { "setBlockBoundsBasedOnState" }, at = { @At("HEAD") }, cancellable = true)
    private void changeChestBox(final IBlockAccess worldIn, final BlockPos pos, final CallbackInfo ci) {
        if (PizzaClient.config.biggerSecretBlocks) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            ci.cancel();
        }
    }
}
