// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc.timer;

public class Timer
{
    private long lastInteractTime;
    
    public boolean timePassed(final int time) {
        if (System.currentTimeMillis() - this.lastInteractTime >= time) {
            this.updateTimer();
            return true;
        }
        return false;
    }
    
    public static boolean timePassed(final long lastInteractTime, final int time) {
        return System.currentTimeMillis() - lastInteractTime >= time;
    }
    
    public void updateTimer() {
        this.lastInteractTime = System.currentTimeMillis();
    }
}
