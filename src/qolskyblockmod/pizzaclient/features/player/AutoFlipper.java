// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import java.util.HashMap;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatStyle;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.util.EnumChatFormatting;
import java.util.Iterator;
import java.util.List;
import com.google.gson.JsonElement;
import java.util.HashSet;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Set;

public class AutoFlipper
{
    private static final long[] lastUpdates;
    private static final Set<String> filter;
    public static final Set<String> checked;
    public static final Map<String, String> itemIds;
    private JsonObject neuResponse;
    private Thread thread;
    
    public AutoFlipper() {
        this.thread = new Thread();
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (!PizzaClient.config.autoFlipper) {
            return;
        }
        if (!this.thread.isAlive()) {
            if (AutoFlipper.itemIds.size() == 0) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Trying to load in items..."));
                (this.thread = new Thread(AutoFlipper::loadIds, "AutoFlipper")).start();
            }
            else {
                this.flipper();
            }
        }
    }
    
    private void flipper() {
        final JsonObject auctionPage;
        final int pageAmount;
        final long lastUpdated;
        int i;
        JsonObject page;
        long update;
        (this.thread = new Thread(() -> {
            this.neuResponse = Utils.getJson("https://api.skytils.gg/api/auctions/lowestbins").getAsJsonObject();
            auctionPage = Utils.getJson("https://api.hypixel.net/skyblock/auctions?page=0").getAsJsonObject();
            pageAmount = auctionPage.get("totalPages").getAsInt() - 1;
            lastUpdated = auctionPage.get("lastUpdated").getAsLong();
            if (lastUpdated != AutoFlipper.lastUpdates[0]) {
                AutoFlipper.lastUpdates[0] = lastUpdated;
                this.getFlips(auctionPage);
            }
            for (i = 1; i < pageAmount; ++i) {
                page = Utils.getJson("https://api.hypixel.net/skyblock/auctions?page=" + i).getAsJsonObject();
                update = page.get("lastUpdated").getAsLong();
                if (update != AutoFlipper.lastUpdates[i]) {
                    AutoFlipper.lastUpdates[i] = update;
                    this.getFlips(auctionPage);
                }
            }
        })).start();
    }
    
    public static void loadIds() {
        final List<String> containsList = new ArrayList<String>(Arrays.asList("_SACK", "_TUXEDO_BOOTS", "_TUXEDO_LEGGINGS"));
        final List<String> startsWithList = new ArrayList<String>(Arrays.asList("WAND_OF", "SALMON_", "GHOST_BOOTS", "PET_ITEM", "FARM_ARMOR", "PURE_MITHRIL", "STARRED_", "PERFECT_", "BOUNCY_", "BEASTMASTER_CREST", "SKELETON_LORD_"));
        final Set<String> equalsList = new HashSet<String>(Arrays.asList("MITHRIL_COAT", "STEEL_CHESTPLATE", "BOSS_SPIRIT_BOW", "END_LEGGINGS", "END_BOOTS", "END_HELMET", "END_CHESTPLATE"));
    Label_0178:
        for (final JsonElement jsons : Utils.getJson("https://api.hypixel.net/resources/skyblock/items").getAsJsonObject().get("items").getAsJsonArray()) {
            final JsonObject item = jsons.getAsJsonObject();
            if (item.has("name") && item.has("id") && item.has("category") && !AutoFlipper.filter.contains(item.get("category").getAsString())) {
                final String id = item.get("id").getAsString();
                if (equalsList.contains(id)) {
                    continue;
                }
                for (final String s : containsList) {
                    if (id.contains(s)) {
                        continue Label_0178;
                    }
                }
                for (final String s : startsWithList) {
                    if (id.startsWith(s)) {
                        continue Label_0178;
                    }
                }
                AutoFlipper.itemIds.put(item.get("name").getAsString(), item.get("id").getAsString());
            }
        }
    }
    
    private int getDemand(final String id) {
        return -1;
    }
    
    private double getAvgPrice(final String id) {
        try {
            if (this.neuResponse.has(id)) {
                return this.neuResponse.get(id).getAsDouble();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -1.0;
    }
    
    private void getFlips(final JsonObject auctionPage) {
        for (final JsonElement item : auctionPage.get("auctions").getAsJsonArray()) {
            final JsonObject o = item.getAsJsonObject();
            final String uuid = o.get("uuid").getAsString();
            if (!AutoFlipper.checked.contains(uuid) && o.has("bin") && o.get("bin").getAsBoolean() && !o.get("claimed").getAsBoolean()) {
                final String name = o.get("item_name").getAsString();
                for (final Map.Entry<String, String> items : AutoFlipper.itemIds.entrySet()) {
                    if (name.contains(items.getKey())) {
                        final double startingBid = o.get("starting_bid").getAsDouble();
                        final double avgPrice = this.getAvgPrice(items.getValue());
                        if (startingBid < avgPrice) {
                            final double profit = avgPrice * 0.9800000190734863 - startingBid;
                            if (profit >= PizzaClient.config.autoFlipperLowestPrice * 1000000L) {
                                AutoFlipper.checked.add(uuid);
                                final int demand = this.getDemand(items.getValue());
                                Utils.playOrbSound();
                                PizzaClient.mc.field_71439_g.func_145747_a(new ChatComponentText("PizzaClient > " + EnumChatFormatting.YELLOW + items.getValue() + EnumChatFormatting.GREEN + " +$" + Utils.formatValue((double)MathUtil.floor_long(profit)) + "\n" + EnumChatFormatting.GOLD + "Sales: " + EnumChatFormatting.GREEN + ((demand == -1) ? "unknown\n" : (demand + "/day\n")) + EnumChatFormatting.GOLD + "Avg Price: " + EnumChatFormatting.GREEN + Utils.formatValue(avgPrice) + "\n" + EnumChatFormatting.GOLD + "Price: " + EnumChatFormatting.GREEN + Utils.formatValue((double)(long)startingBid) + "\n").func_150255_a(new ChatStyle().func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/viewauction " + uuid))));
                                if (PizzaClient.mc.field_71462_r == null) {
                                    PizzaClient.mc.field_71439_g.func_71165_d("/viewauction " + uuid);
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldChange(final WorldChangeEvent event) {
        AutoFlipper.checked.clear();
    }
    
    static {
        lastUpdates = new long[100];
        filter = new HashSet<String>(Arrays.asList("TRAVEL_SCROLL", "COSMETIC", "DUNGEON_PASS", "ARROW_POISON", "PET_ITEM"));
        checked = new HashSet<String>();
        itemIds = new HashMap<String, String>();
    }
}
