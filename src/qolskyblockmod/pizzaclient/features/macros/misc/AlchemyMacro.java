// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.misc;

import qolskyblockmod.pizzaclient.util.misc.Locations;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.item.Item;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

public class AlchemyMacro extends Macro
{
    private boolean clickedBrewingStand;
    private long lastInteractTime;
    private int nextDelay;
    private int phase;
    
    @Override
    public void onTick() {
        if (this.lastInteractTime != 0L && System.currentTimeMillis() - this.lastInteractTime >= 15000L) {
            PizzaClient.mc.field_71439_g.func_71053_j();
            this.phase = 0;
            this.clickedBrewingStand = false;
            this.updateValues();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Detected the macro to have stopped, re-starting now!"));
            return;
        }
        if (PizzaClient.mc.field_71462_r == null) {
            PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = 8;
            if (PizzaClient.mc.field_71476_x == null || Rotater.rotating) {
                return;
            }
            if (!this.facingBrewingStand()) {
                this.clickedBrewingStand = false;
                this.rotateToBrewingStand();
            }
            else if (!this.clickedBrewingStand) {
                KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                this.lastInteractTime = System.currentTimeMillis() + 500L;
                this.nextDelay = Utils.random.nextInt(100);
                this.clickedBrewingStand = true;
            }
        }
        else {
            Movement.disableMovement();
            PizzaClient.rotater = null;
            if (PlayerUtil.isInventoryFull() || this.phase == 4) {
                switch (this.phase) {
                    case 0: {
                        if (System.currentTimeMillis() - this.lastInteractTime >= 600 + this.nextDelay) {
                            final EntityPlayerSP field_71439_g;
                            final ChatComponentText chatComponentText;
                            PizzaClient.tickTask = (() -> {
                                field_71439_g = PizzaClient.mc.field_71439_g;
                                new ChatComponentText(Utils.SUCCESS_MESSAGE + "Emptying inventory...");
                                field_71439_g.func_145747_a((IChatComponent)chatComponentText);
                                PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, 89, 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                this.updateValues(150);
                                PizzaClient.tickTask = null;
                                this.phase = 1;
                            });
                        }
                    }
                    case 1: {
                        if (System.currentTimeMillis() - this.lastInteractTime >= 300 + this.nextDelay && PizzaClient.mc.field_71462_r instanceof GuiContainer) {
                            final ItemStack stack = ((GuiContainer)PizzaClient.mc.field_71462_r).field_147002_h.field_75151_b.get(22).func_75211_c();
                            if (stack != null) {
                                this.updateValues(300);
                                this.phase = 2;
                            }
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (System.currentTimeMillis() - this.lastInteractTime >= 400 + this.nextDelay) {
                            PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
                            this.phase = 3;
                            this.updateValues();
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (PizzaClient.mc.field_71462_r instanceof GuiContainer && Utils.getContainerNameTrimmed().equals("Trades")) {
                            this.updateValues();
                            List slots;
                            int i;
                            Slot slot;
                            ItemStack stack2;
                            Item item;
                            EntityPlayerSP field_71439_g2;
                            final ChatComponentText chatComponentText2;
                            new Thread(() -> {
                                try {
                                    Utils.sleep(500 + Utils.random.nextInt(200));
                                    for (slots = PizzaClient.mc.field_71439_g.field_71070_bA.field_75151_b, i = 53; i < slots.size(); ++i) {
                                        this.lastInteractTime = System.currentTimeMillis();
                                        slot = slots.get(i);
                                        stack2 = slots.get(i).func_75211_c();
                                        if (stack2 == null) {
                                            Utils.sleep(10 + Utils.random.nextInt(5));
                                        }
                                        else {
                                            item = stack2.func_77973_b();
                                            if (item != Items.field_151068_bn && item != Items.field_151069_bo) {
                                                Utils.sleep(10 + Utils.random.nextInt(5));
                                            }
                                            else {
                                                Utils.sleep(PizzaClient.config.alchemyMacroDelay + Utils.random.nextInt(110));
                                                PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, slot.field_75222_d, 0, 1, (EntityPlayer)PizzaClient.mc.field_71439_g);
                                            }
                                        }
                                    }
                                    Utils.sleep(250 + Utils.random.nextInt(100));
                                    PizzaClient.mc.field_71439_g.func_71053_j();
                                    Utils.sleep(300 + Utils.random.nextInt(100));
                                    this.phase = 0;
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                    field_71439_g2 = PizzaClient.mc.field_71439_g;
                                    new ChatComponentText(Utils.ERROR_MESSAGE + "Caught an exception with the alchemy macro. Please report this.");
                                    field_71439_g2.func_145747_a((IChatComponent)chatComponentText2);
                                }
                                return;
                            }).start();
                            this.phase = 4;
                            break;
                        }
                        break;
                    }
                }
                return;
            }
            this.phase = 0;
            this.clickedBrewingStand = false;
            if (!(PizzaClient.mc.field_71462_r instanceof GuiContainer)) {
                PizzaClient.mc.field_71439_g.func_71053_j();
                return;
            }
            if (PizzaClient.mc.field_71462_r instanceof GuiInventory) {
                return;
            }
            if (System.currentTimeMillis() - this.lastInteractTime >= PizzaClient.config.alchemyMacroDelay + this.nextDelay) {
                for (final int j : new int[] { 38, 40, 42 }) {
                    final ItemStack stack3 = ((GuiChest)PizzaClient.mc.field_71462_r).field_147002_h.field_75151_b.get(j).func_75211_c();
                    if (stack3 != null) {
                        PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, j, 0, 1, (EntityPlayer)PizzaClient.mc.field_71439_g);
                        this.updateValues();
                        break;
                    }
                }
            }
        }
    }
    
