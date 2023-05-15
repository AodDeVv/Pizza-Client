// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient;

import com.google.gson.GsonBuilder;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.handler.Blacklist;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import net.minecraft.client.gui.GuiIngameMenu;
import qolskyblockmod.pizzaclient.gui.UpdateGui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.pathfinder.finder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.ai.mining.Refuel;
import qolskyblockmod.pizzaclient.util.PacketUtil;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraft.item.ItemStack;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import qolskyblockmod.pizzaclient.features.player.VelocityHook;
import net.minecraftforge.fml.common.eventhandler.Event;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.util.misc.timer.TickTimer;
import qolskyblockmod.pizzaclient.commands.KuudraAuraCommand;
import qolskyblockmod.pizzaclient.commands.P4BlockCommand;
import qolskyblockmod.pizzaclient.commands.FuckCPUCommand;
import qolskyblockmod.pizzaclient.commands.SetYawCommand;
import qolskyblockmod.pizzaclient.commands.AutoPetCommand;
import qolskyblockmod.pizzaclient.commands.AotvTestCommand;
import qolskyblockmod.pizzaclient.commands.PathfindCommand;
import qolskyblockmod.pizzaclient.commands.ArabFunnyCommand;
import qolskyblockmod.pizzaclient.commands.BlockAbilityCommand;
import qolskyblockmod.pizzaclient.commands.ItemMacroCommand;
import qolskyblockmod.pizzaclient.commands.ChatSpammerCommand;
import qolskyblockmod.pizzaclient.commands.SilencerCommand;
import net.minecraft.command.ICommand;
import qolskyblockmod.pizzaclient.commands.PizzaClientGuiCommand;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import qolskyblockmod.pizzaclient.features.macros.mining.dwarven.MithrilMarkers;
import qolskyblockmod.pizzaclient.features.keybinds.auto.MacroKeybind;
import qolskyblockmod.pizzaclient.features.player.AutoClicker;
import qolskyblockmod.pizzaclient.features.player.BurrowAura;
import qolskyblockmod.pizzaclient.features.dungeons.SuperboomAura;
import qolskyblockmod.pizzaclient.features.player.AutoJerryBox;
import qolskyblockmod.pizzaclient.features.dungeons.QuizAura;
import qolskyblockmod.pizzaclient.features.dungeons.IceFillQol;
import qolskyblockmod.pizzaclient.features.misc.MonolithESP;
import qolskyblockmod.pizzaclient.features.player.HarpSolver;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import qolskyblockmod.pizzaclient.features.player.AutoPowderChest;
import qolskyblockmod.pizzaclient.features.dungeons.f7.FunnyDevice;
import qolskyblockmod.pizzaclient.features.dungeons.ChestESP;
import qolskyblockmod.pizzaclient.features.keybinds.KeybindFeatures;
import qolskyblockmod.pizzaclient.util.SBInfo;
import qolskyblockmod.pizzaclient.features.slayers.SlayerFeatures;
import qolskyblockmod.pizzaclient.features.skills.WorldScanner;
import qolskyblockmod.pizzaclient.features.skills.FunnyEnchanting;
import qolskyblockmod.pizzaclient.features.dungeons.f7.FunnyTerminals;
import qolskyblockmod.pizzaclient.features.skills.MiningFeatures;
import qolskyblockmod.pizzaclient.features.dungeons.DungeonFeatures;
import qolskyblockmod.pizzaclient.features.misc.GuiFeatures;
import qolskyblockmod.pizzaclient.listeners.ChatListener;
import qolskyblockmod.pizzaclient.features.misc.BlockAbility;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;
import qolskyblockmod.pizzaclient.util.handler.DataLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.IRotater;
import com.google.gson.JsonObject;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.util.misc.runnables.TickTask;
import qolskyblockmod.pizzaclient.core.config.Config;
import java.io.File;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.Minecraft;
import com.google.gson.Gson;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Mod;

