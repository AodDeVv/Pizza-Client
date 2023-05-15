// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.blocks;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.EnumFacing;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockSkull;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.Block;

@Mixin({ BlockSkull.class })
public abstract class MixinSkull extends Block
{
    public MixinSkull(final Material blockMaterialIn, final MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }
    
    @Inject(method = { "getCollisionBoundingBox" }, at = { @At("HEAD") }, cancellable = true)
    private void fixBoxWhenColliding(final World worldIn, final BlockPos pos, final IBlockState state, final CallbackInfoReturnable<AxisAlignedBB> cir) {
        if (PizzaClient.config.biggerSecretBlocks) {
            switch ((EnumFacing)worldIn.func_180495_p(pos).func_177229_b((IProperty)BlockSkull.field_176418_a)) {
                default: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + 0.25f), (double)pos.func_177956_o(), (double)(pos.func_177952_p() + 0.25f), (double)(pos.func_177958_n() + 0.75f), (double)(pos.func_177956_o() + 0.5f), (double)(pos.func_177952_p() + 0.75f)));
                }
                case NORTH: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + 0.25f), (double)(pos.func_177956_o() + 0.25f), (double)(pos.func_177952_p() + 0.5f), (double)(pos.func_177958_n() + 0.75f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + 1.0f)));
                }
                case SOUTH: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + 0.25f), (double)(pos.func_177956_o() + 0.25f), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 0.75f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + 0.5f)));
                }
                case WEST: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)(pos.func_177958_n() + 0.5f), (double)(pos.func_177956_o() + 0.25f), (double)(pos.func_177952_p() + 0.25f), (double)(pos.func_177958_n() + 1.0f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + 0.75f)));
                }
                case EAST: {
                    cir.setReturnValue((Object)new AxisAlignedBB((double)pos.func_177958_n(), (double)(pos.func_177956_o() + 0.25f), (double)(pos.func_177952_p() + 0.25f), (double)(pos.func_177958_n() + 0.5f), (double)(pos.func_177956_o() + 0.75f), (double)(pos.func_177952_p() + 0.75f)));
                    break;
                }
            }
        }
    }
    
    @Inject(method = { "setBlockBoundsBasedOnState" }, at = { @At("HEAD") }, cancellable = true)
    private void increaseBlockBox(final IBlockAccess worldIn, final BlockPos pos, final CallbackInfo ci) {
        if (PizzaClient.config.biggerSecretBlocks) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            ci.cancel();
        }
    }
}
