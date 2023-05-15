// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.timer;

public class TickTimer
{
    public static int ticks;
    
    public static void reset() {
        TickTimer.ticks = 0;
    }
    
    public static void increment() {
        ++TickTimer.ticks;
    }
}
