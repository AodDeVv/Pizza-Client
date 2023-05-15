// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.core.events;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class SendChatMessageEvent extends Event
{
    public final String message;
    
    public SendChatMessageEvent(final String message) {
        this.message = message.trim();
    }
}
