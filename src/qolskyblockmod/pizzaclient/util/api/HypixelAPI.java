// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.api;

import com.google.gson.JsonObject;

public class HypixelAPI extends APICategory
{
    public HypixelAPI(final JsonObject json) {
        super(json);
    }
    
    public static HypixelAPI getHypixelAPI() {
        return new HypixelAPI(APICategory.getAPI("player").get("player").getAsJsonObject());
    }
}
