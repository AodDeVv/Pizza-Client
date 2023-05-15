// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.skills;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.ChestBackgroundDrawnEvent;

public class FunnyEnchanting
{
    private long lastInteractTime;
    private boolean getNextChronomatronClick;
    private int[] pattern;
    private int clickCount;
    private int nextSlot;
    
    @SubscribeEvent
    public void onRenderGui(final ChestBackgroundDrawnEvent event) {
        if (!PizzaClient.config.funnyEnchanting || PizzaClient.location != Locations.PRIVATEISLAND || event.chest.field_75151_b.get(49).func_75211_c() == null) {
            return;
        }
        final List<Slot> invSlots = (List<Slot>)event.chest.field_75151_b;
        if (event.displayName.startsWith("Ultrasequencer (")) {
            if (invSlots.get(49).func_75211_c().func_77973_b() == Items.field_151113_aN) {
                if (this.pattern[this.clickCount] != 0 && System.currentTimeMillis() - this.lastInteractTime >= PizzaClient.config.funnyEnchantingDelay) {
                    PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, this.pattern[this.clickCount], 2, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                    this.lastInteractTime = System.currentTimeMillis();
                    this.pattern[this.clickCount] = 0;
                    ++this.clickCount;
                }
            }
            else if (invSlots.get(49).func_75211_c().func_77973_b() == Item.func_150898_a(Blocks.field_150426_aN)) {
                for (int i = 9; i <= 44; ++i) {
                    if (invSlots.get(i).func_75211_c() != null) {
                        if (this.pattern[invSlots.get(i).func_75211_c().field_77994_a - 1] == 0 && !invSlots.get(i).func_75211_c().func_82833_r().startsWith(" ")) {
                            this.pattern[invSlots.get(i).func_75211_c().field_77994_a - 1] = i;
                        }
                    }
                }
                if (PizzaClient.config.funnyEnchantingCloseChest && this.pattern[9] != 0) {
                    PizzaClient.mc.field_71439_g.func_71053_j();
                    return;
                }
                this.clickCount = 0;
            }
        }
        else if (event.displayName.startsWith("Chronomatron (")) {
            if (PizzaClient.config.funnyEnchantingCloseChest && invSlots.get(4).func_75211_c().field_77994_a >= 13) {
                PizzaClient.mc.field_71439_g.func_71053_j();
                return;
            }
            if (invSlots.get(49).func_75211_c().func_77973_b() == Items.field_151113_aN) {
                if (this.getNextChronomatronClick) {
                    for (int i = 11; i <= 33; ++i) {
                        if (invSlots.get(i).func_75211_c() != null) {
                            if (invSlots.get(i).func_75211_c().func_77973_b() == Item.func_150898_a(Blocks.field_150406_ce) && this.pattern[this.nextSlot] == 0) {
                                this.getNextChronomatronClick = false;
                                this.pattern[this.nextSlot] = i;
                                ++this.nextSlot;
                                break;
                            }
                        }
                    }
                }
                if (this.pattern[this.clickCount] != 0 && System.currentTimeMillis() - this.lastInteractTime >= PizzaClient.config.funnyEnchantingDelay) {
                    PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, this.pattern[this.clickCount], 2, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                    this.lastInteractTime = System.currentTimeMillis();
                    ++this.clickCount;
                }
            }
            else if (invSlots.get(49).func_75211_c().func_77973_b() == Item.func_150898_a(Blocks.field_150426_aN)) {
                this.clickCount = 0;
                this.getNextChronomatronClick = true;
            }
        }
    }
    
    @SubscribeEvent
    public void onOpenGui(final GuiOpenEvent event) {
        this.clickCount = 0;
        this.pattern = new int[66];
        this.nextSlot = 0;
    }
}
