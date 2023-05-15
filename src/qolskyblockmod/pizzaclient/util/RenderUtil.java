// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import qolskyblockmod.pizzaclient.util.shader.Shader;
import net.minecraft.util.Vec3;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.Slot;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import net.minecraft.world.World;
import net.minecraft.util.BlockPos;
import net.minecraft.entity.Entity;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.client.renderer.GlStateManager;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.culling.Frustum;
import qolskyblockmod.pizzaclient.mixins.mixin.accessor.AccessorRenderManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderUtil
{
    public static final RenderManager renderManager;
    public static final AccessorRenderManager accessorRender;
    private static final Frustum frustum;
    public static boolean skipEvent;
    public static ScaledResolution resolution;
    
    public static void drawFilledEsp(final AxisAlignedBB aabb, final Color c, final float alphaMultiplier) {
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f * alphaMultiplier);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179084_k();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
    }
    
    public static void drawFilledEsp(final AxisAlignedBB aabb, final Color c) {
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledBoxNoESP(final AxisAlignedBB aabb, final Color c, final float alphaMultiplier) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f * alphaMultiplier);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledBoxNoESP(final AxisAlignedBB aabb, final Color c) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledBoxNoESPWithFrustum(final AxisAlignedBB aabb, final Color c) {
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        if (!RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)) {
            return;
        }
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledEsp(final BlockPos pos, final Color c, final float alphaMultiplier) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f * alphaMultiplier);
        drawFullAABB(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos).func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledEspWithFrustum(final BlockPos pos, final Color c, final float alphaMultiplier) {
        final AxisAlignedBB aabb = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos);
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        if (!RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)) {
            return;
        }
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f * alphaMultiplier);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledEsp(final BlockPos pos, final Color c, final float alphaMultiplier, final float expand) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f * alphaMultiplier);
        drawFullAABB(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos).func_72314_b((double)expand, (double)expand, (double)expand).func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledEspWithFrustum(final BlockPos pos, final Color c, final float alphaMultiplier, final float expand) {
        final AxisAlignedBB aabb = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos).func_72314_b((double)expand, (double)expand, (double)expand);
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        if (!RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)) {
            return;
        }
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f * alphaMultiplier);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledEsp(final BlockPos pos, final Color c) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawFullAABB(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos).func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawFilledEspWithFrustum(final BlockPos pos, final Color c) {
        final AxisAlignedBB aabb = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos);
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        if (!RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)) {
            return;
        }
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawFullAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawOutlinedEsp(final BlockPos pos, final Color c, final float width) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GL11.glLineWidth(width);
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos).func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
    }
    
    public static void drawOutlinedEsp(final BlockPos pos, final Color c) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos).func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
    }
    
    public static void drawOutlinedEspWithFrustum(final BlockPos pos, final Color c, final float width) {
        final AxisAlignedBB aabb = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos);
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        if (!RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)) {
            return;
        }
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GL11.glLineWidth(width);
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
    }
    
    public static void drawOutlinedEspWithFrustum(final BlockPos pos, final Color c) {
        final AxisAlignedBB aabb = PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos);
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        if (!RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)) {
            return;
        }
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
    }
    
    public static void drawOutlinedEsp(final AxisAlignedBB aabb, final Color c) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
    }
    
    public static void drawOutlinedEsp(final AxisAlignedBB aabb, final Color c, final float width) {
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GL11.glLineWidth(width);
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179126_j();
    }
    
    public static void drawOutlinedBoxNoEsp(final AxisAlignedBB aabb, final Color c, final float width) {
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GL11.glLineWidth(width);
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GL11.glLineWidth(1.0f);
    }
    
    public static void drawOutlinedEspWithFrustum(final AxisAlignedBB aabb, final Color c, final float width) {
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        if (!RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)) {
            return;
        }
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GL11.glLineWidth(width);
        GlStateManager.func_179131_c(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        drawOutlinedAABB(aabb.func_72317_d(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n));
        GlStateManager.func_179098_w();
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179126_j();
    }
    
    private static void drawOutlinedAABB(final AxisAlignedBB boundingBox) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldrenderer = tessellator.func_178180_c();
        worldrenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
    }
    
    private static void drawFullAABB(final AxisAlignedBB aabb) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldrenderer = tessellator.func_178180_c();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
        worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
        tessellator.func_78381_a();
    }
    
    public static void drawOnSlot(final Slot slot, final int color) {
        Gui.func_73734_a(slot.field_75223_e, slot.field_75221_f, slot.field_75223_e + 16, slot.field_75221_f + 16, color);
    }
    
    public static void drawOnSlot(final Slot slot, final Color color) {
        Gui.func_73734_a(slot.field_75223_e, slot.field_75221_f, slot.field_75223_e + 16, slot.field_75221_f + 16, color.getRGB());
    }
    
    public static void drawRainbowPath(final List<BlockPos> positions, final float width) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldRenderer = tessellator.func_178180_c();
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        GL11.glTranslated(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n);
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GL11.glLineWidth(width);
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        for (int i = positions.size() - 1; i >= 0; --i) {
            final BlockPos pos = positions.get(i);
            final int color = Color.HSBtoRGB((time - i * 50) % 5000L / 5000.0f, 0.75f, 1.0f);
            worldRenderer.func_181662_b(pos.func_177958_n() + 0.5, pos.func_177956_o() + 0.1, pos.func_177952_p() + 0.5).func_181666_a((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 1.0f).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179126_j();
        GL11.glTranslated(RenderUtil.renderManager.field_78730_l, RenderUtil.renderManager.field_78731_m, RenderUtil.renderManager.field_78728_n);
        GL11.glShadeModel(7424);
    }
    
    public static void drawRainbowLines(final List<Vec3> positions) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldRenderer = tessellator.func_178180_c();
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        GL11.glTranslated(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n);
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        for (int i = positions.size() - 1; i >= 0; --i) {
            final Vec3 pos = positions.get(i);
            final int color = Color.HSBtoRGB((time - i * 50) % 5000L / 5000.0f, 0.75f, 1.0f);
            worldRenderer.func_181662_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c).func_181666_a((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 1.0f).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GL11.glTranslated(RenderUtil.renderManager.field_78730_l, RenderUtil.renderManager.field_78731_m, RenderUtil.renderManager.field_78728_n);
        GL11.glShadeModel(7424);
    }
    
    public static void drawRainbowLines(final List<Vec3> positions, final float width) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldRenderer = tessellator.func_178180_c();
        final long time = System.currentTimeMillis();
        GL11.glShadeModel(7425);
        GL11.glTranslated(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n);
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GL11.glLineWidth(width);
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        for (int i = positions.size() - 1; i >= 0; --i) {
            final Vec3 pos = positions.get(i);
            final int color = Color.HSBtoRGB((time - i * 50) % 5000L / 5000.0f, 0.75f, 1.0f);
            worldRenderer.func_181662_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c).func_181666_a((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 1.0f).func_181675_d();
        }
        tessellator.func_78381_a();
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179126_j();
        GL11.glTranslated(RenderUtil.renderManager.field_78730_l, RenderUtil.renderManager.field_78731_m, RenderUtil.renderManager.field_78728_n);
        GL11.glShadeModel(7424);
    }
    
    public static void draw3DLine(final Vec3 end, final Color color) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldRenderer = tessellator.func_178180_c();
        GL11.glShadeModel(7425);
        GL11.glTranslated(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n);
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179124_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        worldRenderer.func_181662_b(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v).func_181675_d();
        worldRenderer.func_181662_b(end.field_72450_a, end.field_72448_b, end.field_72449_c).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179126_j();
        GL11.glTranslated(RenderUtil.renderManager.field_78730_l, RenderUtil.renderManager.field_78731_m, RenderUtil.renderManager.field_78728_n);
    }
    
    public static void draw3DLine(final Vec3 end, final Color color, final int width) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldRenderer = tessellator.func_178180_c();
        GL11.glShadeModel(7425);
        GL11.glTranslated(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n);
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GL11.glLineWidth((float)width);
        GlStateManager.func_179124_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        worldRenderer.func_181662_b(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v).func_181675_d();
        worldRenderer.func_181662_b(end.field_72450_a, end.field_72448_b, end.field_72449_c).func_181675_d();
        tessellator.func_78381_a();
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179126_j();
        GL11.glTranslated(RenderUtil.renderManager.field_78730_l, RenderUtil.renderManager.field_78731_m, RenderUtil.renderManager.field_78728_n);
    }
    
    public static void draw3DLine(final Vec3 start, final Vec3 end, final Color color) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldRenderer = tessellator.func_178180_c();
        GL11.glShadeModel(7425);
        GL11.glTranslated(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n);
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179124_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        worldRenderer.func_181662_b(start.field_72450_a, start.field_72448_b, start.field_72449_c).func_181675_d();
        worldRenderer.func_181662_b(end.field_72450_a, end.field_72448_b, end.field_72449_c).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179126_j();
        GL11.glTranslated(RenderUtil.renderManager.field_78730_l, RenderUtil.renderManager.field_78731_m, RenderUtil.renderManager.field_78728_n);
    }
    
    public static void draw3DLine(final Vec3 start, final Vec3 end, final Color color, final int width) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldRenderer = tessellator.func_178180_c();
        GL11.glShadeModel(7425);
        GL11.glTranslated(-RenderUtil.renderManager.field_78730_l, -RenderUtil.renderManager.field_78731_m, -RenderUtil.renderManager.field_78728_n);
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GL11.glLineWidth((float)width);
        GlStateManager.func_179124_c(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        worldRenderer.func_181662_b(start.field_72450_a, start.field_72448_b, start.field_72449_c).func_181675_d();
        worldRenderer.func_181662_b(end.field_72450_a, end.field_72448_b, end.field_72449_c).func_181675_d();
        tessellator.func_78381_a();
        GL11.glLineWidth(1.0f);
        GlStateManager.func_179126_j();
        GL11.glTranslated(RenderUtil.renderManager.field_78730_l, RenderUtil.renderManager.field_78731_m, RenderUtil.renderManager.field_78728_n);
    }
    
    public static void drawQuad() {
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(0.0f, (float)RenderUtil.resolution.func_78328_b());
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f((float)RenderUtil.resolution.func_78326_a(), (float)RenderUtil.resolution.func_78328_b());
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f((float)RenderUtil.resolution.func_78326_a(), 0.0f);
        GL11.glEnd();
    }
    
    public static void drawQuad(final ScaledResolution sr) {
        GL11.glBegin(5);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(0.0f, (float)sr.func_78328_b());
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f((float)sr.func_78326_a(), 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f((float)sr.func_78326_a(), (float)sr.func_78328_b());
        GL11.glEnd();
    }
    
    public static void bindFramebufferTexture() {
        GlStateManager.func_179144_i(PizzaClient.mc.func_147110_a().field_147617_g);
    }
    
    public static void resetColor() {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void updateFramebuffers() {
        Shader.updateFramebuffer();
    }
    
    public static Framebuffer updateFramebuffer(final Framebuffer framebuffer) {
        if (framebuffer.field_147621_c != PizzaClient.mc.field_71443_c || framebuffer.field_147618_d != PizzaClient.mc.field_71440_d) {
            framebuffer.func_147608_a();
            return new Framebuffer(PizzaClient.mc.field_71443_c, PizzaClient.mc.field_71440_d, true);
        }
        framebuffer.func_147614_f();
        return framebuffer;
    }
    
    public static Framebuffer updateFramebuffer(final Framebuffer framebuffer, final int customWidth, final int customHeight) {
        if (framebuffer.field_147621_c != customWidth || framebuffer.field_147618_d != customHeight) {
            framebuffer.func_147608_a();
            return new Framebuffer(customWidth, customHeight, true);
        }
        framebuffer.func_147614_f();
        return framebuffer;
    }
    
    public static void startESP() {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
    }
    
    public static void endESP() {
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
    }
    
    public static void startChams() {
        GlStateManager.func_179136_a(1.0f, -2000000.0f);
        GlStateManager.func_179088_q();
        GlStateManager.func_179140_f();
    }
    
    public static void endChams() {
        GlStateManager.func_179136_a(0.0f, 0.0f);
        GlStateManager.func_179113_r();
    }
    
    public static void startESPWithBlend() {
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
    }
    
    public static void endESPWithBlend() {
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179084_k();
    }
    
    public static boolean isInLineOfSight(final Entity ent) {
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        return RenderUtil.frustum.func_78546_a(ent.func_174813_aQ());
    }
    
    public static boolean isInLineOfSight(final AxisAlignedBB aabb) {
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        return RenderUtil.frustum.func_78548_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f);
    }
    
    public static boolean isInLineOfSight(final BlockPos pos) {
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        return RenderUtil.frustum.func_78546_a(PizzaClient.mc.field_71441_e.func_180495_p(pos).func_177230_c().func_180646_a((World)PizzaClient.mc.field_71441_e, pos));
    }
    
    public static boolean isInLineOfSight(final Vec3 pos) {
        final Entity e = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        return RenderUtil.frustum.func_78548_b(pos.field_72450_a - 0.01, pos.field_72448_b - 0.01, pos.field_72449_c - 0.01, pos.field_72450_a + 0.01, pos.field_72448_b + 0.01, pos.field_72449_c + 0.01);
    }
    
    public static void renderEntityNoShadowNoEvent(final Entity entity, final float partialTicks) {
        if (entity.field_70173_aa == 0) {
            entity.field_70142_S = entity.field_70165_t;
            entity.field_70137_T = entity.field_70163_u;
            entity.field_70136_U = entity.field_70161_v;
        }
        final int i = entity.func_70070_b(partialTicks);
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)(i % 65536), (float)(i / 65536));
        doRenderEntityNoShadow(entity, entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * partialTicks - RenderUtil.renderManager.field_78730_l, entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * partialTicks - RenderUtil.renderManager.field_78731_m, entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * partialTicks - RenderUtil.renderManager.field_78728_n, entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * partialTicks, partialTicks);
    }
    
    public static void renderLivingEntityNoShadowNoEvent(final Entity entity, final float partialTicks) {
        if (entity.field_70173_aa == 0) {
            entity.field_70142_S = entity.field_70165_t;
            entity.field_70137_T = entity.field_70163_u;
            entity.field_70136_U = entity.field_70161_v;
        }
        final int i = entity.func_70070_b(partialTicks);
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)(i % 65536), (float)(i / 65536));
        doRenderLivingEntityNoShadow(entity, entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * partialTicks - RenderUtil.renderManager.field_78730_l, entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * partialTicks - RenderUtil.renderManager.field_78731_m, entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * partialTicks - RenderUtil.renderManager.field_78728_n, entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * partialTicks, partialTicks);
    }
    
    public static double getViewerPosX(final float partialTicks) {
        final Entity viewer = PizzaClient.mc.func_175606_aa();
        return viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * partialTicks;
    }
    
    public static double getViewerPosY(final float partialTicks) {
        final Entity viewer = PizzaClient.mc.func_175606_aa();
        return viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * partialTicks;
    }
    
    public static double getViewerPosZ(final float partialTicks) {
        final Entity viewer = PizzaClient.mc.func_175606_aa();
        return viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * partialTicks;
    }
    
    public static void doRenderEntityNoShadow(final Entity entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
        final Render<Entity> render = (Render<Entity>)RenderUtil.renderManager.func_78713_a(entity);
        if (render instanceof RendererLivingEntity) {
            ((RendererLivingEntity)render).func_177086_a(RenderUtil.accessorRender.getRenderOutlines());
        }
        render.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    }
    
    public static void doRenderLivingEntityNoShadow(final Entity entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
        final Render<Entity> render = (Render<Entity>)RenderUtil.renderManager.func_78713_a(entity);
        ((RendererLivingEntity)render).func_177086_a(RenderUtil.accessorRender.getRenderOutlines());
        render.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    }
    
    public static Frustum setupFrustrum() {
        final Entity viewer = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(viewer.field_70165_t, viewer.field_70163_u, viewer.field_70161_v);
        return RenderUtil.frustum;
    }
    
    public static Frustum setupFirstPersonFrustrum() {
        final Entity viewer = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(viewer.field_70165_t, viewer.field_70163_u, viewer.field_70161_v);
        return RenderUtil.frustum;
    }
    
    public static Frustum createFrustrum() {
        final Entity viewer = PizzaClient.mc.func_175606_aa();
        RenderUtil.frustum.func_78547_a(viewer.field_70165_t, viewer.field_70163_u, viewer.field_70161_v);
        return RenderUtil.frustum;
    }
    
    public static FloatBuffer createFloatBuffer(final float[] positions) {
        final FloatBuffer buffer = BufferUtils.createFloatBuffer(positions.length);
        buffer.put(positions);
        buffer.flip();
        return buffer;
    }
    
    public static void color(final int color) {
        GlStateManager.func_179124_c((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f);
    }
    
    public static void colorRainbow() {
        final int color = Color.HSBtoRGB(System.currentTimeMillis() % 3500L / 3500.0f, PizzaClient.config.rgbBrightness, 1.0f);
        GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, 0.4f);
    }
    
    static {
        renderManager = PizzaClient.mc.func_175598_ae();
        accessorRender = (AccessorRenderManager)RenderUtil.renderManager;
        frustum = new Frustum();
        RenderUtil.resolution = new ScaledResolution(PizzaClient.mc);
    }
}
