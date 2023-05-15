// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.remote;

import com.google.gson.JsonObject;
import java.io.Reader;
import java.io.InputStreamReader;
import com.google.gson.JsonParser;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import org.apache.commons.io.Charsets;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Base64;
import qolskyblockmod.pizzaclient.util.Utils;
import java.awt.image.BufferedImage;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class Imgur
{
    public static final String id = "649f2fb48e59767";
    
    public static String upload(final File file) {
        try {
            return upload(FileUtils.readFileToByteArray(file));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String upload(final BufferedImage image) {
        return upload(Utils.toByteArray(image, "png"));
    }
    
    public static String uploadScreenshot() {
        return upload(Utils.toByteArray(Utils.takeScreenshot(), "png"));
    }
    
    public static String upload(final byte[] bytes) {
        try {
            final String data = Base64.getEncoder().encodeToString(bytes);
            final String params = "image=" + URLEncoder.encode(data, "UTF-8");
            final HttpURLConnection connection = (HttpURLConnection)new URL("https://api.imgur.com/3/image").openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Client-ID 649f2fb48e59767");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), Charsets.UTF_8));
            writer.write(params);
            if (connection.getResponseCode() != 200) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to upload image to imgur. Imgur returned response code " + connection.getResponseCode()));
            }
            final JsonObject imgurJson = new JsonParser().parse((Reader)new InputStreamReader(connection.getInputStream(), Charsets.UTF_8)).getAsJsonObject();
            final JsonObject dataJson = imgurJson.get("data").getAsJsonObject();
            return dataJson.get("link").getAsString();
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to upload to imgur. See logs for more info."));
            return null;
        }
    }
}
