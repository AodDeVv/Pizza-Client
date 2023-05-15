// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util;

import java.util.Collection;
import java.util.Arrays;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.entity.item.EntityArmorStand;
import qolskyblockmod.pizzaclient.util.misc.runnables.EntityPredicate;
import net.minecraft.entity.Entity;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.gui.inventory.GuiContainer;
import qolskyblockmod.pizzaclient.mixins.mixin.accessor.AccessorMinecraft;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import org.apache.commons.io.IOUtils;
import java.io.BufferedInputStream;
import qolskyblockmod.pizzaclient.features.macros.pathfinding.util.BetterBlockPos;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.Loader;
import java.nio.IntBuffer;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import java.awt.image.BufferedImage;
import net.minecraft.util.MathHelper;
import net.minecraft.client.gui.GuiChat;
import java.io.InputStream;
import net.minecraft.util.ResourceLocation;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.util.StringUtils;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import java.text.NumberFormat;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import java.awt.Desktop;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpGet;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import com.mojang.authlib.properties.Property;
import net.minecraft.tileentity.TileEntitySkull;
import com.google.common.collect.Iterables;
import org.apache.logging.log4j.Level;
import net.minecraftforge.fml.common.FMLLog;
import java.awt.datatransfer.Clipboard;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import net.minecraft.util.EnumChatFormatting;
import java.util.Iterator;
import net.minecraft.client.network.NetworkPlayerInfo;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.entity.player.EntityPlayer;
import java.text.DateFormat;
import net.minecraft.block.Block;
import java.util.Set;
import java.text.DecimalFormat;
import java.util.Random;

public class Utils
{
    public static final Random random;
    public static final DecimalFormat DECIMAL_FORMAT;
    public static final Set<Block> uncollidables;
    public static final String ERROR_MESSAGE;
    public static final String SUCCESS_MESSAGE;
    public static final DateFormat DATE_FORMAT;
    
    public static boolean isInTablist(final EntityPlayer player) {
        for (final NetworkPlayerInfo pi : PizzaClient.mc.func_147114_u().func_175106_d()) {
            if (pi.func_178845_a().getName().equalsIgnoreCase(player.func_70005_c_())) {
                return true;
            }
        }
        return false;
    }
    
    public static String getColouredBoolean(final boolean bool) {
        return bool ? (EnumChatFormatting.GREEN + "On") : (EnumChatFormatting.RED + "Off");
    }
    
