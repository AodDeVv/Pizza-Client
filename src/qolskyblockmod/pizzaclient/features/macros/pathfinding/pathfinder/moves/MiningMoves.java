// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves;

import net.minecraft.block.Block;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.MutableBlockPosNode;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.AdvancedMiningBot;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;

public enum MiningMoves implements IMovement
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
                final AdvancedMiningBot miningBot = (AdvancedMiningBot)BasePathfinder.path;
                final double extraCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                if (extraCost != -1.0) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    final double cost = miningBot.getMiningCost(pos, block);
                    if (cost != -1.0) {
                        if (cost != 0.0 && miningBot.canGoUp) {
                            final MutableBlockPosNode mutableBlockPosNode3 = pos;
                            mutableBlockPosNode3.field_177960_b += 2;
                            final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                            if (nextCost != -1.0) {
                                final MutableBlockPosNode mutableBlockPosNode4 = pos;
                                mutableBlockPosNode4.field_177960_b -= 2;
                                if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                    final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                    ++mutableBlockPosNode5.field_177960_b;
                                    final PathNode previous = parent.previous;
                                    pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                                    return true;
                                }
                            }
                        }
                        final PathNode previous2 = parent.previous;
                        pos.cost = ((previous2 != null && previous2.x - pos.field_177962_a != 0 && previous2.z - pos.field_177961_c != 0) ? (this.calculatePos() + cost + extraCost + 1.1999) : (this.calculatePos() + cost + extraCost));
                        return true;
                    }
                    if (miningBot.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode6 = pos;
                        mutableBlockPosNode6.field_177960_b += 2;
                        final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                        if (nextCost != -1.0) {
                            final MutableBlockPosNode mutableBlockPosNode7 = pos;
                            --mutableBlockPosNode7.field_177960_b;
                            final PathNode previous = parent.previous;
                            pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                            return true;
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
                final AdvancedMiningBot miningBot = (AdvancedMiningBot)BasePathfinder.path;
                final double extraCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                if (extraCost != -1.0) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    final double cost = miningBot.getMiningCost(pos, block);
                    if (cost != -1.0) {
                        if (cost != 0.0 && miningBot.canGoUp) {
                            final MutableBlockPosNode mutableBlockPosNode3 = pos;
                            mutableBlockPosNode3.field_177960_b += 2;
                            final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                            if (nextCost != -1.0) {
                                final MutableBlockPosNode mutableBlockPosNode4 = pos;
                                mutableBlockPosNode4.field_177960_b -= 2;
                                if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                    final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                    ++mutableBlockPosNode5.field_177960_b;
                                    final PathNode previous = parent.previous;
                                    pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                                    return true;
                                }
                            }
                        }
                        final PathNode previous2 = parent.previous;
                        pos.cost = ((previous2 != null && previous2.x - pos.field_177962_a != 0 && previous2.z - pos.field_177961_c != 0) ? (this.calculatePos() + cost + extraCost + 1.1999) : (this.calculatePos() + cost + extraCost));
                        return true;
                    }
                    if (miningBot.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode6 = pos;
                        mutableBlockPosNode6.field_177960_b += 2;
                        final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                        if (nextCost != -1.0) {
                            final MutableBlockPosNode mutableBlockPosNode7 = pos;
                            --mutableBlockPosNode7.field_177960_b;
                            final PathNode previous = parent.previous;
                            pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                            return true;
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
                final AdvancedMiningBot miningBot = (AdvancedMiningBot)BasePathfinder.path;
                final double extraCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                if (extraCost != -1.0) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    final double cost = miningBot.getMiningCost(pos, block);
                    if (cost != -1.0) {
                        if (cost != 0.0 && miningBot.canGoUp) {
                            final MutableBlockPosNode mutableBlockPosNode3 = pos;
                            mutableBlockPosNode3.field_177960_b += 2;
                            final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                            if (nextCost != -1.0) {
                                final MutableBlockPosNode mutableBlockPosNode4 = pos;
                                mutableBlockPosNode4.field_177960_b -= 2;
                                if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                    final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                    ++mutableBlockPosNode5.field_177960_b;
                                    final PathNode previous = parent.previous;
                                    pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                                    return true;
                                }
                            }
                        }
                        final PathNode previous2 = parent.previous;
                        pos.cost = ((previous2 != null && previous2.x - pos.field_177962_a != 0 && previous2.z - pos.field_177961_c != 0) ? (this.calculatePos() + cost + extraCost + 1.1999) : (this.calculatePos() + cost + extraCost));
                        return true;
                    }
                    if (miningBot.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode6 = pos;
                        mutableBlockPosNode6.field_177960_b += 2;
                        final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                        if (nextCost != -1.0) {
                            final MutableBlockPosNode mutableBlockPosNode7 = pos;
                            --mutableBlockPosNode7.field_177960_b;
                            final PathNode previous = parent.previous;
                            pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                            return true;
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
                final AdvancedMiningBot miningBot = (AdvancedMiningBot)BasePathfinder.path;
                final double extraCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                if (extraCost != -1.0) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
                    final double cost = miningBot.getMiningCost(pos, block);
                    if (cost != -1.0) {
                        if (cost != 0.0 && miningBot.canGoUp) {
                            final MutableBlockPosNode mutableBlockPosNode3 = pos;
                            mutableBlockPosNode3.field_177960_b += 2;
                            final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                            if (nextCost != -1.0) {
                                final MutableBlockPosNode mutableBlockPosNode4 = pos;
                                mutableBlockPosNode4.field_177960_b -= 2;
                                if (pos.field_177960_b + block.func_149669_A() - (parent.y + parent.getBlock().func_149669_A()) <= 1.0) {
                                    final MutableBlockPosNode mutableBlockPosNode5 = pos;
                                    ++mutableBlockPosNode5.field_177960_b;
                                    final PathNode previous = parent.previous;
                                    pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                                    return true;
                                }
                            }
                        }
                        final PathNode previous2 = parent.previous;
                        pos.cost = ((previous2 != null && previous2.x - pos.field_177962_a != 0 && previous2.z - pos.field_177961_c != 0) ? (this.calculatePos() + cost + extraCost + 1.1999) : (this.calculatePos() + cost + extraCost));
                        return true;
                    }
                    if (miningBot.canGoUp) {
                        final MutableBlockPosNode mutableBlockPosNode6 = pos;
                        mutableBlockPosNode6.field_177960_b += 2;
                        final double nextCost = miningBot.getMiningCost(pos, PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c());
                        if (nextCost != -1.0) {
                            final MutableBlockPosNode mutableBlockPosNode7 = pos;
                            --mutableBlockPosNode7.field_177960_b;
                            final PathNode previous = parent.previous;
                            pos.cost = ((previous != null && previous.x - pos.field_177962_a != 0 && previous.z - pos.field_177961_c != 0) ? (2.4999 + nextCost + extraCost) : (1.9999 + nextCost + extraCost));
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }, 
    DOWN {
        @Override
        public boolean addBlock(final PathNode parent) {
            final MutableBlockPosNode pos = BasePathfinder.path.mutableNode;
            pos.field_177962_a = parent.x;
            pos.field_177960_b = parent.y - 1;
            pos.field_177961_c = parent.z;
            final AdvancedMiningBot miningBot = (AdvancedMiningBot)BasePathfinder.path;
            final Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
            final double cost = miningBot.getMiningCost(pos, block);
            if (cost != -1.0) {
                pos.cost = this.calculatePos() + cost;
                return true;
            }
            return false;
        }
    };
}
