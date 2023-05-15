// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base;

import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves.MiningMoves;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves.IMovement;
import net.minecraft.block.Block;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.MovementType;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
import qolskyblockmod.pizzaclient.util.Utils;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.finder.BlockFinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;

public abstract class AdvancedMiningBot extends MiningBot
{
    public static double getEstimatedCost(final BlockPos goal, final int blockCount) {
        double cost = MathUtil.abs(goal.func_177958_n() - PizzaClient.mc.field_71439_g.field_70165_t) + MathUtil.abs(goal.func_177956_o() - PizzaClient.mc.field_71439_g.field_70163_u);
        final double yDiff = goal.func_177956_o() - PizzaClient.mc.field_71439_g.field_70163_u;
        if (yDiff > 0.0) {
            cost += yDiff * 1.5;
        }
        else {
            cost += -yDiff;
        }
        return cost / blockCount;
    }
    
    public AdvancedMiningBot(final Vec3 from, final Vec3 to) {
        super(from, to);
    }
    
    public AdvancedMiningBot(final BetterBlockPos from, final BetterBlockPos to) {
        super(from, to);
    }
    
    public AdvancedMiningBot(final BetterBlockPos to) {
        super(to);
    }
    
    public AdvancedMiningBot(final Vec3 to) {
        super(to);
    }
    
    public abstract BlockFinder getFinder();
    
    @Override
    public void move() {
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), false);
        final BetterBlockPos player = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        BlockPos posToMine = null;
        final boolean removeNodes = this.removeNode();
    Label_0074:
        for (final BlockPos pos : new ArrayList<BlockPos>(this.moves)) {
            if (!this.isPathFree(pos)) {
                posToMine = pos;
                break;
            }
            if (!removeNodes || !player.isSameXandZ(pos)) {
                continue;
            }
            final BetterBlockPos copy = player.copyOf();
            copy.field_177960_b = pos.func_177956_o();
            while (copy.field_177960_b <= player.field_177960_b + 1) {
                if (!Utils.isUncollidable(copy)) {
                    continue Label_0074;
                }
                final BetterBlockPos betterBlockPos = copy;
                ++betterBlockPos.field_177960_b;
            }
            if (this.moves.size() < 2 || this.fixFallingState != 0) {
                while (!player.isSameXandZ(this.moves.get(0))) {
                    this.moves.remove(0);
                }
                this.moves.remove(0);
                break;
            }
            final MovementType type = Movement.calculateRequiredMovement(this.moves.get(0), this.moves.get(1));
            while (!player.isSameXandZ(this.moves.get(0))) {
                this.moves.remove(0);
            }
            this.moves.remove(0);
            if (this.moves.size() >= 2 && type != Movement.calculateRequiredMovement(this.moves.get(0), this.moves.get(1))) {
                this.onCorner();
                break;
            }
        }
        if (this.moves.isEmpty()) {
            return;
        }
        if (posToMine != null) {
            this.minePath();
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), true);
        }
        switch (this.fixFallingState) {
            case 1: {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
                PlayerUtil.moveOpposite();
                this.fixFallingState = 2;
            }
            case 2: {
                Movement.disableMovement();
                if (Movement.isMovingForward()) {
                    this.fixFallingState = 3;
                }
                else {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), true);
                }
            }
            case 3: {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
                if (PizzaClient.mc.field_71439_g.field_70122_E) {
                    this.fixFallingState = 0;
                }
                else {
                    this.stayAligned();
                }
            }
            default: {
                this.useDefaultMovement();
            }
        }
    }
    
    @Override
    public boolean shouldStop() {
        return false;
    }
    
    @Override
    public void rotateIfNeeded() {
        if (PizzaClient.mc.field_71474_y.field_74312_F.func_151470_d()) {
            return;
        }
        super.rotateIfNeeded();
    }
    
    public Block getBlockToMine() {
        return PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)this.goalPos).func_177230_c();
    }
    
    @Override
    public IMovement[] getDirections() {
        return MiningMoves.values();
    }
    
    @Override
    public void checkCanGoUp(final PathNode currentNode) {
        final BlockPos pos = new BlockPos(currentNode.x, currentNode.y + 2, currentNode.z);
        this.canGoUp = (this.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c()) != -1.0);
    }
    
    public void minePath() {
        if (Rotater.rotating) {
            return;
        }
        final BlockPos pos = this.moves.get(0);
        final double diffY = pos.func_177956_o() - PizzaClient.mc.field_71439_g.field_70163_u;
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(diffY + ""));
        if (diffY == 0.0) {
            if (this.mineBlock(pos)) {
                return;
            }
            this.mineBlock(pos.func_177984_a());
        }
        else if (diffY < 0.0) {
            this.mineBlock(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, (double)(MathUtil.ceil(PizzaClient.mc.field_71439_g.field_70163_u) - 1), PizzaClient.mc.field_71439_g.field_70161_v));
        }
        else {
            if (this.mineBlock(pos)) {
                return;
            }
            if (this.mineBlock(pos.func_177984_a())) {
                return;
            }
            this.mineBlock(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u + 2.0, PizzaClient.mc.field_71439_g.field_70161_v));
        }
    }
    
    public boolean isPathFree() {
        final BlockPos pos = this.moves.get(0);
        final double diffY = pos.func_177956_o() - PizzaClient.mc.field_71439_g.field_70163_u;
        if (diffY == 0.0) {
            return Utils.isUncollidable(pos) && Utils.isUncollidable(pos.func_177984_a());
        }
        if (diffY < 0.0) {
            final BlockPos player = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, (double)MathUtil.ceil(PizzaClient.mc.field_71439_g.field_70163_u), PizzaClient.mc.field_71439_g.field_70161_v);
            return !Utils.isSameXandZ(pos, player) || Utils.isUncollidable(player.func_177977_b());
        }
        return Utils.isUncollidable(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u + 2.0, PizzaClient.mc.field_71439_g.field_70161_v)) && Utils.isUncollidable(pos) && Utils.isUncollidable(pos.func_177984_a());
    }
    
    public boolean isPathFree(final BlockPos pos) {
        final double diffY = pos.func_177956_o() - PizzaClient.mc.field_71439_g.field_70163_u;
        if (diffY == 0.0) {
            return VecUtil.canReachBlock(pos) && Utils.isUncollidable(pos) && Utils.isUncollidable(pos.func_177984_a());
        }
        if (diffY < 0.0) {
            final BlockPos player = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, (double)MathUtil.ceil(PizzaClient.mc.field_71439_g.field_70163_u), PizzaClient.mc.field_71439_g.field_70161_v);
            return !Utils.isSameXandZ(pos, player) && Utils.isUncollidable(player.func_177977_b());
        }
        return VecUtil.canReachBlock(pos) && Utils.isUncollidable(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u + 2.0, PizzaClient.mc.field_71439_g.field_70161_v)) && Utils.isUncollidable(pos) && Utils.isUncollidable(pos.func_177984_a());
    }
    
    public boolean mineBlock(final BlockPos pos) {
        if (PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
            if (PizzaClient.mc.field_71476_x == null || !pos.equals((Object)PizzaClient.mc.field_71476_x.func_178782_a())) {
                new Rotater(new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5)).setRotationAmount(15).addSlightRandomRotateAmount().rotate();
            }
            return true;
        }
        return false;
    }
    
    public abstract double getMiningCost(final BlockPos p0, final Block p1);
}
