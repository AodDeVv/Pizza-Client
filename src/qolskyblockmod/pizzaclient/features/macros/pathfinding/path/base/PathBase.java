// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base;

import java.util.Iterator;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.NoMovementPathfinder;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.rotation.RotationUtil;
import qolskyblockmod.pizzaclient.util.rotation.Rotation;
import net.minecraft.block.Block;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.world.World;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.block.BlockLiquid;
import net.minecraft.world.IBlockAccess;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraft.util.AxisAlignedBB;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import net.minecraft.util.EnumFacing;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.MovementType;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.Pathfinding;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.util.ArrayList;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.MutableBlockPosNode;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.PathNodeOpenSet;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;
import qolskyblockmod.pizzaclient.util.collections.fastutil.maps.IntObjectOpenHashMap;
import net.minecraft.util.BlockPos;
import java.util.List;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;

public abstract class PathBase implements IPath
{
    public BetterBlockPos goalPos;
    public BetterBlockPos currentPos;
    public boolean finished;
    public List<BlockPos> moves;
    public final IntObjectOpenHashMap<PathNode> checked;
    public final PathNodeOpenSet openSet;
    public final MutableBlockPosNode mutableNode;
    public boolean canGoUp;
    public int fixFallingState;
    public long lastPathfindTime;
    
    public PathBase(final Vec3 from, final Vec3 to) {
        this.moves = new ArrayList<BlockPos>();
        this.checked = new IntObjectOpenHashMap<PathNode>(1024, 0.6f);
        this.openSet = new PathNodeOpenSet();
        this.mutableNode = new MutableBlockPosNode();
        this.currentPos = new BetterBlockPos(from);
        this.goalPos = new BetterBlockPos(to);
    }
    
    public PathBase(final BetterBlockPos from, final BetterBlockPos to) {
        this.moves = new ArrayList<BlockPos>();
        this.checked = new IntObjectOpenHashMap<PathNode>(1024, 0.6f);
        this.openSet = new PathNodeOpenSet();
        this.mutableNode = new MutableBlockPosNode();
        this.currentPos = from;
        this.goalPos = to;
    }
    
