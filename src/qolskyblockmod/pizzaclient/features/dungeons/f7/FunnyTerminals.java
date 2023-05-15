// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.dungeons.f7;

import java.util.HashMap;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.util.StringUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.core.events.ChestBackgroundDrawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import net.minecraft.item.EnumDyeColor;
import java.util.Map;

public class FunnyTerminals
{
    private static int lastSlot;
    private static int mazeId;
    private static long lastInteractTime;
    private static int[] chest;
    protected static Terminals terminals;
    private static final Map<Integer, Long> terminalFix;
    public static final EnumDyeColor[] LEFT_COLORS;
    public static EnumDyeColor bestColor;
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (FunnyTerminals.terminals != Terminals.NULL && PizzaClient.mc.field_71462_r == null) {
            FunnyTerminals.terminals = Terminals.NULL;
        }
    }
    
    @SubscribeEvent
    public void onRenderGui(final ChestBackgroundDrawnEvent event) {
        if (!PizzaClient.config.funnyTerminals || PizzaClient.location != Locations.DUNGEON) {
            return;
        }
        final List<Slot> invSlots = event.slots;
        final String displayName2;
        final String displayName = displayName2 = event.displayName;
        Terminals currentTerminal = null;
        switch (displayName2) {
            case "Navigate the maze!": {
                currentTerminal = Terminals.MAZE;
                break;
            }
            case "Correct all the panes!": {
                currentTerminal = Terminals.PANELS;
                break;
            }
            case "Click in order!": {
                currentTerminal = Terminals.NUMBERS;
                break;
            }
            case "Change all to same color!": {
                currentTerminal = Terminals.SAME_COLOR;
                break;
            }
            case "Click the button on time!": {
                currentTerminal = Terminals.TIMING;
                break;
            }
            default: {
                if (displayName.startsWith("What starts with:")) {
                    currentTerminal = Terminals.LETTER;
                    break;
                }
                if (displayName.startsWith("Select all the")) {
                    currentTerminal = Terminals.COLOR;
                    break;
                }
                currentTerminal = Terminals.NULL;
                break;
            }
        }
        if (currentTerminal != FunnyTerminals.terminals) {
            this.updateValues();
            FunnyTerminals.terminals = currentTerminal;
        }
        if (FunnyTerminals.mazeId < PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c) {
            FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
        }
        final Iterator<Map.Entry<Integer, Long>> iterator = FunnyTerminals.terminalFix.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Integer, Long> map = iterator.next();
            final int key = map.getKey();
            final long value = map.getValue();
            if (FunnyTerminals.terminals == Terminals.NUMBERS || FunnyTerminals.terminals == Terminals.PANELS) {
                if (invSlots.get(key).func_75211_c() == null) {
                    fixTerminals(value, invSlots);
                    break;
                }
                if (invSlots.get(key).func_75211_c().func_77952_i() == 14) {
                    fixTerminals(value, invSlots);
                    break;
                }
                iterator.remove();
            }
            else if (FunnyTerminals.terminals == Terminals.MAZE) {
                if (invSlots.get(key).func_75211_c() == null) {
                    fixTerminals(value, invSlots);
                    break;
                }
                if (invSlots.get(key).func_75211_c().func_77952_i() == 0) {
                    fixTerminals(value, invSlots);
                    break;
                }
                iterator.remove();
            }
            else if (FunnyTerminals.terminals == Terminals.COLOR || FunnyTerminals.terminals == Terminals.LETTER) {
                if (invSlots.get(key).func_75211_c() == null) {
                    fixTerminals(value, invSlots);
                    break;
                }
                if (!invSlots.get(key).func_75211_c().func_77948_v()) {
                    fixTerminals(value, invSlots);
                    break;
                }
                iterator.remove();
            }
            else {
                if (FunnyTerminals.terminals == Terminals.SAME_COLOR && invSlots.get(key).func_75211_c() == null) {
                    fixTerminals(value, invSlots);
                    break;
                }
                continue;
            }
        }
        switch (FunnyTerminals.terminals) {
            case MAZE: {
                if (System.currentTimeMillis() - FunnyTerminals.lastInteractTime >= sleepTime()) {
                    if (FunnyTerminals.lastSlot == -1) {
                        for (int i = 0; i <= 53; ++i) {
                            final ItemStack item = invSlots.get(i).func_75211_c();
                            if (item != null) {
                                final int itemDamage = item.func_77952_i();
                                if (itemDamage == 0) {
                                    FunnyTerminals.chest[i] = 1;
                                }
                                else if (itemDamage == 5) {
                                    FunnyTerminals.lastSlot = i;
                                    FunnyTerminals.chest[i] = 2;
                                }
                            }
                            else {
                                FunnyTerminals.chest[i] = 1;
                            }
                        }
                    }
                    final int right = FunnyTerminals.lastSlot + 1;
                    final int bottom = FunnyTerminals.lastSlot + 9;
                    final int left = FunnyTerminals.lastSlot - 1;
                    final int top = FunnyTerminals.lastSlot - 9;
                    int clickSlot = -1;
                    if (right % 9 != 0 && right <= 53 && FunnyTerminals.chest[right] == 1) {
                        clickSlot = right;
                    }
                    else if (bottom <= 53 && FunnyTerminals.chest[bottom] == 1) {
                        clickSlot = bottom;
                    }
                    else if (left % 9 != 8 && left >= 0 && left <= 53 && FunnyTerminals.chest[left] == 1) {
                        clickSlot = left;
                    }
                    else if (top >= 0 && FunnyTerminals.chest[top] == 1) {
                        clickSlot = top;
                    }
                    if (clickSlot != -1) {
                        FunnyTerminals.chest[clickSlot] = 0;
                        FunnyTerminals.lastSlot = clickSlot;
                        PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, clickSlot, PizzaClient.config.terminalClickMode, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                        FunnyTerminals.terminalFix.put(clickSlot, System.currentTimeMillis());
                        ++FunnyTerminals.mazeId;
                        FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                    }
                    break;
                }
                break;
            }
            case NUMBERS: {
                if (FunnyTerminals.chest[13] == 0) {
                    for (int i = 10; i < 26; ++i) {
                        if (i != 17 && i != 18) {
                            final ItemStack item = invSlots.get(i).func_75211_c();
                            if (item != null) {
                                FunnyTerminals.chest[item.field_77994_a - 1] = i;
                            }
                        }
                    }
                }
                if (System.currentTimeMillis() - FunnyTerminals.lastInteractTime >= sleepTime()) {
                    for (int i = 0; i < 14; ++i) {
                        if (invSlots.get(FunnyTerminals.chest[i]).func_75211_c() != null) {
                            if (i > FunnyTerminals.lastSlot && invSlots.get(FunnyTerminals.chest[i]).func_75211_c().func_77952_i() == 14) {
                                PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, FunnyTerminals.chest[i], PizzaClient.config.terminalClickMode, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                FunnyTerminals.terminalFix.put(FunnyTerminals.chest[i], System.currentTimeMillis());
                                ++FunnyTerminals.mazeId;
                                FunnyTerminals.lastSlot = i;
                                FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                                break;
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case PANELS: {
                if (System.currentTimeMillis() - FunnyTerminals.lastInteractTime >= sleepTime()) {
                    for (int i = 11; i < 34; ++i) {
                        if (invSlots.get(i).getSlotIndex() > FunnyTerminals.lastSlot) {
                            final ItemStack item = invSlots.get(i).func_75211_c();
                            if (item != null && item.func_77952_i() == 14) {
                                PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, i, PizzaClient.config.terminalClickMode, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                FunnyTerminals.terminalFix.put(i, System.currentTimeMillis());
                                ++FunnyTerminals.mazeId;
                                FunnyTerminals.lastSlot = i;
                                FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                                return;
                            }
                        }
                    }
                    for (int i = 11; i < 34; ++i) {
                        final ItemStack item = invSlots.get(i).func_75211_c();
                        if (item != null && item.func_77952_i() == 14) {
                            PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, i, PizzaClient.config.terminalClickMode, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                            FunnyTerminals.terminalFix.put(i, System.currentTimeMillis());
                            ++FunnyTerminals.mazeId;
                            FunnyTerminals.lastSlot = i;
                            FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                            return;
                        }
                    }
                    break;
                }
                break;
            }
            case LETTER: {
                if (System.currentTimeMillis() - FunnyTerminals.lastInteractTime >= sleepTime()) {
                    final char letter = displayName.charAt(displayName.indexOf("'") + 1);
                    for (int j = 10; j <= 43; ++j) {
                        final Slot slot = invSlots.get(j);
                        if (slot.field_75222_d > FunnyTerminals.lastSlot && slot.getSlotIndex() > FunnyTerminals.lastSlot) {
                            final ItemStack item2 = slot.func_75211_c();
                            if (item2 != null && !item2.func_77948_v() && StringUtils.func_76338_a(item2.func_82833_r()).charAt(0) == letter) {
                                PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, j, PizzaClient.config.terminalClickMode, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                FunnyTerminals.terminalFix.put(j, System.currentTimeMillis());
                                ++FunnyTerminals.mazeId;
                                FunnyTerminals.lastSlot = slot.getSlotIndex();
                                FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                                break;
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case COLOR: {
                if (System.currentTimeMillis() - FunnyTerminals.lastInteractTime >= sleepTime()) {
                    final String colour = displayName.split(" ")[3];
                    for (int j = 10; j <= 43; ++j) {
                        final Slot slot = invSlots.get(j);
                        if (slot.field_75222_d > FunnyTerminals.lastSlot && slot.getSlotIndex() > FunnyTerminals.lastSlot) {
                            final ItemStack item2 = slot.func_75211_c();
                            if (item2 != null && !item2.func_77948_v()) {
                                final String itemName = StringUtils.func_76338_a(item2.func_82833_r()).toUpperCase();
                                if (item2.func_82833_r().toUpperCase().contains(colour) || (colour.equals("SILVER") && itemName.contains("LIGHT GRAY")) || (colour.equals("WHITE") && itemName.equals("WOOL")) || (colour.equals("BLACK") && itemName.contains("INK")) || (colour.equals("BROWN") && itemName.contains("COCOA")) || (colour.equals("BLUE") && itemName.contains("LAPIS")) || (colour.equals("WHITE") && itemName.contains("BONE"))) {
                                    FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                                    PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, j, PizzaClient.config.terminalClickMode, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                    FunnyTerminals.terminalFix.put(j, System.currentTimeMillis());
                                    FunnyTerminals.lastSlot = j;
                                    ++FunnyTerminals.mazeId;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case TIMING: {
                if (System.currentTimeMillis() - FunnyTerminals.lastInteractTime >= sleepTime()) {
                    int greenSlot = -1;
                    int purpleSlot = -1;
                    int clickSlot2 = 0;
                    for (int k = 1; k < 51; ++k) {
                        final ItemStack stack = invSlots.get(k).func_75211_c();
                        if (stack != null) {
                            final EnumDyeColor color = EnumDyeColor.func_176764_b(stack.func_77952_i());
                            switch (color) {
                                case PURPLE: {
                                    if (purpleSlot == -1) {
                                        purpleSlot = k % 9;
                                        break;
                                    }
                                    break;
                                }
                                case LIME: {
                                    final Item item3 = stack.func_77973_b();
                                    if (item3 == Item.func_150898_a((Block)Blocks.field_150397_co)) {
                                        if (greenSlot == -1) {
                                            greenSlot = k % 9;
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        if (item3 == Item.func_150898_a(Blocks.field_150406_ce)) {
                                            clickSlot2 = k;
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (purpleSlot != -1 && clickSlot2 != 0 && greenSlot == purpleSlot) {
                        PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, clickSlot2, PizzaClient.config.terminalClickMode, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                        FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                    }
                    break;
                }
                break;
            }
            case SAME_COLOR: {
                if (System.currentTimeMillis() - FunnyTerminals.lastInteractTime >= sleepTime()) {
                    int count = 0;
                    final Slot[] itemStacks = new Slot[9];
                    for (int l = 12; l < 34; ++l) {
                        final Slot slot2 = invSlots.get(l);
                        final ItemStack stack = slot2.func_75211_c();
                        if (stack != null && stack.func_77952_i() != 15 && stack.func_77952_i() != 0) {
                            itemStacks[count] = slot2;
                            ++count;
                        }
                    }
                    if (FunnyTerminals.bestColor == null) {
                        FunnyTerminals.bestColor = this.getBestColor(itemStacks);
                        if (FunnyTerminals.bestColor == null) {
                            return;
                        }
                    }
                    for (final Slot stack2 : itemStacks) {
                        if (stack2 != null && stack2.func_75211_c() != null && FunnyTerminals.lastSlot != stack2.getSlotIndex()) {
                            final EnumDyeColor color2 = EnumDyeColor.func_176764_b(stack2.func_75211_c().func_77952_i());
                            if (color2 != FunnyTerminals.bestColor) {
                                final int clicks = this.getClicks(color2, FunnyTerminals.bestColor);
                                if (clicks != 0) {
                                    if (clicks < 0) {
                                        PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, stack2.getSlotIndex(), 1, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                    }
                                    else {
                                        PizzaClient.mc.field_71442_b.func_78753_a(FunnyTerminals.mazeId, stack2.getSlotIndex(), 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                    }
                                    FunnyTerminals.lastSlot = stack2.getSlotIndex();
                                    ++FunnyTerminals.mazeId;
                                    FunnyTerminals.lastInteractTime = System.currentTimeMillis();
                                    FunnyTerminals.terminalFix.put(stack2.getSlotIndex(), System.currentTimeMillis());
                                    return;
                                }
                            }
                        }
                    }
                    FunnyTerminals.lastSlot = 0;
                    break;
                }
                break;
            }
        }
    }
    
    private EnumDyeColor getBestColor(final Slot[] itemStacks) {
        EnumDyeColor bestColor = null;
        int clickCount = 9999;
        for (int i = 0; i < 5; ++i) {
            final EnumDyeColor color = FunnyTerminals.LEFT_COLORS[i];
            int clicks = 0;
            for (final Slot stack : itemStacks) {
                if (stack != null && stack.func_75211_c() != null) {
                    int click = this.getClicks(EnumDyeColor.func_176764_b(stack.func_75211_c().func_77952_i()), i);
                    if (click < 0) {
                        click = -click;
                    }
                    clicks += click;
                }
            }
            if (clicks != 0 && clicks < clickCount) {
                clickCount = clicks;
                bestColor = color;
            }
        }
        return bestColor;
    }
    
    private int getClicks(final EnumDyeColor itemStackColor, final EnumDyeColor goalColor) {
        if (itemStackColor == goalColor) {
            return 0;
        }
        int itemStackClicks = 0;
        int goalColorClicks = 0;
        for (int i = 0; i < FunnyTerminals.LEFT_COLORS.length; ++i) {
            final EnumDyeColor color = FunnyTerminals.LEFT_COLORS[i];
            if (color == itemStackColor) {
                itemStackClicks = i;
            }
            else if (color == goalColor) {
                goalColorClicks = i;
            }
        }
        final int clicks = goalColorClicks - itemStackClicks;
        if (clicks < 0) {
            final int otherClicks = clicks + 5;
            if (otherClicks < -clicks) {
                return otherClicks;
            }
        }
        return clicks;
    }
    
    private int getClicks(final EnumDyeColor itemStackColor, final int goalColor) {
        if (itemStackColor == FunnyTerminals.LEFT_COLORS[goalColor]) {
            return 0;
        }
        int itemStackClicks = 0;
        final int goalColorClicks = goalColor;
        for (int i = 0; i < FunnyTerminals.LEFT_COLORS.length; ++i) {
            if (FunnyTerminals.LEFT_COLORS[i] == itemStackColor) {
                itemStackClicks = i;
            }
        }
        final int clicks = goalColorClicks - itemStackClicks;
        if (clicks < 0) {
            final int otherClicks = clicks + 5;
            if (otherClicks < -clicks) {
                return otherClicks;
            }
        }
        return clicks;
    }
    
    private static void fixTerminals(final long value, final List<Slot> invSlots) {
        if (System.currentTimeMillis() - value >= PizzaClient.config.terminalFixTime) {
            FunnyTerminals.lastInteractTime = 0L;
            switch (FunnyTerminals.terminals) {
                case MAZE: {
                    FunnyTerminals.chest = new int[54];
                    for (int i = 0; i < 54; ++i) {
                        final ItemStack item = invSlots.get(i).func_75211_c();
                        if (item != null) {
                            switch (item.func_77952_i()) {
                                case 0: {
                                    FunnyTerminals.chest[i] = 1;
                                    break;
                                }
                                case 5: {
                                    FunnyTerminals.chest[i] = 2;
                                    break;
                                }
                                case 14: {
                                    FunnyTerminals.lastSlot = i;
                                    break;
                                }
                                default: {
                                    FunnyTerminals.chest[i] = 0;
                                    break;
                                }
                            }
                        }
                        else {
                            FunnyTerminals.chest[i] = 1;
                        }
                    }
                    while (true) {
                        final int right = FunnyTerminals.lastSlot + 1;
                        final int bottom = FunnyTerminals.lastSlot + 9;
                        final int left = FunnyTerminals.lastSlot - 1;
                        final int top = FunnyTerminals.lastSlot - 9;
                        if (right % 9 != 0 && right <= 53 && FunnyTerminals.chest[right] != 0) {
                            if (fixMaze(right)) {
                                break;
                            }
                            continue;
                        }
                        else if (bottom <= 53 && FunnyTerminals.chest[bottom] != 0) {
                            if (fixMaze(bottom)) {
                                break;
                            }
                            continue;
                        }
                        else if (left % 9 != 8 && left >= 0 && left <= 53 && FunnyTerminals.chest[left] != 0) {
                            if (fixMaze(left)) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (top < 0 || FunnyTerminals.chest[top] == 0) {
                                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "Could not fix maze! lastSlot: " + FunnyTerminals.lastSlot));
                                return;
                            }
                            if (fixMaze(top)) {
                                break;
                            }
                            continue;
                        }
                    }
                    FunnyTerminals.chest = new int[54];
                    for (int i = 0; i < 54; ++i) {
                        final ItemStack item = invSlots.get(i).func_75211_c();
                        if (item != null) {
                            final int itemDamage = item.func_77952_i();
                            if (itemDamage == 0) {
                                FunnyTerminals.chest[i] = 1;
                            }
                            else if (itemDamage == 5) {
                                FunnyTerminals.chest[i] = 2;
                            }
                        }
                    }
                    FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
                    FunnyTerminals.terminalFix.clear();
                    break;
                }
                case PANELS: {
                    int slotId = 0;
                    for (int j = 11; j <= 33; ++j) {
                        final ItemStack item2 = invSlots.get(j).func_75211_c();
                        if (item2 != null) {
                            if (item2.func_77952_i() == 14) {
                                slotId = j - 1;
                                break;
                            }
                        }
                        else {
                            slotId = j - 1;
                        }
                    }
                    FunnyTerminals.lastSlot = slotId;
                    FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
                    FunnyTerminals.terminalFix.clear();
                    break;
                }
                case NUMBERS: {
                    int highestStack = 0;
                    for (int k = 10; k < 26; ++k) {
                        if (k != 17 && k != 18) {
                            final ItemStack item3 = invSlots.get(k).func_75211_c();
                            if (item3 != null && item3.func_77952_i() == 5 && item3.field_77994_a > highestStack) {
                                highestStack = item3.field_77994_a;
                            }
                        }
                    }
                    FunnyTerminals.lastSlot = highestStack - 1;
                    FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
                    FunnyTerminals.terminalFix.clear();
                    break;
                }
                case LETTER: {
                    FunnyTerminals.lastSlot = -1;
                    FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
                    FunnyTerminals.terminalFix.clear();
                    break;
                }
                case COLOR: {
                    FunnyTerminals.lastSlot = -1;
                    FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
                    FunnyTerminals.terminalFix.clear();
                    break;
                }
                case SAME_COLOR: {
                    FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
                    FunnyTerminals.terminalFix.clear();
                    break;
                }
                case TIMING: {
                    FunnyTerminals.mazeId = PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c;
                    FunnyTerminals.lastSlot = -1;
                    FunnyTerminals.terminalFix.clear();
                    break;
                }
            }
        }
    }
    
    private static int sleepTime() {
        return PizzaClient.config.terminalSleepAmount;
    }
    
    private static boolean fixMaze(final int clickSlot) {
        if (FunnyTerminals.chest[clickSlot] == 1) {
            FunnyTerminals.chest[clickSlot] = 0;
            FunnyTerminals.lastSlot = clickSlot;
        }
        else if (FunnyTerminals.chest[clickSlot] == 2) {
            FunnyTerminals.lastSlot = clickSlot;
            return true;
        }
        return false;
    }
    
    private void updateValues() {
        FunnyTerminals.bestColor = null;
        FunnyTerminals.lastSlot = -1;
        FunnyTerminals.mazeId = -1;
        FunnyTerminals.chest = new int[54];
        FunnyTerminals.terminalFix.clear();
        FunnyTerminals.lastInteractTime = 0L;
    }
    
    static {
        FunnyTerminals.chest = new int[54];
        FunnyTerminals.terminals = Terminals.NULL;
        terminalFix = new HashMap<Integer, Long>();
        LEFT_COLORS = new EnumDyeColor[] { EnumDyeColor.ORANGE, EnumDyeColor.YELLOW, EnumDyeColor.GREEN, EnumDyeColor.BLUE, EnumDyeColor.RED };
    }
    
    protected enum Terminals
    {
        MAZE, 
        NUMBERS, 
        COLOR, 
        LETTER, 
        PANELS, 
        TIMING, 
        SAME_COLOR, 
        NULL;
    }
}
