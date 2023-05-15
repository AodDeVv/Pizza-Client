// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.core.guioverlay;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.util.render.RenderType;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import java.util.Iterator;
import com.google.gson.JsonElement;
import java.io.Reader;
import qolskyblockmod.pizzaclient.PizzaClient;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.File;
import java.util.List;
import java.util.Map;
import qolskyblockmod.pizzaclient.core.config.ConfigFile;

public class GuiManager extends ConfigFile
{
    public static final Map<String, GuiLocation> GUIPOSITIONS;
    public static final List<GuiElement> elements;
    public static final File configFile;
    
    public GuiManager() {
        super(GuiManager.configFile);
    }
    
    public static void registerElement(final GuiElement e) {
        GuiManager.elements.add(e);
    }
    
    @Override
    public void read(final FileReader in) {
        final JsonObject file = (JsonObject)PizzaClient.gson.fromJson((Reader)in, (Class)JsonObject.class);
        for (final Map.Entry<String, JsonElement> e : file.entrySet()) {
            GuiManager.GUIPOSITIONS.put(e.getKey(), new GuiLocation(e.getValue().getAsJsonObject().get("x").getAsFloat(), e.getValue().getAsJsonObject().get("y").getAsFloat()));
        }
    }
    
    public static void saveConfig() {
        for (final GuiElement e : GuiManager.elements) {
            GuiManager.GUIPOSITIONS.put(e.getName(), e.pos);
        }
        ConfigFile.write(GuiManager.GUIPOSITIONS, GuiManager.configFile);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void renderPlayerInfo(final RenderGameOverlayEvent.Post event) {
        if (PizzaClient.mc.field_71441_e == null) {
            return;
        }
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            RenderUtil.resolution = event.resolution;
            if (RenderType.shouldRenderOutlines()) {
                RenderType.renderOutlines();
            }
            if (PizzaClient.mc.field_71462_r == null) {
                for (final GuiElement e : GuiManager.elements) {
                    e.render();
                }
            }
        }
    }
    
    static {
        GUIPOSITIONS = new HashMap<String, GuiLocation>();
        elements = new ArrayList<GuiElement>();
        configFile = new File(PizzaClient.modDir, "guipositions.json");
    }
}
