// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.accessor;

import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.util.Timer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public interface AccessorMinecraft
{
    @Accessor("timer")
    Timer getTimer();
    
    @Invoker("rightClickMouse")
    void invokeRightClick();
    
    @Invoker("clickMouse")
    void invokeLeftClick();
}
