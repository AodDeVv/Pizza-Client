// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import net.minecraft.command.CommandException;
import qolskyblockmod.pizzaclient.features.keybinds.auto.CustomKeybind;
import qolskyblockmod.pizzaclient.features.keybinds.auto.KeybindAction;
import qolskyblockmod.pizzaclient.features.keybinds.auto.MacroKeybind;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;

public class ItemMacroCommand extends CommandBase
{
    public String func_71517_b() {
        return "itemmacro";
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return EnumChatFormatting.RED + "Usage: /itemmacro to see all the item macros or /itemmacro add [part of the item name] + [delay in ms] + [\"left\" or \"right\"] or /itemmacro remove [name]";
    }
    
    public List<String> func_71514_a() {
        return (List<String>)Lists.newArrayList((Object[])new String[] { "itemmacros", "itemmacrokey", "itemmacrokeys" });
    }
    
    public void sendMessage(final ICommandSender sender) {
        sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Usage: /itemmacro to see all the item macros or /itemmacro add [part of the item name] + [delay in ms] + [\"left\" or \"right\"] or /itemmacro remove [name]"));
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) throws CommandException {
        switch (args.length) {
            case 1: {
                if (args[0].equalsIgnoreCase("list")) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText(MacroKeybind.itemMacros.toString()));
                    return;
                }
                this.sendMessage(sender);
                break;
            }
            case 0: {
                sender.func_145747_a((IChatComponent)new ChatComponentText(MacroKeybind.itemMacros.toString()));
                break;
            }
            default: {
                if (args[0].equalsIgnoreCase("add")) {
                    int delay = -1;
                    final StringBuilder sb = new StringBuilder(args[1]);
                    int i = 2;
                    while (i < 11) {
                        try {
                            delay = Integer.parseInt(args[i]);
                        }
                        catch (Exception ignored) {
                            sb.append(" ");
                            sb.append(args[i]);
                            ++i;
                            continue;
                        }
                        break;
                    }
                    if (delay <= -1) {
                        sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "The item displayname was either too long or the delay was invalid/non existant."));
                    }
                    final KeybindAction action = KeybindAction.getActionFromString(args[i + 1]);
                    if (action == KeybindAction.UNKNOWN) {
                        sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "The third parameter must equal to left or right."));
                        return;
                    }
                    MacroKeybind.itemMacros.put(sb.toString().toLowerCase(), new CustomKeybind(delay, action));
                    sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Successfully added " + (Object)sb + " to the list."));
                    MacroKeybind.saveConfig();
                    break;
                }
                else {
                    if (args[0].equalsIgnoreCase("remove")) {
                        final StringBuilder sb2 = new StringBuilder(args[1]);
                        for (int i = 2; i < args.length; ++i) {
                            sb2.append(" ");
                            sb2.append(args[i]);
                        }
                        final String name = sb2.toString().toLowerCase();
                        if (MacroKeybind.itemMacros.containsKey(name)) {
                            MacroKeybind.itemMacros.remove(name);
                            sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Successfully removed " + (Object)sb2 + " from the list."));
                            MacroKeybind.saveConfig();
                        }
                        else {
                            sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "You need to use the exact name of the item that you want to remove"));
                        }
                        break;
                    }
                    int delay = -1;
                    final StringBuilder sb = new StringBuilder(args[0]);
                    int i = 1;
                    while (i < 10) {
                        try {
                            delay = Integer.parseInt(args[i]);
                        }
                        catch (Exception ignored) {
                            sb.append(" ");
                            sb.append(args[i]);
                            ++i;
                            continue;
                        }
                        break;
                    }
                    if (delay <= -1) {
                        sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "The item displayname was either too long or the delay was invalid/non existant."));
                    }
                    final KeybindAction action = KeybindAction.getActionFromString(args[i + 1]);
                    if (action == KeybindAction.UNKNOWN) {
                        sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "The third parameter must equal to left or right."));
                        return;
                    }
                    MacroKeybind.itemMacros.put(sb.toString().toLowerCase(), new CustomKeybind(delay, action));
                    sender.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Successfully added " + (Object)sb + " to the list."));
                    MacroKeybind.saveConfig();
                    break;
                }
                break;
            }
        }
    }
}
