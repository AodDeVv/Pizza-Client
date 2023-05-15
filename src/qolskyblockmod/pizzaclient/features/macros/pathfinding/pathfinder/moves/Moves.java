// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves;

import net.minecraft.block.Block;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.MutableBlockPosNode;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;

public enum Moves implements IMovement
{
    NORTH {
        @Override
        public boolean addBlock(final PathNode parent) {
            final MutableBlockPosNode pos = BasePathfinder.path.mutableNode;
            pos.field_177962_a = parent.x;
            pos.field_177960_b = parent.y;
            pos.field_177961_c = parent.z - 1;
            if (pos.isBlockLoaded()) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    if (Utils.isUncollidable(pos, block)) {
                        final PathNode previous = parent.previous;
                        pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (this.calculatePos() + 1.1999) : this.calculatePos());
                        return true;
                    }
                    if (BasePathfinder.path.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode3 = pos;
                        mutableBlockPosNode3.field_177960_b += 2;
                        if (Utils.isUncollidable(pos)) {
                            final MutableBlockPosNode mutableBlockPosNode4 = pos;
                            mutableBlockPosNode4.field_177960_b -= 2;
                            if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                ++mutableBlockPosNode5.field_177960_b;
                                final PathNode previous = parent.previous;
                                pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? 2.9999 : 2.4999);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }, 
    EAST {
        @Override
        public boolean addBlock(final PathNode parent) {
            final MutableBlockPosNode pos = BasePathfinder.path.mutableNode;
            pos.field_177962_a = parent.x + 1;
            pos.field_177960_b = parent.y;
            pos.field_177961_c = parent.z;
            if (pos.isBlockLoaded()) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    if (Utils.isUncollidable(pos, block)) {
                        final PathNode previous = parent.previous;
                        pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (this.calculatePos() + 1.1999) : this.calculatePos());
                        return true;
                    }
                    if (BasePathfinder.path.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode3 = pos;
                        mutableBlockPosNode3.field_177960_b += 2;
                        if (Utils.isUncollidable(pos)) {
                            final MutableBlockPosNode mutableBlockPosNode4 = pos;
                            mutableBlockPosNode4.field_177960_b -= 2;
                            if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                ++mutableBlockPosNode5.field_177960_b;
                                final PathNode previous = parent.previous;
                                pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? 2.9999 : 2.4999);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }, 
    SOUTH {
        @Override
        public boolean addBlock(final PathNode parent) {
            final MutableBlockPosNode pos = BasePathfinder.path.mutableNode;
            pos.field_177962_a = parent.x;
            pos.field_177960_b = parent.y;
            pos.field_177961_c = parent.z + 1;
            if (pos.isBlockLoaded()) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    if (Utils.isUncollidable(pos, block)) {
                        final PathNode previous = parent.previous;
                        pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (this.calculatePos() + 1.1999) : this.calculatePos());
                        return true;
                    }
                    if (BasePathfinder.path.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode3 = pos;
                        mutableBlockPosNode3.field_177960_b += 2;
                        if (Utils.isUncollidable(pos)) {
                            final MutableBlockPosNode mutableBlockPosNode4 = pos;
                            mutableBlockPosNode4.field_177960_b -= 2;
                            if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                ++mutableBlockPosNode5.field_177960_b;
                                final PathNode previous = parent.previous;
                                pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? 2.9999 : 2.4999);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }, 
    WEST {
        @Override
        public boolean addBlock(final PathNode parent) {
            final MutableBlockPosNode pos = BasePathfinder.path.mutableNode;
            pos.field_177962_a = parent.x - 1;
            pos.field_177960_b = parent.y;
            pos.field_177961_c = parent.z;
            if (pos.isBlockLoaded()) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    if (Utils.isUncollidable(pos, block)) {
                        final PathNode previous = parent.previous;
                        pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (this.calculatePos() + 1.1999) : this.calculatePos());
                        return true;
                    }
                    if (BasePathfinder.path.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode3 = pos;
                        mutableBlockPosNode3.field_177960_b += 2;
                        if (Utils.isUncollidable(pos)) {
                            final MutableBlockPosNode mutableBlockPosNode4 = pos;
                            mutableBlockPosNode4.field_177960_b -= 2;
                            if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                ++mutableBlockPosNode5.field_177960_b;
                                final PathNode previous = parent.previous;
                                pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? 2.9999 : 2.4999);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    };
}
