// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.render;

import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import qolskyblockmod.pizzaclient.util.shader.shaders.ColoredOutlineShader;
import qolskyblockmod.pizzaclient.util.shader.shaders.GlowShader;
import qolskyblockmod.pizzaclient.util.shader.shaders.OutlineShader;
import qolskyblockmod.pizzaclient.util.shader.Shader;
import net.minecraft.client.renderer.GlStateManager;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.awt.Color;
import qolskyblockmod.pizzaclient.util.shader.shaders.TintShader;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraft.entity.Entity;
import java.util.List;
import java.util.Map;

public enum RenderType implements IRenderType
{
    TINT_CHAMS {
        @Override
        public void renderPost() {
            RenderUtil.endChams();
            Shader.endCurrentShader();
        }
    }, 
    CHAMS {
        @Override
        public void renderPost() {
            RenderUtil.endChams();
        }
    };
    
    private static final Map<OutlineRenderer, List<Entity>> outlineRenderer;
    public static IRenderType renderType;
    public static final OutlineRenderer universalOutline;
    
    public static void reset() {
        RenderType.renderType = null;
    }
    
    public static void renderChromaChams(final float alpha) {
        RenderType.renderType = RenderType.TINT_CHAMS;
        RenderUtil.startChams();
        TintShader.instance.applyRainbowTint(alpha);
    }
    
    public static void renderTintChams() {
        RenderType.renderType = RenderType.TINT_CHAMS;
        RenderUtil.startChams();
    }
    
    public static void renderTintChams(final Color c) {
        RenderType.renderType = RenderType.TINT_CHAMS;
        RenderUtil.startChams();
        TintShader.instance.applyTint(c);
    }
    
    public static void renderChams() {
        RenderType.renderType = RenderType.CHAMS;
        RenderUtil.startChams();
    }
    
    public static void onRender(final float partialTicks) {
        RenderUtil.skipEvent = true;
        for (final Map.Entry<OutlineRenderer, List<Entity>> entry : RenderType.outlineRenderer.entrySet()) {
            entry.getKey().framebuffer.clearAndBindFramebuffer();
            for (final Entity entity : entry.getValue()) {
                RenderUtil.renderLivingEntityNoShadowNoEvent(entity, partialTicks);
            }
        }
        RenderUtil.skipEvent = false;
        PizzaClient.mc.func_147110_a().func_147610_a(true);
    }
    
    public static void renderOutlines() {
        GlStateManager.func_179141_d();
        GlStateManager.func_179092_a(516, 0.0f);
        GlStateManager.func_179147_l();
        if (PizzaClient.config.useGlowShader) {
            for (final OutlineRenderer renderer : RenderType.outlineRenderer.keySet()) {
                GlStateManager.func_179144_i(renderer.framebuffer.framebufferTexture);
                Shader.framebuffer.clearAndBindFramebuffer();
                OutlineShader.instance.renderFirst();
                RenderUtil.drawQuad();
                OutlineShader.instance.renderSecond();
                RenderUtil.drawQuad();
                GlStateManager.func_179144_i(Shader.framebuffer.framebufferTexture);
                GlowShader.instance.renderFirst(renderer.renderColor);
                RenderUtil.drawQuad();
                PizzaClient.mc.func_147110_a().func_147610_a(true);
                GlowShader.instance.renderSecond();
                RenderUtil.drawQuad();
                Shader.endCurrentShader();
            }
        }
        else {
            for (final OutlineRenderer renderer : RenderType.outlineRenderer.keySet()) {
                GlStateManager.func_179144_i(renderer.framebuffer.framebufferTexture);
                PizzaClient.mc.func_147110_a().func_147610_a(true);
                ColoredOutlineShader.instance.renderFirst(renderer.renderColor);
                RenderUtil.drawQuad();
                ColoredOutlineShader.instance.renderSecond();
                RenderUtil.drawQuad();
                Shader.endCurrentShader();
            }
        }
        RenderType.outlineRenderer.clear();
    }
    
    public static void addEntity(final OutlineRenderer renderer, final Entity entity) {
        final List<Entity> entities = RenderType.outlineRenderer.get(renderer);
        if (entities == null) {
            RenderType.outlineRenderer.put(renderer, new ArrayList<Entity>(Collections.singletonList(entity)));
        }
        else {
            entities.add(entity);
        }
    }
    
    public static void addEntity(final Entity entity) {
        final List<Entity> entities = RenderType.outlineRenderer.get(RenderType.universalOutline);
        if (entities == null) {
            RenderType.outlineRenderer.put(RenderType.universalOutline, new ArrayList<Entity>(Collections.singletonList(entity)));
        }
        else {
            entities.add(entity);
        }
    }
    
    public static boolean shouldRenderOutlines() {
        return RenderType.outlineRenderer.size() != 0;
    }
    
    public static void addAllEntities(final OutlineRenderer renderer, final List<Entity> entity) {
        final List<Entity> entities = RenderType.outlineRenderer.get(renderer);
        if (entities == null) {
            RenderType.outlineRenderer.put(renderer, new ArrayList<Entity>(entity));
        }
        else {
            entities.addAll(entity);
        }
    }
    
    public static void addAllEntities(final OutlineRenderer renderer, final Set<Entity> entity) {
        final List<Entity> entities = RenderType.outlineRenderer.get(renderer);
        if (entities == null) {
            RenderType.outlineRenderer.put(renderer, new ArrayList<Entity>(entity));
        }
        else {
            entities.addAll(entity);
        }
    }
    
    public static void addAllEntities(final List<Entity> entity) {
        final List<Entity> entities = RenderType.outlineRenderer.get(RenderType.universalOutline);
        if (entities == null) {
            RenderType.outlineRenderer.put(RenderType.universalOutline, new ArrayList<Entity>(entity));
        }
        else {
            entities.addAll(entity);
        }
    }
    
    public static void addAllEntities(final Set<Entity> entity) {
        final List<Entity> entities = RenderType.outlineRenderer.get(RenderType.universalOutline);
        if (entities == null) {
            RenderType.outlineRenderer.put(RenderType.universalOutline, new ArrayList<Entity>(entity));
        }
        else {
            entities.addAll(entity);
        }
    }
    
    public static void setUniversalOutlineColor(final Color c) {
        RenderType.universalOutline.setColor(c);
    }
    
    static {
        outlineRenderer = new HashMap<OutlineRenderer, List<Entity>>();
        universalOutline = new OutlineRenderer();
    }
}
