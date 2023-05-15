// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.farming;

import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import net.minecraft.block.state.IBlockState;
import java.util.Iterator;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockLog;
import qolskyblockmod.pizzaclient.util.rotation.helper.MOPFakeRotationHelper;
import qolskyblockmod.pizzaclient.util.rotation.fake.SmoothFakeRotater;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

@SideOnly(Side.CLIENT)
public class CropAura extends Macro
{
    private float i;
    private Vec3 lastRotation;
    private BlockPos renderBlock;
    
    @Override
    public void onTick() {
        if (this.renderBlock != null) {
            if (!VecUtil.canReachBlock(this.renderBlock)) {
                this.renderBlock = null;
            }
            else if (PizzaClient.config.cropAuraMode == 3) {
                MovingObjectPosition position;
                if (PizzaClient.config.placeThroughBlocks) {
                    position = VecUtil.calculateInterceptLook(this.renderBlock, new Vec3(this.renderBlock.func_177958_n() + 0.5, this.renderBlock.func_177956_o() + 0.5, this.renderBlock.func_177952_p() + 0.5), 4.5f);
                }
                else {
                    position = this.rayTraceCocoaBean(this.renderBlock);
                }
                if (position != null) {
                    if (PizzaClient.mc.field_71441_e.func_180495_p(this.renderBlock.func_177972_a(position.field_178784_b)).func_177230_c() != Blocks.field_150350_a) {
                        this.renderBlock = null;
                    }
                }
                else {
                    this.renderBlock = null;
                }
            }
            else if (PizzaClient.mc.field_71441_e.func_180495_p(this.renderBlock).func_177230_c() != Blocks.field_150350_a) {
                this.renderBlock = null;
            }
        }
        final ItemStack held = PizzaClient.mc.field_71439_g.func_70694_bm();
        Label_0887: {
            switch (PizzaClient.config.cropAuraMode) {
                case 1: {
                    if (held == null || held.func_77973_b() != Items.field_151120_aE) {
                        for (int i = 0; i < 8; ++i) {
                            final ItemStack stackAt = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
                            if (stackAt != null && stackAt.func_77973_b() != null && stackAt.func_77973_b() == Items.field_151120_aE) {
                                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
                                break Label_0887;
                            }
                        }
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Found no sugar cane in your hotbar."));
                        return;
                    }
                    break;
                }
                case 2: {
                    if (held == null || held.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150434_aF)) {
                        for (int i = 0; i < 8; ++i) {
                            final ItemStack stackAt = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
                            if (stackAt != null && stackAt.func_77973_b() != null && stackAt.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150434_aF)) {
                                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
                                break Label_0887;
                            }
                        }
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Found no cactus in your hotbar."));
                        return;
                    }
                    break;
                }
                case 3: {
                    final int metadata = EnumDyeColor.BROWN.func_176767_b();
                    if (held == null) {
                        for (int j = 0; j < 8; ++j) {
                            final ItemStack stackAt2 = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[j];
                            if (stackAt2 != null && stackAt2.func_77973_b() != null && stackAt2.func_77973_b() == Items.field_151100_aR && stackAt2.func_77952_i() == metadata) {
                                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = j;
                                break Label_0887;
                            }
                        }
                    }
                    else {
                        if (held.func_77973_b() == Items.field_151100_aR && held.func_77952_i() == metadata) {
                            break;
                        }
                        if (held.func_77973_b() == Items.field_151100_aR) {
                            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumDyeColor.func_176766_a(held.func_77960_j()).toString()));
                        }
                        for (int j = 0; j < 8; ++j) {
                            final ItemStack stackAt2 = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[j];
                            if (stackAt2 != null && stackAt2.func_77973_b() != null && stackAt2.func_77973_b() == Items.field_151100_aR && stackAt2.func_77952_i() == metadata) {
                                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = j;
                                break Label_0887;
                            }
                        }
                    }
                    PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Found no cocoa beans in your hotbar."));
                    break;
                }
                case 4: {
                    if (held == null || held.func_77973_b() != Items.field_151075_bm) {
                        for (int j = 0; j < 8; ++j) {
                            final ItemStack stackAt2 = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[j];
                            if (stackAt2 != null && stackAt2.func_77973_b() != null && stackAt2.func_77973_b() == Items.field_151075_bm) {
                                PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = j;
                                break Label_0887;
                            }
                        }
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Found no nether wart in your hotbar."));
                        return;
                    }
                    break;
                }
            }
        }
        if (!this.run()) {
            this.renderBlock = null;
            this.i = 0.0f;
            return;
        }
        if (PizzaClient.config.cropAuraBPS != 0) {
            this.i += PizzaClient.config.cropAuraBPS;
            if (this.i >= 20.0f) {
                this.extraBlockPlace();
                this.i %= 20.0f;
            }
        }
    }
    
    private boolean run() {
        if (this.isRotating()) {
            return true;
        }
        if (this.lastRotation == null) {
            this.lastRotation = VecUtil.rayTrace(50.0f).field_72307_f;
            if (this.lastRotation == null) {
                this.lastRotation = PizzaClient.mc.field_71476_x.field_72307_f;
            }
        }
        final MovingObjectPosition position = this.searchBlock();
        if (position == null) {
            return false;
        }
        this.lastRotation = position.field_72307_f;
        if (PizzaClient.config.placeThroughBlocks) {
            new SmoothFakeRotater(position.field_72307_f, PizzaClient.config.cropAuraSmoothRotation) {
                @Override
                public void interact() {
                    if (PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), Utils.getBlockFromHit(position), position.field_178784_b, position.field_72307_f)) {
                        PizzaClient.mc.field_71439_g.func_71038_i();
                    }
                }
            }.use();
        }
        else {
            new SmoothFakeRotater(position.field_72307_f, PizzaClient.config.cropAuraSmoothRotation) {
                @Override
                public void interact() {
                    if (PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), position.func_178782_a(), position.field_178784_b, position.field_72307_f)) {
                        PizzaClient.mc.field_71439_g.func_71038_i();
                    }
                }
            }.use();
        }
        return true;
    }
    
    private void extraBlockPlace() {
        if (this.isRotating()) {
            return;
        }
        if (this.lastRotation == null) {
            this.lastRotation = VecUtil.rayTrace(50.0f).field_72307_f;
        }
        final MovingObjectPosition closest = this.searchBlock();
        if (closest != null && PizzaClient.mc.field_71442_b.func_178890_a(PizzaClient.mc.field_71439_g, PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g(), Utils.getBlockFromHit(closest), closest.field_178784_b, closest.field_72307_f)) {
            PizzaClient.mc.field_71439_g.func_71038_i();
        }
    }
    
    private MovingObjectPosition searchBlock() {
        final MOPFakeRotationHelper helper = new MOPFakeRotationHelper();
        switch (PizzaClient.config.cropAuraMode) {
            case 0: {
                for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t - 5.0, PizzaClient.mc.field_71439_g.field_70163_u - 4.0, PizzaClient.mc.field_71439_g.field_70161_v - 5.0), new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t + 5.0, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v + 5.0))) {
                    final Block blockAt = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
                    if (blockAt == Blocks.field_150458_ak) {
                        MovingObjectPosition position;
                        if (PizzaClient.config.placeThroughBlocks) {
                            final Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.99, pos.func_177952_p() + 0.5);
                            position = VecUtil.calculateInterceptLook(pos, vec, 4.5f);
                        }
                        else {
                            position = this.rayTrace(pos);
                        }
                        if (position == null) {
                            continue;
                        }
                        helper.compare(position);
                    }
                }
                if (helper.isNotNull()) {
                    this.renderBlock = (PizzaClient.config.placeThroughBlocks ? Utils.getBlockFromHit(helper.bestPos).func_177984_a() : helper.bestPos.func_178782_a().func_177984_a());
                    break;
                }
                break;
            }
            case 1: {
                for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t - 5.0, PizzaClient.mc.field_71439_g.field_70163_u - 4.0, PizzaClient.mc.field_71439_g.field_70161_v - 5.0), new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t + 5.0, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v + 5.0))) {
                    final Block blockAt = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
                    if ((blockAt == Blocks.field_150346_d || blockAt == Blocks.field_150349_c || blockAt == Blocks.field_150354_m) && PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a && Utils.isAdjacentToWater(pos)) {
                        MovingObjectPosition position;
                        if (PizzaClient.config.placeThroughBlocks) {
                            position = VecUtil.calculateInterceptLook(pos, new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.99, pos.func_177952_p() + 0.5), 4.5f);
                        }
                        else {
                            position = this.rayTrace(pos);
                        }
                        if (position == null) {
                            continue;
                        }
                        helper.compare(position);
                    }
                }
                if (helper.isNotNull()) {
                    this.renderBlock = (PizzaClient.config.placeThroughBlocks ? Utils.getBlockFromHit(helper.bestPos).func_177984_a() : helper.bestPos.func_178782_a().func_177984_a());
                    break;
                }
                break;
            }
            case 2: {
                for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t - 5.0, PizzaClient.mc.field_71439_g.field_70163_u - 4.0, PizzaClient.mc.field_71439_g.field_70161_v - 5.0), new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t + 5.0, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v + 5.0))) {
                    final Block blockAt = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
                    final BlockPos up = pos.func_177984_a();
                    if (blockAt == Blocks.field_150354_m && PizzaClient.mc.field_71441_e.func_180495_p(up).func_177230_c() == Blocks.field_150350_a && !Utils.isAdjacentToUncollidable(up)) {
                        MovingObjectPosition position2;
                        if (PizzaClient.config.placeThroughBlocks) {
                            position2 = VecUtil.calculateInterceptLook(pos, new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.99, pos.func_177952_p() + 0.5), 4.5f);
                        }
                        else {
                            position2 = this.rayTrace(pos);
                        }
                        if (position2 == null) {
                            continue;
                        }
                        helper.compare(position2);
                    }
                }
                if (helper.isNotNull()) {
                    this.renderBlock = (PizzaClient.config.placeThroughBlocks ? Utils.getBlockFromHit(helper.bestPos).func_177984_a() : helper.bestPos.func_178782_a().func_177984_a());
                    break;
                }
                break;
            }
            case 3: {
                for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t - 5.0, PizzaClient.mc.field_71439_g.field_70163_u - 4.0, PizzaClient.mc.field_71439_g.field_70161_v - 5.0), new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t + 5.0, PizzaClient.mc.field_71439_g.field_70163_u + 6.0, PizzaClient.mc.field_71439_g.field_70161_v + 5.0))) {
                    final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
                    if (state.func_177230_c() instanceof BlockLog && state.func_177229_b((IProperty)BlockPlanks.field_176383_a) == BlockPlanks.EnumType.JUNGLE) {
                        MovingObjectPosition position;
                        if (PizzaClient.config.placeThroughBlocks) {
                            position = VecUtil.calculateInterceptLook(pos, new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5), 4.5f);
                        }
                        else {
                            position = this.rayTraceCocoaBean(pos);
                        }
                        if (position == null || PizzaClient.mc.field_71441_e.func_180495_p(Utils.getBlockFromHit(position).func_177972_a(position.field_178784_b)).func_177230_c() != Blocks.field_150350_a) {
                            continue;
                        }
                        helper.compare(position);
                    }
                }
                if (helper.isNotNull()) {
                    this.renderBlock = (PizzaClient.config.placeThroughBlocks ? Utils.getBlockFromHit(helper.bestPos) : helper.bestPos.func_178782_a());
                    break;
                }
                break;
            }
            case 4: {
                for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t - 5.0, PizzaClient.mc.field_71439_g.field_70163_u - 4.0, PizzaClient.mc.field_71439_g.field_70161_v - 5.0), new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t + 5.0, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v + 5.0))) {
                    final Block blockAt = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
                    if (blockAt == Blocks.field_150425_aM) {
                        MovingObjectPosition position;
                        if (PizzaClient.config.placeThroughBlocks) {
                            final Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.99, pos.func_177952_p() + 0.5);
                            position = VecUtil.calculateInterceptLook(pos, vec, 4.5f);
                        }
                        else {
                            position = this.rayTrace(pos);
                        }
                        if (position == null || position.field_178784_b != EnumFacing.UP) {
                            continue;
                        }
                        helper.compare(position);
                    }
                }
                if (helper.isNotNull()) {
                    this.renderBlock = (PizzaClient.config.placeThroughBlocks ? Utils.getBlockFromHit(helper.bestPos).func_177984_a() : helper.bestPos.func_178782_a().func_177984_a());
                    break;
                }
                break;
            }
        }
        return helper.bestPos;
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        this.renderBlock = null;
        this.addToggleMessage("Crop Place Aura");
    }
    
    @Override
    public void onRender() {
        if (this.renderBlock != null) {
            RenderUtil.drawFilledEspWithFrustum(this.renderBlock, Color.CYAN, 0.5f);
        }
    }
    
    @Override
    public boolean applyFailsafes() {
        return false;
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
    public boolean applyPlayerFailsafes() {
        return false;
    }
    
    private MovingObjectPosition rayTrace(final BlockPos pos) {
        Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
        MovingObjectPosition position = VecUtil.rayTraceLook(vec, 4.5f);
        if (position != null && position.func_178782_a().equals((Object)pos)) {
            return position;
        }
        for (int x = 1; x < 5; ++x) {
            for (int y = 1; y < 5; ++y) {
                for (int z = 1; z < 5; ++z) {
                    vec = new Vec3(pos.func_177958_n() + x / 4.0 - 0.125, pos.func_177956_o() + y / 4.0 - 0.125, pos.func_177952_p() + z / 4.0 - 0.125);
                    position = VecUtil.rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos) && position.field_178784_b == EnumFacing.UP) {
                        return position;
                    }
                }
            }
        }
        return null;
    }
    
    private MovingObjectPosition rayTraceCocoaBean(final BlockPos pos) {
        Vec3 vec = new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
        MovingObjectPosition position = VecUtil.rayTraceLook(vec, 4.5f);
        if (position != null && position.func_178782_a().equals((Object)pos)) {
            return position;
        }
        for (int x = 1; x < 5; ++x) {
            for (int y = 1; y < 5; ++y) {
                for (int z = 1; z < 5; ++z) {
                    vec = new Vec3(pos.func_177958_n() + x / 4.0 - 0.125, pos.func_177956_o() + y / 4.0 - 0.125, pos.func_177952_p() + z / 4.0 - 0.125);
                    position = VecUtil.rayTraceLook(vec, 4.5f);
                    if (position != null && position.func_178782_a().equals((Object)pos) && position.field_178784_b != EnumFacing.UP && position.field_178784_b != EnumFacing.DOWN && PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177972_a(PlayerUtil.getHorizontalFacing(position.field_72307_f).func_176734_d())).func_177230_c() == Blocks.field_150350_a) {
                        return position;
                    }
                }
            }
        }
        return null;
    }
    
    private boolean isRotating() {
        return FakeRotater.rotater instanceof SmoothFakeRotater && ((SmoothFakeRotater)FakeRotater.rotater).rotatingToGoal();
    }
}
