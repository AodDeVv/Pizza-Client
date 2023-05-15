// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.mining.dwarven;

import java.util.Collection;
import java.util.Arrays;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.AOTVMovement;
import java.util.ArrayList;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.Refuel;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.base.PathBase;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.AStarPathfinder;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.path.EntityPath;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import qolskyblockmod.pizzaclient.util.ItemUtil;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.util.Utils;
import net.minecraft.util.StringUtils;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.entity.item.EntityArmorStand;
import java.util.List;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

public class CommissionMacro extends Macro
{
    public static final BlockPos CRUCIBLE;
    private static final List<CommissionLocation> mithrils;
    private CommissionLocation commission;
    private static EntityArmorStand king;
    
    @Override
    public void onTick() {
        if (PizzaClient.location != Locations.DWARVENMINES) {
            return;
        }
        if (CommissionMacro.king == null) {
            findKing();
        }
        if (this.commission == null) {
            this.findCommission(true);
        }
        else if (this.commission.mithril) {
            if (!PizzaClient.config.sneakWhenUsingMacro) {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
            }
            MacroBuilder.macros[2].onTick();
        }
        else if (!this.miscThread.isAlive()) {
            this.huntMobs();
        }
    }
    
    private void findCommission(final boolean mithril) {
        this.terminateIfAlive();
        for (int i = 0; i < 8; ++i) {
            final ItemStack item = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
            if (item != null && StringUtils.func_76338_a(item.func_82833_r()).equals("Royal Pigeon")) {
                final int itemSlot = i;
                final int field_70461_c;
                this.enqueueThread(() -> {
                    if (!mithril) {
                        Utils.sleep(1000);
                        PizzaClient.mc.field_71439_g.func_71165_d("/l");
                        this.commission = null;
                    }
                    else {
                        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = field_70461_c;
                        Utils.sleep(500);
                        while (PizzaClient.mc.field_71462_r == null) {
                            PizzaClient.mc.field_71442_b.func_78769_a((EntityPlayer)PizzaClient.mc.field_71439_g, (World)PizzaClient.mc.field_71441_e, PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g());
                            Utils.sleep(2000);
                        }
                        this.findComm();
                    }
                });
                return;
            }
        }
        if (CommissionMacro.king == null) {
            findKing();
            if (CommissionMacro.king == null) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Found no pigeon nor king. If you want to use the king method, you need to get near a king or an Emissary for every lobby."));
                return;
            }
        }
        this.enqueueThread(() -> {
            if (!mithril) {
                Utils.sleep(1000);
                PizzaClient.mc.field_71439_g.func_71165_d("/l");
                this.commission = null;
                Utils.sleep(3000);
            }
            else {
                while (PizzaClient.mc.field_71462_r == null) {
                    PizzaClient.mc.field_71442_b.func_78768_b((EntityPlayer)PizzaClient.mc.field_71439_g, (Entity)CommissionMacro.king);
                    Utils.sleep(2500);
                }
                this.findComm();
            }
        });
    }
    
    private static void findKing() {
        for (final Entity entity : PizzaClient.mc.field_71441_e.field_72996_f) {
            if (entity instanceof EntityArmorStand) {
                final String name = entity.func_145748_c_().func_150260_c();
                if (name.startsWith("§6§lKing ") || (name.startsWith("§6Emissary ") && !name.contains("Braum"))) {
                    CommissionMacro.king = (EntityArmorStand)entity;
                    break;
                }
                continue;
            }
        }
    }
    
    private void findComm() {
        for (final Slot slot : PizzaClient.mc.field_71439_g.field_71070_bA.field_75151_b) {
            final ItemStack stack = slot.func_75211_c();
            if (stack != null && stack.func_77973_b() == Items.field_151099_bA) {
                for (final String s : ItemUtil.getItemLore(stack)) {
                    final String stripped = StringUtils.func_76338_a(s);
                    if (stripped.contains("COMPLETED")) {
                        PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, slot.field_75222_d, 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                        Utils.sleep(1000);
                        break;
                    }
                }
            }
        }
        CommissionLocation worseComm = null;
        CommissionLocation bestComm = null;
    Label_0308:
        for (final Slot slot2 : PizzaClient.mc.field_71439_g.field_71070_bA.field_75151_b) {
            final ItemStack stack2 = slot2.func_75211_c();
            if (stack2 != null && stack2.func_77973_b() == Items.field_151099_bA) {
                for (final String s2 : ItemUtil.getItemLore(stack2)) {
                    final String stripped2 = StringUtils.func_76338_a(s2);
                    final CommissionLocation comm = CommissionLocation.getBestCommissionFromString(stripped2);
                    if (worseComm != CommissionLocation.ICE_WALKERS) {
                        final CommissionLocation loc = CommissionLocation.getWorseCommissionFromString(stripped2);
                        if (loc != null) {
                            worseComm = loc;
                        }
                    }
                    if (comm != null) {
                        bestComm = comm;
                        break Label_0308;
                    }
                }
            }
        }
        if (bestComm == null) {
            if (worseComm == null) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Failed to find a commission somehow."));
                this.commission = null;
                return;
            }
            bestComm = worseComm;
        }
        PizzaClient.mc.field_71439_g.func_71053_j();
        Utils.sleep(2000);
        if (bestComm != this.commission) {
            PizzaClient.mc.field_71439_g.func_71165_d("/warp forge");
            this.commission = bestComm;
            Utils.sleep(3000);
            this.commission.pathfindToPos();
        }
        Utils.sleep(400);
        MacroBuilder.updatePosition();
        MithrilMacro.miningAi.onMove();
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = this.commission.getTool();
        Utils.sleep(700);
        if (this.commission.mithril && PizzaClient.mc.field_71476_x != null && PizzaClient.mc.field_71476_x.field_72308_g == null) {
            KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
        }
        Utils.sleep(400);
        MithrilMacro.miningAi.update();
    }
    
    private void huntMobs() {
        final EntityPath path = EntityPath.findClosest((Class<? extends Entity>)EntityOtherPlayerMP.class, this.commission.entityName, this.commission.minY, this.commission.maxY);
        if (path != null) {
            this.enqueueThreadIfDead(() -> {
                new AStarPathfinder(path).run(false);
                Utils.sleep(100);
            });
        }
    }
    
    @Override
    public void onChat(final String unformatted) {
        if (unformatted.equals("Mining Speed Boost is now available!") && this.commission != null && this.commission.mithril) {
            PlayerUtil.useAbility();
        }
        else if (unformatted.startsWith("Your ") && unformatted.endsWith("Refuel it by talking to a Drill Mechanic!")) {
            this.enqueueThread(() -> {
                Refuel.legitRefuel();
                PizzaClient.mc.field_71439_g.func_71165_d("/warp forge");
                Utils.sleep(4000);
                this.commission = null;
            });
        }
        else if (unformatted.contains("Commission Complete! Visit the King") && !unformatted.contains(":") && this.commission != null) {
            this.findCommission(this.commission.mithril);
        }
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        MithrilMacro.miningAi.onToggle();
        this.addToggleMessage("Commission Macro");
        this.commission = null;
    }
    
    @Override
    public Locations getLocation() {
        return Locations.DWARVENMINES;
    }
    
    @Override
    public void warpBack() {
        this.commission = null;
        Locations.DWARVENMINES.warpTo();
    }
    
    @Override
    public boolean applyFailsafes() {
        return this.commission != null;
    }
    
    @Override
    public void onDisable() {
        CommissionMacro.king = null;
        this.commission = null;
        MithrilMacro.miningAi.disable();
    }
    
    @Override
    public void onMove() {
        MithrilMacro.miningAi.onMove();
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return this.commission != null && this.commission.mithril;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return this.commission != null && !this.commission.mithril;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return this.commission != null && this.commission.mithril;
    }
    
    static {
        CRUCIBLE = new BlockPos(0, 166, -11);
        mithrils = new ArrayList<CommissionLocation>();
    }
    
    public enum CommissionLocation
    {
        ROYAL_MINES((List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(CommissionMacro.CRUCIBLE, new BlockPos(9, 181, 19), new BlockPos(30, 173, 15), new BlockPos(56, 161, 59), new BlockPos(84, 144, 45), new BlockPos(107, 156, 37), new BlockPos(130, 156, 27))), (List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(new BlockPos(139, 153, 25), new BlockPos(104, 164, 33), new BlockPos(107, 153, 53))), true), 
        CLIFFSIDE_VEINS((List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(CommissionMacro.CRUCIBLE, new BlockPos(9, 181, 19), new BlockPos(30, 173, 15), new BlockPos(56, 161, 59), new BlockPos(30, 127, 42))), (List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(new BlockPos(28, 130, 26), new BlockPos(50, 146, 3), new BlockPos(21, 138, 30))), true), 
        LAVA_SPRINGS((List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(CommissionMacro.CRUCIBLE, new BlockPos(8, 181, 19), new BlockPos(38, 206, 2))), (List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(new BlockPos(47, 216, -29), new BlockPos(74, 212, -28), new BlockPos(71, 218, 6), new BlockPos(53, 205, 16), new BlockPos(80, 221, -13))), true), 
        RAMPART_QUARRY((List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(CommissionMacro.CRUCIBLE, new BlockPos(-24, 179, -27), new BlockPos(-62, 205, -10))), (List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(new BlockPos(-100, 233, -20), new BlockPos(-44, 211, 24), new BlockPos(-48, 222, 21), new BlockPos(-47, 190, 32))), true), 
        UPPER_MINES((List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(CommissionMacro.CRUCIBLE, new BlockPos(-24, 179, -27), new BlockPos(-62, 205, -25), new BlockPos(-74, 222, -50), new BlockPos(-89, 198, -51), new BlockPos(-132, 175, -72), new BlockPos(-130, 171, -12))), (List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(new BlockPos(-146, 170, -31), new BlockPos(-148, 177, -27), new BlockPos(-132, 175, -72))), true), 
        ICE_WALKERS((List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(CommissionMacro.CRUCIBLE, new BlockPos(9, 181, 19), new BlockPos(9, 181, 30), new BlockPos(8, 161, 66), new BlockPos(8, 161, 106), new BlockPos(-5, 127, 137), new BlockPos(-9, 129, 177))), (List<BlockPos>)new ArrayList<BlockPos>(), "Ice Walker", 0.0f, 135.0f), 
        GOBLIN((List<BlockPos>)new ArrayList<BlockPos>(Arrays.asList(CommissionMacro.CRUCIBLE, new BlockPos(9, 181, 19), new BlockPos(9, 181, 30), new BlockPos(8, 161, 66), new BlockPos(8, 161, 106), new BlockPos(-5, 127, 137), new BlockPos(-9, 129, 177), new BlockPos(-39, 130, 156), new BlockPos(-55, 134, 151))), (List<BlockPos>)new ArrayList<BlockPos>(), "Goblin", 125.0f, 255.0f);
        
        public final List<BlockPos> requiredPath;
        public final List<BlockPos> randomPath;
        public final boolean mithril;
        public final String entityName;
        public final float minY;
        public final float maxY;
        
        private CommissionLocation(final List<BlockPos> requiredPath, final List<BlockPos> randomPath, final boolean mithril) {
            this.requiredPath = requiredPath;
            this.randomPath = randomPath;
            this.mithril = mithril;
            if (mithril) {
                CommissionMacro.mithrils.add(this);
            }
            this.entityName = null;
            this.minY = 0.0f;
            this.maxY = 255.0f;
        }
        
        private CommissionLocation(final List<BlockPos> requiredPath, final List<BlockPos> randomPath, final String entityName, final float minY, final float maxY) {
            this.requiredPath = requiredPath;
            this.randomPath = randomPath;
            this.mithril = false;
            this.entityName = entityName;
            this.minY = minY;
            this.maxY = maxY;
        }
        
        public static CommissionLocation getBestCommissionFromString(final String s) {
            if (s.contains("Upper Mines")) {
                return CommissionLocation.UPPER_MINES;
            }
            if (s.contains("Mine 500 Mithril Ore.")) {
                return getRandom();
            }
            if (s.contains("Lava Springs")) {
                return CommissionLocation.LAVA_SPRINGS;
            }
            if (s.contains("Cliffside Veins")) {
                return CommissionLocation.CLIFFSIDE_VEINS;
            }
            if (s.contains("Rampart's Quarry")) {
                return CommissionLocation.RAMPART_QUARRY;
            }
            if (s.contains("Royal Mines")) {
                return CommissionLocation.ROYAL_MINES;
            }
            if (s.contains("Mine 15 Titanium Ore.")) {
                return getRandom();
            }
            return null;
        }
        
        public static CommissionLocation getWorseCommissionFromString(final String s) {
            if (s.contains("Ice Walker")) {
                return CommissionLocation.ICE_WALKERS;
            }
            if (s.contains("Goblin")) {
                return CommissionLocation.GOBLIN;
            }
            return null;
        }
        
        public static CommissionLocation getRandom() {
            return CommissionMacro.mithrils.get(Utils.random.nextInt(CommissionMacro.mithrils.size()));
        }
        
        public void pathfindToPos() {
            AOTVMovement.tpToRandom(this.requiredPath, this.randomPath, () -> {
                if (Refuel.drillNPC == null) {
                    Refuel.findDrillNPC();
                }
                if (CommissionMacro.king == null) {
                    findKing();
                }
            });
        }
        
        public int getTool() {
            switch (this) {
                case GOBLIN: {
                    return PlayerUtil.checkHotBarForEtherwarp();
                }
                case ICE_WALKERS: {
                    final int pickaxe = PlayerUtil.getPickaxe();
                    return (pickaxe == -1) ? PlayerUtil.checkHotBarForEtherwarp() : pickaxe;
                }
                default: {
                    return PlayerUtil.getMiningTool();
                }
            }
        }
    }
}
