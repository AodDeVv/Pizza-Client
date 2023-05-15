// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.plugins;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.launch.MixinBootstrap;
import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name(" ")
public class PizzaClientLoadingPlugin implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.pizzaclient.json");
        MixinEnvironment.getCurrentEnvironment().setObfuscationContext("searge");
    }
}
