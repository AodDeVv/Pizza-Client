// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.command.ICommandSender;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;

public class ArabFunnyCommand extends CommandBase
{
    public static final List<String> arabfunny;
    
    public String func_71517_b() {
        return "arabfunny";
    }
    
    public List<String> func_71514_a() {
        return Collections.singletonList("arab");
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return null;
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        Utils.openUrl(ArabFunnyCommand.arabfunny.get(Utils.random.nextInt(ArabFunnyCommand.arabfunny.size())));
    }
    
    static {
        arabfunny = new ArrayList<String>(Arrays.asList("https://www.youtube.com/watch?v=Azc7A9iKPYk", "https://www.youtube.com/watch?v=8VnYuh19Kxg", "https://www.youtube.com/watch?v=xjNzkgfL3uQ", "https://www.youtube.com/watch?v=6BPhlI50H5Q", "https://www.youtube.com/watch?v=bVRmdTRFofE", "https://www.youtube.com/watch?v=RgGa_uU85Oc", "https://www.youtube.com/watch?v=KYQLjz-h5lM", "https://www.youtube.com/watch?v=qutVOs_T08Q", "https://www.youtube.com/watch?v=PJ98ihwT1yg"));
    }
}
