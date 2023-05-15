// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.dungeons;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.MovingObjectPosition;
import java.util.Iterator;
import net.minecraft.block.state.IBlockState;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotater;
import qolskyblockmod.pizzaclient.util.VecUtil;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;

public class SuperboomAura
{
    private long lastInteractTime;
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        Label_0383: {
            if (PizzaClient.config.superboomAura && PizzaClient.location == Locations.DUNGEON && System.currentTimeMillis() - this.lastInteractTime >= 1500L) {
                for (int x = (int)(PizzaClient.mc.field_71439_g.field_70165_t - 6.0); x < PizzaClient.mc.field_71439_g.field_70165_t + 6.0; ++x) {
                    for (int y = (int)(PizzaClient.mc.field_71439_g.field_70163_u - 6.0); y < PizzaClient.mc.field_71439_g.field_70163_u + 6.0; ++y) {
                        for (int z = (int)(PizzaClient.mc.field_71439_g.field_70161_v - 6.0); z < PizzaClient.mc.field_71439_g.field_70161_v + 6.0; ++z) {
                            final BlockPos pos = new BlockPos(x, y, z);
                            final IBlockState block = PizzaClient.mc.field_71441_e.func_180495_p(pos);
                            if (block.func_177230_c() == Blocks.field_150417_aV && Blocks.field_150417_aV.func_176201_c(block) == BlockStoneBrick.field_176251_N && VecUtil.canReachBlock(pos) && VecUtil.isHittable(pos)) {
                                int count = 0;
                                for (final BlockPos adjacent : BlockPos.func_177980_a(new BlockPos(x - 1, y - 1, z - 1), new BlockPos(x + 1, y + 1, z + 1))) {
                                    final IBlockState state = PizzaClient.mc.field_71441_e.func_180495_p(adjacent);
                                    if (state.func_177230_c() == Blocks.field_150417_aV && Blocks.field_150417_aV.func_176201_c(state) == BlockStoneBrick.field_176251_N) {
                                        ++count;
                                    }
                                }
                                if (count >= 5) {
                                    final MovingObjectPosition hit = VecUtil.getHittableMovingObjectPosition(pos);
                                    if (hit != null) {
                                        FakeRotater.rightClickWithItem(hit.field_72307_f, hit.func_178782_a(), getBoom());
                                        this.lastInteractTime = System.currentTimeMillis();
                                        break Label_0383;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static int getBoom() {
        int superboom = 0;
        final Item tnt = Item.func_150898_a(Blocks.field_150335_W);
        for (int i = 0; i < 8; ++i) {
            final ItemStack stack = PizzaClient.mc.field_71439_g.field_71071_by.field_70462_a[i];
            if (stack != null && stack.func_77973_b() == tnt) {
                final String displayName = stack.func_82833_r();
                if (displayName.contains("Infinityboom")) {
                    return i;
                }
                if (displayName.contains("Superboom")) {
                    superboom = i;
                }
            }
        }
        return superboom;
    }
}
