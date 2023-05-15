// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.skills;

import qolskyblockmod.pizzaclient.core.events.BlockChangeEvent;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.init.Blocks;
import java.util.concurrent.ConcurrentHashMap;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import java.awt.Color;
import net.minecraft.util.BlockPos;
import java.util.Map;

public class WorldScanner
{
    public static Map<BlockPos, Color> markedBlocks;
    public static final Color GEM_RED;
    public static final Color GEM_LIME;
    public static final Color GEM_ORANGE;
    public static final Color GEM_YELLOW;
    public static final Color GEM_LIGHTBLUE;
    public static final Color GEM_PURPLE;
    public static final Color GEM_MAGENTA;
    public static final Color GEM_OPAL;
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (PizzaClient.location == Locations.CHOLLOWS) {
            if (PizzaClient.config.gemstoneScanner && TickTimer.ticks % (PizzaClient.config.scannerDelay * 20) == 0) {
                final int pY;
                int belowY;
                int aboveY;
                final ConcurrentHashMap<BlockPos, Color> foundBlocks;
                int x;
                int y;
                int z;
                BlockPos pos;
                IBlockState state;
                EntityPlayerSP field_71439_g;
                final ChatComponentText chatComponentText;
                new Thread(() -> {
                    pY = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70163_u);
                    belowY = pY - PizzaClient.config.scannerWidth;
                    aboveY = pY + PizzaClient.config.scannerWidth;
                    if (belowY < 31) {
                        belowY = 31;
                    }
                    if (aboveY > 189) {
                        aboveY = 189;
                    }
                    foundBlocks = new ConcurrentHashMap<BlockPos, Color>();
                    for (x = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70165_t) - PizzaClient.config.scannerWidth; x < PizzaClient.mc.field_71439_g.field_70165_t + PizzaClient.config.scannerWidth; ++x) {
                        for (y = belowY; y < aboveY; ++y) {
                            for (z = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70161_v) - PizzaClient.config.scannerWidth; z < PizzaClient.mc.field_71439_g.field_70161_v + PizzaClient.config.scannerWidth; ++z) {
                                pos = new BlockPos(x, y, z);
                                state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
                                if (state.func_177230_c() == Blocks.field_150399_cn) {
                                    switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlass.field_176547_a)) {
                                        case RED: {
                                            if (PizzaClient.config.redGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_RED);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case LIME: {
                                            if (PizzaClient.config.limeGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_LIME);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case ORANGE: {
                                            if (PizzaClient.config.orangeGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_ORANGE);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case YELLOW: {
                                            if (PizzaClient.config.yellowGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_YELLOW);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case LIGHT_BLUE: {
                                            if (PizzaClient.config.lightBlueGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_LIGHTBLUE);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case PURPLE: {
                                            if (PizzaClient.config.purpleGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_PURPLE);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case MAGENTA: {
                                            if (PizzaClient.config.pinkGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_MAGENTA);
                                            }
                                            if (MiningFeatures.sayCoordsFairyGrotto) {
                                                Utils.playSound("random.orb", 0.5);
                                                field_71439_g = PizzaClient.mc.field_71439_g;
                                                new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Fairy Grotto in: " + EnumChatFormatting.RED + "X = " + x + ", Y = " + y + ", Z = " + z);
                                                field_71439_g.func_145747_a((IChatComponent)chatComponentText);
                                                MiningFeatures.miningCoords.add("§dFairy Grotto: §ax " + x + ", y " + y + ", z " + z);
                                                MiningFeatures.sayCoordsFairyGrotto = false;
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                                else if (state.func_177230_c() == Blocks.field_150397_co && PizzaClient.config.glassPanesEsp) {
                                    switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlassPane.field_176245_a)) {
                                        case RED: {
                                            if (PizzaClient.config.redGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_RED);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case LIME: {
                                            if (PizzaClient.config.limeGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_LIME);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case ORANGE: {
                                            if (PizzaClient.config.orangeGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_ORANGE);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case YELLOW: {
                                            if (PizzaClient.config.yellowGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_YELLOW);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case LIGHT_BLUE: {
                                            if (PizzaClient.config.lightBlueGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_LIGHTBLUE);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case PURPLE: {
                                            if (PizzaClient.config.purpleGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_PURPLE);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                        case MAGENTA: {
                                            if (PizzaClient.config.pinkGlassEsp) {
                                                foundBlocks.put(pos, WorldScanner.GEM_MAGENTA);
                                                break;
                                            }
                                            else {
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    WorldScanner.markedBlocks = foundBlocks;
                }).start();
            }
        }
        else if (PizzaClient.location == Locations.CRIMSON_ISLE && (PizzaClient.config.whiteGlassEsp || PizzaClient.config.sulphurEsp) && TickTimer.ticks % (PizzaClient.config.scannerDelay * 20) == 0) {
            final int pY2;
            int belowY2;
            int aboveY2;
            final ConcurrentHashMap<BlockPos, Color> foundBlocks2;
            int x2;
            int y2;
            int z2;
            BlockPos pos2;
            IBlockState state2;
            new Thread(() -> {
                pY2 = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70163_u);
                belowY2 = pY2 - PizzaClient.config.scannerWidth;
                aboveY2 = pY2 + PizzaClient.config.scannerWidth;
                if (belowY2 < 30) {
                    belowY2 = 30;
                }
                if (aboveY2 > 250) {
                    aboveY2 = 250;
                }
                foundBlocks2 = new ConcurrentHashMap<BlockPos, Color>();
                for (x2 = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70165_t) - PizzaClient.config.scannerWidth; x2 < PizzaClient.mc.field_71439_g.field_70165_t + PizzaClient.config.scannerWidth; ++x2) {
                    for (y2 = belowY2; y2 < aboveY2; ++y2) {
                        for (z2 = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70161_v) - PizzaClient.config.scannerWidth; z2 < PizzaClient.mc.field_71439_g.field_70161_v + PizzaClient.config.scannerWidth; ++z2) {
                            pos2 = new BlockPos(x2, y2, z2);
                            state2 = PizzaClient.mc.field_71441_e.func_180495_p(pos2);
                            if (PizzaClient.config.whiteGlassEsp) {
                                if (state2.func_177230_c() == Blocks.field_150399_cn) {
                                    if (state2.func_177229_b((IProperty)BlockStainedGlass.field_176547_a) == EnumDyeColor.SILVER) {
                                        foundBlocks2.put(pos2, WorldScanner.GEM_OPAL);
                                    }
                                }
                                else if (state2.func_177230_c() == Blocks.field_150397_co && PizzaClient.config.glassPanesEsp && state2.func_177229_b((IProperty)BlockStainedGlassPane.field_176245_a) == EnumDyeColor.WHITE) {
                                    foundBlocks2.put(pos2, WorldScanner.GEM_OPAL);
                                }
                            }
                            if (PizzaClient.config.sulphurEsp && state2.func_177230_c() == Blocks.field_150360_v) {
                                foundBlocks2.put(pos2, WorldScanner.GEM_YELLOW);
                            }
                        }
                    }
                }
                WorldScanner.markedBlocks = foundBlocks2;
            }).start();
        }
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (!WorldScanner.markedBlocks.isEmpty()) {
            for (final Map.Entry<BlockPos, Color> map : WorldScanner.markedBlocks.entrySet()) {
                RenderUtil.drawOutlinedEspWithFrustum(map.getKey(), map.getValue(), PizzaClient.config.gemstoneEspSize / 16.0f);
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockChange(final BlockChangeEvent event) {
        if (!WorldScanner.markedBlocks.isEmpty()) {
            WorldScanner.markedBlocks.remove(event.pos);
        }
    }
    
    public static Color getGemstoneColor(final BlockPos pos) {
        final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
        if (state.func_177230_c() == Blocks.field_150399_cn) {
            switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlassPane.field_176245_a)) {
                case RED: {
                    return WorldScanner.GEM_RED;
                }
                case LIME: {
                    return WorldScanner.GEM_LIME;
                }
                case ORANGE: {
                    return WorldScanner.GEM_ORANGE;
                }
                case YELLOW: {
                    return WorldScanner.GEM_YELLOW;
                }
                case LIGHT_BLUE: {
                    return WorldScanner.GEM_LIGHTBLUE;
                }
                case PURPLE: {
                    return WorldScanner.GEM_PURPLE;
                }
                case MAGENTA: {
                    return WorldScanner.GEM_MAGENTA;
                }
                case SILVER: {
                    return WorldScanner.GEM_OPAL;
                }
            }
        }
        else if (state.func_177230_c() == Blocks.field_150397_co) {
            switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlassPane.field_176245_a)) {
                case RED: {
                    return WorldScanner.GEM_RED;
                }
                case LIME: {
                    return WorldScanner.GEM_LIME;
                }
                case ORANGE: {
                    return WorldScanner.GEM_ORANGE;
                }
                case YELLOW: {
                    return WorldScanner.GEM_YELLOW;
                }
                case LIGHT_BLUE: {
                    return WorldScanner.GEM_LIGHTBLUE;
                }
                case PURPLE: {
                    return WorldScanner.GEM_PURPLE;
                }
                case MAGENTA: {
                    return WorldScanner.GEM_MAGENTA;
                }
                case WHITE: {
                    return WorldScanner.GEM_OPAL;
                }
            }
        }
        return Color.BLACK;
    }
    
    public static int getGemstoneType(final BlockPos pos) {
        final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
        if (state.func_177230_c() == Blocks.field_150399_cn) {
            switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlass.field_176547_a)) {
                case RED: {
                    return 1;
                }
                case LIME: {
                    return 2;
                }
                case ORANGE: {
                    return 3;
                }
                case YELLOW: {
                    return 4;
                }
                case LIGHT_BLUE: {
                    return 5;
                }
                case PURPLE: {
                    return 6;
                }
                case MAGENTA: {
                    return 7;
                }
                case SILVER: {
                    return 8;
                }
            }
        }
        else if (state.func_177230_c() == Blocks.field_150397_co) {
            switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlass.field_176547_a)) {
                case RED: {
                    return 1;
                }
                case LIME: {
                    return 2;
                }
                case ORANGE: {
                    return 3;
                }
                case YELLOW: {
                    return 4;
                }
                case LIGHT_BLUE: {
                    return 5;
                }
                case PURPLE: {
                    return 6;
                }
                case MAGENTA: {
                    return 7;
                }
                case WHITE: {
                    return 8;
                }
            }
        }
        return -1;
    }
    
    public static int getGemstoneType(final IBlockState state) {
        if (state.func_177230_c() == Blocks.field_150399_cn) {
            switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlass.field_176547_a)) {
                case RED: {
                    return 1;
                }
                case LIME: {
                    return 2;
                }
                case ORANGE: {
                    return 3;
                }
                case YELLOW: {
                    return 4;
                }
                case LIGHT_BLUE: {
                    return 5;
                }
                case PURPLE: {
                    return 6;
                }
                case MAGENTA: {
                    return 7;
                }
                case SILVER: {
                    return 8;
                }
            }
        }
        else if (state.func_177230_c() == Blocks.field_150397_co) {
            switch ((EnumDyeColor)state.func_177229_b((IProperty)BlockStainedGlass.field_176547_a)) {
                case RED: {
                    return 1;
                }
                case LIME: {
                    return 2;
                }
                case ORANGE: {
                    return 3;
                }
                case YELLOW: {
                    return 4;
                }
                case LIGHT_BLUE: {
                    return 5;
                }
                case PURPLE: {
                    return 6;
                }
                case MAGENTA: {
                    return 7;
                }
                case WHITE: {
                    return 8;
                }
            }
        }
        return -1;
    }
    
    static {
        WorldScanner.markedBlocks = new ConcurrentHashMap<BlockPos, Color>();
        GEM_RED = new Color(200, 15, 45);
        GEM_LIME = new Color(160, 255, 47);
        GEM_ORANGE = new Color(255, 110, 0);
        GEM_YELLOW = new Color(255, 210, 0);
        GEM_LIGHTBLUE = new Color(102, 178, 255);
        GEM_PURPLE = new Color(153, 50, 204);
        GEM_MAGENTA = new Color(250, 0, 250);
        GEM_OPAL = new Color(240, 247, 255);
    }
}
