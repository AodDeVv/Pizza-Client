// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import java.util.ArrayList;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemStack;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.init.Items;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.google.gson.JsonObject;
import java.util.Iterator;
import com.google.gson.JsonArray;
import qolskyblockmod.pizzaclient.util.VecUtil;
import com.google.gson.JsonElement;
import qolskyblockmod.pizzaclient.util.api.SkyblockAPI;
import qolskyblockmod.pizzaclient.util.handler.ThreadHandler;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import net.minecraft.util.BlockPos;
import java.util.List;

public class BurrowAura
{
    public static final List<BlockPos> burrowPositions;
    private long lastCheckTime;
    private static long lastInteractTime;
    public static BlockPos lastHitPos;
    public static boolean clickAgain;
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (!PizzaClient.config.burrowAura || PizzaClient.mc.field_71462_r != null || PizzaClient.location != Locations.HUB || !this.spadeInInv()) {
            return;
        }
        if (BurrowAura.burrowPositions.size() == 0 || System.currentTimeMillis() - this.lastCheckTime >= 60000L) {
            if (ThreadHandler.isDead()) {
                this.lastCheckTime = System.currentTimeMillis();
                JsonArray burrows;
                final Iterator<JsonElement> iterator;
                JsonElement element;
                JsonObject obj;
                ThreadHandler.enqueue(() -> {
                    try {
                        burrows = SkyblockAPI.getLatestProfileSkyblockAPI().getBurrows();
                        BurrowAura.burrowPositions.clear();
                        burrows.iterator();
                        while (iterator.hasNext()) {
                            element = iterator.next();
                            obj = element.getAsJsonObject();
                            BurrowAura.burrowPositions.add(new BlockPos(obj.get("x").getAsInt(), obj.get("y").getAsInt(), obj.get("z").getAsInt()));
                        }
                        if (BurrowAura.lastHitPos != null && !BurrowAura.burrowPositions.contains(BurrowAura.lastHitPos)) {
                            BurrowAura.lastHitPos = null;
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            return;
        }
        if (BurrowAura.clickAgain) {
            clickAndRemove();
        }
        if (System.currentTimeMillis() - BurrowAura.lastInteractTime >= 600L) {
            for (final BlockPos pos : BurrowAura.burrowPositions) {
                if (!pos.equals((Object)BurrowAura.lastHitPos) && VecUtil.canReachBlock(pos, PizzaClient.config.burrowAuraReach)) {
                    BurrowAura.lastHitPos = pos;
                    clickBurrow();
                    BurrowAura.lastInteractTime = System.currentTimeMillis();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityDeath(final LivingDeathEvent event) {
        if (BurrowAura.lastHitPos != null && !(event.entity instanceof EntityArmorStand) && event.entity.func_70068_e((Entity)PizzaClient.mc.field_71439_g) < 50.0) {
            if (event.entity instanceof EntityOcelot) {
                for (final Entity entity : PizzaClient.mc.field_71441_e.field_72996_f) {
                    if (entity instanceof EntityOcelot && ((EntityOcelot)event.entity).func_110138_aP() > 0.0f && event.entity.func_70068_e((Entity)PizzaClient.mc.field_71439_g) < 100.0) {
                        return;
                    }
                }
            }
            BurrowAura.clickAgain = true;
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldChangeEvent event) {
        BurrowAura.burrowPositions.clear();
    }
    
    private static void swapToSpade() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack stack = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
            if (stack != null && stack.func_77973_b() == Items.field_151038_n) {
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
                PlayerUtil.forceUpdateController();
                break;
            }
        }
    }
    
    public static void clickBurrow() {
        if (!holdingSpade()) {
            final int currentItem = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
            swapToSpade();
            if (PizzaClient.mc.field_71442_b.func_180511_b(BurrowAura.lastHitPos, EnumFacing.UP)) {
                PizzaClient.mc.field_71439_g.func_71038_i();
            }
            PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = currentItem;
            return;
        }
        if (PizzaClient.mc.field_71442_b.func_180511_b(BurrowAura.lastHitPos, EnumFacing.UP)) {
            PizzaClient.mc.field_71439_g.func_71038_i();
        }
    }
    
    private static boolean holdingSpade() {
        final ItemStack stack = PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g();
        return stack != null && stack.func_77973_b() == Items.field_151038_n;
    }
    
    private boolean spadeInInv() {
        for (int i = 0; i < 8; ++i) {
            final ItemStack stack = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
            if (stack != null && stack.func_77973_b() == Items.field_151038_n) {
                return true;
            }
        }
        return false;
    }
    
    public static void removeFromList() {
        BurrowAura.burrowPositions.remove(BurrowAura.lastHitPos);
        BurrowAura.lastHitPos = null;
    }
    
    public static void clickAndRemove() {
        if (BurrowAura.lastHitPos == null) {
            BurrowAura.clickAgain = false;
            return;
        }
        if (System.currentTimeMillis() - BurrowAura.lastInteractTime >= 1100L && VecUtil.canReachBlock(BurrowAura.lastHitPos, PizzaClient.config.burrowAuraReach)) {
            if (!holdingSpade()) {
                final int currentItem = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
                swapToSpade();
                if (PizzaClient.mc.field_71442_b.func_180511_b(BurrowAura.lastHitPos, EnumFacing.UP)) {
                    PizzaClient.mc.field_71439_g.func_71038_i();
                }
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = currentItem;
            }
            else if (PizzaClient.mc.field_71442_b.func_180511_b(BurrowAura.lastHitPos, EnumFacing.UP)) {
                PizzaClient.mc.field_71439_g.func_71038_i();
            }
            BurrowAura.burrowPositions.remove(BurrowAura.lastHitPos);
            BurrowAura.lastHitPos = null;
            BurrowAura.clickAgain = false;
            BurrowAura.lastInteractTime = System.currentTimeMillis();
        }
    }
    
    static {
        burrowPositions = new ArrayList<BlockPos>();
    }
}
