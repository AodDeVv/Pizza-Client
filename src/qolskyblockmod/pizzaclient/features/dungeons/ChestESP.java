// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.dungeons;

import java.util.HashSet;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntity;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.render.RenderType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.util.BlockPos;
import java.util.Set;

public class ChestESP
{
    public static final Set<BlockPos> clickedBlocks;
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (RenderType.shouldRenderOutlines()) {
            RenderType.onRender(event.partialTicks);
        }
        if (PizzaClient.config.secretChestEsp) {
            for (final TileEntity tileEntity : PizzaClient.mc.field_71441_e.field_147482_g) {
                if (tileEntity instanceof TileEntityChest) {
                    final TileEntityChest chest = (TileEntityChest)tileEntity;
                    if (chest.func_145980_j() != 0 || ChestESP.clickedBlocks.contains(chest.func_174877_v())) {
                        continue;
                    }
                    if (PizzaClient.config.chestEspMode == 0) {
                        RenderUtil.drawFilledEspWithFrustum(chest.func_174877_v(), PizzaClient.config.secretChestEspColor, 0.7f);
                    }
                    else {
                        RenderUtil.drawOutlinedEspWithFrustum(chest.func_174877_v(), PizzaClient.config.secretChestEspColor, 2.2f);
                    }
                }
            }
        }
    }
    
    static {
        clickedBlocks = new HashSet<BlockPos>();
    }
}
