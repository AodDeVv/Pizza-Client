// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import qolskyblockmod.pizzaclient.gui.LocationEditGui;
import qolskyblockmod.pizzaclient.core.config.Config;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.client.gui.GuiScreen;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.gui.StartGui;
import net.minecraft.command.ICommandSender;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.command.CommandBase;

public class PizzaClientGuiCommand extends CommandBase
{
    public String func_71517_b() {
        return "pizzaclient";
    }
    
    public List<String> func_71514_a() {
        return (List<String>)Lists.newArrayList((Object[])new String[] { "pizza" });
    }
    
    public String func_71518_a(final ICommandSender arg0) {
        return "/" + this.func_71517_b();
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        if (args.length == 0) {
            PizzaClient.displayScreen(new StartGui());
            return;
        }
        if (args.length == 1) {
            final String s = args[0];
            switch (s) {
                case "config": {
                    PizzaClient.displayScreen(new StartGui());
                    break;
                }
                case "arab": {
                    Utils.openUrl(ArabFunnyCommand.arabfunny.get(Utils.random.nextInt(ArabFunnyCommand.arabfunny.size())));
                    break;
                }
                case "arabfunny": {
                    Utils.openUrl(ArabFunnyCommand.arabfunny.get(Utils.random.nextInt(ArabFunnyCommand.arabfunny.size())));
                    break;
                }
                case "commands": {
                    Config.sendCommandsList();
                    PizzaClient.mc.field_71439_g.func_71053_j();
                    break;
                }
                case "editlocations": {
                    PizzaClient.mc.func_147108_a((GuiScreen)new LocationEditGui());
                    break;
                }
                case "locationedit": {
                    PizzaClient.mc.func_147108_a((GuiScreen)new LocationEditGui());
                    break;
                }
                case "discord": {
                    Utils.openUrl("https://discord.gg/NWeacCr3B8");
                    break;
                }
            }
        }
    }
}
