// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.failsafes;

import qolskyblockmod.pizzaclient.util.render.RenderType;
import java.awt.Color;
import net.minecraft.util.StringUtils;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import java.util.Iterator;
import net.minecraft.client.renderer.culling.Frustum;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraft.util.Vec3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.entity.Entity;

public class PlayerDetectionFailsafe
{
    private static Entity niceGuy;
    private static long lastInteractTime;
    private static int phase;
    
    public static boolean checkForPlayers() {
        if (!PizzaClient.config.stopWhenNearPlayer || !MacroBuilder.currentMacro.applyPlayerFailsafes()) {
            return false;
        }
        if (PlayerDetectionFailsafe.niceGuy != null) {
            onPlayerSeen();
            return true;
        }
        return isBeingSeen();
    }
    
    private static boolean isBeingSeen() {
        final Entity player = PlayerUtil.getFacingPlayer();
        if (player != null) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "A nice player with the name " + player.func_70005_c_() + EnumChatFormatting.GREEN + " is standing infront of you!"));
            PlayerDetectionFailsafe.niceGuy = player;
            return true;
        }
        final Frustum frustum = RenderUtil.setupFrustrum();
        final Vec3 position = PlayerUtil.getPositionEyes();
        for (final Entity e : PizzaClient.mc.field_71441_e.field_72996_f) {
            if (e instanceof EntityOtherPlayerMP) {
                final EntityPlayer entity = (EntityPlayer)e;
                final String name = entity.func_145748_c_().func_150254_d();
                if (!name.startsWith("§r§") || name.startsWith("§r§f") || name.endsWith(" ")) {
                    continue;
                }
                if (entity.func_82150_aj() && !Failsafes.hasSorrow(entity)) {
                    continue;
                }
                if (position.func_72436_e(new Vec3(entity.field_70165_t, entity.field_70163_u + 1.6200000047683716, entity.field_70161_v)) < PizzaClient.config.stopWhenNearPlayerRange * PizzaClient.config.stopWhenNearPlayerRange && VecUtil.isPlayerBeingLookedAt(entity, frustum)) {
                    PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + name + " is looking at you!"));
                    PlayerDetectionFailsafe.niceGuy = e;
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    private static void onPlayerSeen() {
        Label_0215: {
            switch (PlayerDetectionFailsafe.phase) {
                case 0: {
                    PlayerDetectionFailsafe.lastInteractTime = System.currentTimeMillis();
                    PlayerDetectionFailsafe.phase = 1;
                }
                case 1: {
                    if (System.currentTimeMillis() - PlayerDetectionFailsafe.lastInteractTime < 250L || TickTimer.ticks % 3 != 0) {
                        break;
                    }
                    if (!isBeingSeen()) {
                        PlayerDetectionFailsafe.niceGuy = null;
                        PlayerDetectionFailsafe.phase = 0;
                        return;
                    }
                    Failsafes.unpressMovement();
                    PlayerDetectionFailsafe.lastInteractTime = System.currentTimeMillis();
                    PlayerDetectionFailsafe.phase = 2;
                    break;
                }
                case 2: {
                    if (System.currentTimeMillis() - PlayerDetectionFailsafe.lastInteractTime < 150L || TickTimer.ticks % 3 != 0) {
                        break Label_0215;
                    }
                    if (!isBeingSeen()) {
                        PlayerDetectionFailsafe.niceGuy = null;
                        PlayerDetectionFailsafe.phase = 0;
                        return;
                    }
                    Failsafes.unpressMovement();
                    new Rotater(PlayerDetectionFailsafe.niceGuy.func_174824_e(1.0f)).antiSus(12.0f, 15.0f).rotate();
                    Failsafes.writeToWebhook("Player Detection (Name: " + StringUtils.func_76338_a(PlayerDetectionFailsafe.niceGuy.func_70005_c_()) + ")");
                    Utils.playOrbSound();
                    PlayerDetectionFailsafe.lastInteractTime = System.currentTimeMillis();
                    PlayerDetectionFailsafe.phase = 3;
                    break Label_0215;
                }
                case 3: {
                    if (System.currentTimeMillis() - PlayerDetectionFailsafe.lastInteractTime < 4500L || TickTimer.ticks % 30 != 0) {
                        break;
                    }
                    if (!isBeingSeen()) {
                        PlayerDetectionFailsafe.niceGuy = null;
                        PlayerDetectionFailsafe.phase = 0;
                        return;
                    }
                    PizzaClient.mc.field_71439_g.func_71165_d("/hub");
                    PlayerDetectionFailsafe.lastInteractTime = System.currentTimeMillis();
                    PlayerDetectionFailsafe.phase = 4;
                    break;
                }
                case 4: {
                    if (System.currentTimeMillis() - PlayerDetectionFailsafe.lastInteractTime >= 4000L) {
                        PlayerDetectionFailsafe.phase = 0;
                        PlayerDetectionFailsafe.niceGuy = null;
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public static void renderNiceGuy() {
        if (PlayerDetectionFailsafe.niceGuy != null) {
            RenderType.setUniversalOutlineColor(Color.RED);
            RenderType.addEntity(PlayerDetectionFailsafe.niceGuy);
        }
    }
}
