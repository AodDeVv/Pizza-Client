// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.plugins;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.List;
import java.util.Set;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;

public class PizzaClientMixinPlugin implements IMixinConfigPlugin
{
    public void onLoad(final String mixinPackage) {
    }
    
    public String getRefMapperConfig() {
        return null;
    }
    
    public boolean shouldApplyMixin(final String targetClassName, final String mixinClassName) {
        return mixinClassName.startsWith("qolskyblockmod.pizzaclient.mixins");
    }
    
    public void acceptTargets(final Set<String> myTargets, final Set<String> otherTargets) {
    }
    
    public List<String> getMixins() {
        return null;
    }
    
    public void preApply(final String targetClassName, final ClassNode targetClass, final String mixinClassName, final IMixinInfo mixinInfo) {
    }
    
    public void postApply(final String targetClassName, final ClassNode targetClass, final String mixinClassName, final IMixinInfo mixinInfo) {
    }
}
