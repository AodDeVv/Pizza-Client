// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.renderer;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.util.render.RenderType;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLivingBase;

@Mixin(value = { RendererLivingEntity.class }, priority = 900)
public abstract class MixinRendererLiving<T extends EntityLivingBase> extends Render<T>
{
    @Shadow
    protected ModelBase field_77045_g;
    @Shadow
    protected boolean field_177098_i;
    
    @Shadow
    protected abstract float func_77044_a(final T p0, final float p1);
    
    @Shadow
    protected abstract void func_77039_a(final T p0, final double p1, final double p2, final double p3);
    
    @Shadow
    protected abstract void func_77043_a(final T p0, final float p1, final float p2, final float p3);
    
    @Shadow
    protected abstract void func_77041_b(final T p0, final float p1);
    
    @Shadow
    protected abstract boolean func_177088_c(final T p0);
    
    @Shadow
    protected abstract void func_77036_a(final T p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    @Shadow
    protected abstract boolean func_177090_c(final T p0, final float p1);
    
    @Shadow
    protected abstract void func_177093_a(final T p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final float p7);
    
    protected MixinRendererLiving(final RenderManager renderManager) {
        super(renderManager);
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V") })
    private void renderEntityOutlinesPost(final CallbackInfo ci) {
        if (RenderType.renderType != null) {
            RenderType.renderType.renderPost();
            RenderType.reset();
        }
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V" }, at = { @At("HEAD") }, cancellable = true)
    private void cancelIfNoEvent(final T entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo ci) {
        if (RenderUtil.skipEvent) {
            GL11.glPushMatrix();
            GlStateManager.func_179129_p();
            this.field_77045_g.field_78095_p = entity.func_70678_g(partialTicks);
            final boolean shouldSit = entity.func_70115_ae() && entity.field_70154_o != null && entity.field_70154_o.shouldRiderSit();
            this.field_77045_g.field_78093_q = shouldSit;
            this.field_77045_g.field_78091_s = entity.func_70631_g_();
            float f = this.interpolateRotation(entity.field_70760_ar, entity.field_70761_aq, partialTicks);
            final float f2 = this.interpolateRotation(entity.field_70758_at, entity.field_70759_as, partialTicks);
            float f3 = f2 - f;
            if (shouldSit && entity.field_70154_o instanceof EntityLivingBase) {
                final EntityLivingBase entitylivingbase = (EntityLivingBase)entity.field_70154_o;
                f = this.interpolateRotation(entitylivingbase.field_70760_ar, entitylivingbase.field_70761_aq, partialTicks);
                f3 = f2 - f;
                float f4 = MathHelper.func_76142_g(f3);
                if (f4 < -85.0f) {
                    f4 = -85.0f;
                }
                if (f4 >= 85.0f) {
                    f4 = 85.0f;
                }
                f = f2 - f4;
                if (f4 * f4 > 2500.0f) {
                    f += f4 * 0.2f;
                }
            }
            final float f5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
            this.func_77039_a(entity, x, y, z);
            final float f6 = this.func_77044_a(entity, partialTicks);
            this.func_77043_a(entity, f6, f, partialTicks);
            GlStateManager.func_179091_B();
            GL11.glScalef(-1.0f, -1.0f, 1.0f);
            this.func_77041_b(entity, partialTicks);
            GL11.glTranslatef(0.0f, -1.5078125f, 0.0f);
            float f7 = entity.field_70722_aY + (entity.field_70721_aZ - entity.field_70722_aY) * partialTicks;
            float f8 = entity.field_70754_ba - entity.field_70721_aZ * (1.0f - partialTicks);
            if (entity.func_70631_g_()) {
                f8 *= 3.0f;
            }
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GlStateManager.func_179141_d();
            this.field_77045_g.func_78086_a((EntityLivingBase)entity, f8, f7, partialTicks);
            this.field_77045_g.func_78087_a(f8, f7, f6, f3, f5, 0.0625f, (Entity)entity);
            if (this.field_177098_i) {
                final boolean flag1 = this.func_177088_c(entity);
                this.func_77036_a(entity, f8, f7, f6, f3, f5, 0.0625f);
                if (flag1) {
                    this.unsetScoreTeamColor();
                }
            }
            else {
                final boolean flag2 = this.func_177090_c(entity, partialTicks);
                this.func_77036_a(entity, f8, f7, f6, f3, f5, 0.0625f);
                if (flag2) {
                    this.unsetBrightness();
                }
                GlStateManager.func_179132_a(true);
                if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).func_175149_v()) {
                    this.func_177093_a(entity, f8, f7, partialTicks, f6, f3, f5, 0.0625f);
                }
            }
            GlStateManager.func_179101_C();
            GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
            GlStateManager.func_179098_w();
            GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
            GlStateManager.func_179089_o();
            GL11.glPopMatrix();
            ci.cancel();
        }
    }
    
    protected float interpolateRotation(final float par1, final float par2, final float par3) {
        float f;
        for (f = par2 - par1; f < -180.0f; f += 360.0f) {}
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        return par1 + par3 * f;
    }
    
    protected void unsetScoreTeamColor() {
        GlStateManager.func_179145_e();
        GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
        GlStateManager.func_179098_w();
        GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
        GlStateManager.func_179098_w();
        GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
    }
    
    protected void unsetBrightness() {
        GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
        GlStateManager.func_179098_w();
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_77478_a);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_77478_a);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176079_G, OpenGlHelper.field_176093_u);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176086_J, 770);
        GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179138_g(OpenGlHelper.field_176096_r);
        GlStateManager.func_179090_x();
        GlStateManager.func_179144_i(0);
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
        GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
        GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
    }
}
