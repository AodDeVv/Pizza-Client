// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.core.config;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.PizzaClient;
import gg.essential.vigilance.data.SortingBehavior;
import gg.essential.vigilance.data.PropertyCollector;
import gg.essential.vigilance.data.JVMAnnotationPropertyCollector;
import java.io.File;
import java.awt.Color;
import gg.essential.vigilance.data.PropertyType;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.Vigilant;

public class Config extends Vigilant
{
    private final String WIP = "§b[WIP] §f";
    @Property(type = PropertyType.SWITCH, name = "Funny Terminals", description = "Haha, Terminals go brrrrr", category = "F7 Boss", subcategory = "Terminals", searchTags = { "Auto Terminals", "Funny Terminals", "Terminal", "0 Sleep", "Stop Sending Click Packets Index" })
    public boolean funnyTerminals;
    @Property(type = PropertyType.SELECTOR, name = "Funny Terminals Click", description = "Change the click used on terminals.", category = "F7 Boss", subcategory = "Terminals", options = { "Left Click", "Right Click", "Middle Click" })
    public int terminalClickMode;
    @Property(type = PropertyType.SLIDER, name = "Terminal Sleepamount", category = "F7 Boss", subcategory = "Terminals", max = 250, min = 100)
    public int terminalSleepAmount;
    @Property(type = PropertyType.SLIDER, name = "Terminal Fix Time", description = "Change the time required to fix terminals. If it's too low, the terminals will be slower.", category = "F7 Boss", subcategory = "Terminals", min = 200, max = 500)
    public int terminalFixTime;
    @Property(type = PropertyType.SWITCH, name = "Auto Aligment Device", description = "Automatically clicks the amounts required on the alignment device.", category = "F7 Boss", subcategory = "Device")
    public boolean autoAlignment;
    @Property(type = PropertyType.SWITCH, name = "Auto Simon Says", description = "Automatically completes simon says.", category = "F7 Boss", subcategory = "Device")
    public boolean autoSimonSays;
    @Property(type = PropertyType.DECIMAL_SLIDER, name = "Auto Simon Says Range", description = "Change the range of the clicks.", category = "F7 Boss", subcategory = "Device", minF = 4.5f, maxF = 5.5f, decimalPlaces = 2)
    public float autoSimonSaysRange;
    @Property(type = PropertyType.SLIDER, name = "Auto Simon Says Delay", description = "Change the delay between each click (in ms).", category = "F7 Boss", subcategory = "Device", min = 150, max = 300)
    public int autoSimonSaysDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Aiming Device (4 Device)", description = "Automatically aims to the emerald block and left clicks.", category = "F7 Boss", subcategory = "Device")
    public boolean autoAimingDevice;
    @Property(type = PropertyType.SWITCH, name = "Clip Ghostblocks", description = "Creates clip ghostblocks.", category = "F7 Boss", subcategory = "Other")
    public boolean autoClipGhostblocks;
    @Property(type = PropertyType.SWITCH, name = "Spirit Bear Aura", description = "Once a spirit bear spawns, Rotates to the bear server side and left clicks afterwards", category = "Dungeons", subcategory = "Aura", searchTags = { "Bear Aura" })
    public boolean spiritBearAura;
    @Property(type = PropertyType.SWITCH, name = "Spirit Bear Aura Always Aim", description = "Always aim at the spirit bear server side instead of only when it spawns.", category = "Dungeons", subcategory = "Aura", searchTags = { "Bear Aura" })
    public boolean alwaysAimAtSpiritBear;
    @Property(type = PropertyType.SWITCH, name = "Livid Aura", description = "Always face the correct livid server side.", category = "Dungeons", subcategory = "Aura")
    public boolean lividAura;
    @Property(type = PropertyType.SWITCH, name = "§b[WIP] §fSuperboom Aura", description = "Automatically place super/infiniboom on cracked walls (not on crypts yet).", category = "Dungeons", subcategory = "Aura")
    public boolean superboomAura;
    @Property(type = PropertyType.SWITCH, name = "Quiz Aura", description = "Works for inf range once all armor stands were loaded once. If this doesn't work, make sure that no mod is removing the names of the armor stands.", category = "Dungeons", subcategory = "Aura", searchTags = { "NecronIsBad Aura" })
    public boolean quizAura;
    @Property(type = PropertyType.SWITCH, name = "Auto Close Chests", description = "Auto close chests in dungeon.", category = "Dungeons", subcategory = "Macros")
    public boolean autoCloseDungChest;
    @Property(type = PropertyType.SWITCH, name = "Auto Open Free Chest", description = "0 dt!!1!11!", category = "Dungeons", subcategory = "Macros")
    public boolean autoOpenFreeChest;
    @Property(type = PropertyType.SWITCH, name = "Auto Aotv When Sneak", category = "Dungeons", subcategory = "Macros")
    public boolean sneakAotv;
    @Property(type = PropertyType.SLIDER, name = "Auto Aotv When Sneak Delay", description = "Useful for higher ping", category = "Dungeons", subcategory = "Macros", min = 50, max = 400)
    public int sneakAotvDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Ready", description = "(not blatant).", category = "Dungeons", subcategory = "Macros")
    public boolean autoReady;
    @Property(type = PropertyType.SWITCH, name = "Auto Leap To Door Opener", description = "Auto leaps to door opener when left clicking spirit leap.", category = "Dungeons", subcategory = "Macros")
    public boolean autoLeapDoor;
    @Property(type = PropertyType.SWITCH, name = "Terminator Autoclicker", description = "Spams right click when terminator is held and right click is pressed (delay per click is changeable). Also works for juju.", category = "Dungeons", subcategory = "Macros")
    public boolean terminatorClicker;
    @Property(type = PropertyType.SLIDER, name = "Terminator Autoclicker Delay", description = "Change the time until clicking terminator again.", category = "Dungeons", subcategory = "Macros", min = 10, max = 75)
    public int terminatorClickerDelay;
    @Property(type = PropertyType.SWITCH, name = "Qol Ice Fill", description = "§b[WIP] §fMakes ice fill never break and faster. Don't go too fast. Sucks for 200+ ping.", category = "Dungeons", subcategory = "Macros")
    public boolean qolIceFill;
    @Property(type = PropertyType.SWITCH, name = "Blood Trigger Bot", description = "Auto left clicks once you're facing a blood mob", category = "Dungeons", subcategory = "Macros")
    public boolean bloodTriggerBot;
    @Property(type = PropertyType.SWITCH, name = "Blood Aimbot", description = "Auto rotate to a blood mob and left click.", category = "Dungeons", subcategory = "Macros")
    public boolean bloodTriggerBotAimbot;
    @Property(type = PropertyType.SWITCH, name = "Bat Esp", category = "Dungeons", subcategory = "Esp")
    public boolean batEsp;
    @Property(type = PropertyType.SWITCH, name = "Correct Livid Esp", description = "Finds correct livid and gives it an colored esp.", category = "Dungeons", subcategory = "Esp")
    public boolean lividEsp;
    @Property(type = PropertyType.SWITCH, name = "Dungeon Key Esp", description = "Gives dungeon keys (wither keys and blood keys) an esp.", category = "Dungeons", subcategory = "Esp")
    public boolean dungeonKeyEsp;
    @Property(type = PropertyType.SWITCH, name = "Starred Mobs Esp", category = "Dungeons", subcategory = "Esp")
    public boolean starredMobsEsp;
    @Property(type = PropertyType.SELECTOR, name = "Starred Mobs Esp Mode", description = "Change the mode of starred mob esp.", category = "Dungeons", subcategory = "Esp", options = { "Box", "Miniboss Glow", "All Glow" })
    public int starredMobsEspMode;
    @Property(type = PropertyType.SLIDER, name = "Starred Mobs Esp Range", description = "Change the range.", category = "Dungeons", subcategory = "Esp", min = 8, max = 64)
    public int starredMobsEspRange;
    @Property(type = PropertyType.COLOR, name = "Starred Mobs Esp Color", category = "Dungeons", subcategory = "Esp")
    public Color starredMobsEspColor;
    @Property(type = PropertyType.SWITCH, name = "Useless Names Hider", description = "Hides the name of superboom, revive stones and blessings.", category = "Dungeons", subcategory = "Other")
    public boolean hideSuperboom;
    @Property(type = PropertyType.SWITCH, name = "Bigger Secret Blocks", description = "Increase the size of lever, chests and skulls to 1 full block.", category = "Dungeons", subcategory = "Other")
    public boolean biggerSecretBlocks;
    @Property(type = PropertyType.SWITCH, name = "Ignore Carpet Bounding Box", description = "Useful to help prevent lagbacks.", category = "Dungeons", subcategory = "Other")
    public boolean removeCarpets;
    @Property(type = PropertyType.SWITCH, name = "Show Hidden Mobs", description = "Shows hidden fels and shadow assassins.", category = "Dungeons", subcategory = "Other")
    public boolean showHiddenMobs;
    @Property(type = PropertyType.SELECTOR, name = "Ghostblocks", description = "Change the speed and range of ghost blocks", category = "Dungeons", subcategory = "Other", options = { "Normal", "Instant w/ 6 Reach", "BEST MINER" })
    public int fasterGhostblocks;
    @Property(type = PropertyType.SWITCH, name = "Auto Activate Soulcry", description = "Works when a voidgloom spawned.", category = "Slayer", subcategory = "Macros")
    public boolean autoSoulcry;
    @Property(type = PropertyType.SWITCH, name = "Auto Swap Blaze Dagger", description = "Automatically swap to the correct blaze dagger.", category = "Slayer", subcategory = "Macros")
    public boolean autoBlazeDagger;
    @Property(type = PropertyType.SWITCH, name = "Miniboss Esp", description = "Gives all slayer minibosses an esp.", category = "Slayer", subcategory = "Esp")
    public boolean minibossEsp;
    @Property(type = PropertyType.SWITCH, name = "Slayer Boss Esp", description = "Gives all slayer bosses but endermen an esp. (good for boss trading)", category = "Slayer", subcategory = "Esp")
    public boolean bossEsp;
    @Property(type = PropertyType.SWITCH, name = "Dark Monolith Esp", description = "Monoliths are the dragon eggs in dwarven mines", category = "Mining", subcategory = "Esp")
    public boolean monolithEsp;
    @Property(type = PropertyType.SWITCH, name = "Bal Esp", description = "Gives Bal an esp and a notification. Short detection range.", category = "Mining", subcategory = "Esp")
    public boolean balEsp;
    @Property(type = PropertyType.SWITCH, name = "Corleone Esp", description = "Gives Corleone a ping when detected. To get an esp, enable \"Team Treasurite Esp\".", category = "Mining", subcategory = "CommissionEsp")
    public boolean corleonePing;
    @Property(type = PropertyType.SWITCH, name = "Team Tresurite Esp", description = "Gives all of Team Tresurite an esp (including corleone).", category = "Mining", subcategory = "CommissionEsp")
    public boolean teamTresuriteEsp;
    @Property(type = PropertyType.SWITCH, name = "Automaton Esp", category = "Mining", subcategory = "CommissionEsp")
    public boolean ironGolemEsp;
    @Property(type = PropertyType.SWITCH, name = "Goblin Esp", category = "Mining", subcategory = "CommissionEsp")
    public boolean goblinEsp;
    @Property(type = PropertyType.SWITCH, name = "Sludge Esp", description = "Gives Sludges an esp. Applies to all slimes in crystal hollows.", category = "Mining", subcategory = "CommissionEsp")
    public boolean sludgeEsp;
    @Property(type = PropertyType.SWITCH, name = "Yog Esp", description = "Gives Yogs an esp. Applies to all magma cubes in crystal hollows.", category = "Mining", subcategory = "CommissionEsp")
    public boolean yogEsp;
    @Property(type = PropertyType.SWITCH, name = "Gemstone ESP", description = "Scans for gemstones while in crystal hollows.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean gemstoneScanner;
    @Property(type = PropertyType.SLIDER, name = "World Scanner Width", description = "Change the range the world scanner can go.", category = "Mining", subcategory = "GemstoneEsp", min = 16, max = 128)
    public int scannerWidth;
    @Property(type = PropertyType.SLIDER, name = "World Scanner Delay", description = "Sets the world scanner delay (in seconds). Default is 5.", category = "Mining", subcategory = "GemstoneEsp", min = 1, max = 15)
    public int scannerDelay;
    @Property(type = PropertyType.SLIDER, name = "Gemstone Esp Size", description = "Change the outline size of the gemstone Esp", category = "Mining", subcategory = "GemstoneEsp", max = 100, min = 1)
    public int gemstoneEspSize;
    @Property(type = PropertyType.SWITCH, name = "Ruby Esp", description = "Gives Ruby an esp.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean redGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Amber Esp", description = "Gives Amber an esp.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean orangeGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Sapphire Esp", description = "Gives Sapphire an esp.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean lightBlueGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Jade Esp", description = "Gives Jade an esp.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean limeGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Amethyst Esp", description = "Gives Amethyst an esp.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean purpleGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Topaz Esp", description = "Gives Topaz an esp.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean yellowGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Jasper Esp", description = "Gives Jasper an esp.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean pinkGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Glass Panes Esp", description = "Gives glass panes an esp too.", category = "Mining", subcategory = "GemstoneEsp")
    public boolean glassPanesEsp;
    @Property(type = PropertyType.SWITCH, name = "Hide Golden Goblins", description = "Removes all golden goblin entities.", category = "Mining", subcategory = "Misc")
    public boolean hideGoldenGoblin;
    @Property(type = PropertyType.SWITCH, name = "Special Coords List", description = "Note down the special coords on a list.", category = "Mining", subcategory = "Other")
    public boolean coordsList;
    @Property(type = PropertyType.SWITCH, name = "Auto Close Loot Chests", description = "Automatically Close Loot Chests.", category = "Mining", subcategory = "Other")
    public boolean autoCloseLootChest;
    @Property(type = PropertyType.SWITCH, name = "Powder Chest Macro", description = "Auto completes powder chests. Rotates server side only.", category = "Mining", subcategory = "Other")
    public boolean powderChestMacro;
    @Property(type = PropertyType.SWITCH, name = "Blaze Esp", description = "Gives all blazes an esp.", category = "Nether", subcategory = "Slayer")
    public boolean blazeEsp;
    @Property(type = PropertyType.COLOR, name = "Blaze Esp Color", category = "Nether", subcategory = "Slayer")
    public Color blazeEspColor;
    @Property(type = PropertyType.SWITCH, name = "Opal Esp", description = "Gives Opal an esp.", category = "Nether", subcategory = "Mining")
    public boolean whiteGlassEsp;
    @Property(type = PropertyType.SWITCH, name = "Sulphur Esp", description = "Gives Sulphur an esp.", category = "Nether", subcategory = "Mining")
    public boolean sulphurEsp;
    @Property(type = PropertyType.SWITCH, name = "Ashfang Orb Aura", description = "Automatically throw all ashfang orbs on the ashfang.", category = "Nether", subcategory = "Ashfang")
    public boolean ashfangAura;
    @Property(type = PropertyType.SWITCH, name = "Auto Swap To Pickonimbus", description = "Auto swaps to the next pickonimbus after the current one breaking. Can be anywhere in the inventory, but hotbar is faster", category = "Player", subcategory = "Macro")
    public boolean autoPickonimbus;
    @Property(type = PropertyType.SWITCH, name = "Burrow Aura", description = "Click burrows in x range.", category = "Player", subcategory = "Aura")
    public boolean burrowAura;
    @Property(type = PropertyType.DECIMAL_SLIDER, name = "Burrow Aura Reach", description = "Change the range of the burrow aura.", category = "Player", subcategory = "Aura", minF = 4.5f, maxF = 6.0f, decimalPlaces = 2)
    public float burrowAuraReach;
    @Property(type = PropertyType.SWITCH, name = "Skyblock Anti KB", description = "Only works in skyblock.", category = "Player", subcategory = "Other")
    public boolean antiKb;
    @Property(type = PropertyType.SWITCH, name = "Increased Velocity", description = "Increases velocity of stuff like bonzo staff, lava and leaping swords.", category = "Player", subcategory = "Other")
    public boolean increasedVelocity;
    @Property(type = PropertyType.DECIMAL_SLIDER, name = "Increased Velocity Amount", description = "Increase the velocity amount of bonzo staff.", category = "Player", subcategory = "Other", minF = 1.2f, maxF = 2.0f, decimalPlaces = 2)
    public float increasedVelocityAmount;
    @Property(type = PropertyType.SWITCH, name = "Shoot Terminator With Valk", description = "Shoots terminator when left clicking with valk.", category = "Player", subcategory = "Other")
    public boolean terminatorValk;
    @Property(type = PropertyType.SWITCH, name = "Auto Jerry Box", description = "Auto click and close jerry boxes when holding them.", category = "Player", subcategory = "Other")
    public boolean autoJerryBox;
    @Property(type = PropertyType.SLIDER, name = "Auto Jerry Box Extra Delay", description = "Add extra delay to the auto jerry box opening.", category = "Player", subcategory = "Other", max = 400)
    public int autoJerryBoxDelay;
    @Property(type = PropertyType.SLIDER, name = "Auto Jerry Box Extra Closing Delay", description = "Add extra delay to the closing of the jerry box. Useful for higher ping.", category = "Player", subcategory = "Other", max = 400)
    public int autoJerryBoxClosingDelay;
    @Property(type = PropertyType.SLIDER, name = "Auto Jerry Box Extra Open Delay", description = "Add extra delay before opening the jerry box.", category = "Player", subcategory = "Other", max = 400)
    public int autoJerryBoxOpenDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Jerry Box Swap Slot", description = "Auto Swap to a jerry box slot.", category = "Player", subcategory = "Other")
    public boolean autoJerryBoxSwapSlot;
    @Property(type = PropertyType.SWITCH, name = "Funny Enchanting", description = "Haha, Enchanting go brrrrrr. Increase the delay if it breaks sometimes.", category = "Player", subcategory = "Gui", searchTags = { "Enchanting", "Auto Enchanting", "Experiment", "Auto Experiment" })
    public boolean funnyEnchanting;
    @Property(type = PropertyType.SWITCH, name = "Funny Enchanting 3 Superpair Clicks", description = "Auto closes the gui after you get 3 superpair clicks in the experiment.", category = "Player", subcategory = "Gui", searchTags = { "Enchanting", "Auto Enchanting", "Experiment", "Auto Experiment" })
    public boolean funnyEnchantingCloseChest;
    @Property(type = PropertyType.SLIDER, name = "Funny Enchanting Delay", description = "Change the amount of delay when using funny enchanting. Default is 130.", category = "Player", subcategory = "Gui", min = 110, max = 200, searchTags = { "Enchanting", "Auto Enchanting", "Experiment", "Auto Experiment" })
    public int funnyEnchantingDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Harp Macro", description = "Automatically complete harp. If it sucks then change the delay ffs", category = "Player", subcategory = "Gui")
    public boolean autoHarpSolver;
    @Property(type = PropertyType.SLIDER, name = "Harp Macro Delay", description = "Change the delay of the harp macro. Since the harp macro is made different it needs a delay.", category = "Player", subcategory = "Gui", min = 50, max = 250)
    public int harpSolverDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Book Combine", description = "Auto combine books. Will automatically combine if the anvil gui is open.", category = "Player", subcategory = "Gui")
    public boolean autoBookCombine;
    @Property(type = PropertyType.SLIDER, name = "Auto Book Combine Delay", description = "Adds extra delay to auto book combiner. Useful for higher ping", category = "Player", subcategory = "Gui", max = 300)
    public int autoBookCombineDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Salvage", description = "Auto salvage useless items. Will automatically combine if the salvaging gui is open.", category = "Player", subcategory = "Gui")
    public boolean autoSalvage;
    @Property(type = PropertyType.SLIDER, name = "Auto Salvager Delay", description = "Adds extra delay to auto salvager. Useful for higher ping", category = "Player", subcategory = "Gui", max = 300)
    public int autoSalvageDelay;
    @Property(type = PropertyType.SELECTOR, name = "Macro Keybind", description = "Set the macro keybind to do that specific task. Only works with macro key, the item keybind is something completely different.", category = "Macros", subcategory = "Macro", options = { "None", "Farming", "Mithril Macro", "Foraging Macro", "Powder Macro", "Nuker", "Crop Aura", "Commission Macro", "§b[WIP] §fFishing Macro", "Alchemy Macro" }, searchTags = { "Farming", "Mithril Macro", "Foraging Macro", "Gold Macro", "Hardstone Macro", "Powder Macro", "Nuker", "Crop Aura", "Crop Placer Aura", "Commission Macro", "Fishing Macro", "Alchemy Macro" })
    public int macroKey;
    @Property(type = PropertyType.SELECTOR, name = "Farming Macro Keybind", description = "Change the farming macro keybind. You still need to set your macro keybind to \"Farming\".", category = "Macros", subcategory = "Macro", options = { "None", "F11", "Sugar Cane Macro", "S Shaped Farm", "Cocoa Bean" }, searchTags = { "F11", "AutoF11", "Sugar Cane Macro", "S Shaped Farm", "Cocoa Bean" })
    public int farmingMacroKey;
    @Property(type = PropertyType.SWITCH, name = "Sneak While Using Macros", description = "Sneak while using the macros.", category = "Macros", subcategory = "Macro")
    public boolean sneakWhenUsingMacro;
    @Property(type = PropertyType.SLIDER, name = "Foraging Macro Extra Delay", description = "Sets the extra delay of the foraging macro. Use if monkey isn't lvl 100.", category = "Macros", subcategory = "Macro", max = 1500)
    public int foragingMacroDelay;
    @Property(type = PropertyType.SLIDER, name = "Powder Macro Ticks", description = "Change the amount of ticks the powder macro uses for each hardstone block.", category = "Macros", subcategory = "Macro", min = 1, max = 5)
    public int powderMacroTicks;
    @Property(type = PropertyType.SLIDER, name = "Alchemy Macro Delay", description = "Change the delay of the alchemy macro per action. The delays are randomized ofc.", category = "Macros", subcategory = "Macro - Alchemy", min = 150, max = 400)
    public int alchemyMacroDelay;
    @Property(type = PropertyType.SLIDER, name = "AOTV TP Movement Wait", description = "Change the cooldown of the aotv tping for all of the aotv tp's. Useful if you have low mana. (measured in ms)", category = "Macros", subcategory = "Macro - Commisssion", min = 500, max = 5000, searchTags = { "Commission Macro", "Mithril Macro" })
    public int commissionMacroTpWait;
    @Property(type = PropertyType.SLIDER, name = "Mithril Macro Rotate Amount", description = "Change the rotate amount of the mithril and gold macro.", category = "Macros", subcategory = "Macro - Mithril", min = 10, max = 30)
    public int mithrilRotateAmount;
    @Property(type = PropertyType.SELECTOR, name = "Mithril Macro Auto Refuel", description = "Change the mode of the mithril auto refuel.", category = "Macros", subcategory = "Macro - Mithril", options = { "Off", "Normal", "Blatant" })
    public int mithrilAutoRefuel;
    @Property(type = PropertyType.SELECTOR, name = "Mithril Macro Priority", description = "Change the highest mithril macro priority.", category = "Macros", subcategory = "Macro - Mithril", options = { "Wool", "Prismarine", "Clay", "Titanium", "Gold" })
    public int mithrilPriority;
    @Property(type = PropertyType.SELECTOR, name = "Mithril Macro Priority #2", description = "Change the 2nd highest mithril macro priority.", category = "Macros", subcategory = "Macro - Mithril", options = { "Wool", "Prismarine", "Clay", "Titanium", "Gold" })
    public int mithrilPriority2;
    @Property(type = PropertyType.SELECTOR, name = "Mithril Macro Priority #3", description = "Change the 3rd highest mithril macro priority.", category = "Macros", subcategory = "Macro - Mithril", options = { "Wool", "Prismarine", "Clay", "Titanium", "Gold" })
    public int mithrilPriority3;
    @Property(type = PropertyType.SLIDER, name = "Mithril & Gold Macro Fix Time", description = "Change the amount of fix time for mithril and gold macro (in seconds). Use high fix times with low mining speed.", category = "Macros", subcategory = "Macro - Mithril", min = 2, max = 15)
    public int mithrilMacroFixTime;
    @Property(type = PropertyType.SLIDER, name = "Crop Aura Extra BPS", description = "Change the amount of bps when using crop aura. 0 is 20 bps, 20 is 40 bps. Apparently this doesn't ban but not sure.", category = "Macros", subcategory = "Macro - Crop Aura", max = 20)
    public int cropAuraBPS;
    @Property(type = PropertyType.SLIDER, name = "Crop Aura Smooth Rotation", description = "Change the smooth rotation amount of crop aura. Too high might flag watchdog. Default is 45.", category = "Macros", subcategory = "Macro - Crop Aura", min = 30, max = 100)
    public int cropAuraSmoothRotation;
    @Property(type = PropertyType.SELECTOR, name = "Crop Aura Mode", description = "Change the crop aura mode.", category = "Macros", subcategory = "Macro - Crop Aura", options = { "Default", "Sugar Cane", "Cactus", "Cocoa Beans", "Nether Wart" })
    public int cropAuraMode;
    @Property(type = PropertyType.SWITCH, name = "Crop Aura Place Through Blocks", description = "Allows the crop aura to place through blocks. Shouldn't be beamable.", category = "Macros", subcategory = "Macro - Crop Aura")
    public boolean placeThroughBlocks;
    @Property(type = PropertyType.SWITCH, name = "Nuker Fake Rotation", description = "Changes the rotation packets of the nuker to be the rotation of the block. Use if using nuker on hypixel.", category = "Macros", subcategory = "Macro - Nuker")
    public boolean nukerRotationPackets;
    @Property(type = PropertyType.SWITCH, name = "Nuker Only Nuke Reachable Blocks", description = "Only nuke blocks that are actually reachable to the player. Hypixel has no checks for that tho.", category = "Macros", subcategory = "Macro - Nuker")
    public boolean nukeReachableBlocks;
    @Property(type = PropertyType.SELECTOR, name = "Nuker Type", description = "Change the type of the nuker.", category = "Macros", subcategory = "Macro - Nuker", options = { "All", "Gemstone", "Crops", "Gold", "Bed", "Foraging", "Sand", "Ores", "Nether" }, searchTags = { "Gemstone Nuker" })
    public int nukerType;
    @Property(type = PropertyType.SLIDER, name = "§cNuker Extra BPS", description = "§c(Use at own risk!) §fChance to send extra packets per second. Normal amount are 20/tick.", category = "Macros", subcategory = "Macro - Nuker", max = 80)
    public int nukerExtraPackets;
    @Property(type = PropertyType.SWITCH, name = "Gemstone Nuker Glass Panes", description = "Makes the gemstone nuker also mine glass panes.", category = "Macros", subcategory = "Macro - Nuker")
    public boolean gemstoneNukerGlassPanes;
    @Property(type = PropertyType.SELECTOR, name = "Gemstone Nuker Gemstone Type", description = "Change the gemstone type the nuker should focus.", category = "Macros", subcategory = "Macro - Nuker", options = { "All", "Ruby", "Jade", "Amber", "Topaz", "Sapphire", "Amethyst", "Jasper" })
    public int gemstoneNukerType;
    @Property(type = PropertyType.SWITCH, name = "Captcha Warning", description = "Plays a sound if a captcha happens.", category = "Macros", subcategory = "Macro - Failsafe")
    public boolean captchaWarning;
    @Property(type = PropertyType.SWITCH, name = "Failsafe Webhook", description = "Sends a message to the webhook when a failsafe is performed and which failsafe was performed.", category = "Macros", subcategory = "Macro - Failsafes")
    public boolean failsafeWebhook;
    @Property(type = PropertyType.SWITCH, name = "Failsafe Webhook Ping", description = "Pings @everyone when a failsafe is performed.", category = "Macros", subcategory = "Macro - Failsafes")
    public boolean failsafeWebhookPing;
    @Property(type = PropertyType.TEXT, name = "Failsafe Webhook URL", description = "Change the url of the failsafe webhook.", category = "Macros", subcategory = "Macro - Failsafes")
    public String failsafeWebhookName;
    @Property(type = PropertyType.SWITCH, name = "Use Best Failsafes", description = "Always use the best failsafes for the macro. Does not apply for player detection and rotation packet failsafes. I'd recommend keeping this on.", category = "Macros", subcategory = "Macro - Failsafe")
    public boolean optimalFailsafes;
    @Property(type = PropertyType.SWITCH, name = "Disable Macro On Switch World", description = "Disables the current macro when switching world.", category = "Macros", subcategory = "Macro - Failsafes")
    public boolean disableOnWorldLoad;
    @Property(type = PropertyType.SWITCH, name = "Rotation Packet Failsafe", description = "Temporarily disables the macro when receiving a rotation packet.", category = "Macros", subcategory = "Macro - Failsafes")
    public boolean rotationFailsafe;
    @Property(type = PropertyType.SWITCH, name = "TP Failsafe", description = "Temporarily disables the macro when receiving a TP Packet.", category = "Macros", subcategory = "Macro - Failsafes")
    public boolean positionChangeFailsafe;
    @Property(type = PropertyType.SWITCH, name = "Bedrock Box Failsafe", description = "Disables, then reenables the macro if you're trapped in a bedrock box. Use Position Change Failsafe for mithril.", category = "Macros", subcategory = "Macro - Failsafes")
    public boolean bedrockBoxFailsafe;
    @Property(type = PropertyType.SWITCH, name = "Player Detection", description = "Warps out of the lobby if a player is in x range of you.", category = "Macros", subcategory = "Macro - Failsafes")
    public boolean stopWhenNearPlayer;
    @Property(type = PropertyType.SLIDER, name = "Player Detection Range Slider", description = "Change the range of the player detection.", category = "Macros", subcategory = "Macro - Failsafes", min = 5, max = 35)
    public int stopWhenNearPlayerRange;
    @Property(type = PropertyType.SWITCH, name = "Farming Macros Lock Yaw And Pitch", description = "Once toggled, saves the yaw and pitch and will always rotate to that yaw and pitch.", category = "Macros", subcategory = "Macro - Farming")
    public boolean lockYawAndPitch;
    @Property(type = PropertyType.SWITCH, name = "AutoF11 Go Back To Island", description = "Goes back to island if autof11 is toggled.", category = "Macros", subcategory = "Macro - F11")
    public boolean goBackToIs;
    @Property(type = PropertyType.SWITCH, name = "Hold \"W\"", description = "Holds \"W\" (forward) when using AutoF11.", category = "Macros", subcategory = "Macro - F11")
    public boolean holdW;
    @Property(type = PropertyType.SWITCH, name = "Hold \"A\"", description = "Holds \"A\" (left) when using AutoF11.", category = "Macros", subcategory = "Macro - F11")
    public boolean holdA;
    @Property(type = PropertyType.SWITCH, name = "Hold \"D\"", description = "Holds \"D\" (right) when using AutoF11.", category = "Macros", subcategory = "Macro - F11")
    public boolean holdD;
    @Property(type = PropertyType.SWITCH, name = "Hold \"S\"", description = "Holds \"S\" (back) when using AutoF11.", category = "Macros", subcategory = "Macro - F11")
    public boolean holdS;
    @Property(type = PropertyType.SWITCH, name = "Hold Leftclick", description = "Holds Leftclick when using AutoF11.", category = "Macros", subcategory = "Macro - F11")
    public boolean holdLeftClick;
    @Property(type = PropertyType.SWITCH, name = "Toggle Auto Item Key", description = "Toggle the auto item keybind. Can also be done with a keybind.", category = "Keybinds", subcategory = "Auto Item Keybind")
    public boolean autoMacroKeyToggle;
    @Property(type = PropertyType.SWITCH, name = "Auto Macro Key Sync Held Item", description = "Syncs the currently held item by the player with the server instead of waiting 1 tick. Having this off looks less blatant.", category = "Keybinds", subcategory = "Auto Item Keybind")
    public boolean autoMacroKeyToggleSync;
    @Property(type = PropertyType.TEXT, name = "Any Item With Anything Name", description = "Change the item name of the any item with anything keybind. Only has to be part of the display name and isn't case sensitive.", category = "Keybinds", subcategory = "Auto Item Keybind")
    public String anyItemWithAnythingName;
    @Property(type = PropertyType.SELECTOR, name = "Any Item With Anything Action", description = "Change the action of the any item with anything keybind.", category = "Keybinds", subcategory = "Auto Item Keybind", options = { "Right", "Left" })
    public int anyItemWithAnythingAction;
    @Property(type = PropertyType.SLIDER, name = "Any Item With Anything Delay", description = "Change the delay per action. Anything below 50 won't help.", category = "Keybinds", subcategory = "Auto Item Keybind", max = 500)
    public int anyItemWithAnythingDelay;
    @Property(type = PropertyType.SLIDER, name = "Auto Clicker Delay", description = "Change the delay of the autoclicker.", category = "Keybinds", subcategory = "AutoClicker", min = 1, max = 250)
    public int autoClickerDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Clicker Hold", description = "Make the autoclicker be holdable instead of toggleable.", category = "Keybinds", subcategory = "AutoClicker")
    public boolean autoClickerHold;
    @Property(type = PropertyType.SELECTOR, name = "Auto Clicker Type", description = "Change the type of the autoclicker.", category = "Keybinds", subcategory = "AutoClicker", options = { "Left Click", "Right Click", "Right Click Burst", "Use Item" })
    public int autoClickerType;
    @Property(type = PropertyType.SLIDER, name = "Autoclicker Burst Clickamount", description = "Change the clickamount of the burst option.", category = "Keybinds", subcategory = "AutoClicker", min = 25, max = 400)
    public int clickAmount;
    @Property(type = PropertyType.SWITCH, name = "Auschwitz Simulator", description = "Want to see auschwitz in minecraft? say no more!", category = "Jokes", subcategory = "Skin")
    public boolean auschwitzSimulator;
    @Property(type = PropertyType.SWITCH, name = "Pizzaboi Skin", description = "Change all the skins to pizzaboi skin", category = "Jokes", subcategory = "Skin")
    public boolean replaceSkin;
    @Property(type = PropertyType.SWITCH, name = "Funny Puzzle Fail Msg", description = "Shows ur ip in chat when u fail a puzzle. Doesn't send it, only shows it. Wouldn't recommend if ur screensharing or smth", category = "Jokes", subcategory = "Dungeons")
    public boolean funnyDungeonFail;
    @Property(type = PropertyType.SWITCH, name = "Funny Loading Screen", description = "Ever think the loading screen is a bit boring?", category = "Jokes", subcategory = "Loading Screen")
    public boolean funnyLoadingScreen;
    @Property(type = PropertyType.SWITCH, name = "Funny Loading Screen Arabic Text", description = "\u0644\u0627 \u0623\u0639\u0631\u0641 \u0645\u0627 \u064a\u0641\u062a\u0631\u0636 \u0623\u0646 \u064a\u0642\u0648\u0644\u0647 \u0647\u0630\u0627 \u0627\u0644\u0646\u0635 \u060c \u0623\u0631\u064a\u062f \u0641\u0642\u0637 \u0627\u0644\u062d\u0635\u0648\u0644 \u0639\u0644\u0649 \u0646\u0635 \u0639\u0631\u0628\u064a \u0647\u0646\u0627", category = "Jokes", subcategory = "Loading Screen")
    public boolean funnyLoadingScreenArab;
    @Property(type = PropertyType.SWITCH, name = "Funny Loading Screen Trollface", description = "\u28c0\u28e0\u28e4\u28e4\u28e4\u28e4\u28a4\u28e4\u28c4\u28c0\u28c0\u28c0\u28c0\u2840\u2840\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\n\u2804\u2809\u2839\u28fe\u28ff\u28db\u28ff\u28ff\u28de\u28ff\u28db\u28fa\u28fb\u28be\u28fe\u28ff\u28ff\u28ff\u28f6\u28f6\u28f6\u28c4\u2840\u2804\u2804\u2804\n\u2804\u2804\u2820\u28ff\u28f7\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ef\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28c6\u2804\u2804\n\u2804\u2804\u2818\u281b\u281b\u281b\u281b\u280b\u283f\u28f7\u28ff\u28ff\u287f\u28ff\u28bf\u281f\u281f\u281f\u283b\u283b\u28ff\u28ff\u28ff\u28ff\u2840\u2804\n\u2804\u2880\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u289b\u28ff\u28c1\u2804\u2804\u2812\u2802\u2804\u2804\u28c0\u28f0\u28ff\u28ff\u28ff\u28ff\u2840\n\u2804\u2809\u281b\u283a\u28b6\u28f7\u2876\u2803\u2804\u2804\u2828\u28ff\u28ff\u2847\u2804\u287a\u28fe\u28fe\u28fe\u28ff\u28ff\u28ff\u28ff\u28fd\u28ff\u28ff\n\u2804\u2804\u2804\u2804\u2804\u281b\u2801\u2804\u2804\u2804\u2880\u28ff\u28ff\u28e7\u2840\u2804\u2839\u28ff\u28ff\u28ff\u28ff\u28ff\u287f\u28ff\u28fb\u28ff\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u281b\u281f\u2807\u2880\u28b0\u28ff\u28ff\u28ff\u28cf\u2809\u28bf\u28fd\u28bf\u284f\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2820\u2824\u28e4\u28f4\u28fe\u28ff\u28ff\u28fe\u28ff\u28ff\u28e6\u2804\u28b9\u287f\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2812\u28f3\u28f6\u28e4\u28e4\u28c4\u28c0\u28c0\u2848\u28c0\u2881\u2881\u2881\u28c8\u28c4\u2890\u2803\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28f0\u28ff\u28db\u28fb\u287f\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u286f\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u28ec\u28fd\u28ff\u28fb\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u2801\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2898\u28ff\u28ff\u28fb\u28db\u28ff\u287f\u28df\u28fb\u28ff\u28ff\u28ff\u28ff\u285f\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u281b\u289b\u28bf\u28ff\u28ff\u28ff\u28ff\u28ff\u28ff\u28f7\u287f\u2801\u2804\u2804\u2804\n\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2804\u2809\u2809\u2809\u2809\u2808\u2804\u2804\u2804\u2804\u2804\u2804\n", category = "Jokes", subcategory = "Loading Screen")
    public boolean funnyLoadingScreenTrollFace;
    @Property(type = PropertyType.SWITCH, name = "Chest Esp", description = "Creates an esp on chests. Esp will be removed after clicking on the chest.", category = "Visuals", subcategory = "Esp")
    public boolean secretChestEsp;
    @Property(type = PropertyType.SELECTOR, name = "Chest Esp Type", description = "Change the type of esp of the chest.", category = "Visuals", subcategory = "Esp", options = { "Filled", "Outlined" })
    public int chestEspMode;
    @Property(type = PropertyType.COLOR, name = "Chest Esp Color", category = "Visuals", subcategory = "Esp")
    public Color secretChestEspColor;
    @Property(type = PropertyType.SWITCH, name = "All Mob Esp", description = "Creates an esp on all mob armor stands.", category = "Visuals", subcategory = "Esp")
    public boolean allMobEsp;
    @Property(type = PropertyType.SWITCH, name = "Dragon Hitbox ESP", description = "Creates an esp on all dragons.", category = "Visuals", subcategory = "Esp")
    public boolean dragonEsp;
    @Property(type = PropertyType.SWITCH, name = "Remove Annoying Potion Effects", description = "Removes Nausea and all fogs like blindness and lava fog.", category = "Visuals", subcategory = "Render", searchTags = { "Anti Blindness", "Anti Nausea" })
    public boolean antiBlindness;
    @Property(type = PropertyType.SWITCH, name = "Use Glowing Outline ESP", description = "Uses glow outline instead of the normal outline shader. Outline is a bit faster than glow.", category = "Visuals", subcategory = "Render")
    public boolean useGlowShader;
    @Property(type = PropertyType.DECIMAL_SLIDER, name = "RGB Brightness", description = "Change the brightness of both the mod message and rgb names.", category = "Visuals", subcategory = "RGB", minF = 0.5f, maxF = 1.0f, decimalPlaces = 2)
    public float rgbBrightness;
    @Property(type = PropertyType.SELECTOR, name = "Custom Mod Message RGB Color", description = "Change the color gradient of the mod message.", category = "Visuals", subcategory = "Mod Message", options = { "Rainbow", "More Red", "More Blue", "More Green", "Ukranian" })
    public int modMessageColor;
    @Property(type = PropertyType.SWITCH, name = "Toggle ToggleSprint", description = "Toggle ToggleSprint", category = "Visuals", subcategory = "QoL")
    public boolean toggleSprint;
    @Property(type = PropertyType.TEXT, name = "Custom ToggleSprint Name", category = "Visuals", subcategory = "QoL")
    public String customToggleSprintName;
    @Property(type = PropertyType.COLOR, name = "Custom ToggleSprint Color", category = "Visuals", subcategory = "QoL")
    public Color customToggleSprintColor;
    @Property(type = PropertyType.SWITCH, name = "Stop Renderering Hand", description = "Doesn't render the player's hand.", category = "Visuals", subcategory = "QoL")
    public boolean stopRenderingHand;
    @Property(type = PropertyType.SWITCH, name = "Session Protection", description = "Enable session protection. Having this enabled might interfere with a lot of stuff. \n§cNOTE: Allthough it's really good, i can't gurantee a 100% chance of having your sessionid protected.", category = "Other", subcategory = "General")
    public boolean sessionProtection;
    @Property(type = PropertyType.TEXT, name = "API Key", description = "Set ur api key. Required for burrow aura.", category = "Other", subcategory = "General", protectedText = true)
    public String apiKey;
    @Property(type = PropertyType.SWITCH, name = "Keep Focus While Tabbed Out", description = "Cancels the esc menu when tabbed out.", category = "Other", subcategory = "Misc")
    public boolean noEscMenu;
    @Property(type = PropertyType.SWITCH, name = "Hide Horses", category = "Other", subcategory = "Misc")
    public boolean hideHorses;
    @Property(type = PropertyType.SWITCH, name = "Ignore Entities Bounding Box", description = "Ignores all entities so you'll always be facing a block. Useful for building n mining. If you can't interact with entities, this is the reason.", category = "Other", subcategory = "Misc")
    public boolean ignoreEntities;
    @Property(type = PropertyType.SWITCH, name = "Change Cocoa Bean Size", description = "Changes the size of fully grown cocoa beans to 1 block and the size of not fully grown cocoa beans to 0.", category = "Other", subcategory = "Misc")
    public boolean cocoaBeanSize;
    @Property(type = PropertyType.SWITCH, name = "Hit Through Blocks", description = "Hits entites through blocks if there's an entity in range.", category = "Other", subcategory = "Misc")
    public boolean hitThroughBlocks;
    @Property(type = PropertyType.SWITCH, name = "Auto Reconnect", description = "Auto reconnect to hypixel when getting disconnected. The macros always have this feature on.", category = "Other", subcategory = "Misc")
    public boolean autoReconnect;
    @Property(type = PropertyType.SWITCH, name = "Auto Join Skyblock", description = "Automatically join skyblock when in hypixel.", category = "Other", subcategory = "Misc", searchTags = { "Auto Skyblock" })
    public boolean autoSkyblock;
    @Property(type = PropertyType.SWITCH, name = "Block Useless Zombie Swords", description = "Blocks useless zombie swords.", category = "Other", subcategory = "Misc")
    public boolean blockUselessZombieSword;
    @Property(type = PropertyType.SWITCH, name = "Block Alignment", description = "Blocks you from using alignment.", category = "Other", subcategory = "Misc")
    public boolean blockAlignment;
    @Property(type = PropertyType.SWITCH, name = "Get Current Sound", description = "Get current sound playing. Helps to get the name of the sound to silence.", category = "Other", subcategory = "Misc")
    public boolean getCurrentSound;
    @Property(type = PropertyType.TEXT, name = "Chat Spammer", description = "/p [ign] /p disband /p [ign] /p disband", category = "Other", subcategory = "Misc")
    public String chatSpammerName;
    @Property(type = PropertyType.SLIDER, name = "Chat Spammer Delay", description = "Changes the chat spammer delay for each action.", category = "Other", subcategory = "Misc", min = 10, max = 500)
    public int chatSpammerDelay;
    @Property(type = PropertyType.SWITCH, name = "Auto Flipper", description = "Auto buy items.", category = "Other", subcategory = "Exclusive")
    public boolean autoFlipper;
    @Property(type = PropertyType.SLIDER, name = "Auto Flipper Lowest Price", description = "Change the lowest price of the auto flipper. Measured in millions.", category = "Other", subcategory = "Exclusive", min = 10, max = 100)
    public int autoFlipperLowestPrice;
    
    public Config() {
        super(new File("./config/pizzaclient/config.toml"), "PizzaClient", (PropertyCollector)new JVMAnnotationPropertyCollector(), (SortingBehavior)new ConfigSorting());
        this.funnyTerminals = false;
        this.terminalClickMode = 0;
        this.terminalSleepAmount = 130;
        this.terminalFixTime = 350;
        this.autoAlignment = false;
        this.autoSimonSays = false;
        this.autoSimonSaysRange = 4.8f;
        this.autoSimonSaysDelay = 200;
        this.autoAimingDevice = false;
        this.autoClipGhostblocks = false;
        this.spiritBearAura = false;
        this.alwaysAimAtSpiritBear = false;
        this.lividAura = false;
        this.superboomAura = false;
        this.quizAura = false;
        this.autoCloseDungChest = false;
        this.autoOpenFreeChest = false;
        this.sneakAotv = false;
        this.sneakAotvDelay = 100;
        this.autoReady = false;
        this.autoLeapDoor = false;
        this.terminatorClicker = false;
        this.terminatorClickerDelay = 25;
        this.qolIceFill = false;
        this.batEsp = false;
        this.lividEsp = false;
        this.dungeonKeyEsp = false;
        this.starredMobsEsp = false;
        this.starredMobsEspMode = 1;
        this.starredMobsEspRange = 32;
        this.starredMobsEspColor = new Color(255, 150, 0);
        this.hideSuperboom = false;
        this.biggerSecretBlocks = false;
        this.removeCarpets = false;
        this.showHiddenMobs = false;
        this.fasterGhostblocks = 0;
        this.autoSoulcry = false;
        this.autoBlazeDagger = false;
        this.minibossEsp = false;
        this.bossEsp = false;
        this.monolithEsp = false;
        this.balEsp = false;
        this.corleonePing = false;
        this.teamTresuriteEsp = false;
        this.ironGolemEsp = false;
        this.goblinEsp = false;
        this.sludgeEsp = false;
        this.yogEsp = false;
        this.gemstoneScanner = false;
        this.scannerWidth = 32;
        this.scannerDelay = 5;
        this.gemstoneEspSize = 40;
        this.redGlassEsp = false;
        this.orangeGlassEsp = false;
        this.lightBlueGlassEsp = false;
        this.limeGlassEsp = false;
        this.purpleGlassEsp = false;
        this.yellowGlassEsp = false;
        this.pinkGlassEsp = false;
        this.glassPanesEsp = false;
        this.hideGoldenGoblin = false;
        this.coordsList = false;
        this.autoCloseLootChest = false;
        this.powderChestMacro = false;
        this.blazeEsp = false;
        this.blazeEspColor = new Color(0, 131, 255, 191);
        this.whiteGlassEsp = false;
        this.sulphurEsp = false;
        this.ashfangAura = false;
        this.autoPickonimbus = false;
        this.burrowAura = false;
        this.burrowAuraReach = 5.0f;
        this.antiKb = false;
        this.increasedVelocity = false;
        this.increasedVelocityAmount = 1.8f;
        this.terminatorValk = false;
        this.autoJerryBox = false;
        this.autoJerryBoxDelay = 0;
        this.autoJerryBoxClosingDelay = 0;
        this.autoJerryBoxOpenDelay = 0;
        this.autoJerryBoxSwapSlot = false;
        this.funnyEnchanting = false;
        this.funnyEnchantingCloseChest = false;
        this.funnyEnchantingDelay = 130;
        this.autoHarpSolver = false;
        this.harpSolverDelay = 110;
        this.autoBookCombine = false;
        this.autoBookCombineDelay = 0;
        this.autoSalvage = false;
        this.autoSalvageDelay = 0;
        this.macroKey = 0;
        this.farmingMacroKey = 0;
        this.sneakWhenUsingMacro = false;
        this.foragingMacroDelay = 0;
        this.powderMacroTicks = 2;
        this.alchemyMacroDelay = 230;
        this.commissionMacroTpWait = 2200;
        this.mithrilRotateAmount = 15;
        this.mithrilAutoRefuel = 0;
        this.mithrilPriority = 0;
        this.mithrilPriority2 = 1;
        this.mithrilPriority3 = 3;
        this.mithrilMacroFixTime = 4;
        this.cropAuraBPS = 0;
        this.cropAuraSmoothRotation = 45;
        this.cropAuraMode = 0;
        this.placeThroughBlocks = false;
        this.nukerRotationPackets = true;
        this.nukeReachableBlocks = false;
        this.nukerType = 0;
        this.nukerExtraPackets = 0;
        this.gemstoneNukerGlassPanes = true;
        this.gemstoneNukerType = 0;
        this.captchaWarning = false;
        this.failsafeWebhook = false;
        this.failsafeWebhookPing = false;
        this.failsafeWebhookName = "";
        this.optimalFailsafes = true;
        this.disableOnWorldLoad = false;
        this.rotationFailsafe = false;
        this.positionChangeFailsafe = false;
        this.bedrockBoxFailsafe = false;
        this.stopWhenNearPlayer = false;
        this.stopWhenNearPlayerRange = 20;
        this.lockYawAndPitch = true;
        this.goBackToIs = true;
        this.holdW = false;
        this.holdA = false;
        this.holdD = false;
        this.holdS = false;
        this.holdLeftClick = false;
        this.autoMacroKeyToggle = false;
        this.autoMacroKeyToggleSync = false;
        this.anyItemWithAnythingName = "Aspect of the Void";
        this.anyItemWithAnythingAction = 0;
        this.anyItemWithAnythingDelay = 0;
        this.autoClickerDelay = 100;
        this.autoClickerHold = false;
        this.autoClickerType = 1;
        this.clickAmount = 50;
        this.auschwitzSimulator = false;
        this.replaceSkin = false;
        this.funnyDungeonFail = false;
        this.funnyLoadingScreen = true;
        this.funnyLoadingScreenArab = true;
        this.funnyLoadingScreenTrollFace = true;
        this.secretChestEsp = false;
        this.chestEspMode = 1;
        this.secretChestEspColor = new Color(200, 10, 10);
        this.allMobEsp = false;
        this.dragonEsp = false;
        this.antiBlindness = false;
        this.useGlowShader = false;
        this.rgbBrightness = 0.9f;
        this.modMessageColor = 2;
        this.toggleSprint = false;
        this.customToggleSprintName = "pizza clint";
        this.customToggleSprintColor = Color.WHITE;
        this.stopRenderingHand = false;
        this.sessionProtection = true;
        this.apiKey = this.getApiKey();
        this.noEscMenu = false;
        this.hideHorses = false;
        this.ignoreEntities = false;
        this.cocoaBeanSize = false;
        this.hitThroughBlocks = false;
        this.autoReconnect = false;
        this.autoSkyblock = false;
        this.blockUselessZombieSword = false;
        this.blockAlignment = false;
        this.getCurrentSound = false;
        this.chatSpammerName = "";
        this.chatSpammerDelay = 300;
        this.autoFlipper = false;
        this.autoFlipperLowestPrice = 50;
        for (final String s : new String[] { "terminalClickMode", "terminalSleepAmount", "terminalFixTime" }) {
            this.addDependency(s, "funnyTerminals");
        }
        for (final String s : new String[] { "gemstoneEspSize", "redGlassEsp", "orangeGlassEsp", "lightBlueGlassEsp", "limeGlassEsp", "purpleGlassEsp", "yellowGlassEsp", "pinkGlassEsp", "glassPanesEsp" }) {
            this.addDependency(s, "gemstoneScanner");
        }
        this.addDependency("starredMobsEspColor", "starredMobsEsp");
        this.addDependency("starredMobsEspRange", "starredMobsEsp");
        this.addDependency("starredMobsEspMode", "starredMobsEsp");
        this.addDependency("harpSolverDelay", "autoHarpSolver");
        this.addDependency("secretChestEspColor", "secretChestEsp");
        this.addDependency("chestEspMode", "secretChestEsp");
        this.addDependency("stopWhenNearPlayerRange", "stopWhenNearPlayer");
        this.addDependency("funnyEnchantingDelay", "funnyEnchanting");
        this.addDependency("funnyEnchantingCloseChest", "funnyEnchanting");
        this.addDependency("alwaysAimAtSpiritBear", "spiritBearAura");
        this.addDependency("customToggleSprintName", "toggleSprint");
        this.addDependency("customToggleSprintColor", "toggleSprint");
        this.addDependency("autoBookCombineDelay", "autoBookCombine");
        this.addDependency("autoSalvageDelay", "autoSalvage");
        this.addDependency("funnyLoadingScreenTrollFace", "funnyLoadingScreen");
        this.addDependency("funnyLoadingScreenArab", "funnyLoadingScreen");
        this.addDependency("autoJerryBoxDelay", "autoJerryBox");
        this.addDependency("autoJerryBoxClosingDelay", "autoJerryBox");
        this.addDependency("autoJerryBoxSwapSlot", "autoJerryBox");
        this.addDependency("failsafeWebhookName", "failsafeWebhook");
        this.addDependency("failsafeWebhookPing", "failsafeWebhook");
        this.addDependency("terminatorClickerDelay", "terminatorClicker");
        this.addDependency("autoSimonSaysRange", "autoSimonSays");
        this.addDependency("autoSimonSaysDelay", "autoSimonSays");
        this.addDependency("burrowAuraReach", "burrowAura");
        this.addDependency("sneakAotvDelay", "sneakAotv");
        this.addDependency("bloodTriggerBotAimbot", "bloodTriggerBot");
        this.addDependency("blazeEspColor", "blazeEsp");
        this.addDependency("increasedVelocityAmount", "increasedVelocity");
        this.init();
    }
    
    private void init() {
        this.initialize();
        this.markDirty();
        this.preload();
    }
    
    private String getApiKey() {
        final String property = System.getenv("HYPIXEL_KEY");
        return (property == null) ? "" : property;
    }
    
    public static void sendCommandsList() {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.AQUA + "List of commands:\n" + EnumChatFormatting.GREEN + "- /arabfunny: opens a random r/arabfunny video\n" + EnumChatFormatting.GREEN + "- /aotvtest: test the current mithril markers. /aotvtest clear clears all markers\n" + EnumChatFormatting.GREEN + "- /autopet [number or pet name]: opens pets menu and clicks the slot of the number\n" + EnumChatFormatting.GREEN + "- /blockability: blocks the right click ability of the held item\n" + EnumChatFormatting.GREEN + "- /chatspammer: /p [ign] /p disband /p [ign] /p disband /p [ign] /p disband...\n" + EnumChatFormatting.GREEN + "- /fuckcpu: kills ur cpu and memory\n" + EnumChatFormatting.GREEN + "- /itemmacro: changes the custom item macro key option to use the held item as the macrokey\n" + EnumChatFormatting.GREEN + "- /p4: places a block below you on y 63 to stop you from falling into lava\n" + EnumChatFormatting.GREEN + "- /setyaw [yaw or yaw and pitch]: sets your rotation to the yaw you entered in\n" + EnumChatFormatting.GREEN + "- /silencer (alias: silence): Silences a specific sound (get the sound name by a feature in \"Other\")\n"));
    }
}
