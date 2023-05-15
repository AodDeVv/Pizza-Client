// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc;

import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;

public enum Locations
{
    PRIVATEISLAND("Private Island", () -> PizzaClient.mc.field_71439_g.func_71165_d("/is")), 
    CHOLLOWS("Crystal Hollows", PlayerUtil::warpToChollows), 
    DWARVENMINES("Dwarven Mines", () -> PizzaClient.mc.field_71439_g.func_71165_d("/warp forge")), 
    PARK("Park", () -> PizzaClient.mc.field_71439_g.func_71165_d("/warp park")), 
    END("The End"), 
    DUNGEON("Dungeons"), 
    HUB("The Hub"), 
    NULL("None"), 
    NOTNULL("Unknown"), 
    CRIMSON_ISLE("Crimson Isle");
    
    private final String name;
    private final Runnable warpBack;
    
    private Locations(final String name) {
        this.name = name;
        this.warpBack = null;
    }
    
    private Locations(final String name, final Runnable warpBack) {
        this.name = name;
        this.warpBack = warpBack;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void warpTo() {
        if (this.warpBack != null) {
            warpToSb();
            while (PizzaClient.location != this) {
                closeScreen();
                this.warpBack.run();
                Utils.sleep(3000);
            }
            Utils.sleep(1500);
        }
    }
    
    public static void warpToSb() {
        closeScreen();
        while (PlayerUtil.isInLimbo()) {
            closeScreen();
            PizzaClient.mc.field_71439_g.func_71165_d("/l");
            Utils.sleep(5000);
        }
        while (PlayerUtil.isInLobby()) {
            closeScreen();
            PizzaClient.mc.field_71439_g.func_71165_d("/play sb");
            Utils.sleep(5000);
        }
        Utils.sleep(2000);
    }
    
    public static void closeScreen() {
        while (PizzaClient.mc.field_71462_r != null) {
            PizzaClient.mc.field_71439_g.func_71053_j();
            Utils.sleep(500);
        }
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
