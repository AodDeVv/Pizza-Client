// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.api;

import java.util.UUID;
import qolskyblockmod.pizzaclient.util.exceptions.APIException;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import com.google.gson.JsonParser;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpGet;
import java.net.URI;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class APICategory
{
    protected final JsonObject json;
    
    public APICategory(final JsonObject json) {
        this.json = json;
    }
    
    public static JsonElement getJson(final String endpoint) {
        try {
            final HttpGet httpGet = new HttpGet(new URI(endpoint));
            httpGet.addHeader("User-Agent", "Mozilla/5.0");
            final CloseableHttpResponse response = HttpClientBuilder.create().build().execute((HttpUriRequest)httpGet);
            final HttpEntity entity = response.getEntity();
            final String json = EntityUtils.toString(entity);
            EntityUtils.consume(response.getEntity());
            return new JsonParser().parse(json);
        }
        catch (Exception e) {
            e.printStackTrace();
            return (JsonElement)new JsonObject();
        }
    }
    
    public static JsonObject getAPI(final String path) {
        if (PizzaClient.config.apiKey.length() < 10) {
            PizzaClient.mc.field_71439_g.func_145747_a(new ChatComponentText(Utils.ERROR_MESSAGE + "One of your features requires an api key! " + EnumChatFormatting.GOLD + "Click here to generate a new key!").func_150255_a(new ChatStyle().func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/api new"))));
            throw new APIException("No API Key.");
        }
        final JsonElement json = getJson("https://api.hypixel.net/" + path + "?key=" + PizzaClient.config.apiKey + "&uuid=" + PizzaClient.mc.field_71439_g.func_146103_bH().getId());
        if (json == null) {
            throw new APIException("Failed to get a response.");
        }
        final JsonObject object = json.getAsJsonObject();
        if (!object.get("success").getAsBoolean()) {
            throw new APIException("Reponse returned \"false\". Report this.");
        }
        return object;
    }
    
    public static String getKey() {
        return PizzaClient.config.apiKey;
    }
    
    public static String getDashedUUID() {
        return PizzaClient.mc.field_71439_g.func_146103_bH().getId().toString();
    }
    
    public static UUID getUUID() {
        return PizzaClient.mc.field_71439_g.func_146103_bH().getId();
    }
    
    public static String getNonDashedUUID() {
        return PizzaClient.mc.func_110432_I().func_148255_b();
    }
}
