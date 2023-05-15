// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.client.settings.GameSettings;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class AutoClicker
{
    private static boolean toggled;
    private long lastInteractTime;
    
    @SubscribeEvent
    public void onRenderTick(final RenderWorldLastEvent event) {
        if (AutoClicker.toggled) {
            if (PizzaClient.mc.field_71462_r == null) {
                this.use();
            }
            else if (GameSettings.func_100015_a(PizzaClient.keyBindings[0])) {
                AutoClicker.toggled = false;
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "AutoClicker is now " + EnumChatFormatting.RED + "Off"));
            }
        }
        else if (PizzaClient.config.autoClickerHold && PizzaClient.keyBindings[0].func_151470_d()) {
            this.use();
        }
    }
    
    private void use() {
        if (System.currentTimeMillis() - this.lastInteractTime >= PizzaClient.config.autoClickerDelay) {
            switch (PizzaClient.config.autoClickerType) {
                case 0: {
                    KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i());
                    this.updateDelay();
                    this.lastInteractTime += MathUtil.clamp(MathUtil.floor(Utils.random.nextGaussian() * 20.0), -(PizzaClient.config.autoClickerDelay / 2), PizzaClient.config.autoClickerDelay / 2);
                    break;
                }
                case 1: {
                    PlayerUtil.rightClick();
                    this.updateDelay();
                    break;
                }
                case 3: {
                    PlayerUtil.useAbility();
                    this.updateDelay();
                    break;
                }
            }
        }
    }
    
    private void updateDelay() {
        this.lastInteractTime = System.currentTimeMillis() - this.lastInteractTime % PizzaClient.config.autoClickerDelay;
    }
    
    public static void toggle() {
        if (PizzaClient.config.autoClickerType == 2) {
            PlayerUtil.rightClick(PizzaClient.config.clickAmount);
            AutoClicker.toggled = false;
            return;
        }
        if (PizzaClient.config.autoClickerHold) {
            AutoClicker.toggled = false;
            return;
        }
        AutoClicker.toggled = !AutoClicker.toggled;
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "AutoClicker is now " + Utils.getColouredBoolean(AutoClicker.toggled)));
    }
}
