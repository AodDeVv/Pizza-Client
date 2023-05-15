// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.SnapRotater;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.MathHelper;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.command.ICommandSender;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;

public class SetYawCommand extends CommandBase
{
    public String func_71517_b() {
        return "setyaw";
    }
    
    public List<String> func_71514_a() {
        return Collections.singletonList("setrotation");
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return null;
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        switch (args.length) {
            case 1: {
                final String s = args[0];
                try {
                    final float goalYaw = Float.parseFloat(s);
                    final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
                    field_71439_g.field_70177_z += goalYaw - MathHelper.func_76142_g(PizzaClient.mc.field_71439_g.field_70177_z);
                }
                catch (Exception e) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "\"" + s + "\" is not a valid number!"));
                    e.printStackTrace();
                }
                break;
            }
            case 2: {
                final String yaw = args[0];
                try {
                    final float goalYaw2 = Float.parseFloat(yaw);
                    final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
                    field_71439_g2.field_70177_z += goalYaw2 - MathHelper.func_76142_g(PizzaClient.mc.field_71439_g.field_70177_z);
                }
                catch (Exception e2) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "\"" + yaw + "\" is not a valid number!"));
                    e2.printStackTrace();
                }
                final String pitch = args[1];
                try {
                    final float goalPitch = Float.parseFloat(pitch);
                    PizzaClient.mc.field_71439_g.field_70125_A = MathUtil.clampPitch(goalPitch);
                }
                catch (Exception e3) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "\"" + pitch + "\" is not a valid number!"));
                    e3.printStackTrace();
                }
                break;
            }
            case 3: {
                float x;
                try {
                    x = Float.parseFloat(args[0]);
                }
                catch (Exception e4) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "\"" + args[0] + "\" is not a valid number!"));
                    e4.printStackTrace();
                    return;
                }
                float y;
                try {
                    y = Float.parseFloat(args[1]);
                }
                catch (Exception e4) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "\"" + args[1] + "\" is not a valid number!"));
                    e4.printStackTrace();
                    return;
                }
                float z;
                try {
                    z = Float.parseFloat(args[2]);
                }
                catch (Exception e4) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "\"" + args[2] + "\" is not a valid number!"));
                    e4.printStackTrace();
                    return;
                }
                SnapRotater.snapTo(new Vec3((double)x, (double)y, (double)z));
                break;
            }
            default: {
                sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Usage: /setyaw [yaw] or /setyaw [yaw] [pitch]"));
                break;
            }
        }
    }
}
