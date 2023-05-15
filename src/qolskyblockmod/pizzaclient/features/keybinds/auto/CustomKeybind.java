// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.keybinds.auto;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.PizzaClient;

public class CustomKeybind
{
    public int delay;
    public KeybindAction actionType;
    public long lastSwitch;
    
    public CustomKeybind(final int delay, final KeybindAction actionType) {
        this.delay = delay;
        this.actionType = actionType;
        this.lastSwitch = System.currentTimeMillis();
    }
    
    public void useItemAndUpdate(final int slot) {
        final int currentSlot = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = slot;
        if (this.actionType == KeybindAction.LEFT) {
            PlayerUtil.forceUpdateController();
            PlayerUtil.leftClick();
        }
        else {
            PizzaClient.mc.field_71442_b.func_78769_a((EntityPlayer)PizzaClient.mc.field_71439_g, (World)PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[slot]);
        }
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = currentSlot;
        if (PizzaClient.config.autoMacroKeyToggleSync) {
            PlayerUtil.forceUpdateController();
        }
        this.lastSwitch = System.currentTimeMillis();
    }
    
    @Override
    public String toString() {
        return "{delay: " + this.delay + ", actionType: " + KeybindAction.getString(this.actionType) + "}";
    }
}
