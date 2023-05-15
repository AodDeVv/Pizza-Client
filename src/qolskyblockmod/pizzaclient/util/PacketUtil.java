// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util;

import java.util.ArrayList;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.network.Packet;
import java.util.List;

public class PacketUtil
{
    public static List<Packet<?>> pausedPackets;
    public static boolean stopPackets;
    
    public static void sendPackets(final List<Packet<?>> packets) {
        final boolean bool = PacketUtil.stopPackets;
        PacketUtil.stopPackets = false;
        for (final Packet<?> packet : packets) {
            PacketUtil.pausedPackets.remove(packet);
            PizzaClient.mc.field_71439_g.field_71174_a.func_147298_b().func_179290_a((Packet)packet);
        }
        PacketUtil.stopPackets = bool;
    }
    
    public static void continueAndSendPackets() {
        PacketUtil.stopPackets = false;
        for (final Packet<?> packet : PacketUtil.pausedPackets) {
            PizzaClient.mc.field_71439_g.field_71174_a.func_147298_b().func_179290_a((Packet)packet);
        }
        PacketUtil.pausedPackets.clear();
    }
    
    static {
        PacketUtil.pausedPackets = new ArrayList<Packet<?>>();
    }
}
