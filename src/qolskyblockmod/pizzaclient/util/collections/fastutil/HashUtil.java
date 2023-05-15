// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.collections.fastutil;

import qolskyblockmod.pizzaclient.util.MathUtil;

public class HashUtil
{
    private static final int INT_PHI = -1640531527;
    public static final float DEFAULT_LOAD_FACTOR = 0.7f;
    public static final int DEFAULT_INITIAL_SIZE = 16;
    
    public static int mix(final int x) {
        final int h = x * -1640531527;
        return h ^ h >>> 16;
    }
    
    public static int arraySize(final int expected, final float f) {
        return MathUtil.max(2, nextPowerOfTwo(MathUtil.ceil(expected / f) - 1));
    }
    
    private static int nextPowerOfTwo(int x) {
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        return (x | x >> 16) + 1;
    }
    
    public static int maxFill(final int n, final float f) {
        return MathUtil.min(MathUtil.ceil(n * f), n - 1);
    }
}
