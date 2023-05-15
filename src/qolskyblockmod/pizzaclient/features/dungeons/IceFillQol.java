// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.dungeons;

import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.Packet;
import qolskyblockmod.pizzaclient.core.events.BlockChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.util.PacketUtil;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.BlockPos;

public class IceFillQol
{
    private BlockPos currentBlock;
    private final List<BlockPos> iceBlocks;
    
    public IceFillQol() {
        this.iceBlocks = new ArrayList<BlockPos>();
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (!PizzaClient.config.qolIceFill || PizzaClient.location != Locations.DUNGEON) {
            return;
        }
        final BlockPos player = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u - 1.0, PizzaClient.mc.field_71439_g.field_70161_v);
        if (PizzaClient.mc.field_71441_e.func_180495_p(player).func_177230_c() == Blocks.field_150432_aD) {
            if (this.currentBlock == null) {
                this.currentBlock = player;
                PacketUtil.stopPackets = true;
            }
            if (!this.iceBlocks.contains(player)) {
                this.iceBlocks.add(player);
            }
        }
        else {
            PacketUtil.continueAndSendPackets();
            this.currentBlock = null;
            this.iceBlocks.clear();
        }
    }
    
    @SubscribeEvent
    public void onBlockChange(final BlockChangeEvent event) {
        if (PizzaClient.config.qolIceFill && event.pos.equals((Object)this.currentBlock) && event.currentState.func_177230_c() == Blocks.field_150403_cj && event.oldState.func_177230_c() == Blocks.field_150432_aD) {
            this.iceBlocks.remove(0);
            if (this.iceBlocks.size() == 0) {
                PacketUtil.continueAndSendPackets();
                this.currentBlock = null;
                this.iceBlocks.clear();
                return;
            }
            this.currentBlock = this.iceBlocks.get(0);
            if (this.currentBlock.equals((Object)new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u - 1.0, PizzaClient.mc.field_71439_g.field_70161_v))) {
                PacketUtil.continueAndSendPackets();
                this.currentBlock = null;
                this.iceBlocks.clear();
                return;
            }
            final List<Packet<?>> stopped = new ArrayList<Packet<?>>();
            boolean wasInsideBox = false;
            for (final Packet<?> packet : PacketUtil.pausedPackets) {
                if (wasInsideBox) {
                    stopped.add(packet);
                    PacketUtil.sendPackets(stopped);
                    return;
                }
                if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
                    final C03PacketPlayer.C06PacketPlayerPosLook pos = (C03PacketPlayer.C06PacketPlayerPosLook)packet;
                    if (this.currentBlock.equals((Object)new BlockPos(pos.func_149464_c(), (double)this.currentBlock.func_177956_o(), pos.func_149472_e())) && MathUtil.inBetween(pos.func_149464_c(), this.currentBlock.func_177958_n() + 0.15, this.currentBlock.func_177958_n() + 0.85) && MathUtil.inBetween(pos.func_149472_e(), this.currentBlock.func_177952_p() + 0.15, this.currentBlock.func_177952_p() + 0.85)) {
                        wasInsideBox = true;
                    }
                }
                else if (packet instanceof C03PacketPlayer.C04PacketPlayerPosition) {
                    final C03PacketPlayer.C04PacketPlayerPosition pos2 = (C03PacketPlayer.C04PacketPlayerPosition)packet;
                    if (this.currentBlock.equals((Object)new BlockPos(pos2.func_149464_c(), (double)this.currentBlock.func_177956_o(), pos2.func_149472_e())) && MathUtil.inBetween(pos2.func_149464_c(), this.currentBlock.func_177958_n() + 0.15, this.currentBlock.func_177958_n() + 0.85) && MathUtil.inBetween(pos2.func_149472_e(), this.currentBlock.func_177952_p() + 0.15, this.currentBlock.func_177952_p() + 0.85)) {
                        wasInsideBox = true;
                    }
                }
                stopped.add(packet);
            }
            PacketUtil.continueAndSendPackets();
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldChangeEvent event) {
        this.currentBlock = null;
        this.iceBlocks.clear();
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.currentBlock != null) {
            RenderUtil.drawOutlinedEspWithFrustum(this.currentBlock, Color.CYAN, 3.0f);
            for (final BlockPos pos : this.iceBlocks) {
                RenderUtil.drawOutlinedEspWithFrustum(pos, Color.GREEN);
            }
        }
    }
}
