// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.movement;

import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.util.Vec3;
import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.rotation.rotaters.Rotater;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.util.VecUtil;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.BlockPos;
import java.util.List;

public class AOTVMovement
{
    public static void run(final List<BlockPos> moves, final Runnable tick) {
        if (moves.size() == 0) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "The AOTV Movement moves are empty."));
            return;
        }
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
        final int currentItem = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.checkHotBarForEtherwarp();
        for (final BlockPos pos : moves) {
            final Vec3 hittable = VecUtil.getVeryAccurateHittableHitVec(pos);
            if (tick != null) {
                tick.run();
            }
            if (hittable == null) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Something went wrong with the AOTV Movement, trying again"));
                PizzaClient.mc.field_71439_g.func_71165_d("/hub");
                Utils.sleep(2500);
                while (PizzaClient.location != Locations.DWARVENMINES) {
                    PizzaClient.mc.field_71439_g.func_71165_d("/warp forge");
                    Utils.sleep(1000);
                }
                final BlockPos forge = new BlockPos(0, 149, -69);
                while (!new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v).equals((Object)forge)) {
                    Utils.sleep(1000);
                    PizzaClient.mc.field_71439_g.func_71165_d("/warp forge");
                }
                Utils.sleep(500);
                run(moves, tick);
                return;
            }
            new Rotater(hittable).rotate();
            while (PizzaClient.rotater != null) {
                Utils.sleep(1);
            }
            Utils.sleep(200);
            KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
            Utils.sleep(PizzaClient.config.commissionMacroTpWait);
        }
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = currentItem;
        Utils.sleep(500);
    }
    
    public static void run(final Runnable tick, final BlockPos... moves) {
        if (moves.length == 0) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "The AOTV Movement moves are empty."));
            return;
        }
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
        final int currentItem = PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c;
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = PlayerUtil.checkHotBarForEtherwarp();
        for (final BlockPos pos : moves) {
            final Vec3 hittable = VecUtil.getVeryAccurateHittableHitVec(pos);
            if (tick != null) {
                tick.run();
            }
            if (hittable == null) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Something went wrong with the AOTV Movement, trying again"));
                PizzaClient.mc.field_71439_g.func_71165_d("/hub");
                Utils.sleep(2500);
                while (PizzaClient.location != Locations.DWARVENMINES) {
                    Locations.warpToSb();
                    PizzaClient.mc.field_71439_g.func_71165_d("/warp forge");
                    Utils.sleep(1000);
                }
                final BlockPos forge = new BlockPos(0, 149, -69);
                while (!new BlockPos(PizzaClient.mc.field_71439_g.field_70165_t, PizzaClient.mc.field_71439_g.field_70163_u, PizzaClient.mc.field_71439_g.field_70161_v).equals((Object)forge)) {
                    Utils.sleep(1000);
                    PizzaClient.mc.field_71439_g.func_71165_d("/warp forge");
                }
                Utils.sleep(500);
                run(tick, moves);
                return;
            }
            new Rotater(hittable).setRotationAmount(40).rotate();
            while (PizzaClient.rotater != null) {
                Utils.sleep(1);
            }
            Utils.sleep(200);
            KeyBinding.func_74507_a(PizzaClient.mc.field_71474_y.field_74313_G.func_151463_i());
            Utils.sleep(PizzaClient.config.commissionMacroTpWait);
        }
        KeyBinding.func_74510_a(PizzaClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
        PizzaClient.mc.field_71439_g.field_71071_by.field_70461_c = currentItem;
        Utils.sleep(500);
    }
    
    public static void run(final List<BlockPos> moves) {
        run(moves, null);
    }
    
    public static void tpToRandom(final List<BlockPos> moves, final List<BlockPos> randMoves) {
        if (moves.size() == 0) {
            return;
        }
        final List<BlockPos> addedMoves = new ArrayList<BlockPos>(moves);
        if (randMoves.size() != 0) {
            addedMoves.add(randMoves.get(Utils.random.nextInt(randMoves.size())));
        }
        run(addedMoves, null);
    }
    
    public static void tpToRandom(final List<BlockPos> moves, final List<BlockPos> randMoves, final Runnable tick) {
        if (moves.size() == 0) {
            return;
        }
        final List<BlockPos> addedMoves = new ArrayList<BlockPos>(moves);
        if (randMoves.size() != 0) {
            addedMoves.add(randMoves.get(Utils.random.nextInt(randMoves.size())));
        }
        run(addedMoves, tick);
    }
}
