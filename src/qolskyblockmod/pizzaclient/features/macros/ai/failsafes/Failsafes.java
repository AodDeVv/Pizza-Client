// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.failsafes;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import qolskyblockmod.pizzaclient.util.remote.DiscordWebhook;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroState;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.FailsafeRotater;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import net.minecraft.util.MathHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.init.Items;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.SBInfo;
import java.util.List;

public class Failsafes
{
    public static final List<String> messages;
    public static final int TIMEOUT = 1800000;
    
    public static void checkForCaptchas() {
        if (SBInfo.inSkyblock) {
            for (int i = 0; i < 8; ++i) {
                final ItemStack item = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
                if (item == null || item.func_77973_b() != Items.field_151148_bJ) {
                    return;
                }
            }
            Utils.playOrbSound();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "Captcha!!"));
        }
    }
    
    public static void unpressMovement() {
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74370_x.func_151463_i(), false);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74366_z.func_151463_i(), false);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74368_y.func_151463_i(), false);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), false);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i(), false);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
    }
    
    public static void checkBedrockBox() {
        if (PizzaClient.config.bedrockBoxFailsafe && MacroBuilder.currentMacro.applyBedrockFailsafe() && PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u - 1.0, PizzaClient.mc.field_71439_g.field_70161_v)).func_177230_c() == Blocks.field_150357_h) {
            disable();
            final EntityPlayerSP field_71439_g;
            final ChatComponentText chatComponentText;
            final int currentItem;
            MacroBuilder.currentMacro.enqueueFailsafeThread(() -> {
                field_71439_g = PizzaClient.mc.field_71439_g;
                new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "Found bedrock box, applying failsafe.");
                field_71439_g.func_145747_a((IChatComponent)chatComponentText);
                sleepAndStop();
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
                Utils.sleep(1000 + Utils.random.nextInt(400));
                currentItem = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.getAdjacentHotbarSlot();
                sendRandomMessage();
                warpBackAndCooldown();
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = currentItem;
                Utils.sleep(3000);
                reEnable();
                return;
            });
            writeToWebhook("Bedrock Box");
        }
    }
    
    public static void onPacketPosLook(final float yawIn) {
        if (MacroBuilder.currentMacro.applyFailsafes() && MacroBuilder.currentMacro.applyRotationFailsafe()) {
            final float yaw = PizzaClient.mc.field_71439_g.field_70177_z;
            final float pitch = PizzaClient.mc.field_71439_g.field_70125_A;
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Detected rotation packet, applying failsafe. Rotated yaw: " + yawIn));
            disable();
            final float n;
            final float goalYaw;
            final float n2;
            final float goalPitch;
            final EntityPlayerSP field_71439_g;
            final ChatComponentText chatComponentText;
            MacroBuilder.currentMacro.enqueueFailsafeThread(() -> {
                if (PizzaClient.rotater != null) {
                    Utils.sleep(250 + Utils.random.nextInt(150));
                }
                else {
                    Utils.sleep(500 + Utils.random.nextInt(250));
                }
                PizzaClient.rotater = null;
                Utils.sleep(100 + Utils.random.nextInt(50));
                unpressMovement();
                Utils.sleep(1000 + Utils.random.nextInt(200));
                goalYaw = MathHelper.func_76142_g(n - PizzaClient.mc.field_71439_g.field_70177_z);
                goalPitch = MathHelper.func_76142_g(n2 - PizzaClient.mc.field_71439_g.field_70125_A);
                new Rotater(goalYaw, goalPitch).rotate();
                Utils.sleep(10000 + Utils.random.nextInt(3000));
                field_71439_g = PizzaClient.mc.field_71439_g;
                new ChatComponentText(Utils.SUCCESS_MESSAGE + "re-enabling the macro!");
                field_71439_g.func_145747_a((IChatComponent)chatComponentText);
                MacroBuilder.updatePosition();
                return;
            });
            writeToWebhook("Received A Rotation Packet");
        }
    }
    
    public static void onChangePosition() {
        if (PizzaClient.config.positionChangeFailsafe && MacroBuilder.currentMacro.applyFailsafes() && MacroBuilder.currentMacro.applyPositionFailsafe()) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Detected position change, applying failsafe"));
            disable();
            final int currentItem;
            MacroBuilder.currentMacro.enqueueFailsafeThread(() -> {
                sleepAndStop(3000);
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
                Utils.sleep(1000 + Utils.random.nextInt(400));
                currentItem = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.getAdjacentHotbarSlot();
                Utils.sleep(2000);
                warpBackAndCooldown();
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = currentItem;
                Utils.sleep(3000);
                reEnable();
                return;
            });
            writeToWebhook("Player Changed Position");
        }
    }
    
    public static void reEnable() {
        if (!PizzaClient.config.disableOnWorldLoad) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "re-enabling the macro!"));
            MacroBuilder.updatePosition();
        }
        else {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Not re-enabling the macro since \"Disable Macro On Switch World\" is enabled."));
        }
    }
    
    public static boolean hasSorrow(final EntityPlayer entity) {
        for (final ItemStack item : entity.field_71071_by.field_70460_b) {
            if (item != null) {
                final Item item2 = item.func_77973_b();
                if (item2 == Items.field_151029_X || item2 == Items.field_151022_W || item2 == Items.field_151023_V || item2 == Items.field_151020_U) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void sleepAndStop() {
        sleepAndStop(4000);
    }
    
    private static void sleepAndStop(final int rotationAmt) {
        if (PizzaClient.rotater == null) {
            Utils.sleep(500 + Utils.random.nextInt(300));
        }
        else {
            Utils.sleep(300 + Utils.random.nextInt(150));
            PizzaClient.rotater = null;
            Utils.sleep(50 + Utils.random.nextInt(25));
        }
        unpressMovement();
        Utils.sleep(300 + Utils.random.nextInt(150));
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
        Utils.sleep(400 + Utils.random.nextInt(100));
        new FailsafeRotater(rotationAmt).rotate();
        while (Rotater.rotating) {
            Utils.sleep(1);
        }
        Utils.sleep(1500 + Utils.random.nextInt(500));
    }
    
    private static void sneak() {
        Utils.sleep(1000 + Utils.random.nextInt(400));
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
        Utils.sleep(100 + Utils.random.nextInt(50));
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
        Utils.sleep(110 + Utils.random.nextInt(60));
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
        Utils.sleep(100 + Utils.random.nextInt(50));
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
        Utils.sleep(400 + Utils.random.nextInt(100));
    }
    
    private static void sendRandomMessage() {
        Utils.sleep(2400 + Utils.random.nextInt(500));
        final String msg = "/ac " + Failsafes.messages.get(Utils.random.nextInt(Failsafes.messages.size()));
        PizzaClient.mc.field_71439_g.func_71165_d(msg);
        Utils.sleep(300 + Utils.random.nextInt(150));
    }
    
    private static void warpBackAndCooldown() {
        Utils.sleep(3300 + Utils.random.nextInt(1000));
        PizzaClient.mc.field_71439_g.func_71165_d("/hub");
        timeout();
    }
    
    public static void disable() {
        Utils.playOrbSound();
        MacroBuilder.currentMacro.terminateIfAlive();
        MacroBuilder.state = MacroState.FAILSAFE;
    }
    
    public static void writeToWebhook(final String reason) {
        if (PizzaClient.config.failsafeWebhook) {
            if (!PizzaClient.config.failsafeWebhookName.startsWith("https://") || !PizzaClient.config.failsafeWebhookName.contains("discord.com")) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Invalid webhook name."));
                return;
            }
            DiscordWebhook webhook;
            EntityPlayerSP field_71439_g;
            final ChatComponentText chatComponentText;
            new Thread(() -> {
                try {
                    webhook = new DiscordWebhook(PizzaClient.config.failsafeWebhookName);
                    webhook.setUsername("PizzaClient Failsafe");
                    if (PizzaClient.config.failsafeWebhookPing) {
                        webhook.setContent("@everyone");
                    }
                    webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("Failsafe Activated").setDescription(Utils.getFormattedDate()).addField("Username", PizzaClient.username, true).addField("Cause", reason, true).setColor(Color.CYAN));
                    webhook.execute();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    field_71439_g = PizzaClient.mc.field_71439_g;
                    new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to write to the webhook. See logs for more info.");
                    field_71439_g.func_145747_a((IChatComponent)chatComponentText);
                }
            }, "Failsafe Webhook").start();
        }
    }
    
    public static void timeout() {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Disabling the macros, will re-enable in 30 minutes to make sure that the admin has left."));
        Utils.sleep(1800000);
    }
    
    public static void timeout(final int timeout) {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Disabling the macros, will re-enable after a while to make sure that the admin has left."));
        Utils.sleep(timeout);
    }
    
    static {
        messages = new ArrayList<String>(Arrays.asList("hi?", "wtf", "what", "???", "lol wtf?", "uhm what?", "huh??", "tf"));
    }
}
