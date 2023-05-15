// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import java.util.HashSet;
import java.util.Iterator;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonElement;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Arrays;
import qolskyblockmod.pizzaclient.util.Utils;
import java.io.Reader;
import com.google.gson.JsonArray;
import java.io.FileReader;
import java.util.Locale;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.command.ICommandSender;
import com.google.common.collect.Lists;
import java.util.List;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.io.File;
import java.util.Set;
import net.minecraft.command.ICommand;
import net.minecraft.command.CommandBase;

public class SilencerCommand extends CommandBase implements ICommand
{
    public static Set<String> silencedSounds;
    private static File saveFile;
    
    public SilencerCommand() {
        SilencerCommand.saveFile = new File(PizzaClient.modDir, "soundsilencer.json");
        reloadSave();
    }
    
    public String func_71517_b() {
        return "silencer";
    }
    
    public List<String> func_71514_a() {
        return (List<String>)Lists.newArrayList((Object[])new String[] { "soundsilencer", "silencesound", "silence" });
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return EnumChatFormatting.RED + "Usages: /silencer + add + [sound]\n" + EnumChatFormatting.RED + "/silencer + remove + all or [name]\n" + EnumChatFormatting.RED + "/silencer list";
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        final EntityPlayerSP player = (EntityPlayerSP)sender;
        switch (args.length) {
            case 1: {
                if (args[0].equalsIgnoreCase("list")) {
                    player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Silenced sounds: \n" + EnumChatFormatting.GOLD + SilencerCommand.silencedSounds));
                    return;
                }
                player.func_145747_a((IChatComponent)new ChatComponentText(this.func_71518_a(sender)));
                break;
            }
            case 2: {
                final String lowerCase2 = args[0].toLowerCase();
                switch (lowerCase2) {
                    case "add": {
                        if (args[1] == null) {
                            player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Usages: /silencer + add + [sound]"));
                            break;
                        }
                        final String lowerCase = args[1].toLowerCase(Locale.ROOT);
                        if (!SilencerCommand.silencedSounds.contains(lowerCase)) {
                            SilencerCommand.silencedSounds.add(lowerCase);
                            writeSave();
                            player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Successfully added " + EnumChatFormatting.GOLD + args[1].toLowerCase() + EnumChatFormatting.GREEN + "."));
                            return;
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + args[1] + " is already added!"));
                        return;
                    }
                    case "remove": {
                        if (args[1].equalsIgnoreCase("all")) {
                            SilencerCommand.silencedSounds.clear();
                            writeSave();
                            player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Cleared all silencers."));
                            return;
                        }
                        if (SilencerCommand.silencedSounds.contains(args[1])) {
                            SilencerCommand.silencedSounds.remove(args[1]);
                            writeSave();
                            player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Successfully removed the sound \"" + args[1] + "\" from the silencer!"));
                            return;
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "the sound \"" + args[1] + "\" does not exist in the silencer!"));
                        break;
                    }
                    default: {
                        player.func_145747_a((IChatComponent)new ChatComponentText(this.func_71518_a(sender)));
                        break;
                    }
                }
                break;
            }
            default: {
                player.func_145747_a((IChatComponent)new ChatComponentText(this.func_71518_a(sender)));
                break;
            }
        }
    }
    
    public static void reloadSave() {
        SilencerCommand.silencedSounds.clear();
        try (final FileReader in = new FileReader(SilencerCommand.saveFile)) {
            final JsonArray dataArray = (JsonArray)PizzaClient.gson.fromJson((Reader)in, (Class)JsonArray.class);
            SilencerCommand.silencedSounds.addAll(Arrays.asList(Utils.getStringArrayFromJsonArray(dataArray)));
        }
        catch (Exception e) {
            final JsonArray dataArray = new JsonArray();
            try (final FileWriter writer = new FileWriter(SilencerCommand.saveFile)) {
                PizzaClient.gson.toJson((JsonElement)dataArray, (Appendable)writer);
            }
            catch (Exception ex) {}
        }
    }
    
    public static void writeSave() {
        try (final FileWriter writer = new FileWriter(SilencerCommand.saveFile)) {
            final JsonArray arr = new JsonArray();
            for (final String itemId : SilencerCommand.silencedSounds) {
                arr.add((JsonElement)new JsonPrimitive(itemId));
            }
            PizzaClient.gson.toJson((JsonElement)arr, (Appendable)writer);
        }
        catch (Exception ex) {}
    }
    
    static {
        SilencerCommand.silencedSounds = new HashSet<String>();
    }
}
