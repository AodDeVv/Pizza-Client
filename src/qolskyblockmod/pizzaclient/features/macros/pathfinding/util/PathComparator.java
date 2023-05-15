// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.pathfinding.util;

import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;
import java.util.Comparator;

public class PathComparator implements Comparator<PathBase>
{
    @Override
    public int compare(final PathBase o1, final PathBase o2) {
        return (int)MathUtil.compare(o1.currentPos.distanceToSq(o1.goalPos), o2.currentPos.distanceToSq(o2.goalPos));
    }
}
