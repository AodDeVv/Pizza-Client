// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.ItemUtil;
import net.minecraft.init.Items;
import qolskyblockmod.pizzaclient.util.handler.ThreadHandler;
import net.minecraft.inventory.Slot;
import java.util.List;
import java.util.Map;

public class AutoBookCombine
{
    private static final Map<String, Integer> books;
    
    public static void onOpenGui() {
        AutoBookCombine.books.clear();
    }
    
    public static void combineBooks(final List<Slot> invSlots) {
        if (ThreadHandler.isAlive()) {
            return;
        }
        for (int i = 54; i <= 89; ++i) {
            final Slot slot = invSlots.get(i);
            if (slot.func_75211_c() != null && slot.func_75211_c().func_77973_b() == Items.field_151134_bR) {
                final NBTTagCompound extraAttr = ItemUtil.getExtraAttributes(slot.func_75211_c());
                final NBTTagCompound enchantments = extraAttr.func_74775_l("enchantments");
                if (enchantments.func_150296_c().size() == 1) {
                    if (AutoBookCombine.books.containsKey(enchantments.toString()) && AutoBookCombine.books.get(enchantments.toString()) != i) {
                        if (invSlots.get(AutoBookCombine.books.get(enchantments.toString())).func_75211_c() != null) {
                            final int click = i;
                            final NBTTagCompound nbtTagCompound;
                            final String pair;
                            final int n;
                            ThreadHandler.enqueue(() -> {
                                Utils.sleep(150 + PizzaClient.config.autoBookCombineDelay);
                                pair = nbtTagCompound.toString();
                                if (PizzaClient.mc.field_71462_r != null) {
                                    PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, (int)AutoBookCombine.books.get(pair), 0, 1, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                }
                                AutoBookCombine.books.remove(pair);
                                Utils.sleep(300 + PizzaClient.config.autoBookCombineDelay);
                                if (PizzaClient.mc.field_71462_r != null) {
                                    if (invSlots.get(n).func_75211_c() == null) {
                                        PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, fix(pair, invSlots), 0, 1, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                    }
                                    else {
                                        PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, n, 0, 1, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                    }
                                }
                                while (invSlots.get(13).func_75211_c().func_77973_b() != Items.field_151134_bR) {
                                    if (PizzaClient.mc.field_71462_r == null) {
                                        return;
                                    }
                                }
                                Utils.sleep(50);
                                if (PizzaClient.mc.field_71462_r != null) {
                                    PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                }
                                Utils.sleep(250 + PizzaClient.config.autoBookCombineDelay);
                                if (PizzaClient.mc.field_71462_r != null) {
                                    PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, 13, 0, 1, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                }
                                Utils.sleep(50);
                            });
                            return;
                        }
                        AutoBookCombine.books.remove(enchantments.toString());
                    }
                    else {
                        int value;
                        try {
                            value = Integer.parseInt(String.valueOf(enchantments.toString().charAt(enchantments.toString().indexOf(":") + 2)));
                        }
                        catch (Exception e) {
                            value = Integer.parseInt(String.valueOf(enchantments.toString().charAt(enchantments.toString().indexOf(":") + 1)));
                        }
                        if (enchantments.toString().contains("feather_falling") || enchantments.toString().contains("infinite_quiver")) {
                            if (value >= 10) {
                                continue;
                            }
                        }
                        else if (value >= 5) {
                            continue;
                        }
                        AutoBookCombine.books.put(enchantments.toString(), i);
                    }
                }
            }
        }
    }
    
    private static int fix(final String name, final List<Slot> invSlots) {
        for (int i = 54; i <= 89; ++i) {
            final Slot slot = invSlots.get(i);
            if (slot.func_75211_c() != null && slot.func_75211_c().func_77973_b() == Items.field_151134_bR) {
                final NBTTagCompound extraAttr = ItemUtil.getExtraAttributes(slot.func_75211_c());
                final NBTTagCompound enchantments = extraAttr.func_74775_l("enchantments");
                if (enchantments.func_150296_c().size() == 1 && enchantments.toString().equals(name)) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    public static void salvage(final List<Slot> invSlots) {
        if (ThreadHandler.isAlive()) {
            return;
        }
        for (int i = 54; i <= 89; ++i) {
            final Slot slot = invSlots.get(i);
            if (invSlots.get(i).func_75211_c() != null) {
                final NBTTagCompound extraAttr = ItemUtil.getExtraAttributes(slot.func_75211_c());
                final String sbId = ItemUtil.getSkyBlockItemID(slot.func_75211_c());
                if (!sbId.equals("ICE_SPRAY_WAND") && extraAttr != null && extraAttr.func_74764_b("baseStatBoostPercentage") && !extraAttr.func_74764_b("dungeon_item_level")) {
                    final int click = i;
                    final int n;
                    ThreadHandler.enqueue(() -> {
                        Utils.sleep(335 + PizzaClient.config.autoSalvageDelay);
                        PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, n, 0, 1, (EntityPlayer)PizzaClient.mc.field_71439_g);
                        while (invSlots.get(22).func_75211_c() == null) {
                            if (PizzaClient.mc.field_71462_r == null) {
                                return;
                            }
                        }
                        Utils.sleep(200 + PizzaClient.config.autoSalvageDelay);
                        PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, 31, 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                        return;
                    });
                }
            }
        }
    }
    
    static {
        books = new ConcurrentHashMap<String, Integer>();
    }
}
