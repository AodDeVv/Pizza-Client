// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.listeners;

import qolskyblockmod.pizzaclient.commands.SilencerCommand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.client.audio.ISound;

public class SoundListener
{
    public static boolean onPlaySound(final ISound p_sound, final String sound) {
        if (PizzaClient.config.getCurrentSound) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Current Sound Playing: " + EnumChatFormatting.AQUA + sound + ", pitch: " + p_sound.func_147655_f() + ", volume: " + p_sound.func_147653_e()));
        }
        if (PizzaClient.config.autoHarpSolver && sound.equals("note.bassattack") && PizzaClient.mc.field_71462_r instanceof GuiChest && ((ContainerChest)PizzaClient.mc.field_71439_g.field_71070_bA).func_85151_d().func_145748_c_().func_150260_c().trim().startsWith("Harp ")) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "The Harp Macro clicked too early/late. Try changing the delay."));
        }
        return SilencerCommand.silencedSounds.contains(sound);
    }
}
