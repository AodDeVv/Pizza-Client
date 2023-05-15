// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.ChestBackgroundDrawnEvent;

public class HarpSolver
{
    private static long lastInteractTime;
    public static boolean click;
    private static String harpTag;
    
    @SubscribeEvent
    public void onBackgroundDrawn(final ChestBackgroundDrawnEvent event) {
        if (PizzaClient.config.autoHarpSolver && event.displayName.startsWith("Harp -")) {
            if (!HarpSolver.click) {
                final StringBuilder currentTag = new StringBuilder();
                for (int i = 1; i <= 34; ++i) {
                    if (event.chestInv.func_70301_a(i) != null) {
                        currentTag.append(event.chestInv.func_70301_a(i).func_77973_b());
                    }
                }
                if (!currentTag.toString().equals(HarpSolver.harpTag)) {
                    HarpSolver.harpTag = currentTag.toString();
                    HarpSolver.lastInteractTime = 0L;
                    HarpSolver.click = true;
                }
            }
            if (HarpSolver.click) {
                if (HarpSolver.lastInteractTime == 0L) {
                    HarpSolver.lastInteractTime = System.currentTimeMillis();
                    return;
                }
                if (System.currentTimeMillis() - HarpSolver.lastInteractTime >= PizzaClient.config.harpSolverDelay) {
                    int woolPos = -1;
                    for (int i = 28; i <= 34; ++i) {
                        if (event.chestInv.func_70301_a(i) != null && event.chestInv.func_70301_a(i).func_77973_b() == Item.func_150898_a(Blocks.field_150325_L)) {
                            woolPos = i + 9;
                            break;
                        }
                    }
                    if (woolPos == -1) {
                        HarpSolver.lastInteractTime = 0L;
                        HarpSolver.click = false;
                        return;
                    }
                    PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, woolPos, 2, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                    HarpSolver.lastInteractTime = 0L;
                    HarpSolver.click = false;
                }
            }
        }
    }
}
