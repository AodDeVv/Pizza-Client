// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.dungeons;

import java.util.ArrayList;
import java.util.Iterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import java.util.List;

public class ThreeWeirdosAura
{
    public static final List<String> correctAnswers;
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
    }
    
    public static void loadSolutions() {
        if (ThreeWeirdosAura.correctAnswers.size() == 0) {
            final JsonArray creditsToSkytils = Utils.getJson("https://cdn.jsdelivr.net/gh/Skytils/SkytilsMod-Data@main/solvers/threeweirdos.json").getAsJsonArray();
            for (final JsonElement s : creditsToSkytils) {
                ThreeWeirdosAura.correctAnswers.add(s.getAsString());
            }
        }
    }
    
    static {
        correctAnswers = new ArrayList<String>();
    }
}
