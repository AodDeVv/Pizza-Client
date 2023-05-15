// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.mining;

import java.util.Collection;
import java.util.Arrays;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.VecUtil;
import java.util.ConcurrentModificationException;
import java.awt.Color;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockFence;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import java.util.Iterator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.World;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import qolskyblockmod.pizzaclient.util.rotation.helper.BlockPosRotationHelper;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import net.minecraft.util.Vec3;
import net.minecraft.util.BlockPos;
import net.minecraft.block.Block;
import java.util.Set;

public class AiMining
{
    public Set<Block> miningBlocks;
    public BlockPos currentBlock;
    public Vec3 blockVec;
    public static final Set<Block> mineables;
    public long lastBlockSwitch;
    private boolean fixing;
    public final List<BlockPos> hittables;
    private final Map<Block, CustomBlock> customBlocks;
    
    public AiMining(final Block block) {
        this.miningBlocks = new HashSet<Block>();
        this.hittables = new ArrayList<BlockPos>();
        this.miningBlocks.add(block);
        this.customBlocks = new HashMap<Block, CustomBlock>();
    }
    
    public AiMining(final Set<Block> blocks) {
        this.miningBlocks = new HashSet<Block>();
        this.hittables = new ArrayList<BlockPos>();
        this.miningBlocks = blocks;
        this.customBlocks = new HashMap<Block, CustomBlock>();
    }
    
    public AiMining(final Block block, final Map<Block, CustomBlock> map) {
        this.miningBlocks = new HashSet<Block>();
        this.hittables = new ArrayList<BlockPos>();
        this.miningBlocks.add(block);
        this.customBlocks = map;
    }
    
    public AiMining(final Set<Block> blocks, final Map<Block, CustomBlock> map) {
        this.miningBlocks = new HashSet<Block>();
        this.hittables = new ArrayList<BlockPos>();
        this.miningBlocks = blocks;
        this.customBlocks = map;
    }
    
