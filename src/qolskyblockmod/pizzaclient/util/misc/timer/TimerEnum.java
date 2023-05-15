// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.timer;

public interface TimerEnum
{
    public static final Timer timer = new Timer();
    
    int getDelay();
    
    default boolean timePassed() {
        return TimerEnum.timer.timePassed(this.getDelay());
    }
}
