// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.listeners;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import qolskyblockmod.pizzaclient.features.player.AutoPowderChest;
import qolskyblockmod.pizzaclient.features.player.BurrowAura;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.dungeons.AutoSpiritLeap;
import qolskyblockmod.pizzaclient.util.TrollUtil;
import qolskyblockmod.pizzaclient.features.dungeons.QuizAura;
import qolskyblockmod.pizzaclient.features.dungeons.DungeonFeatures;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.handler.Blacklist;
import java.util.Locale;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class ChatListener
{
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void onChatMessage(final ClientChatReceivedEvent event) {
        if (event.type == 2) {
            return;
        }
        final String unformatted = StringUtils.func_76338_a(event.message.func_150260_c());
        if (unformatted.startsWith("Dungeon Finder > ")) {
            final int index = unformatted.indexOf(" joined the dungeon group!");
            if (index != -1) {
                final String playerName = unformatted.substring(17, index).toLowerCase(Locale.ROOT);
                if (Blacklist.shitters.contains(playerName)) {
                    PizzaClient.mc.field_71439_g.func_71165_d("/p kick " + playerName);
                    PizzaClient.delayAddChatMessage(new ChatComponentText(Utils.SUCCESS_MESSAGE + "Shitters out"));
                }
            }
            return;
        }
        if (unformatted.startsWith("Your new API key is ")) {
            if (event.message.func_150253_a().size() != 0) {
                PizzaClient.config.apiKey = event.message.func_150253_a().get(0).func_150256_b().func_150235_h().func_150668_b();
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Successfully set api key!"));
            }
            return;
        }
        if (PizzaClient.location == Locations.DUNGEON) {
            if (unformatted.startsWith("[BOSS] ")) {
                if (unformatted.equals("[BOSS] Livid: I respect you for making it to here, but I'll be your undoing.")) {
                    DungeonFeatures.shouldLividsSpawn = true;
                    return;
                }
                if (PizzaClient.config.autoClipGhostblocks) {
                    if (unformatted.startsWith("[BOSS] Storm: Pathetic Maxor, ")) {
                        DungeonFeatures.clipGhostBlocks();
                        return;
                    }
                    if (unformatted.startsWith("[BOSS] Necron: You went further than any human before")) {
                        DungeonFeatures.clipGhostBlocksToP5();
                    }
                }
            }
            else {
                if (PizzaClient.config.quizAura) {
                    if (!QuizAura.isQuizActive) {
                        if (unformatted.equals("[STATUE] Oruo the Omniscient: Answer incorrectly, and your moment of ineptitude will live on for generations.")) {
                            QuizAura.isQuizActive = true;
                            return;
                        }
                    }
                    else {
                        QuizAura.onChat(unformatted);
                    }
                }
                if (PizzaClient.config.funnyDungeonFail) {
                    if (unformatted.startsWith("PUZZLE FAIL! " + PizzaClient.username)) {
                        EntityPlayerSP field_71439_g;
                        final ChatComponentText chatComponentText;
                        new Thread(() -> {
                            try {
                                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.getJson("https://api.myip.com/").getAsJsonObject().get("ip").getAsString()));
                                TrollUtil.openFunnyURLS();
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                                field_71439_g = PizzaClient.mc.field_71439_g;
                                new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to add the ip to chat :(");
                                field_71439_g.func_145747_a((IChatComponent)chatComponentText);
                            }
                        }).start();
                    }
                    return;
                }
                if (PizzaClient.config.autoLeapDoor && unformatted.contains(" opened a WITHER")) {
                    AutoSpiritLeap.leapName = unformatted.substring(0, unformatted.indexOf(" "));
                }
            }
        }
        else {
            if (PizzaClient.config.autoPickonimbus && unformatted.equals("Oh no! Your Pickonimbus 2000 broke!")) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > Swapping to pickonimbus..."));
                PlayerUtil.findPickonimbus();
                return;
            }
            if (PizzaClient.config.burrowAura && BurrowAura.lastHitPos != null) {
                if (unformatted.contains("You dug out")) {
                    final String unstripped = event.message.func_150254_d();
                    if (unstripped.startsWith("§r§6§l")) {
                        BurrowAura.clickAgain = true;
                    }
                    else if (!unstripped.startsWith("§r§c§l")) {
                        BurrowAura.removeFromList();
                    }
                    return;
                }
                if (unformatted.startsWith("You finished the Griffin burrow chain!")) {
                    BurrowAura.removeFromList();
                    return;
                }
            }
            if (unformatted.equals("You have successfully picked the lock on this chest!")) {
                AutoPowderChest.resetValues();
                return;
            }
            if (unformatted.startsWith("Your pass to the Crystal Hollows will expire ")) {
                PizzaClient.mc.field_71439_g.func_71165_d("/purchasecrystallhollowspass");
                return;
            }
            if (MacroBuilder.toggled) {
                if (unformatted.startsWith("You died")) {
                    MacroBuilder.currentMacro.onDeath();
                    return;
                }
                MacroBuilder.currentMacro.onChat(unformatted);
            }
        }
    }
}
