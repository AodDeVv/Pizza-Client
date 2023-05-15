// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.handler;

public class ThreadHandler
{
    public static Thread thread;
    
    public static boolean isDead() {
        return !ThreadHandler.thread.isAlive();
    }
    
    public static boolean isAlive() {
        return ThreadHandler.thread.isAlive();
    }
    
    public static void enqueue(final Runnable runnable) {
        (ThreadHandler.thread = new Thread(runnable)).start();
    }
    
    public static void enqueueIfDead(final Runnable runnable) {
        if (!ThreadHandler.thread.isAlive()) {
            (ThreadHandler.thread = new Thread(runnable)).start();
        }
    }
    
    static {
        ThreadHandler.thread = new Thread();
    }
}
