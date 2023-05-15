// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.entity;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Unique;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.player.EntityPlayer;

@Mixin({ AbstractClientPlayer.class })
public abstract class MixinAbstractPlayerClient extends EntityPlayer
{
    @Unique
    private final ResourceLocation skin;
    @Unique
    private final ResourceLocation nice_guy;
    
    public MixinAbstractPlayerClient(final World worldIn, final GameProfile gameProfileIn) {
        super(worldIn, gameProfileIn);
        this.skin = new ResourceLocation("pizzaclient", "skin/skin.png");
        this.nice_guy = new ResourceLocation("pizzaclient", "skin/auschwitz/nice_guy.png");
    }
    
    @Inject(method = { "getLocationSkin()Lnet/minecraft/util/ResourceLocation;" }, at = { @At("RETURN") }, cancellable = true)
    private void replaceSkin(final CallbackInfoReturnable<ResourceLocation> cir) {
        if (PizzaClient.config.auschwitzSimulator) {
            if (PizzaClient.mc.field_71439_g.func_145782_y() == this.func_145782_y()) {
                cir.setReturnValue((Object)this.nice_guy);
            }
            else {
                cir.setReturnValue((Object)new ResourceLocation("pizzaclient", "skin/auschwitz/jew" + MathUtil.abs(this.func_70005_c_().hashCode() % 2) + ".png"));
            }
        }
        else if (PizzaClient.config.replaceSkin) {
            cir.setReturnValue((Object)this.skin);
        }
    }
}
