// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.core.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.io.FileWriter;
import java.util.Map;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public abstract class ConfigFile
{
    public ConfigFile(final File file) {
        if (!file.exists()) {
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Something went wrong when trying to create a new config file.");
            }
        }
        try {
            this.read(new FileReader(file));
        }
        catch (Exception ex) {}
    }
    
    public static void write(final Map<?, ?> obj, final File configFile) {
        try (final FileWriter writer = new FileWriter(configFile)) {
            PizzaClient.gson.toJson((Object)obj, (Appendable)writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void write(final List<?> obj, final File configFile) {
        try (final FileWriter writer = new FileWriter(configFile)) {
            PizzaClient.gson.toJson((Object)obj, (Appendable)writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void write(final String obj, final File configFile) {
        try (final FileWriter writer = new FileWriter(configFile)) {
            PizzaClient.gson.toJson((Object)obj, (Appendable)writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void write(final JsonObject obj, final File configFile) {
        try (final FileWriter writer = new FileWriter(configFile)) {
            PizzaClient.gson.toJson((JsonElement)obj, (Appendable)writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void write(final JsonArray obj, final File configFile) {
        try (final FileWriter writer = new FileWriter(configFile)) {
            PizzaClient.gson.toJson((JsonElement)obj, (Appendable)writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public abstract void read(final FileReader p0);
}
