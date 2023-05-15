// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import net.minecraft.command.CommandException;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.command.ICommandSender;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;

public class AutoPetCommand extends CommandBase
{
    public static String clickSlot;
    
    public String func_71517_b() {
        return "autopet";
    }
    
    public List<String> func_71514_a() {
        return Collections.singletonList("autopets");
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) throws CommandException {
        PizzaClient.mc.field_71439_g.func_71165_d("/pets");
        final StringBuilder sb = new StringBuilder();
        for (final String s : args) {
            sb.append(s.toLowerCase()).append(" ");
        }
        AutoPetCommand.clickSlot = sb.substring(0, sb.toString().length() - 1);
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return null;
    }
    
    public int func_82362_a() {
        return 0;
    }
}
