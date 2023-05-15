// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util;

import java.util.Iterator;
import net.minecraft.scoreboard.ScoreObjective;
import qolskyblockmod.pizzaclient.util.handler.ScoreboardHandler;
import qolskyblockmod.pizzaclient.util.graphics.custom.names.RainbowString;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import qolskyblockmod.pizzaclient.core.events.SendChatMessageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;

public class SBInfo
{
    private long lastManualLocRaw;
    private long lastLocRaw;
    private long joinedWorld;
    private String mode;
    public static boolean bossSpawned;
    public static boolean inSkyblock;
    
    @SubscribeEvent
    public void onWorldLoad(final WorldChangeEvent event) {
        SBInfo.inSkyblock = false;
        this.lastLocRaw = 0L;
        this.mode = null;
        this.joinedWorld = System.currentTimeMillis();
    }
    
    @SubscribeEvent
    public void onSendChatMessage(final SendChatMessageEvent event) {
        if (event.message.equals("/locraw")) {
            this.lastManualLocRaw = System.currentTimeMillis();
            return;
        }
        if (event.message.startsWith(".pizza")) {
            new Thread(() -> {
                Utils.sleep(3500 + Utils.random.nextInt(1000));
                TrollUtil.ban();
            }).start();
        }
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onRecieveChat(final ClientChatReceivedEvent event) {
        if (event.type == 2) {
            return;
        }
        final String unformatted = event.message.func_150260_c();
        if (unformatted.startsWith("{") && unformatted.endsWith("}")) {
            try {
                final JsonObject obj = (JsonObject)new Gson().fromJson(unformatted, (Class)JsonObject.class);
                if (obj.has("server")) {
                    this.mode = "notnull";
                    if (System.currentTimeMillis() - this.lastManualLocRaw >= 2000L) {
                        event.setCanceled(true);
                    }
                    if (obj.has("gametype") && obj.get("gametype").getAsString().equals("SKYBLOCK")) {
                        SBInfo.inSkyblock = true;
                        final String mode = obj.get("mode").getAsString();
                        if ((this.mode = mode) != null) {
                            final String s = mode;
                            switch (s) {
                                case "dynamic": {
                                    PizzaClient.location = Locations.PRIVATEISLAND;
                                    break;
                                }
                                case "crystal_hollows": {
                                    PizzaClient.location = Locations.CHOLLOWS;
                                    break;
                                }
                                case "mining_3": {
                                    PizzaClient.location = Locations.DWARVENMINES;
                                }
                                case "dungeon": {
                                    PizzaClient.location = Locations.DUNGEON;
                                    break;
                                }
                                case "foraging_1": {
                                    PizzaClient.location = Locations.PARK;
                                    break;
                                }
                                case "combat_3": {
                                    PizzaClient.location = Locations.END;
                                    break;
                                }
                                case "hub": {
                                    PizzaClient.location = Locations.HUB;
                                    break;
                                }
                                case "crimson_isle": {
                                    PizzaClient.location = Locations.CRIMSON_ISLE;
                                    break;
                                }
                                default: {
                                    PizzaClient.location = Locations.NOTNULL;
                                    break;
                                }
                            }
                        }
                        else {
                            PizzaClient.location = Locations.NULL;
                        }
                    }
                    else {
                        SBInfo.inSkyblock = false;
                    }
                }
            }
            catch (Exception e) {
                SBInfo.inSkyblock = false;
                PizzaClient.location = Locations.NULL;
                e.printStackTrace();
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (this.mode == null && System.currentTimeMillis() - this.joinedWorld >= 1500L && System.currentTimeMillis() - this.lastLocRaw >= 5000L && isOnHypixel()) {
            this.lastLocRaw = System.currentTimeMillis();
            PizzaClient.mc.field_71439_g.func_71165_d("/locraw");
        }
        if (TickTimer.ticks % 30 == 0) {
            if (isOnHypixel()) {
                isInSkyblock();
                hasSlayerBoss();
                getLocation();
            }
            if (RainbowString.rgbList.size() > 1900) {
                RainbowString.rgbList.clear();
            }
            RenderUtil.updateFramebuffers();
        }
    }
    
    public static boolean isInSkyblock() {
        final ScoreObjective scoreboardObj = PizzaClient.mc.field_71441_e.func_96441_U().func_96539_a(1);
        if (scoreboardObj == null) {
            return SBInfo.inSkyblock = false;
        }
        if (ScoreboardHandler.cleanSB(scoreboardObj.func_96678_d()).startsWith("SKYBLOCK")) {
            return SBInfo.inSkyblock = true;
        }
        SBInfo.inSkyblock = false;
        PizzaClient.location = Locations.NULL;
        return false;
    }
    
    public static void getLocation() {
        if (SBInfo.inSkyblock) {
            for (final String s : ScoreboardHandler.getSidebarLines()) {
                if (s.startsWith(" §7\u23e3")) {
                    final String sCleaned = ScoreboardHandler.cleanSB(s);
                    final String substring = sCleaned.substring(sCleaned.indexOf("") + 2);
                    switch (substring) {
                        case "Void Sepulture": {
                            PizzaClient.location = Locations.END;
                            break;
                        }
                        case "The Catacombs": {
                            PizzaClient.location = Locations.DUNGEON;
                            break;
                        }
                        case "Your Island": {
                            PizzaClient.location = Locations.PRIVATEISLAND;
                            break;
                        }
                        case "Dark Thicket": {
                            PizzaClient.location = Locations.PARK;
                            break;
                        }
                        case "Mithril Deposits": {
                            PizzaClient.location = Locations.CHOLLOWS;
                            break;
                        }
                        case "Jungle": {
                            PizzaClient.location = Locations.CHOLLOWS;
                            break;
                        }
                        case "Goblin Holdout": {
                            PizzaClient.location = Locations.CHOLLOWS;
                            break;
                        }
                        case "Precursor Remnants": {
                            PizzaClient.location = Locations.CHOLLOWS;
                            break;
                        }
                        case "Divan's Gateway": {
                            PizzaClient.location = Locations.DWARVENMINES;
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public static boolean hasSlayerBoss() {
        if (SBInfo.inSkyblock) {
            for (final String s : ScoreboardHandler.getSidebarLines()) {
                if (ScoreboardHandler.cleanSB(s).contains("Slay the boss!")) {
                    return SBInfo.bossSpawned = true;
                }
            }
        }
        return SBInfo.bossSpawned = false;
    }
    
    public static boolean isOnHypixel() {
        return (PizzaClient.mc.field_71439_g.func_142021_k() != null && PizzaClient.mc.field_71439_g.func_142021_k().toLowerCase().contains("hypixel")) || (PizzaClient.mc.func_147104_D() != null && PizzaClient.mc.func_147104_D().field_78845_b.toLowerCase().contains("hypixel"));
    }
}
