// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.exceptions;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;

public class APIException extends RuntimeException
{
    public APIException(final String cause) {
        super("An error occured when reading the api. Cause: " + cause + " See logs for more info.");
    }
    
    @Override
    public void printStackTrace() {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + this.getMessage()));
        super.printStackTrace();
    }
}