    public static void sleep(final int sleeptime) {
        try {
            Thread.sleep(sleeptime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void playSound(final String sound, final double pitch) {
        PizzaClient.mc.field_71439_g.func_85030_a(sound, 1.0f, (float)pitch);
    }
    
    public static void playOrbSound(final double pitch) {
        PizzaClient.mc.field_71439_g.func_85030_a("random.orb", 1.0f, (float)pitch);
    }
    
    public static void playOrbSound() {
        PizzaClient.mc.field_71439_g.func_85030_a("random.orb", 1.0f, 0.5f);
    }
    
    public static void writeToClipboard(final String text, final String successMessage) {
        try {
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            final StringSelection output = new StringSelection(text);
            clipboard.setContents(output, output);
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + successMessage));
        }
        catch (IllegalStateException exception) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "Clipboard not available!"));
            exception.printStackTrace();
        }
    }
    
    public static void writeToClipboard(final String text) {
        try {
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            final StringSelection output = new StringSelection(text);
            clipboard.setContents(output, output);
        }
        catch (Exception e) {
            FMLLog.getLogger().log(Level.ERROR, "Failed to copy to clipboard.");
            e.printStackTrace();
        }
    }
    
    public static int betterRandom(int randomness) {
        int randomNumb = 0;
        while (randomness > 0) {
            randomNumb += (int)(Utils.random.nextGaussian() * randomness);
            --randomness;
        }
        return randomNumb;
    }
    
    public static <T> T firstOrNull(final Iterable<T> iterable) {
        return (T)Iterables.getFirst((Iterable)iterable, (Object)null);
    }
    
    public static boolean isWitherEssence(final TileEntitySkull skull) {
        if (skull.func_145904_a() != 3) {
            return false;
        }
        final Property property = firstOrNull((Iterable<Property>)skull.func_152108_a().getProperties().get((Object)"textures"));
        return property != null && property.getValue().equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=");
    }
    
    public static boolean isSkullTexture(final TileEntitySkull skull, final String texture) {
        if (skull.func_145904_a() != 3) {
            return false;
        }
        final Property property = firstOrNull((Iterable<Property>)skull.func_152108_a().getProperties().get((Object)"textures"));
        return property != null && property.getValue().equals(texture);
    }
    
    public static String readFile(final String file) throws IOException {
        final StringBuilder sb = new StringBuilder();
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
    }
    
    public static String readFile(final File file) {
        try {
            final StringBuilder sb = new StringBuilder();
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            return sb.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static String readURL(final String url) {
        try {
            final HttpGet httpGet = new HttpGet(new URI(url));
            httpGet.addHeader("User-Agent", "Mozilla/5.0");
            final CloseableHttpResponse response = HttpClientBuilder.create().build().execute((HttpUriRequest)httpGet);
            final String s = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
            return s;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static void openUrl(final String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static String[] getStringArrayFromJsonArray(final JsonArray jsonArray) {
        final int arraySize = jsonArray.size();
        final String[] stringArray = new String[arraySize];
        for (int i = 0; i < arraySize; ++i) {
            stringArray[i] = jsonArray.get(i).getAsString();
        }
        return stringArray;
    }
    
    public static List<String> getStringListFromJsonArray(final JsonArray jsonArray) {
        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.size(); ++i) {
            list.add(jsonArray.get(i).getAsString());
        }
        return list;
    }
    
    public static Set<String> getStringSetFromJsonArray(final JsonArray jsonArray) {
        final Set<String> strings = new HashSet<String>();
        for (int i = 0; i < jsonArray.size(); ++i) {
            strings.add(jsonArray.get(i).getAsString());
        }
        return strings;
    }
    
    public static Vec3 scaleVec(final Vec3 vec, final double scale) {
        return new Vec3(vec.field_72450_a * scale, vec.field_72448_b * scale, vec.field_72449_c * scale);
    }
    
    public static Vec3 divideVec(final Vec3 vec, final double scale) {
        return new Vec3(vec.field_72450_a / scale, vec.field_72448_b / scale, vec.field_72449_c / scale);
    }
    
    public static boolean isJar() {
        try {
            Minecraft.class.getDeclaredMethod("func_147121_ag", (Class<?>[])new Class[0]);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public static JsonElement getJson(final String jsonUrl) {
        try {
            final HttpGet httpGet = new HttpGet(new URI(jsonUrl));
            httpGet.addHeader("User-Agent", "Mozilla/5.0");
            final CloseableHttpResponse response = HttpClientBuilder.create().build().execute((HttpUriRequest)httpGet);
            final String s = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
            return new JsonParser().parse(s);
        }
        catch (Exception e) {
            e.printStackTrace();
            return (JsonElement)new JsonObject();
        }
    }
    
    public static JsonElement getJson(final File file) {
        return new JsonParser().parse(readFile(file));
    }
    
    public static String formatValue(final double amount) {
        if (amount >= 1.0E9) {
            return formatValue(amount, 1.0E9, 'b');
        }
        if (amount >= 1000000.0) {
            return formatValue(amount, 1000000.0, 'm');
        }
        if (amount >= 1000.0) {
            return formatValue(amount, 1000.0, 'k');
        }
        return NumberFormat.getInstance().format(amount);
    }
    
    private static String formatValue(final double amount, final double div, final char suffix) {
        return new DecimalFormat(".##").format(amount / div) + suffix;
    }
    
    public static boolean isGoldenGoblin(final EntityOtherPlayerMP entityIn) {
        for (final ItemStack item : entityIn.field_71071_by.field_70460_b) {
            if (item == null) {
                return false;
            }
            final Item armor = item.func_77973_b();
            if (armor != Items.field_151169_ag && armor != Items.field_151171_ah && armor != Items.field_151149_ai && armor != Items.field_151151_aj) {
                return false;
            }
        }
        return true;
    }
    
    public static AxisAlignedBB getBlockBoundingBox(final BlockPos pos) {
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        block.func_180654_a((IBlockAccess)PizzaClient.mc.field_71441_e, pos);
        return block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos);
    }
    
    public static double getXandZDistance(final double x1, final double x2, final double z1, final double z2) {
        final double diffX = x1 - x2;
        final double diffZ = z1 - z2;
        return Math.sqrt(diffX * diffX + diffZ * diffZ);
    }
    
    public static double getXandZDistanceSquared(final double x1, final double x2, final double z1, final double z2) {
        final double diffX = x1 - x2;
        final double diffZ = z1 - z2;
        return diffX * diffX + diffZ * diffZ;
    }
    
    public static double getXandZDistance(final Vec3 position, final Vec3 goal) {
        final double diffX = position.field_72450_a - goal.field_72450_a;
        final double diffZ = position.field_72449_c - goal.field_72449_c;
        return Math.sqrt(diffX * diffX + diffZ * diffZ);
    }
    
    public static double getXandZDistanceSquared(final Vec3 position, final Vec3 goal) {
        final double diffX = position.field_72450_a - goal.field_72450_a;
        final double diffZ = position.field_72449_c - goal.field_72449_c;
        return diffX * diffX + diffZ * diffZ;
    }
    
    public static double getXandZDistance(final double x, final double z) {
        final double diffX = PizzaClient.mc.field_71439_g.field_70165_t - x;
        final double diffZ = PizzaClient.mc.field_71439_g.field_70161_v - z;
        return Math.sqrt(diffX * diffX + diffZ * diffZ);
    }
    
    public static double getXandZDistance(final Vec3 vec) {
        final double diffX = PizzaClient.mc.field_71439_g.field_70165_t - vec.field_72450_a;
        final double diffZ = PizzaClient.mc.field_71439_g.field_70161_v - vec.field_72449_c;
        return Math.sqrt(diffX * diffX + diffZ * diffZ);
    }
    
    public static double getXandZDistanceSquared(final double x, final double z) {
        final double diffX = PizzaClient.mc.field_71439_g.field_70165_t - x;
        final double diffZ = PizzaClient.mc.field_71439_g.field_70161_v - z;
        return diffX * diffX + diffZ * diffZ;
    }
    
    public static double getXandZDistanceSquared(final Vec3 vec) {
        final double diffX = PizzaClient.mc.field_71439_g.field_70165_t - vec.field_72450_a;
        final double diffZ = PizzaClient.mc.field_71439_g.field_70161_v - vec.field_72449_c;
        return diffX * diffX + diffZ * diffZ;
    }
    
    public static double getXandZDistanceSquared(final BlockPos pos, final BlockPos pos2) {
        final double diffX = pos.func_177958_n() - pos2.func_177958_n();
        final double diffZ = pos.func_177952_p() - pos2.func_177952_p();
        return diffX * diffX + diffZ * diffZ;
    }
    
    public static double getXandZDistanceSquared(final Vec3 pos, final BlockPos pos2) {
        final double diffX = pos.field_72450_a - (pos2.func_177958_n() + 0.5);
        final double diffZ = pos.field_72449_c - (pos2.func_177952_p() + 0.5);
        return diffX * diffX + diffZ * diffZ;
    }
    
    public static double getXandZDistanceSquared(final BlockPos pos2, final Vec3 pos) {
        final double diffX = pos.field_72450_a - (pos2.func_177958_n() + 0.5);
        final double diffZ = pos.field_72449_c - (pos2.func_177952_p() + 0.5);
        return diffX * diffX + diffZ * diffZ;
    }
    
    public static Vec3 getMiddleOfAABB(final AxisAlignedBB aabb) {
        return new Vec3((aabb.field_72336_d + aabb.field_72340_a) / 2.0, (aabb.field_72337_e + aabb.field_72338_b) / 2.0, (aabb.field_72334_f + aabb.field_72339_c) / 2.0);
    }
    
    public static Vec3 getEntityEyeHeightAABB(final AxisAlignedBB aabb) {
        return new Vec3((aabb.field_72336_d + aabb.field_72340_a) / 2.0, aabb.field_72338_b + 1.52, (aabb.field_72334_f + aabb.field_72339_c) / 2.0);
    }
    
    public static Vec3 getBottomOfAABB(final AxisAlignedBB aabb) {
        return new Vec3((aabb.field_72336_d + aabb.field_72340_a) / 2.0, aabb.field_72338_b, (aabb.field_72334_f + aabb.field_72339_c) / 2.0);
    }
    
    public static Vec3 getMiddleOfAABB(final BlockPos pos) {
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        block.func_180654_a((IBlockAccess)PizzaClient.mc.field_71441_e, pos);
        return getMiddleOfAABB(block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos));
    }
    
    public static AxisAlignedBB getBlockAABB(final BlockPos pos) {
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        block.func_180654_a((IBlockAccess)PizzaClient.mc.field_71441_e, pos);
        return block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos);
    }
    
    public static AxisAlignedBB getBlockAABB(final Vec3 vec) {
        final BlockPos pos = new BlockPos(vec);
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        block.func_180654_a((IBlockAccess)PizzaClient.mc.field_71441_e, pos);
        return block.func_180646_a((World)PizzaClient.mc.field_71441_e, pos);
    }
    
    public static boolean isAdjacentToWater(final BlockPos pos) {
        return PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(pos.func_177958_n() - 1, pos.func_177956_o(), pos.func_177952_p())).func_177230_c() == Blocks.field_150355_j || PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(pos.func_177958_n() + 1, pos.func_177956_o(), pos.func_177952_p())).func_177230_c() == Blocks.field_150355_j || PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p() - 1)).func_177230_c() == Blocks.field_150355_j || PizzaClient.mc.field_71441_e.func_180495_p(new BlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p() + 1)).func_177230_c() == Blocks.field_150355_j;
    }
    
    public static BlockPos getBlockFromHit(final MovingObjectPosition hit) {
        switch (hit.field_178784_b) {
            case UP: {
                return new BlockPos(hit.field_72307_f.field_72450_a, hit.field_72307_f.field_72448_b - 0.05, hit.field_72307_f.field_72449_c);
            }
            case EAST: {
                return new BlockPos(hit.field_72307_f.field_72450_a - 0.05, hit.field_72307_f.field_72448_b, hit.field_72307_f.field_72449_c);
            }
            case SOUTH: {
                return new BlockPos(hit.field_72307_f.field_72450_a, hit.field_72307_f.field_72448_b, hit.field_72307_f.field_72449_c - 0.05);
            }
            default: {
                return new BlockPos(hit.field_72307_f);
            }
        }
    }
    
    public static EnumFacing getRightEnumfacing(final EnumFacing facing) {
        switch (facing) {
            case NORTH: {
                return EnumFacing.EAST;
            }
            case EAST: {
                return EnumFacing.SOUTH;
            }
            case SOUTH: {
                return EnumFacing.WEST;
            }
            case WEST: {
                return EnumFacing.NORTH;
            }
            default: {
                return EnumFacing.NORTH;
            }
        }
    }
    
    public static EnumFacing getLeftEnumfacing(final EnumFacing facing) {
        switch (facing) {
            case NORTH: {
                return EnumFacing.WEST;
            }
            case EAST: {
                return EnumFacing.NORTH;
            }
            case SOUTH: {
                return EnumFacing.EAST;
            }
            case WEST: {
                return EnumFacing.SOUTH;
            }
            default: {
                return EnumFacing.NORTH;
            }
        }
    }
    
    public static String getEnumfacingString(final EnumFacing enumfacing) {
        switch (enumfacing) {
            case NORTH: {
                return "Towards negative Z";
            }
            case SOUTH: {
                return "Towards positive Z";
            }
            case WEST: {
                return "Towards negative X";
            }
            case EAST: {
                return "Towards positive X";
            }
            default: {
                return "Invalid";
            }
        }
    }
    
    public static void addToggleMessage(final String name, final boolean condition) {
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + name + " is now " + (condition ? (EnumChatFormatting.GREEN + "On") : (EnumChatFormatting.RED + "Off"))));
    }
    
    public static boolean isBlockInAir(BlockPos pos) {
        pos = pos.func_177977_b();
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        block.func_180654_a((IBlockAccess)PizzaClient.mc.field_71441_e, pos);
        return Utils.uncollidables.contains(block);
    }
    
    public static boolean isBlockBlocked(final BlockPos pos) {
        Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177984_a()).func_177230_c();
        if (!block.func_149730_j() || Utils.uncollidables.contains(block)) {
            return false;
        }
        block = PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177978_c()).func_177230_c();
        if (!block.func_149730_j() || Utils.uncollidables.contains(block)) {
            return false;
        }
        block = PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177974_f()).func_177230_c();
        if (!block.func_149730_j() || Utils.uncollidables.contains(block)) {
            return false;
        }
        block = PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177968_d()).func_177230_c();
        if (!block.func_149730_j() || Utils.uncollidables.contains(block)) {
            return false;
        }
        block = PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177976_e()).func_177230_c();
        if (!block.func_149730_j() || Utils.uncollidables.contains(block)) {
            return false;
        }
        block = PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177977_b()).func_177230_c();
        return block.func_149730_j() && !Utils.uncollidables.contains(block);
    }
    
    public static boolean isAdjacentToUncollidable(final BlockPos pos) {
        return PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177978_c()).func_177230_c() != Blocks.field_150350_a || PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177974_f()).func_177230_c() != Blocks.field_150350_a || PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177968_d()).func_177230_c() != Blocks.field_150350_a || PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177976_e()).func_177230_c() != Blocks.field_150350_a;
    }
    
    public static String getContainerName() {
        return ((ContainerChest)PizzaClient.mc.field_71439_g.field_71070_bA).func_85151_d().func_145748_c_().func_150260_c();
    }
    
    public static String getContainerNameTrimmed() {
        return StringUtils.func_76338_a(((ContainerChest)PizzaClient.mc.field_71439_g.field_71070_bA).func_85151_d().func_145748_c_().func_150260_c().trim());
    }
    
    public static String getFormattedDate() {
        final Date date = new Date();
        return new SimpleDateFormat("dd/MM/yyyy").format(date) + ", at " + new SimpleDateFormat("HH:mm aa").format(date);
    }
    
    public static InputStream getResourceInputStream(final ResourceLocation location) throws IOException {
        return PizzaClient.mc.func_110442_L().func_110536_a(location).func_110527_b();
    }
    
    public static boolean isChatOpen() {
        return PizzaClient.mc.field_71462_r instanceof GuiChat;
    }
    
    public static int getChatWidth() {
        return calculateChatboxWidth(PizzaClient.mc.field_71474_y.field_96692_F);
    }
    
    public static int getChatHeight() {
        return calculateChatboxHeight(isChatOpen() ? PizzaClient.mc.field_71474_y.field_96694_H : PizzaClient.mc.field_71474_y.field_96693_G);
    }
    
    public static float getChatScale() {
        return PizzaClient.mc.field_71474_y.field_96691_E;
    }
    
    public static int calculateChatboxWidth(final float scale) {
        final int i = 320;
        final int j = 40;
        return MathHelper.func_76141_d(scale * (i - j) + j);
    }
    
    public static int calculateChatboxHeight(final float scale) {
        final int i = 180;
        final int j = 20;
        return MathHelper.func_76141_d(scale * (i - j) + j);
    }
    
    public static int getLineCount() {
        return getChatHeight() / 9;
    }
    
    public static BufferedImage readBufferedImage(final ResourceLocation location) throws IOException {
        return TextureUtil.func_177053_a(PizzaClient.mc.func_110442_L().func_110536_a(location).func_110527_b());
    }
    
    public static Vec3 toVec(final BlockPos pos) {
        return new Vec3(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.5, pos.func_177952_p() + 0.5);
    }
    
    public static Vec3 toRawVec(final BlockPos pos) {
        return new Vec3((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p());
    }
    
    @Deprecated
    public static boolean onGround() {
        if (!PizzaClient.mc.field_71439_g.field_70122_E) {
            return false;
        }
        final Vec3 player = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
        final BlockPos pos = new BlockPos(player.field_72450_a, (double)MathUtil.ceil(player.field_72448_b), player.field_72449_c).func_177977_b();
        final Block block = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c();
        block.func_180654_a((IBlockAccess)PizzaClient.mc.field_71441_e, pos);
        return !Utils.uncollidables.contains(block);
    }
    
    public static int getSkyblockYear() {
        return MathUtil.floor(System.currentTimeMillis() / 1000.0 - 1.560276E9) / 446400 + 1;
    }
    
    public static <T> T getLastElement(final List<T> list) {
        return list.get(list.size() - 1);
    }
    
    public static double formatDouble(final double value) {
        return MathUtil.round(value * 10000.0) / 10000.0;
    }
    
    public static BufferedImage takeScreenshot() {
        try {
            final Framebuffer buffer = PizzaClient.mc.func_147110_a();
            int width = PizzaClient.mc.field_71443_c;
            int height = PizzaClient.mc.field_71440_d;
            if (OpenGlHelper.func_148822_b()) {
                width = buffer.field_147622_a;
                height = buffer.field_147620_b;
            }
            final int i = width * height;
            final IntBuffer pixelBuffer = BufferUtils.createIntBuffer(i);
            final int[] pixelValues = new int[i];
            GL11.glPixelStorei(3333, 1);
            GL11.glPixelStorei(3317, 1);
            pixelBuffer.clear();
            if (OpenGlHelper.func_148822_b()) {
                GlStateManager.func_179144_i(buffer.field_147617_g);
                GL11.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
            }
            else {
                GL11.glReadPixels(0, 0, width, height, 32993, 33639, pixelBuffer);
            }
            pixelBuffer.get(pixelValues);
            TextureUtil.func_147953_a(pixelValues, width, height);
            BufferedImage bufferedimage;
            if (OpenGlHelper.func_148822_b()) {
                bufferedimage = new BufferedImage(buffer.field_147621_c, buffer.field_147618_d, 1);
                int k;
                for (int j = k = buffer.field_147620_b - buffer.field_147618_d; k < buffer.field_147620_b; ++k) {
                    for (int l = 0; l < buffer.field_147621_c; ++l) {
                        bufferedimage.setRGB(l, k - j, pixelValues[k * buffer.field_147622_a + l]);
                    }
                }
            }
            else {
                bufferedimage = new BufferedImage(width, height, 1);
                bufferedimage.setRGB(0, 0, width, height, pixelValues, 0, width);
            }
            return bufferedimage;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
    public static File getTimestampedPNGFileForDirectory(final File gameDirectory) {
        final String s = Utils.DATE_FORMAT.format(new Date());
        int i = 1;
        File file1;
        while (true) {
            file1 = new File(gameDirectory, s + ((i == 1) ? "" : ("_" + i)) + ".png");
            if (!file1.exists()) {
                break;
            }
            ++i;
        }
        return file1;
    }
    
    public static float formatFloat(final float value) {
        return MathUtil.round(value * 1000.0f) / 1000.0f;
    }
    
    public static Set<String> getModIDs() {
        final Set<String> ids = new HashSet<String>();
        for (final ModContainer container : Loader.instance().getActiveModList()) {
            ids.add(container.getModId());
        }
        return ids;
    }
    
    public static List<BlockPos> getAdjacents(final BlockPos pos) {
        final List<BlockPos> adjacents = new ArrayList<BlockPos>();
        adjacents.add(pos.func_177984_a());
        adjacents.add(pos.func_177977_b());
        adjacents.add(pos.func_177978_c());
        adjacents.add(pos.func_177974_f());
        adjacents.add(pos.func_177968_d());
        adjacents.add(pos.func_177976_e());
        return adjacents;
    }
    
    public static Vec3 getHittableAdjacent(final BlockPos pos) {
        Vec3 hit = VecUtil.getHittableHitVec(pos.func_177984_a());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableHitVec(pos.func_177978_c());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableHitVec(pos.func_177974_f());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableHitVec(pos.func_177968_d());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableHitVec(pos.func_177976_e());
        if (hit != null) {
            return hit;
        }
        return VecUtil.getHittableHitVec(pos.func_177977_b());
    }
    
    public static MovingObjectPosition getHittablePosition(final BlockPos pos) {
        MovingObjectPosition hit = VecUtil.getHittableMovingObjectPosition(pos.func_177984_a());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableMovingObjectPosition(pos.func_177978_c());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableMovingObjectPosition(pos.func_177974_f());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableMovingObjectPosition(pos.func_177968_d());
        if (hit != null) {
            return hit;
        }
        hit = VecUtil.getHittableMovingObjectPosition(pos.func_177976_e());
        if (hit != null) {
            return hit;
        }
        return VecUtil.getHittableMovingObjectPosition(pos.func_177977_b());
    }
    
    public static double squareDistanceToBlockPos(final Vec3 vec, final BlockPos pos) {
        final double d0 = vec.field_72450_a - pos.func_177958_n();
        final double d2 = vec.field_72448_b - pos.func_177956_o();
        final double d3 = vec.field_72449_c - pos.func_177952_p();
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public static double squareDistanceToBlockPos(final BlockPos pos1, final BlockPos pos) {
        final double d0 = pos1.func_177958_n() - pos.func_177958_n();
        final double d2 = pos1.func_177956_o() - pos.func_177956_o();
        final double d3 = pos1.func_177952_p() - pos.func_177952_p();
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public static float getExactDay() {
        return PizzaClient.mc.field_71441_e.func_72820_D() / 24000.0f;
    }
    
    public static BlockPos getClosestInRange(final BlockPos original) {
        if (!Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p(original).func_177230_c())) {
            final Vec3 player = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
            BlockPos closest = null;
            double bestDist = 9.99999999E8;
            for (final BlockPos pos : BlockPos.func_177980_a(original.func_177982_a(-3, -3, -3), original.func_177982_a(3, 3, 3))) {
                if (Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c()) && Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177984_a()).func_177230_c()) && !Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177977_b()).func_177230_c())) {
                    final double dist = squareDistanceToBlockPos(player, pos);
                    if (closest != null && dist >= bestDist) {
                        continue;
                    }
                    closest = pos;
                    bestDist = dist;
                }
            }
            return (closest == null) ? original : closest;
        }
        return original;
    }
    
    public static BetterBlockPos getClosestInRange(final BetterBlockPos original) {
        if (!Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)original).func_177230_c())) {
            final Vec3 player = new Vec3(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v);
            BlockPos closest = null;
            double bestDist = 9.99999999E8;
            for (final BlockPos pos : BlockPos.func_177980_a((BlockPos)original.add(-3, -3, -3), (BlockPos)original.add(3, 3, 3))) {
                if (Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c()) && Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177984_a()).func_177230_c()) && !Utils.uncollidables.contains(PizzaClient.mc.field_71441_e.func_180495_p(pos.func_177977_b()).func_177230_c())) {
                    final double dist = squareDistanceToBlockPos(player, pos);
                    if (closest != null && dist >= bestDist) {
                        continue;
                    }
                    closest = pos;
                    bestDist = dist;
                }
            }
            return (closest == null) ? original : new BetterBlockPos(closest);
        }
        return original;
    }
    
    public static byte[] toByteArray(final BufferedInputStream inputStream) throws IOException {
        byte[] bytes;
        try {
            bytes = IOUtils.toByteArray((InputStream)inputStream);
        }
        finally {
            inputStream.close();
        }
        return bytes;
    }
    
    public static boolean isBlockLoaded(final BlockPos pos) {
        return pos.func_177958_n() >= -30000000 && pos.func_177952_p() >= -30000000 && pos.func_177958_n() < 30000000 && pos.func_177952_p() < 30000000 && pos.func_177956_o() >= 0 && pos.func_177956_o() < 256 && isChunkLoaded(pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
    }
    
    public static boolean isBlockLoaded(final BetterBlockPos pos) {
        return pos.field_177962_a >= -30000000 && pos.field_177961_c >= -30000000 && pos.field_177962_a < 30000000 && pos.field_177961_c < 30000000 && pos.field_177960_b >= 0 && pos.field_177960_b < 256 && isChunkLoaded(pos.field_177962_a >> 4, pos.field_177961_c >> 4);
    }
    
    public static boolean isBlockLoaded(final int x, final int y, final int z) {
        return x >= -30000000 && z >= -30000000 && x < 30000000 && z < 30000000 && y >= 0 && y < 256 && isChunkLoaded(x >> 4, z >> 4);
    }
    
    public static boolean isChunkLoaded(final int x, final int z) {
        return PizzaClient.mc.field_71441_e.func_72863_F().func_73149_a(x, z) && !PizzaClient.mc.field_71441_e.func_72863_F().func_73154_d(x, z).func_76621_g();
    }
    
    public static double distanceToSq(final int x, final int y, final int z, final int x1, final int y1, final int z1) {
        final double d0 = x - x1;
        final double d2 = y - y1;
        final double d3 = z - z1;
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public static double distanceTo(final int x, final int y, final int z, final int x1, final int y1, final int z1) {
        final double d0 = x - x1;
        final double d2 = y - y1;
        final double d3 = z - z1;
        return Math.sqrt(d0 * d0 + d2 * d2 + d3 * d3);
    }
    
    public static double distanceToSq(final int x, final int y, final int z, final BetterBlockPos pos) {
        final double d0 = x - pos.field_177962_a;
        final double d2 = y - pos.field_177960_b;
        final double d3 = z - pos.field_177961_c;
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public static boolean isSameXandZ(final BlockPos pos1, final BlockPos pos2) {
        return pos1.func_177958_n() == pos2.func_177958_n() && pos1.func_177952_p() == pos2.func_177952_p();
    }
    
    public static boolean isUncollidable(final BlockPos pos) {
        final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(pos);
        return state.func_177230_c().func_180640_a((World)PizzaClient.mc.field_71441_e, pos, state) == null;
    }
    
    public static boolean isUncollidable(final BlockPos pos, final IBlockState state) {
        return state.func_177230_c().func_180640_a((World)PizzaClient.mc.field_71441_e, pos, state) == null;
    }
    
    public static boolean isUncollidable(final BlockPos pos, final Block block) {
        return block.func_180640_a((World)PizzaClient.mc.field_71441_e, pos, block.func_176223_P()) == null;
    }
    
    public static boolean isUncollidable(final Block block, final BlockPos pos) {
        return block.func_180640_a((World)PizzaClient.mc.field_71441_e, pos, block.func_176223_P()) == null;
    }
    
    public static void pushOutOfBlocks(final BetterBlockPos pos) {
        Block block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
        if (!isUncollidable(block, pos) || block instanceof BlockLiquid) {
            return;
        }
        --pos.field_177960_b;
        block = PizzaClient.mc.field_71441_e.func_180495_p((BlockPos)pos).func_177230_c();
        if (isUncollidable(block, pos) && !(block instanceof BlockLiquid)) {
            calculatePos(pos);
            return;
        }
        ++pos.field_177960_b;
    }
    
    public static void calculatePos(final BetterBlockPos pos) {
    }
    
    public static float getPartialTicks() {
        return ((AccessorMinecraft)PizzaClient.mc).getTimer().field_74281_c;
    }
    
    public static boolean isStringEmpty(final String s) {
        return s == null || s.isEmpty();
    }
    
    public static boolean equals(final float[] arr1, final float[] arr2) {
        for (int i = 0; i < arr1.length; ++i) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean equals(final char[] arr1, final char[] arr2) {
        for (int i = 0; i < arr1.length; ++i) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isGuiOpen() {
        return PizzaClient.mc.field_71462_r != null && !(PizzaClient.mc.field_71462_r instanceof GuiChat);
    }
    
    public static boolean isInSbMenu() {
        return PizzaClient.mc.field_71462_r instanceof GuiContainer && getContainerNameTrimmed().equals("SkyBlock Menu");
    }
    
    public static void freeMemory() {
        Minecraft.field_71444_a = new byte[0];
        MinecraftForge.EVENT_BUS.post((Event)new WorldEvent.Load((World)PizzaClient.mc.field_71441_e));
        System.gc();
    }
    
    public static String formatMemory(final long amount) {
        final long fixedAmount = MathUtil.abs(amount);
        if (fixedAmount >= 1073741824L) {
            return formatMemory(amount, 1073741824L, "GB");
        }
        if (fixedAmount >= 1048576L) {
            return formatMemory(amount, 1048576L, "MB");
        }
        if (fixedAmount >= 1024L) {
            return formatMemory(amount, 1024L, "KB");
        }
        return NumberFormat.getInstance().format(amount);
    }
    
    private static String formatMemory(final long amount, final long div, final String suffix) {
        return new DecimalFormat(".##").format(amount / (double)div) + suffix;
    }
    
    public static boolean isEntityAlive(final EntityLivingBase entity) {
        return entity.func_110138_aP() > 0.0f;
    }
    
    public static byte[] toByteArray(final BufferedImage bi, final String format) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, format, baos);
            final byte[] bytes = baos.toByteArray();
            return bytes;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean hasArmorStand(final Entity entity, final EntityPredicate predicate) {
        return hasEntity((Class<? extends Entity>)EntityArmorStand.class, new AxisAlignedBB(entity.field_70165_t - 0.4, entity.field_70163_u + 1.0, entity.field_70161_v - 0.4, entity.field_70165_t + 0.4, entity.field_70163_u + 3.0, entity.field_70161_v + 0.4), predicate);
    }
    
    public static boolean hasEntity(final Class<? extends Entity> clazz, final AxisAlignedBB aabb, final EntityPredicate predicate) {
        final int i = MathHelper.func_76128_c(aabb.field_72340_a - 1.0) >> 4;
        final int j = MathHelper.func_76128_c(aabb.field_72336_d + 1.0) >> 4;
        final int k = MathHelper.func_76128_c(aabb.field_72339_c - 1.0) >> 4;
        final int l = MathHelper.func_76128_c(aabb.field_72334_f + 1.0) >> 4;
        for (int i2 = i; i2 <= j; ++i2) {
            for (int j2 = k; j2 <= l; ++j2) {
                if (chunkHasEntity(PizzaClient.mc.field_71441_e.func_72964_e(i2, j2), clazz, aabb, predicate)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean chunkHasEntity(final Chunk chunk, final Class<? extends Entity> clazz, final AxisAlignedBB aabb, final EntityPredicate predicate) {
        final ClassInheritanceMultiMap<Entity>[] entityLists = (ClassInheritanceMultiMap<Entity>[])chunk.func_177429_s();
        int i = MathHelper.func_76128_c((aabb.field_72338_b - World.MAX_ENTITY_RADIUS) / 16.0);
        int j = MathHelper.func_76128_c((aabb.field_72337_e + World.MAX_ENTITY_RADIUS) / 16.0);
        i = MathHelper.func_76125_a(i, 0, entityLists.length - 1);
        j = MathHelper.func_76125_a(j, 0, entityLists.length - 1);
        for (int k = i; k <= j; ++k) {
            if (!entityLists[k].isEmpty()) {
                for (final Entity e : entityLists[k].func_180215_b((Class)clazz)) {
                    if (e.func_174813_aQ().func_72326_a(aabb) && predicate.apply(e)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static String getColorCode(final String s) {
        for (int i = s.length() - 1; i > 0; --i) {
            if (s.charAt(i) == '§') {
                return "§" + s.charAt(i + 1);
            }
        }
        return null;
    }
    
    static {
        random = new Random();
        DECIMAL_FORMAT = new DecimalFormat("0.00");
        uncollidables = new HashSet<Block>(Arrays.asList(Blocks.field_150350_a, Blocks.field_180393_cK, Blocks.field_180394_cL, Blocks.field_150456_au, Blocks.field_150443_bT, Blocks.field_150445_bS, Blocks.field_150452_aw, Blocks.field_150430_aB, Blocks.field_150471_bO, (Block)Blocks.field_150480_ab, Blocks.field_150442_at, (Block)Blocks.field_150353_l, (Block)Blocks.field_150356_k, (Block)Blocks.field_150355_j, (Block)Blocks.field_150358_i, (Block)Blocks.field_150427_aO, Blocks.field_150448_aq, Blocks.field_150408_cc, Blocks.field_150319_E, Blocks.field_150318_D, (Block)Blocks.field_150488_af, (Block)Blocks.field_150436_aH, Blocks.field_150472_an, Blocks.field_150444_as, Blocks.field_150478_aa, Blocks.field_150395_bd, (Block)Blocks.field_150329_H, (Block)Blocks.field_150330_I, (Block)Blocks.field_150337_Q, (Block)Blocks.field_150338_P, Blocks.field_150464_aj, Blocks.field_150388_bm, Blocks.field_150459_bM, Blocks.field_150469_bN, Blocks.field_150345_g, Blocks.field_150393_bb, Blocks.field_150394_bc, (Block)Blocks.field_150328_O, (Block)Blocks.field_150327_N));
        ERROR_MESSAGE = "PizzaClient > " + EnumChatFormatting.RED;
        SUCCESS_MESSAGE = "PizzaClient > " + EnumChatFormatting.GREEN;
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    }
}
