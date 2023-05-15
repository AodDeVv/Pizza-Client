// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.misc;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.Unique;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { SplashProgress.class }, priority = 2000)
public class MixinSplashProgress
{
    @Unique
    private static final ResourceLocation troll;
    @Unique
    private static final ResourceLocation arabic;
    
    @ModifyVariable(method = { "start" }, at = @At("STORE"), ordinal = 1, remap = false)
    @Dynamic
    private static ResourceLocation setForgeTitle(final ResourceLocation original) {
        try {
            return (PizzaClient.config.funnyLoadingScreen && PizzaClient.config.funnyLoadingScreenTrollFace) ? MixinSplashProgress.troll : original;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to apply custom splash screen.");
            return original;
        }
    }
    
    @ModifyVariable(method = { "start" }, at = @At("STORE"), ordinal = 0, remap = false)
    @Dynamic
    private static ResourceLocation setForgeUnicode(final ResourceLocation original) {
        try {
            return (PizzaClient.config.funnyLoadingScreen && PizzaClient.config.funnyLoadingScreenArab) ? MixinSplashProgress.arabic : original;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to apply custom splash screen.");
            return original;
        }
    }
    
    static {
        troll = new ResourceLocation("pizzaclient", "splash/troll.png");
        arabic = new ResourceLocation("pizzaclient", "splash/arabic.png");
    }
}