    private void rotateToBrewingStand() {
        final Vec3 stand = this.getBrewingStand();
        if (stand == null) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Found no brewing stand."));
            return;
        }
        new Rotater(stand).setRotationAmount(17).addRandomRotateAmount().rotate();
    }
    
    private Vec3 getBrewingStand() {
        for (final BlockPos pos : BlockPos.func_177980_a(new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t - 4.0, PizzaClient.mc.field_71439_g.field_70163_u - 2.0, PizzaClient.mc.field_71439_g.field_70161_v - 4.0), new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t + 4.0, PizzaClient.mc.field_71439_g.field_70163_u + 2.0, PizzaClient.mc.field_71439_g.field_70161_v + 4.0))) {
            if (PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() == Blocks.field_150382_bo) {
                final Vec3 hittable = VecUtil.getClosestHittableToMiddle(pos);
                if (hittable != null) {
                    return hittable;
                }
                continue;
            }
        }
        return null;
    }
    
    private void updateValues() {
        this.nextDelay = Utils.random.nextInt(110);
        this.lastInteractTime = System.currentTimeMillis();
    }
    
    private void updateValues(final int randomness) {
        this.nextDelay = Utils.random.nextInt(randomness);
        this.lastInteractTime = System.currentTimeMillis();
    }
    
    private boolean facingBrewingStand() {
        return PizzaClient.mc.field_71476_x != null && PizzaClient.mc.field_71476_x.func_178782_a() != null && PizzaClient.mc.field_71441_e.func_180495_p(PizzaClient.mc.field_71476_x.func_178782_a()).func_177230_c() == Blocks.field_150382_bo;
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        this.addToggleMessage("Alchemy Macro");
        if (toggled) {
            PlayerUtil.setSpawnPoint();
        }
        this.clickedBrewingStand = false;
        this.phase = 0;
    }
    
    @Override
    public void warpBack() {
        Locations.PRIVATEISLAND.warpTo();
        this.clickedBrewingStand = false;
        this.phase = 0;
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
        return false;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return false;
    }
    
    @Override
    public boolean acceptGui() {
        return true;
    }
    
    @Override
    public Locations getLocation() {
        return Locations.PRIVATEISLAND;
    }
}
