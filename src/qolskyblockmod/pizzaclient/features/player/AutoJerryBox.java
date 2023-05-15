// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import net.minecraft.item.ItemStack;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.ChestBackgroundDrawnEvent;

public class AutoJerryBox
{
    private static boolean clickBox;
    private static boolean clickedSlot;
    private static long lastInteractTime;
    
    @SubscribeEvent
    public void onRenderGui(final ChestBackgroundDrawnEvent event) {
        if (PizzaClient.config.autoJerryBox && event.displayName.equals("Open a Jerry Box")) {
            AutoJerryBox.clickBox = true;
            if (AutoJerryBox.lastInteractTime == 0L) {
                AutoJerryBox.lastInteractTime = System.currentTimeMillis();
                return;
            }
            if (System.currentTimeMillis() - AutoJerryBox.lastInteractTime >= 400 + PizzaClient.config.autoJerryBoxDelay + PizzaClient.config.autoJerryBoxClosingDelay) {
                PizzaClient.mc.field_71439_g.func_71053_j();
                AutoJerryBox.lastInteractTime = 0L;
                AutoJerryBox.clickedSlot = false;
            }
            else if (System.currentTimeMillis() - AutoJerryBox.lastInteractTime >= 300 + PizzaClient.config.autoJerryBoxDelay && !AutoJerryBox.clickedSlot) {
                PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                AutoJerryBox.clickedSlot = true;
            }
        }
    }
    
    public static void useJerryBox() {
        if (AutoJerryBox.clickBox) {
            AutoJerryBox.clickedSlot = false;
            if (AutoJerryBox.lastInteractTime == 0L) {
                AutoJerryBox.lastInteractTime = System.currentTimeMillis();
                return;
            }
            if (System.currentTimeMillis() - AutoJerryBox.lastInteractTime >= PizzaClient.config.autoJerryBoxOpenDelay + 100) {
                KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                AutoJerryBox.clickBox = false;
                AutoJerryBox.lastInteractTime = 0L;
            }
        }
    }
    
    public static void swapToBox() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack stack = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
            if (stack != null && stack.func_82833_r().contains("Jerry Box")) {
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
                break;
            }
        }
    }
    
    static {
        AutoJerryBox.clickBox = true;
    }
}
