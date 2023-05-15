// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.blocks;

import org.spongepowered.asm.mixin.Final;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.util.Vec3i;

@Mixin({ BlockPos.class })
public class MixinBlockPos extends Vec3i
{
    public MixinBlockPos(final int xIn, final int yIn, final int zIn) {
        super(xIn, yIn, zIn);
    }
    
    @Overwrite
    public BlockPos func_177984_a() {
        return new BlockPos(this.func_177958_n(), this.func_177956_o() + 1, this.func_177952_p());
    }
    
    @Overwrite
    public BlockPos func_177981_b(final int offset) {
        return (BlockPos)((offset == 0) ? this : new BlockPos(this.func_177958_n(), this.func_177956_o() + offset, this.func_177952_p()));
    }
    
    @Overwrite
    public BlockPos func_177977_b() {
        return new BlockPos(this.func_177958_n(), this.func_177956_o() - 1, this.func_177952_p());
    }
    
    @Overwrite
    public BlockPos func_177979_c(final int offset) {
        return (BlockPos)((offset == 0) ? this : new BlockPos(this.func_177958_n(), this.func_177956_o() - offset, this.func_177952_p()));
    }
    
    @Overwrite
    public BlockPos func_177978_c() {
        return new BlockPos(this.func_177958_n(), this.func_177956_o(), this.func_177952_p() - 1);
    }
    
    @Overwrite
    public BlockPos func_177964_d(final int offset) {
        return (BlockPos)((offset == 0) ? this : new BlockPos(this.func_177958_n(), this.func_177956_o(), this.func_177952_p() - offset));
    }
    
    @Overwrite
    public BlockPos func_177968_d() {
        return new BlockPos(this.func_177958_n(), this.func_177956_o(), this.func_177952_p() + 1);
    }
    
    @Overwrite
    public BlockPos func_177970_e(final int offset) {
        return (BlockPos)((offset == 0) ? this : new BlockPos(this.func_177958_n(), this.func_177956_o(), this.func_177952_p() + offset));
    }
    
    @Overwrite
    public BlockPos func_177976_e() {
        return new BlockPos(this.func_177958_n() - 1, this.func_177956_o(), this.func_177952_p());
    }
    
    @Overwrite
    public BlockPos func_177985_f(final int offset) {
        return (BlockPos)((offset == 0) ? this : new BlockPos(this.func_177958_n() - offset, this.func_177956_o(), this.func_177952_p()));
    }
    
    @Overwrite
    public BlockPos func_177974_f() {
        return new BlockPos(this.func_177958_n() + 1, this.func_177956_o(), this.func_177952_p());
    }
    
    @Overwrite
    public BlockPos func_177965_g(final int offset) {
        return (BlockPos)((offset == 0) ? this : new BlockPos(this.func_177958_n() + offset, this.func_177956_o(), this.func_177952_p()));
    }
    
    @Final
    @Overwrite
    public BlockPos func_177972_a(final EnumFacing direction) {
        final Vec3i dir = direction.func_176730_m();
        return new BlockPos(this.func_177958_n() + dir.func_177958_n(), this.func_177956_o() + dir.func_177956_o(), this.func_177952_p() + dir.func_177952_p());
    }
    
    @Final
    @Overwrite
    public BlockPos func_177967_a(final EnumFacing facing, final int n) {
        if (n == 0) {
            return (BlockPos)this;
        }
        final Vec3i dir = facing.func_176730_m();
        return new BlockPos(this.func_177958_n() + dir.func_177958_n() * n, this.func_177956_o() + dir.func_177956_o() * n, this.func_177952_p() + dir.func_177952_p() * n);
    }
}