    public PathBase(final BetterBlockPos to) {
        this.moves = new ArrayList<BlockPos>();
        this.checked = new IntObjectOpenHashMap<PathNode>(1024, 0.6f);
        this.openSet = new PathNodeOpenSet();
        this.mutableNode = new MutableBlockPosNode();
        this.currentPos = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, MathUtil.ceil(PizzaClient.mc.field_71439_g.field_70163_u), PizzaClient.mc.field_71439_g.field_70161_v);
        this.goalPos = to;
    }
    
    public PathBase(final Vec3 to) {
        this.moves = new ArrayList<BlockPos>();
        this.checked = new IntObjectOpenHashMap<PathNode>(1024, 0.6f);
        this.openSet = new PathNodeOpenSet();
        this.mutableNode = new MutableBlockPosNode();
        this.currentPos = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, MathUtil.ceil(PizzaClient.mc.field_71439_g.field_70163_u), PizzaClient.mc.field_71439_g.field_70161_v);
        this.goalPos = new BetterBlockPos(to);
    }
    
    @Override
    public void shutdown() {
        this.finished = true;
        Movement.disableMovement();
        AStarPathfinder.path = null;
        Pathfinding.unregister();
        PizzaClient.rotater = null;
        this.moves.clear();
    }
    
    public final void addMovement(final BlockPos current) {
        Movement.endMovement();
        if (!PizzaClient.mc.field_71439_g.field_70122_E) {
            return;
        }
        final double nextTickX = PizzaClient.mc.field_71439_g.field_70165_t;
        final double nextTickZ = PizzaClient.mc.field_71439_g.field_70161_v;
        final EnumFacing facing = PizzaClient.mc.field_71439_g.func_174811_aO();
        if (!MathUtil.inBetween(nextTickZ, current.func_177952_p() + 0.3, current.func_177952_p() + 0.7)) {
            if (nextTickZ <= current.func_177952_p() + 0.3) {
                switch (facing) {
                    case NORTH: {
                        Movement.addMovement(MovementType.BACKWARDS);
                        break;
                    }
                    case SOUTH: {
                        Movement.addMovement(MovementType.FORWARDS);
                        break;
                    }
                    case WEST: {
                        Movement.addMovement(MovementType.LEFT);
                        break;
                    }
                    case EAST: {
                        Movement.addMovement(MovementType.RIGHT);
                        break;
                    }
                }
            }
            else if (nextTickZ >= current.func_177952_p() + 0.7) {
                switch (facing) {
                    case NORTH: {
                        Movement.addMovement(MovementType.FORWARDS);
                        break;
                    }
                    case SOUTH: {
                        Movement.addMovement(MovementType.BACKWARDS);
                        break;
                    }
                    case WEST: {
                        Movement.addMovement(MovementType.RIGHT);
                        break;
                    }
                    case EAST: {
                        Movement.addMovement(MovementType.LEFT);
                        break;
                    }
                }
            }
        }
        if (!MathUtil.inBetween(nextTickX, current.func_177958_n() + 0.3, current.func_177958_n() + 0.7)) {
            if (nextTickX <= current.func_177958_n() + 0.3) {
                switch (facing) {
                    case NORTH: {
                        Movement.addMovement(MovementType.RIGHT);
                        break;
                    }
                    case SOUTH: {
                        Movement.addMovement(MovementType.LEFT);
                        break;
                    }
                    case WEST: {
                        Movement.addMovement(MovementType.BACKWARDS);
                        break;
                    }
                    case EAST: {
                        Movement.addMovement(MovementType.FORWARDS);
                        break;
                    }
                }
            }
            else if (nextTickX >= current.func_177958_n() + 0.7) {
                switch (facing) {
                    case NORTH: {
                        Movement.addMovement(MovementType.LEFT);
                        break;
                    }
                    case SOUTH: {
                        Movement.addMovement(MovementType.RIGHT);
                        break;
                    }
                    case WEST: {
                        Movement.addMovement(MovementType.FORWARDS);
                        break;
                    }
                    case EAST: {
                        Movement.addMovement(MovementType.BACKWARDS);
                        break;
                    }
                }
            }
        }
    }
    
    public void rotateIfNeeded() {
        if (!Rotater.rotating) {
            final int index = MathUtil.min(this.moves.size(), 4) - 1;
            final EnumFacing facing = PizzaClient.mc.field_71439_g.func_174811_aO();
            if (!this.moves.get(0).func_177972_a(facing).equals((Object)this.moves.get(index).func_177967_a(facing, 3))) {
                new Rotater(this.moves.get(index)).setRotationAmount(15).addSlightRandomRotateAmount().rotate();
            }
        }
    }
    
    public final boolean isVerticalPassable(final AxisAlignedBB aabb) {
        return VecUtil.entityRayTrace(aabb);
    }
    
    public final void useDefaultMovement() {
        if (this.shouldStop()) {
            return;
        }
        final Vec3 position = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        final BetterBlockPos player = new BetterBlockPos(position);
        final BlockPos current = this.moves.get(0);
        if (PizzaClient.mc.field_71439_g.field_70122_E) {
            this.addMovement(current);
        }
        else {
            Movement.disableMovement();
            Movement.addMovement(position, current);
        }
        final BlockPos pos = current.func_177977_b();
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        block.func_180654_a((IBlockAccess)PizzaClient.mc.field_71441_e, pos);
        if (PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)player).func_177230_c() instanceof BlockLiquid) {
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), true);
            return;
        }
        if (PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u - 0.4, PizzaClient.mc.field_71439_g.field_70161_v)).func_177230_c() instanceof BlockLiquid) {
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), true);
            return;
        }
        if (block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos).field_72337_e > PizzaClient.mc.field_71439_g.field_70163_u + 0.6 && PizzaClient.mc.field_71439_g.field_70122_E && !Utils.isUncollidable(block, pos)) {
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), true);
            this.fixFallingState = 1;
            return;
        }
        if (!PizzaClient.mc.field_71439_g.field_70122_E) {
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
            this.fixFallingState = 1;
            return;
        }
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
        this.rotateIfNeeded();
    }
    
    public void update() {
        this.currentPos = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        this.checked.clear();
        this.moves.clear();
    }
    
    public void update(final BetterBlockPos goalPos) {
        this.currentPos = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        this.goalPos = goalPos;
        this.checked.clear();
        this.moves.clear();
    }
    
    public void initRotater() {
        new Rotater(RotationUtil.getYawClosestTo90Degrees(Rotation.getRotation(Utils.toVec(this.moves.get(1))).yaw) - PizzaClient.mc.field_71439_g.field_70177_z, 0.0f).randomPitch().setRotationAmount(17).rotate();
        while (PizzaClient.rotater != null) {
            Utils.sleep(5);
        }
        Utils.sleep(100);
    }
    
    public final void closeScreenIfOpen() {
        while (PizzaClient.mc.field_71462_r != null) {
            Movement.disableMovement();
            PizzaClient.mc.field_71439_g.func_71053_j();
            Utils.sleep(600);
        }
    }
    
    public void clearPath() {
        this.openSet.clear();
        this.checked.clear();
        this.mutableNode.resetValues();
    }
    
    public boolean isJumping() {
        return true;
    }
    
    public void onCorner() {
        Movement.moveOpposite();
    }
    
    public void stayAligned() {
        final Vec3 position = PlayerUtil.getMotionPosition();
        final BetterBlockPos player = new BetterBlockPos(position);
        if (this.moves.size() < 2 || PizzaClient.mc.field_71439_g.field_70181_x > 0.0) {
            final boolean move = Movement.isMovingForward();
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74368_y.func_151463_i(), move);
            return;
        }
        for (int i = 0; i < this.moves.size(); ++i) {
            final BlockPos pos = this.moves.get(i);
            if (player.isSameXandZ(pos)) {
                if (this.isPathFree(player, pos)) {
                    this.moves.subList(0, i).clear();
                    Movement.addMovement(position, this.moves.get(0));
                }
                else {
                    final boolean move2 = Movement.isMovingForward();
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74368_y.func_151463_i(), move2);
                }
                return;
            }
        }
        Movement.addMovement(position, this.moves.get(0));
    }
    
    public boolean isPathFree(final BetterBlockPos pos, final BlockPos next) {
        if (this.moves.size() < 2) {
            return false;
        }
        final BetterBlockPos copy = pos.copyOf();
        copy.field_177960_b = next.func_177956_o() + 1;
        while (copy.field_177960_b <= pos.field_177960_b + 1) {
            if (!Utils.isUncollidable(copy)) {
                return false;
            }
            final BetterBlockPos betterBlockPos = copy;
            ++betterBlockPos.field_177960_b;
        }
        return true;
    }
    
    public void recalculateMoves() {
        new Thread(() -> {
            try {
                new NoMovementPathfinder(this.reuse()).run(false);
            }
            catch (Exception ex) {}
        }).start();
    }
    
    public final boolean removeNode() {
        return this.moves.size() != 1 || PizzaClient.mc.field_71439_g.field_70122_E;
    }
    
    public boolean hasNode(final BetterBlockPos player) {
        for (final BlockPos pos : this.moves) {
            if (player.isSameXandZ(pos)) {
                final BetterBlockPos copy = player.copyOf();
                copy.field_177960_b = pos.func_177956_o();
                while (copy.field_177960_b <= player.field_177960_b + 1) {
                    if (!Utils.isUncollidable(copy)) {
                        return false;
                    }
                    final BetterBlockPos betterBlockPos = copy;
                    ++betterBlockPos.field_177960_b;
                }
                return true;
            }
        }
        return false;
    }
    
    public final void updatePathfindTime() {
        this.lastPathfindTime = System.currentTimeMillis();
    }
    
    public PathBase reuse() {
        this.clearCache();
        this.currentPos = new BetterBlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        return this;
    }
    
    public void clearCache() {
        this.openSet.clear();
        this.checked.clear();
    }
    
    public boolean shouldRecalcPath() {
        return System.currentTimeMillis() - this.lastPathfindTime >= 1400L && this.moves.size() > 3;
    }
    
    public void checkCanGoUp(final PathNode currentNode) {
        this.canGoUp = Utils.isUncollidable(new BlockPos(currentNode.x, currentNode.y + 2, currentNode.z));
    }
    
    public boolean shouldStop() {
        final Vec3 position = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        final BetterBlockPos player = new BetterBlockPos(position);
        if (this.removeNode() && this.hasNode(player)) {
            if (this.moves.size() < 2 || this.fixFallingState != 0) {
                while (!player.isSameXandZ(this.moves.get(0))) {
                    this.moves.remove(0);
                }
                this.moves.remove(0);
                return true;
            }
            final MovementType type = Movement.calculateRequiredMovement(this.moves.get(0), this.moves.get(1));
            while (!player.isSameXandZ(this.moves.get(0))) {
                this.moves.remove(0);
            }
            this.moves.remove(0);
            if (this.moves.size() >= 2 && type != Movement.calculateRequiredMovement(this.moves.get(0), this.moves.get(1))) {
                this.onCorner();
                return true;
            }
        }
        switch (this.fixFallingState) {
            case 1: {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
                PlayerUtil.moveOpposite();
                this.fixFallingState = 2;
                return true;
            }
            case 2: {
                Movement.disableMovement();
                if (Movement.isMovingForward()) {
                    this.fixFallingState = 3;
                }
                else {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), true);
                }
                return true;
            }
            case 3: {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
                if (PizzaClient.mc.field_71439_g.field_70122_E) {
                    this.fixFallingState = 0;
                }
                else {
                    this.stayAligned();
                }
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    @Override
    public void onBeginMove() {
        this.initRotater();
    }
}
