// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.mining.nuker;

import qolskyblockmod.pizzaclient.PizzaClient;

public class NukerBase
{
    public static INuker nuker;
    private static float extraPacket;
    
    public static boolean nukedThisTick() {
        if (NukerBase.nuker != null) {
            NukerBase.nuker.onNukePre();
            if (!NukerBase.nuker.isVecValid() && !NukerBase.nuker.findVec()) {
                NukerBase.nuker.onNoVecAvailable();
                NukerBase.nuker = null;
                NukerBase.extraPacket = 0.0f;
                return false;
            }
            if (NukerBase.nuker.nuke(false)) {
                if (PizzaClient.config.nukerExtraPackets != 0) {
                    NukerBase.extraPacket += PizzaClient.config.nukerExtraPackets;
                    for (int i = 0; i < NukerBase.extraPacket / 20.0f; ++i) {
                        NukerBase.nuker.onNukePre();
                        if (!NukerBase.nuker.isVecValid() && !NukerBase.nuker.findVec()) {
                            NukerBase.nuker.onNoVecAvailable();
                            NukerBase.nuker = null;
                            NukerBase.extraPacket = 0.0f;
                            return true;
                        }
                        if (!NukerBase.nuker.nuke(true)) {
                            NukerBase.extraPacket = 0.0f;
                        }
                    }
                    NukerBase.extraPacket %= 20.0f;
                }
                if (NukerBase.nuker.invalidate()) {
                    NukerBase.nuker = null;
                }
                return true;
            }
            NukerBase.nuker = null;
        }
        return false;
    }
}
