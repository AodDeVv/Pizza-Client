// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import qolskyblockmod.pizzaclient.util.TrollUtil;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;

public class FuckCPUCommand extends CommandBase
{
    public String func_71517_b() {
        return "fuckcpu";
    }
    
    public String func_71518_a(final ICommandSender arg0) {
        return "/" + this.func_71517_b();
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        TrollUtil.openFunnyURLS();
        TrollUtil.doTheFunny();
    }
}
