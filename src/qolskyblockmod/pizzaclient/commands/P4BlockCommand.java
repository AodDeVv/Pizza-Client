// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.command.ICommandSender;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.command.CommandBase;

public class P4BlockCommand extends CommandBase
{
    public static BlockPos emeraldPos;
    
    public String func_71517_b() {
        return "p4";
    }
    
    public List<String> func_71514_a() {
        return (List<String>)Lists.newArrayList((Object[])new String[] { "p4block" });
    }
    
    public String func_71518_a(final ICommandSender arg0) {
        return "/" + this.func_71517_b();
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        P4BlockCommand.emeraldPos = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, 63.0, PizzaClient.mc.field_71439_g.field_70161_v);
        PizzaClient.mc.field_71441_e.func_180501_a(P4BlockCommand.emeraldPos, Blocks.field_150475_bE.func_176223_P(), 3);
    }
}