@Mod(name = "Pizza Client", modid = "pizzaclient", version = "1.1.3", acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true)
@SideOnly(Side.CLIENT)
public class PizzaClient
{
    public static final String MODID = "pizzaclient";
    public static final String VERSION = "1.1.3";
    public static final String NAME = "Pizza Client";
    public static final Gson gson;
    public static final Minecraft mc;
    public static final String username;
    public static final KeyBinding[] keyBindings;
    public static final String modMessage = "PizzaClient > ";
    public static final File modDir;
    public static Config config;
    public static TickTask tickTask;
    public static Locations location;
    public static JsonObject response;
    public static IRotater rotater;
    private static boolean joinSkyblock;
    private static long lastWorldChange;
    
    public static void onStartGame() {
        if (!PizzaClient.modDir.exists()) {
            PizzaClient.modDir.mkdirs();
        }
        PizzaClient.config = new Config();
        PizzaClient.mc.func_110432_I().func_148254_d();
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        DataLoader.fetch();
        Display.setTitle("Pizza Supporter");
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        register(new GuiManager());
        register(this);
        register(new BlockAbility());
        register(new ChatListener());
        register(new GuiFeatures());
        register(new DungeonFeatures());
        register(new MiningFeatures());
        register(new FunnyTerminals());
        register(new FunnyEnchanting());
        register(new WorldScanner());
        register(new SlayerFeatures());
        register(new SBInfo());
        register(new KeybindFeatures());
        register(new ChestESP());
        register(new FunnyDevice());
        register(new AutoPowderChest());
        register(new MacroBuilder());
        register(new HarpSolver());
        register(new MonolithESP());
        register(new IceFillQol());
        register(new QuizAura());
        register(new AutoJerryBox());
        register(new SuperboomAura());
        register(new BurrowAura());
        register(new AutoClicker());
        register(new MacroKeybind());
        new MithrilMarkers();
        PizzaClient.keyBindings[0] = new KeyBinding("AutoClicker", 45, "Pizza Client");
        PizzaClient.keyBindings[1] = new KeyBinding("Ghost Blocks", 34, "Pizza Client");
        PizzaClient.keyBindings[2] = new KeyBinding("ToggleSprint", 23, "Pizza Client");
        PizzaClient.keyBindings[3] = new KeyBinding("Quick Open Config", 54, "Pizza Client");
        PizzaClient.keyBindings[4] = new KeyBinding("Auto Item Key Toggle", 35, "Pizza Client");
        PizzaClient.keyBindings[5] = new KeyBinding("Macro Key", 33, "Pizza Client");
        PizzaClient.keyBindings[6] = new KeyBinding("Mithril Markers", 50, "Pizza Client");
        PizzaClient.keyBindings[7] = new KeyBinding("Any Item With Anything", 19, "Pizza Client");
        for (final KeyBinding key : PizzaClient.keyBindings) {
            ClientRegistry.registerKeyBinding(key);
        }
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        registerCommand((ICommand)new PizzaClientGuiCommand());
        registerCommand((ICommand)new SilencerCommand());
        registerCommand((ICommand)new ChatSpammerCommand());
        registerCommand((ICommand)new ItemMacroCommand());
        registerCommand((ICommand)new BlockAbilityCommand());
        registerCommand((ICommand)new ArabFunnyCommand());
        registerCommand((ICommand)new PathfindCommand());
        registerCommand((ICommand)new AotvTestCommand());
        registerCommand((ICommand)new AutoPetCommand());
        registerCommand((ICommand)new SetYawCommand());
        registerCommand((ICommand)new FuckCPUCommand());
        registerCommand((ICommand)new P4BlockCommand());
        registerCommand((ICommand)new KuudraAuraCommand());
    }
    