    public boolean findBlock(final Block block, final int rotateAmount) {
        final BlockPosRotationHelper helper = new BlockPosRotationHelper();
        final Frustum frustum = RenderUtil.setupFrustrum();
        for (final BlockPos pos : this.hittables) {
            if (PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() == block && frustum.func_78546_a(block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos))) {
                helper.compare(pos);
            }
        }
        if (helper.bestPos != null) {
            this.setValues(helper.bestPos, rotateAmount);
            return true;
        }
        return false;
    }
    
    public boolean findBlock(final Block block, final PropertyEnum<?> property, final Enum<?> value, final int rotateAmount) {
        final BlockPosRotationHelper helper = new BlockPosRotationHelper();
        final Frustum frustum = RenderUtil.setupFrustrum();
        for (final BlockPos pos : this.hittables) {
            final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
            if (state.func_177230_c() == block && frustum.func_78546_a(block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos)) && state.func_177229_b((IProperty)property) == value) {
                helper.compare(pos);
            }
        }
        if (helper.bestPos != null) {
            this.setValues(helper.bestPos, rotateAmount);
            return true;
        }
        return false;
    }
    
    public boolean findTitanium(final int rotateAmount) {
        final BlockPosRotationHelper helper = new BlockPosRotationHelper();
        final Frustum frustum = RenderUtil.setupFrustrum();
        for (final BlockPos pos : this.hittables) {
            final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
            if (state.func_177230_c() == Blocks.field_150348_b && state.func_177229_b((IProperty)BlockStone.field_176247_a) == BlockStone.EnumType.DIORITE_SMOOTH && frustum.func_78546_a(Blocks.field_150348_b.func_180646_a((World)PizzaClient.mc.field_71441_e, pos))) {
                helper.compare(pos);
            }
        }
        if (helper.bestPos != null) {
            this.setValues(helper.bestPos, rotateAmount);
            return true;
        }
        return false;
    }
    
    public boolean findBlock(final Set<Block> blocks, final int rotateAmount) {
        final BlockPosRotationHelper helper = new BlockPosRotationHelper();
        final Frustum frustum = RenderUtil.setupFrustrum();
        for (final BlockPos pos : this.hittables) {
            final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
            if (blocks.contains(block) && frustum.func_78546_a(block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos))) {
                helper.compare(pos);
            }
        }
        if (helper.bestPos != null) {
            this.setValues(helper.bestPos, rotateAmount);
            return true;
        }
        return false;
    }
    
    public boolean findBlockNoFrustrum(final Set<Block> blocks, final int rotateAmount) {
        final BlockPosRotationHelper helper = new BlockPosRotationHelper();
        for (final BlockPos pos : this.hittables) {
            if (blocks.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c())) {
                helper.compare(pos);
            }
        }
        if (helper.bestPos != null) {
            this.setValues(helper.bestPos, rotateAmount);
            return true;
        }
        return false;
    }
    
    public boolean findBlock(final Block block, final Block custom, final CustomBlock customBlock, final int rotateAmount) {
        final BlockPosRotationHelper helper = new BlockPosRotationHelper();
        final Frustum frustum = RenderUtil.setupFrustrum();
        for (final BlockPos pos : this.hittables) {
            final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
            final Block bl = state.func_177230_c();
            if (frustum.func_78546_a(block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos))) {
                if (block == bl) {
                    helper.compare(pos);
                }
                else {
                    if (bl != custom || state.func_177229_b((IProperty)customBlock.property) != customBlock.value) {
                        continue;
                    }
                    helper.compare(pos);
                }
            }
        }
        if (helper.bestPos != null) {
            this.setValues(helper.bestPos, rotateAmount);
            return true;
        }
        return false;
    }
    
    public void disable() {
        this.currentBlock = null;
        this.fixing = false;
    }
    
    public void rotate(final Vec3 vec, final int rotation) {
        if (vec != null) {
            new Rotater(vec).antiSus((float)rotation).rotate();
        }
        else {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Vec returned null somehow."));
            this.currentBlock = null;
            this.onMove();
        }
    }
    
    public boolean onTick() {
        if (this.blockVec == null) {
            this.blockVec = PizzaClient.mc.field_71476_x.field_72307_f;
        }
        if (this.currentBlock != null && PizzaClient.mc.field_71441_e.func_180495_p(this.currentBlock).func_177230_c() == Blocks.field_150357_h) {
            this.currentBlock = null;
            return this.fixing;
        }
        if (this.fixing) {
            return true;
        }
        if (this.currentBlock != null && System.currentTimeMillis() - this.lastBlockSwitch >= PizzaClient.config.mithrilMacroFixTime * 1000L) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Detected not mining. If you were still mining a block, change the mithril macro fix time in the config."));
            this.fixing = true;
            KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74312_F.func_151463_i(), true);
            new Thread(() -> {
                this.onMove();
                Utils.sleep(350 + Utils.random.nextInt(200));
                this.rotateToClosestHitttable();
                while (Rotater.rotating) {
                    Utils.sleep(1);
                }
                Utils.sleep(500);
                this.fixing = false;
                this.lastBlockSwitch = System.currentTimeMillis();
                return;
            }).start();
            return true;
        }
        return false;
    }
    
    public void onToggle() {
        this.blockVec = PizzaClient.mc.field_71476_x.field_72307_f;
        this.lastBlockSwitch = System.currentTimeMillis();
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.getMiningTool();
        this.fixing = false;
    }
    
    public static boolean isBlockUnmineable(final Block blockIn) {
        return blockIn instanceof BlockFence || blockIn instanceof BlockStairs;
    }
    
    public void render() {
        if (this.currentBlock != null) {
            RenderUtil.drawFilledEsp(this.currentBlock, Color.CYAN, 0.5f);
        }
        try {
            for (final BlockPos pos : this.hittables) {
                RenderUtil.drawOutlinedEspWithFrustum(pos, Color.CYAN);
            }
        }
        catch (ConcurrentModificationException ex) {}
    }
    
    public void render(final Color c) {
        if (this.currentBlock != null) {
            RenderUtil.drawFilledEsp(this.currentBlock, c, 0.5f);
        }
        try {
            for (final BlockPos pos : this.hittables) {
                RenderUtil.drawOutlinedEspWithFrustum(pos, c);
            }
        }
        catch (ConcurrentModificationException ex) {}
    }
    
    public void onMove() {
        this.hittables.clear();
        if (this.customBlocks.size() == 0) {
            for (final BlockPos pos : PlayerUtil.getPlayerBlocks()) {
                if (this.miningBlocks.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c()) && pos.func_177956_o() != PizzaClient.mc.field_71439_g.field_70163_u - 1.0 && !Utils.isBlockBlocked(pos) && VecUtil.isHittable(pos)) {
                    this.hittables.add(pos);
                }
            }
        }
        else {
            for (final BlockPos pos : PlayerUtil.getPlayerBlocks()) {
                final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
                final Block block = state.func_177230_c();
                if (this.miningBlocks.contains(block)) {
                    if (pos.func_177956_o() == PizzaClient.mc.field_71439_g.field_70163_u - 1.0 || Utils.isBlockBlocked(pos) || !VecUtil.isHittable(pos)) {
                        continue;
                    }
                    this.hittables.add(pos);
                }
                else {
                    if (!this.customBlocks.containsKey(block)) {
                        continue;
                    }
                    final CustomBlock customBlock = this.customBlocks.get(block);
                    if (state.func_177229_b((IProperty)customBlock.property) != customBlock.value || pos.func_177956_o() == PizzaClient.mc.field_71439_g.field_70163_u - 1.0 || Utils.isBlockBlocked(pos) || !VecUtil.isHittable(pos)) {
                        continue;
                    }
                    this.hittables.add(pos);
                }
            }
        }
    }
    
    public void setValues(final BlockPos closestBlock, final int rotateAmount) {
        if (closestBlock == null) {
            return;
        }
        this.currentBlock = closestBlock;
        this.lastBlockSwitch = System.currentTimeMillis();
        this.blockVec = VecUtil.getClosestHittableToMiddle(closestBlock);
        if (this.blockVec == null) {
            this.hittables.remove(closestBlock);
            return;
        }
        this.rotate(this.blockVec, rotateAmount);
    }
    
    public void setValues(final Vec3 closestBlock, final int rotateAmount) {
        this.setValues(new BlockPos(closestBlock), rotateAmount);
    }
    
    private void rotateToClosestHitttable() {
        final BlockPosRotationHelper helper = new BlockPosRotationHelper();
        for (final BlockPos pos : this.hittables) {
            if (!pos.equals((Object)this.currentBlock)) {
                helper.compare(pos);
            }
        }
        if (helper.isNotNull()) {
            this.currentBlock = helper.bestPos;
            new Rotater(VecUtil.getClosestHittableToMiddle(helper.bestPos)).antiSus((float)PizzaClient.config.mithrilRotateAmount).rotate();
            return;
        }
        final float rand = MathUtil.randomFloat(3.0f);
        final float pitch = MathUtil.randomFloat(3.0f);
        new Rotater(60.0f + rand, pitch).rotate();
        while (Rotater.rotating) {
            Utils.sleep(1);
        }
        Utils.sleep(50 + Utils.random.nextInt(50));
        new Rotater(-(60.0f + rand), -pitch).rotate();
    }
    
    public void update() {
        this.lastBlockSwitch = System.currentTimeMillis();
        this.fixing = false;
        this.onMove();
    }
    
    static {
        mineables = new HashSet<Block>(Arrays.asList(Blocks.field_150365_q, Blocks.field_150366_p, Blocks.field_150450_ax, Blocks.field_150352_o, Blocks.field_150482_ag, Blocks.field_150348_b, Blocks.field_150369_x, Blocks.field_150412_bA));
    }
}
