// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util;

import java.util.Iterator;
import net.minecraft.util.StringUtils;
import net.minecraft.nbt.NBTTagList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;

public class ItemUtil
{
    public static String getDisplayName(final ItemStack item) {
        String s = item.func_77973_b().func_77653_i(item);
        if (item.func_77978_p() != null && item.func_77978_p().func_150297_b("display", 10)) {
            final NBTTagCompound nbttagcompound = item.func_77978_p().func_74775_l("display");
            if (nbttagcompound.func_150297_b("Name", 8)) {
                s = nbttagcompound.func_74779_i("Name");
            }
        }
        return s;
    }
    
    public static String getSkyBlockItemID(final ItemStack item) {
        if (item == null) {
            return "";
        }
        final NBTTagCompound extraAttributes = getExtraAttributes(item);
        if (extraAttributes == null) {
            return "";
        }
        if (!extraAttributes.func_150297_b("id", 8)) {
            return "";
        }
        return extraAttributes.func_74779_i("id");
    }
    
    public static NBTTagCompound getExtraAttributes(final ItemStack item) {
        if (!item.func_77942_o()) {
            return new NBTTagCompound();
        }
        return item.func_179543_a("ExtraAttributes", false);
    }
    
    public static String getSkyBlockItemID(final NBTTagCompound extraAttributes) {
        if (extraAttributes != null) {
            final String itemId = extraAttributes.func_74779_i("id");
            if (!itemId.equals("")) {
                return itemId;
            }
        }
        return "";
    }
    
    public static List<String> getItemLore(final ItemStack itemStack) {
        if (itemStack.func_77942_o() && itemStack.func_77978_p().func_150297_b("display", 10)) {
            final NBTTagCompound display = itemStack.func_77978_p().func_74775_l("display");
            if (display.func_150297_b("Lore", 9)) {
                final NBTTagList lore = display.func_150295_c("Lore", 8);
                final List<String> loreAsList = new ArrayList<String>();
                for (int lineNumber = 0; lineNumber < lore.func_74745_c(); ++lineNumber) {
                    loreAsList.add(lore.func_150307_f(lineNumber));
                }
                return Collections.unmodifiableList((List<? extends String>)loreAsList);
            }
        }
        return Collections.emptyList();
    }
    
    public static boolean hasRightClickAbility(final ItemStack itemStack) {
        for (final String line : getItemLore(itemStack)) {
            final String stripped = StringUtils.func_76338_a(line);
            if (stripped.startsWith("Ability:") && stripped.endsWith("RIGHT CLICK")) {
                return true;
            }
        }
        return false;
    }
}