    public static void onTick() {
        if (PizzaClient.tickTask != null) {
            PizzaClient.tickTask.onTick();
        }
        if (PizzaClient.mc.field_71441_e == null) {
            return;
        }
        TickTimer.increment();
        MinecraftForge.EVENT_BUS.post((Event)new TickStartEvent());
        VelocityHook.onTick();
        if (PizzaClient.mc.field_71462_r == null) {
            if (Rotater.rotating && PizzaClient.rotater == null) {
                Rotater.rotating = false;
            }
            final ItemStack held = PizzaClient.mc.field_71439_g.field_71071_by.func_70448_g();
            if (held != null) {
                final String displayName = held.func_82833_r();
                if (PizzaClient.config.autoJerryBox) {
                    if (displayName.contains("Jerry Box")) {
                        AutoJerryBox.useJerryBox();
                        return;
                    }
                    if (PizzaClient.config.autoJerryBoxSwapSlot) {
                        AutoJerryBox.swapToBox();
                    }
                }
                if (PizzaClient.config.autoSkyblock && PizzaClient.joinSkyblock && displayName.equals("§aGame Menu §7(Right Click)")) {
                    if (!MacroBuilder.toggled) {
                        Utils.sleep(400);
                    }
                    PizzaClient.joinSkyblock = false;
                    PizzaClient.mc.field_71439_g.func_71165_d("/play sb");
                    return;
                }
                if (PizzaClient.config.autoSoulcry) {
                    SlayerFeatures.useSoulCry();
                }
            }
            else if (PizzaClient.config.autoJerryBox && PizzaClient.config.autoJerryBoxSwapSlot) {
                AutoJerryBox.swapToBox();
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        if (System.currentTimeMillis() - PizzaClient.lastWorldChange >= 1400L) {
            PizzaClient.lastWorldChange = System.currentTimeMillis();
            MinecraftForge.EVENT_BUS.post((Event)new WorldChangeEvent());
            DataLoader.fetch();
            PizzaClient.location = Locations.NULL;
            PacketUtil.stopPackets = false;
            ChestESP.clickedBlocks.clear();
            PizzaClient.joinSkyblock = true;
            Refuel.drillNPC = null;
            BasePathfinder.path = null;
            GuiFeatures.clicked = false;
            PizzaClient.config.autoMacroKeyToggle = false;
            P4BlockCommand.emeraldPos = null;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onOpenGui(final GuiOpenEvent event) {
        if (event.gui instanceof GuiMainMenu) {
            if (!UpdateGui.shownGui) {
                UpdateGui.checkForUpdates();
            }
            return;
        }
        if (!(event.gui instanceof GuiIngameMenu) && FakeRotater.rotater != null && PizzaClient.mc.field_71439_g != null) {
            FakeRotater.rotater.onOpenGui();
            FakeRotater.rotater = null;
        }
    }
    
    public static void displayScreen(final GuiScreen screen) {
        PizzaClient.tickTask = (() -> {
            PizzaClient.mc.func_147108_a(screen);
            PizzaClient.tickTask = null;
        });
    }
    
    public static void loadResponse() {
        PizzaClient.response = Utils.getJson("https://gist.githubusercontent.com/PizzaboiBestLegit/e86e41c230949e51309c621548be55aa/raw/pizzaclient").getAsJsonObject();
        Blacklist.shittersOut();
    }
    
    public static void delayAddChatMessage(final String text) {
        PizzaClient.tickTask = (() -> {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(text));
            PizzaClient.tickTask = null;
        });
    }
    
    public static void delayAddChatMessage(final ChatComponentText text) {
        PizzaClient.tickTask = (() -> {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)text);
            PizzaClient.tickTask = null;
        });
    }
    
    public static void addChatMessage(final String text) {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(text));
    }
    
    public static void addChatMessage(final ChatComponentText text) {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)text);
    }
    
    public static void addModMessage(final String text) {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + text));
    }
    
    private static void register(final Object target) {
        MinecraftForge.EVENT_BUS.register(target);
    }
    
    private static void registerCommand(final ICommand command) {
        ClientCommandHandler.instance.func_71560_a(command);
    }
    
    static {
        gson = new GsonBuilder().setPrettyPrinting().create();
        mc = Minecraft.func_71410_x();
        username = PizzaClient.mc.func_110432_I().func_111285_a();
        keyBindings = new KeyBinding[8];
        modDir = new File(new File(PizzaClient.mc.field_71412_D, "config"), "pizzaclient");
        PizzaClient.location = Locations.NULL;
    }
}
