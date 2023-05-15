// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.keybinds;

import qolskyblockmod.pizzaclient.util.graphics.font.renderer.FontUtil;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiManager;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiLocation;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiElement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import qolskyblockmod.pizzaclient.features.macros.ai.failsafes.Failsafes;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import net.minecraft.world.World;
import qolskyblockmod.pizzaclient.features.dungeons.AutoSpiritLeap;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.features.macros.mining.dwarven.MithrilMarkers;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import qolskyblockmod.pizzaclient.features.keybinds.auto.MacroKeybind;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.client.settings.KeyBinding;
import qolskyblockmod.pizzaclient.features.player.AutoClicker;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.StringUtils;
import net.minecraft.inventory.Slot;
import qolskyblockmod.pizzaclient.commands.AutoPetCommand;
import qolskyblockmod.pizzaclient.core.events.ChestBackgroundDrawnEvent;
import net.minecraft.block.Block;
import java.util.Set;

public class KeybindFeatures
{
    private long lastTermClick;
    public static final Set<Block> notGhostBlockable;
    
    public KeybindFeatures() {
        new ToggleSprintElement();
    }
    
    @SubscribeEvent
    public void onRenderGui(final ChestBackgroundDrawnEvent event) {
        if (event.displayName.endsWith("Pets") && AutoPetCommand.clickSlot != null && event.slots.get(53).func_75211_c() != null) {
            int clickSlot = 0;
            Label_0128: {
                try {
                    clickSlot = Integer.parseInt(AutoPetCommand.clickSlot);
                }
                catch (Exception e) {
                    Block_10: {
                        for (final Slot slot : event.slots) {
                            final ItemStack stack = slot.func_75211_c();
                            if (stack != null && StringUtils.func_76338_a(stack.func_82833_r()).toLowerCase().contains(AutoPetCommand.clickSlot)) {
                                break Block_10;
                            }
                        }
                        break Label_0128;
                    }
                    final Slot slot;
                    clickSlot = slot.getSlotIndex();
                }
            }
            if (clickSlot == 0) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Found no pet with the name \"" + AutoPetCommand.clickSlot + "\"."));
            }
            else {
                PizzaClient.mc.field_71442_b.func_78753_a(PizzaClient.mc.field_71439_g.field_71070_bA.field_75152_c, clickSlot, 0, 0, (EntityPlayer)PizzaClient.mc.field_71439_g);
            }
            AutoPetCommand.clickSlot = null;
        }
    }
    
    @SubscribeEvent
    public void onInputKey(final InputEvent.KeyInputEvent event) {
        if (PizzaClient.keyBindings[0].func_151468_f()) {
            AutoClicker.toggle();
        }
        else if (PizzaClient.keyBindings[2].func_151468_f()) {
            PizzaClient.config.toggleSprint = !PizzaClient.config.toggleSprint;
            if (PizzaClient.config.toggleSprint) {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_151444_V.func_151463_i(), true);
            }
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "ToggleSprint is now " + Utils.getColouredBoolean(PizzaClient.config.toggleSprint)));
        }
        else if (PizzaClient.keyBindings[3].func_151468_f()) {
            PizzaClient.mc.func_147108_a((GuiScreen)PizzaClient.config.gui());
        }
        else if (PizzaClient.keyBindings[4].func_151468_f()) {
            MacroKeybind.updateToggle();
        }
        else if (PizzaClient.keyBindings[5].func_151468_f()) {
            MacroBuilder.onKey();
        }
        else if (PizzaClient.keyBindings[6].func_151468_f()) {
            MithrilMarkers.onKey();
        }
        else if (PizzaClient.location == Locations.DUNGEON) {
            if (PizzaClient.config.sneakAotv && PlayerUtil.isHoldingAotv() && PizzaClient.mc.field_71474_y.field_74311_E.func_151468_f()) {
                final long lastInteractTime = System.currentTimeMillis();
                PizzaClient.tickTask = (() -> {
                    if (System.currentTimeMillis() - lastInteractTime >= PizzaClient.config.sneakAotvDelay) {
                        KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
                        PizzaClient.tickTask = null;
                    }
                });
                return;
            }
            if (PizzaClient.config.autoLeapDoor && AutoSpiritLeap.leapName != null) {
                final ItemStack held = PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g();
                if (held != null && held.func_82833_r().contains("Spirit Leap") && PizzaClient.mc.field_71474_y.field_74312_F.func_151468_f() && PizzaClient.mc.field_71442_b.func_78769_a((EntityPlayer)PizzaClient.mc.field_71439_g, (World)PizzaClient.mc.field_71441_e, held)) {
                    PizzaClient.mc.field_71460_t.field_78516_c.func_78445_c();
                    AutoSpiritLeap.leapToDoor = true;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (PizzaClient.keyBindings[7].func_151470_d()) {
            AnyItemWithAnything.use();
        }
        if (PizzaClient.mc.field_71462_r == null && TickTimer.ticks % 4 == 0) {
            if (PizzaClient.config.captchaWarning) {
                Failsafes.checkForCaptchas();
            }
            if (PizzaClient.config.toggleSprint) {
                KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_151444_V.func_151463_i(), true);
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (PizzaClient.mc.field_71476_x != null && PizzaClient.keyBindings[1].func_151470_d()) {
            switch (PizzaClient.config.fasterGhostblocks) {
                case 0: {
                    final MovingObjectPosition hit = VecUtil.rayTrace();
                    if (hit != null) {
                        PlayerUtil.ghostBlockIfValid(hit.func_178782_a());
                        break;
                    }
                    break;
                }
                case 1: {
                    PlayerUtil.ghostBlock(6.0f);
                    break;
                }
                case 2: {
                    PlayerUtil.ghostBlockIgnoreInteractables(150.0f);
                    break;
                }
            }
        }
        if (PizzaClient.config.terminatorClicker && PizzaClient.mc.field_71474_y.field_74313_G.func_151470_d()) {
            final ItemStack held = PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g();
            if (held != null) {
                final String displayName = held.func_82833_r();
                if (displayName.contains("Terminator") || displayName.contains("Juju")) {
                    if (this.lastTermClick == 0L) {
                        if (PizzaClient.mc.field_71442_b.func_78769_a((EntityPlayer)PizzaClient.mc.field_71439_g, (World)PizzaClient.mc.field_71441_e, held)) {
                            PizzaClient.mc.field_71460_t.field_78516_c.func_78445_c();
                        }
                        this.lastTermClick = System.currentTimeMillis();
                        return;
                    }
                    if (System.currentTimeMillis() - this.lastTermClick >= PizzaClient.config.terminatorClickerDelay) {
                        if (PizzaClient.mc.field_71442_b.func_78769_a((EntityPlayer)PizzaClient.mc.field_71439_g, (World)PizzaClient.mc.field_71441_e, held)) {
                            PizzaClient.mc.field_71460_t.field_78516_c.func_78445_c();
                        }
                        this.lastTermClick = System.currentTimeMillis() - this.lastTermClick % PizzaClient.config.terminatorClickerDelay;
                    }
                }
            }
        }
    }
    
    static {
        notGhostBlockable = new HashSet<Block>(Arrays.asList(Blocks.field_180410_as, Blocks.field_150467_bQ, (Block)Blocks.field_150461_bJ, Blocks.field_150324_C, Blocks.field_180412_aq, Blocks.field_150382_bo, Blocks.field_150483_bI, Blocks.field_150462_ai, (Block)Blocks.field_150486_ae, Blocks.field_180409_at, (Block)Blocks.field_150453_bW, (Block)Blocks.field_180402_cm, Blocks.field_150367_z, Blocks.field_150409_cd, Blocks.field_150381_bn, Blocks.field_150477_bB, Blocks.field_150460_al, Blocks.field_180411_ar, Blocks.field_150442_at, Blocks.field_150323_B, (Block)Blocks.field_150455_bV, (Block)Blocks.field_150441_bU, (Block)Blocks.field_150416_aS, (Block)Blocks.field_150413_aR, Blocks.field_150472_an, Blocks.field_150444_as, Blocks.field_150415_aT, Blocks.field_150447_bR, Blocks.field_150471_bO, Blocks.field_150430_aB, Blocks.field_180413_ao, (Block)Blocks.field_150355_j, (Block)Blocks.field_150353_l, (Block)Blocks.field_150358_i, (Block)Blocks.field_150356_k));
    }
    
    public static class ToggleSprintElement extends GuiElement
    {
        public ToggleSprintElement() {
            super(new GuiLocation(0.022f, 0.9f));
            GuiManager.registerElement(this);
        }
        
        @Override
        public String getName() {
            return "ToggleSprint";
        }
        
        @Override
        public void render() {
            if (PizzaClient.config.toggleSprint) {
                PizzaClient.mc.field_71466_p.func_175065_a(PizzaClient.config.customToggleSprintName, this.getActualX(), this.getActualY(), PizzaClient.config.customToggleSprintColor.getRGB(), true);
            }
        }
        
        @Override
        public void demoRender() {
            FontUtil.drawString(PizzaClient.config.customToggleSprintName, this.getActualX(), this.getActualY(), PizzaClient.config.customToggleSprintColor.getRGB());
        }
        
        @Override
        public boolean isToggled() {
            return PizzaClient.config.toggleSprint;
        }
        
        @Override
        public int getHeight() {
            return PizzaClient.mc.field_71466_p.field_78288_b;
        }
        
        @Override
        public int getWidth() {
            return PizzaClient.mc.field_71466_p.func_78256_a(PizzaClient.config.customToggleSprintName);
        }
    }
}
