// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.builder.macros;

import qolskyblockmod.pizzaclient.features.macros.builder.MacroState;
import qolskyblockmod.pizzaclient.features.macros.ai.failsafes.Failsafes;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.PizzaClient;

public abstract class Macro
{
    public Thread miscThread;
    
    public Macro() {
        this.miscThread = new Thread(() -> {});
    }
    
    public void addToggleMessage(final String name) {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + name + " is now " + (MacroBuilder.toggled ? (EnumChatFormatting.GREEN + "On") : (EnumChatFormatting.RED + "Off"))));
    }
    
    public abstract void onTick();
    
    public abstract void onToggle(final boolean p0);
    
    public void onRender() {
    }
    
    public void onDisable() {
    }
    
    public void onMove() {
    }
    
    public void onChat(final String msg) {
    }
    
    public void warpBack() {
        final Locations locations = this.getLocation();
        if (locations != null) {
            locations.warpTo();
        }
        else {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "No warp back failsafe for this macro."));
        }
    }
    
    public void writeToWebhook(final String content) {
        Failsafes.writeToWebhook(content);
    }
    
    public abstract boolean applyFailsafes();
    
    public Locations getLocation() {
        return null;
    }
    
    public abstract boolean applyPositionFailsafe();
    
    public abstract boolean applyBedrockFailsafe();
    
    public abstract boolean applyPlayerFailsafes();
    
    public boolean applyRotationFailsafe() {
        return PizzaClient.config.rotationFailsafe;
    }
    
    public void enqueueThread(final Runnable runnable) {
        this.terminateIfAlive();
        MacroBuilder.state = MacroState.NEW_THREAD;
        (this.miscThread = new Thread(runnable)).start();
    }
    
    public void enqueueThreadIfDead(final Runnable runnable) {
        if (!this.miscThread.isAlive()) {
            MacroBuilder.state = MacroState.NEW_THREAD;
            (this.miscThread = new Thread(runnable)).start();
        }
    }
    
    public void enqueueFailsafeThread(final Runnable runnable) {
        this.terminateIfAlive();
        MacroBuilder.state = MacroState.FAILSAFE;
        (this.miscThread = new Thread(runnable)).start();
    }
    
    public void terminateIfAlive() {
        if (this.miscThread.isAlive()) {
            this.terminate();
        }
    }
    
    public void terminate() {
        this.miscThread.stop();
    }
    
    public void onDeath() {
        this.onDisable();
        this.terminateIfAlive();
    }
    
    public boolean acceptGui() {
        return false;
    }
}
