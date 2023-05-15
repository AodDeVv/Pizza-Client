// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.api;

import com.google.gson.JsonArray;

public class MojangAPI
{
    public static String getNameByUUID(final String uuid) {
        final JsonArray arr = APICategory.getJson("https://api.mojang.com/user/profiles/" + uuid + "/names").getAsJsonArray();
        return arr.get(arr.size() - 1).getAsJsonObject().get("name").getAsString();
    }
    
    public static JsonArray getNameHistoryByUUID(final String uuid) {
        return APICategory.getJson("https://api.mojang.com/user/profiles/" + uuid + "/names").getAsJsonArray();
    }
    
    public static String getUUIDByName(final String name) {
        return APICategory.getJson("https://api.mojang.com/users/profiles/minecraft/" + name).getAsJsonObject().get("id").getAsString();
    }
    
    public static JsonArray getNameHistoryByName(final String name) {
        final String uuid = APICategory.getJson("https://api.mojang.com/users/profiles/minecraft/" + name).getAsJsonObject().get("id").getAsString();
        return APICategory.getJson("https://api.mojang.com/user/profiles/" + uuid + "/names").getAsJsonArray();
    }
}
