// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.mining.nuker;

import java.util.HashSet;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import net.minecraft.block.Block;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import qolskyblockmod.pizzaclient.util.rotation.RotationUtil;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.AiMining;
import net.minecraft.init.Blocks;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.features.player.AutoPowderChest;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import java.util.Set;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.NukerMacro;

public class PowderMacro extends NukerMacro
{
    public static final Set<BlockPos> clicked;
    
    @Override
    public void onTick() {
        if (PizzaClient.location == Locations.CHOLLOWS) {
            if (AutoPowderChest.particleVec == null) {
                NukerBase.nuker = this;
                if (PowderMacro.vec != null && VecUtil.calculateInterceptLook(PowderMacro.vec, 4.5f) == null) {
                    PowderMacro.vec = null;
                }
                if (PizzaClient.mc.field_71476_x.func_178782_a() != null) {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), AutoPowderChest.particleVec == null);
                    if (PizzaClient.mc.field_71462_r == null) {
                        final Block mouse = PizzaClient.mc.field_71441_e.func_180495_p(PizzaClient.mc.field_71476_x.func_178782_a()).func_177230_c();
                        if (mouse == Blocks.field_150486_ae || (mouse == Blocks.field_150357_h && PizzaClient.mc.field_71476_x.func_178782_a().func_177956_o() != 30) || AiMining.isBlockUnmineable(mouse)) {
                            new Rotater(RotationUtil.getYawDifference(RotationUtil.getYawClosestTo90Degrees()) + 90.0f, RotationUtil.getPitchDifference(5.0f)).setRotationAmount(16).rotate();
                        }
                    }
                }
            }
            else {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
            }
        }
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        PowderMacro.clicked.clear();
        if (toggled) {
            PizzaClient.config.nukerRotationPackets = true;
            PizzaClient.config.powderChestMacro = true;
            PizzaClient.config.autoCloseLootChest = true;
            new Rotater(RotationUtil.getYawDifference(RotationUtil.getYawClosestTo90Degrees()), RotationUtil.getPitchDifference(5.0f)).setRotationAmount(16).rotate();
        }
        this.addToggleMessage("Powder Macro");
    }
    
    @Override
    public void onDisable() {
        PowderMacro.vec = null;
    }
    
    @Override
    public void onNukePre() {
        if (PowderMacro.vec != null) {
            final BlockPos pos = new BlockPos(PowderMacro.vec);
            if (AiMining.mineables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c()) && TickTimer.ticks % PizzaClient.config.powderMacroTicks == 0) {
                PowderMacro.clicked.add(pos);
                PowderMacro.vec = null;
            }
        }
    }
    
    @Override
    public boolean isBlockValid(final BlockPos pos) {
        if (pos.func_177956_o() <= PizzaClient.mc.field_71439_g.field_70163_u - 1.0) {
            return false;
        }
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        return !PowderMacro.avoid.contains(block) && !PowderMacro.clicked.contains(pos) && !AiMining.isBlockUnmineable(block);
    }
    
    @Override
    public void onNoVecAvailable() {
        PowderMacro.clicked.clear();
    }
    
    @Override
    public Locations getLocation() {
        return Locations.CHOLLOWS;
    }
    
    @Override
    public void warpBack() {
        Locations.CHOLLOWS.warpTo();
    }
    
    static {
        clicked = new HashSet<BlockPos>();
    }
}
