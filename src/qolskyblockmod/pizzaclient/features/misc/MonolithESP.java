// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.util.BlockPos;
import java.util.List;

public class MonolithESP
{
    private final List<BlockPos> spawns;
    private BlockPos monolithPos;
    
    public MonolithESP() {
        this.spawns = new ArrayList<BlockPos>(Arrays.asList(new BlockPos(-15, 236, -92), new BlockPos(49, 202, -162), new BlockPos(56, 214, -25), new BlockPos(0, 170, 0), new BlockPos(150, 196, 190), new BlockPos(-64, 206, -63), new BlockPos(-93, 221, -53), new BlockPos(-94, 201, -30), new BlockPos(-9, 162, 109), new BlockPos(1, 183, 23), new BlockPos(61, 204, 181), new BlockPos(77, 160, 162), new BlockPos(92, 187, 131), new BlockPos(128, 187, 58)));
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (PizzaClient.config.monolithEsp && PizzaClient.location == Locations.DWARVENMINES) {
            if (this.monolithPos == null) {
                for (final BlockPos p : this.spawns) {
                    for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(p.func_177958_n() - 3, p.func_177956_o() - 1, p.func_177952_p() - 3), new BlockPos(p.func_177958_n() + 3, p.func_177956_o() + 1, p.func_177952_p() + 3))) {
                        if (PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() == Blocks.field_150380_bt) {
                            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Monolith!"));
                            this.monolithPos = pos;
                        }
                    }
                }
            }
            else {
                if (PizzaClient.mc.field_71441_e.func_180495_p(this.monolithPos).func_177230_c() != Blocks.field_150380_bt && PizzaClient.mc.field_71441_e.func_175668_a(this.monolithPos, false)) {
                    this.monolithPos = null;
                    return;
                }
                final BlockPos pos2 = this.monolithPos;
                RenderUtil.drawOutlinedEspWithFrustum(new AxisAlignedBB((double)(pos2.func_177958_n() - 1), (double)(pos2.func_177956_o() - 1), (double)(pos2.func_177952_p() - 1), (double)(pos2.func_177958_n() + 2), (double)(pos2.func_177956_o() + 2), (double)(pos2.func_177952_p() + 2)), Color.MAGENTA, 7.5f);
                RenderUtil.drawFilledEspWithFrustum(pos2, Color.MAGENTA);
            }
        }
    }
}
