// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.dungeons.f7;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import java.util.HashMap;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.commands.P4BlockCommand;
import qolskyblockmod.pizzaclient.core.events.BlockChangeEvent;
import net.minecraft.util.MovingObjectPosition;
import qolskyblockmod.pizzaclient.util.VecUtil;
import java.util.Collection;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.init.Items;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.Entity;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.util.BlockPos;
import java.util.Set;

public class FunnyDevice
{
    private final Set<BlockPos> clickedItemFrames;
    private static final Map<BlockPos, Integer> requiredClicksForEntity;
    private boolean foundPattern;
    private final List<BlockPos> simonSaysQueue;
    public static final BlockPos simonSaysStart;
    public static boolean clickedSimonSays;
    private long lastInteractTime;
    
    public FunnyDevice() {
        this.clickedItemFrames = new HashSet<BlockPos>();
        this.simonSaysQueue = new ArrayList<BlockPos>();
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (PizzaClient.config.autoAlignment && this.foundPattern) {
            for (final Entity entity : PizzaClient.mc.field_71441_e.field_72996_f) {
                if (entity instanceof EntityItemFrame) {
                    final EntityItemFrame itemFrame = (EntityItemFrame)entity;
                    if (itemFrame.func_82335_i() == null || itemFrame.func_82335_i().func_77973_b() != Items.field_151032_g) {
                        continue;
                    }
                    final BlockPos pos = new BlockPos(itemFrame.field_70165_t, itemFrame.field_70163_u, itemFrame.field_70161_v);
                    if (!FunnyDevice.requiredClicksForEntity.containsKey(pos)) {
                        continue;
                    }
                    final double x = itemFrame.field_70165_t;
                    final double y = itemFrame.field_70163_u;
                    final double z = itemFrame.field_70161_v;
                    RenderUtil.drawFilledBoxNoESPWithFrustum(new AxisAlignedBB(x - 0.0025, y + 0.33, z - 0.33, x + 0.025, y - 0.33, z + 0.33), this.clickedItemFrames.contains(pos) ? new Color(0, 200, 0) : new Color(200, 0, 0));
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (PizzaClient.location != Locations.DUNGEON || PizzaClient.mc.field_71462_r != null || PizzaClient.mc.field_71476_x == null) {
            return;
        }
        if (PizzaClient.config.autoAlignment) {
            if (this.foundPattern && PizzaClient.mc.field_71476_x.field_72308_g instanceof EntityItemFrame) {
                final EntityItemFrame itemFrame = (EntityItemFrame)PizzaClient.mc.field_71476_x.field_72308_g;
                if (itemFrame.func_82335_i() != null && itemFrame.func_82335_i().func_77973_b() == Items.field_151032_g) {
                    final BlockPos itemFrameFixedPos = new BlockPos(itemFrame.field_70165_t, itemFrame.field_70163_u, itemFrame.field_70161_v);
                    if (!this.clickedItemFrames.contains(itemFrameFixedPos) && FunnyDevice.requiredClicksForEntity.containsKey(itemFrameFixedPos)) {
                        final int requiredRotation = FunnyDevice.requiredClicksForEntity.get(itemFrameFixedPos);
                        final int currentRotation = itemFrame.func_82333_j();
                        if (currentRotation != requiredRotation) {
                            final int clickAmount = (currentRotation < requiredRotation) ? (requiredRotation - currentRotation) : (requiredRotation - currentRotation + 8);
                            PlayerUtil.rightClick(clickAmount);
                        }
                        this.clickedItemFrames.add(itemFrameFixedPos);
                    }
                }
            }
            if (TickTimer.ticks % 80 == 0) {
                this.calculatePattern();
            }
        }
        if (PizzaClient.config.autoSimonSays && this.simonSaysQueue.size() != 0 && System.currentTimeMillis() - this.lastInteractTime >= PizzaClient.config.autoSimonSaysDelay && PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(110, 121, 92)).func_177230_c() == Blocks.field_150430_aB) {
            for (final BlockPos pos : new ArrayList<BlockPos>(this.simonSaysQueue)) {
                final MovingObjectPosition intercept = VecUtil.calculateInterceptLook(pos, PizzaClient.config.autoSimonSaysRange);
                if (intercept != null && PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), pos, intercept.field_178784_b, intercept.field_72307_f)) {
                    PizzaClient.mc.field_71439_g.func_71038_i();
                    this.simonSaysQueue.remove(pos);
                    this.lastInteractTime = System.currentTimeMillis();
                    break;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockChange(final BlockChangeEvent event) {
        if (event.pos.equals((Object)P4BlockCommand.emeraldPos)) {
            event.setCanceled(true);
            return;
        }
        if (PizzaClient.config.autoAimingDevice && event.currentState.func_177230_c() == Blocks.field_150475_bE) {
            if (event.oldState.func_177230_c() == Blocks.field_150406_ce) {
                FakeRotater.leftClick(new Vec3(event.pos.func_177958_n() + 0.5, event.pos.func_177956_o() + 1.2, event.pos.func_177952_p() + 0.5));
            }
            return;
        }
        if (PizzaClient.config.autoSimonSays && event.pos.func_177958_n() == 111 && event.currentState.func_177230_c() == Blocks.field_180398_cJ && (this.simonSaysQueue.size() == 0 || !this.simonSaysQueue.get(this.simonSaysQueue.size() - 1).equals((Object)event.pos))) {
            this.simonSaysQueue.add(new BlockPos(110, event.pos.func_177956_o(), event.pos.func_177952_p()));
            FunnyDevice.clickedSimonSays = true;
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldChangeEvent event) {
        this.foundPattern = false;
        this.clickedItemFrames.clear();
        FunnyDevice.requiredClicksForEntity.clear();
        this.simonSaysQueue.clear();
        FunnyDevice.clickedSimonSays = false;
    }
    
    private void calculatePattern() {
        FunnyDevice.requiredClicksForEntity.clear();
        final Map<BlockPos, Entity> itemFrames = new HashMap<BlockPos, Entity>();
        final List<BlockPos> currentItemFrames = new ArrayList<BlockPos>();
        final List<BlockPos> startItemFrames = new ArrayList<BlockPos>();
        final Set<BlockPos> endItemFrames = new HashSet<BlockPos>();
        for (final Entity entity : PizzaClient.mc.field_71441_e.field_72996_f) {
            if (entity instanceof EntityItemFrame) {
                final ItemStack displayed = ((EntityItemFrame)entity).func_82335_i();
                if (displayed == null) {
                    continue;
                }
                final Item item = displayed.func_77973_b();
                if (item == Items.field_151032_g) {
                    itemFrames.put(new BlockPos(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), entity);
                }
                else {
                    if (item != Item.func_150898_a(Blocks.field_150325_L)) {
                        continue;
                    }
                    if (EnumDyeColor.func_176764_b(displayed.func_77952_i()) == EnumDyeColor.LIME) {
                        startItemFrames.add(new BlockPos(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v));
                    }
                    else {
                        endItemFrames.add(new BlockPos(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v));
                    }
                }
            }
        }
        if (itemFrames.size() >= 9 && startItemFrames.size() != 0) {
            for (final BlockPos pos : startItemFrames) {
                BlockPos adjacent = pos.func_177984_a();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
                adjacent = pos.func_177977_b();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
                adjacent = pos.func_177968_d();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
                adjacent = pos.func_177978_c();
                if (itemFrames.containsKey(adjacent)) {
                    currentItemFrames.add(adjacent);
                }
            }
            for (int i = 0; i < 200; ++i) {
                if (currentItemFrames.size() == 0) {
                    if (!this.foundPattern) {
                        this.foundPattern = true;
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Found Pattern!"));
                    }
                    return;
                }
                final List<BlockPos> list = new ArrayList<BlockPos>(currentItemFrames);
                currentItemFrames.clear();
                for (final BlockPos pos2 : list) {
                    BlockPos adjacent2 = pos2.func_177984_a();
                    if (endItemFrames.contains(adjacent2)) {
                        FunnyDevice.requiredClicksForEntity.put(pos2, 7);
                    }
                    else {
                        adjacent2 = pos2.func_177977_b();
                        if (endItemFrames.contains(adjacent2)) {
                            FunnyDevice.requiredClicksForEntity.put(pos2, 3);
                        }
                        else {
                            adjacent2 = pos2.func_177968_d();
                            if (endItemFrames.contains(adjacent2)) {
                                FunnyDevice.requiredClicksForEntity.put(pos2, 5);
                            }
                            else {
                                adjacent2 = pos2.func_177978_c();
                                if (endItemFrames.contains(adjacent2)) {
                                    FunnyDevice.requiredClicksForEntity.put(pos2, 1);
                                }
                                else {
                                    if (FunnyDevice.requiredClicksForEntity.containsKey(pos2)) {
                                        continue;
                                    }
                                    adjacent2 = pos2.func_177984_a();
                                    if (itemFrames.containsKey(adjacent2) && !FunnyDevice.requiredClicksForEntity.containsKey(adjacent2)) {
                                        currentItemFrames.add(adjacent2);
                                        FunnyDevice.requiredClicksForEntity.put(pos2, 7);
                                    }
                                    else {
                                        adjacent2 = pos2.func_177977_b();
                                        if (itemFrames.containsKey(adjacent2) && !FunnyDevice.requiredClicksForEntity.containsKey(adjacent2)) {
                                            currentItemFrames.add(adjacent2);
                                            FunnyDevice.requiredClicksForEntity.put(pos2, 3);
                                        }
                                        else {
                                            adjacent2 = pos2.func_177968_d();
                                            if (itemFrames.containsKey(adjacent2) && !FunnyDevice.requiredClicksForEntity.containsKey(adjacent2)) {
                                                currentItemFrames.add(adjacent2);
                                                FunnyDevice.requiredClicksForEntity.put(pos2, 5);
                                            }
                                            else {
                                                adjacent2 = pos2.func_177978_c();
                                                if (!itemFrames.containsKey(adjacent2) || FunnyDevice.requiredClicksForEntity.containsKey(adjacent2)) {
                                                    continue;
                                                }
                                                currentItemFrames.add(adjacent2);
                                                FunnyDevice.requiredClicksForEntity.put(pos2, 1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.foundPattern = false;
        }
    }
    
    static {
        requiredClicksForEntity = new HashMap<BlockPos, Integer>();
        simonSaysStart = new BlockPos(110, 121, 91);
    }
}
