// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.Entity;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;

public class KuudraAuraCommand extends CommandBase
{
    public String func_71517_b() {
        return "kuudra";
    }
    
    public String func_71518_a(final ICommandSender arg0) {
        return "/" + this.func_71517_b();
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        if (args.length != 1) {
            sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Usage: /kuudra [cannon/shop]"));
        }
        final String s = args[0];
        switch (s) {
            case "cannon": {
                for (final Entity entity : PizzaClient.mc.field_71441_e.field_72996_f) {
                    if (entity instanceof EntityArmorStand && entity.func_145818_k_() && entity.func_95999_t().contains("RIGHT-CLICK TO MOUNT")) {
                        PizzaClient.mc.field_71442_b.func_78768_b((EntityPlayer)PizzaClient.mc.field_71439_g, entity);
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Clicked cannon!"));
                        return;
                    }
                }
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to find a cannon armor stand!"));
                break;
            }
            case "shop": {
                for (final Entity entity : PizzaClient.mc.field_71441_e.field_72996_f) {
                    if (entity instanceof EntityArmorStand && entity.func_145818_k_() && entity.func_95999_t().contains("Cannon Perk Shop")) {
                        PizzaClient.mc.field_71442_b.func_78768_b((EntityPlayer)PizzaClient.mc.field_71439_g, entity);
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Clicked shop!"));
                        return;
                    }
                }
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to find the shop armor stand!"));
                break;
            }
            default: {
                sender.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Usage: /kuudra [cannon/shop]"));
                break;
            }
        }
    }
}
