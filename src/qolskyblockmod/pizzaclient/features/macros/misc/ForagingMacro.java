// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.misc;

import net.minecraft.block.Block;
import java.util.Iterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Items;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.SnapRotater;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

public class ForagingMacro extends Macro
{
    private long treeMode;
    
    @Override
    public void onTick() {
        if (PizzaClient.location != Locations.PRIVATEISLAND) {
            return;
        }
        switch ((int)this.treeMode) {
            case 0: {
                if (!PlayerUtil.holdingItem(Item.func_150898_a(Blocks.field_150345_g))) {
                    PlayerUtil.swapToSlot(Item.func_150898_a(Blocks.field_150345_g));
                    return;
                }
                final Vec3 furthest = this.getDirt();
                if (furthest != null) {
                    SnapRotater.snapTo(furthest);
                    this.treeMode = 1L;
                    break;
                }
                this.treeMode = 2L;
                break;
            }
            case 1: {
                KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                this.treeMode = 0L;
                break;
            }
            case 2: {
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.checkHotBarForItem(Items.field_151100_aR);
                this.treeMode = 3L;
                break;
            }
            case 3: {
                this.treeMode = 4L;
                break;
            }
            case 4: {
                KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                this.treeMode = 5L;
                break;
            }
            case 5: {
                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.checkHotbarForDisplayName("Treecapitator");
                this.treeMode = 6L;
                break;
            }
            case 6: {
                if (PizzaClient.mc.field_71476_x.func_178782_a() != null && PizzaClient.mc.field_71441_e.func_180495_p(PizzaClient.mc.field_71476_x.func_178782_a()).func_177230_c() instanceof BlockLog) {
                    this.treeMode = 7L;
                    break;
                }
                break;
            }
            case 7: {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), true);
                final BlockPos pos = PizzaClient.mc.field_71476_x.func_178782_a();
                if (pos != null && !(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() instanceof BlockLog)) {
                    this.treeMode = System.currentTimeMillis();
                    KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), false);
                    break;
                }
                break;
            }
            default: {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), false);
                final Vec3 dirt = this.getDirt();
                if (dirt != null) {
                    SnapRotater.snapTo(dirt);
                }
                if (System.currentTimeMillis() - this.treeMode >= PizzaClient.config.foragingMacroDelay + 100) {
                    this.treeMode = 0L;
                    PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.checkHotBarForItem(Item.func_150898_a(Blocks.field_150345_g));
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        if (toggled) {
            PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.checkHotBarForItem(Item.func_150898_a(Blocks.field_150345_g));
        }
        this.addToggleMessage("Foraging Macro");
        this.treeMode = 0L;
    }
    
    @Override
    public boolean applyFailsafes() {
        return true;
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return true;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return true;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return false;
    }
    
    @Override
    public Locations getLocation() {
        return Locations.PRIVATEISLAND;
    }
    
    @Override
    public void warpBack() {
        Locations.PRIVATEISLAND.warpTo();
        this.treeMode = 0L;
    }
    
    private Vec3 getDirt() {
        Vec3 furthest = null;
        final Vec3 player = PlayerUtil.getPositionEyes();
        for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t + 4.0, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v + 4.0), new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t - 4.0, PizzaClient.mc.field_71439_g.field_70163_u - 1.0, PizzaClient.mc.field_71439_g.field_70161_v - 4.0))) {
            if (PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() == Blocks.field_150346_d || PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() == Blocks.field_150349_c) {
                final Block block = PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(pos.func_177958_n(), pos.func_177956_o() + 1, pos.func_177952_p())).func_177230_c();
                if (block instanceof BlockLog || block == Blocks.field_150345_g) {
                    continue;
                }
                final Vec3 distance = new Vec3(pos.func_177958_n() + 0.5, (double)(pos.func_177956_o() + 1), pos.func_177952_p() + 0.5);
                if (furthest != null && player.func_72436_e(distance) <= player.func_72436_e(furthest)) {
                    continue;
                }
                furthest = distance;
            }
        }
        return furthest;
    }
}
