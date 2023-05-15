// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.distance;

import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class BlockPosDistanceHelper
{
    private final Vec3 pos;
    private double bestDist;
    public BlockPos closest;
    
    public BlockPosDistanceHelper() {
        this.bestDist = 9.99999999E8;
        this.pos = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight(), PizzaClient.mc.field_71439_g.field_70161_v);
    }
    
    public BlockPosDistanceHelper(final Vec3 pos) {
        this.bestDist = 9.99999999E8;
        this.pos = pos;
    }
    
    public BlockPosDistanceHelper(final BlockPos lastBlock) {
        this.bestDist = 9.99999999E8;
        this.pos = new Vec3(lastBlock.func_177958_n() + 0.5, lastBlock.func_177956_o() + 0.5, lastBlock.func_177952_p() + 0.5);
    }
    
    public void compare(final BlockPos block) {
        final Vec3 vecIn = new Vec3(block.func_177958_n() + 0.5, block.func_177956_o() + 0.5, block.func_177952_p() + 0.5);
        final double dist = this.pos.func_72436_e(vecIn);
        if (dist < this.bestDist) {
            this.bestDist = dist;
            this.closest = block;
        }
    }
    
    public boolean isNotNull() {
        return this.closest != null;
    }
}
