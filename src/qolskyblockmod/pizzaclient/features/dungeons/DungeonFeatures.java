// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.dungeons;

import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.util.MathHelper;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.util.BlockPos;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import qolskyblockmod.pizzaclient.core.events.ResizeWindowEvent;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.Vec3;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.features.dungeons.f7.FunnyDevice;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraft.util.AxisAlignedBB;
import qolskyblockmod.pizzaclient.util.render.RenderType;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.StringUtils;
import net.minecraft.entity.item.EntityArmorStand;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import java.util.Set;
import qolskyblockmod.pizzaclient.util.render.OutlineRenderer;
import qolskyblockmod.pizzaclient.util.render.EntityType;
import java.util.Map;
import net.minecraft.entity.Entity;
import java.awt.Color;

public class DungeonFeatures
{
    public static boolean shouldLividsSpawn;
    private static Color lividColor;
    public static Entity lividEntity;
    public static long blindnessDuration;
    private static final Map<Entity, EntityType> starMobs;
    public static final OutlineRenderer felsOutline;
    public static final OutlineRenderer minibossOutline;
    public static final Set<String> BLOOD_MOBS;
    
    @SubscribeEvent(priority = EventPriority.LOW, receiveCanceled = true)
    public void onRenderLiving(final RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (PizzaClient.location != Locations.DUNGEON) {
            return;
        }
        if (event.entity instanceof EntityArmorStand) {
            if (event.entity.func_145818_k_()) {
                final String name = StringUtils.func_76338_a(event.entity.func_95999_t());
                if (PizzaClient.config.starredMobsEsp && name.startsWith("\u272f ")) {
                    if (Utils.getXandZDistanceSquared(event.entity.field_70165_t, event.entity.field_70161_v) < PizzaClient.config.starredMobsEspRange * PizzaClient.config.starredMobsEspRange) {
                        final EntityType mob = DungeonFeatures.starMobs.get(event.entity);
                        if (mob != null) {
                            if (mob.isDead()) {
                                DungeonFeatures.starMobs.remove(event.entity);
                                return;
                            }
                            switch (mob.type) {
                                case 0: {
                                    if (PizzaClient.config.starredMobsEspMode == 2) {
                                        RenderType.setUniversalOutlineColor(PizzaClient.config.starredMobsEspColor);
                                        RenderType.addEntity((Entity)mob.entityToRender);
                                        break;
                                    }
                                    final Entity e = (Entity)mob.entityToRender;
                                    RenderUtil.drawOutlinedEsp(new AxisAlignedBB(e.field_70165_t - 0.35, e.field_70163_u, e.field_70161_v - 0.35, e.field_70165_t + 0.35, e.field_70163_u + 2.0, e.field_70161_v + 0.35), PizzaClient.config.starredMobsEspColor, 3.0f);
                                    break;
                                }
                                case 1: {
                                    if (PizzaClient.config.starredMobsEspMode == 0) {
                                        RenderUtil.drawOutlinedEsp(mob.entityToRender.func_174813_aQ(), PizzaClient.config.starredMobsEspColor, 3.0f);
                                        break;
                                    }
                                    RenderType.addEntity(DungeonFeatures.felsOutline, (Entity)mob.entityToRender);
                                    break;
                                }
                                case 2: {
                                    if (PizzaClient.config.starredMobsEspMode == 0) {
                                        final Entity e = (Entity)mob.entityToRender;
                                        RenderUtil.drawOutlinedEsp(new AxisAlignedBB(e.field_70165_t - 0.4, e.field_70163_u, e.field_70161_v - 0.4, e.field_70165_t + 0.4, e.field_70163_u + 2.0, e.field_70161_v + 0.4), new Color(105, 0, 10), 3.0f);
                                        break;
                                    }
                                    RenderType.addEntity(DungeonFeatures.minibossOutline, (Entity)mob.entityToRender);
                                    break;
                                }
                            }
                        }
                        else if (Utils.getXandZDistanceSquared(event.entity.field_70165_t, event.entity.field_70161_v) < PizzaClient.config.starredMobsEspRange * PizzaClient.config.starredMobsEspRange) {
                            this.getStarredMobsWithinAABB((Entity)event.entity);
                        }
                    }
                    return;
                }
                if (PizzaClient.config.dungeonKeyEsp) {
                    if (name.equals("Wither Key")) {
                        RenderUtil.drawOutlinedEsp(new AxisAlignedBB(event.entity.field_70165_t - 0.5, event.entity.field_70163_u + 2.4, event.entity.field_70161_v - 0.5, event.entity.field_70165_t + 0.5, event.entity.field_70163_u + 0.9, event.entity.field_70161_v + 0.5), new Color(20, 20, 20), 3.0f);
                        return;
                    }
                    if (name.equals("Blood Key")) {
                        RenderUtil.drawOutlinedEsp(new AxisAlignedBB(event.entity.field_70165_t - 0.5, event.entity.field_70163_u + 2.4, event.entity.field_70161_v - 0.5, event.entity.field_70165_t + 0.5, event.entity.field_70163_u + 0.9, event.entity.field_70161_v + 0.5), new Color(180, 0, 0), 3.0f);
                        return;
                    }
                }
                if (PizzaClient.config.hideSuperboom && (name.equals("Revive Stone") || name.startsWith("Superboom TNT") || name.startsWith("Blessing of"))) {
                    PizzaClient.mc.field_71441_e.func_72900_e((Entity)event.entity);
                    event.setCanceled(true);
                    return;
                }
                if (PizzaClient.config.autoSimonSays && !FunnyDevice.clickedSimonSays && name.startsWith("Inactive")) {
                    final MovingObjectPosition intercept = VecUtil.calculateInterceptLook(FunnyDevice.simonSaysStart, PizzaClient.config.autoSimonSaysRange);
                    if (intercept != null) {
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Starting auto simon says!"));
                        if (PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), FunnyDevice.simonSaysStart, intercept.field_178784_b, intercept.field_72307_f)) {
                            PizzaClient.mc.field_71439_g.func_71038_i();
                        }
                        FunnyDevice.clickedSimonSays = true;
                    }
                    return;
                }
            }
            if (DungeonFeatures.lividEntity == event.entity) {
                if (PizzaClient.config.lividAura) {
                    FakeRotater.rotate(event.entity.func_174824_e(1.0f));
                }
                if (PizzaClient.config.lividEsp) {
                    RenderType.setUniversalOutlineColor(DungeonFeatures.lividColor);
                    RenderType.addEntity((Entity)event.entity);
                }
            }
        }
        else if (event.entity instanceof EntityOtherPlayerMP) {
            final String entityName = event.entity.func_70005_c_().trim();
            if (PizzaClient.config.alwaysAimAtSpiritBear && entityName.equals("Spirit Bear")) {
                FakeRotater.rotate((event.entity.field_70163_u % 1.0 == 0.0) ? event.entity.func_174824_e(1.0f) : new Vec3(event.entity.field_70165_t, 69.5, event.entity.field_70161_v));
                return;
            }
            if (PizzaClient.config.starredMobsEsp && entityName.equals("Shadow Assassin")) {
                if (PizzaClient.config.starredMobsEspMode == 0) {
                    final Entity e2 = (Entity)event.entity;
                    RenderUtil.drawOutlinedEsp(new AxisAlignedBB(e2.field_70165_t - 0.4, e2.field_70163_u, e2.field_70161_v - 0.4, e2.field_70165_t + 0.4, e2.field_70163_u + 2.0, e2.field_70161_v + 0.4), new Color(105, 0, 10), 3.0f);
                }
                else {
                    RenderType.addEntity(DungeonFeatures.minibossOutline, (Entity)event.entity);
                }
            }
        }
        else if (event.entity instanceof EntityEnderman) {
            if (PizzaClient.config.showHiddenMobs && event.entity.func_82150_aj()) {
                event.entity.func_82142_c(false);
            }
        }
        else if (event.entity instanceof EntityBat && PizzaClient.config.batEsp) {
            final float maxHP = event.entity.func_110138_aP();
            if (maxHP == 100.0f || maxHP == 200.0f) {
                RenderType.renderChromaChams(0.77f);
                event.setCanceled(false);
            }
        }
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onEntityJoin(final EntityJoinWorldEvent event) {
        if (PizzaClient.location != Locations.DUNGEON) {
            return;
        }
        if (event.entity instanceof EntityOtherPlayerMP) {
            final String trimmed = event.entity.func_70005_c_().trim();
            if (PizzaClient.config.spiritBearAura && trimmed.equals("Spirit Bear")) {
                FakeRotater.leftClick(new Vec3(event.entity.field_70165_t, 69.5, event.entity.field_70161_v));
                return;
            }
            if (PizzaClient.config.showHiddenMobs && trimmed.equals("Shadow Assassin")) {
                event.entity.func_82142_c(false);
                return;
            }
            if (PizzaClient.config.bloodTriggerBot && DungeonFeatures.BLOOD_MOBS.contains(trimmed) && !Utils.isGuiOpen()) {
                if (PizzaClient.config.bloodTriggerBotAimbot) {
                    if (event.entity.func_70068_e((Entity)PizzaClient.mc.field_71439_g) < 500.0) {
                        FakeRotater.leftClick(new Vec3(event.entity.field_70165_t, event.entity.field_70163_u + 0.2, event.entity.field_70161_v));
                    }
                }
                else if (VecUtil.isFacingAABB(new AxisAlignedBB(event.entity.field_70165_t - 0.5, event.entity.field_70163_u - 2.0, event.entity.field_70161_v - 0.5, event.entity.field_70165_t + 0.5, event.entity.field_70163_u + 3.0, event.entity.field_70161_v + 0.5), 30.0f)) {
                    PlayerUtil.leftClick();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldChangeEvent event) {
        DungeonFeatures.lividEntity = null;
        DungeonFeatures.blindnessDuration = 0L;
        DungeonFeatures.starMobs.clear();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void onFogDensity(final EntityViewRenderEvent.FogDensity event) {
        if (PizzaClient.mc.field_71441_e == null) {
            return;
        }
        if (PizzaClient.mc.field_71439_g.func_70644_a(Potion.field_76440_q) && (PizzaClient.config.lividEsp || PizzaClient.config.lividAura) && DungeonFeatures.shouldLividsSpawn) {
            if (DungeonFeatures.blindnessDuration == 0L) {
                DungeonFeatures.blindnessDuration = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - DungeonFeatures.blindnessDuration >= 600L) {
                getLivid();
            }
        }
        if (PizzaClient.config.antiBlindness) {
            event.density = 0.0f;
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onResizeWindow(final ResizeWindowEvent event) {
        DungeonFeatures.felsOutline.updateFramebuffer();
        DungeonFeatures.minibossOutline.updateFramebuffer();
        RenderType.universalOutline.updateFramebuffer();
    }
    
    @SubscribeEvent
    public void onEntityDeath(final LivingDeathEvent event) {
        if (DungeonFeatures.starMobs.size() != 0) {
            DungeonFeatures.starMobs.remove(event.entity);
        }
    }
    
    private static void getLivid() {
        EnumChatFormatting chatColor = null;
        switch ((EnumDyeColor)PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(6, 109, 43)).func_177229_b((IProperty)BlockStainedGlass.field_176547_a)) {
            case WHITE: {
                chatColor = EnumChatFormatting.WHITE;
                DungeonFeatures.lividColor = new Color(255, 250, 250);
                break;
            }
            case PINK: {
                chatColor = EnumChatFormatting.LIGHT_PURPLE;
                DungeonFeatures.lividColor = Color.MAGENTA;
                break;
            }
            case RED: {
                chatColor = EnumChatFormatting.RED;
                DungeonFeatures.lividColor = new Color(200, 0, 0);
                break;
            }
            case SILVER: {
                chatColor = EnumChatFormatting.GRAY;
                DungeonFeatures.lividColor = new Color(180, 180, 180);
                break;
            }
            case GRAY: {
                chatColor = EnumChatFormatting.GRAY;
                DungeonFeatures.lividColor = new Color(100, 100, 100);
                break;
            }
            case GREEN: {
                chatColor = EnumChatFormatting.DARK_GREEN;
                DungeonFeatures.lividColor = new Color(34, 100, 34);
                break;
            }
            case LIME: {
                chatColor = EnumChatFormatting.GREEN;
                DungeonFeatures.lividColor = new Color(0, 245, 0);
                break;
            }
            case BLUE: {
                chatColor = EnumChatFormatting.BLUE;
                DungeonFeatures.lividColor = new Color(0, 0, 225);
                break;
            }
            case PURPLE: {
                chatColor = EnumChatFormatting.DARK_PURPLE;
                DungeonFeatures.lividColor = new Color(128, 0, 160);
                break;
            }
            case YELLOW: {
                chatColor = EnumChatFormatting.YELLOW;
                DungeonFeatures.lividColor = new Color(245, 245, 0);
                break;
            }
            case MAGENTA: {
                chatColor = EnumChatFormatting.LIGHT_PURPLE;
                DungeonFeatures.lividColor = new Color(240, 0, 240);
                break;
            }
            default: {
                DungeonFeatures.lividColor = null;
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.DARK_RED + "ERROR: " + EnumChatFormatting.RED + "Could not find the glass color! Please report this. The unknown Glass Color is: " + EnumChatFormatting.GOLD + ((EnumDyeColor)PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(205, 109, 242)).func_177229_b((IProperty)BlockStainedGlass.field_176547_a)).name().toLowerCase()));
                return;
            }
        }
        for (final Entity entity : PizzaClient.mc.field_71441_e.func_72910_y()) {
            if (entity.func_70005_c_().startsWith(chatColor + "\ufd3e " + chatColor + "§lLivid")) {
                DungeonFeatures.lividEntity = entity;
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > Real Livid: " + DungeonFeatures.lividEntity.func_70005_c_()));
                DungeonFeatures.shouldLividsSpawn = false;
                DungeonFeatures.starMobs.clear();
                break;
            }
        }
    }
    
    public static void clipGhostBlocks() {
        for (final BlockPos pos : Arrays.asList(new BlockPos(88, 167, 41), new BlockPos(89, 167, 41), new BlockPos(90, 167, 41), new BlockPos(91, 166, 40), new BlockPos(91, 166, 41), new BlockPos(91, 167, 40), new BlockPos(91, 167, 41), new BlockPos(92, 166, 40), new BlockPos(92, 166, 41), new BlockPos(92, 167, 40), new BlockPos(92, 167, 41), new BlockPos(93, 166, 40), new BlockPos(93, 166, 41), new BlockPos(93, 167, 40), new BlockPos(93, 167, 41), new BlockPos(94, 166, 40), new BlockPos(94, 166, 41), new BlockPos(94, 167, 41), new BlockPos(94, 167, 40), new BlockPos(95, 166, 40), new BlockPos(95, 166, 41), new BlockPos(95, 167, 41), new BlockPos(95, 167, 40), new BlockPos(96, 167, 41), new BlockPos(96, 167, 40))) {
            PlayerUtil.ghostBlock(pos);
        }
    }
    
    public static void clipGhostBlocksToP5() {
        for (final BlockPos pos : Arrays.asList(new BlockPos(54, 64, 80), new BlockPos(54, 64, 79), new BlockPos(54, 63, 79))) {
            PlayerUtil.ghostBlock(pos);
        }
    }
    
    private void getStarredMobsWithinAABB(final Entity entity) {
        final AxisAlignedBB aabb = new AxisAlignedBB(entity.field_70165_t + 0.4, entity.field_70163_u - 2.0, entity.field_70161_v + 0.4, entity.field_70165_t - 0.4, entity.field_70163_u + 0.2, entity.field_70161_v - 0.4);
        final int i = MathHelper.func_76128_c(aabb.field_72340_a - 1.0) >> 4;
        final int j = MathHelper.func_76128_c(aabb.field_72336_d + 1.0) >> 4;
        final int k = MathHelper.func_76128_c(aabb.field_72339_c - 1.0) >> 4;
        final int l = MathHelper.func_76128_c(aabb.field_72334_f + 1.0) >> 4;
        for (int i2 = i; i2 <= j; ++i2) {
            for (int j2 = k; j2 <= l; ++j2) {
                this.getStarredMobsWithinAABBForEntity(PizzaClient.mc.field_71441_e.func_72964_e(i2, j2), entity, aabb);
            }
        }
    }
    
    private void getStarredMobsWithinAABBForEntity(final Chunk chunk, final Entity entityIn, final AxisAlignedBB aabb) {
        final ClassInheritanceMultiMap<Entity>[] entityLists = (ClassInheritanceMultiMap<Entity>[])chunk.func_177429_s();
        int i = MathHelper.func_76128_c((aabb.field_72338_b - World.MAX_ENTITY_RADIUS) / 16.0);
        int j = MathHelper.func_76128_c((aabb.field_72337_e + World.MAX_ENTITY_RADIUS) / 16.0);
        i = MathHelper.func_76125_a(i, 0, entityLists.length - 1);
        j = MathHelper.func_76125_a(j, 0, entityLists.length - 1);
        for (int k = i; k <= j; ++k) {
            if (!entityLists[k].isEmpty()) {
                for (final Entity e : entityLists[k]) {
                    if (e.func_174813_aQ().func_72326_a(aabb)) {
                        if (e instanceof EntityOtherPlayerMP) {
                            if (((EntityOtherPlayerMP)e).func_110143_aJ() <= 0.0f) {
                                continue;
                            }
                            final String trim = e.func_70005_c_().trim();
                            switch (trim) {
                                case "Lost Adventurer":
                                case "Diamond Guy": {
                                    DungeonFeatures.starMobs.put(entityIn, new EntityType((EntityLivingBase)e, 2));
                                    continue;
                                }
                                default: {
                                    if (e.func_82150_aj()) {
                                        continue;
                                    }
                                    if (e.func_110124_au().version() != 2) {
                                        continue;
                                    }
                                    DungeonFeatures.starMobs.put(entityIn, new EntityType((EntityLivingBase)e, 0));
                                    continue;
                                }
                            }
                        }
                        else if (e instanceof EntitySkeleton || e instanceof EntityZombie) {
                            if (((EntityMob)e).func_110143_aJ() <= 0.0f) {
                                continue;
                            }
                            if (e.func_82150_aj()) {
                                continue;
                            }
                            DungeonFeatures.starMobs.put(entityIn, new EntityType((EntityLivingBase)e, 0));
                        }
                        else {
                            if (!(e instanceof EntityEnderman) || ((EntityEnderman)e).func_110143_aJ() <= 0.0f) {
                                continue;
                            }
                            DungeonFeatures.starMobs.put(entityIn, new EntityType((EntityLivingBase)e, 1));
                        }
                    }
                }
            }
        }
    }
    
    static {
        DungeonFeatures.shouldLividsSpawn = false;
        starMobs = new HashMap<Entity, EntityType>();
        felsOutline = new OutlineRenderer(Color.MAGENTA);
        minibossOutline = new OutlineRenderer(new Color(140, 20, 50));
        BLOOD_MOBS = new HashSet<String>(Arrays.asList("Revoker", "Psycho", "Reaper", "Cannibal", "Mute", "Ooze", "Putrid", "Freak", "Leech", "Tear", "Parasite", "Flamer", "Skull", "Mr. Dead", "Vader", "Frost", "Walker", "Bonzo", "Scarf", "Livid", "WanderingSoul"));
    }
}
