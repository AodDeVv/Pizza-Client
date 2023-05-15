// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.world;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import qolskyblockmod.pizzaclient.core.events.BlockChangeEvent;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.PizzaClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ WorldClient.class })
public class MixinWorldClient
{
    @Inject(method = { "invalidateRegionAndSetBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void onBlockChange(final BlockPos pos, final IBlockState state, final CallbackInfoReturnable<Boolean> cir) {
        if (PizzaClient.mc.field_71441_e == null) {
            return;
        }
        final IBlockState old = PizzaClient.mc.field_71441_e.func_180495_p(pos);
        if (old != state && MinecraftForge.EVENT_BUS.post((Event)new BlockChangeEvent(old, state, pos))) {
            cir.setReturnValue((Object)false);
        }
    }
}
