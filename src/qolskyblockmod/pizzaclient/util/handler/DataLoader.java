// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.handler;

import qolskyblockmod.pizzaclient.features.dungeons.ThreeWeirdosAura;
import qolskyblockmod.pizzaclient.features.dungeons.QuizAura;
import qolskyblockmod.pizzaclient.util.graphics.custom.names.RainbowString;
import qolskyblockmod.pizzaclient.PizzaClient;

public class DataLoader
{
    private static Thread fetcher;
    private static boolean allLoaded;
    
    private static boolean isDead() {
        return !DataLoader.fetcher.isAlive();
    }
    
    private static void loadData() {
        if (!DataLoader.fetcher.isAlive()) {
            (DataLoader.fetcher = new Thread(() -> {
                try {
                    PizzaClient.loadResponse();
                    RainbowString.updateList();
                    QuizAura.loadSolutions();
                    ThreeWeirdosAura.loadSolutions();
                    DataLoader.allLoaded = true;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            })).start();
        }
    }
    
    private static void reload() {
        if (!DataLoader.fetcher.isAlive()) {
            (DataLoader.fetcher = new Thread(() -> {
                try {
                    RainbowString.updateList();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            })).start();
        }
    }
    
    public static void fetch() {
        if (!DataLoader.allLoaded) {
            loadData();
        }
        else {
            reload();
        }
    }
    
    static {
        DataLoader.fetcher = new Thread();
    }
}
