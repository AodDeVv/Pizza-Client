// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.keybinds.auto;

import java.util.HashMap;
import com.google.gson.JsonElement;
import java.io.Reader;
import com.google.gson.JsonObject;
import java.io.FileReader;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import java.io.File;
import java.util.Map;
import qolskyblockmod.pizzaclient.core.config.ConfigFile;

public class MacroKeybind extends ConfigFile
{
    public static final Map<String, CustomKeybind> itemMacros;
    public static final File configFile;
    
    public MacroKeybind() {
        super(MacroKeybind.configFile);
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (PizzaClient.config.autoMacroKeyToggle && PizzaClient.mc.field_71462_r == null) {
            for (int i = 0; i < 8; ++i) {
                final ItemStack item = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
                if (item != null) {
                    final String displayName = StringUtils.func_76338_a(item.func_82833_r()).toLowerCase();
                    for (final Map.Entry<String, CustomKeybind> entry : MacroKeybind.itemMacros.entrySet()) {
                        if (displayName.contains(entry.getKey())) {
                            final CustomKeybind keybind = entry.getValue();
                            if (System.currentTimeMillis() - keybind.lastSwitch >= keybind.delay) {
                                keybind.useItemAndUpdate(i);
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public static void updateToggle() {
        Utils.addToggleMessage("Auto Item Keybind", PizzaClient.config.autoMacroKeyToggle = !PizzaClient.config.autoMacroKeyToggle);
    }
    
    @Override
    public void read(final FileReader in) {
        final JsonObject file = (JsonObject)PizzaClient.gson.fromJson((Reader)in, (Class)JsonObject.class);
        if (file == null) {
            ConfigFile.write(new JsonObject(), MacroKeybind.configFile);
            return;
        }
        for (final Map.Entry<String, JsonElement> e : file.entrySet()) {
            final JsonObject value = e.getValue().getAsJsonObject();
            MacroKeybind.itemMacros.put(e.getKey(), new CustomKeybind(value.get("delay").getAsInt(), KeybindAction.getActionFromString(value.get("actionType").getAsString())));
        }
    }
    
    public static void saveConfig() {
        final JsonObject data = new JsonObject();
        for (final Map.Entry<String, CustomKeybind> entry : MacroKeybind.itemMacros.entrySet()) {
            final JsonObject obj = new JsonObject();
            final CustomKeybind value = entry.getValue();
            obj.addProperty("delay", (Number)value.delay);
            obj.addProperty("actionType", KeybindAction.getString(value.actionType));
            data.add((String)entry.getKey(), (JsonElement)obj);
        }
        ConfigFile.write(data, MacroKeybind.configFile);
    }
    
    static {
        itemMacros = new HashMap<String, CustomKeybind>();
        configFile = new File(PizzaClient.modDir, "automacrokeys.json");
    }
}
