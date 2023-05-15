// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.api;

import java.util.Iterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SkyblockAPI extends APICategory
{
    public SkyblockAPI(final JsonObject json) {
        super(json);
    }
    
    public static SkyblockAPI getLatestProfileSkyblockAPI() {
        final JsonArray profiles = APICategory.getAPI("skyblock/profiles").get("profiles").getAsJsonArray();
        long lastSave = 0L;
        JsonObject currentProfile = null;
        for (final JsonElement element : profiles) {
            final JsonObject obj = element.getAsJsonObject().get("members").getAsJsonObject().get(APICategory.getNonDashedUUID()).getAsJsonObject();
            final long save = obj.get("last_save").getAsLong();
            if (lastSave < save) {
                currentProfile = obj;
                lastSave = save;
            }
        }
        return new SkyblockAPI(currentProfile);
    }
    
    public JsonArray getBurrows() {
        return this.json.get("griffin").getAsJsonObject().get("burrows").getAsJsonArray();
    }
    
    public long getPurse() {
        return 0L;
    }
}
