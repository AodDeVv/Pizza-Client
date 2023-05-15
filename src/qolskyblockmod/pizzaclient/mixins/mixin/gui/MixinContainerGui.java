// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.gui;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraft.inventory.Slot;
import java.util.List;
import qolskyblockmod.pizzaclient.core.events.ChestBackgroundDrawnEvent;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.inventory.ContainerChest;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.inventory.Container;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiScreen;

@Mixin(value = { GuiContainer.class }, priority = 999)
public abstract class MixinContainerGui extends GuiScreen
{
    @Shadow
    public Container field_147002_h;
    
    @Inject(method = { "drawScreen" }, at = { @At("HEAD") })
    private void backgroundDrawn(final CallbackInfo ci) {
        if (this.field_147002_h instanceof ContainerChest) {
            final IInventory chest = ((ContainerChest)this.field_147002_h).func_85151_d();
            MinecraftForge.EVENT_BUS.post((Event)new ChestBackgroundDrawnEvent(this.field_147002_h, StringUtils.func_76338_a(chest.func_145748_c_().func_150260_c().trim()), this.field_147002_h.field_75151_b.size(), this.field_147002_h.field_75151_b, chest));
        }
    }
}
