// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.mining.dwarven;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import java.util.Iterator;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockStone;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.CustomBlock;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.block.BlockColored;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.Refuel;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.AiMining;
import net.minecraft.block.Block;
import java.util.Set;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

public class MithrilMacro extends Macro
{
    public static final Set<Block> mithrilBlocks;
    public static final Set<Block> mineables;
    public static final AiMining miningAi;
    
    @Override
    public void onTick() {
        if (PizzaClient.location != Locations.DWARVENMINES) {
            return;
        }
        if (!PlayerUtil.holdingMiningTool()) {
            PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.getMiningTool();
        }
        if (MithrilMacro.miningAi.hittables.size() == 0) {
            MithrilMacro.miningAi.onMove();
        }
        if (PizzaClient.config.mithrilAutoRefuel == 2 && Refuel.drillNPC == null) {
            Refuel.drillNPC = Refuel.findDrillNPC();
        }
        if (MithrilMacro.miningAi.onTick()) {
            return;
        }
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), true);
        if (!Rotater.rotating) {
            if (PizzaClient.mc.field_71476_x.func_178782_a() == null || !PizzaClient.mc.field_71476_x.func_178782_a().equals((Object)MithrilMacro.miningAi.currentBlock)) {
                MithrilMacro.miningAi.currentBlock = null;
            }
            else if (PizzaClient.mc.field_71441_e.func_180495_p(MithrilMacro.miningAi.currentBlock).func_177230_c() == Blocks.field_150357_h || PizzaClient.mc.field_71441_e.func_180495_p(PizzaClient.mc.field_71476_x.func_178782_a()).func_177230_c() == Blocks.field_150357_h) {
                MithrilMacro.miningAi.currentBlock = null;
            }
            if (MithrilMacro.miningAi.currentBlock == null) {
                if (this.foundBlock(PizzaClient.config.mithrilPriority)) {
                    return;
                }
                if (this.foundBlock(PizzaClient.config.mithrilPriority2)) {
                    return;
                }
                if (this.foundBlock(PizzaClient.config.mithrilPriority3)) {
                    return;
                }
                if (!MithrilMacro.miningAi.findBlockNoFrustrum(MithrilMacro.mineables, PizzaClient.config.mithrilRotateAmount)) {
                    PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > Found no mithril blocks."));
                    MithrilMacro.miningAi.onMove();
                }
            }
        }
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        MithrilMacro.miningAi.onToggle();
        if (toggled) {
            PlayerUtil.useAbility();
        }
        this.addToggleMessage("Mithril Macro");
    }
    
    @Override
    public void onDisable() {
        MithrilMacro.miningAi.disable();
    }
    
    @Override
    public void onRender() {
        MithrilMacro.miningAi.render();
    }
    
    private boolean foundBlock(final int priority) {
        switch (priority) {
            case 0: {
                return MithrilMacro.miningAi.findBlock(Blocks.field_150325_L, (PropertyEnum<?>)BlockColored.field_176581_a, (Enum<?>)EnumDyeColor.LIGHT_BLUE, PizzaClient.config.mithrilRotateAmount);
            }
            case 1: {
                return MithrilMacro.miningAi.findBlock(Blocks.field_180397_cI, PizzaClient.config.mithrilRotateAmount);
            }
            case 2: {
                return MithrilMacro.miningAi.findBlock(Blocks.field_150406_ce, Blocks.field_150325_L, new CustomBlock((PropertyEnum<?>)BlockColored.field_176581_a, (Enum<?>)EnumDyeColor.GRAY), PizzaClient.config.mithrilRotateAmount);
            }
            case 3: {
                return MithrilMacro.miningAi.findTitanium(PizzaClient.config.mithrilRotateAmount);
            }
            case 4: {
                return MithrilMacro.miningAi.findBlock(Blocks.field_150340_R, PizzaClient.config.mithrilRotateAmount);
            }
            default: {
                return false;
            }
        }
    }
    
    @Override
    public void onChat(final String unformatted) {
        if (unformatted.equals("Mining Speed Boost is now available!")) {
            PlayerUtil.useAbility();
        }
        else if (unformatted.startsWith("Your ") && unformatted.endsWith("Refuel it by talking to a Drill Mechanic!")) {
            if (PizzaClient.config.mithrilAutoRefuel == 2) {
                if (Refuel.drillNPC == null) {
                    Refuel.drillNPC = Refuel.findDrillNPC();
                }
                if (Refuel.drillNPC != null) {
                    this.enqueueThread(Refuel::funnyRefuel);
                }
            }
            else if (PizzaClient.config.mithrilAutoRefuel == 1) {
                this.enqueueThread(() -> {
                    Refuel.legitRefuel();
                    MithrilMarkers.run();
                });
            }
        }
    }
    
    @Override
    public void onMove() {
        MithrilMacro.miningAi.hittables.clear();
        final int x = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70165_t);
        final int z = MathUtil.floor(PizzaClient.mc.field_71439_g.field_70161_v);
        for (final BlockPos pos : PlayerUtil.getPlayerBlocks()) {
            final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
            final Block block = state.func_177230_c();
            if (MithrilMacro.miningAi.miningBlocks.contains(block)) {
                if ((pos.func_177958_n() == x && pos.func_177952_p() == z) || Utils.isBlockBlocked(pos) || !VecUtil.isHittable(pos)) {
                    continue;
                }
                MithrilMacro.miningAi.hittables.add(pos);
            }
            else {
                if (block != Blocks.field_150348_b || state.func_177229_b((IProperty)BlockStone.field_176247_a) != BlockStone.EnumType.DIORITE_SMOOTH || pos.func_177958_n() == x || pos.func_177952_p() == z || Utils.isBlockBlocked(pos) || !VecUtil.isHittable(pos)) {
                    continue;
                }
                MithrilMacro.miningAi.hittables.add(pos);
            }
        }
    }
    
    @Override
    public void warpBack() {
        MithrilMarkers.run();
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.getMiningTool();
    }
    
    @Override
    public boolean applyFailsafes() {
        return true;
    }
    
    @Override
    public Locations getLocation() {
        return Locations.DWARVENMINES;
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return true;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return false;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return true;
    }
    
    static {
        mithrilBlocks = new HashSet<Block>(Arrays.asList(Blocks.field_150406_ce, Blocks.field_150325_L, Blocks.field_180397_cI, Blocks.field_150357_h, Blocks.field_150340_R));
        mineables = new HashSet<Block>(Arrays.asList(Blocks.field_150406_ce, Blocks.field_150325_L, Blocks.field_180397_cI, Blocks.field_150340_R));
        miningAi = new AiMining(MithrilMacro.mithrilBlocks, new HashMap<Block, CustomBlock>((Map<? extends Block, ? extends CustomBlock>)Collections.singletonMap((K)Blocks.field_150348_b, (V)CustomBlock.TITANIUM)));
    }
}
