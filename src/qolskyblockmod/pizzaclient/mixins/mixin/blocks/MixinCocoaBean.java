// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.blocks;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.properties.IProperty;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockCocoa;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.Block;

@Mixin({ BlockCocoa.class })
public abstract class MixinCocoaBean extends Block
{
    public MixinCocoaBean(final Material blockMaterialIn, final MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }
    
    @Inject(method = { "getCollisionBoundingBox" }, at = { @At("HEAD") }, cancellable = true)
    private void fixBoxWhenColliding(final World worldIn, final BlockPos pos, final IBlockState state, final CallbackInfoReturnable<AxisAlignedBB> cir) {
        if (PizzaClient.config.cocoaBeanSize) {
            final IBlockState iblockstate = worldIn.func_180495_p(pos);
            final int i = (int)iblockstate.func_177229_b((IProperty)BlockCocoa.field_176501_a);
            final int j = 4 + i * 2;
            final int k = 5 + i * 2;
            final float f = j / 2.0f;
            switch ((EnumFacing)iblockstate.func_177229_b((IProperty)BlockCocoa.field_176387_N)) {
                case SOUTH: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + (8.0f - f) / 16.0f), (double)(pos.func_177956_o() + (12.0f - k) / 16.0f), (double)(pos.func_177952_p() + (15.0f - j) / 16.0f), (double)(pos.func_177958_n() + (8.0f + f) / 16.0f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + 0.9375f)));
                }
                case NORTH: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + (8.0f - f) / 16.0f), (double)(pos.func_177956_o() + (12.0f - k) / 16.0f), (double)(pos.func_177952_p() + 0.0625f), (double)(pos.func_177958_n() + (8.0f + f) / 16.0f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + (1.0f + j) / 16.0f)));
                }
                case WEST: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + 0.0625f), (double)(pos.func_177956_o() + (12.0f - k) / 16.0f), (double)(pos.func_177952_p() + (8.0f - f) / 16.0f), (double)(pos.func_177958_n() + (1.0f + j) / 16.0f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + (8.0f + f) / 16.0f)));
                }
                case EAST: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + (15.0f - j) / 16.0f), (double)(pos.func_177956_o() + (12.0f - k) / 16.0f), (double)(pos.func_177952_p() + (8.0f - f) / 16.0f), (double)(pos.func_177958_n() + 0.9375f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + (8.0f + f) / 16.0f)));
                    break;
                }
            }
        }
    }
    
    @Inject(method = { "setBlockBoundsBasedOnState" }, at = { @At("HEAD") }, cancellable = true)
    private void increaseBlockBox(final IBlockAccess worldIn, final BlockPos pos, final CallbackInfo ci) {
        if (PizzaClient.config.cocoaBeanSize) {
            if ((int)worldIn.func_180495_p(pos).func_177229_b((IProperty)BlockCocoa.field_176501_a) < 2) {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            }
            else {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            }
            ci.cancel();
        }
    }
}
