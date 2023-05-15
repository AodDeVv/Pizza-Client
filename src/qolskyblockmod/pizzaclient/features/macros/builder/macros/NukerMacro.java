// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.builder.macros;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.mining.nuker.NukerBase;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.rotation.helper.BlockPosFakeRotationHelper;
import net.minecraft.util.EnumFacing;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import net.minecraft.util.BlockPos;
import net.minecraft.block.Block;
import java.util.Set;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.features.macros.mining.nuker.INuker;

public abstract class NukerMacro extends Macro implements INuker
{
    public static Vec3 vec;
    public static final Set<Block> avoid;
    
    @Override
    public abstract boolean isBlockValid(final BlockPos p0);
    
    @Override
    public boolean nuke(final boolean noSwing) {
        if (NukerMacro.vec != null) {
            FakeRotater.rotate(NukerMacro.vec);
            final BlockPos block = new BlockPos(NukerMacro.vec);
            if (!PizzaClient.mc.field_71439_g.func_71039_bw()) {
                if (PizzaClient.mc.field_71462_r == null) {
                    final EnumFacing facing = VecUtil.calculateEnumfacingLook(NukerMacro.vec);
                    if (PizzaClient.mc.field_71442_b.func_180512_c(block, facing)) {
                        PizzaClient.mc.field_71452_i.func_180532_a(block, facing);
                        if (!noSwing) {
                            PizzaClient.mc.field_71439_g.func_71038_i();
                        }
                        return true;
                    }
                }
                else {
                    PizzaClient.mc.field_71442_b.func_78767_c();
                }
            }
        }
        return false;
    }
    
    public boolean nukeSilent() {
        if (NukerMacro.vec != null) {
            final BlockPos block = new BlockPos(NukerMacro.vec);
            if (!PizzaClient.mc.field_71439_g.func_71039_bw()) {
                if (PizzaClient.mc.field_71462_r == null) {
                    final EnumFacing facing = VecUtil.calculateEnumfacingLook(NukerMacro.vec);
                    if (PizzaClient.mc.field_71442_b.func_180512_c(block, facing)) {
                        PizzaClient.mc.field_71452_i.func_180532_a(block, facing);
                        return true;
                    }
                }
                else {
                    PizzaClient.mc.field_71442_b.func_78767_c();
                }
            }
        }
        return false;
    }
    
    @Override
    public void onNukePre() {
    }
    
    @Override
    public boolean findVec() {
        final BlockPosFakeRotationHelper helper = new BlockPosFakeRotationHelper();
        for (int y = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70163_u); y < PizzaClient.mc.field_71439_g.field_70163_u + 2.0; ++y) {
            for (int x = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70165_t - 5.0); x < PizzaClient.mc.field_71439_g.field_70165_t + 5.0; ++x) {
                for (int z = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70161_v - 5.0); z < PizzaClient.mc.field_71439_g.field_70161_v + 5.0; ++z) {
                    final BlockPos pos = new BlockPos(x, y, z);
                    if (NukerBase.nuker.isBlockValid(pos)) {
                        if (PizzaClient.config.nukeReachableBlocks) {
                            if (!VecUtil.isHittable(pos)) {
                                continue;
                            }
                        }
                        else if (!VecUtil.canReachBlock(pos)) {
                            continue;
                        }
                        helper.compare(pos);
                    }
                }
            }
        }
        if (helper.isNotNull()) {
            NukerMacro.vec = MathUtil.randomAABBPoint(helper.bestPos);
            return true;
        }
        for (int y = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight() - 5.0); y < PizzaClient.mc.field_71439_g.field_70163_u + PlayerUtil.fastEyeHeight() + 5.0; ++y) {
            for (int x = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70165_t - 5.0); x < PizzaClient.mc.field_71439_g.field_70165_t + 5.0; ++x) {
                for (int z = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70161_v - 5.0); z < PizzaClient.mc.field_71439_g.field_70161_v + 5.0; ++z) {
                    final BlockPos pos = new BlockPos(x, y, z);
                    if (NukerBase.nuker.isBlockValid(pos)) {
                        if (PizzaClient.config.nukeReachableBlocks) {
                            if (!VecUtil.isHittable(pos)) {
                                continue;
                            }
                        }
                        else if (!VecUtil.canReachBlock(pos)) {
                            continue;
                        }
                        helper.compare(pos);
                    }
                }
            }
        }
        if (helper.isNotNull()) {
            NukerMacro.vec = MathUtil.randomAABBPoint(helper.bestPos);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isVecValid() {
        if (NukerMacro.vec == null) {
            return false;
        }
        final BlockPos pos = new BlockPos(NukerMacro.vec);
        return VecUtil.canReachBlock(pos) && this.isBlockValid(pos);
    }
    
    public void drawCurrentBlock() {
        if (NukerMacro.vec != null) {
            RenderUtil.drawFilledEspWithFrustum(new BlockPos(NukerMacro.vec), Color.CYAN, 0.5f);
        }
    }
    
    public void drawCurrentBlock(final Color c) {
        if (NukerMacro.vec != null) {
            RenderUtil.drawFilledEspWithFrustum(new BlockPos(NukerMacro.vec), c, 0.5f);
        }
    }
    
    public void setPoint(final Vec3 vecIn) {
        NukerMacro.vec = MathUtil.randomAABBPoint(vecIn);
    }
    
    public void setPoint(final BlockPos pos) {
        NukerMacro.vec = MathUtil.randomAABBPoint(pos);
    }
    
    public void swapToTool() {
        if (!PlayerUtil.holdingMiningTool()) {
            PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.getMiningTool();
        }
    }
    
    @Override
    public void onChat(final String unformatted) {
        if (unformatted.equals("Mining Speed Boost is now available!")) {
            PizzaClient.mc.field_71442_b.func_78769_a((EntityPlayer)PizzaClient.mc.field_71439_g, (World)PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g());
        }
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return false;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return false;
    }
    
    @Override
    public void onRender() {
        this.drawCurrentBlock();
    }
    
    @Override
    public boolean applyFailsafes() {
        return false;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return PizzaClient.config.stopWhenNearPlayer;
    }
    
    static {
        avoid = new HashSet<Block>(Arrays.asList(Blocks.field_150350_a, Blocks.field_150357_h, (Block)Blocks.field_150486_ae));
    }
}
