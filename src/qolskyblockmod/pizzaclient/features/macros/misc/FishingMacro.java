// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.misc;

import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.EnumFacing;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.init.Items;
import net.minecraft.block.BlockLiquid;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.AntiAFK;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

public class FishingMacro extends Macro
{
    private int fishingMode;
    private BlockPos position;
    private long lastNoHookTime;
    
    @Override
    public void onTick() {
        final BlockPos pos = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        if (this.position == null) {
            this.position = pos;
        }
        if (!AntiAFK.isMoving()) {
            if (!this.position.equals((Object)pos)) {
                final EnumFacing facing = PizzaClient.mc.field_71439_g.func_174811_aO();
                if (this.position.equals((Object)pos.func_177972_a(Utils.getLeftEnumfacing(facing)))) {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74370_x.func_151463_i(), true);
                }
                else if (this.position.equals((Object)pos.func_177972_a(Utils.getRightEnumfacing(facing)))) {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74366_z.func_151463_i(), true);
                }
            }
        }
        else {
            Movement.disableMovement();
        }
        if (!this.facingWater()) {
            this.fishingMode = -1;
            final MovingObjectPosition rayTrace = VecUtil.rayTrace(100.0f);
            if (rayTrace != null && rayTrace.func_178782_a() != null) {
                final BlockPos hit = rayTrace.func_178782_a();
                final EnumFacing player = PizzaClient.mc.field_71439_g.func_174811_aO();
                if (PizzaClient.mc.field_71441_e.func_180495_p(hit.func_177972_a(Utils.getLeftEnumfacing(player))).func_177230_c() instanceof BlockLiquid) {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74370_x.func_151463_i(), true);
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
                }
                else if (PizzaClient.mc.field_71441_e.func_180495_p(hit.func_177972_a(Utils.getRightEnumfacing(player))).func_177230_c() instanceof BlockLiquid) {
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74366_z.func_151463_i(), true);
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
                }
            }
            return;
        }
        switch (this.fishingMode) {
            case -1: {
                if (this.facingWater()) {
                    Movement.disableMovement();
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
                    this.fishingMode = 0;
                    AntiAFK.reset();
                    break;
                }
                break;
            }
            case 0: {
                if (AntiAFK.mode != 0) {
                    AntiAFK.check();
                    return;
                }
                if (PizzaClient.mc.field_71439_g.field_71104_cf == null) {
                    final ItemStack held = PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g();
                    if (held == null || held.func_77973_b() != Items.field_151112_aM) {
                        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.getFishingRod();
                    }
                    else {
                        KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                        this.fishingMode = 1;
                    }
                    break;
                }
                if (this.isInWater()) {
                    this.fishingMode = 1;
                    this.lastNoHookTime = 0L;
                    break;
                }
                if (this.isStandingStill()) {
                    KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                    break;
                }
                break;
            }
            case 1: {
                AntiAFK.check();
                if (PizzaClient.mc.field_71439_g.field_71104_cf == null) {
                    if (this.lastNoHookTime == 0L) {
                        this.lastNoHookTime = System.currentTimeMillis();
                        return;
                    }
                    if (System.currentTimeMillis() - this.lastNoHookTime >= 1000L) {
                        this.fishingMode = 0;
                        this.lastNoHookTime = 0L;
                    }
                    return;
                }
                else {
                    if (!this.isInWater()) {
                        this.fishingMode = 0;
                        this.lastNoHookTime = 0L;
                        return;
                    }
                    if (this.isStandingStill() && MathUtil.getDecimals(PizzaClient.mc.field_71439_g.field_71104_cf.field_70163_u) >= 0.77) {
                        this.fishingMode = 2;
                        this.lastNoHookTime = 0L;
                        break;
                    }
                    break;
                }
                break;
            }
            case 2: {
                AntiAFK.check();
                if (PizzaClient.mc.field_71439_g.field_71104_cf == null) {
                    if (this.lastNoHookTime == 0L) {
                        this.lastNoHookTime = System.currentTimeMillis();
                        return;
                    }
                    if (System.currentTimeMillis() - this.lastNoHookTime >= 1000L) {
                        this.fishingMode = 0;
                        this.lastNoHookTime = 0L;
                    }
                    return;
                }
                else {
                    if (!this.isInWater()) {
                        this.fishingMode = 0;
                        this.lastNoHookTime = 0L;
                        return;
                    }
                    if (MathUtil.getDecimals(PizzaClient.mc.field_71439_g.field_71104_cf.field_70163_u) < 0.77) {
                        KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                        this.fishingMode = 3;
                        this.lastNoHookTime = System.currentTimeMillis();
                        break;
                    }
                    break;
                }
                break;
            }
            case 3: {
                AntiAFK.check();
                if (System.currentTimeMillis() - this.lastNoHookTime >= 400L) {
                    this.fishingMode = 0;
                    this.lastNoHookTime = 0L;
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        this.addToggleMessage("Fishing Macro");
        AntiAFK.reset();
        this.position = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        this.fishingMode = 0;
    }
    
    @Override
    public boolean applyFailsafes() {
        return true;
    }
    
    private boolean isInWater() {
        return PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(PizzaClient.mc.field_71439_g.field_71104_cf.field_70165_t, PizzaClient.mc.field_71439_g.field_71104_cf.field_70163_u, PizzaClient.mc.field_71439_g.field_71104_cf.field_70161_v)).func_177230_c() instanceof BlockLiquid;
    }
    
    private boolean facingWater() {
        final MovingObjectPosition rayTrace = VecUtil.rayTrace(100.0f);
        return rayTrace != null && PizzaClient.mc.field_71441_e.func_180495_p(rayTrace.func_178782_a()).func_177230_c() instanceof BlockLiquid;
    }
    
    private boolean isStandingStill() {
        return MathUtil.abs(PizzaClient.mc.field_71439_g.field_71104_cf.field_70159_w) < 0.01 && MathUtil.abs(PizzaClient.mc.field_71439_g.field_71104_cf.field_70179_y) < 0.01;
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return false;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return true;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return PizzaClient.config.stopWhenNearPlayer;
    }
    
    @Override
    public void onRender() {
        final MovingObjectPosition pos = VecUtil.rayTraceStopLiquid(50.0f);
        if (pos != null) {
            RenderUtil.drawFilledEsp(pos.func_178782_a(), Color.CYAN, 0.4f, -0.251f);
        }
    }
}
