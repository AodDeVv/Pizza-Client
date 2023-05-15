// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.farming;

import net.minecraft.init.Blocks;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.FarmingMacro;

public class SShapedMacro extends FarmingMacro
{
    private boolean forward;
    private boolean left;
    private boolean right;
    
    @Override
    public void onTick() {
        super.onTick();
        if (PlayerUtil.isStandingStill() || (!this.right && !this.left && !this.forward)) {
            this.changeKeys();
        }
        else {
            final BlockPos pos = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, (double)MathUtil.round(PizzaClient.mc.field_71439_g.field_70163_u), PizzaClient.mc.field_71439_g.field_70161_v);
            final double moving = Utils.formatDouble(MathUtil.abs(PlayerUtil.getMovingDirection()) % 1.0);
            if (moving == 0.0) {
                final EnumFacing player = PizzaClient.mc.field_71439_g.func_174811_aO();
                if (this.left) {
                    final EnumFacing f = Utils.getLeftEnumfacing(player);
                    if (!this.isValid(f, pos)) {
                        this.changeKeys();
                    }
                }
                if (this.right) {
                    final EnumFacing f = Utils.getRightEnumfacing(player);
                    if (!this.isValid(f, pos)) {
                        this.changeKeys();
                    }
                }
                if (this.forward) {
                    final EnumFacing f = player;
                    if (!this.isValid(f, pos)) {
                        this.changeKeys();
                    }
                }
            }
        }
        if (this.left && this.right) {
            this.left = false;
        }
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), true);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74351_w.func_151463_i(), this.forward);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74370_x.func_151463_i(), this.left);
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74366_z.func_151463_i(), this.right);
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "S Shaped Farm Macro is now " + Utils.getColouredBoolean(toggled)));
        final boolean left = false;
        this.forward = left;
        this.right = left;
        this.left = left;
        super.onToggle();
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return false;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return true;
    }
    
    private void changeKeys() {
        this.disableMovement();
        final boolean left = false;
        this.forward = left;
        this.right = left;
        this.left = left;
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Changing keys..."));
        final EnumFacing player = PizzaClient.mc.field_71439_g.func_174811_aO();
        final BlockPos pos = new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, (double)MathUtil.round(PizzaClient.mc.field_71439_g.field_70163_u), PizzaClient.mc.field_71439_g.field_70161_v);
        EnumFacing facing = player;
        if (this.isValid(facing, pos)) {
            this.forward = true;
            return;
        }
        facing = Utils.getLeftEnumfacing(player);
        if (this.isValid(facing, pos)) {
            this.left = true;
            return;
        }
        facing = Utils.getRightEnumfacing(player);
        this.right = this.isValid(facing, pos);
    }
    
    public boolean isValid(final EnumFacing f, final BlockPos pos) {
        final BlockPos offset = pos.func_177972_a(f);
        return SShapedMacro.walkthrough.contains(PizzaClient.mc.field_71441_e.func_180495_p(offset).func_177230_c()) && SShapedMacro.walkthrough.contains(PizzaClient.mc.field_71441_e.func_180495_p(offset.func_177984_a()).func_177230_c()) && PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, (double)(MathUtil.roundUp(PizzaClient.mc.field_71439_g.field_70163_u) - 1), PizzaClient.mc.field_71439_g.field_70161_v)).func_177230_c() != Blocks.field_150350_a;
    }
}
