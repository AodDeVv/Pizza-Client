// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.moves;

import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.MutableBlockPosNode;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.PathNode;

public enum FlyMoves implements IMovement
{
    NORTH {
        @Override
        public boolean addBlock(final PathNode parent) {
            final MutableBlockPosNode pos = BasePathfinder.path.mutableNode;
            pos.field_177962_a = parent.x;
            pos.field_177960_b = parent.y;
            pos.field_177961_c = parent.z - 1;
            if (pos.isBlockLoaded() && Utils.isUncollidable(pos)) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    pos.cost = 0.9999;
                    return true;
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
            if (pos.isBlockLoaded() && Utils.isUncollidable(pos)) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    pos.cost = 0.9999;
                    return true;
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
            if (pos.isBlockLoaded() && Utils.isUncollidable(pos)) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    pos.cost = 0.9999;
                    return true;
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
            if (pos.isBlockLoaded() && Utils.isUncollidable(pos)) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    pos.cost = 0.9999;
                    return true;
                }
            }
            return false;
        }
    }, 
    UP {
        @Override
        public boolean addBlock(final PathNode parent) {
            final MutableBlockPosNode pos = BasePathfinder.path.mutableNode;
            pos.field_177962_a = parent.x;
            pos.field_177960_b = parent.y + 1;
            pos.field_177961_c = parent.z;
            if (Utils.isUncollidable(pos)) {
                final MutableBlockPosNode mutableBlockPosNode = pos;
                ++mutableBlockPosNode.field_177960_b;
                if (Utils.isUncollidable(pos)) {
                    final MutableBlockPosNode mutableBlockPosNode2 = pos;
                    --mutableBlockPosNode2.field_177960_b;
                    pos.cost = 0.9999;
                    return true;
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
            if (Utils.isUncollidable(pos)) {
                pos.cost = 0.9999;
                return true;
            }
            return false;
        }
    };
}
