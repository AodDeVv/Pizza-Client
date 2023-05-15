// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.skills;

import qolskyblockmod.pizzaclient.util.graphics.font.renderer.FontUtil;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiManager;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiLocation;
import qolskyblockmod.pizzaclient.core.guioverlay.GuiElement;
import qolskyblockmod.pizzaclient.core.events.WorldChangeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.Entity;
import qolskyblockmod.pizzaclient.util.shader.shaders.TintShader;
import qolskyblockmod.pizzaclient.util.render.RenderType;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StringUtils;
import net.minecraft.entity.item.EntityArmorStand;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import java.util.ArrayList;

public class MiningFeatures
{
    private static boolean sayCoordsBal;
    protected static boolean sayCoordsFairyGrotto;
    private static boolean sayCoordsCorleone;
    public static boolean foundCorleone;
    protected static final ArrayList<String> miningCoords;
    
    public MiningFeatures() {
        new MiningElement();
    }
    
    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.LOWEST)
    public void onRenderEntity(final RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (PizzaClient.location == Locations.CHOLLOWS || PizzaClient.location == Locations.DWARVENMINES) {
            final double x = event.entity.field_70165_t;
            final double y = event.entity.field_70163_u;
            final double z = event.entity.field_70161_v;
            if (event.entity instanceof EntityArmorStand) {
                final EntityArmorStand entity = (EntityArmorStand)event.entity;
                if (!entity.func_145818_k_()) {
                    return;
                }
                final String entityName = StringUtils.func_76338_a(entity.func_95999_t());
                if (PizzaClient.config.balEsp && entityName.equals("[Lv100] Bal ???\u2764")) {
                    RenderUtil.drawOutlinedEsp(new AxisAlignedBB(x - 4.0, y - 10.0, z - 4.0, x + 4.0, y, z + 4.0), Color.RED, 5.0f);
                    if (MiningFeatures.sayCoordsBal) {
                        Utils.playSound("random.orb", 0.5);
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Bal in: " + EnumChatFormatting.RED + "X = " + (int)x + ", Y = " + (int)y + ", Z = " + (int)z));
                        MiningFeatures.miningCoords.add("§cBal: §ax " + (int)x + ", y " + (int)y + ", z " + (int)z);
                        MiningFeatures.sayCoordsBal = false;
                    }
                    return;
                }
                if (PizzaClient.config.corleonePing && entityName.startsWith("[Lv200] Boss Corleone ") && MiningFeatures.sayCoordsCorleone) {
                    Utils.playSound("random.orb", 0.5);
                    PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Boss Corleone in: " + EnumChatFormatting.RED + "X = " + (int)x + ", Y = " + (int)y + ", Z = " + (int)z));
                    MiningFeatures.miningCoords.add("§bBoss Corleone: §ax " + (int)x + ", y " + (int)y + ", z " + (int)z);
                    MiningFeatures.sayCoordsCorleone = false;
                }
            }
            else if (event.entity instanceof EntityOtherPlayerMP) {
                final String entityName2 = event.entity.func_70005_c_();
                if (PizzaClient.config.teamTresuriteEsp && entityName2.equals("Team Treasurite")) {
                    RenderType.renderTintChams();
                    TintShader.instance.applyTint(new Color(255, 255, 51, 191));
                    event.setCanceled(false);
                    return;
                }
                if (PizzaClient.config.goblinEsp) {
                    if (entityName2.startsWith("Weakling")) {
                        RenderType.renderTintChams();
                        TintShader.instance.applyTint(new Color(130, 67, 0, 191));
                        event.setCanceled(false);
                        return;
                    }
                    if (entityName2.startsWith("Goblin")) {
                        RenderType.renderTintChams();
                        TintShader.instance.applyTint(new Color(130, 67, 0, 191));
                        event.setCanceled(false);
                        return;
                    }
                }
                if (PizzaClient.config.hideGoldenGoblin && entityName2.startsWith("Goblin") && Utils.isGoldenGoblin((EntityOtherPlayerMP)event.entity)) {
                    PizzaClient.mc.field_71441_e.func_72900_e((Entity)event.entity);
                    event.setCanceled(true);
                }
            }
            else if (event.entity instanceof EntitySlime) {
                if (event.entity instanceof EntityMagmaCube) {
                    if (PizzaClient.config.yogEsp) {
                        RenderType.renderTintChams();
                        TintShader.instance.applyTint(new Color(175, 34, 34, 191));
                        event.setCanceled(false);
                    }
                }
                else if (PizzaClient.config.sludgeEsp) {
                    RenderType.renderTintChams();
                    TintShader.instance.applyTint(new Color(50, 200, 50, 191));
                    event.setCanceled(false);
                }
            }
            else if (event.entity instanceof EntityIronGolem && PizzaClient.config.ironGolemEsp) {
                RenderType.renderTintChams();
                TintShader.instance.applyTint(new Color(0, 0, 111, 191));
                event.setCanceled(false);
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldChangeEvent event) {
        MiningFeatures.sayCoordsBal = true;
        MiningFeatures.sayCoordsFairyGrotto = true;
        MiningFeatures.sayCoordsCorleone = true;
        MiningFeatures.foundCorleone = false;
        MiningFeatures.miningCoords.clear();
        WorldScanner.markedBlocks.clear();
    }
    
    static {
        MiningFeatures.sayCoordsBal = true;
        MiningFeatures.sayCoordsFairyGrotto = true;
        MiningFeatures.sayCoordsCorleone = true;
        MiningFeatures.foundCorleone = false;
        miningCoords = new ArrayList<String>();
    }
    
    public static class MiningElement extends GuiElement
    {
        public MiningElement() {
            super(new GuiLocation(100, 30));
            GuiManager.registerElement(this);
        }
        
        @Override
        public String getName() {
            return "Mining List";
        }
        
        @Override
        public void render() {
            if (PizzaClient.config.coordsList && PizzaClient.location == Locations.CHOLLOWS) {
                for (int i = 0; i < MiningFeatures.miningCoords.size(); ++i) {
                    FontUtil.drawString(MiningFeatures.miningCoords.get(i), this.getActualX(), this.getActualY() + i * PizzaClient.mc.field_71466_p.field_78288_b, 16777215);
                }
            }
        }
        
        @Override
        public void demoRender() {
            final String[] locations = { "§dFairy Grotto: §ax 1000, y 256, z 1000", "§bBoss Corleone: §ax 1000, y 256, z 1000", "§cBal: §ax 1000, y 256, z 1000" };
            for (int i = 0; i < locations.length; ++i) {
                FontUtil.drawString(locations[i], this.getActualX(), this.getActualY() + i * PizzaClient.mc.field_71466_p.field_78288_b, 16777215);
            }
        }
        
        @Override
        public boolean isToggled() {
            return PizzaClient.config.coordsList && PizzaClient.location == Locations.CHOLLOWS;
        }
        
        @Override
        public int getHeight() {
            return PizzaClient.mc.field_71466_p.field_78288_b * 3;
        }
        
        @Override
        public int getWidth() {
            return PizzaClient.mc.field_71466_p.func_78256_a("Boss Corleone: x -1000, y 200, z -1000 ");
        }
    }
}
